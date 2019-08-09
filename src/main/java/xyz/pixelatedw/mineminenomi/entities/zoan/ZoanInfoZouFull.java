package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.entities.zoan.RenderZoanMorph.Factory;
import xyz.pixelatedw.mineminenomi.entities.zoan.models.ModelZouFull;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class ZoanInfoZouFull extends ZoanInfo
{

	@Override
	public String getDevilFruit()
	{
		return "zouzou";
	}

	@Override
	public String getForm()
	{
		return "full";
	}

	@Override
	public Factory getFactory()
	{
		return new RenderZoanMorph.Factory(new ModelZouFull(), "zoufull", 1.3, new float[] { 0, 0.65f, 0 });
	}

	@Override
	public AbilityAttribute getAttribute()
	{
		return ModAttributes.ZOU_FULL_POINT;
	}

	@Override
	public double getWidth()
	{
		return 1.7;
	}

	@Override
	public double getHeight()
	{
		return 2.2;
	}

}
