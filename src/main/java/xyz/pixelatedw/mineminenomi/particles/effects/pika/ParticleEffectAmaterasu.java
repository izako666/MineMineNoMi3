package xyz.pixelatedw.MineMineNoMi3.particles.effects.pika;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;

public class ParticleEffectAmaterasu extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
        double motionX = (double)(-MathHelper.sin(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 1);
        double motionZ = (double)(MathHelper.cos(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 1);
        double motionY = (double)(-MathHelper.sin((player.rotationPitch) / 180.0F * (float)Math.PI) * 1);

		CustomParticle cp = new CustomParticle(player.world, ID.PARTICLE_ICON_PIKA,
				posX, 
				posY,
				posZ, 
				0,
				0,
				0)
				.setParticleScale(50F)
				.setParticleGravity(0)
				.setParticleAge(10);
		Minecraft.getInstance().particles.addEffect(cp);
	}

}
