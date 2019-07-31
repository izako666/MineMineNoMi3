package xyz.pixelatedw.mineminenomi.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;

public class BlockKage extends Block
{

	private int ticks = 100;
	
	public BlockKage()
	{
		super(Block.Properties.create(Material.WEB).doesNotBlockMovement().hardnessAndResistance(Float.MAX_VALUE).tickRandomly().noDrops());
	}

	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
	{
		entityIn.setMotionMultiplier(state, new Vec3d(0.25D, 0.05F, 0.25D));
	}

	@Override
	public int tickRate(IWorldReader world)
	{
		return 1;
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving)
	{
		world.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(world));
	}

	@Override
	public void tick(BlockState state, World world, BlockPos pos, Random random)
	{
		if (ticks > 0)
		{
			ticks--;
		}
		else
		{
			world.setBlockState(pos, Blocks.AIR.getDefaultState());
			ticks = 100 + random.nextInt(10);
		}
		
		world.getPendingBlockTicks().scheduleTick(pos, this, 1, TickPriority.EXTREMELY_HIGH);
	}
}
