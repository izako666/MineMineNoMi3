package xyz.pixelatedw.MineMineNoMi3.helpers.anvil;

import net.minecraft.item.Item;

public class AnvilRecipe
{
	private int cost = 1;
	private int materialCost = 1;
	private AnvilRecipeType type = AnvilRecipeType.SWORD;
	private Item rightSlot = null;
	
	public AnvilRecipe(int cost, int materialCost, AnvilRecipeType type, Item materialItem)
	{
		this.cost = cost;
		this.materialCost = materialCost;
		this.type = type;
		this.rightSlot = materialItem;
	}
	
	public enum AnvilRecipeType
	{
		SWORD, BOW
	}
}
