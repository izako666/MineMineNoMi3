package xyz.pixelatedw.mineminenomi.api.json.models.item;

import xyz.pixelatedw.mineminenomi.api.json.models.JSONModelItem;

public class JSONModelSword extends JSONModelItem
{
	public JSONModelSword(String itemName)
	{
		super(itemName, "sword");
	}

	public String[] getModel()
	{		
		return this.replaceMarkedElements();
	}
}
