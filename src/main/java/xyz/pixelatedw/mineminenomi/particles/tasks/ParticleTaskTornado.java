package xyz.pixelatedw.MineMineNoMi3.particles.tasks;

import java.util.TimerTask;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;

public class ParticleTaskTornado extends TimerTask
{
	
	private LivingEntity player;
	private Object particle;
	private double radius, opening, posX, posY, posZ, height;
	private int density, repeats;

	public static ParticleTaskTornado Create(LivingEntity player, double posX, double posY, double posZ, Object particle, double radius, int density, double opening, double height)
	{
		return new ParticleTaskTornado(player, posX, posY, posZ, particle, radius, density, opening, height);
	}
	
	private ParticleTaskTornado(LivingEntity player, double posX, double posY, double posZ, Object particle, double radius, int density, double opening, double height)
	{
		this.player = player;
		this.particle = particle;
		this.radius = radius;
		this.density = density;
		this.opening = opening;
		this.height = height;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
	}

	public void run()
	{
		double phi = 0;
		double x, y, z;
		
		while(phi < Math.PI * density)
		{
			phi += Math.PI / 16;
			for(double t = 0; t <= 2 * Math.PI; t += Math.PI / 16)
			{
				for(double i = 0; i <= 1; i += 1)
				{				
					x = opening * (radius * Math.PI + t) * Math.cos(t * phi + i * Math.PI);
					y = height * t;
					z = opening * (radius * Math.PI + t) * Math.sin(t * phi - i * Math.PI);
					
					try
					{
						if(this.particle instanceof CustomParticle)
						{
							CustomParticle clone = ((CustomParticle)particle).clone(this.posX + x, this.posY + y, this.posZ + z);
							Minecraft.getInstance().particles.addEffect(clone);
						}
						else							
							player.world.addParticle((IParticleData) particle, this.posX + x, this.posY + y, this.posZ + z, 0.0D, 0.0D, 0.0D);
						Thread.sleep(1);
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}	
				}
			}
		}		
	}
}
