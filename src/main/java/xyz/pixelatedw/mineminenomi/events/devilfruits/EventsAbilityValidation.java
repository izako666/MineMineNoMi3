package xyz.pixelatedw.mineminenomi.events.devilfruits;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.api.network.PacketAbilityDataSync;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModMiscItems;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.items.ItemAkumaNoMi;
import xyz.pixelatedw.mineminenomi.packets.PacketDevilFruitSync;
import xyz.pixelatedw.mineminenomi.packets.PacketEntityStatsSync;

public class EventsAbilityValidation
{
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.getEntity() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntity();
			IEntityStats entityStatsProps = EntityStatsCapability.get(player);
			IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
			//QuestProperties questProps = QuestProperties.get(player);
			IAbilityData abilityProps = AbilityDataCapability.get(player);
			
			if (!player.world.isRemote)
			{			
				if (!entityStatsProps.hasRace() && !entityStatsProps.hasFaction() && !entityStatsProps.hasFightingStyle() && !player.inventory.hasItemStack(new ItemStack(ModMiscItems.characterCreator)))
					player.inventory.addItemStackToInventory(new ItemStack(ModMiscItems.characterCreator, 1));
				
				if(!WyHelper.isNullOrEmpty(devilFruitProps.getDevilFruit()))
				{					
					ItemStack df = DevilFruitsHelper.getDevilFruitItem(devilFruitProps.getDevilFruit());
					
					abilityProps.clearDevilFruitAbilities();
					if(!devilFruitProps.getZoanPoint().equalsIgnoreCase("yomi"))
						devilFruitProps.setZoanPoint("");
					
					if(df != null && df.getItem() != null)
					{
						if(devilFruitProps.hasYamiPower())
						{
							ItemStack yami = DevilFruitsHelper.getDevilFruitItem("yamiyami");
							for(Ability a : ((ItemAkumaNoMi)yami.getItem()).abilities)
								if(!DevilFruitsHelper.verifyIfAbilityIsBanned(a))
									abilityProps.addDevilFruitAbility(a);
						}
						for(Ability a : ((ItemAkumaNoMi)df.getItem()).abilities)
							if(!DevilFruitsHelper.verifyIfAbilityIsBanned(a))
								abilityProps.addDevilFruitAbility(a);
					}
					
					for(Ability a : abilityProps.getAbilitiesInHotbar())
						if(a != null && a.isOnCooldown())
							a.startUpdate(player);
				}
				
				DevilFruitsHelper.validateRacialMoves(player);
				DevilFruitsHelper.validateStyleMoves(player);
				
				for(int i = 0; i < abilityProps.countAbilitiesInHotbar(); i++)
				{
					if(abilityProps.getHotbarAbilityFromSlot(i) != null)
					{
						if(DevilFruitsHelper.verifyIfAbilityIsBanned(abilityProps.getHotbarAbilityFromSlot(i)))
							abilityProps.setAbilityInSlot(i, null);
					}
				}			
				
				ModNetwork.sendTo(new PacketEntityStatsSync(entityStatsProps), (ServerPlayerEntity) player);
				ModNetwork.sendTo(new PacketDevilFruitSync(devilFruitProps), (ServerPlayerEntity) player);
				//ModNetwork.sendTo(new PacketQuestSync(questProps), (ServerPlayerEntity) player);
				ModNetwork.sendTo(new PacketAbilityDataSync(abilityProps), (ServerPlayerEntity) player);	
				
				//ModNetwork.sendTo(new PacketAbilityReset(true), (ServerPlayerEntity) player);
			}		
		}
	}
}
