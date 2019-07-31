package xyz.pixelatedw.MineMineNoMi3.particles.effects.doku;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;

public class ParticleEffectChloroBallCloud extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 128; i++)
		{
			double offsetX = WyMathHelper.randomWithRange(-2, 2) + WyMathHelper.randomDouble();
			double offsetY = WyMathHelper.randomWithRange(-1, 2) + WyMathHelper.randomDouble();
			double offsetZ = WyMathHelper.randomWithRange(-2, 2) + WyMathHelper.randomDouble();
			
			double motionX = WyMathHelper.randomDouble() / 8;
			double motionZ = WyMathHelper.randomDouble() / 8;
			
			CustomParticle cp = new CustomParticle(player.world, ID.PARTICLE_ICON_DOKU,
					posX + offsetX, 
					posY + offsetY,
					posZ + offsetZ, 
					motionX,
					0.05D,
					motionZ)
					.setParticleAge(5).setParticleGravity(0).setParticleScale(1.5F);
			Minecraft.getInstance().particles.addEffect(cp);
		}	
	}

}
