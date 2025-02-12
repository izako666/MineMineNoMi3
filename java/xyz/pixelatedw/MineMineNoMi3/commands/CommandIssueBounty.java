package xyz.pixelatedw.MineMineNoMi3.commands;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedWorldData;

public class CommandIssueBounty extends CommandBase
{
	public void processCommand(ICommandSender sender, String[] str)
	{
		EntityPlayer player = null;
		boolean allFlag = false;
		
		if(str.length == 1)
		{
			if(str[0].equalsIgnoreCase("all"))
			{
				allFlag = true;
				player = this.getCommandSenderAsPlayer(sender);
			}
			else
				player = this.getPlayer(sender, str[0]);
		}
		else
			player = this.getCommandSenderAsPlayer(sender);
			
		ExtendedEntityData props = ExtendedEntityData.get(player);
		ExtendedWorldData worldData = ExtendedWorldData.get(player.worldObj);
		
		if(!allFlag)
		{
			worldData.issueBounty(player.getCommandSenderName(), props.getBounty());
		}
		else
		{
			player.worldObj.loadedEntityList.stream().filter(x -> 
			{
				return x instanceof EntityPlayer && ExtendedEntityData.get((EntityLivingBase) x).getFaction().equalsIgnoreCase(ID.FACTION_PIRATE) && ExtendedEntityData.get((EntityLivingBase) x).getBounty() > 0;
			}).forEach(x ->
			{
				EntityPlayer pirate = (EntityPlayer) x;
				worldData.issueBounty(pirate.getCommandSenderName(), ExtendedEntityData.get(pirate).getBounty());
			});
		}
	}
	
	public boolean canCommandSenderUseCommand(ICommandSender sender)
	{
		if(!(sender instanceof EntityPlayer))
			return true;
		
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
