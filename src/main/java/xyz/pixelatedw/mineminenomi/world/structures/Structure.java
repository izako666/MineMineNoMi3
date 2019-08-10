package xyz.pixelatedw.mineminenomi.world.structures;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModMiscBlocks;

public abstract class Structure
{
	
	protected static void addChestLoot(World world, ChestTileEntity te, double rarity, ItemStack loot)
	{
		if (getRandomChance(world) <= rarity)
			te.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), loot);
	}
	
	protected static void addChestLoot(World world, ChestTileEntity te, double rarity, Item loot, int min, int max)
	{
		addChestLoot(world, te, rarity, loot, null, min, max);
	}

	protected static void addChestLoot(World world, ChestTileEntity te, double rarity, Item loot, CompoundNBT metadata, int min, int max)
	{
		if (getRandomChance(world) <= rarity)
		{
			int chance = max <= 0 ? min : (int) WyMathHelper.randomWithRange(min, max);
			te.setInventorySlotContents((int) WyMathHelper.randomWithRange(0, 26), new ItemStack(loot, chance, metadata));		
		}
	}
	
	protected static void addChestTileEntity(World world, int[][] positions, int maxChests, Consumer<ChestTileEntity> lootList)
	{
		List<ChestTileEntity> chests = new ArrayList<ChestTileEntity>();
		
		for(int[] pos : positions)
		{
			ChestTileEntity chest = new ChestTileEntity();
			world.setTileEntity(new BlockPos(pos[0], pos[1], pos[2]), chest);
			
			chests.add(chest);
		}

		WyMathHelper.shuffle(chests);
		
		int index = 0;
		for(ChestTileEntity chest : chests)
		{
			if(maxChests > 0 && index > maxChests)
				break;
			
			lootList.accept(chest);
			
			index++;
		}
	}
	
	protected static void addSpawnerTileEntity(World world, int[][] positions, String mobName, int min, int max)
	{
		List<TileEntityCustomSpawner> spawners = new ArrayList<TileEntityCustomSpawner>();
		String mob = ID.PROJECT_ID + "." + mobName;
		
		for(int[] pos : positions)
		{
			int chance = max <= 0 ? min : (int) WyMathHelper.randomWithRange(min, max);		
			TileEntityCustomSpawner spawner = new TileEntityCustomSpawner().setSpawnerMob(mob).setSpawnerLimit(chance);
			world.setBlock(pos[0], pos[1], pos[2], ModMiscBlocks.customSpawner);
			world.setTileEntity(pos[0], pos[1], pos[2], spawner);
		}
	}
	
	protected static Enchantment getEnchantment()
	{
		Enchantment ench = null;
		switch((int)WyMathHelper.randomWithRange(0, 5))
		{
			case 0:
				ench = Enchantments.SHARPNESS;
				break;
			case 1:
				ench = Enchantments.BANE_OF_ARTHROPODS;
				break;
			case 2:
				ench = Enchantments.EFFICIENCY;
				break;
			case 3:
				ench = Enchantments.PROTECTION;
				break;
			case 4:
				ench = Enchantments.THORNS;
				break;
			case 5:
				ench = Enchantments.UNBREAKING;
				break;
			default:
				ench = Enchantments.SHARPNESS;
		}
		
		return ench;
	}
	
	protected static double getRandomChance(World world)
	{
		return world.rand.nextInt(100) + world.rand.nextDouble();
	}
}
