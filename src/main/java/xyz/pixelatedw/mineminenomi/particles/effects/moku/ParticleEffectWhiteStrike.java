package xyz.pixelatedw.MineMineNoMi3.particles.effects.moku;

import java.util.Timer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;
import xyz.pixelatedw.MineMineNoMi3.particles.tasks.ParticleTaskWave;

public class ParticleEffectWhiteStrike extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		Timer timer = new Timer(true); 
		
		CustomParticle cp = new CustomParticle(player.world, ID.PARTICLE_ICON_MOKU,
				posX, 
				posY - 0.8, 
				posZ, 
				0, 0, 0)
				.setParticleScale(4F);
		Minecraft.getInstance().particles.addEffect(cp);

		timer.schedule(ParticleTaskWave.Create(player, cp.getPos().getX(), cp.getPos().getY(), cp.getPos().getZ(), cp, 15), 0);
	}
	
}