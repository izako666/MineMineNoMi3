package xyz.pixelatedw.mineminenomi.data.entity.devilfruit;

import xyz.pixelatedw.mineminenomi.api.WyHelper;

public class DevilFruitBase implements IDevilFruit
{

	private String devilFruit = "", zoanPoint = "";
	private boolean isLogia = false, hasYamiPower = false;
	
	@Override
	public String getDevilFruit()
	{
		return this.devilFruit;
	}

	@Override
	public void setDevilFruit(String value)
	{
		this.devilFruit = value;
	}

	@Override
	public boolean hasDevilFruit()
	{
		return !WyHelper.isNullOrEmpty(devilFruit);
	}
	
	@Override
	public boolean isLogia()
	{
		return this.isLogia;
	}

	@Override
	public void setLogia(boolean value)
	{
		this.isLogia = value;
	}

	@Override
	public boolean hasYamiPower()
	{
		return this.hasYamiPower;
	}

	@Override
	public void setYamiPower(boolean value)
	{
		this.hasYamiPower = value;
	}

	@Override
	public String getZoanPoint()
	{
		return this.zoanPoint;
	}

	@Override
	public void setZoanPoint(String value)
	{
		this.zoanPoint = value;
	}

}
