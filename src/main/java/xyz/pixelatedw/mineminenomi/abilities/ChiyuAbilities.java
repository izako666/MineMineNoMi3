package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class ChiyuAbilities
{

	public static Ability[] abilitiesArray = new Ability[] {new HealingTouch(), new Chiyupopo()};

	public static class Chiyupopo extends Ability
	{
		public Chiyupopo() 
		{
			super(ModAttributes.CHIYUPOPO); 
		}	

		@Override
		public void use(PlayerEntity player) 
		{
			if(!this.isOnCooldown())
			{
				for(LivingEntity entity : WyHelper.getEntitiesNear(player, 20))
				{
					entity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 200, 0));
				}
				ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_CHIYUPOPO, player), player);
				
				super.use(player);
			}
		}			
	}
	
	public static class HealingTouch extends Ability
	{
		public HealingTouch() 
		{
			super(ModAttributes.HEALING_TOUCH); 
		}	

		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target) 
		{
			target.setHealth(target.getHealth() + 20);
			target.addPotionEffect(new EffectInstance(Effects.REGENERATION, 400, 1));
			
			super.hitEntity(player, target);
			
			AbilityExplosion explosion = WyHelper.newExplosion(player, target.posX, target.posY, target.posZ, 2);
			explosion.setExplosionSound(false);
			explosion.setDamageOwner(false);
			explosion.setDestroyBlocks(false);
			explosion.setDamageEntities(false);
			explosion.setSmokeParticles(ID.PARTICLEFX_HEALINGTOUCH);
			explosion.doExplosion();
			
			passiveActive = false;
			startCooldown();
			this.startExtUpdate(player);
		}
	}
	
}
