package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.telemetry.WyTelemetry;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketEntityStatsSync;

public class ItemCola extends Item
{

	public ItemCola()
	{
		super(new Properties().group(ModCreativeTabs.MISC).maxStackSize(16));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		player.setActiveHand(hand);
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity)
	{
		if(!world.isRemote && entity instanceof PlayerEntity)
		{		
			PlayerEntity player = (PlayerEntity) entity;
			IEntityStats props = EntityStatsCapability.get(player);
			
			if(props.isCyborg())
			{
				if(props.getCola() <= props.getMaxCola() - 15) 
					props.alterCola(15);
				else 
					props.setCola(props.getMaxCola());
			}
			
	    	if(!player.abilities.isCreativeMode)
	    		WyTelemetry.addMiscStat("bottlesOfColaDrank", "Bottles of Cola Drank", 1);
			
			ModNetwork.sendTo(new PacketEntityStatsSync(props), (ServerPlayerEntity) player);
		}
		
		itemStack.shrink(1);
		return itemStack;
	}
}