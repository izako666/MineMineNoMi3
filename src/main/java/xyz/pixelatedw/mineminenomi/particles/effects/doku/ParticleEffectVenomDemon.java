package xyz.pixelatedw.MineMineNoMi3.particles.effects.doku;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;

public class ParticleEffectVenomDemon extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 3; i++)
		{
			double offsetX = WyMathHelper.randomWithRange(-2, 2) + WyMathHelper.randomDouble();
			double offsetY = WyMathHelper.randomWithRange(-2, 0) + WyMathHelper.randomDouble();
			double offsetZ = WyMathHelper.randomWithRange(-2, 2) + WyMathHelper.randomDouble();
			
			CustomParticle cp = new CustomParticle(player.world, ID.PARTICLE_ICON_DOKU,
					posX + offsetX, 
					posY + 1 + offsetY,
					posZ + offsetZ, 
					0, 0, 0)
					.setParticleAge((int) (1 + WyMathHelper.randomWithRange(0, 4))).setParticleScale(1.5F);
			cp.setColor(1, 0, 0);
			
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}

}
