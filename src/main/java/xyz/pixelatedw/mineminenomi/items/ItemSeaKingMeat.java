package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.telemetry.WyTelemetry;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

public class ItemSeaKingMeat extends Item
{

	public ItemSeaKingMeat()
	{
		super(new Properties().group(ModCreativeTabs.MISC));
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
			player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 100, 0));
			player.heal(player.getHealth() / 3.0F);
			
	    	if(!player.abilities.isCreativeMode)
	    		WyTelemetry.addMiscStat("seaKingMeatEaten", "Sea King Meat Eaten", 1);
		}
		
		itemStack.shrink(1);
		return itemStack;
	}
}
