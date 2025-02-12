package xyz.pixelatedw.MineMineNoMi3.commands;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedWorldData;
import xyz.pixelatedw.MineMineNoMi3.helpers.ItemsHelper;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class CommandGetWantedPoster extends CommandBase
{
	public void processCommand(ICommandSender sender, String[] str)
	{
		EntityPlayer player = null;
		
		if(str.length == 1)
			player = this.getPlayer(sender, str[0]);
		else
			player = this.getCommandSenderAsPlayer(sender);
		
		ExtendedEntityData props = ExtendedEntityData.get(player);
		ExtendedWorldData worldData = ExtendedWorldData.get(player.worldObj);

		worldData.issueBounty(player.getCommandSenderName(), props.getBounty());
		
		ItemStack posterStack = new ItemStack(ListMisc.WantedPoster);
		posterStack.setTagCompound(ItemsHelper.setWantedData(player.getCommandSenderName(), worldData.getBounty(player.getCommandSenderName())));
		player.inventory.addItemStackToInventory(posterStack);
	}

	public boolean canCommandSenderUseCommand(ICommandSender sender)
	{
		if(!(sender instanceof EntityPlayer))
			return true;
		
		EntityPlayer senderEntity = this.getCommandSenderAsPlayer(sender);
		boolean flag = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152596_g(senderEntity.getGameProfile());

		if ((MainConfig.commandPermissionGetWantedPoster == 2 && flag) || MainConfig.commandPermissionGetWantedPoster < 2)
			return true;
		else
			return false;
	}
	
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "/getwantedposter";
	}

	public String getCommandName()
	{
		return "getwantedposter";
	}
}
