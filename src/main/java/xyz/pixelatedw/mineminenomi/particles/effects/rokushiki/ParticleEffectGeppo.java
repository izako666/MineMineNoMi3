package xyz.pixelatedw.MineMineNoMi3.particles.effects.rokushiki;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.Particles;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;

public class ParticleEffectGeppo extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		player.world.addParticle(Particles.CLOUD, (int) posX, (int) posY, (int) posZ, 0, 0, 0);
		
		player.world.addParticle(Particles.CLOUD, (int) posX + 0.2, (int) posY, (int) posZ + 0.2, 0, 0, 0);
		player.world.addParticle(Particles.CLOUD, (int) posX + 0.2, (int) posY, (int) posZ - 0.2, 0, 0, 0);
		player.world.addParticle(Particles.CLOUD, (int) posX - 0.2, (int) posY, (int) posZ - 0.2, 0, 0, 0);
		player.world.addParticle(Particles.CLOUD, (int) posX - 0.2, (int) posY, (int) posZ + 0.2, 0, 0, 0);
			
		player.world.addParticle(Particles.CLOUD, (int) posX + 0.5, (int) posY, (int) posZ, 0, 0, 0);
		player.world.addParticle(Particles.CLOUD, (int) posX + 0.2, (int) posY, (int) posZ, 0, 0, 0);
		player.world.addParticle(Particles.CLOUD, (int) posX - 0.5, (int) posY, (int) posZ, 0, 0, 0);	
		player.world.addParticle(Particles.CLOUD, (int) posX - 0.2, (int) posY, (int) posZ, 0, 0, 0);	
			
		player.world.addParticle(Particles.CLOUD, (int) posX, (int) posY, (int) posZ + 0.5, 0, 0, 0);
		player.world.addParticle(Particles.CLOUD, (int) posX, (int) posY, (int) posZ + 0.2, 0, 0, 0);
		player.world.addParticle(Particles.CLOUD, (int) posX, (int) posY, (int) posZ - 0.5, 0, 0, 0);
		player.world.addParticle(Particles.CLOUD, (int) posX, (int) posY, (int) posZ - 0.2, 0, 0, 0);
	}

}
