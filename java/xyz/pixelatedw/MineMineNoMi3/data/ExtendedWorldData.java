package xyz.pixelatedw.MineMineNoMi3.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.items.AkumaNoMi;

public class ExtendedWorldData extends WorldSavedData
{

	private static final String IDENTIFIER = "mineminenomi";

	private boolean isSwordsmanDojoSpawned = false;
	private int totalDojosSpawned;
	private HashMap<String, Long> issuedBounties = new HashMap<String, Long>();
	private List<String> devilFruitsInWorld = new ArrayList<String>();
	
	public ExtendedWorldData()
	{
		super(IDENTIFIER);
	}

	public ExtendedWorldData(String identifier)
	{
		super(identifier);
	}

	public static ExtendedWorldData get(World world)
	{
		ExtendedWorldData data = (ExtendedWorldData) world.loadItemData(ExtendedWorldData.class, IDENTIFIER);
		if (data == null)
		{
			data = new ExtendedWorldData();
			world.setItemData(IDENTIFIER, data);
		}
		return data;
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		 this.isSwordsmanDojoSpawned = nbt.getBoolean("isSwordsmanDojoSpawned");
		 this.totalDojosSpawned = nbt.getInteger("totalDojosSpawned");
		 
		 NBTTagCompound bounties = nbt.getCompoundTag("issuedBounties");	 
		 this.issuedBounties.clear();
		 bounties.func_150296_c().stream().forEach(x -> 
		 {
			 this.issuedBounties.put((String)x, bounties.getLong((String)x));
		 });
		 
		 NBTTagCompound devilFruits = nbt.getCompoundTag("devilFruits");	 
		 this.devilFruitsInWorld.clear();
		 devilFruits.func_150296_c().stream().forEach(x -> 
		 {
			 this.devilFruitsInWorld.add((String) x);
		 });
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setBoolean("isSwordsmanDojoSpawned",  this.isSwordsmanDojoSpawned);
		nbt.setInteger("totalDojosSpawned", this.totalDojosSpawned);
		
		NBTTagCompound bounties = new NBTTagCompound();	
		if(issuedBounties.size() > 0)
		{
			issuedBounties.entrySet().stream().forEach(x -> 
			{
				bounties.setLong(x.getKey(), x.getValue());
			});
		}		
		nbt.setTag("issuedBounties", bounties);
		
		NBTTagCompound devilFruits = new NBTTagCompound();	
		if(devilFruitsInWorld.size() > 0)
		{
			devilFruitsInWorld.stream().forEach(x -> 
			{
				devilFruits.setBoolean(x, true);
			});
		}		
		nbt.setTag("devilFruits", devilFruits);
	
	}
	
	public HashMap<String, Long> getAllBounties()
	{
		return this.issuedBounties;
	}
	
	public long getBounty(String name)
	{
		if(this.issuedBounties.containsKey(name.toLowerCase()))
			return this.issuedBounties.get(name.toLowerCase());
		
		return 0;
	}
	
	public void issueBounty(String name, long bounty)
	{
		if(this.issuedBounties.containsKey(name.toLowerCase()))
		{
			this.issuedBounties.remove(name.toLowerCase());
			this.issuedBounties.put(name.toLowerCase(), bounty);
		}
		else
			this.issuedBounties.put(name.toLowerCase(), bounty);
		
		markDirty();
	}
	
	public int getTotalDojosSpawned()
	{
		return this.totalDojosSpawned;
	}
	
	public void countUpDojoSpawned()
	{
		this.totalDojosSpawned++;
		if(this.totalDojosSpawned >= MainConfig.maxDojoSpawn)
			this.setSwordsmanDojoSpawned(true);
		markDirty();
	}
	
	public void setDojoSpawned(int value)
	{
		this.totalDojosSpawned = value;
		if(this.totalDojosSpawned >= MainConfig.maxDojoSpawn)
			this.setSwordsmanDojoSpawned(true);
		markDirty();		
	}
	
	public boolean isSwordsmanDojoSpawned() 
	{ 
		return this.isSwordsmanDojoSpawned; 
	}
	
	public void setSwordsmanDojoSpawned(boolean value) 
	{ 
		this.isSwordsmanDojoSpawned = value; 
		markDirty();
	}
	
	public void addDevilFruitInWorld(AkumaNoMi fruit)
	{
		String name = fruit.getUnlocalizedName().substring(5).replace("nomi", "").replace(":", "").replace(",", "").replace("model", "");
	
		if(!this.devilFruitsInWorld.contains(name))
		{
			this.devilFruitsInWorld.add(name);
			markDirty();
		}
	}
	
	public void addDevilFruitInWorld(String name)
	{
		if(!this.devilFruitsInWorld.contains(name))
		{
			this.devilFruitsInWorld.add(name);
			markDirty();
		}
	}
	
	public boolean isDevilFruitInWorld(String name)
	{
		return this.devilFruitsInWorld.contains(name);
	}
}
