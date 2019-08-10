package xyz.pixelatedw.mineminenomi.api.json.models.item;

import xyz.pixelatedw.mineminenomi.api.json.models.JSONModelItem;

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
