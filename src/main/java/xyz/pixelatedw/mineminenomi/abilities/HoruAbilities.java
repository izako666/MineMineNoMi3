package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.abilities.effects.DFEffectChiyuHormone;
import xyz.pixelatedw.mineminenomi.abilities.effects.DFEffectTensionHormone;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class HoruAbilities
{
	public static Ability[] abilitiesArray = new Ability[] {new OnnaHormone(), new ChiyuHormone(), new TensionHormone()};
	
	public static class TensionHormone extends Ability
	{
		public TensionHormone()
		{
			super(ModAttributes.TENSION_HORMONE);
		}
		
		@Override
		public void passive(PlayerEntity player)
		{
			if(player.isSneaking() && !this.isPassiveActive())
			{
				new DFEffectTensionHormone(player, 600);
	            this.startCooldown();
	            this.startExtUpdate(player);
			}
			else
				super.passive(player);
		}
		
		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target)
		{
			super.hitEntity(player, target);
			
			new DFEffectTensionHormone(target, 600);
		}
	}
	
	public static class ChiyuHormone extends Ability
	{
		public ChiyuHormone()
		{
			super(ModAttributes.CHIYUHORMONE);
		}
		
		@Override
		public void passive(PlayerEntity player)
		{
			if(player.isSneaking() && !this.isPassiveActive())
			{
				new DFEffectChiyuHormone(player, 100);
	            this.startCooldown();
	            this.startExtUpdate(player);
			}
			else
				super.passive(player);
		}
		
		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target)
		{
			super.hitEntity(player, target);
			
			new DFEffectChiyuHormone(target, 100);
		}
	}
	
	public static class OnnaHormone extends Ability
	{
		public OnnaHormone() 
		{
			super(ModAttributes.ONNA_HORMONE); 
		}
		
		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target)
		{
			super.hitEntity(player, target);
			
			target.addPotionEffect(new EffectInstance(Effects.NAUSEA, 600, 1));
			target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 600, 1));
			target.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 0));
		}
	}
}
