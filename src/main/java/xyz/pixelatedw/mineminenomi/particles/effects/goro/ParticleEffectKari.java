package xyz.pixelatedw.MineMineNoMi3.particles.effects.goro;

import java.util.Timer;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;
import xyz.pixelatedw.MineMineNoMi3.particles.tasks.ParticleTaskSparks;

public class ParticleEffectKari extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		Timer timer = new Timer(true); 
		timer.schedule(ParticleTaskSparks.Create(player, player.posX, player.posY, player.posZ, 25, 5, 25), 0);		
	}
	
}
