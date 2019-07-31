package xyz.pixelatedw.mineminenomi.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.EnumValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import xyz.pixelatedw.mineminenomi.api.WyHelper;

public class CommonConfig
{
	
	public static CommonConfig instance;
	
	// General
	private EnumValue keepStatsAfterDeath;
	private Map<String, ForgeConfigSpec.BooleanValue> statsToKeep;
	private List<String> bannedAbilities;

	private BooleanValue logiaInvulnerability;
	private BooleanValue devilFruitDropsFromLeaves;
	private BooleanValue extraHearts;
	private BooleanValue mobRewards;
	private BooleanValue griefing;
	private BooleanValue animeScreaming;
	private BooleanValue specialFlying;
	private BooleanValue oneFruitPerWorld;
	private BooleanValue yamiPower;
	private DoubleValue dorikiRewardMultiplier;
		
	//private DoubleValue devilFruitDropsFromLeaves
		
	// Structures
	private BooleanValue spawnShips;
	private BooleanValue spawnDojos;
	private IntValue maxDojoSpawns;
	private DoubleValue multiplierShipsSpawn;
	
	// Quests
	private BooleanValue quests;
	private BooleanValue questProgression;
	
	// Bounty
	private BooleanValue wantedPosterPackages;
	private IntValue timeBetweenPackageDrops;
	
	// Permissions
	
	// System
	private BooleanValue telemetry;
	private BooleanValue fovRemover;
	private BooleanValue updateMessage;
	
	private enum KeepStatsLogc
	{
		NONE, AUTO, FULL
	}
	
