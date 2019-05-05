package xyz.pixelatedw.MineMineNoMi3.events;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import cpw.mods.fml.common.eventhandler.Event.Result;
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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.player.PlayerEvent;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityProperties;
import xyz.pixelatedw.MineMineNoMi3.api.debug.WyDebug;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.api.quests.QuestProperties;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.events.customevents.DorikiEvent;
import xyz.pixelatedw.MineMineNoMi3.events.customevents.YomiTriggerEvent;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketNewAABB;

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
			ExtendedEntityData oldPlayerProps = ExtendedEntityData.get(e.original);	
			ExtendedEntityData newPlayerProps = ExtendedEntityData.get(e.entityPlayer);
			
			//WyNetworkHelper.sendTo(new PacketNewAABB(0.6F, 1.8F), (EntityPlayerMP) e.entityPlayer);
			
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
				ExtendedEntityData oldProps = ExtendedEntityData.get(e.original);
				
				String faction = oldProps.getFaction();
				String race = oldProps.getRace();
				String fightStyle = oldProps.getFightStyle();
				String crew = oldProps.getCrew();
				int doriki = oldProps.getDoriki() / 3;

				ExtendedEntityData props = ExtendedEntityData.get(e.entityPlayer);
				props.setFaction(faction);
				props.setRace(race);
				props.setFightStyle(fightStyle);
				props.setCrew(crew);			
				props.setMaxCola(100);
				props.setCola(oldProps.getMaxCola());
				props.setDoriki(doriki);
			}
			else if(MainConfig.enableKeepIEEPAfterDeath.equals("custom"))
			{
				ExtendedEntityData oldProps = ExtendedEntityData.get(e.original);
				ExtendedEntityData props = ExtendedEntityData.get(e.entityPlayer);

				for(String stat : MainConfig.statsToKepp)
				{
					switch(WyHelper.getFancyName(stat))
					{
						case "doriki":
							props.setDoriki(oldProps.getDoriki()); break;
						case "bounty":
							props.setBounty(oldProps.getBounty()); break;
						case "belly":
							props.setBelly(oldProps.getBelly()); break;
						case "race":
							props.setRace(oldProps.getRace()); break;
						case "faction":
							props.setFaction(oldProps.getFaction()); break;
						case "fightingstyle":
							props.setFightStyle(oldProps.getFightStyle()); break;
						case "devilfruit":
							props.setUsedFruit(oldProps.getUsedFruit()); break;
					}
				}
			}
					
			NBTTagCompound compound = new NBTTagCompound();
			QuestProperties.get(e.original).saveNBTData(compound);
			QuestProperties questProps = QuestProperties.get(e.entityPlayer);
			questProps.loadNBTData(compound);
			
			YomiTriggerEvent yomiEvent = new YomiTriggerEvent(e.entityPlayer, oldPlayerProps, newPlayerProps);
			if (MinecraftForge.EVENT_BUS.post(yomiEvent))
				return;
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
					WyHelper.isPatreon(player);
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
									ChatStyle updateStyle = new ChatStyle().setColor(EnumChatFormatting.GOLD).setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://pixelatedw.xyz/versions"));
									
									player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "[UPDATE]" + EnumChatFormatting.RED + " Mine Mine no Mi " + parts[1] + " is now available !").setChatStyle(updateStyle) );
									player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "Download it from the official website : [http://pixelatedw.xyz/versions]").setChatStyle(updateStyle) );
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
