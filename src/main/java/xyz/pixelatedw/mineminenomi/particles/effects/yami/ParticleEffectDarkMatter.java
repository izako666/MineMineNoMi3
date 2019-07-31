package xyz.pixelatedw.MineMineNoMi3.particles.effects.yami;

import java.util.Timer;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;
import xyz.pixelatedw.MineMineNoMi3.particles.tasks.ParticleTaskTornado;

public class ParticleEffectDarkMatter extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		Timer timer = new Timer(true); 
		
		CustomParticle particle = new CustomParticle(player.world, ID.PARTICLE_ICON_DARKNESS,
				posX, 
				posY + 1,
				posZ, 
				0, 0, 0)
				.setParticleGravity(-1.25f + player.world.rand.nextFloat()).setParticleScale(player.world.rand.nextInt(3) + 1);
		timer.schedule(ParticleTaskTornado.Create(player, posX, posY, posZ, particle, 8.0, 2, 0.15, 0.5), 0);
	}

}
