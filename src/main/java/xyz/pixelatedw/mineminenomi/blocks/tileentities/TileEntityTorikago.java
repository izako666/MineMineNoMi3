package xyz.pixelatedw.mineminenomi.blocks.tileentities;

import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.init.ModMiscBlocks;

public class TileEntityTorikago extends TileEntity implements ITickableTileEntity
{

	public static final TileEntityType TORIKAGO = WyRegistry.registerTileEntity("torikago", TileEntityTorikago::new, ModMiscBlocks.stringMid);

	public TileEntityTorikago()
	{
		super(TORIKAGO);
	}

	@Override
	public void tick()
	{
		if (!this.world.isRemote)
		{
			List<LivingEntity> nearbyPlayers = WyHelper.getEntitiesNear(this, 28).stream().filter(x ->
			{
				if (x instanceof PlayerEntity && DevilFruitCapability.get(x).getDevilFruit().equalsIgnoreCase("itoito"))
					return true;

				return false;
			}).collect(Collectors.toList());

			if (nearbyPlayers.isEmpty())
				clearRoom();
		}
	}

	public void clearRoom()
	{
		World world = this.world;

		for (int i = -22; i < 22; i++)
			for (int k = -21; k < 21; k++)
				for (int j = -22; j < 22; j++)
					if (world.getBlockState(new BlockPos(this.getPos().getX() + i, this.getPos().getY() + k, this.getPos().getZ() + j)).getBlock() == ModMiscBlocks.stringWall)
						world.setBlockState(new BlockPos(this.getPos().getX() + i, this.getPos().getY() + k, this.getPos().getZ() + j), Blocks.AIR.getDefaultState());
		world.setBlockState(new BlockPos(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ()), Blocks.AIR.getDefaultState());
	}

}
