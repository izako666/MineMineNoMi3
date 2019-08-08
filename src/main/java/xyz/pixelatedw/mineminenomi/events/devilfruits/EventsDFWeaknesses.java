package xyz.pixelatedw.mineminenomi.events.devilfruits;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.api.network.PacketAbilityDataSync;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.mobs.EntityNewMob;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;

public class EventsDFWeaknesses
{

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof LivingEntity)
		{
			LivingEntity entity = event.getEntityLiving();
			IDevilFruit props = DevilFruitCapability.get(entity);

			if(props.hasDevilFruit() && DevilFruitsHelper.isAffectedByWater(entity))
			{			
				if(entity instanceof PlayerEntity && !((PlayerEntity) entity).abilities.isCreativeMode)
					entity.setMotion(entity.getMotion().x, entity.getMotion().y - 5, entity.getMotion().z);
				else if(entity instanceof EntityNewMob)
					entity.setMotion(entity.getMotion().x, entity.getMotion().y - 5, entity.getMotion().z);
			}
		}
		
		if (event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			IDevilFruit props = DevilFruitCapability.get(player);
			IAbilityData abilityProps = AbilityDataCapability.get(player);
			ItemStack heldItem = player.getHeldItemMainhand();
			boolean updateDisabledAbilities = false;
			
			if (props.hasDevilFruit() && !player.world.isRemote)
			{
				if (ItemsHelper.hasKairosekiItem(player))
					player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 200, 0));
				
				if (DevilFruitsHelper.isNearbyKairoseki(player))
				{
					for (int i = 0; i < abilityProps.countAbilitiesInHotbar(); i++)
					{
						if (abilityProps.getHotbarAbilityFromSlot(i) != null && !abilityProps.getHotbarAbilityFromSlot(i).isDisabled() && !abilityProps.getHotbarAbilityFromSlot(i).isOnCooldown())
						{						
							abilityProps.getHotbarAbilityFromSlot(i).endPassive(player);
							abilityProps.getHotbarAbilityFromSlot(i).setCooldownActive(true);
							abilityProps.getHotbarAbilityFromSlot(i).disable(player, true);
							updateDisabledAbilities = true;
						}
					}		
					
					if(updateDisabledAbilities)
						ModNetwork.sendTo(new PacketAbilityDataSync(abilityProps), (ServerPlayerEntity) player);					
					player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 0));
				}
				else
				{
					for (int i = 0; i < abilityProps.countAbilitiesInHotbar(); i++)
					{										
						if (abilityProps.getHotbarAbilityFromSlot(i) != null && abilityProps.getHotbarAbilityFromSlot(i).isDisabled())
						{
							abilityProps.getHotbarAbilityFromSlot(i).setPassiveActive(false);
							abilityProps.getHotbarAbilityFromSlot(i).disable(player, false);
							abilityProps.getHotbarAbilityFromSlot(i).startUpdate(player);
							updateDisabledAbilities = true;
						}
						
					}

					for(int i = 0; i < abilityProps.countAbilitiesInHotbar(); i++)
					{					
						if(abilityProps.getHotbarAbilityFromSlot(i) != null && abilityProps.getHotbarAbilityFromSlot(i).isRepeating())			
							abilityProps.getHotbarAbilityFromSlot(i).duringRepeater(player);
					}

					if(updateDisabledAbilities)
						ModNetwork.sendTo(new PacketAbilityDataSync(abilityProps), (ServerPlayerEntity) player);
				}
			}
		}
	}
}
