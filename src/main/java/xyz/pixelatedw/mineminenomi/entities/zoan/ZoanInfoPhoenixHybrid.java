package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.entities.zoan.RenderZoanMorph.Factory;
import xyz.pixelatedw.mineminenomi.entities.zoan.models.ModelPhoenixHybrid;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class ZoanInfoPhoenixHybrid extends ZoanInfo
{

	@Override
	public String getDevilFruit()
	{
		return "toritoriphoenix";
	}

	@Override
	public String getForm()
	{
		return "hybrid";
	}

	@Override
	public Factory getFactory()
	{
		return new RenderZoanMorph.Factory(new ModelPhoenixHybrid(), "phoenixhybrid", 1, new float[] { 0, 0.2f, 0 });
	}

	@Override
	public AbilityAttribute getAttribute()
	{
		return ModAttributes.PHOENIX_HYBRID_POINT;
	}

	@Override
	public double getWidth()
	{
		return 0.6;
	}

	@Override
	public double getHeight()
	{
		return 1.8;
	}

}
