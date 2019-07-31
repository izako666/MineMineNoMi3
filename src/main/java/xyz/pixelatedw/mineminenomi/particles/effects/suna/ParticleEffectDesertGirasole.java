package xyz.pixelatedw.MineMineNoMi3.particles.effects.suna;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;

public class ParticleEffectDesertGirasole extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{		
		for (int i = 0; i < 64; i++)
		{
			double offsetX = WyMathHelper.randomWithRange(-15, 15);
			double offsetZ = WyMathHelper.randomWithRange(-15, 15);
			
			for (int j = 0; j < 90; j++)
			{
				CustomParticle cp = new CustomParticle(player.world, ID.PARTICLE_ICON_SUNA2,
						posX + offsetX + WyMathHelper.randomDouble(), 
						posY + 0.5 - (0.15 * j),
						posZ + offsetZ + WyMathHelper.randomDouble(), 
						0, 0, 0)
						.setParticleAge( (int) (80 + (0.2 * j)) ).setParticleScale(4).setParticleGravity(-3.8F);
				Minecraft.getInstance().particles.addEffect(cp);
			}
		}	
	}

}
