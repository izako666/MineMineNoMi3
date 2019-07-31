package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyHelper.Direction;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.BaneProjectiles;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class BaneAbilities
{

	static
	{
		Values.abilityWebAppExtraParams.put("springhopper", new String[] {"desc", "Turning the userps legs into springs, which launches them into the air."});
		Values.abilityWebAppExtraParams.put("springdeathknock", new String[] {"desc", "By turning the user's arm into a spring and compressing it, they can launch a powerful punch."});
		Values.abilityWebAppExtraParams.put("springsnipe", new String[] {"desc", "Turning the user's forelegs into springs, they can launch themselves directly at the opponent."});
		
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new SpringDeathKnock(), new SpringSnipe(), new SpringHopper()};
	 
	public static class SpringDeathKnock extends Ability
	{
		public SpringDeathKnock() 
		{
			super(ModAttributes.SPRING_DEATH_KNOCK); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new BaneProjectiles.SpringDeathKnock(player.world, player, attr);
			super.use(player);
		} 
	}
	
	public static class SpringSnipe extends Ability
	{
		public SpringSnipe() 
		{
			super(ModAttributes.SPRING_SNIPE); 
		}	
		
		@Override
		public void endCharging(PlayerEntity player)
		{	
			double mX = -MathHelper.sin(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 0.4;
			double mZ = MathHelper.cos(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 0.4;
			double mY = -MathHelper.sin((player.rotationPitch + 0) / 180.0F * (float)Math.PI) * 0.4;		        
				
			double f2 = MathHelper.sqrt(mX * mX + mY * mY + mZ * mZ);
			mX /= f2;
			mY /= f2;
			mZ /= f2;
			mX += player.world.rand.nextGaussian() * 0.007499999832361937D * 1.0;
			mY += player.world.rand.nextGaussian() * 0.007499999832361937D * 1.0;
			mZ += player.world.rand.nextGaussian() * 0.007499999832361937D * 1.0;
			mX *= 8;
			mY *= 3;
			mZ *= 8;

			//DevilFruitsHelper.changeMotion("=", mX, mY, mZ, player);
			
			super.endCharging(player);
	    }
		
	    @Override
		public void duringCooldown(PlayerEntity player, int currentCooldown)
	    {
			if((currentCooldown / 20) > (ModAttributes.SPRING_SNIPE.getAbilityCooldown() / 20) - 3)
				for(LivingEntity e : WyHelper.getEntitiesNear(player, 1.6))
					e.attackEntityFrom(DamageSource.causePlayerDamage(player), 8);
	    }
	}
	
	public static class SpringHopper extends Ability
	{
		public SpringHopper() 
		{
			super(ModAttributes.SPRING_HOPPER); 
		}

	    @Override
		public void endCharging(PlayerEntity player)
	    {
			Direction dir = WyHelper.get8Directions(player);

			if(player.onGround)
				DevilFruitsHelper.changeMotion("+", 0, 1.2 + (double)1/2, 0, player);
			else
				DevilFruitsHelper.changeMotion("+", 0, 1.36 + (double)1/7, 0, player);
	
			if(dir == WyHelper.Direction.NORTH) 		DevilFruitsHelper.changeMotion("-", 0, 0, 1.4 + (double)1/2, player);
			if(dir == WyHelper.Direction.NORTH_WEST) {	DevilFruitsHelper.changeMotion("-", 1.4 + (double)1/2, 0, 1.4 + (double)1/2, player);}
			if(dir == WyHelper.Direction.SOUTH)			DevilFruitsHelper.changeMotion("+", 0, 0, 1.4 + (double)1/2, player);
			if(dir == WyHelper.Direction.NORTH_EAST) {	DevilFruitsHelper.changeMotion("-", 0, 0, 1.4 + (double)1/2, player);DevilFruitsHelper.changeMotion("+", 1.4 + (double)1/2, 0, 0, player);}
			if(dir == WyHelper.Direction.WEST) 			DevilFruitsHelper.changeMotion("-", 1.4 + (double)1/2, 0, 0, player);
			if(dir == WyHelper.Direction.SOUTH_WEST) {	DevilFruitsHelper.changeMotion("+", 0, 0, 1.4 + (double)1/2, player);DevilFruitsHelper.changeMotion("-", 1.4 + (double)1/2, 0, 0, player);}
			if(dir == WyHelper.Direction.EAST) 			DevilFruitsHelper.changeMotion("+", 1.4 + (double)1/2, 0, 0, player);
			if(dir == WyHelper.Direction.SOUTH_EAST) {	DevilFruitsHelper.changeMotion("+", 1.4 + (double)1/2, 0, 1.4 + (double)1/2, player);}
			
			super.endCharging(player);
	    }
	}

}
