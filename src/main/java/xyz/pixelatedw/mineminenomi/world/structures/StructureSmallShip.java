package xyz.pixelatedw.mineminenomi.world.structures;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import xyz.pixelatedw.MineMineNoMi3.blocks.tileentities.TileEntityCustomSpawner;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.Schematic;
import xyz.pixelatedw.mineminenomi.api.WySchematicHelper;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.world.MainWorldGen;

public class StructureSmallShip extends Structure
{

	public static boolean build(Schematic sch, World world, int posX, int posY, int posZ, Biome biome)
	{
		boolean flagBiome = biome.getCategory() != Category.OCEAN;
		boolean flagSpecialCheck = !MainWorldGen.checkForWaterSpawn(sch, world, posX, posY, posZ);
		
		if(flagBiome || flagSpecialCheck)
			return false;
		
		WySchematicHelper.build(sch, world, posX, posY, posZ);
		populate(posX, posY, posZ, world, sch.getName());

		return true;
	}

	private static void populate(int posX, int posY, int posZ, World world, String faction)
	{
		String trashWithSword;
		String trashWithGun;
		Item swordToSpawn;
		if (faction.equals("marineShip"))
		{
			trashWithSword = ID.PROJECT_ID + ".Marine with Sword";
			trashWithGun = ID.PROJECT_ID + ".Marine with Gun";
			swordToSpawn = ModWeapons.marineSword;
		}
		else
		{
			trashWithSword = ID.PROJECT_ID + ".Pirate with Sword";
			trashWithGun = ID.PROJECT_ID + ".Pirate with Gun";
			swordToSpawn = ModWeapons.pirateCutlass;
		}

		TileEntityCustomSpawner spawnTrash01 = new TileEntityCustomSpawner().setSpawnerMob(trashWithGun).setSpawnerLimit(5);
		TileEntityCustomSpawner spawnTrash02 = new TileEntityCustomSpawner().setSpawnerMob(trashWithSword).setSpawnerLimit(5);
		TileEntityCustomSpawner spawnTrash03 = new TileEntityCustomSpawner().setSpawnerMob(trashWithSword).setSpawnerLimit(2);

		world.setBlock(posX + 10, posY + 2, posZ + 32, ListMisc.CustomSpawner);
		world.setTileEntity(posX + 10, posY + 2, posZ + 32, spawnTrash01);
		world.setBlock(posX + 10, posY + 2, posZ + 43, ListMisc.CustomSpawner);
		world.setTileEntity(posX + 10, posY + 2, posZ + 43, spawnTrash02);
		world.setBlock(posX + 10, posY + 7, posZ + 45, ListMisc.CustomSpawner);
		world.setTileEntity(posX + 10, posY + 7, posZ + 45, spawnTrash03);

		world.setBlock(posX + 12, posY + 2, posZ + 32, Blocks.TORCH);
		world.setBlock(posX + 12, posY + 2, posZ + 38, Blocks.TORCH);
		world.setBlock(posX + 12, posY + 2, posZ + 43, Blocks.TORCH);

		TileEntityChest combatChest = new TileEntityChest();
		world.setTileEntity(posX + 11, posY + 2, posZ + 25, combatChest);
		
		addChestLoot(world, combatChest, 100, ListMisc.Bullets, 5, 10);
		addChestLoot(world, combatChest, 100, ListMisc.Bullets, 1, 5);	
		addChestLoot(world, combatChest, 50, ListMisc.KairosekiBullets, 1, 5);
		addChestLoot(world, combatChest, 50, Items.boat, 1, 0);
		addChestLoot(world, combatChest, 45, swordToSpawn, 0, 1);
		addChestLoot(world, combatChest, 45, ListMisc.Flintlock, 0, 1);
		addChestLoot(world, combatChest, 45, ListMisc.BellyPouch, 1, 0);	
		addChestLoot(world, combatChest, 15, ListMisc.BellyPouch, 1, 0);
		addChestLoot(world, combatChest, 10, ListMisc.Box1, 1, 0);
		addChestLoot(world, combatChest, 5, ListMisc.Box2, 1, 0);	

		TileEntityChest foodChest = new TileEntityChest();
		world.setTileEntity(posX + 8, posY + 2, posZ + 25, foodChest);
		
		addChestLoot(world, foodChest, 100, Items.bread, 1, 3);
		addChestLoot(world, foodChest, 100, Items.bread, 0, 1);
		addChestLoot(world, foodChest, 45, Items.baked_potato, 0, 5);
		addChestLoot(world, foodChest, 45, Items.apple, 1, 4);
		if(getRandomChance(world) <= 70)
			addChestLoot(world, foodChest, 45, Items.bread, 1, 3);
		addChestLoot(world, foodChest, 10, ListMisc.Cola, 0, 3);
		if(getRandomChance(world) <= 50)
			addChestLoot(world, foodChest, 10, Items.cooked_chicken, 0, 2);
		else
			addChestLoot(world, foodChest, 10, Items.cooked_beef, 0, 2);
		addChestLoot(world, foodChest, 10, ListMisc.Cola, 0, 5);

		world.setBlock(posX + 11, posY + 2, posZ + 24, Blocks.air);
		world.setBlock(posX + 8, posY + 2, posZ + 24, Blocks.air);

		world.setBlock(posX + 11, posY + 2, posZ + 22, Blocks.air);
		world.setBlock(posX + 8, posY + 2, posZ + 22, Blocks.air);
		world.setBlock(posX + 11, posY + 2, posZ + 21, Blocks.air);
		world.setBlock(posX + 8, posY + 2, posZ + 21, Blocks.air);

	}

}
