package xyz.pixelatedw.mineminenomi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

import net.minecraft.item.Item;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.init.ModMiscBlocks;
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
				ModMiscItems.kairoseki, ModMiscItems.kairosekiBullets, ModMiscItems.denseKairoseki, ModMiscBlocks.kairosekiBlock.asItem(), ModMiscBlocks.kairosekiBars.asItem(), ModMiscBlocks.kairosekiOre.asItem()
		};

	// Network related stuff
	public static String urlConnection;
	public static HttpClient httpClient = HttpClientBuilder.create().build();
	public static Gson gson = new Gson();
	static
	{
		if (WyDebug.isDebug())
			urlConnection = "http://localhost/mmnm-webserver/api";
		else
			urlConnection = "http://pixelatedw.xyz/api";
	}

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
