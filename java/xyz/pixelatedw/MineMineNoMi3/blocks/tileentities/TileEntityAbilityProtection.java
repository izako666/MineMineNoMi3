package xyz.pixelatedw.MineMineNoMi3.blocks.tileentities;

import java.util.ArrayList;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAbilityProtection extends TileEntity
{

	private int protectedRadius = 100;

	public TileEntityAbilityProtection setRadius(int radius) { protectedRadius = radius; return this; }
	public int getRadius() { return protectedRadius; }
	
	public void readFromNBT(NBTTagCompound nbtTag)
	{
		super.readFromNBT(nbtTag);
		this.protectedRadius = nbtTag.getInteger("radius");
	}

	public void writeToNBT(NBTTagCompound nbtTag)
	{
		super.writeToNBT(nbtTag);
		nbtTag.setInteger("radius", this.protectedRadius);
	}

}
