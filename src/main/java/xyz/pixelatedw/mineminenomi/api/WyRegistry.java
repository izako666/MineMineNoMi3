package xyz.pixelatedw.mineminenomi.api;

import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages.SpawnEntity;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.json.JSONModelBlock;
import xyz.pixelatedw.mineminenomi.api.json.JSONModelItem;
import xyz.pixelatedw.mineminenomi.api.json.block.JSONModelSimpleBlock;
import xyz.pixelatedw.mineminenomi.api.json.item.JSONModelItemBlock;
import xyz.pixelatedw.mineminenomi.api.json.item.JSONModelSimpleItem;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;

public class WyRegistry
{

	private static int entityID = 200;
	private static int packetID = 0;

	public static HashMap<Item, JSONModelItem> items = new HashMap<Item, JSONModelItem>();
	public static HashMap<Block, JSONModelBlock> blocks = new HashMap<Block, JSONModelBlock>();
	public static HashMap<String, String> langMap = new HashMap<String, String>();

	public static void registerName(String key, String localizedName)
	{
		langMap.put(key, localizedName);
	}
	
	public static BlockItem registerItemBlock(Block block, boolean isInCreative)
	{
		return registerItemBlock(block, isInCreative, new JSONModelItemBlock(block.getRegistryName().getPath()) );
	}
	
	public static BlockItem registerItemBlock(Block block, boolean isInCreative, JSONModelItem jsonType)
	{
		BlockItem itemBlock;
		if(isInCreative)
			itemBlock = new BlockItem(block, new Properties().group(ModCreativeTabs.MISC));
		else
			itemBlock = new BlockItem(block, new Properties());
		itemBlock.setRegistryName(block.getRegistryName());
		
		items.put(itemBlock, jsonType);
		
		return itemBlock;
	}
	
	public static Block registerBlock(Block block, String localizedName)
	{
		return registerBlock(block, localizedName, new JSONModelSimpleBlock(localizedName));
	}
	
	public static Block registerBlock(Block block, String localizedName, JSONModelBlock jsonType)
	{
		String truename = WyHelper.getFancyName(localizedName);
		langMap.put("block." + ID.PROJECT_ID + "." + truename, localizedName);
		block.setRegistryName(ID.PROJECT_ID, truename);
		
		blocks.put(block, jsonType);
		
		return block;
	}
	
	public static TileEntityType<?> registerTileEntity(String id, Supplier factory, Block... blocks)
	{
		String name = WyHelper.getFancyName(id);

		TileEntityType<?> type = TileEntityType.Builder.create(factory, blocks).build(null);
		type.setRegistryName(ID.PROJECT_ID, name);
		
		return type;
	}
	
	/*public static void registerBlock(Block block, String localizedName, float hard, CreativeTabs tab, Class<? extends TileEntity> tile)
	{	
		String truename = WyHelper.getFancyName(localizedName);
		block.setBlockName(truename).setBlockTextureName(ID.PROJECT_ID + ":" + truename).setHardness(hard).setResistance(hard);
		GameRegistry.registerBlock(block, truename);
		if(tab != null)
			block.setCreativeTab(tab);
		if(tile != null)
			GameRegistry.registerTileEntity(tile, truename);		
		getItemsMap().put(block, localizedName);
		registerName("tile." + truename + ".name", localizedName);
	}*/
	
	public static Item registerItem(Item item, String localizedName)
	{
		return registerItem(item, localizedName, new JSONModelSimpleItem(localizedName));
	}
	
	public static Item registerItem(Item item, String localizedName, JSONModelItem jsonType)
	{
		String truename = WyHelper.getFancyName(localizedName);
		langMap.put("item." + ID.PROJECT_ID + "." + truename, localizedName);
		item.setRegistryName(ID.PROJECT_ID, truename);
		
		items.put(item, jsonType);

		return item;
	}
	
	public static <T extends Entity> EntityType<T> registerEntityType(String id, EntityType.IFactory<T> factory, BiFunction<SpawnEntity, World, T> clientFactory)
	{
		String name = WyHelper.getFancyName(id);
		
		EntityType type = EntityType.Builder.create(factory, EntityClassification.MISC)
			.setTrackingRange(128)
			.setShouldReceiveVelocityUpdates(true)
			.setUpdateInterval(3)
			.setCustomClientFactory(clientFactory)
			.size(1, 1)
			.build(name)
			.setRegistryName(ID.PROJECT_ID, name);

		return type;
	}

	/*
	public static void registerSpawnBiomesFor(Class<? extends EntityLiving> entity, int rarity, int min, int max, Type... biomeTypes)
	{
		BiomeGenBase[] biomes = new BiomeGenBase[0];
		for(Type t : biomeTypes)
			biomes = ArrayUtils.addAll(biomes, BiomeDictionary.getBiomesForType(t));
		EntityRegistry.addSpawn(entity, rarity, min, max, EnumCreatureType.creature, biomes);		
	}
	
	public static void registerEnchantment(Enchantment enc, String name)
	{
		String truename = WyHelper.getFancyName(name);
		//GameRegistry.register(enc.setName(truename));
		enc.setName(truename);
		registerName("enchantment." + truename, name);
	}*/
	
	/*public void registerDimension(String name, int id, Class<? extends WorldProvider> clazz)
	{
		DimensionType.register(name, "_" + WyHelper.instance().getFancyName(name), id, clazz, true);
		DimensionManager.registerDimension(id, DimensionType.getById(id));	
		DimensionManager.createProviderFor(id);
	}*/

}
