package xyz.pixelatedw.mineminenomi.particles.effects.common;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectCommonExplosion extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{	
        player.world.addParticle(ParticleTypes.EXPLOSION_EMITTER, posX, posY, posZ, 1.0D, 0.0D, 0.0D);
		
		for (int i = 0; i < 256; i++)
		{
			int explosionSize = 5;
			
	        double motionX = WyMathHelper.randomWithRange(-2, 2) + player.world.rand.nextDouble();
	        double motionY = WyMathHelper.randomWithRange(-2, 2) + player.world.rand.nextDouble();
	        double motionZ = WyMathHelper.randomWithRange(-2, 2) + player.world.rand.nextDouble();
	        
            double middlePoint = 0.5D / (5 / explosionSize + 0.1D);
            middlePoint *= player.world.rand.nextFloat() * player.world.rand.nextFloat() + 0.3F;
	        
	        motionX *= middlePoint / 2;
	        motionY *= middlePoint / 2;
	        motionZ *= middlePoint / 2;
			
            player.world.addParticle(ParticleTypes.EXPLOSION, posX, posY + 1, posZ, motionX, motionY, motionZ);
            player.world.addParticle(ParticleTypes.SMOKE, posX, posY + 1, posZ, motionX, motionY, motionZ);
		}
	}

}