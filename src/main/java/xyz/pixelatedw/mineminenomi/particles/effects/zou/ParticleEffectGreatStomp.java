package xyz.pixelatedw.MineMineNoMi3.particles.effects.zou;

import java.util.Timer;

import net.minecraft.block.state.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.Particles;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;
import xyz.pixelatedw.MineMineNoMi3.particles.tasks.ParticleTaskWave;

public class ParticleEffectGreatStomp extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for(int i = 0; i < 5; i++)
		{
			Timer timer = new Timer(true); 
			BlockState BlockState = player.world.getBlockState(new BlockPos(posX, posY, posZ).down());
			timer.schedule(ParticleTaskWave.Create(player, player.posX, player.posY - 1.5, player.posZ, new BlockParticleData(Particles.BLOCK, BlockState), 12), 0);
		}
	}

}
