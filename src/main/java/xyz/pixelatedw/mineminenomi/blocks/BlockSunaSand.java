package xyz.pixelatedw.mineminenomi.blocks;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.api.WyHelper;

public class BlockSunaSand extends FallingBlock
{

	private final int dustColor;
	
	public BlockSunaSand()
	{
		super(Properties.create(Material.SAND).doesNotBlockMovement().noDrops());
		this.dustColor = WyHelper.hexToRGB("#765038").getRGB();
	}

	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
	{
		entityIn.setMotionMultiplier(state, new Vec3d(0.25D, 0.05F, 0.25D));
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public int getDustColor(BlockState state)
	{
		return this.dustColor;
	}
	
	public int quantityDropped(BlockState state, Random random)
	{
		return 0;
	}
}
