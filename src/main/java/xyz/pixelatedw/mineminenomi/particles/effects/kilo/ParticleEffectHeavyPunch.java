package xyz.pixelatedw.MineMineNoMi3.particles.effects.kilo;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;

public class ParticleEffectHeavyPunch extends ParticleEffect
{

    @Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
    {
        for (int i = 0; i < 20; i++)
        {
            double offsetX = (new Random().nextInt(10) + 1.0D - 10.0D) / 10.0D;
            double offsetY = (new Random().nextInt(30) + 1.0D - 10.0D) / 10.0D;
            double offsetZ = (new Random().nextInt(10) + 1.0D - 10.0D) / 10.0D;

            CustomParticle cp = new CustomParticle(player.world, ID.PARTICLE_ICON_KILO,
                            posX + offsetX,
                            posY + offsetY,
                            posZ + offsetZ,
                            0, 0, 0)
                            .setParticleScale(3F).setParticleGravity(0).setParticleAge(1);
            Minecraft.getInstance().particles.addEffect(cp);
        }
    }

}
