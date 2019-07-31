package xyz.pixelatedw.mineminenomi.packets;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.api.network.PacketAbilityDataSync;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.ExtraEffectCapability;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.IExtraEffect;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;

public class PacketForceSync
{
	private byte sync;

	public PacketForceSync() {}
	
	/*  
	 	First Byte = Devil Fruits
	 	Second Byte = Entity Stats
	 	Third Byte = Ability Data
	 	Forth Byte = Haki Data
	 	Fifth Byte = Extra Effects
	 	Sixth Byte = 
	 	Seventh Byte = 
	 	Eight Byte = 
	 */
	public PacketForceSync(byte sync)
	{
		this.sync = sync;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeByte(this.sync);
	}
	
	public static PacketForceSync decode(PacketBuffer buffer)
	{
		PacketForceSync msg = new PacketForceSync();
		msg.sync = buffer.readByte();
		return msg;
	}

	public static void handle(PacketForceSync message, final Supplier<NetworkEvent.Context> ctx)
	{

		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
				IEntityStats entityStatsProps = EntityStatsCapability.get(player);
				IAbilityData abilityDataProps = AbilityDataCapability.get(player);
				IHakiData hakiDataProps = HakiDataCapability.get(player);
				IExtraEffect extraEffectProps = ExtraEffectCapability.get(player);
				
				if(((message.sync >> 0) & 1) == 1)
					ModNetwork.sendTo(new PacketDevilFruitSync(devilFruitProps), (ServerPlayerEntity) player);
				
				if(((message.sync >> 1) & 1) == 1)
					ModNetwork.sendTo(new PacketEntityStatsSync(entityStatsProps), (ServerPlayerEntity) player);
				
				if(((message.sync >> 2) & 1) == 1)
					ModNetwork.sendTo(new PacketAbilityDataSync(abilityDataProps), (ServerPlayerEntity) player);
				
				//if(((message.sync >> 3) & 1) == 1)
				//	WyNetworkHelper.sendTo(new PacketHakiDataSync(hakiDataProps), (ServerPlayerEntity) player);
				
				if(((message.sync >> 4) & 1) == 1)
					ModNetwork.sendTo(new PacketExtraEffectSync(extraEffectProps), (ServerPlayerEntity) player);
			});			
		}
		ctx.get().setPacketHandled(true);
	}

}
