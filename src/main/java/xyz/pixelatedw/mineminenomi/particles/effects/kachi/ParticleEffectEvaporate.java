package xyz.pixelatedw.MineMineNoMi3.particles.effects.kachi;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;

public class ParticleEffectEvaporate extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 3; i++)
		{
			double offsetX = player.world.rand.nextDouble();
			double offsetY = player.world.rand.nextDouble();
			double offsetZ = player.world.rand.nextDouble();
			
	        double motionX = WyMathHelper.randomWithRange(0, 1) + player.world.rand.nextDouble();
	        double motionY = WyMathHelper.randomWithRange(0, 1) + player.world.rand.nextDouble();
	        double motionZ = WyMathHelper.randomWithRange(0, 1) + player.world.rand.nextDouble();
	        
            double middlePoint = 0.5D / (5 / 0.5);
            middlePoint *= (double)(player.world.rand.nextFloat() * player.world.rand.nextFloat() + 0.3F);
	        
	        motionX *= middlePoint / 2;
	        motionY *= middlePoint / 2;
	        motionZ *= middlePoint / 2;
			
			CustomParticle cp = new CustomParticle(player.world, ID.PARTICLE_ICON_MOKU,
					posX + offsetX, 
					posY + 1.5 + offsetY,
					posZ + offsetZ, 
					motionX,
					motionY + 0.05,
					motionZ)
					.setParticleScale(2.3F)
					.setParticleAge(10);
			Minecraft.getInstance().particles.addEffect(cp);

			cp = new CustomParticle(player.world, ID.PARTICLE_ICON_MERA,
					posX + offsetX, 
					posY + 1.5 + offsetY,
					posZ + offsetZ, 
					motionX,
					motionY + 0.05,
					motionZ)
					.setParticleScale(2.3F)
					.setParticleAge(10);
			Minecraft.getInstance().particles.addEffect(cp);	
		}
	}
}