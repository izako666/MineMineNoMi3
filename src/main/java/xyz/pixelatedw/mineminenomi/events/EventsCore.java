package xyz.pixelatedw.mineminenomi.events;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;

import com.google.gson.internal.LinkedTreeMap;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;

public class EventsCore
{
	// Cloning the player data to the new entity based on the config option
	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone e) 
	{
		/*if(e.isWasDeath()) 
		{
			ExtendedEntityData oldPlayerProps = ExtendedEntityData.get(e.original);	
			ExtendedEntityData newPlayerProps = ExtendedEntityData.get(e.PlayerEntity);
			
			//WyNetworkHelper.sendTo(new PacketNewAABB(0.6F, 1.8F), (PlayerEntityMP) e.PlayerEntity);
			
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
				ExtendedEntityData props = ExtendedEntityData.get(e.PlayerEntity);
				props.loadNBTData(compound);				
				
				compound = new NBTTagCompound();
				AbilityProperties.get(e.original).saveNBTData(compound);
				AbilityProperties abilityProps = AbilityProperties.get(e.PlayerEntity);
				abilityProps.loadNBTData(compound);
				
				if(e.PlayerEntity != null && MainConfig.enableExtraHearts)		
				{
					IAttributeInstance maxHp = e.PlayerEntity.getEntityAttribute(SharedMonsterAttributes.maxHealth);
								
					if(props.getDoriki() / 100 <= 20)
						e.PlayerEntity.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
					else
						e.PlayerEntity.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(props.getDoriki() / 100);
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

				ExtendedEntityData props = ExtendedEntityData.get(e.PlayerEntity);
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
				ExtendedEntityData props = ExtendedEntityData.get(e.PlayerEntity);

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
			QuestProperties questProps = QuestProperties.get(e.PlayerEntity);
			questProps.loadNBTData(compound);
			
			YomiTriggerEvent yomiEvent = new YomiTriggerEvent(e.PlayerEntity, oldPlayerProps, newPlayerProps);
			if (MinecraftForge.EVENT_BUS.post(yomiEvent))
				return;
		}*/
	}
	
	// Protection code and the update notification message
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntity();

			if (!player.world.isRemote)
			{
				if(ID.DEV_EARLYACCESS && !WyDebug.isDebug())
				{
					WyHelper.isPatreon(player);
				}
				
				if(CommonConfig.instance.getUpdateMessage())
				{
					try 
					{
						String[] version = ID.PROJECT_VERSION.split("\\.");
	
						int x = Integer.parseInt(version[0]) * 100;
						int y = Integer.parseInt(version[1]) * 10;
						int z = Integer.parseInt(version[2]);
						
						int versionCode = x + y + z;
						
						String url = "/getNewestVersion";
						String json = Values.gson.toJson(ID.PROJECT_MCVERSION);
						
						HttpPost post = new HttpPost(Values.urlConnection + "" + url);	
						StringEntity postingString;
						postingString = new StringEntity(json);
						post.setEntity(postingString);
						post.setHeader("Content-Type", "application/json");
						
						HttpResponse response = Values.httpClient.execute(post);
						ResponseHandler<String> handler = new BasicResponseHandler();
						String body = handler.handleResponse(response);
						
						if(!body.isEmpty())
						{
							LinkedTreeMap result = Values.gson.fromJson(body, LinkedTreeMap.class);
							int highestVersion = ((Double) result.get("highestVersionCode")).intValue();
							String highestName = (String) result.get("highestVersionName");
							
							if(highestVersion > versionCode)
							{
								player.sendMessage(new StringTextComponent(TextFormatting.RED + "" + TextFormatting.BOLD + "[UPDATE]" + TextFormatting.RED + " Mine Mine no Mi " + highestName + " is now available !").applyTextStyle((style) -> { style.setColor(TextFormatting.GOLD).setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://pixelatedw.xyz/versions")); }) );
								player.sendMessage(new StringTextComponent(TextFormatting.RED + "Download it from the official website : [http://pixelatedw.xyz/versions]").applyTextStyle((style) -> { style.setColor(TextFormatting.GOLD).setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://pixelatedw.xyz/versions")); })  );
							}
						}
					}
					catch(Exception e)
					{
						System.out.println("Connection failed !");
					}
				}
			}
		}
	}
}
