package xyz.pixelatedw.mineminenomi.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

public class ItemCharacterCreator extends Item
{
	
    public ItemCharacterCreator()
	{
		super(new Properties().group(ModCreativeTabs.MISC).maxStackSize(1));
	}

	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    { 
		//Minecraft.getInstance().displayGuiScreen(new GUICharacterCreator(player));
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}

}
