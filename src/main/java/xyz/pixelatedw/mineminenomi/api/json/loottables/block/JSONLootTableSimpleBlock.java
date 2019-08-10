package xyz.pixelatedw.mineminenomi.api.json.loottables.block;

import xyz.pixelatedw.mineminenomi.api.json.loottables.JSONLootTableBlock;

public class JSONLootTableSimpleBlock extends JSONLootTableBlock
{

	public JSONLootTableSimpleBlock(String itemName)
	{
		this(itemName, 1, 1);
	}
	
	public JSONLootTableSimpleBlock(String itemName, int min, int max)
	{
		super(itemName, min, max, "simple_drop");
	}

	@Override
	public String[] getLootTable()
	{
		return this.replaceMarkedElements();
	}

}
