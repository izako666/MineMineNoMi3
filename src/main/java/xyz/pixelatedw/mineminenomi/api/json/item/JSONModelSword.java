package xyz.pixelatedw.mineminenomi.api.json.item;

import xyz.pixelatedw.mineminenomi.api.json.JSONModelItem;

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
