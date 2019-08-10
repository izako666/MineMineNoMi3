package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockKairosekiOre extends OreBlock
{
		
	public BlockKairosekiOre()
	{
		super(Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).harvestLevel(0).hardnessAndResistance(8));
	}

}
