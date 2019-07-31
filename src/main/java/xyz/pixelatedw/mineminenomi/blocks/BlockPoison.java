package xyz.pixelatedw.mineminenomi.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;

public class BlockPoison extends Block
{
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
	private int ticks = 100;

	public BlockPoison()
	{
		super(Properties.create(Material.ORGANIC).hardnessAndResistance(0.5F).tickRandomly().noDrops());
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		return SHAPE;
	}

	public boolean isFullCube(BlockState state)
	{
		return false;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
	{
		return !worldIn.isAirBlock(pos.down());
	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		return !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : stateIn.updatePostPlacement(facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side)
	{
		return adjacentBlockState.getBlock() == this ? true : state.isSideInvisible(adjacentBlockState, side);
	}

	/*public BlockFaceUV getBlockFaceShape(IBlockReader worldIn, BlockState state, BlockPos pos, Direction face)
	{
		return face == Direction.DOWN ? BlockFaceUV.SOLID : BlockFaceUV.UNDEFINED;
	}*/

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
		if (entity instanceof LivingEntity)
		{
			IDevilFruit props = DevilFruitCapability.get((LivingEntity) entity);

			if (!props.getDevilFruit().equals("dokudoku"))
			{
				if (!((LivingEntity) entity).isPotionActive(Effects.POISON))
				{
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.POISON, 300, 1));
				}
			}
			else
			{
				if (!((LivingEntity) entity).isPotionActive(Effects.REGENERATION))
				{
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.REGENERATION, 50, 0));
				}
			}
		}
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
