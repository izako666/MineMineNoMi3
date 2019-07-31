package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class HakiAbilities 
{		
	static
	{
		Values.abilityWebAppExtraParams.put("busoshokuhaki", new String[] {"desc", "The user forms an invisible armor around themselves using their willpower, By using this form of Haki, the user can damage Logias.", "dorikiRequiredForHumans", "9000", "dorikiRequiredForFishman", "9000", "dorikiRequiredForCyborgs", "8500"});
		Values.abilityWebAppExtraParams.put("kenbunshokuhaki", new String[] {"desc", "Allows the user to sense the presence of others, pointing them to the opponent, Can also locate invisible mobs and players.", "dorikiRequiredForHumans", "5000", "dorikiRequiredForFishman", "4000", "dorikiRequiredForCyborgs", "5500"});
	}
	
	public static Ability KENBUNSHOKU_HAKI = new KenbunshokuHaki();
	public static Ability BUSOSHOKU_HAKI = new BusoshokuHaki();
	
	public static Ability[] abilitiesArray = new Ability[] {KENBUNSHOKU_HAKI, BUSOSHOKU_HAKI};
	
	public static class KenbunshokuHaki extends Ability
	{
		public KenbunshokuHaki() 
		{
			super(ModAttributes.KENBUNSHOKU_HAKI); 
		}
		
		@Override
		public void startPassive(PlayerEntity player)
		{

		}
		
		@Override
		public void endPassive(PlayerEntity player)
		{

		}
	}
	
	public static class BusoshokuHaki extends Ability
	{
		public BusoshokuHaki() 
		{
			super(ModAttributes.BUSOSHOKU_HAKI); 			
		}
		
		@Override
		public void startPassive(PlayerEntity player)
		{

		}
		
		@Override
		public void endPassive(PlayerEntity player)
		{
			
		}
	}
}
