package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.json.models.item.JSONModelRod;
import xyz.pixelatedw.mineminenomi.api.json.models.item.JSONModelSword;
import xyz.pixelatedw.mineminenomi.items.weapons.ItemAbilityWeapon;
import xyz.pixelatedw.mineminenomi.items.weapons.ItemCoreWeapon;

@Mod.EventBusSubscriber(modid = ID.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModWeapons
{
	// Normal Melee Weapons
	public static ItemCoreWeapon marineSword = new ItemCoreWeapon(5, 300);
	public static ItemCoreWeapon pirateCutlass = new ItemCoreWeapon(5, 300);
	public static ItemCoreWeapon pipe = new ItemCoreWeapon(4, 200);
	public static ItemCoreWeapon scissors = new ItemCoreWeapon(6, 500);
	public static ItemCoreWeapon kikoku = new ItemCoreWeapon(8, 500);
	public static ItemCoreWeapon kiribachi = new ItemCoreWeapon(6, 500);
	public static ItemCoreWeapon yoru = new ItemCoreWeapon(10, 500);
	public static ItemCoreWeapon bisento = new ItemCoreWeapon(8, 500);
	public static ItemCoreWeapon hook = new ItemCoreWeapon(6, 500).setIsPoisonous();
	public static ItemCoreWeapon umbrella = new ItemCoreWeapon(3, 500);
	public static ItemCoreWeapon jitte = new ItemCoreWeapon(7, 500);
	public static ItemCoreWeapon boStick = new ItemCoreWeapon(6, 500);
	public static ItemCoreWeapon hammer5t = new ItemCoreWeapon(1, 500);
	public static ItemCoreWeapon hammer10t = new ItemCoreWeapon(1, 500);
	public static ItemCoreWeapon hammer100t = new ItemCoreWeapon(1, 500);
	public static ItemCoreWeapon tonfa = new ItemCoreWeapon(4, 500);
	public static ItemCoreWeapon knife1 = new ItemCoreWeapon(3, 500);
	public static ItemCoreWeapon knife2 = new ItemCoreWeapon(3, 500);
	public static ItemCoreWeapon knife3 = new ItemCoreWeapon(3, 250);
	public static ItemCoreWeapon wadoIchimonji = new ItemCoreWeapon(8, 500);
	public static ItemCoreWeapon sandaiKitetsu = new ItemCoreWeapon(8, 500);
	public static ItemCoreWeapon nidaiKitetsu = new ItemCoreWeapon(10, 500);
	public static ItemCoreWeapon shusui = new ItemCoreWeapon(8, 500);
	public static ItemCoreWeapon soulSolid = new ItemCoreWeapon(8, 500);
	public static ItemCoreWeapon durandal = new ItemCoreWeapon(7, 500);
	
	// Normal Ranged Weapons
	
	// Devil Fruit Weapons
	public static ItemAbilityWeapon iceSaber = new ItemAbilityWeapon(9).setIsSlownessInducing();
	public static ItemAbilityWeapon amaNoMurakumo = new ItemAbilityWeapon(9);
	public static ItemAbilityWeapon noroNoroBeamSword = new ItemAbilityWeapon(5).setIsSlownessInducing(75, true);
	public static ItemAbilityWeapon doruDoruArtsKen = new ItemAbilityWeapon(6);
	public static ItemAbilityWeapon blueSword = new ItemAbilityWeapon(8).setIsFireAspect();
	public static ItemAbilityWeapon tabiraYuki = new ItemAbilityWeapon(8).setIsSlownessInducing(50);
	
	@SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll
        (
        	registerSword(marineSword, "Marine Sword"),
        	registerSword(pirateCutlass, "Pirate Cutlass"),
        	registerSword(pipe, "Pipe"),
        	registerSword(scissors, "Scissors"),
        	registerSword(kikoku, "Kikoku"),
        	registerSword(kiribachi, "Kiribachi"),
        	registerSword(yoru, "Yoru"),
        	registerRod(bisento, "Bisento"),     	
        	registerSword(hook, "Hook"),
        	registerSword(umbrella, "Umbrella"),
        	registerSword(jitte, "Jitte"),
        	registerRod(boStick, "Bo Staff"),
        	registerSword(hammer5t, "5t Hammer"),
        	registerSword(hammer10t, "10t Hammer"),
        	registerSword(hammer100t, "100t Hammer"),
        	registerSword(tonfa, "Tonfa"),      	
        	registerSword(knife1, "Knife"),
        	//registerSword(knife2, "Knife"),
        	//registerSword(knife3, "Knife"),
        	registerSword(wadoIchimonji, "Wado Ichimonji"),
        	registerSword(sandaiKitetsu, "Sandai Kitetsu"),
        	registerSword(nidaiKitetsu, "Nidai Kitetsu"),
        	registerSword(shusui, "Shusui"),
        	registerSword(soulSolid, "Soul Solid"),
        	registerSword(durandal, "Durandal"),
        	
        	registerSword(iceSaber, "Ice Saber"),
        	registerSword(amaNoMurakumo, "Ama no Murakumo"),
        	registerSword(noroNoroBeamSword, "Noro Noro Beam Sword"),
        	registerSword(doruDoruArtsKen, "Doru Doru Arts: Ken"),
        	registerSword(blueSword, "Blue Sword"),
        	registerSword(tabiraYuki, "Tabira Yuki")
        );
    }
	
	private static Item registerSword(Item item, String localizedName)
	{
		return WyRegistry.registerItem(item, localizedName, new JSONModelSword(localizedName));
	}
	
	private static Item registerRod(Item item, String localizedName)
	{
		return WyRegistry.registerItem(item, localizedName, new JSONModelRod(localizedName));
	}
}
