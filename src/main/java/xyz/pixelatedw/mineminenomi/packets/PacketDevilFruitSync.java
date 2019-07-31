package xyz.pixelatedw.mineminenomi.packets;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.ModMain;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;

public class PacketDevilFruitSync
{
	private INBT data;

	public PacketDevilFruitSync() {}
	
	public PacketDevilFruitSync(IDevilFruit devilFruitProps)
	{
		this.data = new CompoundNBT();
		this.data = DevilFruitCapability.INSTANCE.getStorage().writeNBT(DevilFruitCapability.INSTANCE, devilFruitProps, null);
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeCompoundTag((CompoundNBT) data);
	}
	
	public static PacketDevilFruitSync decode(PacketBuffer buffer)
	{
		PacketDevilFruitSync msg = new PacketDevilFruitSync();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(PacketDevilFruitSync message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
				
				DevilFruitCapability.INSTANCE.getStorage().readNBT(DevilFruitCapability.INSTANCE, devilFruitProps, null, message.data);
								
			});			
		}
		else if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ModMain.proxy.getClientPlayer();
				IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
				
				DevilFruitCapability.INSTANCE.getStorage().readNBT(DevilFruitCapability.INSTANCE, devilFruitProps, null, message.data);
								
			});	
		}
		ctx.get().setPacketHandled(true);
	}
}
