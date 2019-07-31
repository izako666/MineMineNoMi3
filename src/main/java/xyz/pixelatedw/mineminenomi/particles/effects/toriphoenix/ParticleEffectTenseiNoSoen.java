package xyz.pixelatedw.MineMineNoMi3.particles.effects.toriphoenix;

import java.util.Timer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;
import xyz.pixelatedw.MineMineNoMi3.particles.tasks.ParticleTaskTornado;

public class ParticleEffectTenseiNoSoen extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		Timer timer = new Timer(true); 
		
		CustomParticle particle = new CustomParticle(player.world, ID.PARTICLE_ICON_BLUEFLAME,
				posX, 
				posY + 4,
				posZ, 
				0, 0, 0);
		timer.schedule(ParticleTaskTornado.Create(player, particle.getPos().getX(), particle.getPos().getY(), particle.getPos().getZ(), particle, 4.0, 1, 0.15, -1.7), 0);
	}

}
