package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.SniperProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

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
			super(ListAttributes.SAKURETSUSABOTENBOSHI); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!player.worldObj.isRemote)
			{
				this.projectile = new SniperProjectiles.SakuretsuSabotenBoshi(player.worldObj, player, attr);
				this.setPassiveActive(false);
				super.use(player);
			}
		}
	}
	
	public static class RenpatsuNamariBoshi extends Ability
	{
		public RenpatsuNamariBoshi() 
		{
			super(ListAttributes.RENPATSUNAMARIBOSHI); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!player.worldObj.isRemote)
			{
				this.projectile = new SniperProjectiles.RenpatsuNamariBoshi(player.worldObj, player, attr);
				this.setPassiveActive(false);
				super.use(player);
			}
		}
	}
	
	public static class KemuriBoshi extends Ability
	{
		public KemuriBoshi() 
		{
			super(ListAttributes.KEMURIBOSHI); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!player.worldObj.isRemote)
			{
				this.projectile = new SniperProjectiles.KemuriBoshi(player.worldObj, player, attr);
				this.setPassiveActive(false);
				super.use(player);
			}
		}
	}
	
	public static class KaenBoshi extends Ability
	{
		public KaenBoshi() 
		{
			super(ListAttributes.KAENBOSHI); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!player.worldObj.isRemote)
			{
				this.projectile = new SniperProjectiles.KaenBoshi(player.worldObj, player, attr);
				this.setPassiveActive(false);
				super.use(player);
			}
		}
	}
	
}
