package xyz.pixelatedw.mineminenomi.data.entity.extraeffects;

public class ExtraEffectBase implements IExtraEffect
{

	private String[] extraEffects = new String[32];
	
	@Override
	public boolean addExtraEffect(String effect)
	{
		for(int i = 0; i < this.extraEffects.length; i++)
		{
			if(this.extraEffects[i] == null || this.extraEffects[i].isEmpty())
			{
				this.extraEffects[i] = effect;
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void removeExtraEffect(String effect)
	{
		for(int i = 0; i < this.extraEffects.length; i++)
		{
			if(this.extraEffects[i] != null && !this.extraEffects[i].isEmpty() && this.extraEffects[i].equalsIgnoreCase(effect))
			{
				this.extraEffects[i] = null;
				break;
			}
		}
	}

	@Override
	public boolean hasExtraEffect(String effect)
	{
		for(int i = 0; i < this.extraEffects.length; i++)
		{
			if(this.extraEffects[i] != null && !this.extraEffects[i].isEmpty() && this.extraEffects[i].equalsIgnoreCase(effect))
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public String[] getExtraEffects()
	{
		return this.extraEffects;
	}

}
