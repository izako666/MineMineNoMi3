package xyz.pixelatedw.MineMineNoMi3.particles.effects.fishkarate;

import java.util.Timer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.Particles;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;
import xyz.pixelatedw.MineMineNoMi3.particles.tasks.ParticleTaskSphere;

public class ParticleEffectSamehada extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		Timer timer = new Timer(true); 
		timer.schedule(ParticleTaskSphere.Create(player, posX, posY, posZ, Particles.SPLASH, 1.5, 10, 1), 0);
	}

}
