package xyz.pixelatedw.mineminenomi.abilities.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.ID;

public class DFEffectTensionHormone extends DFEffect
{

	public DFEffectTensionHormone(LivingEntity entity, int timer)
	{
		super(entity, timer, ID.EXTRAEFFECT_TENSIONHORMONE);
	}

	@Override
	public void onEffectStart(LivingEntity entity)
	{
		entity.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 1));
		entity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 600, 1));
		entity.addPotionEffect(new EffectInstance(Effects.STRENGTH, 600, 1));
		entity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 1));
	}

	@Override
	public void onEffectEnd(LivingEntity entity)
	{
		entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 300, 1));
	}
		
}
