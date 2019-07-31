package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.network.PacketAbilityDataSync;
import xyz.pixelatedw.mineminenomi.api.network.WyNetworkHelper;
import xyz.pixelatedw.mineminenomi.packets.PacketClientSyncAll;
import xyz.pixelatedw.mineminenomi.packets.PacketCombatModeTrigger;
import xyz.pixelatedw.mineminenomi.packets.PacketDeleteCCBook;
import xyz.pixelatedw.mineminenomi.packets.PacketDevilFruitSync;
import xyz.pixelatedw.mineminenomi.packets.PacketEntityStatsSync;
import xyz.pixelatedw.mineminenomi.packets.PacketExtraEffectSync;
import xyz.pixelatedw.mineminenomi.packets.PacketForceSync;
import xyz.pixelatedw.mineminenomi.packets.PacketMotion;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;
import xyz.pixelatedw.mineminenomi.packets.PacketSpecialFlying;
import xyz.pixelatedw.mineminenomi.packets.PacketUseAbility;

public class ModNetwork
{
	private static final String PROTOCOL_VERSION = Integer.toString(1);
	public static SimpleChannel channel = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(ID.PROJECT_ID, "main_channel")).clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals).networkProtocolVersion(() -> PROTOCOL_VERSION).simpleChannel();

	public static void init() 
	{
		int packet = 0;

		channel.registerMessage(packet++, PacketDevilFruitSync.class, PacketDevilFruitSync::encode, PacketDevilFruitSync::decode, PacketDevilFruitSync::handle);
		channel.registerMessage(packet++, PacketAbilityDataSync.class, PacketAbilityDataSync::encode, PacketAbilityDataSync::decode, PacketAbilityDataSync::handle);
		channel.registerMessage(packet++, PacketEntityStatsSync.class, PacketEntityStatsSync::encode, PacketEntityStatsSync::decode, PacketEntityStatsSync::handle);
		channel.registerMessage(packet++, PacketForceSync.class, PacketForceSync::encode, PacketForceSync::decode, PacketForceSync::handle);
		channel.registerMessage(packet++, PacketCombatModeTrigger.class, PacketCombatModeTrigger::encode, PacketCombatModeTrigger::decode, PacketCombatModeTrigger::handle);
		channel.registerMessage(packet++, PacketUseAbility.class, PacketUseAbility::encode, PacketUseAbility::decode, PacketUseAbility::handle);
		channel.registerMessage(packet++, PacketDeleteCCBook.class, PacketDeleteCCBook::encode, PacketDeleteCCBook::decode, PacketDeleteCCBook::handle);
		channel.registerMessage(packet++, PacketExtraEffectSync.class, PacketExtraEffectSync::encode, PacketExtraEffectSync::decode, PacketExtraEffectSync::handle);
		channel.registerMessage(packet++, PacketClientSyncAll.class, PacketClientSyncAll::encode, PacketClientSyncAll::decode, PacketClientSyncAll::handle);
		channel.registerMessage(packet++, PacketParticles.class, PacketParticles::encode, PacketParticles::decode, PacketParticles::handle);
		channel.registerMessage(packet++, PacketMotion.class, PacketMotion::encode, PacketMotion::decode, PacketMotion::handle);
		channel.registerMessage(packet++, PacketSpecialFlying.class, PacketSpecialFlying::encode, PacketSpecialFlying::decode, PacketSpecialFlying::handle);
	}
	
	public static <MSG> void sendToServer(MSG msg)
	{
		WyNetworkHelper.sendToServer(channel, msg);
	}
	
	public static <MSG> void sendTo(MSG msg, ServerPlayerEntity player)
	{
		WyNetworkHelper.sendTo(channel, msg, player);
	}
	
	public static <MSG> void sendToAll(MSG msg)
	{
		WyNetworkHelper.sendToAll(channel, msg);
	}
	
	public static <MSG> void sendToAllAround(MSG msg, LivingEntity sender)
	{
		WyNetworkHelper.sendToAllAround(channel, msg, sender);
	}
}
