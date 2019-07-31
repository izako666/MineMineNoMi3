package xyz.pixelatedw.mineminenomi.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

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
	public boolean spawnParticles(PlayerEntity player, double posX, double posY, double posZ, String fx)
	{
		return false;
	}

}
