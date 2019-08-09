package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.entities.zoan.RenderZoanMorph.Factory;
import xyz.pixelatedw.mineminenomi.entities.zoan.models.ModelZouHybrid;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class ZoanInfoZouHybrid extends ZoanInfo
{

	@Override
	public String getDevilFruit()
	{
		return "zouzou";
	}

	@Override
	public String getForm()
	{
		return "hybrid";
	}

	@Override
	public Factory getFactory()
	{
		return new RenderZoanMorph.Factory(new ModelZouHybrid(), "zouhybrid", 1.0, new float[] { 0, 0.2f, 0 });
	}

	@Override
	public AbilityAttribute getAttribute()
	{
		return ModAttributes.ZOU_HYBRID_POINT;
	}

	@Override
	public double getWidth()
	{
		return 1.2;
	}

	@Override
	public double getHeight()
	{
		return 2.7;
	}

}
