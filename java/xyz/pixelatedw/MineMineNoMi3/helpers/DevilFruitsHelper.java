package xyz.pixelatedw.MineMineNoMi3.helpers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.google.common.collect.Multimap;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.abilities.CyborgAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.FishKarateAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.HakiAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.RokushikiAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.SniperAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.SwordsmanAbilities;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityProperties;
import xyz.pixelatedw.MineMineNoMi3.api.debug.WyDebug;
import xyz.pixelatedw.MineMineNoMi3.api.quests.QuestProperties;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedWorldData;
import xyz.pixelatedw.MineMineNoMi3.events.customevents.DorikiEvent;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.lists.ListQuests;

public class DevilFruitsHelper
{

	public static String[][] zoanModels = new String[][]
	{
			{
					"ushiushibison", "bison"
			},
			{
					"toritoriphoenix", "phoenix"
			}
	};

	public static String[] flyingFruits = new String[]
	{
			"gasugasu", "sunasuna", "mokumoku"
	};

	private static HashMap<String, List<Block>> blockRules = createBlockRulesMap();
	
	private static HashMap<String, List<Block>> createBlockRulesMap()
	{
		HashMap<String, List<Block>> map = new HashMap<String, List<Block>>();
		
		map.put("core", Arrays.asList(new Block[]
				{
						Blocks.ice, Blocks.packed_ice, Blocks.stone, Blocks.grass, Blocks.dirt, Blocks.snow, Blocks.snow_layer, Blocks.sand, Blocks.sandstone, Blocks.sandstone_stairs, Blocks.wooden_door, Blocks.wooden_slab, Blocks.log, Blocks.log2, Blocks.carpet, Blocks.cake, ListMisc.Poison, ListMisc.DemonPoison, Blocks.torch, Blocks.redstone_torch, Blocks.redstone_wire, Blocks.cobblestone, Blocks.fence, Blocks.farmland, Blocks.fence_gate, Blocks.flower_pot, Blocks.clay, Blocks.gravel
				}));
		
		map.put("air", Arrays.asList(new Block[]
				{
						Blocks.air
				}));

		map.put("foliage", Arrays.asList(new Block[]
				{
						Blocks.leaves, Blocks.leaves2, Blocks.waterlily, Blocks.double_plant, Blocks.yellow_flower, Blocks.red_flower, Blocks.vine, Blocks.brown_mushroom, Blocks.brown_mushroom_block, Blocks.red_mushroom, Blocks.red_mushroom_block, Blocks.tallgrass, Blocks.potatoes, Blocks.carrots, Blocks.cactus
				}));
		
		map.put("ores", Arrays.asList(new Block[]
				{
						Blocks.coal_ore, Blocks.coal_block, Blocks.diamond_ore, Blocks.diamond_block, Blocks.iron_ore, Blocks.iron_block, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.redstone_block, Blocks.gold_ore, Blocks.gold_block, ListMisc.KairosekiOre, ListMisc.KairosekiBlock
				}));

		map.put("liquids", Arrays.asList(new Block[]
				{
						Blocks.water, Blocks.flowing_water, Blocks.lava, Blocks.flowing_lava
				}));
		
		return map;
	}
	
