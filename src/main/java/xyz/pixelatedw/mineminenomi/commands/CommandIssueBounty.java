package xyz.pixelatedw.mineminenomi.commands;

import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketEntityStatsSync;

public class CommandIssueBounty
{

	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("issuebounty").requires(source -> source.hasPermissionLevel(2));

		builder
			.then(Commands.argument("targets", EntityArgument.players())
				.executes(context ->
					{
						return issueBounty(context, EntityArgument.getPlayers(context, "targets")); 
					}
				)
			);
		
		dispatcher.register(builder);
	}

	private static int issueBounty(CommandContext<CommandSource> context, Collection<ServerPlayerEntity> players)
	{
		for (ServerPlayerEntity player : players)
		{
			IEntityStats entityStatsProps = EntityStatsCapability.get(player);
			ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
			
			worldData.issueBounty(player.getName().getFormattedText(), entityStatsProps.getBounty());
						
			if(WyDebug.isDebug())
				WyHelper.sendMsgToPlayer(player, TextFormatting.GREEN + "" + TextFormatting.ITALIC + "[DEBUG] A new bounty was issued on your name!");

			ModNetwork.sendTo(new PacketEntityStatsSync(entityStatsProps), (ServerPlayerEntity) player);
		}
		
		return 1;
	}
}
