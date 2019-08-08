package xyz.pixelatedw.mineminenomi.events.devilfruits;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;

public class EventsZoanPassives
{
	@SubscribeEvent
	public void onEntityAttack(LivingHurtEvent event)
	{
		if (event.getSource().getTrueSource() instanceof PlayerEntity)
		{
			PlayerEntity attacker = (PlayerEntity) event.getSource().getTrueSource();
			IDevilFruit props = DevilFruitCapability.get(attacker);
			LivingEntity attacked = event.getEntityLiving();
			
			if(props.getDevilFruit().equalsIgnoreCase("ushiushibison") && props.getZoanPoint().equalsIgnoreCase("power"))
				event.setAmount(3);
			
			if(props.getDevilFruit().equalsIgnoreCase("zouzou") && props.getZoanPoint().equalsIgnoreCase("hybrid"))
				event.setAmount(3);
				
			if(props.getDevilFruit().equalsIgnoreCase("mogumogu") && props.getZoanPoint().equalsIgnoreCase("power"))
				event.setAmount(3);
		}
	}
	
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			IDevilFruit props = DevilFruitCapability.get(player);
			ItemStack heldItem = player.getHeldItemMainhand();
			
			if(props.getDevilFruit().equalsIgnoreCase("mogumogu") && props.getZoanPoint().equalsIgnoreCase("power"))
			{
				if(!player.world.isRemote && player.world.getLightFor(LightType.BLOCK, new BlockPos(player.posX, player.posY, player.posZ)) < 7)
				{
					player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 300, 1));					
				}
			}
			
			if(props.getDevilFruit().equals("zouzou"))
			{
				if(props.getZoanPoint().equals("full"))
				{
					player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 2 * 20, 1));
					player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 2 * 20, 0));
				}
				if(props.getZoanPoint().equals("hybrid"))
				{
					player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 2 * 20, 0));
				}
			}
			
			if(props.getDevilFruit().equals("ushiushibison"))
			{
				if(props.getZoanPoint().equals("speed"))
				{
					player.addPotionEffect(new EffectInstance(Effects.SPEED, 2 * 20, 1));
					
					double[] speed = DevilFruitsHelper.propulsion(player, 2, 2) ;
					
					for(LivingEntity e : WyHelper.getEntitiesNear(player, 0.5))
					{
						e.attackEntityFrom(DamageSource.causePlayerDamage(player), 2);
						e.setMotion(speed[0], e.getMotion().y, speed[1]);
					}
				}
				else
				{
					player.removePotionEffect(Effects.SPEED);
				}
			}

		}
	}
}
