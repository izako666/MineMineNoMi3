package xyz.pixelatedw.mineminenomi.api.json.loottables.block;

import xyz.pixelatedw.mineminenomi.api.json.loottables.JSONLootTableBlock;

public class JSONLootTableSelfDrop extends JSONLootTableBlock
{

	public JSONLootTableSelfDrop(String itemName)
	{
		super(itemName, 1, 1, "simple_drop");
	}

	@Override
	public String[] getLootTable()
	{
		return this.replaceMarkedElements();
	}

}
