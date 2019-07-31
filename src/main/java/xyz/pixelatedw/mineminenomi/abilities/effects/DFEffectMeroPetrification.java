package xyz.pixelatedw.mineminenomi.abilities.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.ID;

public class DFEffectMeroPetrification extends DFEffect
{

	public DFEffectMeroPetrification(LivingEntity entity, int timer)
	{
		super(entity, timer, ID.EXTRAEFFECT_MERO);
	}

	@Override
	public void onEffectStart(LivingEntity entity)
	{
		entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, this.timer, 1));
		entity.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, this.timer, 1));
		entity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, this.timer, -5));
	}

	@Override
	public void onEffectEnd(LivingEntity entity)
	{
		
	}
		
}
