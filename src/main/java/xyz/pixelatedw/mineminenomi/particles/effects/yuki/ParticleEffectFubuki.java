package xyz.pixelatedw.MineMineNoMi3.particles.effects.yuki;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;

public class ParticleEffectFubuki extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 1024 * 15; i++)
		{
			double offsetX = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;
			double offsetY = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;
			double offsetZ = (new Random().nextInt(50) + 1.0D - 25.0D) / 1.0D;

	        double motionX = WyMathHelper.randomWithRange(-1, 1) + player.world.rand.nextDouble();
	        double motionY = WyMathHelper.randomWithRange(-1, 1) + player.world.rand.nextDouble();
	        double motionZ = WyMathHelper.randomWithRange(-1, 1) + player.world.rand.nextDouble();
	        
            double middlePoint = 0.2D;
            middlePoint *= (double)(player.world.rand.nextFloat() * player.world.rand.nextFloat() + 0.3F);
	        
	        motionX *= middlePoint / 2;
	        motionY *= middlePoint / 2;
	        motionZ *= middlePoint / 2;
			
			CustomParticle cp = new CustomParticle(player.world, ID.PARTICLE_ICON_YUKI,
					posX + offsetX, 
					posY + offsetY,
					posZ + offsetZ, 
					motionX, 
					motionY, 
					motionZ)
					.setParticleAge(300).setParticleGravity(30).setParticleScale((float) (1 + WyMathHelper.randomWithRange(0, 2)));
			Minecraft.getInstance().particles.addEffect(cp);
		}		
	}

}
