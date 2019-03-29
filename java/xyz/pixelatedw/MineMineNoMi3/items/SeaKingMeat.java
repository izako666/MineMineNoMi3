package xyz.pixelatedw.MineMineNoMi3.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SeaKingMeat extends ItemFood
{

	public SeaKingMeat()
	{
		super(12, 0.8F, false);
	}

	public void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
	{
		super.onFoodEaten(itemStack, world, player);
		if (!world.isRemote)
		{
			player.heal(player.getHealth() / 3.0F);
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 100, 0));
		}
	}
}
