package xyz.pixelatedw.mineminenomi.api.json.models.item;

import xyz.pixelatedw.mineminenomi.api.json.models.JSONModelItem;

public class JSONModelRod extends JSONModelItem
{
	public JSONModelRod(String itemName)
	{
		super(itemName, "rod");
	}

	@Override
	public String[] getModel()
	{		
		return this.replaceMarkedElements();
	}
}
