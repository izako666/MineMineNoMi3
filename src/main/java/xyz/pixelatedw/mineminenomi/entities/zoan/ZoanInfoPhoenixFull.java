package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.entities.zoan.RenderZoanMorph.Factory;
import xyz.pixelatedw.mineminenomi.entities.zoan.models.ModelPhoenixFull;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class ZoanInfoPhoenixFull extends ZoanInfo
{

	@Override
	public String getDevilFruit()
	{
		return "toritoriphoenix";
	}

	@Override
	public String getForm()
	{
		return "full";
	}

	@Override
	public Factory getFactory()
	{
		return new RenderZoanMorph.Factory(new ModelPhoenixFull(), "phoenixfull", 1.3, new float[] { 0, -0.5f, 0 });
	}

	@Override
	public AbilityAttribute getAttribute()
	{
		return ModAttributes.PHOENIX_FULL_POINT;
	}

	@Override
	public double getWidth()
	{
		return 0.9;
	}

	@Override
	public double getHeight()
	{
		return 0.8;
	}

}
