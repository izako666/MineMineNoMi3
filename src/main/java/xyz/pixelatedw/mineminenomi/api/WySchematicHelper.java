package xyz.pixelatedw.mineminenomi.api;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.common.primitives.UnsignedBytes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.ID;

public class WySchematicHelper
{

	public static Schematic load(String name)
	{
		try
		{
			InputStream is = WySchematicHelper.class.getClassLoader().getResourceAsStream("assets/" + ID.PROJECT_ID + "/schematics/" + name + ".schematic");
			CompoundNBT nbt = CompressedStreamTools.readCompressed(is);

			short width = nbt.getShort("Width");
			short height = nbt.getShort("Height");
			short length = nbt.getShort("Length");

			byte[] blocks = nbt.getByteArray("Blocks");
			byte[] data = nbt.getByteArray("Data");
		
			ListNBT tiles = nbt.getList("TileEntities", 10);

			is.close();

			return new Schematic(name, tiles, width, height, length, blocks, data);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static void build(Schematic sch, World world, int posX, int posY, int posZ)
	{
		build(sch, world, posX, posY, posZ, null);
	}

	public static void build(Schematic sch, World world, int posX, int posY, int posZ, Block airReplacement)
	{
		try
		{
			int i = 0;
			List<TileEntity> tiles = new ArrayList<TileEntity>();
			for (int sy = 0; sy < sch.getHeight(); sy++)
			{
				for (int sz = 0; sz < sch.getLength(); sz++)
				{
					for (int sx = 0; sx < sch.getWidth(); sx++)
					{
						BlockState b = Block.getStateById(UnsignedBytes.toInt(sch.getBlocks()[i]));

						//b = Blocks.air;
						
						if (b != Blocks.AIR.getDefaultState())
						{
							if (world.getBlockState(new BlockPos(posX + sx, posY + sy, posZ + sz)) != b)
							{
								if(b != airReplacement.getDefaultState())
									world.setBlockState(new BlockPos(posX + sx, posY + sy, posZ + sz), b, 2);
								else
									world.setBlockState(new BlockPos(posX + sx, posY + sy, posZ + sz), Blocks.AIR.getDefaultState());
								
								//if (world.getBlockState(new BlockPos(posX + sx, posY + sy, posZ + sz)) == Blocks.WATER.getDefaultState())
								//	world.markBlockForUpdate(posX + sx, posY + sy, posZ + sz);
							}
						}
						i++;
					}
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("[ERROR] The .schematic could not be built due to : " + e.toString());
			e.printStackTrace();
		}
	}

}