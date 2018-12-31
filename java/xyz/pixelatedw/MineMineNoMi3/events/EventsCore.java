package xyz.pixelatedw.MineMineNoMi3.events;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.player.PlayerEvent;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityProperties;
import xyz.pixelatedw.MineMineNoMi3.api.debug.WyDebug;
import xyz.pixelatedw.MineMineNoMi3.api.quests.QuestProperties;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;

public class EventsCore
{
	
	// Registering the extended properties
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) 
	{
		if (event.entity instanceof EntityLivingBase && ExtendedEntityData.get((EntityLivingBase) event.entity) == null)
			ExtendedEntityData.register((EntityLivingBase) event.entity);
		
		if (event.entity instanceof EntityPlayer)
		{
			if(QuestProperties.get((EntityPlayer) event.entity) == null)
				QuestProperties.register((EntityPlayer) event.entity);
			if(AbilityProperties.get((EntityPlayer) event.entity) == null)
				AbilityProperties.register((EntityPlayer) event.entity);
		}
	}
	
	// Cloning the player data to the new entity based on the config option
	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone e) 
	{
		if(e.wasDeath) 
		{
			if(MainConfig.enableKeepIEEPAfterDeath.equals("full"))
			{
				NBTTagCompound compound = new NBTTagCompound();
				
				ExtendedEntityData oldProps = ExtendedEntityData.get(e.original);
				oldProps.saveNBTData(compound);
				oldProps.triggerActiveHaki(false);
				oldProps.triggerBusoHaki(false);
				oldProps.triggerKenHaki(false);
				oldProps.setGear(1);
				oldProps.setZoanPoint("n/a");
				ExtendedEntityData props = ExtendedEntityData.get(e.entityPlayer);
				props.loadNBTData(compound);				
				
				compound = new NBTTagCompound();
				AbilityProperties.get(e.original).saveNBTData(compound);
				AbilityProperties abilityProps = AbilityProperties.get(e.entityPlayer);
				abilityProps.loadNBTData(compound);
				
				if(e.entityPlayer != null && MainConfig.enableExtraHearts)		
				{
					IAttributeInstance maxHp = e.entityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
								
					if(props.getDoriki() / 100 <= 20)
						e.entityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
					else
						e.entityPlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(props.getDoriki() / 100);
				}
			}
			else if(MainConfig.enableKeepIEEPAfterDeath.equals("auto"))
			{
				ExtendedEntityData props = ExtendedEntityData.get(e.original);
				NBTTagCompound compound = new NBTTagCompound();
				
				String faction = props.getFaction();
				String race = props.getRace();
				String fightStyle = props.getFightStyle();
				String crew = props.getCrew();			
				
				props.resetNBTData(compound);
				props.loadNBTData(compound);
								
				props.setFaction(faction);
				props.setRace(race);
				props.setFightStyle(fightStyle);
				props.setCrew(crew);
				
				props.setMaxCola(100);
				props.setCola(props.getMaxCola());
				
				props.saveNBTData(compound);
								
				ExtendedEntityData.get(e.entityPlayer).loadNBTData(compound);
			}
			
			NBTTagCompound compound = new NBTTagCompound();
			QuestProperties.get(e.original).saveNBTData(compound);
			QuestProperties questProps = QuestProperties.get(e.entityPlayer);
			questProps.loadNBTData(compound);
		}
	}
	
	// Protection code and the update notification message
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;

			if (!player.worldObj.isRemote)
			{
				if(ID.DEV_EARLYACCESS && !WyDebug.isDebug())
				{
					try 
					{
						URL url = new URL("https://dl.dropboxusercontent.com/s/cs2cv9ezaatzgd3/earlyaccess.txt?dl=0");
						Scanner scanner = new Scanner(url.openStream());
						boolean flag = false;
						
						while(scanner.hasNextLine())
						{
							String uuid = scanner.nextLine();
							if(uuid.startsWith("$"))
								continue;
													
							if(player.getUniqueID().toString().equals(uuid) || (uuid.startsWith("&") && player.getDisplayName().equalsIgnoreCase(uuid.replace("& ", ""))))
							{
								flag = true;
								break;
							}
						}
						
						if(!flag)
							((EntityPlayerMP)player).playerNetServerHandler.kickPlayerFromServer(EnumChatFormatting.BOLD + "" + EnumChatFormatting.RED + "WARNING! \n\n " + EnumChatFormatting.RESET + "You don't have access to this version yet!");														
						
						scanner.close();
					} 
					catch (IOException e) 
					{
						((EntityPlayerMP)player).playerNetServerHandler.kickPlayerFromServer(EnumChatFormatting.BOLD + "" + EnumChatFormatting.RED + "WARNING! \n\n " + EnumChatFormatting.RESET + "You don't have access to this version yet!");						
						e.printStackTrace();
					}				
				}
				
				if(MainConfig.enableUpdateMsg)
				{
					try 
					{
						URL url = new URL("https://dl.dropboxusercontent.com/s/3io0vaqiqaoabnh/version.txt?dl=0");
						Scanner scanner = new Scanner(url.openStream());
						
						while(scanner.hasNextLine())
						{
							String[] parts = scanner.nextLine().split("\\-");
	
							if(ID.PROJECT_MCVERSION.equals(parts[0]))
							{
								String cloudVersion = parts[1].replace(".", "");
								String localVersion = ID.PROJECT_VERSION.replace(".", "");
								
								if(Integer.parseInt(localVersion) < Integer.parseInt(cloudVersion))
								{
									ChatStyle updateStyle = new ChatStyle().setColor(EnumChatFormatting.GOLD).setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://pixelatedw.xyz/builds.php"));
									
									player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "[UPDATE]" + EnumChatFormatting.RED + " Mine Mine no Mi " + parts[1] + " is now available !").setChatStyle(updateStyle) );
									player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "Download it from the official website : [http://pixelatedw.xyz/builds.php]").setChatStyle(updateStyle) );
								}
							}					
						}
						
						scanner.close();
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
			}
		}
	}
}
