package xyz.pixelatedw.MineMineNoMi3.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.blocks.tileentities.TileEntityWantedPoster;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.world.ExtendedWorldData;

public class BlockWantedPoster extends BlockContainer
{

	public BlockWantedPoster()
	{
		super(Material.cloth);
	}

	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		AxisAlignedBB box = AxisAlignedBB.getBoundingBox(x + 0.9, y, z, x + 1, y + 1.25, z + 1);

		if (world.getTileEntity(x, y, z) != null)
		{
			int rot = world.getTileEntity(x, y, z).getBlockMetadata();

			switch (rot)
			{
				case 2:
					box = AxisAlignedBB.getBoundingBox(x, y, z + 0.9, x + 1, y + 1.25, z + 1);
					break;
				case 3:
					box = AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1.25, z + 0.1);
					break;
				case 4:
					box = AxisAlignedBB.getBoundingBox(x + 0.9, y, z, x + 1, y + 1.25, z + 1);
					break;
				case 5:
					box = AxisAlignedBB.getBoundingBox(x, y, z, x + 0.1, y + 1.25, z + 1);
					break;
			}
		}

		return box;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		AxisAlignedBB box = AxisAlignedBB.getBoundingBox(x + 0.9, y, z, x + 1, y + 1.25, z + 1);

		if (world.getTileEntity(x, y, z) != null)
		{
			int rot = world.getTileEntity(x, y, z).getBlockMetadata();

			switch (rot)
			{
				case 2:
					box = AxisAlignedBB.getBoundingBox(x, y, z + 0.9, x + 1, y + 1.25, z + 1);
					break;
				case 3:
					box = AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1.25, z + 0.1);
					break;
				case 4:
					box = AxisAlignedBB.getBoundingBox(x + 0.9, y, z, x + 1, y + 1.25, z + 1);
					break;
				case 5:
					box = AxisAlignedBB.getBoundingBox(x, y, z, x + 0.1, y + 1.25, z + 1);
					break;
			}
		}
		return box;
	}

    public void breakBlock(World world, int posX, int posY, int posZ, Block block, int i1)
    {
    	ExtendedWorldData worldData = ExtendedWorldData.get(world);
    	
		ItemStack stack = new ItemStack(ListMisc.WantedPoster);
		TileEntityWantedPoster poster = (TileEntityWantedPoster) world.getTileEntity(posX, posY, posZ);
		
		if(poster.getEntityName().isEmpty() || poster.getPosterBounty().isEmpty() || poster.getIssuedDate().isEmpty()) return;
		
    	stack.setTagCompound(DevilFruitsHelper.setWantedData( poster.getEntityName(), Integer.parseInt(poster.getPosterBounty()) ));
    	world.spawnEntityInWorld(new EntityItem(world, posX, posY + 1, posZ, stack));
    }
	
	public boolean isOpaqueCube()
	{
		return false;
	}

	public int getRenderType()
	{
		return -1;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public TileEntity createNewTileEntity(World world, int pass)
	{
		return new TileEntityWantedPoster();
	}

}
