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
	public static final int MAX_GENERAL = 999999999; //Used by Bounty, Reputation, Belly & Extol
	public static final int MAX_CREW = 50;
	public static final int MAX_ACTIVITIES = 4;
	
	public static final Item[] KAIROSEKI_ITEMS = new Item[] {ListMisc.Kairoseki, ListMisc.KairosekiBullets, ListMisc.DenseKairoseki};
	public static final List<Block> BANNED_BLOCKS = createBannedBlocksList();
			
	private static List<Block> createBannedBlocksList()
	{
		List<Block> blocks = new ArrayList<Block>();
		
		blocks.add(Blocks.bedrock);
		blocks.add(ListMisc.Ope);
		blocks.add(ListMisc.OpeMid);
		
		return blocks;
	}
	
	public static HashMap<Class, EnumQuestlines> questGivers = createQuestGiversMap();

	private static HashMap<Class, EnumQuestlines> createQuestGiversMap()
	{
		HashMap<Class, EnumQuestlines> map = new HashMap<Class, EnumQuestlines>();
		
		map.put(EntityDojoSensei.class, EnumQuestlines.SWORDSMANPROGRESSION);
		
		return map;
	}
	
	public static HashMap<String, String[]> abilityManualParams = createManualParamsMap();

	private static HashMap<String, String[]> createManualParamsMap()
	{
		HashMap<String, String[]> map = new HashMap<String, String[]>();
		
		map.put("hiken", new String[] {"desc", "Turns the user\\'s fist into flames and launches it towards the target."});
		map.put("higan", new String[] {"desc", "Turns the user\\'s fingertips into flames and shoots bullets made of fire from them."});
		map.put("hidaruma", new String[] {"desc", "Creates small green fireballs that set the target on fire."});
		map.put("jujika", new String[] {"desc", "Launches a cross-shaped column of fire at the opponent, leaving a cross of fire."});
		map.put("enjomo", new String[] {"desc", "Creates a circle of fire around the user, burning everyone inside of it."});
		map.put("daienkaientei", new String[] {"desc", "Amasses the user\\'s flames into a gigantic fireball that the user hurls at the opponent."});
		
		map.put("iceblockpartisan", new String[] {"desc", "Creates several spears of ice that the user hurls at the enemy."});
		map.put("iceball", new String[] {"desc", "Creates a sphere of ice that surrounds the opponent."});
		map.put("iceage", new String[] {"desc", "Freezes a large area around the user and everyone inside of it."});
		map.put("icetimecapsule", new String[] {"desc", "A wave of ice is sent along the ground and freezes every enemy it hits."});
		map.put("iceblockpheasant", new String[] {"desc", "Releases a massive wave of ice in the shape of a pheasant."});
		map.put("icesaber", new String[] {"desc", "Creates a sharp blade made of solid ice."});
		
		map.put("springhopper", new String[] {"desc", "Turning the userps legs into springs, which launches them into the air."});
		map.put("springdeathknock", new String[] {"desc", "By turning the user\\'s arm into a spring and compressing it, they can launch a powerful punch."});
		map.put("springsnipe", new String[] {"desc", "Turning the user\\'s forelegs into springs, they can launch themselves directly at the opponent."});
		
		map.put("yasakaninomagatama", new String[] {"desc", "Fires a torrent of deadly light particles, causing huge destruction."});
		map.put("yatanokagami", new String[] {"desc", "Uses light to instantly teleport the user to their desired location."});
		map.put("amaterasu", new String[] {"desc", "Creates an immense beam of light, which causes massive damage."});
		map.put("flash", new String[] {"desc", "The user creates a bright flash of light, blinding their opponents."});
		map.put("amanomurakumo", new String[] {"desc", "Focuses light in the user\\'s hand to create a sword."});

		map.put("shishanote", new String[] {"desc", "Shoots invisible projectiles that explode upon impact."});
		map.put("skatting", new String[] {"desc", "Turns the user\\'s entire body invisible."});

		map.put("noronorobeam", new String[] {"desc", "Shoots a beam of photons at the opponent, completely slowing them down."});
		map.put("noronorobeamsword", new String[] {"desc", "Focuses photons inside a hilt to create a sword."});
		map.put("kyubirush", new String[] {"desc", "While the opponent is slowed, the user delivers a series of punches, which hits the opponent all at once."});

		map.put("gomugomunopistol", new String[] {"desc", "The user stretches their arm to hit the opponent."});
		map.put("gomugomunobazooka", new String[] {"desc", "The user stretches their arms to send the opponent flying by hitting them with both palms"});
		map.put("gearsecond", new String[] {"desc", "By speding up their blood flow, the user gains strength, speed and mobility."});
		map.put("gearthird", new String[] {"desc", "By blowing air and inflating their body, the user\\'s attacks get bigger and gain incredible strength."});
		map.put("gearforth", new String[] {"desc", "The user inflates their muscle structure to tremendously increase the power of their attacks."});
		
		map.put("powerpoint", new String[] {"desc", "The user transforms into a half-bison hybrid, which focuses on strength, Allows the user to use \\'Kokutei Cross\\' and \\'Fiddle Banff\\'"});
		map.put("kokuteicross", new String[] {"desc", "The transformed user crosses their hooves to hit the opponent."});
		map.put("fiddlebanff", new String[] {"desc", "The transformed user dashes towards the opponent to crash against them with great power."});
		
		map.put("room", new String[] {"desc", "Creates a spherical space around the user, in which they can manipulate anything with other skills."});
		map.put("countershock", new String[] {"desc", "Releases a strong electrical current, which shocks the opponent."});
		map.put("mes", new String[] {"desc", "Removes the heart of the user\\'s target, which they can then damage to hurt the opponent."});
		map.put("gammaknife", new String[] {"desc", "Creates a blade of gamma radiation, which massively damages the opponent\\'s organs"});
		map.put("shambles", new String[] {"desc", "The user swaps place with the closest entity within the ROOM."});
		map.put("takt", new String[] {"desc", "Lifts all entities inside ROOM, making them unable to move."});	
		map.put("injectionshot", new String[] {"desc", "While holding a weapon, the user charges at the enemy, leaving them poisoned and confused."});	

		map.put("elthor", new String[] {"desc", "Focuses the user\\'s electricity to strike the opponent with lightning from above."});
		map.put("voltvari", new String[] {"desc", "Creates a concentrated ball of lightning, which varies in power."});
		map.put("raigo", new String[] {"desc", "Creates a huge cloud filled with electricity, which causes massive damage."});
		map.put("kari", new String[] {"desc", "Creates an electrical current around the user, which then explodes."});
		map.put("sango", new String[] {"desc", "Launches a huge concentrated chunk of electricity at the opponent."});		
				
		map.put("kickbomb", new String[] {"desc", "The user kicks their opponent, detonating their leg on impact."});
		map.put("nosefancycannon", new String[] {"desc", "Shoots dried mucus at the opponent, which explodes on impact."});

		map.put("whiteout", new String[] {"desc", "Shoots clouds of smoke to engulf the opponent and trap them."});
		map.put("whitesnake", new String[] {"desc", "Launches a long dense smoke cloud in the shape of a snake to grab the opponent and damage them."});
		map.put("whitelauncher", new String[] {"desc", "Transforms the user into smoke and launches them forward."});
		
		map.put("darkmatter", new String[] {"desc", "Launches a ball of darkness that engulfs the opponent."});
		map.put("kurouzu", new String[] {"desc", "Creates a strong gravitational force, that pulls the opponent towards the user."});
		map.put("blackhole", new String[] {"desc", "The user spreads darkness over the target area, which engulfs anyone and anything inside of it."});
		map.put("liberation", new String[] {"desc", "The user expells everything sucked up by the \"Black Hole\" at the target location."});
		map.put("blackworld", new String[] {"desc", "The user spreads darkness to the surroundings, blinding enemies and creating pillars made of Darkness."});

		map.put("todoroki", new String[] {"desc", "The user shouts and creates a powerful sound blast."});
		
		map.put("negativehollow", new String[] {"desc", "The user launches a ghost that drains the target\\'s will."});
		map.put("minihollow", new String[] {"desc", "Launches small ghosts at the opponent, exploding upon impact."});
		map.put("tokuhollow", new String[] {"desc", "Creates a huge ghost that causes a massive explosion upon impact."});
		
		map.put("barrier", new String[] {"desc", "The user creates an impenetrable wall that shields them from attacks."});
		map.put("barrierball", new String[] {"desc", "The user creates a spherical barrier around them, shielding them from attacks."});
		map.put("barriercrash", new String[] {"desc", "Launches a barrier towards the opponent, smashing it against them."});
		map.put("baribarinopistol", new String[] {"desc", "The user shapes a barrier aroud their fist, which greatly increases the power of a punch."});
		map.put("barrierbilitystairs", new String[] {"desc", "By shaping the Barrier, the user can create stairs."});

		map.put("hydra", new String[] {"desc", "Launches a dragon made out of liqiud poison at the opponent."});
		map.put("chloroball", new String[] {"desc", "The user spits a bubble made of poison towards the enemy, which leaves poison on the ground."});
		map.put("dokufugu", new String[] {"desc", "Shoots multiple poisonous bullets at the opponent."});
		map.put("dokugumo", new String[] {"desc", "Creates a dense cloud of poisonous smoke, which moves along with the user and poisons and blinds everyone inside."});
		map.put("venomroad", new String[] {"desc", "The user fires a Hydra at the target location and transports there through its path."});
		map.put("venomdemon", new String[] {"desc", "The user coats himself in layers of strong corrosive venom, becoming a Venom Demon and leaving a highly poisonou trail."});
				
		map.put("candlewall", new String[] {"desc", "Creates a wax wall to protect the user."});
		map.put("candlehouse", new String[] {"desc", "Creates a big house-like structure made of wax, to protect the user."});
		map.put("dorudoruartsmori", new String[] {"desc", "The user fires a harpoon made of wax at the opponent."});
		map.put("dorudoruartsken", new String[] {"desc", "The user uses hardened wax to create a sword."});
		map.put("candlelock", new String[] {"desc", "Traps the opponent\\'s feet in hardened wax, which makes them unable to move."});

		map.put("daifunka", new String[] {"desc", "The user covers their fist in lava and fires it at the opponent."});
		map.put("meigo", new String[] {"desc", "The user transforms their arm into magma and thrusts it at the opponent."});
		map.put("ryuseikazan", new String[] {"desc", "Functions like \"Dai Funka\", but multiple fists are launched at the opponent."});
		map.put("bakuretsukazan", new String[] {"desc", "By spreading magma to the surroundings, the user turns everything into lava."});

		map.put("brickbat", new String[] {"desc", "Launches bats created from the user\\'s shadow at the the opponent."});
		map.put("blackbox", new String[] {"desc", "Encases and suffocates the opponent in a box made of shadows."});
		map.put("tsunotokage", new String[] {"desc", "The user creates a lizard-like shadow under his opponent, which pierces them from below."});
		map.put("doppelman", new String[] {"desc", "Creates a living version of the user\\'s shadow to help them fight."});

		map.put("kaishin", new String[] {"desc", "The user cracks the air, which launches a small but powerful explosion towards the opponent."});
		map.put("kabutowari", new String[] {"desc", "The user punches the air and creates a massive explosion around themselves."});
		map.put("shimayurashi", new String[] {"desc", "Launches a powerful explosion which spreads towards the opponent."});
		map.put("gekishin", new String[] {"desc", "The user creates a tremor while punching the enemy, inflicting massive damage."});

		map.put("ursusshock", new String[] {"desc", "The user compresses air and sends it towards the opponent to create a massive explosion."});
		map.put("padho", new String[] {"desc", "Launches a paw-shaped shockwave at the opponent."});
		map.put("tsupparipadho", new String[] {"desc", "Similar to \"Pad Ho\", but fires a barrage of shockwaves."});
		map.put("hanpatsu", new String[] {"desc", "Anyone the user punches gets sent flying far into the air."});
		 
		map.put("parasite", new String[] {"desc", "The user binds the opponent with a string that renders them immobile."});
		map.put("soranomichi", new String[] {"desc", "The user creates strings under their feet to launch themselves into the air."});
		map.put("overheat", new String[] {"desc", "The user shoots a rope made of heated strings at the opponent, exploding upon impact."});
		map.put("tamaito", new String[] {"desc", "The user shoots a small bundle of strings, acting like a bullet."});
		map.put("kumonosugaki", new String[] {"desc", "Creates a huge web that protects the user from any attack."});
		map.put("torikago", new String[] {"desc", "Creates an indestructible dome made of strings, that damage anyone who toches then"});

		map.put("barjan", new String[] {"desc", "Launches a crescent-shaped wave of sand at the opponent, that dehydrates them."});
		map.put("grounddeath", new String[] {"desc", "Dries out the surroundings and suffucates all nearby opponents in sand."});
		map.put("sables", new String[] {"desc", "The user launches a compressed sandstorm at the opponent, which sends them flying."});		
		map.put("desertspada", new String[] {"desc", "The user extends their sand along the ground, splitting it and suffocating everything it its path. "});		
		
		map.put("karakuni", new String[] {"desc", "Removes the oxygen around the user, suffocating everyone in the vicinity."});
		map.put("gastanet", new String[] {"desc", "The user fills castanets with unstable gas, whcih causes an explosion when slammed together."});
		map.put("gastille", new String[] {"desc", "Shoots a beam of poisonous gas from the user\\'s mouth, that explodes on impact."});		
		map.put("gasrobe", new String[] {"desc", "Launches a cloud of poison at the opponent."});
		map.put("bluesword", new String[] {"desc", "The user fills a hilt with lamable gas, them sets it on fire to create a sword."});
		
		map.put("fubuki", new String[] {"desc", "The user releases an extremely cold stream of snow that spreads into the air around them."});
		map.put("kamakura", new String[] {"desc", "Creates an igloo-like snow barrier around the user."});
		map.put("kamakurajussoshi", new String[] {"desc", "Like \"Kamakura\", but creates a multi-layered snow barrier."});		
		map.put("yukirabi", new String[] {"desc", "Launches numerous hardened snowballs, that have the shape of a rabbit\\'s head."});
		map.put("tabirayuki", new String[] {"desc", "The user creates a sword made of solid hardened snow."});
		map.put("yukigaki", new String[] {"desc", "The user creates a huge wall of snow."});

		map.put("hybridpoint", new String[] {"desc", "The user transforms into a phoenix-human hybrid, which allows them to fly. Allows the user to use \\'Phoenix Goen\\' "});
		map.put("phoenixpoint", new String[] {"desc", "The user fully transforms into a phoenix, allowing them to fly at great speed. Allows the user to use both \\'Phoenix Goen\\' and \\'Tensei no Soen\\'"});
		map.put("phoenixgoen", new String[] {"desc", "Launches a powerful fiery shockwave made of blue flames at the target."});
		map.put("tenseinosoen", new String[] {"desc", "While in the air, the user amasses spiraling flames, then slams into the ground, releasing a massive shockwave."});		
		map.put("blueflamesofresurrection", new String[] {"desc", "Blue phoenix flames grant the user regeneration."});
		map.put("flameofrestoration", new String[] {"desc", "Uses the blue flames to heal the target."});	
		
		map.put("soru", new String[] {"desc", "Allows the user to move at an extremely high speed.", "dorikiRequiredForHumans", "500"});
		map.put("tekkai", new String[] {"desc", "Hardens the user\\'s body to protect themselves, but they\\'re unable to move.", "dorikiRequiredForHumans", "1500"});
		map.put("geppo", new String[] {"desc", "The user kicks the air beneath them to launch themselves into the air.", "dorikiRequiredForHumans", "4500"});
		map.put("rankyaku", new String[] {"desc", "By kicking at a very high speed, the user launches an air blade at the opponent.", "dorikiRequiredForHumans", "8500"});
		map.put("shigan", new String[] {"desc", "The user thrusts their finger at the opponent, to pierce them.", "dorikiRequiredForHumans", "3000"});
		map.put("kamie", new String[] {"desc", "Maked the user\\'s body flexible in order to avoid attacks.", "dorikiRequiredForHumans", "6000"});
		
		map.put("uchimizu", new String[] {"desc", "The user hurls big and fast water droplets at the opponent.", "dorikiRequiredForFishman", "800"});
		map.put("murasame", new String[] {"desc", "The user fires densely compressed shark-shaped waterbullet at the opponent.", "dorikiRequiredForFishman", "2000"});
		map.put("samehadashotei", new String[] {"desc", "The user concentrates their power to their palms, protecting themselves from attacks.", "dorikiRequiredForFishman", "3000"});
		map.put("karakusagawaraseiken", new String[] {"desc", "The user punches the air, which sends a shockwave through water vapor in the air.", "dorikiRequiredForFishman", "7500"});
		map.put("kachiagehaisoku", new String[] {"desc", "The user delivers a powerful kick to the opponent\\'s chin.", "dorikiRequiredForFishman", "2500"});
		
		map.put("freshfire", new String[] {"desc", "The user heats up and breathes fire like a flamethrower at the opponent.", "dorikiRequiredForCyborgs", "0"});
		map.put("colaoverdrive", new String[] {"desc", "The user absorbs all of their cola at once to boost their physical abilities.", "dorikiRequiredForCyborgs", "0"});
		map.put("radicalbeam", new String[] {"desc", "After charging, the user launches a powerful beam of energy at the opponent.", "dorikiRequiredForCyborgs", "0"});
		map.put("strongright", new String[] {"desc", "The user punches the opponent with a metal fist.", "dorikiRequiredForCyborgs", "0"});
		map.put("coupdevent", new String[] {"desc", "Launches a powerful blast of compressed air that blows the opponent away.", "dorikiRequiredForCyborgs", "0"});

		map.put("busoshokuhaki", new String[] {"desc", "The user forms an invisible armor around themselves using their willpower, By using this form of Haki, the user can damage Logias.", "dorikiRequiredForHumans", "9000", "dorikiRequiredForFishman", "9000", "dorikiRequiredForCyborgs", "8500"});
		map.put("kenbunshokuhaki", new String[] {"desc", "Allows the user to sense the presence of others, pointing them to the opponent, Can also locate invisible mobs and players.", "dorikiRequiredForHumans", "5000", "dorikiRequiredForFishman", "4000", "dorikiRequiredForCyborgs", "5500"});
		
		map.put("shishishisonson", new String[] {"desc", "The user dashes forward and rapidly slashes the opponent."});
		map.put("yakkodori", new String[] {"desc", "Launches a crescent moon-shaped slash, which destroys everything in its path."});
		map.put("sanbyakurokujupoundho", new String[] {"desc", "The user launches a powerful slash, causing great destruction."});
		map.put("otatsumaki", new String[] {"desc", "By spinning, the user creates a small tornado, which slashes and weakens nearby opponents."});
		
		map.put("kaenboshi", new String[] {"desc", "Fires a flaming pellet, that sets the target on fire."});
		map.put("kemuriboshi", new String[] {"desc", "On impact releases smoke that poisons and confuses targets."});
		map.put("renpatsunamariboshi", new String[] {"desc", "Lets the user fire a barrage of exploding shots."});
		map.put("sakuretsusabotenboshi", new String[] {"desc", "The fired projectile explodes on impact and creates cacti arond the target, to trap them."});
		
		return map;
	}

}
