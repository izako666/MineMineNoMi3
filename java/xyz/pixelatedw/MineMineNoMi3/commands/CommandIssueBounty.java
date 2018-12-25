package xyz.pixelatedw.MineMineNoMi3.commands;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.debug.WyDebug;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.world.ExtendedWorldData;

public class CommandIssueBounty extends CommandBase
{
	public void processCommand(ICommandSender sender, String[] str)
	{
		if(str.length >= 0)
		{
			EntityPlayer player = this.getCommandSenderAsPlayer(sender);
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			ExtendedWorldData worldData = ExtendedWorldData.get(player.worldObj);
			
			worldData.issueBounty(player.getCommandSenderName(), props.getBounty());			
		}
	}
	
	public boolean canCommandSenderUseCommand(ICommandSender sender)
	{
		EntityPlayer senderEntity = this.getCommandSenderAsPlayer(sender);
		boolean flag = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152596_g(senderEntity.getGameProfile());

		if ((MainConfig.commandPermissionIssueBounty == 2 && flag) || MainConfig.commandPermissionIssueBounty < 2)
			return true;
		else
			return false;
	}

	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "/issuebounty <player>";
	}

	public String getCommandName()
	{
		return "issuebounty";
	}
}
