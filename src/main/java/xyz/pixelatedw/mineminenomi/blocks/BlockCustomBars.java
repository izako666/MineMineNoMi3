package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.PaneBlock;
import net.minecraft.block.material.Material;

public class BlockCustomBars extends PaneBlock
{

	public BlockCustomBars()
	{
		super(Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F));
	}

}
