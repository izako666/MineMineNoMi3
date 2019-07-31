package xyz.pixelatedw.MineMineNoMi3.particles.effects.pika;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;

public class ParticleEffectFlash extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		CustomParticle cp = new CustomParticle(player.world, ID.PARTICLE_ICON_PIKA,
				posX, 
				posY + 3.5,
				posZ, 
				0,
				0,
				0)
				.setParticleScale(50F)
				.setParticleGravity(0)
				.setParticleAge(10);
		Minecraft.getInstance().particles.addEffect(cp);
	}

}
