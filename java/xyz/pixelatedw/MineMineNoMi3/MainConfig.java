package xyz.pixelatedw.MineMineNoMi3;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class MainConfig 
{
	
	private static Configuration config;
	
	public static String enableKeepIEEPAfterDeath;
	public static String[] statsToKepp;
	public static boolean enableShips;
	public static boolean enableCamps;
	public static boolean enableDFtoDrop;
	public static boolean enableLogiaInvulnerability;	
	public static boolean enableExtraHearts;
	public static boolean enableMobRewards;
	public static boolean enableQuestProgression;
	public static boolean enableQuests;
	public static boolean enableGriefing;
	public static boolean enableWantedPostersPackages;
	public static boolean enableAnimeScreaming;
	public static boolean enableSpecialFlying;
	public static boolean enableOneFruitPerWorld;
	public static boolean enableYamiSpecialPower;
	public static double rateDFDrops;
	public static double modifierShipsSpawn;
	public static double rateWantedPostersPackagesSpawn;
	public static double modifierDorikiReward;
	public static int maxDojoSpawn;
	
	public static boolean enableTelemetry;
	public static boolean enableUpdateMsg;
	public static boolean enableFOVModifier;	
	
	public static int enchantmentDialImpactId;
	public static int enchantmentKairosekiId;
	public static int enchantmentDialFlashId;
	
	public static int commandPermissionRemoveDF = 2;
	public static int commandPermissionDoriki = 2;
	public static int commandPermissionBelly = 2;
	public static int commandPermissionBounty = 2;
	public static int commandPermissionExtol = 2;
	public static int commandPermissionIssueBounty = 2;
	public static int commandPermissionGetWantedPoster = 2;
	public static String[] abilityRestrictions;
	
	
	public static void init(File configFile)
	{
		config = new Configuration(configFile);
		
		config.load();
		
		enableKeepIEEPAfterDeath = config.get(Configuration.CATEGORY_GENERAL, "Keep stats after death", "auto").getString();
		statsToKepp = config.get(Configuration.CATEGORY_GENERAL, "Data to Keep", new String[] {"Doriki", "Bounty", "Belly", "Race", "Faction", "Fighting Style", "Devil Fruit"}).getStringList();		
		//enableCamps = config.get(Configuration.CATEGORY_GENERAL, "Allow Camps to Spawn", true).getBoolean();
		enableGriefing = config.get(Configuration.CATEGORY_GENERAL, "Allow Griefing in Worlds", true).getBoolean();
		enableAnimeScreaming  = config.get(Configuration.CATEGORY_GENERAL, "Anime Screaming", false).getBoolean();
		enableSpecialFlying  = config.get(Configuration.CATEGORY_GENERAL, "Allow Special Flying", false).getBoolean();
		enableOneFruitPerWorld = config.get(Configuration.CATEGORY_GENERAL, "Allow Only One Fruit Per World", false).getBoolean();
		enableYamiSpecialPower = config.get(Configuration.CATEGORY_GENERAL, "Allow Yami Users to eat another Devil Fruit", true).getBoolean();
		enableDFtoDrop = config.get(Configuration.CATEGORY_GENERAL, "Allow Devil Fruits to drop from leaves", false).getBoolean();
		rateDFDrops = config.get(Configuration.CATEGORY_GENERAL, "Rate at which Devil Fruits drop from leaves", 1).getDouble();
		enableLogiaInvulnerability = config.get(Configuration.CATEGORY_GENERAL, "Allow Logia Invulnerability", true).getBoolean();
		enableExtraHearts = config.get(Configuration.CATEGORY_GENERAL, "Receive Extra Hearts", true).getBoolean();
		enableMobRewards = config.get(Configuration.CATEGORY_GENERAL, "Allow Mob Rewards", true).getBoolean();
		modifierDorikiReward = config.get(Configuration.CATEGORY_GENERAL, "Modifier for Doriki Reward", 1, "Multiplier for the doriki reward when killing a mob").getDouble();
		
		enableShips = config.get("structures", "Allow Ships to Spawn", true).getBoolean();
		modifierShipsSpawn = config.get("structures", "Modifier for Spawning Ships", 5).getDouble();
		maxDojoSpawn = config.get("structures", "Max Dojos to Spawn per World", 5).getInt();

		enableQuests = config.get("quests", "Allow Quests", true).getBoolean();
		enableQuestProgression = config.get("quests", "Allow Quest Progression", false).getBoolean();
		
		rateWantedPostersPackagesSpawn = config.get("bounty", "Rate at which Wanted Poster Packages will drop", 18000, "Represented in minecraft ticks, 20 ticks = 1 second, 18000 ticks = 15 min").getInt();
		enableWantedPostersPackages  = config.get("bounty", "Allow Wanted Poster Packages", true).getBoolean();

		enchantmentDialImpactId = config.get("ids", "Enchantment ID : Dial Impact", 200).getInt();
		enchantmentKairosekiId = config.get("ids", "Enchantment ID : Kairoseki", 201).getInt();
		enchantmentDialFlashId = config.get("ids", "Enchantment ID : Dial Flash", 202).getInt();

		enableTelemetry = config.get("system", "Allow Telemetry", true).getBoolean();
		enableUpdateMsg = config.get("system", "Allow Update Message", true).getBoolean();	
		enableFOVModifier = config.get("system", "Allow FOV Modifiers", false).getBoolean();
		
		commandPermissionRemoveDF = config.get("permissions", "Permission : /removedf", 2).getInt();
		commandPermissionDoriki = config.get("permissions", "Permission : /doriki", 2).getInt();
		commandPermissionBelly = config.get("permissions", "Permission : /belly", 2).getInt();
		commandPermissionBounty = config.get("permissions", "Permission : /bounty", 2).getInt();
		commandPermissionExtol = config.get("permissions", "Permission : /extol", 2).getInt();
		commandPermissionIssueBounty = config.get("permissions", "Permission : /issueBounty", 2).getInt();
		commandPermissionGetWantedPoster = config.get("permissions", "Permission : /getwantedposter", 2).getInt();
		abilityRestrictions = config.get("permissions", "Ability Restrictions", new String[] {"example1", "example2"}).getStringList();		
		
		config.save();
	}
	 
	public static Configuration getConfig() { return config; }

}
