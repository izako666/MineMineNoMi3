package xyz.pixelatedw.mineminenomi.commands;

import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketEntityStatsSync;

public class CommandBounty
{
	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("bounty").requires(source -> source.hasPermissionLevel(2));

		builder
			.then(Commands.literal("+")
				.then(Commands.argument("value", LongArgumentType.longArg(1, Values.MAX_BOUNTY))
					.then(Commands.argument("targets", EntityArgument.players())
						.executes(context -> 
							{ 
								return addValue(context, LongArgumentType.getLong(context, "value"), EntityArgument.getPlayers(context, "targets")); 
							}
						)
					)	
				)
			);
		
		builder
			.then(Commands.literal("=")
				.then(Commands.argument("value", LongArgumentType.longArg(1, Values.MAX_BOUNTY))
					.then(Commands.argument("targets", EntityArgument.players())
						.executes(context -> 
							{ 
								return setValue(context, LongArgumentType.getLong(context, "value"), EntityArgument.getPlayers(context, "targets")); 
							}
						)
					)	
				)
				.then(Commands.literal("MAX")
					.then(Commands.argument("targets", EntityArgument.players())
						.executes(context ->
							{
								return setValue(context, Values.MAX_BOUNTY, EntityArgument.getPlayers(context, "targets")); 
							}
						)
					)
				)
			);
		
		builder
			.then(Commands.literal("-")
				.then(Commands.argument("value", LongArgumentType.longArg(1, Values.MAX_BOUNTY))
					.then(Commands.argument("targets", EntityArgument.players())
						.executes(context -> 
							{ 
								return subtractValue(context, LongArgumentType.getLong(context, "value"), EntityArgument.getPlayers(context, "targets")); 
							}
						)
					)	
				)
			);
		
		dispatcher.register(builder);
	}
	
	private static int subtractValue(CommandContext<CommandSource> context, long value, Collection<ServerPlayerEntity> targets)
	{		
		for (ServerPlayerEntity player : targets)
		{
			IEntityStats entityStatsProps = EntityStatsCapability.get(player);
			
			entityStatsProps.alterBounty(-value);
			
			if(WyDebug.isDebug())
				WyHelper.sendMsgToPlayer(player, TextFormatting.GREEN + "" + TextFormatting.ITALIC + "[DEBUG] Substracted " + value + " bounty from " + player.getName().getFormattedText()); 

			ModNetwork.sendTo(new PacketEntityStatsSync(entityStatsProps), (ServerPlayerEntity) player);
		}
		
		return 1;
	}
	
	private static int setValue(CommandContext<CommandSource> context, long value, Collection<ServerPlayerEntity> targets)
	{		
		for (ServerPlayerEntity player : targets)
		{
			IEntityStats entityStatsProps = EntityStatsCapability.get(player);
			
			entityStatsProps.setBounty(value);
			
			if(WyDebug.isDebug())
				WyHelper.sendMsgToPlayer(player, TextFormatting.GREEN + "" + TextFormatting.ITALIC + "[DEBUG] " + player.getName().getFormattedText() + " now has " + value + " bounty"); 

			ModNetwork.sendTo(new PacketEntityStatsSync(entityStatsProps), (ServerPlayerEntity) player);
		}
		
		return 1;
	}

	private static int addValue(CommandContext<CommandSource> context, long value, Collection<ServerPlayerEntity> targets) throws CommandSyntaxException 
	{
		for (ServerPlayerEntity player : targets)
		{
			IEntityStats entityStatsProps = EntityStatsCapability.get(player);
			
			entityStatsProps.alterBounty(value);
			
			if(WyDebug.isDebug())
				WyHelper.sendMsgToPlayer(player, TextFormatting.GREEN + "" + TextFormatting.ITALIC + "[DEBUG] Added " + value + " bounty to " + player.getName().getFormattedText()); 

			ModNetwork.sendTo(new PacketEntityStatsSync(entityStatsProps), (ServerPlayerEntity) player);
		}
		
		return 1;
	}
}
