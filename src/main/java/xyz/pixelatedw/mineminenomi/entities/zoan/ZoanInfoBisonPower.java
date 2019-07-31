package xyz.pixelatedw.MineMineNoMi3.entities.zoan;

import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.RenderZoanMorph.Factory;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelBisonPower;
import xyz.pixelatedw.MineMineNoMi3.init.ModAttributes;

public class ZoanInfoBisonPower extends ZoanInfo
{

	@Override
	public String getDevilFruit()
	{
		return "ushiushibison";
	}

	@Override
	public String getForm()
	{
		return "power";
	}

	@Override
	public Factory getFactory()
	{
		return new RenderZoanMorph.Factory(new ModelBisonPower(), "bisonpower", 1.4, new float[] { 0, 2.3f, 0 });
	}

	@Override
	public AbilityAttribute getAttribute()
	{
		return ModAttributes.BISON_POWER_POINT;
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
