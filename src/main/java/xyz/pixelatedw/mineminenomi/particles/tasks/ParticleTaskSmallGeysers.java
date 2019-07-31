package xyz.pixelatedw.MineMineNoMi3.particles.tasks;

import java.util.TimerTask;

import net.minecraft.entity.LivingEntity;
import xyz.pixelatedw.MineMineNoMi3.particles.CustomParticle;

public class ParticleTaskSmallGeysers extends TimerTask
{
	private LivingEntity player;
	private Object particle;
	private double posX, posY, posZ;
	
	public ParticleTaskSmallGeysers(LivingEntity player, double posX, double posY, double posZ, String particle)
	{
		this.player = player;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.particle = particle;
	}
	
	public ParticleTaskSmallGeysers(LivingEntity player, double posX, double posY, double posZ, CustomParticle particle)
	{
		this.player = player;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.particle = particle;

	}
	
	@Override
	public void run()
	{
		
	}

}
