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
		enableGriefing = config.get(Configuration.CATEGORY_GENERAL, "Allow Griefing in Worlds", true, "Allows abilities to break or replace blocks, this will make some abilities completly useless; true by default").getBoolean();
		enableAnimeScreaming  = config.get(Configuration.CATEGORY_GENERAL, "Anime Screaming", false, "Will send a chat message to nearby players with the used ability's name; false by default").getBoolean();
		enableSpecialFlying  = config.get(Configuration.CATEGORY_GENERAL, "Allow Special Flying", false, "Allows Gasu Gasu no Mi, Moku Moku no Mi and Suna Suna no Mi users to fly, this option does not affect flying Zoans which will be able to fly regardless; false by default").getBoolean();
		enableOneFruitPerWorld = config.get(Configuration.CATEGORY_GENERAL, "Allow Only One Fruit Per World", false, "Restricts the Devil Fruit spawns to only 1 of each type per world; false by default").getBoolean();
		enableYamiSpecialPower = config.get(Configuration.CATEGORY_GENERAL, "Allow Yami Users to eat another Devil Fruit", true, "Allows Yami Yami no Mi users to eat an additional fruit; true by default").getBoolean();
		enableDFtoDrop = config.get(Configuration.CATEGORY_GENERAL, "Allow Devil Fruits to drop from leaves", false).getBoolean();
		rateDFDrops = config.get(Configuration.CATEGORY_GENERAL, "Rate at which Devil Fruits drop from leaves", 1).getDouble();
		enableLogiaInvulnerability = config.get(Configuration.CATEGORY_GENERAL, "Allow Logia Invulnerability", true, "Allows logia users to avoid physical attacks; true by default").getBoolean();
		enableExtraHearts = config.get(Configuration.CATEGORY_GENERAL, "Receive Extra Hearts", true, "Allows players to receive extra hearts based on their doriki; true by default").getBoolean();
		enableMobRewards = config.get(Configuration.CATEGORY_GENERAL, "Allow Mob Rewards", true, "Allows mobs to reward doriki, bounty or items; true by default").getBoolean();
		modifierDorikiReward = config.get(Configuration.CATEGORY_GENERAL, "Modifier for Doriki Reward", 1, "Multiplier for the doriki reward when killing a mob").getDouble();
		
		enableShips = config.get("structures", "Allow Ships to Spawn", true).getBoolean();
		modifierShipsSpawn = config.get("structures", "Modifier for Spawning Ships", 5).getDouble();
		maxDojoSpawn = config.get("structures", "Max Dojos to Spawn per World", 5).getInt();

		enableQuests = config.get("quests", "Allow Quests", true, "Allows quests to be accepted / completed; true by default").getBoolean();
		enableQuestProgression = config.get("quests", "Allow Quest Progression", false, "Allows quests to reward players with abilities, otherwise all abilities will be unlocked from the beginning; true by default").getBoolean();
		
		rateWantedPostersPackagesSpawn = config.get("bounty", "Rate at which Wanted Poster Packages will drop", 18000, "Represented in minecraft ticks, 20 ticks = 1 second, 18000 ticks = 15 min").getInt();
		enableWantedPostersPackages  = config.get("bounty", "Allow Wanted Poster Packages", true, "Allows wanted poster packages to drop from the sky; true by default").getBoolean();

		enchantmentDialImpactId = config.get("ids", "Enchantment ID : Dial Impact", 200).getInt();
		enchantmentKairosekiId = config.get("ids", "Enchantment ID : Kairoseki", 201).getInt();
		enchantmentDialFlashId = config.get("ids", "Enchantment ID : Dial Flash", 202).getInt();

		enableTelemetry = config.get("system", "Allow Telemetry", true, "Allows the game to send data to our server for statistics, no personal information is sent only minor things like which fruit the player ate, what ability was used, which mobs killed etc; true by default").getBoolean();
		enableUpdateMsg = config.get("system", "Allow Update Message", true, "Allows the game to show a text message when the installed mod is outdated; true by default").getBoolean();	
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
