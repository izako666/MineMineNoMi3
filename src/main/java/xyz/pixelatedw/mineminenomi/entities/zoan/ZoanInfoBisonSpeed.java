package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.entities.zoan.RenderZoanMorph.Factory;
import xyz.pixelatedw.mineminenomi.entities.zoan.models.ModelBisonSpeed;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class ZoanInfoBisonSpeed extends ZoanInfo
{

	@Override
	public String getDevilFruit()
	{
		return "ushiushibison";
	}

	@Override
	public String getForm()
	{
		return "speed";
	}

	@Override
	public Factory getFactory()
	{
		return new RenderZoanMorph.Factory(new ModelBisonSpeed(), "bisonspeed", 1.4, new float[] { 0, 2.4f, 0 });
	}

	@Override
	public AbilityAttribute getAttribute()
	{
		return ModAttributes.BISON_SPEED_POINT;
	}

	@Override
	public double getWidth()
	{
		return 1.3;
	}

	@Override
	public double getHeight()
	{
		return 1.5;
	}

}