	public static boolean isNearbyKairoseki(EntityPlayer player)
	{
		if (WyHelper.isBlockNearby(player, 4, ListMisc.KairosekiBlock, ListMisc.KairosekiOre) || ItemsHelper.hasKairosekiItem(player) || player.inventory.hasItem(Item.getItemFromBlock(ListMisc.KairosekiBlock)) || player.inventory.hasItem(Item.getItemFromBlock(ListMisc.KairosekiOre)) || (player.isInsideOfMaterial(Material.water) || (player.isWet() && (player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) == Blocks.water || player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) == Blocks.flowing_water) && !player.isRiding())))
		{
			return true;
		}

		return false;
	}

	public static boolean verifyIfAbilityIsBanned(Ability a)
	{
		for (String str : MainConfig.abilityRestrictions)
		{
			if (WyHelper.getFancyName(str).contains(WyHelper.getFancyName(a.getAttribute().getAttributeName())))
				return true;
		}

		return false;
	}

	public static void validateRacialMoves(EntityPlayer player)
	{
		ExtendedEntityData props = ExtendedEntityData.get(player);
		AbilityProperties abilityProps = AbilityProperties.get(player);

		DorikiEvent e = new DorikiEvent(player);
		if (MinecraftForge.EVENT_BUS.post(e))
			return;

		List<Ability> tempAblList = new ArrayList<Ability>();

		if (props.isHuman())
			for (Ability a : RokushikiAbilities.abilitiesArray)
				if (abilityProps.hasRacialAbility(a) && !verifyIfAbilityIsBanned(a))
					tempAblList.add(a);

		if (props.isFishman())
			for (Ability a : FishKarateAbilities.abilitiesArray)
				if (abilityProps.hasRacialAbility(a) && !verifyIfAbilityIsBanned(a))
					tempAblList.add(a);

		if (props.isCyborg())
			for (Ability a : CyborgAbilities.abilitiesArray)
				if (abilityProps.hasRacialAbility(a) && !verifyIfAbilityIsBanned(a))
					tempAblList.add(a);

		abilityProps.clearRacialAbilities();

		for (Ability a : tempAblList)
			abilityProps.addRacialAbility(a);

		tempAblList.clear();

		for (Ability a : HakiAbilities.abilitiesArray)
			if (abilityProps.hasHakiAbility(a) && !verifyIfAbilityIsBanned(a))
				tempAblList.add(a);

		abilityProps.clearHakiAbilities();

		for (Ability a : tempAblList)
			abilityProps.addHakiAbility(a);
	}

	public static void validateStyleMoves(EntityPlayer player)
	{
		ExtendedEntityData props = ExtendedEntityData.get(player);
		QuestProperties questProps = QuestProperties.get(player);
		AbilityProperties abilityProps = AbilityProperties.get(player);

		if (props.isSwordsman())
		{
			if (!verifyIfAbilityIsBanned(SwordsmanAbilities.SHISHISHISONSON))
				abilityProps.addRacialAbility(SwordsmanAbilities.SHISHISHISONSON);

			if (MainConfig.enableQuestProgression)
			{
				if (questProps.hasQuestCompleted(ListQuests.swordsmanProgression04) && !verifyIfAbilityIsBanned(SwordsmanAbilities.SANBYAKUROKUJUPOUNDHO))
					abilityProps.addRacialAbility(SwordsmanAbilities.SANBYAKUROKUJUPOUNDHO);
			}
			else
			{
				if (!verifyIfAbilityIsBanned(SwordsmanAbilities.SANBYAKUROKUJUPOUNDHO))
					abilityProps.addRacialAbility(SwordsmanAbilities.SANBYAKUROKUJUPOUNDHO);
				if (!verifyIfAbilityIsBanned(SwordsmanAbilities.YAKKODORI))
					abilityProps.addRacialAbility(SwordsmanAbilities.YAKKODORI);
				if (!verifyIfAbilityIsBanned(SwordsmanAbilities.OTATSUMAKI))
					abilityProps.addRacialAbility(SwordsmanAbilities.OTATSUMAKI);
			}
		}
		else if (props.isSniper())
		{
			if (!verifyIfAbilityIsBanned(SniperAbilities.KAENBOSHI))
				abilityProps.addRacialAbility(SniperAbilities.KAENBOSHI);

			if (MainConfig.enableQuestProgression)
			{

			}
			else
			{
				if (!verifyIfAbilityIsBanned(SniperAbilities.KEMURIBOSHI))
					abilityProps.addRacialAbility(SniperAbilities.KEMURIBOSHI);
				if (!verifyIfAbilityIsBanned(SniperAbilities.RENPATSUNAMARIBOSHI))
					abilityProps.addRacialAbility(SniperAbilities.RENPATSUNAMARIBOSHI);
				if (!verifyIfAbilityIsBanned(SniperAbilities.SAKURETSUSABOTENBOSHI))
					abilityProps.addRacialAbility(SniperAbilities.SAKURETSUSABOTENBOSHI);
			}
		}
	}

	public static boolean isSniperAbility(Ability abl)
	{
		for (Ability a : SniperAbilities.abilitiesArray)
		{
			if (abl.getAttribute().getAttributeName().equalsIgnoreCase(a.getAttribute().getAttributeName()))
				return true;
		}

		return false;
	}

	/**
	 * Will place a given block in the given positions IF the block it tries to
	 * replace can be replaced given some rules
	 * 
	 * rule format : "add core", "ignore core", if no formula (add, ignore) is
	 * specified "add" will be the default choice
	 * 
	 * core - basic blocks that will always be replaceable either due to their
	 * low value or commonness air - specific filter for the air block ores -
	 * self explanatory, also includes the compressed blocks for those ores
	 * foliage - things like flowers, vines and leaves liquids - water and lava
	 * all - all blocks restricted - removes some blocks that should never be
	 * replaced, has no use for add/ignore param
	 */
	
	public static boolean placeBlockIfAllowed(World world, int posX, int posY, int posZ, Block toPlace, String... rules)
	{
		Block b = world.getBlock((int) posX, (int) posY, (int) posZ);
		List<Block> bannedBlocks = new ArrayList<Block>();

		Arrays.stream(rules).forEach(rule ->
		{
			String formula;
			if (rule.split(" ").length > 1)
			{
				formula = rule.split(" ")[0];
				rule = rule.split(" ")[1];
			}
			else
				formula = "add";
			
			if(blockRules.containsKey(rule))
			{
				if (formula.equalsIgnoreCase("add"))
					bannedBlocks.addAll(blockRules.get(rule));
				else if (formula.equalsIgnoreCase("ignore"))
					bannedBlocks.removeAll(blockRules.get(rule));
			}
			
			if (rule.equalsIgnoreCase("all"))
			{
				if (formula.equalsIgnoreCase("add"))
				{
					Block.blockRegistry.forEach(block ->
					{
						bannedBlocks.add((Block) block);
					});
				}
				else if (formula.equalsIgnoreCase("ignore"))
				{
					Block.blockRegistry.forEach(block ->
					{
						bannedBlocks.remove((Block) block);
					});
				}
			}
			else if (rule.equalsIgnoreCase("restricted"))
			{
				bannedBlocks.remove(Blocks.bedrock);
				bannedBlocks.remove(ListMisc.Ope);
				bannedBlocks.remove(ListMisc.OpeMid);
				bannedBlocks.remove(ListMisc.StringMid);
				bannedBlocks.remove(ListMisc.StringWall);
				bannedBlocks.remove(ListMisc.Darkness);
			}
		});

		for (Block blk : bannedBlocks)
		{
			if (b == blk)
			{
				world.setBlock(posX, posY, posZ, toPlace, 0, 2);
				return true;
			}
		}

		return false;
	}

	public static ItemStack getDevilFruitItem(String fullName)
	{
		String model = "";
		String fullModel = "";

		for (String[] s : zoanModels)
		{
			if (fullName.equals(s[0]))
			{
				model = s[1];
				fullModel = "model" + model;
				break;
			}
		}

		if (fullName.equals("yamidummy"))
			fullName = "yamiyami";

		return new ItemStack(GameRegistry.findItem(ID.PROJECT_ID, fullName.replace(model, "") + "nomi" + fullModel));
	}

	public static boolean isEntityInRoom(EntityLivingBase entity)
	{
		for (int i = -20; i < 20; i++)
			for (int j = -20; j < 20; j++)
				for (int k = -20; k < 20; k++)
				{
					if (entity.worldObj.getBlock((int) entity.posX + i, (int) entity.posY + j, (int) entity.posZ + k) == ListMisc.OpeMid)
						return true;
				}

		return false;
	}

	public static int getRegenFromPoision(EntityLivingBase entity)
	{
		return entity.getActivePotionEffect(Potion.poison).getAmplifier() / 5;
	}

}
