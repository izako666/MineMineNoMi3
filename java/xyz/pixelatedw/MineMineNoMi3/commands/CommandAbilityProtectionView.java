package xyz.pixelatedw.MineMineNoMi3.commands;

import java.util.Arrays;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedWorldData;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketViewProtection;

public class CommandAbilityProtectionView extends CommandBase
{
	public void processCommand(ICommandSender sender, String[] str)
	{
		EntityPlayer player = this.getCommandSenderAsPlayer(sender);
		ExtendedWorldData worldData = ExtendedWorldData.get(player.worldObj);

		for(int[][] area : worldData.getAllRestrictions())
		{
			int[] minPos = area[0];
			int[] maxPos = area[1];
			
			if(minPos.length <= 0 || maxPos.length <= 0)
				continue;
			
			int midX = (minPos[0] + maxPos[0]) / 2;
			int midY = (minPos[1] + maxPos[1]) / 2;
			int midZ = (minPos[2] + maxPos[2]) / 2;
			int[] midPoint = new int[] { midX, midY, midZ };
			WyNetworkHelper.sendTo(new PacketViewProtection(true, midPoint), (EntityPlayerMP) player);
		}
	}

	public boolean canCommandSenderUseCommand(ICommandSender sender)
	{
		EntityPlayer senderEntity = this.getCommandSenderAsPlayer(sender);
		boolean flag = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152596_g(senderEntity.getGameProfile());

		if (flag)
			return true;

		return false;
	}

	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "/abilityprotectionview";
	}

	public String getCommandName()
	{
		return "abilityprotectionview";
	}
}
