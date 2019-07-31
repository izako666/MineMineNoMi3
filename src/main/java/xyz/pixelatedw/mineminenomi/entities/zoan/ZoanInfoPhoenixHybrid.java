package xyz.pixelatedw.MineMineNoMi3.entities.zoan;

import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.RenderZoanMorph.Factory;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelPhoenixHybrid;
import xyz.pixelatedw.MineMineNoMi3.init.ModAttributes;

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
		return new RenderZoanMorph.Factory(new ModelPhoenixHybrid(), "phoenixhybrid", 1, new float[] { 0, 1.8f, 0 });
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
