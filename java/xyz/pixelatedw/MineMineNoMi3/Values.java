package xyz.pixelatedw.MineMineNoMi3;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.EntityDojoSensei;
import xyz.pixelatedw.MineMineNoMi3.items.AkumaNoMi;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.quests.EnumQuestlines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Values 
{
	public static List<AkumaNoMi> devilfruits = new ArrayList();
	public static List<AkumaNoMi> logias = new ArrayList();
	public static List<Item> miscItems = new ArrayList();
	public static List<Block> miscBlocks = new ArrayList();
	public static List<Object[]> customDFs = new ArrayList();
	
	public static final int MAX_DORIKI = 10000;
	public static final int MAX_ULTRACOLA = 10;
	public static final int MAX_GENERAL = 999999999;
	public static final long MAX_BOUNTY = 100000000000L;
	public static final int MAX_CREW = 50;
	public static final int MAX_ACTIVITIES = 4;
	
	public static String RESOURCES_FOLDER;
	
	public static Item[] KAIROSEKI_ITEMS = new Item[] {ListMisc.Kairoseki, ListMisc.KairosekiBullets, ListMisc.DenseKairoseki};
	
	public static HashMap<Class, EnumQuestlines> questGivers = createQuestGiversMap();

	private static HashMap<Class, EnumQuestlines> createQuestGiversMap()
	{
		HashMap<Class, EnumQuestlines> map = new HashMap<Class, EnumQuestlines>();
		
		map.put(EntityDojoSensei.class, EnumQuestlines.SWORDSMANPROGRESSION);
		
		return map;
	}
	
	public static HashMap<String, String[]> abilityWebAppExtraParams = new HashMap<String, String[]>();

}
