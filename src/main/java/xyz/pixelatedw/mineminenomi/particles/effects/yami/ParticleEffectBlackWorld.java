package xyz.pixelatedw.MineMineNoMi3.particles.effects.yami;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;

public class ParticleEffectBlackWorld extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 2048 * 2; i++)
		{
			double offsetX = (new Random().nextInt(40) + 1.0D - 20.0D) / 2.0D;
			double offsetY = (new Random().nextInt(40) + 1.0D - 20.0D) / 35.0D;
			double offsetZ = (new Random().nextInt(40) + 1.0D - 20.0D) / 2.0D;
			
			CustomParticle cp = new CustomParticle(player.world, ID.PARTICLE_ICON_DARKNESS,
					posX + offsetX + WyMathHelper.randomWithRange(0, 5), 
					posY + offsetY,
					posZ + offsetZ + WyMathHelper.randomWithRange(0, 5), 
					0, 0, 0)
					.setParticleGravity(-1 + (player.world.rand.nextInt(2) * -1)).setParticleAge(30 + player.world.rand.nextInt(10)).setParticleScale(1.2F);
			Minecraft.getInstance().particles.addEffect(cp);

			cp = new CustomParticle(player.world, ID.PARTICLE_ICON_DARKNESS,
					posX + offsetX + WyMathHelper.randomWithRange(0, 5), 
					posY + 1.5 + offsetY,
					posZ + offsetZ + WyMathHelper.randomWithRange(0, 5), 
					0, 0, 0)
					.setParticleGravity(-1 + (player.world.rand.nextInt(2) * -1)).setParticleAge(30 + player.world.rand.nextInt(10)).setParticleScale(1.2F);
			Minecraft.getInstance().particles.addEffect(cp);

			cp = new CustomParticle(player.world, ID.PARTICLE_ICON_DARKNESS,
					posX + offsetX + WyMathHelper.randomWithRange(0, 5), 
					posY + 2.5 + offsetY,
					posZ + offsetZ + WyMathHelper.randomWithRange(0, 5), 
					0, 0, 0)
					.setParticleGravity(-1 + (player.world.rand.nextInt(2) * -1)).setParticleAge(30 + player.world.rand.nextInt(10)).setParticleScale(1.2F);
			Minecraft.getInstance().particles.addEffect(cp);
		}		

	}

}
