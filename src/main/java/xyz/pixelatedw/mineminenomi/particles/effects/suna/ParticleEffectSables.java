package xyz.pixelatedw.MineMineNoMi3.particles.effects.suna;

import java.util.Timer;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;
import xyz.pixelatedw.MineMineNoMi3.particles.tasks.ParticleTaskTornado;

public class ParticleEffectSables extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		Timer timer = new Timer(true); 
		
		CustomParticle particle = new CustomParticle(player.world, ID.PARTICLE_ICON_SUNA2,
				posX, 
				posY - 1,
				posZ, 
				0, 0, 0)
				.setParticleAge(10).setParticleScale(1.3F);
		timer.schedule(ParticleTaskTornado.Create(player, particle.getPos().getX(), particle.getPos().getY(), particle.getPos().getZ(), particle, 2.0, 2, 0.35, 0.7), 0);		
	}

}
