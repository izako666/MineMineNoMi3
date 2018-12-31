package xyz.pixelatedw.MineMineNoMi3.helpers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.google.common.collect.Multimap;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedWorldData;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class ItemsHelper
{

	public static void dropWantedPosters(World world, int posX, int posY, int posZ)
	{
    	ExtendedWorldData worldData = ExtendedWorldData.get(world);
    	
    	// Populating the list with wanted posters
    	List<Entry<String, Integer>> bountiesInPackage = new ArrayList<Entry<String, Integer>>();
    	    	
    	if(!WyHelper.getEntitiesNear(posX, posY, posZ, world, 10).isEmpty())
    	{
    		WyHelper.getEntitiesNear(posX, posY, posZ, world, 10).stream().filter(x -> 
    		{
    			return x instanceof EntityPlayer && ExtendedEntityData.get(x).isPirate() && worldData.getBounty(x.getCommandSenderName()) != 0;
    		}).forEach(x -> 
    		{
    			SimpleEntry<String, Integer> se = new SimpleEntry<String, Integer>( x.getCommandSenderName().toLowerCase(), worldData.getBounty(x.getCommandSenderName()) );
    			bountiesInPackage.add( se );
    		});
    	}
    	
    	if((5 + world.rand.nextInt(2)) - bountiesInPackage.size() > 0)
    		bountiesInPackage.addAll( worldData.getAllBounties().entrySet().stream().filter(x -> !bountiesInPackage.contains(x) ).limit((5 + world.rand.nextInt(2)) - bountiesInPackage.size()).collect(Collectors.toList()) );
    	    	
    	// Spawning the wanted posters
    	bountiesInPackage.stream().forEach(x -> 
    	{
    		ItemStack stack = new ItemStack(ListMisc.WantedPoster);
	    	stack.setTagCompound(setWantedData(x.getKey(), x.getValue()));
	    	world.spawnEntityInWorld(new EntityItem(world, posX, posY + 1, posZ, stack));
    	});
	}
	
	public static NBTTagCompound setWantedData(String entityName, int bounty)
	{
		NBTTagCompound data = null;

		data = new NBTTagCompound();

		data.setString("Name", entityName);
		data.setInteger("Bounty", bounty);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = format.format(new Date());

		data.setString("Date", dateString);

		return data;
	}
	
	public static boolean isSword(ItemStack itemStack)
	{
		if (itemStack.getItem() instanceof ItemSword)
			return true;

		Multimap multimap = itemStack.getAttributeModifiers();
		if (multimap.containsKey(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName()))
			return true;

		return false;
	}
	
	public static boolean hasKairosekiItem(EntityPlayer player)
	{
		for (Item itm : Values.KAIROSEKI_ITEMS)
		{
			if (player.inventory.hasItem(itm))
			{
				return true;
			}
		}

		return false;
	}
}
