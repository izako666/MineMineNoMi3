package xyz.pixelatedw.mineminenomi.entities.zoan;

import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;

public abstract class ZoanInfo
{
	public abstract String getDevilFruit();
	public abstract String getForm();
	
	public abstract RenderZoanMorph.Factory getFactory();
	
	public abstract AbilityAttribute getAttribute();
	
	public abstract double getWidth();
	public abstract double getHeight();
}
