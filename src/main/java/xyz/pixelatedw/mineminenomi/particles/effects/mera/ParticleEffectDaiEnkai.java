package xyz.pixelatedw.mineminenomi.particles.effects.mera;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectDaiEnkai extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 10; i++)
		{
			double offsetX = WyMathHelper.randomWithRange(-3, 3) + player.world.rand.nextDouble();
			double offsetY = WyMathHelper.randomWithRange(-3, 3) + player.world.rand.nextDouble();
			double offsetZ = WyMathHelper.randomWithRange(-3, 3) + player.world.rand.nextDouble();
			
	        double motionX = WyMathHelper.randomWithRange(-1, 1) + player.world.rand.nextDouble();
	        double motionY = WyMathHelper.randomWithRange(-1, 1) + player.world.rand.nextDouble();
	        double motionZ = WyMathHelper.randomWithRange(-1, 1) + player.world.rand.nextDouble();
	        
            double middlePoint = 0.5D / (5 / 0.5);
            middlePoint *= player.world.rand.nextFloat() * player.world.rand.nextFloat() + 0.3F;
	        
	        motionX *= middlePoint / 2;
	        motionY *= middlePoint / 2;
	        motionZ *= middlePoint / 2;
			
			CustomParticle cp = new CustomParticle(player.world, ID.PARTICLE_ICON_MERA,
					posX + offsetX, 
					posY + 1.5 + offsetY,
					posZ + offsetZ, 
					motionX,
					motionY + 0.05,
					motionZ)
					.setParticleScale(1.3F)
					.setParticleAge(10);
			Minecraft.getInstance().particles.addEffect(cp);
	
		}
	}

}
