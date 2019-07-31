package xyz.pixelatedw.mineminenomi.api.json.block;

import xyz.pixelatedw.mineminenomi.api.json.JSONModelBlock;

public class JSONModelSimpleBlock extends JSONModelBlock
{

	public JSONModelSimpleBlock(String itemName)
	{
		super(itemName, "simple_block", "simple_blockstate");
	}

	@Override
	public String[] getModel()
	{
		return this.replaceMarkedElements(this.getBlockTemplateFile());
	}

	@Override
	public String[] getBlockStateModel()
	{
		return this.replaceMarkedElements(this.getBlockStateTemplateFile());
	}

}
