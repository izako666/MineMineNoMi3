package xyz.pixelatedw.MineMineNoMi3.particles.effects.yami;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;

public class ParticleEffectKorouzu extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 30; i++)
		{
			double offsetX = WyMathHelper.randomWithRange(-2, 2) + player.world.rand.nextDouble();
			double offsetY = WyMathHelper.randomWithRange(-2, 2) + player.world.rand.nextDouble();
			double offsetZ = WyMathHelper.randomWithRange(-2, 2) + player.world.rand.nextDouble();
			
			CustomParticle cp = new CustomParticle(player.world, ID.PARTICLE_ICON_DARKNESS,
					posX + offsetX , 
					posY + offsetY,
					posZ + offsetZ, 
					0, 0, 0)
					.setParticleScale(3F).setParticleGravity(0).setParticleAge(1);		
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}

}
