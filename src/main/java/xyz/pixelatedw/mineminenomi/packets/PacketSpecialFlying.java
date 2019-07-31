package xyz.pixelatedw.mineminenomi.packets;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.ModMain;

public class PacketSpecialFlying
{

	private boolean specialFlying;
	
	public PacketSpecialFlying() {}
	
	public PacketSpecialFlying(boolean canSpecialFly)
	{
		this.specialFlying = canSpecialFly;
	}
	
	public void encode(PacketBuffer buffer)
	{
		buffer.writeBoolean(this.specialFlying);
	}
	
	public static PacketSpecialFlying decode(PacketBuffer buffer)
	{
		PacketSpecialFlying msg = new PacketSpecialFlying();
		msg.specialFlying = buffer.readBoolean();
		return msg;
	}
	
	public static void handle(PacketSpecialFlying message, final Supplier<NetworkEvent.Context> ctx)
	{
		if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ModMain.proxy.getClientPlayer();
				
				player.abilities.allowFlying = message.specialFlying;
				if(!message.specialFlying)
				{
					player.abilities.isFlying = false;
				}
			});
		}
		
		ctx.get().setPacketHandled(true);
	}
}
