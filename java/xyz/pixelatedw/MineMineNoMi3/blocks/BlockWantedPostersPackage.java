package xyz.pixelatedw.MineMineNoMi3.blocks;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.world.ExtendedWorldData;

public class BlockWantedPostersPackage extends Block
{

	public BlockWantedPostersPackage()
	{
		super(Material.iron);
		this.setHardness(1);
	}

    public void breakBlock(World world, int posX, int posY, int posZ, Block block, int i1)
    {
    	ExtendedWorldData worldData = ExtendedWorldData.get(world);
    	
    	// Populating the list with wanted posters
    	List<Entry<String, Integer>> bountiesInPackage = new ArrayList<Entry<String, Integer>>();
    	    	
    	if(!WyHelper.getEntitiesNear(posX, posY, posZ, world, 10).isEmpty())
    	{
    		WyHelper.getEntitiesNear(posX, posY, posZ, world, 10).stream().filter(x -> 
    		{
    			return x instanceof EntityPlayer && ExtendedEntityStats.get(x).getFaction().equalsIgnoreCase(ID.FACTION_PIRATE) && worldData.getBounty(x.getCommandSenderName()) != 0;
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
	    	stack.setTagCompound(DevilFruitsHelper.setWantedData(x.getKey(), x.getValue()));
	    	world.spawnEntityInWorld(new EntityItem(world, posX, posY + 1, posZ, stack));
    	});
    }

}