	public static void init()
	{
		Pair<CommonConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        ForgeConfigSpec configSpec = pair.getRight();
        CommonConfig.instance = pair.getLeft();
    	ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, configSpec);
	}
	
	public CommonConfig(ForgeConfigSpec.Builder builder)
	{
		builder.push("General");
		{
			this.logiaInvulnerability = builder.comment("Allows logia users to avoid physical attacks; true by default").define("Logia Invulnerability", true);
			this.devilFruitDropsFromLeaves = builder.comment("Allows Devil Fruits to drop from leaves; false by default").define("Devil Fruit drops from leaves", false);
			this.extraHearts = builder.comment("Allows players to receive extra hearts based on their doriki; true by default").define("Extra Hearts", true);
			this.mobRewards = builder.comment("Allows mobs to reward doriki, bounty or items; true by default").define("Mob Rewards", true);
			this.griefing = builder.comment("Allows abilities to break or replace blocks, this will make some abilities completly useless; true by default").define("Griefing", true);
			this.animeScreaming = builder.comment("Will send a chat message to nearby players with the used ability's name; false by default").define("Anime Scream", false);
			this.specialFlying = builder.comment("Allows Gasu Gasu no Mi, Moku Moku no Mi and Suna Suna no Mi users to fly, this option does not affect flying Zoans which will be able to fly regardless; false by default").define("Special Flying", false);
			this.oneFruitPerWorld = builder.comment("Restricts the Devil Fruit spawns to only 1 of each type per world; false by default").define("One Devil Fruit per World", false);
			this.yamiPower = builder.comment("Allows Yami Yami no Mi users to eat an additional fruit; true by default").define("Yami Yami no Mi additional fruit", true);
			this.dorikiRewardMultiplier = builder.comment("Multiplies any doriki gained by this amount; 1 by default, min: 0, max: 10").defineInRange("Doriki Reward Multiplier", 1.0, 0.0, 10.0);

			this.bannedAbilities = new ArrayList<String>();
			Predicate<Object> bannedAbilitiesTest = new Predicate<Object>() 
			{
				@Override
				public boolean test(Object t)
				{
					if(!(t instanceof String))
						return false;
					
					String str = (String)t;
					return !WyHelper.isNullOrEmpty(str);
				}
			};
			this.bannedAbilities.add("Example1");
			this.bannedAbilities.add("Example2");
			builder.comment("List with ability names that are banned, the names can be written in any case with or without spaces").defineList("Banned Abilities", this.bannedAbilities, bannedAbilitiesTest);
			
			this.keepStatsAfterDeath = builder.comment("Defines which logic to apply after a player's death \n NONE - nothing is kept \n AUTO (default) - only the faction/race/fighting style stats are kept \n FULL - everything is kept").defineEnum("Keep Stats after Death", KeepStatsLogc.AUTO, KeepStatsLogc.values());
			builder.push("Stats to Keep");
			{
				String[] statsToKeepNames = new String[] {"Doriki", "Bounty", "Belly", "Race", "Faction", "Fighting Style", "Devil Fruit"};
				this.statsToKeep = new HashMap<String, ForgeConfigSpec.BooleanValue>();
				
				for(String stat : statsToKeepNames) 
					this.statsToKeep.put(stat, builder.define(stat, true));
			}
			builder.pop();

		}
		builder.pop();
		
		builder.push("Structures");
		
		this.spawnShips = builder.comment("Allows ships to spawn in the world; true by default").define("Spawn Ships", true);
		this.multiplierShipsSpawn = builder.comment("Multiplier for Ships spawn rate").defineInRange("Ships Spawn multiplier", 5.0, 1.0, 100.0);
		this.spawnDojos = builder.comment("Allows dojos to spawn in the world; true by default").define("Spawn Dojos", true);
		this.maxDojoSpawns = builder.comment("Sets the maximum number of dojos that can spawn in a world; 5 by default, min is 0 and max is 9999").defineInRange("Max Dojos per World", 5, 0, 9999);	
		
		builder.pop();
		
		builder.push("Quests");
		
		this.quests = builder.comment("Allows quests to be accepted / completed; true by default").define("Quests", true);
		this.questProgression = builder.comment("Allows quests to reward players with abilities, otherwise all abilities will be unlocked from the beginning; true by default").define("Quest Progression", false);
		
		builder.pop();
		
		builder.push("Bounty");
		
		this.wantedPosterPackages = builder.comment("Allows wanted poster packages to drop from the sky; true by default").define("Wanted Poster Package Drops", true);
		this.timeBetweenPackageDrops = builder.comment("Time it takes for another package to drop; 18000 (15 minutes) by default").defineInRange("Time Between Package Drops", 18000, 0, Integer.MAX_VALUE);
		
		builder.pop();
		
		builder.push("Permissions");
		builder.pop();
		
		builder.push("System");	
		
		this.telemetry = builder.comment("Allows the game to send data to our server for statistics, no personal information is sent only minor things like which fruit the player ate, what ability was used, which mobs killed etc; true by default").define("Telemtry", true);
		this.updateMessage = builder.comment("Allows the game to show a text message when the installed mod is outdated; true by default").define("Update Message", true);	
		this.fovRemover = builder.comment("Keeps the FOV fixed when the player has speed effects active").define("FOV Remover", true);
		
		builder.pop();
	}
	
	public double getDorikiRewardMultiplier()
	{
		return this.dorikiRewardMultiplier.get();
	}
	
	public boolean getUpdateMessage()
	{
		return this.updateMessage.get();
	}
	
	public int getMaxDojoSpawns()
	{
		return this.maxDojoSpawns.get();
	}
	
	public boolean getQuestProgression()
	{
		return this.questProgression.get();
	}
	
	public boolean getFOVRemover()
	{
		return this.fovRemover.get();
	}
	
	public boolean getQuests()
	{
		return this.quests.get();
	}
	
	public String[] getBannedAbilities()
	{
		return Arrays.copyOf(this.bannedAbilities.toArray(), this.bannedAbilities.toArray().length, String[].class);
	}
	
	public String getAfterDeathLogic()
	{
		return this.keepStatsAfterDeath.get().toString();
	}
	
	public boolean getLogiaInvulnerability()
	{
		return this.logiaInvulnerability.get().booleanValue();
	}
	
	public boolean getDevilFruitDropsFromLeaves()
	{
		return this.devilFruitDropsFromLeaves.get().booleanValue();
	}
	
	public boolean getExtraHearts()
	{
		return this.extraHearts.get().booleanValue();
	}
	
	public boolean getMobRewards()
	{
		return this.mobRewards.get().booleanValue();
	}
	
	public boolean getGriefing()
	{
		return this.griefing.get().booleanValue();
	}
	
	public boolean getAnimeScreaming()
	{
		return this.animeScreaming.get().booleanValue();
	}
	
	public boolean getSpecialFlying()
	{
		return this.specialFlying.get().booleanValue();
	}
	
	public boolean getOneFruitPerWorld()
	{
		return this.oneFruitPerWorld.get().booleanValue();
	}
	
	public boolean getYamiPower()
	{
		return this.yamiPower.get().booleanValue();
	}

	public boolean getTelemetry()
	{
		return this.telemetry.get().booleanValue();
	}
}
