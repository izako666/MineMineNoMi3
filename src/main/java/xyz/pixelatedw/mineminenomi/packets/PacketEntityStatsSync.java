package xyz.pixelatedw.mineminenomi.packets;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.ModMain;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;

public class PacketEntityStatsSync
{
	private INBT data;

	public PacketEntityStatsSync() {}
	
	public PacketEntityStatsSync(IEntityStats entityStatsProps)
	{
		this.data = new CompoundNBT();
		this.data = EntityStatsCapability.INSTANCE.getStorage().writeNBT(EntityStatsCapability.INSTANCE, entityStatsProps, null);
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeCompoundTag((CompoundNBT) data);
	}
	
	public static PacketEntityStatsSync decode(PacketBuffer buffer)
	{
		PacketEntityStatsSync msg = new PacketEntityStatsSync();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(PacketEntityStatsSync message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IEntityStats entityStatsProps = EntityStatsCapability.get(player);

				EntityStatsCapability.INSTANCE.getStorage().readNBT(EntityStatsCapability.INSTANCE, entityStatsProps, null, message.data);
								
			});			
		}
		else if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ModMain.proxy.getClientPlayer();
				IEntityStats entityStatsProps = EntityStatsCapability.get(player);
				
				EntityStatsCapability.INSTANCE.getStorage().readNBT(EntityStatsCapability.INSTANCE, entityStatsProps, null, message.data);
								
			});	
		}
		ctx.get().setPacketHandled(true);
	}

}
