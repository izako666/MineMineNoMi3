package xyz.pixelatedw.mineminenomi.api.json.item;

import xyz.pixelatedw.mineminenomi.api.json.JSONModelItem;

public class JSONModelSimpleItem extends JSONModelItem
{
	public JSONModelSimpleItem(String itemName)
	{
		super(itemName, "simple_item");
	}

	public String[] getModel()
	{		
		return this.replaceMarkedElements();
	}
}
