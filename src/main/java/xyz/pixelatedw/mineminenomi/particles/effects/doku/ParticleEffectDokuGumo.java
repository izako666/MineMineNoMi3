package xyz.pixelatedw.MineMineNoMi3.particles.effects.doku;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;

public class ParticleEffectDokuGumo extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 30; i++)
		{
			double offsetX = (new Random().nextInt(20) - 10.0D) / 3.0D;
			double offsetY = (new Random().nextInt(20) - 10.0D) / 35.0D;
			double offsetZ = (new Random().nextInt(20) - 10.0D) / 3.0D;
			
			CustomParticle cp = new CustomParticle(player.world, ID.PARTICLE_ICON_DOKU,
					posX - 1 + offsetX + WyMathHelper.randomWithRange(-5, 5), 
					posY + offsetY,
					posZ - 1 + offsetZ + WyMathHelper.randomWithRange(-5, 5), 
					0, 0, 0)
					.setParticleAge((int) (1 + WyMathHelper.randomWithRange(0, 2))).setParticleGravity(0).setParticleScale(1F);
			Minecraft.getInstance().particles.addEffect(cp);

			cp = new CustomParticle(player.world, ID.PARTICLE_ICON_DOKU,
					posX - 1 + offsetX + WyMathHelper.randomWithRange(-5, 5), 
					posY + 1.5 + offsetY,
					posZ - 1 + offsetZ + WyMathHelper.randomWithRange(-5, 5), 
					0, 0, 0)
					.setParticleAge((int) (1 + WyMathHelper.randomWithRange(0, 2))).setParticleGravity(0).setParticleScale(1F);
			Minecraft.getInstance().particles.addEffect(cp);

			cp = new CustomParticle(player.world, ID.PARTICLE_ICON_DOKU,
					posX - 1 + offsetX + WyMathHelper.randomWithRange(-5, 5), 
					posY + 2.5 + offsetY,
					posZ - 1 + offsetZ + WyMathHelper.randomWithRange(-5, 5), 
					0, 0, 0)
					.setParticleAge((int) (1 + WyMathHelper.randomWithRange(0, 2))).setParticleGravity(0).setParticleScale(1F);
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}

}
