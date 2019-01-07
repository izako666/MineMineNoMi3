package xyz.pixelatedw.MineMineNoMi3.data;

import java.util.HashMap;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketWorldData;

public class ExtendedWorldData extends WorldSavedData
{

	private static final String IDENTIFIER = "mineminenomi";

	private boolean isSwordsmanDojoSpawned = false;
	private int totalDojosSpawned;
	private HashMap<String, Integer> issuedBounties = new HashMap<String, Integer>();
		
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
			 this.issuedBounties.put((String)x, bounties.getInteger((String)x));
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
				bounties.setInteger(x.getKey(), x.getValue());
			});
		}
		
		nbt.setTag("issuedBounties", bounties);
	}
	
	public HashMap<String, Integer> getAllBounties()
	{
		return this.issuedBounties;
	}
	
	public int getBounty(String name)
	{
		if(this.issuedBounties.containsKey(name.toLowerCase()))
			return this.issuedBounties.get(name.toLowerCase());
		
		return 0;
	}
	
	public void issueBounty(String name, int bounty)
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
	
}
