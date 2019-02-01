package xyz.pixelatedw.MineMineNoMi3.events.devilfruits;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityProperties;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.helpers.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.helpers.ItemsHelper;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class EventsDFWeaknesses
{
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			ExtendedEntityData props = ExtendedEntityData.get(player);
			AbilityProperties abilityProps = AbilityProperties.get(player);
			ItemStack heldItem = player.getHeldItem();

			if(props.hasDevilFruit())
			{
				if( player.isInsideOfMaterial(Material.water) 
						|| (player.isWet() && (player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) == Blocks.water || player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) == Blocks.flowing_water) && !player.isRiding() ))
					if(!player.capabilities.isCreativeMode)
						player.motionY -= 5;
			}
			
			if (props.hasDevilFruit() && !player.worldObj.isRemote)
			{				
				if (ItemsHelper.hasKairosekiItem(player))
					player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 200, 0));

				if (DevilFruitsHelper.isNearbyKairoseki(player))
				{
					for (int i = 0; i < abilityProps.countAbilitiesInHotbar(); i++)
					{
						if (abilityProps.getAbilityFromSlot(i) != null && !abilityProps.getAbilityFromSlot(i).isDisabled())
						{
							abilityProps.getAbilityFromSlot(i).setCooldownActive(true);
							abilityProps.getAbilityFromSlot(i).disable(player, true);
						}
					}
				}
				else
				{
					for (int i = 0; i < abilityProps.countAbilitiesInHotbar(); i++)
					{
						if (abilityProps.getAbilityFromSlot(i) != null && abilityProps.getAbilityFromSlot(i).isDisabled())
						{
							abilityProps.getAbilityFromSlot(i).setCooldownActive(false);
							abilityProps.getAbilityFromSlot(i).disable(player, false);
						}
					}
					
					for(int i = 0; i < abilityProps.countAbilitiesInHotbar(); i++)
					{					
						if(abilityProps.getAbilityFromSlot(i) != null && abilityProps.getAbilityFromSlot(i).isRepeating())
						{ 					
							abilityProps.getAbilityFromSlot(i).duringRepeater(player);
						}				
					}
				}
			}
		}
	}
}
