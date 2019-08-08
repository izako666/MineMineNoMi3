package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.entities.zoan.RenderZoanMorph.Factory;
import xyz.pixelatedw.mineminenomi.entities.zoan.models.ModelMoguPower;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class ZoanInfoMoguPower extends ZoanInfo
{

	@Override
	public String getDevilFruit()
	{
		return "mogumogu";
	}

	@Override
	public String getForm()
	{
		return "power";
	}

	@Override
	public Factory getFactory()
	{
		return new RenderZoanMorph.Factory(new ModelMoguPower(), "mogu", 0.9, new float[] { 0, 1.7f, 0 });
	}

	@Override
	public AbilityAttribute getAttribute()
	{
		return ModAttributes.MOGU_POWER_POINT;
	}

	@Override
	public double getWidth()
	{
		return 0.8;
	}

	@Override
	public double getHeight()
	{
		return 1.5;
	}

}
