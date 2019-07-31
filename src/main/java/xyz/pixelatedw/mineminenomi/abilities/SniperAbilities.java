package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.SniperProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class SniperAbilities
{
	static
	{
		Values.abilityWebAppExtraParams.put("kaenboshi", new String[] {"desc", "Fires a flaming pellet, that sets the target on fire."});
		Values.abilityWebAppExtraParams.put("kemuriboshi", new String[] {"desc", "On impact releases smoke that poisons and confuses targets."});
		Values.abilityWebAppExtraParams.put("renpatsunamariboshi", new String[] {"desc", "Lets the user fire a barrage of exploding shots."});
		Values.abilityWebAppExtraParams.put("sakuretsusabotenboshi", new String[] {"desc", "The fired projectile explodes on impact and creates cacti arond the target, to trap them."});
	}

	public static Ability KAENBOSHI = new KaenBoshi();
	public static Ability KEMURIBOSHI = new KemuriBoshi();
	public static Ability RENPATSUNAMARIBOSHI = new RenpatsuNamariBoshi();
	public static Ability SAKURETSUSABOTENBOSHI = new SakuretsuSabotenBoshi();
	
	public static Ability[] abilitiesArray = new Ability[] {KAENBOSHI, KEMURIBOSHI, RENPATSUNAMARIBOSHI, SAKURETSUSABOTENBOSHI};
	
	public static class SakuretsuSabotenBoshi extends Ability
	{
		public SakuretsuSabotenBoshi() 
		{
			super(ModAttributes.SAKURETSU_SABOTEN_BOSHI); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			if(!player.world.isRemote)
			{
				this.projectile = new SniperProjectiles.SakuretsuSabotenBoshi(player.world, player, attr);
				this.setPassiveActive(false);
				super.use(player);
			}
		}
	}
	
	public static class RenpatsuNamariBoshi extends Ability
	{
		public RenpatsuNamariBoshi() 
		{
			super(ModAttributes.RENPATSU_NAMARI_BOSHI); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			if(!player.world.isRemote)
			{
				this.projectile = new SniperProjectiles.RenpatsuNamariBoshi(player.world, player, attr);
				this.setPassiveActive(false);
				super.use(player);
			}
		}
	}
	
	public static class KemuriBoshi extends Ability
	{
		public KemuriBoshi() 
		{
			super(ModAttributes.KEMURI_BOSHI); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			if(!player.world.isRemote)
			{
				this.projectile = new SniperProjectiles.KemuriBoshi(player.world, player, attr);
				this.setPassiveActive(false);
				super.use(player);
			}
		}
	}
	
	public static class KaenBoshi extends Ability
	{
		public KaenBoshi() 
		{
			super(ModAttributes.KAEN_BOSHI); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			if(!player.world.isRemote)
			{
				this.projectile = new SniperProjectiles.KaenBoshi(player.world, player, attr);
				this.setPassiveActive(false);
				super.use(player);
			}
		}
	}
	
}
