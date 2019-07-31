package xyz.pixelatedw.mineminenomi.particles.effects.mera;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;

public class ParticleEffectDaiEnkai2 extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		double t = 0;
		double x, y, z;
		Random rand = player.getRNG();

		while(t < 1)
		{
			t += 0.5 * Math.PI;
			
			for(double theta = 0; theta <= 4 * Math.PI; theta += Math.PI / 32)
			{
				x = t * Math.cos(theta);
				y = rand.nextInt(1);
				z = t * Math.sin(theta);
										
				double motionX = x / 10;
				double motionY = 0.05 + (player.world.rand.nextDouble() / 10);
				double motionZ = z / 10;

				CustomParticle cp = new CustomParticle(player.world, ID.PARTICLE_ICON_MERA,
						posX + (x * 1.25) + WyMathHelper.randomDouble(), 
						posY + y,
						posZ + (z * 1.25) + WyMathHelper.randomDouble(), 
						motionX,
						motionY,
						motionZ)
						.setParticleScale(1.3F)
						.setParticleAge(-3);
				Minecraft.getInstance().particles.addEffect(cp);

				cp = new CustomParticle(player.world, ID.PARTICLE_ICON_MERA,
						posX + (x * 2.0) + WyMathHelper.randomDouble(), 
						posY + y,
						posZ + (z * 2.0) + WyMathHelper.randomDouble(), 
						motionX,
						motionY,
						motionZ)
						.setParticleScale(1.3F)
						.setParticleAge(1);
				Minecraft.getInstance().particles.addEffect(cp);
				
				cp = new CustomParticle(player.world, ID.PARTICLE_ICON_MERA,
						posX + (x * 3.25) + WyMathHelper.randomDouble(), 
						posY + y,
						posZ + (z * 3.25) + WyMathHelper.randomDouble(), 
						motionX,
						motionY + 0.1,
						motionZ)
						.setParticleScale(1.3F)
						.setParticleAge(3);
				Minecraft.getInstance().particles.addEffect(cp);
			}
		}
	}

}
