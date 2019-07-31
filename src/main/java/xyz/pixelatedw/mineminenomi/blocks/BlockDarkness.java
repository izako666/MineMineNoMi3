package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BlockDarkness extends Block
{	
	public BlockDarkness()
	{
		super(Block.Properties.create(Material.WEB).doesNotBlockMovement().hardnessAndResistance(Float.MAX_VALUE).noDrops());
	}

	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
	{
		entityIn.setMotionMultiplier(state, new Vec3d(0.25D, 0.05F, 0.25D));
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving)
	{
		world.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(world));
	}
}
