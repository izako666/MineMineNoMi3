package xyz.pixelatedw.mineminenomi.packets;

import java.util.function.Supplier;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.ModMain;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.ExtraEffectCapability;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.IExtraEffect;

public class PacketClientSyncAll
{

	private int entityId = 0;
	private INBT data;
	private byte sync;

	public PacketClientSyncAll()
	{
	}

	/*
	 * First Byte = Devil Fruits
	 * Second Byte = Entity Stats
	 * Third Byte = Ability Data
	 * Forth Byte = Haki Data
	 * Fifth Byte = Extra Effects
	 * Sixth Byte =
	 * Seventh Byte =
	 * Eight Byte =
	 */
	public PacketClientSyncAll(int entityId, IExtraEffect data, byte sync)
	{
		this.entityId = entityId;
		this.data = new CompoundNBT();
		this.data = ExtraEffectCapability.INSTANCE.getStorage().writeNBT(ExtraEffectCapability.INSTANCE, data, null);
		this.sync = sync;
	}
	
	public PacketClientSyncAll(int entityId, IDevilFruit data, byte sync)
	{
		this.entityId = entityId;
		this.data = new CompoundNBT();
		this.data = DevilFruitCapability.INSTANCE.getStorage().writeNBT(DevilFruitCapability.INSTANCE, data, null);
		this.sync = sync;
	}

	public PacketClientSyncAll(int entityId, IEntityStats data, byte sync)
	{
		this.entityId = entityId;
		this.data = new CompoundNBT();
		this.data = EntityStatsCapability.INSTANCE.getStorage().writeNBT(EntityStatsCapability.INSTANCE, data, null);
		this.sync = sync;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.entityId);
		buffer.writeCompoundTag((CompoundNBT) this.data);
		buffer.writeByte(this.sync);
	}

	public static PacketClientSyncAll decode(PacketBuffer buffer)
	{
		PacketClientSyncAll msg = new PacketClientSyncAll();
		msg.entityId = buffer.readInt();
		msg.data = buffer.readCompoundTag();
		msg.sync = buffer.readByte();
		return msg;
	}

	public static void handle(PacketClientSyncAll message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ModMain.proxy.getClientPlayer();
				World world = ModMain.proxy.getClientWorld();
								
				if(message.entityId > 0)
				{
					Entity entity = world.getEntityByID(message.entityId);

					if(entity instanceof LivingEntity)
					{
						if(((message.sync >> 0) & 1) == 1)
						{
							IDevilFruit props = DevilFruitCapability.get((LivingEntity) entity);				
							DevilFruitCapability.INSTANCE.getStorage().readNBT(DevilFruitCapability.INSTANCE, props, null, message.data);							
						}
						if(((message.sync >> 1) & 1) == 1)
						{
							IEntityStats props = EntityStatsCapability.get((LivingEntity) entity);				
							EntityStatsCapability.INSTANCE.getStorage().readNBT(EntityStatsCapability.INSTANCE, props, null, message.data);							
						}					
						if(((message.sync >> 4) & 1) == 1)
						{
							IExtraEffect props = ExtraEffectCapability.get((LivingEntity) entity);				
							ExtraEffectCapability.INSTANCE.getStorage().readNBT(ExtraEffectCapability.INSTANCE, props, null, message.data);
						}
					}
				}
				
			});
		}
		ctx.get().setPacketHandled(true);
	}

}
