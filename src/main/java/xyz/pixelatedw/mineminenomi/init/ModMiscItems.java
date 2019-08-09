package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.items.ItemBellyPouch;
import xyz.pixelatedw.mineminenomi.items.ItemCharacterCreator;
import xyz.pixelatedw.mineminenomi.items.ItemCola;
import xyz.pixelatedw.mineminenomi.items.ItemHeart;
import xyz.pixelatedw.mineminenomi.items.ItemSeaKingMeat;
import xyz.pixelatedw.mineminenomi.items.ItemUltraCola;

@Mod.EventBusSubscriber(modid = ID.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModMiscItems
{

	// Random stuff
	public static Item characterCreator = new ItemCharacterCreator();
	public static Item kairoseki = new Item(new Properties().group(ModCreativeTabs.MISC));
	public static Item denseKairoseki = new Item(new Properties().group(ModCreativeTabs.MISC));
	public static Item blackMetal = new Item(new Properties().group(ModCreativeTabs.MISC));
	public static Item shadow = new Item(new Properties().group(ModCreativeTabs.MISC));
	public static Item heart = new ItemHeart();
	public static Item bellyPouch = new ItemBellyPouch();
	public static Item key = new Item(new Properties().group(ModCreativeTabs.MISC));
	//public static Item Box1;
	//public static Item Box2;
	//public static Item Box3;
	public static Item wantedPoster = new Item(new Properties().group(ModCreativeTabs.MISC));//(WantedPoster) new WantedPoster().setMaxStackSize(1);
	
	// Food
	public static Item seaKingMeat = new ItemSeaKingMeat();
	public static Item cola = new ItemCola();
	public static Item ultraCola = new ItemUltraCola();
	
	// Dials
	/*public static Item DialEisen = new DialEisen();
	public static Item DialFire = new DialFire();
	public static Item DialAxe = new DialAxe();
	public static Item DialImpact = new DialImpact();
	public static Item DialMilky = new DialMilky();
	public static Item DialFlash = new DialFlash();
	public static Item DialReject = new DialReject();
	public static Item DialBreath = new DialBreath();*/
	
	// Ammo
	public static Item bullets = new Item(new Properties().group(ModCreativeTabs.MISC));
	public static Item kairosekiBullets = new Item(new Properties().group(ModCreativeTabs.MISC));
	public static Item kujaArrow = new Item(new Properties().group(ModCreativeTabs.MISC));
	public static Item popGreen = new Item(new Properties().group(ModCreativeTabs.MISC));

	@SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
		if (!event.getName().equals(ForgeRegistries.ITEMS.getRegistryName())) return;
		
        event.getRegistry().registerAll
        (
        	WyRegistry.registerItem(kairoseki, "Kairoseki"),
        	WyRegistry.registerItem(denseKairoseki, "Dense Kairoseki"),
        	WyRegistry.registerItem(blackMetal, "Black Metal"),
        	WyRegistry.registerItem(shadow, "Shadow"),
        	WyRegistry.registerItem(key, "Key"),
        	WyRegistry.registerItem(characterCreator, "Character Creator"),
        	WyRegistry.registerItem(bellyPouch, "Belly Pouch"),
        	WyRegistry.registerItem(seaKingMeat, "Sea King Meat"),
        	WyRegistry.registerItem(cola, "Cola"),
        	WyRegistry.registerItem(ultraCola, "Ultra Cola"),
        	WyRegistry.registerItem(heart, "Heart"),
        	WyRegistry.registerItem(wantedPoster, "Wanted Poster"),     	
        	WyRegistry.registerItem(bullets, "Bullet"),
        	WyRegistry.registerItem(kairosekiBullets, "Kairoseki Bullet"),
        	WyRegistry.registerItem(kujaArrow, "Kuja Arrow"),
        	WyRegistry.registerItem(popGreen, "Pop Green")
        );
    }
}
