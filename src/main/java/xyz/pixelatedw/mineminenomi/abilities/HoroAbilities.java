package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.HoroProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class HoroAbilities 
{

	static
	{
		Values.abilityWebAppExtraParams.put("negativehollow", new String[] {"desc", "The user launches a ghost that drains the target's will."});
		Values.abilityWebAppExtraParams.put("minihollow", new String[] {"desc", "Launches small ghosts at the opponent, exploding upon impact."});
		Values.abilityWebAppExtraParams.put("tokuhollow", new String[] {"desc", "Creates a huge ghost that causes a massive explosion upon impact."});
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new NegativeHollow(), new MiniHollow(), new TokuHollow()};
	
	public static class TokuHollow extends Ability
	{
		public TokuHollow() 
		{
			super(ModAttributes.TOKU_HOLLOW); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new HoroProjectiles.TokuHollow(player.world, player, attr);
			super.use(player);
		} 
	}
	
	public static class MiniHollow extends Ability
	{
		public MiniHollow() 
		{
			super(ModAttributes.MINI_HOLLOW); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new HoroProjectiles.MiniHollow(player.world, player, attr);
			super.use(player);
		} 
	}
	
	public static class NegativeHollow extends Ability
	{
		public NegativeHollow() 
		{
			super(ModAttributes.NEGATIVE_HOLLOW); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new HoroProjectiles.NegativeHollow(player.world, player, attr);
			super.use(player);
		} 
	}
	
	
}
