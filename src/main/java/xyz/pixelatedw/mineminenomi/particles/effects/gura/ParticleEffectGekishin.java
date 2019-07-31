package xyz.pixelatedw.MineMineNoMi3.particles.effects.gura;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.Particles;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;

public class ParticleEffectGekishin extends ParticleEffect
{

	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		double offsetX = 0;
		double offsetZ = 0;
		
		switch(WyHelper.get4Directions(player))
		{
			case NORTH:
				offsetZ = -2.5; break;
			case SOUTH:
				offsetZ = 2.5; break;
			case EAST:
				offsetX = 2.5; break;
			case WEST:
				offsetX = -2.5; break;
			default:
				break;
		}
		
		CustomParticle cp = new CustomParticle(player.world, ID.PARTICLE_ICON_GURA,
				posX + offsetX , 
				posY + 1.0,
				posZ + offsetZ, 
				0, 0, 0)
				.setParticleScale(50).setParticleGravity(0).setParticleAge(10);
		Minecraft.getInstance().particles.addEffect(cp);
		
		for (int i = 0; i < 50; i++)
		{
			offsetX = (new Random().nextInt(40) + 1.0D - 20.0D) / 2.0D;
			double offsetY = (new Random().nextInt(40) + 1.0D - 20.0D) / 10.0D;
			offsetZ = (new Random().nextInt(40) + 1.0D - 20.0D) / 2.0D;
			
		}	
	}

}
