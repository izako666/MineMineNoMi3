package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.BomuProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class BomuAbilities 
{

	static
	{
		Values.abilityWebAppExtraParams.put("kickbomb", new String[] {"desc", "The user kicks their opponent, detonating their leg on impact."});
		Values.abilityWebAppExtraParams.put("nosefancycannon", new String[] {"desc", "Shoots dried mucus at the opponent, which explodes on impact."});
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new KickBomb(), new NoseFancyCannon()};

	public static class KickBomb extends Ability
	{
		public KickBomb() 
		{
			super(ModAttributes.KICK_BOMB); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{			
			super.use(player);
		} 
	}
	
	public static class NoseFancyCannon extends Ability
	{
		public NoseFancyCannon() 
		{
			super(ModAttributes.NOSE_FANCY_CANNON); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{		
			this.projectile = new BomuProjectiles.NoseFancyCannon(player.world, player, attr);
			super.use(player);
		} 
	}
}
