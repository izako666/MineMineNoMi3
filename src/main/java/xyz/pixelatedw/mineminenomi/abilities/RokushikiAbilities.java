package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyHelper.Direction;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.RokushikiProjectiles;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class RokushikiAbilities 
{
	
	static
	{
		Values.abilityWebAppExtraParams.put("soru", new String[] {"desc", "Allows the user to move at an extremely high speed.", "dorikiRequiredForHumans", "500"});
		Values.abilityWebAppExtraParams.put("tekkai", new String[] {"desc", "Hardens the user's body to protect themselves, but they're unable to move.", "dorikiRequiredForHumans", "1500"});
		Values.abilityWebAppExtraParams.put("geppo", new String[] {"desc", "The user kicks the air beneath them to launch themselves into the air.", "dorikiRequiredForHumans", "4500"});
		Values.abilityWebAppExtraParams.put("rankyaku", new String[] {"desc", "By kicking at a very high speed, the user launches an air blade at the opponent.", "dorikiRequiredForHumans", "8500"});
		Values.abilityWebAppExtraParams.put("shigan", new String[] {"desc", "The user thrusts their finger at the opponent, to pierce them.", "dorikiRequiredForHumans", "3000"});
		Values.abilityWebAppExtraParams.put("kamie", new String[] {"desc", "Maked the user's body flexible in order to avoid attacks.", "dorikiRequiredForHumans", "6000"});
	}
	
	public static Ability SORU = new Soru();
	public static Ability TEKKAI = new Tekkai();
	public static Ability GEPPO = new Geppo();
	public static Ability RANKYAKU = new Rankyaku();
	public static Ability SHIGAN = new Shigan();
	public static Ability KAMIE = new Kamie();
	
	public static Ability[] abilitiesArray = new Ability[] {SORU, TEKKAI, GEPPO, RANKYAKU, SHIGAN, KAMIE};
	
	public static class Soru extends Ability
	{
		public Soru() 
		{
			super(ModAttributes.SORU); 
		}
	}
	
	public static class Tekkai extends Ability
	{
		public Tekkai() 
		{
			super(ModAttributes.TEKKAI); 
		}
		
		@Override
		public void duringPassive(PlayerEntity player, int passiveTimer)
		{
			if(passiveTimer > 1200)
			{
				this.setPassiveActive(false);
				this.startCooldown();
				this.startExtUpdate(player);
				super.endPassive(player);
			}
		}
		
		@Override
		public void endPassive(PlayerEntity player)
		{
			this.startCooldown();
			this.startExtUpdate(player);	
		}
	}
	
	public static class Geppo extends Ability
	{
		public Geppo() 
		{
			super(ModAttributes.GEPPO); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			if(!this.isOnCooldown)
			{
				Direction dir = WyHelper.get8Directions(player);
				
				double mX = 0;
				double mY = 0;
				double mZ = 0;
				
				if(player.onGround)
					mY += 1.7;
				else
					mY += 1.86;
	
				if(dir == WyHelper.Direction.NORTH) mZ -= 1;
				if(dir == WyHelper.Direction.NORTH_WEST) {mZ -= 1; mX -= 1;}
				if(dir == WyHelper.Direction.SOUTH) mZ += 1;
				if(dir == WyHelper.Direction.NORTH_EAST) {mZ -= 1; mX += 1;}
				if(dir == WyHelper.Direction.WEST) mX -= 1;
				if(dir == WyHelper.Direction.SOUTH_WEST) {mZ += 1; mX -= 1;}
				if(dir == WyHelper.Direction.EAST) mX += 1;
				if(dir == WyHelper.Direction.SOUTH_EAST) {mZ += 1; mX += 1;}
				
				DevilFruitsHelper.changeMotion("=", mX, mY, mZ, player);
				
				ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_GEPPO, player), player);
				
				super.use(player);
			}
		};
	}
		
	public static class Rankyaku extends Ability
	{
		public Rankyaku() 
		{
			super(ModAttributes.RANKYAKU); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new RokushikiProjectiles.Rankyaku(player.world, player, ModAttributes.RANKYAKU);
			super.use(player);
		}
	}
	

	public static class Shigan extends Ability
	{
		public Shigan() 
		{
			super(ModAttributes.SHIGAN); 
		}
		
		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target) 
		{
			super.hitEntity(player, target);
			target.attackEntityFrom(DamageSource.causePlayerDamage(player), 20);
		}
	}
	
	public static class Kamie extends Ability
	{
		public Kamie() 
		{
			super(ModAttributes.KAMI_E); 
		}
		
		@Override
		public void duringPassive(PlayerEntity player, int passiveTimer)
		{
			if(passiveTimer > 400)
			{
				this.setPassiveActive(false);
				this.startCooldown();
				this.startExtUpdate(player);
				super.endPassive(player);
			}
		}
		
		@Override
		public void endPassive(PlayerEntity player)
		{
			this.startCooldown();
			this.startExtUpdate(player);	
		}
	}
}
