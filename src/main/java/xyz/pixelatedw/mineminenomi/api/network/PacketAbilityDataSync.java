package xyz.pixelatedw.mineminenomi.api.network;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.ModMain;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;

public class PacketAbilityDataSync
{
	private INBT data;

	public PacketAbilityDataSync() {}
	
	public PacketAbilityDataSync(IAbilityData abiltiyDataProps)
	{
		this.data = new CompoundNBT();
		this.data = AbilityDataCapability.INSTANCE.getStorage().writeNBT(AbilityDataCapability.INSTANCE, abiltiyDataProps, null);
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeCompoundTag((CompoundNBT) data);
	}
	
	public static PacketAbilityDataSync decode(PacketBuffer buffer)
	{
		PacketAbilityDataSync msg = new PacketAbilityDataSync();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(PacketAbilityDataSync message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IAbilityData abilityDataProps = AbilityDataCapability.get(player);
								
				AbilityDataCapability.INSTANCE.getStorage().readNBT(AbilityDataCapability.INSTANCE, abilityDataProps, null, message.data);
			});			
		}
		else if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ModMain.proxy.getClientPlayer();
				IAbilityData abilityDataProps = AbilityDataCapability.get(player);
				
				AbilityDataCapability.INSTANCE.getStorage().readNBT(AbilityDataCapability.INSTANCE, abilityDataProps, null, message.data);
			});	
		}
		ctx.get().setPacketHandled(true);
	}

}
