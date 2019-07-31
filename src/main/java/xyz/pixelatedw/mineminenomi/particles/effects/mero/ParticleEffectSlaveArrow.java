package xyz.pixelatedw.MineMineNoMi3.particles.effects.mero;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;

public class ParticleEffectSlaveArrow extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
        double motionX = (double)(-MathHelper.sin(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 0.05);
        double motionZ = (double)(MathHelper.cos(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 0.05);
        double motionY = (double)(-MathHelper.sin((player.rotationPitch) / 180.0F * (float)Math.PI) * 0);

		CustomParticle cp = new CustomParticle(player.world, ID.PARTICLE_ICON_MERO,
				posX , 
				posY + 1.5,
				posZ, 
				motionX,
				motionY,
				motionZ)
				.setParticleScale(10F)
				.setParticleGravity(0)
				.setParticleAge(50);
		cp.setAlphaF(0.5F);
		Minecraft.getInstance().particles.addEffect(cp);
	}

}
