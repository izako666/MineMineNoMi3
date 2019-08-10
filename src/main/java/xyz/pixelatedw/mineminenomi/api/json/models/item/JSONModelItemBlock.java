package xyz.pixelatedw.mineminenomi.api.json.models.item;

import xyz.pixelatedw.mineminenomi.api.json.models.JSONModelItem;

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
