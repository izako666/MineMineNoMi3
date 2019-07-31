package xyz.pixelatedw.MineMineNoMi3.entities.zoan;

import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;

public abstract class ZoanInfo
{
	public abstract String getDevilFruit();
	public abstract String getForm();
	
	public abstract RenderZoanMorph.Factory getFactory();
	
	public abstract AbilityAttribute getAttribute();
	
	public abstract double getWidth();
	public abstract double getHeight();
}
