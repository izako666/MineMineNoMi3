package xyz.pixelatedw.mineminenomi.api;

import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.TileEntity;

public class Schematic
{
	private ListNBT tileEntitiesNBT;
	private TileEntity[] tileEtities = new TileEntity[0];
	private short width, height, length;
	private byte[] blocks, data;
	private String name = "N/A";

	public Schematic(String name, ListNBT tilesNBT, short width, short height, short length, byte[] blocks, byte[] data)
	{
		this.name = name;
		this.tileEntitiesNBT = tilesNBT;
		this.width = width;
		this.height = height;
		this.length = length;
		this.blocks = blocks;
		this.data = data;
	}	 

	public int getWidth()
	{
		return width;
	}
	
	public int getLength()
	{
		return length;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public String getName()
	{
		return name;
	}

	public ListNBT getTilesNBT() 
	{
		return tileEntitiesNBT;
	}
	
	public TileEntity[] getTiles()
	{
		return this.tileEtities;
	}
	
	public void setTiles(TileEntity[] tiles)
	{
		this.tileEtities = tiles;
	}
	
	public byte[] getBlocks() 
	{
		return blocks;
	}

	public byte[] getData() 
	{
		return data;
	}
	
}
