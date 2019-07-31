package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.MokuProjectiles;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class MokuAbilities 
{

	static
	{
		Values.abilityWebAppExtraParams.put("whiteout", new String[] {"desc", "Shoots clouds of smoke to engulf the opponent and trap them."});
		Values.abilityWebAppExtraParams.put("whitesnake", new String[] {"desc", "Launches a long dense smoke cloud in the shape of a snake to grab the opponent and damage them."});
		Values.abilityWebAppExtraParams.put("whitelauncher", new String[] {"desc", "Transforms the user into smoke and launches them forward."});
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new WhiteOut(), new WhiteSnake(), new WhiteLauncher(), new WhiteStrike()};
	
	public static class WhiteStrike extends Ability
	{
		public WhiteStrike() 
		{
			super(ModAttributes.WHITE_STRIKE); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{	
			if(!this.isOnCooldown())
				ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_WHITESTRIKE, player), (ServerPlayerEntity) player);
			super.use(player);
		}
	}
	
	public static class WhiteLauncher extends Ability
	{
		public WhiteLauncher() 
		{
			super(ModAttributes.WHITE_LAUNCHER); 
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
			mX *= 5;
			mY *= 1.5;
			mZ *= 5;
		
			DevilFruitsHelper.changeMotion("=", mX, mY, mZ, player);

			super.endCharging(player);
	    }
		
	    @Override
		public void duringCooldown(PlayerEntity player, int currentCooldown)
	    {
			if((currentCooldown / 20) > (ModAttributes.WHITE_LAUNCHER.getAbilityCooldown() / 20) - 3)
			{
				for(LivingEntity e : WyHelper.getEntitiesNear(player, 1.6))
					e.attackEntityFrom(DamageSource.causePlayerDamage(player), 2);
		    	ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_WHITELAUNCHER, player), (ServerPlayerEntity) player);
			}
	    }
	}
	
	public static class WhiteSnake extends Ability
	{
		public WhiteSnake() 
		{
			super(ModAttributes.WHITE_SNAKE); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{		
			this.projectile = new MokuProjectiles.WhiteSnake(player.world, player, attr);
			super.use(player);
		}
	}
	
	public static class WhiteOut extends Ability
	{
		public WhiteOut() 
		{
			super(ModAttributes.WHITE_OUT); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{		
			this.projectile = new MokuProjectiles.WhiteOut(player.world, player, attr);
			super.use(player);
		}
	}
	
}
