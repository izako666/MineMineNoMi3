package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.entities.zoan.RenderZoanMorph.Factory;
import xyz.pixelatedw.mineminenomi.entities.zoan.models.ModelVenomDemon;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class ZoanInfoVenomDemon  extends ZoanInfo
{

	@Override
	public String getDevilFruit()
	{
		return "dokudoku";
	}

	@Override
	public String getForm()
	{
		return "venomDemon";
	}

	@Override
	public Factory getFactory()
	{
		return new RenderZoanMorph.Factory(new ModelVenomDemon(), "venomdemon", 1.1, new float[] { 0, 0.3f, 0 });
	}

	@Override
	public AbilityAttribute getAttribute()
	{
		return ModAttributes.VENOM_DEMON;
	}

	@Override
	public double getWidth()
	{
		return 1.5;
	}

	@Override
	public double getHeight()
	{
		return 2.5;
	}
}
