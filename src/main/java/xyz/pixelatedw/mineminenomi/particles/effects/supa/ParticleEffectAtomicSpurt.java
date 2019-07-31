package xyz.pixelatedw.MineMineNoMi3.particles.effects.supa;

import net.minecraft.block.state.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.Particles;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.particles.effects.ParticleEffect;

public class ParticleEffectAtomicSpurt extends ParticleEffect
{

	@Override
	public void spawn(PlayerEntity player, double posX, double posY, double posZ)
	{
		for (int i = 0; i < 2; i++)
		{
			double offsetX = WyMathHelper.randomDouble() / 2;
			double offsetY = 0.25;
			double offsetZ = WyMathHelper.randomDouble() / 2;

			BlockState BlockState = player.world.getBlockState(new BlockPos(posX, posY, posZ).down());
			
			player.world.addParticle(
					new BlockParticleData(Particles.BLOCK, BlockState), 
					posX + offsetX, 
					posY, 
					posZ + offsetZ, 
					0, 
					0, 
					0);			
		}
	}

}
