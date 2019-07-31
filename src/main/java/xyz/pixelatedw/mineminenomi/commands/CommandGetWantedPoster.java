package xyz.pixelatedw.mineminenomi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.init.ModMiscItems;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketEntityStatsSync;

public class CommandGetWantedPoster
{

	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("getwantedposter").requires(source -> source.hasPermissionLevel(2));

		builder
			.then(Commands.argument("target", EntityArgument.player())
				.executes(context ->
					{
						return giveWantedPoster(context, EntityArgument.getPlayer(context, "target")); 
					}
				)
			);
		
		dispatcher.register(builder);
	}

	private static int giveWantedPoster(CommandContext<CommandSource> context, ServerPlayerEntity player)
	{
		IEntityStats entityStatsProps = EntityStatsCapability.get(player);
		ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
			
		worldData.issueBounty(player.getName().getFormattedText(), entityStatsProps.getBounty());
						
		if(WyDebug.isDebug())
			WyHelper.sendMsgToPlayer(player, TextFormatting.GREEN + "" + TextFormatting.ITALIC + "[DEBUG] A new bounty was issued on your name!");
		
		ModNetwork.sendTo(new PacketEntityStatsSync(entityStatsProps), (ServerPlayerEntity) player);
		
		ItemStack posterStack = new ItemStack(ModMiscItems.wantedPoster);
		posterStack.setTag(ItemsHelper.setWantedData(player.getName().getFormattedText(), worldData.getBounty(player.getName().getFormattedText())));
		player.inventory.addItemStackToInventory(posterStack);
		
		return 1;
	}
}
