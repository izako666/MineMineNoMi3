package xyz.pixelatedw.mineminenomi.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.particles.CustomParticleData;

public class ServerProxy implements IProxy
{

	@Override
	public PlayerEntity getClientPlayer()
	{
		return null;
	}

	@Override
	public World getClientWorld()
	{
		return null;
	}

	@Override
	public void spawnLogiaParticles(World world, String fx, double posX, double posY, double posZ)
	{
		
	}

	@Override
	public void spawnParticles(World world, CustomParticleData data)
	{		
	}

	@Override
	public boolean spawnParticleEffects(PlayerEntity player, double posX, double posY, double posZ, String fx)
	{
		return false;
	}

}
