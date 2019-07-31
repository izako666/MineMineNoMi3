package xyz.pixelatedw.MineMineNoMi3.particles.effects.suna;

import java.util.Timer;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;
import xyz.pixelatedw.MineMineNoMi3.particles.tasks.ParticleTaskTornado;

public class ParticleEffectDesertGirasole2 extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{		
		Timer timer = new Timer(true); 

		CustomParticle particle = new CustomParticle(player.world, ID.PARTICLE_ICON_SUNA,
				posX, 
				posY - 1,
				posZ, 
				0, 0, 0)
				.setParticleScale(4).setParticleGravity(-1.5F);

		timer.schedule(ParticleTaskTornado.Create(player, particle.getPos().getX(), particle.getPos().getY(), particle.getPos().getZ(), particle, 0.3, 1, 4, .8), 0);
	}

}
