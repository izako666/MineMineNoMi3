package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.SukeProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class SukeAbilities
{

	static
	{
		Values.abilityWebAppExtraParams.put("shishanote", new String[] {"desc", "Shoots invisible projectiles that explode upon impact."});
		Values.abilityWebAppExtraParams.put("skatting", new String[] {"desc", "Turns the user's entire body invisible."});
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new ShishaNoTe(), new Skatting(), new SukePunch()};
	
	public static class SukePunch extends Ability
	{
		public SukePunch()
		{
			super(ModAttributes.SUKE_PUNCH);
		}

		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target)
		{
			if (target.isPotionActive(Effects.INVISIBILITY))
				target.removePotionEffect(Effects.INVISIBILITY);
			else
				target.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 400, 5, true, false));

			super.hitEntity(player, target);
		}
	}
	
	public static class ShishaNoTe extends Ability
	{
		public ShishaNoTe() 
		{
			super(ModAttributes.SHISHA_NO_TE); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new SukeProjectiles.ShishaNoTe(player.world, player, attr);
			super.use(player);
		} 
	}
	
	public static class Skatting extends Ability
	{
		public Skatting() 
		{
			super(ModAttributes.SKATTING); 
		}	
	}

}
