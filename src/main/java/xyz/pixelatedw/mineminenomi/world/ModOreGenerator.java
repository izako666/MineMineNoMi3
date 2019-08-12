package xyz.pixelatedw.mineminenomi.world;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.init.ModMiscBlocks;

public class ModOreGenerator
{
    public static void setupOreGen()
    {
    	for (Biome biome: ForgeRegistries.BIOMES.getValues())
        {
			if(biome.getCategory() == Category.OCEAN || biome.getCategory() == Category.BEACH)
			{
				biome.addFeature
				(
					Decoration.UNDERGROUND_ORES, 
					Biome.createDecoratedFeature
					(
						Feature.ORE, 
						new OreFeatureConfig
						(
							OreFeatureConfig.FillerBlockType.NATURAL_STONE,
							ModMiscBlocks.kairosekiOre.getDefaultState(),
							6
						),
						Placement.COUNT_RANGE,
						new CountRangeConfig(40, 10, 0, 128)
					)
				);
			}		
        }
    }
    
}
