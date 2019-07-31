package xyz.pixelatedw.MineMineNoMi3.particles.effects.mero;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;

public class ParticleEffectPerfumeFemur extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{	
		for (int i = 0; i < 64; i++)
		{
	        double motionX = WyMathHelper.randomWithRange(-3, 3) + player.world.rand.nextDouble();
	        double motionY = WyMathHelper.randomWithRange(-3, 3) + player.world.rand.nextDouble();
	        double motionZ = WyMathHelper.randomWithRange(-3, 3) + player.world.rand.nextDouble();
	        
            double middlePoint = 0.1;
            middlePoint *= (double)(player.world.rand.nextFloat() * player.world.rand.nextFloat() + 0.3F);
	        
	        motionX *= middlePoint / 2;
	        motionY *= middlePoint / 2;
	        motionZ *= middlePoint / 2;
			
			CustomParticle cp = new CustomParticle(player.world, ID.PARTICLE_ICON_MERO,
					posX, 
					posY + 1,
					posZ, 
					motionX,
					motionY,
					motionZ)
					.setParticleScale(1F)
					.setParticleAge(10);
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}
}