package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.json.block.JSONModelBars;
import xyz.pixelatedw.mineminenomi.api.json.block.JSONModelThinBlock;
import xyz.pixelatedw.mineminenomi.api.json.item.JSONModelSimpleItem;
import xyz.pixelatedw.mineminenomi.blocks.BlockBarrier;
import xyz.pixelatedw.mineminenomi.blocks.BlockCustomBars;
import xyz.pixelatedw.mineminenomi.blocks.BlockDarkness;
import xyz.pixelatedw.mineminenomi.blocks.BlockDemonPoison;
import xyz.pixelatedw.mineminenomi.blocks.BlockKage;
import xyz.pixelatedw.mineminenomi.blocks.BlockKairosekiOre;
import xyz.pixelatedw.mineminenomi.blocks.BlockOpe;
import xyz.pixelatedw.mineminenomi.blocks.BlockOpeMid;
import xyz.pixelatedw.mineminenomi.blocks.BlockPoison;
import xyz.pixelatedw.mineminenomi.blocks.BlockSkyBlock;
import xyz.pixelatedw.mineminenomi.blocks.BlockStringMid;
import xyz.pixelatedw.mineminenomi.blocks.BlockStringWall;
import xyz.pixelatedw.mineminenomi.blocks.BlockSunaSand;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.TileEntityRoom;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.TileEntityTorikago;

@Mod.EventBusSubscriber(modid = ID.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModMiscBlocks
{

	// Devil Fruit created blocks	
	public static Block ope = new BlockOpe();
	public static Block opeMid = new BlockOpeMid();
	public static Block kageBlock = new BlockKage();
	public static Block sunaSand = new BlockSunaSand();
	public static Block waxBlock = new Block(Properties.create(Material.CLAY).hardnessAndResistance(2));
	public static Block poison = new BlockPoison();
	public static Block demonPoison = new BlockDemonPoison();
	public static Block stringWall = new BlockStringWall();
	public static Block stringMid = new BlockStringMid();
	public static Block barrier = new BlockBarrier();
	public static Block darkness = new BlockDarkness();
	public static Block oriBars = new BlockCustomBars();

	// Other blocks
	public static Block kairosekiBlock = new Block(Properties.create(Material.ROCK).hardnessAndResistance(10));
	public static Block kairosekiOre = new BlockKairosekiOre();
	public static Block kairosekiBars = new BlockCustomBars();	
	public static Block skyBlock = new BlockSkyBlock();

	//public static Block DenDenMushi = new BlockDenDenMushi();
	
	/*
	public static BlockCustomSpawner CustomSpawner = new BlockCustomSpawner();

	
	{
		public int quantityDropped(Random random)
		{
			return 0;
		}

	};
	public static Block WantedPostersPackage = new BlockWantedPostersPackage();
	public static Block WantedPosterBlock = new BlockWantedPoster();

	public static Block AbilityProtectionBlock = new BlockAbilityProtection();
	public static Block AbilityProtectionAreaBlock = new BlockAbilityProtectionArea();
	public static Block AbilityProtectionCenterBlock = new BlockAbilityProtectionArea();

	public static Block DialEisenBlock = new BlockEisenDial();
	public static Block DialFireBlock = new BlockFlameDial();
	public static Block DialAxeBlock = new BlockAxeDial();
	public static Block DialImpactBlock = new BlockImpactDial();
	public static Block DialMilkyBlock = new BlockMilkyDial();
	public static Block DialFlashBlock = new BlockFlashDial();
	public static Block DialRejectBlock = new BlockRejectDial();
	public static Block DialBreathBlock = new BlockBreathDial();*/
	
	@SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll
        (
        	WyRegistry.registerBlock(ope, "Ope"),
        	WyRegistry.registerBlock(opeMid, "Ope Mid"),
        	WyRegistry.registerBlock(kairosekiBlock, "Kairoseki Block"),
        	WyRegistry.registerBlock(kairosekiOre, "Kairoseki Ore"),
        	WyRegistry.registerBlock(skyBlock, "Sky Block"),
        	WyRegistry.registerBlock(kairosekiBars, "Kairoseki Bars", new JSONModelBars("Kairoseki Bars")),
        	WyRegistry.registerBlock(kageBlock, "Kage Block"),
        	WyRegistry.registerBlock(sunaSand, "Suna Sand"),
        	WyRegistry.registerBlock(waxBlock, "Wax Block"),
        	WyRegistry.registerBlock(poison, "Poison", new JSONModelThinBlock("Poison")),
        	WyRegistry.registerBlock(demonPoison, "Demon Poison", new JSONModelThinBlock("Demon Poison")),
        	WyRegistry.registerBlock(stringWall, "String Wall"),
        	WyRegistry.registerBlock(stringMid, "String Mid"),
        	WyRegistry.registerBlock(barrier, "Barrier"),
        	WyRegistry.registerBlock(darkness, "Darkness"),
        	WyRegistry.registerBlock(oriBars, "Ori Bars", new JSONModelBars("Ori Bars"))
        );
    }
	
	@SubscribeEvent
	public static void registerTE(RegistryEvent.Register<TileEntityType<?>> event) 
	{
		if (!event.getName().equals(ForgeRegistries.TILE_ENTITIES.getRegistryName()))
			return;
		
		event.getRegistry().registerAll
		(
			TileEntityRoom.ROOM,
			TileEntityTorikago.TORIKAGO
		);
	}
	
	@SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll
        (
        	WyRegistry.registerItemBlock(ope, false),
        	WyRegistry.registerItemBlock(opeMid, false),
        	WyRegistry.registerItemBlock(kairosekiBlock, true),
        	WyRegistry.registerItemBlock(kairosekiOre, true),
        	WyRegistry.registerItemBlock(skyBlock, true),
        	WyRegistry.registerItemBlock(kairosekiBars, true, new JSONModelSimpleItem("Kairoseki Bars")),
        	WyRegistry.registerItemBlock(kageBlock, false),
        	WyRegistry.registerItemBlock(sunaSand, false),
        	WyRegistry.registerItemBlock(waxBlock, false),
        	WyRegistry.registerItemBlock(poison, false),
        	WyRegistry.registerItemBlock(demonPoison , false),
        	WyRegistry.registerItemBlock(stringWall, false),
        	WyRegistry.registerItemBlock(stringMid, false),
        	WyRegistry.registerItemBlock(barrier, false),
        	WyRegistry.registerItemBlock(darkness, false),
        	WyRegistry.registerItemBlock(oriBars, false, new JSONModelSimpleItem("Ori Bars"))
        );
    }
}
