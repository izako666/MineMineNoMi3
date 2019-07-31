package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.api.telemetry.WyTelemetry;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

public class ItemBellyPouch extends Item
{

	public ItemBellyPouch()
	{
		super(new Properties().group(ModCreativeTabs.MISC).maxStackSize(1));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    { 
		IEntityStats props = EntityStatsCapability.get(player);
		
		if(!world.isRemote)
		{
			int amount = (int) WyMathHelper.randomWithRange(5, 100);		
			
			System.out.println(amount);
			
			if(props.getBelly() <= Values.MAX_GENERAL - amount)
			{
				props.alterBelly(amount);
				WyHelper.sendMsgToPlayer(player, "You've obtained " + amount + " belly !");
				WyHelper.removeStackFromInventory(player, player.getHeldItem(hand));
			}
			else
				props.setBelly(Values.MAX_GENERAL);	
			
	    	if(!player.abilities.isCreativeMode)
	    		WyTelemetry.addMiscStat("bellyEarnedFromPouches", "Belly Earned From Pouches", amount);
		}
				
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
}
