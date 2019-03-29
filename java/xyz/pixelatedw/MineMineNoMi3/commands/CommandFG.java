package xyz.pixelatedw.MineMineNoMi3.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.PacketQuestSync;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.api.quests.Quest;
import xyz.pixelatedw.MineMineNoMi3.api.quests.QuestManager;
import xyz.pixelatedw.MineMineNoMi3.api.quests.QuestProperties;
import xyz.pixelatedw.MineMineNoMi3.blocks.tileentities.TileEntityCustomSpawner;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedWorldData;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.baroqueWorks.EntityMr0;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.EntityWantedPostersPackage;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.temp.TempEntityDummy;
import xyz.pixelatedw.MineMineNoMi3.helpers.ItemsHelper;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.lists.ListQuests;
import xyz.pixelatedw.MineMineNoMi3.world.TeleporterScenarioArena;

public class CommandFG extends CommandBase
{	
	
	private Quest[] questsPool = new Quest[] {ListQuests.bountyLowLevel01, ListQuests.bountyLowLevel02, ListQuests.bountyLowLevel03};
	
	public void processCommand(ICommandSender sender, String[] str) 
	{
		if(str.length >= 1)
		{
			EntityPlayer player = this.getCommandSenderAsPlayer(sender);
			ExtendedEntityData props = ExtendedEntityData.get(player);
			QuestProperties questProps = QuestProperties.get(player);
			Entity toSpawn = null;

			if(str[0].equalsIgnoreCase("dummy"))
				toSpawn = new TempEntityDummy(player.worldObj);
			else if(str[0].equalsIgnoreCase("mr0"))
				toSpawn = new EntityMr0(player.worldObj);
						
			else if(str[0].equalsIgnoreCase("package"))
			{			
				toSpawn = new EntityWantedPostersPackage(player.worldObj);
				toSpawn.setLocationAndAngles(player.posX + WyMathHelper.randomWithRange(-10, 10), player.posY + 30, player.posZ + WyMathHelper.randomWithRange(-10, 10), 0, 0);
				player.worldObj.spawnEntityInWorld(toSpawn);
				return;
			}
			
			else if(str[0].equalsIgnoreCase("wantedPoster"))
			{
				ExtendedWorldData worldData = ExtendedWorldData.get(player.worldObj);
				
				ItemStack posterStack = new ItemStack(ListMisc.WantedPoster);
				posterStack.setTagCompound(ItemsHelper.setWantedData(player.getCommandSenderName(), worldData.getBounty(player.getCommandSenderName())));
				player.inventory.addItemStackToInventory(posterStack);				
			}
			else if(str[0].equalsIgnoreCase("randBounties"))
			{
				ExtendedWorldData worldData = ExtendedWorldData.get(player.worldObj);
				
				for(int i = 1; i < 10; i++)
				{
					worldData.issueBounty("Test Name #"+i, i*100 + player.worldObj.rand.nextInt(1000));
				}
			}
			else if(str[0].equalsIgnoreCase("monsterspawner"))
			{
				String toSpawn1 = ID.PROJECT_ID + ".Marine with Sword";
				
				TileEntityCustomSpawner spw1 = new TileEntityCustomSpawner().setSpawnerMob(toSpawn1).setSpawnerLimit(5);
				player.worldObj.setBlock((int)player.posX, (int)player.posY + 1, (int)player.posZ, ListMisc.CustomSpawner );
				player.worldObj.setTileEntity((int)player.posX, (int)player.posY + 1, (int)player.posZ, spw1);
			}
			
			else if(str[0].equalsIgnoreCase("scenario"))
			{
				if(str[1].equalsIgnoreCase("start"))
				{
					if(!player.worldObj.isRemote)
						new TeleporterScenarioArena((WorldServer) player.worldObj).teleport(player, ID.SCENARIO_ROMANCEDAWN_CAPTAINMORGAN);
				}
				else if(str[1].equalsIgnoreCase("end"))
				{
					if(!player.worldObj.isRemote)
						new TeleporterScenarioArena((WorldServer) player.worldObj).endScenario(player, ID.SCENARIO_ROMANCEDAWN_CAPTAINMORGAN);
				}
			}
			else if(str[0].equalsIgnoreCase("maxcola"))
			{
				if(!props.hasColaBackpack())
					props.setMaxCola(250);
				else
					props.setMaxCola(450);
			}
			else if(str[0].equalsIgnoreCase("fillcola"))
				props.setCola(props.getMaxCola());
			else if(str[0].equalsIgnoreCase("rngquest"))
			{
				//QuestManager.instance().startQuest(player, questsPool[player.getRNG().nextInt(questsPool.length)]);
				QuestManager.instance().startQuest(player, ListQuests.bountyLowLevel01);
				WyNetworkHelper.sendTo(new PacketQuestSync(questProps), (EntityPlayerMP) player);
			}
			else if(str[0].equalsIgnoreCase("resetquests"))
			{
				questProps.clearQuestTracker();
				questProps.clearCompletedQuests();
				WyNetworkHelper.sendTo(new PacketQuestSync(questProps), (EntityPlayerMP) player);
			}

			if(toSpawn != null)
			{
				toSpawn.setLocationAndAngles(player.posX, player.posY, player.posZ, 0, 0);
				player.worldObj.spawnEntityInWorld(toSpawn);
			}
		}
	}

	public boolean canCommandSenderUseCommand(ICommandSender cmd)
	{
		return true;
	}
	
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "/fg <entity>";
	}

	public String getCommandName() 
	{
		return "fg";
	}

}
