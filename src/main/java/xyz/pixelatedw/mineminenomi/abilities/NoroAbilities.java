package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.NoroProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;

public class NoroAbilities
{

	static
	{
		Values.abilityWebAppExtraParams.put("noronorobeam", new String[] {"desc", "Shoots a beam of photons at the opponent, completely slowing them down."});
		Values.abilityWebAppExtraParams.put("noronorobeamsword", new String[] {"desc", "Focuses photons inside a hilt to create a sword."});
		Values.abilityWebAppExtraParams.put("kyubirush", new String[] {"desc", "While the opponent is slowed, the user delivers a series of punches, which hits the opponent all at once."});
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new NoroNoroBeam(), new NoroNoroBeamSword(), new KyubiRush()};
	
	public static class NoroNoroBeamSword extends Ability
	{
		public NoroNoroBeamSword()
		{
			super(ModAttributes.NORO_NORO_BEAM_SWORD); 
		}
		
		@Override
		public void startPassive(PlayerEntity player) 
		{
			if(player.getHeldItemMainhand() == ItemStack.EMPTY || player.getHeldItemMainhand().getItem() == Items.AIR)
				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ModWeapons.noroNoroBeamSword));
			else
			{
				WyHelper.sendMsgToPlayer(player, "Cannot equip " + this.getAttribute().getAttributeName() + " while holding another item in hand !");
				this.passive(player);
			}
		}
		
		@Override
		public void endPassive(PlayerEntity player) 
		{
			WyHelper.removeStackFromInventory(player, new ItemStack(ModWeapons.noroNoroBeamSword));
		}
	}
		
	
	public static class KyubiRush extends Ability
	{
		public KyubiRush() 
		{
			super(ModAttributes.KYUBI_RUSH); 
		}
		
		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target) 
		{
			float damageFromSlowness = 0;
			
			if(target.isPotionActive(Effects.SLOWNESS))
			{
				damageFromSlowness = (float) (Math.sqrt(target.getActivePotionEffect(Effects.SLOWNESS).getDuration()) / 2);
				
				int newTime = target.getActivePotionEffect(Effects.SLOWNESS).getDuration() / 2;
				int newAmplifier = target.getActivePotionEffect(Effects.SLOWNESS).getAmplifier() - 5;
				
				target.removePotionEffect(Effects.SLOWNESS);
				target.removePotionEffect(Effects.MINING_FATIGUE);			
				target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, newTime, newAmplifier));
				target.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, newTime, newAmplifier));
			}
			else
				damageFromSlowness = 2;
			
			super.hitEntity(player, target);
			target.attackEntityFrom(DamageSource.causePlayerDamage(player), damageFromSlowness);
		}
	}	
	
	public static class NoroNoroBeam extends Ability
	{
		public NoroNoroBeam() 
		{
			super(ModAttributes.NORO_NORO_BEAM); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new NoroProjectiles.NoroNoroBeam(player.world, player, attr);
			super.use(player);
		} 
	}

}
