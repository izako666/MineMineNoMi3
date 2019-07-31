package xyz.pixelatedw.mineminenomi.api.json.item;

import xyz.pixelatedw.mineminenomi.api.json.JSONModelItem;

public class JSONModelItemBlock extends JSONModelItem
{

	public JSONModelItemBlock(String itemName)
	{
		super(itemName, "item_block");
	}

	@Override
	public String[] getModel()
	{
		return this.replaceMarkedElements();
	}

}
