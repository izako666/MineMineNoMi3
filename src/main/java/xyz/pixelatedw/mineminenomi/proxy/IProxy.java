package xyz.pixelatedw.mineminenomi.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public interface IProxy 
{
	
    PlayerEntity getClientPlayer();

    World getClientWorld();
    
	void spawnLogiaParticles(World world, String fx, double posX, double posY, double posZ);

	boolean spawnParticles(PlayerEntity player, double posX, double posY, double posZ, String fx);
	
}