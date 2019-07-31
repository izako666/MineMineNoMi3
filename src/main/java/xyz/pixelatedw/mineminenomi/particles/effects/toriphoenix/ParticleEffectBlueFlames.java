package xyz.pixelatedw.MineMineNoMi3.particles.effects.toriphoenix;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.ModMain;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;

public class ParticleEffectBlueFlames extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 50; i++)
		{
			double offsetX = (new Random().nextInt(50) + 1.0D - 25.0D) / 30.0D;
			double offsetY = (new Random().nextInt(50) + 1.0D - 25.0D) / 30.0D;
			double offsetZ = (new Random().nextInt(50) + 1.0D - 25.0D) / 30.0D;
			
			CustomParticle cp = new CustomParticle(player.world, ID.PARTICLE_ICON_BLUEFLAME,
					posX + offsetX, 
					posY + 1 + offsetY,
					posZ + offsetZ, 
					0, 0, 0)
					.setParticleScale(1.2F).setParticleGravity(0).setParticleAge(1);
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}

}
