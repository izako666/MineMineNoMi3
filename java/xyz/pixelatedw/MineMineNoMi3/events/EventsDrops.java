package xyz.pixelatedw.MineMineNoMi3.events;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class EventsDrops
{

	@SubscribeEvent
    public void onInteractEvent(EntityInteractEvent event)
    {
		if(event.target instanceof EntityLivingBase)
		{
			ExtendedEntityData props = ExtendedEntityData.get(event.entityPlayer);
			ExtendedEntityData propz = ExtendedEntityData.get((EntityLivingBase) event.target);
			ItemStack heldItem = event.entityPlayer.getHeldItem();
			
			if(props.getUsedFruit().equalsIgnoreCase("kagekage") && propz.hasShadow() && heldItem != null && heldItem.getItem() == ListMisc.Scissors)
			{
				propz.setHasShadow(false);
				event.entityPlayer.inventory.addItemStackToInventory(new ItemStack(ListMisc.Shadow));
			}
		}
    }
	
	@SubscribeEvent	
	public void onBreak(BreakEvent event)
	{
		if(MainConfig.enableDFtoDrop && (event.block == Blocks.leaves || event.block == Blocks.leaves2))
		{
			Random rand = new Random();
			double chance = rand.nextInt(99) + rand.nextDouble();
			
			if( chance < MainConfig.rateDFDrops )
			{
				ItemStack df = new ItemStack(Values.devilfruits.get(rand.nextInt(Values.devilfruits.size())));
				event.getPlayer().inventory.addItemStackToInventory(df);
			}
			
		}
	}
	
	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
		if(event.entityLiving instanceof EntityPlayer)
		{	
			EntityPlayer player = ((EntityPlayer)event.entityLiving);

			WyHelper.removeStackFromInventory(player, new ItemStack(ListMisc.CharacterCreator));
		}
	}
	
}
