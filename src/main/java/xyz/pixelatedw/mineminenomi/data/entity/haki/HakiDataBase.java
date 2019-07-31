package xyz.pixelatedw.mineminenomi.data.entity.haki;

public class HakiDataBase implements IHakiData
{

	private boolean hasKenHakiActive = false, hasBusoHakiActive = false;
	
	@Override
	public boolean hasKenHakiActive()
	{
		return this.hasKenHakiActive;
	}

	@Override
	public void setKenHakiActive(boolean value)
	{
		this.hasKenHakiActive = value;
	}

	@Override
	public boolean hasBusoHakiActive()
	{
		return this.hasBusoHakiActive;
	}

	@Override
	public void setBusoHakiActive(boolean value)
	{
		this.hasBusoHakiActive = value;
	}

	@Override
	public boolean haHakiActive()
	{
		return hasKenHakiActive() || hasBusoHakiActive();
	}

}
