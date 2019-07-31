package xyz.pixelatedw.mineminenomi.data.entity.extraeffects;

public interface IExtraEffect
{

	boolean addExtraEffect(String effect);
	void removeExtraEffect(String effect);
	boolean hasExtraEffect(String effect);
	String[] getExtraEffects();
}
