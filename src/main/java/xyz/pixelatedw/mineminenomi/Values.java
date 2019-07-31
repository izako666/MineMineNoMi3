package xyz.pixelatedw.mineminenomi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.Item;
import xyz.pixelatedw.mineminenomi.init.ModMiscItems;
import xyz.pixelatedw.mineminenomi.items.ItemAkumaNoMi;

public class Values
{
	public static List<ItemAkumaNoMi> devilfruits = new ArrayList();
	public static List<ItemAkumaNoMi> logias = new ArrayList();
	public static List<Object[]> customDFs = new ArrayList();

	public static final int MAX_DORIKI = 10000;
	public static final int MAX_ULTRACOLA = 10;
	public static final int MAX_GENERAL = 999999999;
	public static final long MAX_BOUNTY = 100000000000L;
	public static final int MAX_CREW = 50;
	public static final int MAX_ACTIVITIES = 4;

	public static Item[] KAIROSEKI_ITEMS = new Item[]
		{
				ModMiscItems.kairoseki, ModMiscItems.kairosekiBullets, ModMiscItems.denseKairoseki
		};

	/*
	 * public static HashMap<Class, EnumQuestlines> questGivers = createQuestGiversMap();
	 * private static HashMap<Class, EnumQuestlines> createQuestGiversMap()
	 * {
	 * HashMap<Class, EnumQuestlines> map = new HashMap<Class, EnumQuestlines>();
	 * map.put(EntityDojoSensei.class, EnumQuestlines.SWORDSMANPROGRESSION);
	 * return map;
	 * }
	 */

	public static HashMap<String, String[]> abilityWebAppExtraParams = new HashMap<String, String[]>();
}
