package xyz.pixelatedw.mineminenomi.particles.effects;

import net.minecraft.entity.player.PlayerEntity;

public abstract class ParticleEffect
{
	public abstract void spawn(PlayerEntity player, double posX, double posY, double posZ);	
}