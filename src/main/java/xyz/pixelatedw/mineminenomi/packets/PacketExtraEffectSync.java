package xyz.pixelatedw.mineminenomi.packets;

import java.util.function.Supplier;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.ModMain;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.ExtraEffectCapability;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.IExtraEffect;

public class PacketExtraEffectSync
{
	private INBT data;

	public PacketExtraEffectSync()
	{
	}

	public PacketExtraEffectSync(IExtraEffect props)
	{
		this.data = new CompoundNBT();
		this.data = ExtraEffectCapability.INSTANCE.getStorage().writeNBT(ExtraEffectCapability.INSTANCE, props, null);
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeCompoundTag((CompoundNBT) data);
	}

	public static PacketExtraEffectSync decode(PacketBuffer buffer)
	{
		PacketExtraEffectSync msg = new PacketExtraEffectSync();
		msg.data = buffer.readCompoundTag();
		return msg;
	}

	public static void handle(PacketExtraEffectSync message, final Supplier<NetworkEvent.Context> ctx)
	{

		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IExtraEffect props = ExtraEffectCapability.get(player);

				ExtraEffectCapability.INSTANCE.getStorage().readNBT(ExtraEffectCapability.INSTANCE, props, null, message.data);

			});
		}
		else if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ModMain.proxy.getClientPlayer();
				IExtraEffect props = ExtraEffectCapability.get(player);

				ExtraEffectCapability.INSTANCE.getStorage().readNBT(ExtraEffectCapability.INSTANCE, props, null, message.data);

			});
		}
		ctx.get().setPacketHandled(true);
	}

}