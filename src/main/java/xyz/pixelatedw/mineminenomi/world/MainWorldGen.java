package xyz.pixelatedw.mineminenomi.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.chunk.AbstractChunkProvider;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.common.IWorldGenerator;
import xyz.pixelatedw.mineminenomi.init.ModMiscBlocks;

public class MainWorldGen implements IWorldGenerator 
{
	//-8290517664781417306
	//1682725888991043500

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, ChunkGenerator chunkGenerator, AbstractChunkProvider chunkProvider)
	{
		if(world.dimension.getType() == DimensionType.OVERWORLD)
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
	}	
	
	private void generateSurface(World world, Random random, int i, int j) 
	{
		this.addOreSpawn(ModMiscBlocks.kairosekiOre, world, random, i, j, 16, 16, 4 + random.nextInt(3), 10, 15, 50);	
				
		/*if(CommonConfig.instance.getSpawnShips())
		{
			this.addStructureSpawn(WySchematicHelper.load("marineShip"), world, random, i, j, 1, 1, 1.5 * CommonConfig.instance.getSpawnShipsMultiplier());
			this.addStructureSpawn(WySchematicHelper.load("pyrateShip"), world, random, i, j, 1, 1, 1.8 * CommonConfig.instance.getSpawnShipsMultiplier());
			this.addStructureSpawn(WySchematicHelper.load("pyrateLargeShip"), world, random, i, j, 1, 1, 1.3 * CommonConfig.instance.getSpawnShipsMultiplier());
			this.addStructureSpawn(WySchematicHelper.load("marineLargeShip"), world, random, i, j, 1, 1, 1.4 * CommonConfig.instance.getSpawnShipsMultiplier());
		}
		
		this.addStructureSpawn(WySchematicHelper.load("dojo"), world, random, i, j, 1, 1, 25);*/
		
		/*this.addDialSpawn(ModMiscBlocks.dialEisenBlock, world, random, i, j, 1, 1, 100);
		this.addDialSpawn(ModMiscBlocks.dialFireBlock, world, random, i, j, 1, 1, 70);
		this.addDialSpawn(ModMiscBlocks.dialAxeBlock, world, random, i, j, 1, 1, 70);
		this.addDialSpawn(ModMiscBlocks.dialMilkyBlock, world, random, i, j, 1, 1, 20);
		this.addDialSpawn(ModMiscBlocks.dialRejectBlock, world, random, i, j, 1, 1, 10);
		this.addDialSpawn(ModMiscBlocks.dialBreathBlock, world, random, i, j, 1, 1, 50);
		this.addDialSpawn(ModMiscBlocks.dialFlashBlock, world, random, i, j, 1, 1, 45);*/
		
	}
	 
	public boolean addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY)
	{
		int maxPossY = minY + (maxY - 1);
		int diffBtwnMinMaxY = maxY - minY;
		
		for(int x = 0; x < chancesToSpawn; x++)
		{
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(diffBtwnMinMaxY);
			int posZ = blockZPos + random.nextInt(maxZ);
			Biome biome = world.getBiome(new BlockPos(posX, posY, posZ));
			
			if(block == ModMiscBlocks.kairosekiOre)
			{
				if(biome.getCategory() == Category.OCEAN || biome.getCategory() == Category.BEACH)
				{
					biome.addFeature(
							Decoration.UNDERGROUND_ORES, 
							Biome.createDecoratedFeature
							(
									Feature.ORE, 
									new OreFeatureConfig(
                                            OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                            ModMiscBlocks.kairosekiOre.getDefaultState(),
                                            6),
									Placement.COUNT_RANGE,
									new CountRangeConfig(15, 40, 0, 128)));
					//new WorldGenMinable(block, maxVeinSize).generate(world, random, posX, posY, posZ);
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	/*public boolean addDialSpawn(Block blockToSpawn, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, double rarity)
	{
		if(world.rand.nextInt(100) + world.rand.nextDouble() <= rarity)
		{		
			int posX = blockXPos;
			int posY = random.nextInt(128);
			int posZ = blockZPos;
			BiomeGenBase biome = world.getBiomeGenForCoordsBody(posX, posZ);	
			
			if( (biome == BiomeGenBase.beach || biome == BiomeGenBase.plains) && (world.getBlock(posX, posY - 1, posZ) == Blocks.sand || world.getBlock(posX, posY - 1, posZ) == Blocks.grass) && world.getBlock(posX, posY + 1, posZ) == Blocks.air)
			{
				world.setBlock(posX, posY, posZ, blockToSpawn);
				
				if(WyDebug.isDebug())
					System.out.println("" + blockToSpawn.getLocalizedName() + " spawned at /tp @p " + posX + " " + (posY + 1) + " " + posZ);
				
				WyTelemetry.addStructureStat(WyHelper.getFancyName(blockToSpawn.getLocalizedName()), blockToSpawn.getLocalizedName(), 1);
		    	
		    	return true;
			}
			
		}
		
		return false;
	}
	
	public boolean addStructureSpawn(Schematic sch, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, double rarity)
	{
		if(random.nextInt(100) + random.nextDouble() <= rarity)
		{
			int posX = blockXPos;
			int posY = random.nextInt(128);
			int posZ = blockZPos;
			BiomeGenBase biome = world.getBiomeGenForCoordsBody(posX, posZ);		

			if(sch != null)
			{
				boolean spawned = false;
				
				// Ships
				if(sch.getName().equals("marineShip") || sch.getName().equals("pyrateShip"))
					spawned = StructureSmallShip.build(sch, world, posX, posY, posZ, biome);			
				if(sch.getName().equals("marineLargeShip") || sch.getName().equals("pyrateLargeShip"))
					spawned = StructureLargeShip.build(sch, world, posX, posY, posZ, biome);
				
				// Camps
				if(sch.getName().equals("marineCamp") || sch.getName().equals("banditCamp"))
					spawned = StructureCamp.build(sch, world, posX, posY, posZ, biome);
				
				// Bases
				if(sch.getName().equals("marineLargeBase"))
					spawned = StructureMarineLargeBase.build(sch, world, posX, posY, posZ, biome);
				if(sch.getName().equals("banditBase"))
					spawned = StructureBanditSmallBase.build(sch, world, posX, posY, posZ, biome);
				
				// Quest Related
				if(sch.getName().equals("dojo"))
					spawned = StructureDojo.build(sch, world, posX, posY, posZ, biome);
							
				if(spawned)
				{
					if(WyDebug.isDebug())
						System.out.println("" + sch.getName() + " spawned at /tp @p " + posX + " " + posY + " " + posZ);
	
					WyTelemetry.addStructureStat(WyHelper.getFancyName(sch.getName()), sch.getName(), 1);
				}   	
				
				return spawned;
			}
			else
			{
				Logger.getGlobal().log(Level.SEVERE, "Some schematic is null, this should never happen !");
			}
		}
		
		return false;
	}

	public static boolean checkCorners(Schematic sch, World world, int posX, int posY, int posZ)
	{
		boolean corner1 = false, corner2 = false, corner3 = false, corner4 = false;
		for(int i = 1; i < 4; i++)
		{
			if(!corner1)
				corner1 = world.getBlock(posX, posY - i, posZ).isSideSolid(world, posX, posY - i, posZ, ForgeDirection.DOWN);
			if(!corner2)
				corner2 = world.getBlock(posX + sch.getWidth(), posY - i, posZ).isSideSolid(world, posX + sch.getWidth(), posY - i, posZ, ForgeDirection.DOWN);
			if(!corner3)
				corner3 = world.getBlock(posX, posY - i, posZ + sch.getLength()).isSideSolid(world, posX, posY - i, posZ + sch.getLength(), ForgeDirection.DOWN);
			if(!corner4)
				corner4 = world.getBlock(posX + sch.getWidth(), posY - i, posZ + sch.getLength()).isSideSolid(world, posX + sch.getWidth(), posY - i, posZ + sch.getLength(), ForgeDirection.DOWN);		
	
			if((corner1?1:0) + (corner2?1:0) + (corner3?1:0) + (corner4?1:0) >= 3)
			{
				return true;
			}			
		}
		
		return false;
	}

	public static boolean checkCornersAboveGround(Schematic sch, World world, int posX, int posY, int posZ)
	{
		boolean corner1 = false, corner2 = false, corner3 = false, corner4 = false;
		for(int i = 0; i < 3; i++)
		{
			if(!corner1)
				corner1 = world.getBlock(posX, posY + i, posZ) == Blocks.air;
			if(!corner2)
				corner2 = world.getBlock(posX + sch.getWidth(), posY + i, posZ) == Blocks.air;
			if(!corner3)
				corner3 = world.getBlock(posX, posY + i, posZ + sch.getLength()) == Blocks.air;
			if(!corner4)
				corner4 = world.getBlock(posX + sch.getWidth(), posY + i, posZ + sch.getLength()) == Blocks.air;		

			if((corner1?1:0) + (corner2?1:0) + (corner3?1:0) + (corner4?1:0) >= 3)
			{
				return true;
			}
		}

		return false;
	}
	
	public static boolean checkForWaterSpawn(Schematic s, World world, int posX, int posY, int posZ)
	{
		for(int i = 0; i < s.getWidth(); i++)
		for(int j = 0; j < s.getHeight(); j++)
		for(int k = 0; k < s.getLength(); k++)
		{
			if(world.getBlock(posX + i, posY + j, posZ + k) == Blocks.air) //|| world.getBlock(posX, posY, posZ) == Blocks.water || world.getBlock(posX + i, posY + j, posZ + k) == Blocks.flowing_water)
			{
				if( world.getBlock(posX, posY - 1, posZ) == Blocks.water || world.getBlock(posX, posY - 1, posZ) == Blocks.flowing_water )
				{
					if( world.getBlock(posX, posY + 2, posZ) == Blocks.air)
						return true;
					else return false;
				}
				else return false;
			}
		}		
		return false;
	}*/

}
