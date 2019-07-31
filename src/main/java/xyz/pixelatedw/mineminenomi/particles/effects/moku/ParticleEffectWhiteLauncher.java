package xyz.pixelatedw.MineMineNoMi3.particles.effects.moku;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;

public class ParticleEffectWhiteLauncher extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 20; i++)
		{
			double offsetX = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
			double offsetY = (new Random().nextInt(20) + 1.0D - 10.0D) / 10.0D;
			double offsetZ = (new Random().nextInt(20) + 1.0D - 10.0D) / 15.0D;
	      
			CustomParticle cp = new CustomParticle(player.world, ID.PARTICLE_ICON_MOKU,
					posX + offsetX, 
					posY + 1 + offsetY,
					posZ + offsetZ, 
					0, 0, 0)
					.setParticleAge(20).setParticleGravity(0).setParticleScale(1.3F);
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}

}
