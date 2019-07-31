package xyz.pixelatedw.MineMineNoMi3.particles.effects.ushibison;

import java.util.Timer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.Particles;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;
import xyz.pixelatedw.MineMineNoMi3.particles.tasks.ParticleTaskTornado2;

public class ParticleEffectKokuteiCross extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		Timer timer = new Timer(true); 
		timer.schedule(ParticleTaskTornado2.Create(player, posX, posY, posZ, Particles.EXPLOSION_EMITTER, 2.0, 1), 0);		
	}

}
