package xyz.pixelatedw.mineminenomi.init;

import java.awt.Color;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.EffectType;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.ModelType;

public class ModAttributes
{
	public static AbilityAttribute MINI_MINI_NO_FULL_REBOUND = new AbilityAttribute("Mini Mini no Full Rebound").setAbilityCooldown(3).setAbilityPassive(true);
	
	public static AbilityAttribute BIGAN = new AbilityAttribute("Bigan").setAbilityCooldown(8).setProjectileDamage(20).setProjectileModel(ModelType.CUBE).setProjectileColor("#6D5E24").setProjectileSize(1, 1, 2.4).setProjectileTicks(10).addEffects(EffectType.PROJECTILE, new EffectInstance(Effects.WEAKNESS, 600, 0), new EffectInstance(Effects.SLOWNESS, 600, 2)).setModelOffsets(0, 1.5, 0);
	public static AbilityAttribute GIRAFFE_SPEED_POINT = new AbilityAttribute("Giraffe Speed Point").setAbilityDisplayName("Speed Point").setAbilityTexture("giraffefull").setAbilityCooldown(1).setAbilityPassive(true);
	public static AbilityAttribute GIRAFFE_POWER_POINT = new AbilityAttribute("Giraffe Power Point").setAbilityDisplayName("Power Point").setAbilityTexture("giraffehybrid").setAbilityCooldown(1).setAbilityPassive(true);
	
	public static AbilityAttribute MOGURA_TONPO = new AbilityAttribute("Mogura Tonpo: Mogugyo").setAbilityCooldown(10).setAbilityTexture("moguratonpo");
	public static AbilityAttribute MOGURA_BANANA = new AbilityAttribute("Mogura Banana").setAbilityCooldown(12).setAbilityPunch(10);
	public static AbilityAttribute MOGU_POWER_POINT = new AbilityAttribute("Mogu Power Point").setAbilityDisplayName("Mole Point").setAbilityTexture("molepoint").setAbilityCooldown(1).setAbilityPassive(true);
	
	public static AbilityAttribute GANMEN_SEICHO_HORMONE = new AbilityAttribute("Ganmen Seicho Hormone").setAbilityCooldown(5).setAbilityPunch();
	public static AbilityAttribute TENSION_HORMONE = new AbilityAttribute("Tension Hormone").setAbilityCooldown(40).setAbilityPunch();
	public static AbilityAttribute CHIYUHORMONE = new AbilityAttribute("Chiyu Hormone").setAbilityCooldown(20).setAbilityPunch();
	public static AbilityAttribute ONNA_HORMONE = new AbilityAttribute("Onna Hormone").setAbilityCooldown(15).setAbilityPunch();
	
	public static AbilityAttribute CHIYUPOPO = new AbilityAttribute("Chiyupopo").setAbilityCooldown(10);
	public static AbilityAttribute HEALING_TOUCH = new AbilityAttribute("Healing Touch").setAbilityCooldown(15).setAbilityPunch();
	
	public static AbilityAttribute ZOU_HYBRID_POINT = new AbilityAttribute("Zou Hybrid Point").setAbilityCooldown(1).setAbilityDisplayName("Hybrid Point").setAbilityTexture("zouhybrid").setAbilityPassive(true);
	public static AbilityAttribute ZOU_FULL_POINT = new AbilityAttribute("Zou Point").setAbilityTexture("zoufull").setAbilityCooldown(1).setAbilityPassive(true);
	public static AbilityAttribute IVORY_DART = new AbilityAttribute("Ivory Dart").setAbilityCooldown(10);
	public static AbilityAttribute GREAT_STOMP = new AbilityAttribute("Great Stomp").setAbilityCooldown(15);
	public static AbilityAttribute IVORY_STOMP = new AbilityAttribute("Ivory Stomp").setAbilityCooldown(8).setAbilityPunch();
	public static AbilityAttribute TRUNK_SHOT = new AbilityAttribute("Trunk Shot").setAbilityCooldown(7).setProjectileModel(ModelType.CUBE).setProjectileTexture("zouskin").setProjectileSize(2.5, 2.5, 4).setProjectileColor("838993").setProjectileDamage(10).addEffects(EffectType.PROJECTILE, new EffectInstance(Effects.WEAKNESS, 5 * 20)).setProjectilePhysical();
	
	public static AbilityAttribute SOUL_PARADE = new AbilityAttribute("Soul Parade").addEffects(EffectType.USER, new EffectInstance(Effects.RESISTANCE, 30, 100), new EffectInstance(Effects.SLOWNESS, 30, 100)).setAbilityCooldown(5).setAbilityPassive();
	public static AbilityAttribute KASURIUTA_FUBUKI_GIRI = new AbilityAttribute("Kasuriuta: Fubuki Giri").setAbilityCooldown(10);
	
	public static AbilityAttribute BAKU_BAKU_FACTORY = new AbilityAttribute("Baku Baku Factory");
	public static AbilityAttribute BAKU_TSUIHO = new AbilityAttribute("Baku Tsuiho").setAbilityCooldown(10).setAbilityCharges(10 * 20).setProjectileModel(ModelType.CUBE).setProjectileSize(2, 2, 2).setProjectileColor("E3E3E3").setProjectileDamage(8);
	public static AbilityAttribute BERO_CANNON = new AbilityAttribute("Bero Cannon").setAbilityCooldown(5).setProjectileModel(ModelType.CUBE).setProjectileSize(2, 2, 2).setProjectileColor("E3E3E3").setProjectileDamage(4);
	public static AbilityAttribute BAKU_MUNCH = new AbilityAttribute("Baku Munch").setAbilityCooldown(5);
	
	public static AbilityAttribute PHOENIX_HYBRID_POINT = new AbilityAttribute("Phoenix Hybrid Point").setAbilityCooldown(1).setAbilityDisplayName("Hybrid Point").setAbilityTexture("phoenixhybrid").setAbilityPassive(true);
	public static AbilityAttribute PHOENIX_FULL_POINT = new AbilityAttribute("Phoenix Point").setAbilityTexture("phoenixfull").setAbilityCooldown(1).setAbilityPassive(true);
	public static AbilityAttribute BLUE_FLAMES_OF_RESURRECTION = new AbilityAttribute("Blue Flames of Resurrection").setAbilityCooldown(20).addEffects(EffectType.USER, new EffectInstance(Effects.REGENERATION, 3 * 20, 4));
	public static AbilityAttribute FLAME_OF_RESTORATION = new AbilityAttribute("Flame of Restoration").setAbilityCooldown(3).setAbilityPassive().setAbilityPunch();
	public static AbilityAttribute PHOENIX_GOEN = new AbilityAttribute("Phoenix Goen").setAbilityCooldown(10).setProjectileDamage(12);
	public static AbilityAttribute TENSEI_NO_SOEN = new AbilityAttribute("Tensei no Soen").setAbilityCooldown(30).setAbilityCharges(5 * 20);
	
	public static AbilityAttribute BISON_POWER_POINT = new AbilityAttribute("Bison Power Point").setAbilityCooldown(1).setAbilityDisplayName("Hybrid Point").setAbilityTexture("bisonhybrid").setAbilityPassive(true);
	public static AbilityAttribute BISON_SPEED_POINT = new AbilityAttribute("Bison Speed Point").setAbilityCooldown(1).setAbilityDisplayName("Bison Point").setAbilityTexture("bisonfull").setAbilityPassive(true);	
	public static AbilityAttribute FIDDLE_BANFF = new AbilityAttribute("Fiddle Banff").setAbilityCooldown(7);
	public static AbilityAttribute KOKUTEI_CROSS = new AbilityAttribute("Kokutei Cross").setAbilityCooldown(7).setAbilityPassive().setAbilityPunch();
	
	public static AbilityAttribute SAGARI_NO_RYUSEI = new AbilityAttribute("Sagari no Ryusei").setAbilityCooldown(20).setProjectileTicks(256).setProjectileModel(ModelType.SPHERE).setProjectileColor("51585B").setProjectileSize(50, 50, 50).setProjectileDamage(50).setProjectileExplosion(20, false).setProjectileCollisionSizes(2, 2, 2);
	public static AbilityAttribute MOKO = new AbilityAttribute("Moko").setProjectileDamage(10).setAbilityCooldown(12).setProjectileModel(ModelType.CUBE).setProjectileSize(new double[] {0, 0, 0}).setProjectileMoveThroughBlocks(true);
	public static AbilityAttribute ABARE_HIMATSURI = new AbilityAttribute("Abare Himatsuri").setAbilityCooldown(10).setAbilityPassive();
	public static AbilityAttribute JURYOKU = new AbilityAttribute("Juryoku").setAbilityDisplayName("Jigoku Tabi").setAbilityCooldown(12).setAbilityPassive();
	
	public static AbilityAttribute BLACK_WORLD = new AbilityAttribute("Black World").setAbilityCooldown(25).addEffects(EffectType.AOE, new EffectInstance(Effects.SLOWNESS, 200, 100), new EffectInstance(Effects.MINING_FATIGUE, 200, 100), new EffectInstance(Effects.BLINDNESS, 200, 2)).setEffectRadius(20);
	public static AbilityAttribute DARK_MATTER = new AbilityAttribute("Dark Matter").setAbilityCooldown(12).setProjectileModel(ModelType.SPHERE).setProjectileColor("000000").setProjectileSize(7, 7, 7);
	public static AbilityAttribute KUROUZU = new AbilityAttribute("Kurouzu").setAbilityCooldown(10).setAbilityCharges(3 * 20);;
	public static AbilityAttribute LIBERATION = new AbilityAttribute("Liberation").setAbilityCooldown(5);
	public static AbilityAttribute BLACK_HOLE = new AbilityAttribute("Black Hole").setAbilityCooldown(7);
	
	public static AbilityAttribute TODOROKI = new AbilityAttribute("Todoroki").setAbilityCooldown(20).setProjectileModel(ModelType.CUBE).setProjectileColor("#87CEFA").setProjectileSize(2, 2, 4).setProjectileDamage(15).setAbilityRepeater(20, 3);
	
	public static AbilityAttribute PERFUME_FEMUR = new AbilityAttribute("Perfume Femur").setAbilityCooldown(10).setAbilityPunch(10);
	public static AbilityAttribute SLAVE_ARROW = new AbilityAttribute("Slave Arrow").setAbilityCooldown(10).setProjectileModel(ModelType.ARROW).setProjectileColor("#FF69B4").setProjectileSize(1, 1, 2).setProjectileDamage(6).setAbilityRepeater().setAbilityCharges(7 * 20);
	public static AbilityAttribute PISTOL_KISS = new AbilityAttribute("Pistol Kiss").setAbilityCooldown(5).setProjectileModel(ModelType.HEART).setProjectileSize(.5, .5, .5).setProjectileTexture("meromeromellow").setProjectileDamage(4).setModelOffsets(0, -0.5, 0).addEffects(EffectType.PROJECTILE, new EffectInstance(Effects.SLOWNESS, 100, 5), new EffectInstance(Effects.MINING_FATIGUE, 100, 5));
	public static AbilityAttribute MERO_MERO_MELLOW = new AbilityAttribute("Mero Mero Mellow").setAbilityCooldown(20).setProjectileModel(ModelType.HEART).setProjectileSize(3, 3, 3).setProjectileTexture("meromeromellow").setProjectileDamage(10).setModelOffsets(0, -1, 0).addEffects(EffectType.PROJECTILE, new EffectInstance(Effects.SLOWNESS, 400, 100), new EffectInstance(Effects.MINING_FATIGUE, 400, 100)).setProjectileCollisionSizes(1.25, 1.25, 1.25);
	
	public static AbilityAttribute SPARKLING_DAISY = new AbilityAttribute("Sparkling Daisy").setAbilityCooldown(12);
	public static AbilityAttribute SPIRAL_HOLLOW = new AbilityAttribute("Spiral Hollow").setAbilityCooldown(10).setProjectileDamage(20).setProjectileModel(ModelType.CUBE).setProjectileColor("#F8F8FF").setProjectileSize(3, 3, 5).setProjectileTicks(3).setProjectilePhysical();
	public static AbilityAttribute ATOMIC_SPURT = new AbilityAttribute("Atomic Spurt").setAbilityCooldown(25).setAbilityPassive();
	public static AbilityAttribute SPAR_CLAW = new AbilityAttribute("Spar Claw").setAbilityCooldown(5).setAbilityPassive(true).setAbilityPunch(10);
	public static AbilityAttribute SPIDER = new AbilityAttribute("Spider").addEffects(EffectType.USER, new EffectInstance(Effects.RESISTANCE, 30, 100), new EffectInstance(Effects.SLOWNESS, 30, 100), new EffectInstance(Effects.MINING_FATIGUE, 30, 5), new EffectInstance(Effects.JUMP_BOOST, 30, -100)).setAbilityPassive();
	
	public static AbilityAttribute NEGATIVE_HOLLOW = new AbilityAttribute("Negative Hollow").setAbilityCooldown(6).setProjectileModel(ModelType.NEGATIVE_HOLLOW).setProjectileTexture("negativehollow").setProjectileAlpha(150).setProjectileSize(2, 2, 2).setProjectileDamage(10).addEffects(EffectType.PROJECTILE, new EffectInstance(Effects.NAUSEA, 200, 1), new EffectInstance(Effects.SLOWNESS, 200, 1));
	public static AbilityAttribute MINI_HOLLOW = new AbilityAttribute("Mini Hollow").setAbilityCooldown(3).setProjectileModel(ModelType.MINI_HOLLOW).setProjectileSize(0.4, 0.4, 0.4).setProjectileColor("#F8F8FF").setProjectileAlpha(150).setProjectileDamage(2).setProjectileExplosion(2, false).addEffects(EffectType.PROJECTILE, new EffectInstance(Effects.NAUSEA, 120, 0), new EffectInstance(Effects.SLOWNESS, 120, 0)).setAbilityRepeater();
	public static AbilityAttribute TOKU_HOLLOW = new AbilityAttribute("Toku Hollow").setAbilityCooldown(10).setProjectileModel(ModelType.TOKU_HOLLOW).setProjectileTexture("tokuhollow").setProjectileAlpha(150).setProjectileSize(4, 4, 4).setProjectileDamage(10).setProjectileExplosion(7, false).addEffects(EffectType.PROJECTILE, new EffectInstance(Effects.NAUSEA, 400, 1), new EffectInstance(Effects.SLOWNESS, 400, 1)).setProjectileCollisionSizes(1.5, 1.5, 1.5);
	
	public static AbilityAttribute BLACK_KNIGHT = new AbilityAttribute("Black Knight").setAbilityCooldown(15).setAbilityPassive();
	public static AbilityAttribute KUMO_NO_SUGAKI = new AbilityAttribute("Kumo no Sugaki").setAbilityCooldown(10).addEffects(EffectType.USER,new EffectInstance(Effects.RESISTANCE, 30, 100), new EffectInstance(Effects.SLOWNESS, 30, 100), new EffectInstance(Effects.MINING_FATIGUE, 30, 5), new EffectInstance(Effects.JUMP_BOOST, 30, -100)).setAbilityPassive();;
	public static AbilityAttribute TORIKAGO = new AbilityAttribute("Torikago").setAbilityCooldown(2).setAbilityPassive(true);
	public static AbilityAttribute TAMAITO = new AbilityAttribute("Tamaito").setAbilityCooldown(5).setProjectileDamage(6).setProjectileModel(ModelType.CUBE).setProjectileSize(.5, .5, 1).setProjectileColor("#dee1e5");
	public static AbilityAttribute OVERHEAT = new AbilityAttribute("Overheat").setAbilityCooldown(8).setProjectileDamage(20).setProjectileModel(ModelType.CUBE).setProjectileSize(1, 1, 5).setProjectileExplosion(3, false).setProjectileColor("#f77c25");
	public static AbilityAttribute SORA_NO_MICHI = new AbilityAttribute("Sora no Michi").setAbilityCooldown(1);
	public static AbilityAttribute PARASITE = new AbilityAttribute("Parasite").setAbilityCooldown(5);
	
	public static AbilityAttribute BARRIERBILITY_STAIRS = new AbilityAttribute("Barrierbility Stairs").setProjectileTicks(60).setProjectileModel(ModelType.CUBE).setProjectileSize(0, 0, 0).setProjectileMoveThroughBlocks(true).setAbilityPassive();
	public static AbilityAttribute BARIBARI_NO_PISTOL = new AbilityAttribute("Bari Bari no Pistol").setAbilityCooldown(5).setAbilityPassive().setAbilityPunch();
	public static AbilityAttribute BARRIER_BALL = new AbilityAttribute("Barrier Ball").setAbilityCooldown(0.5).setAbilityPassive();
	public static AbilityAttribute BARRIER_CRASH = new AbilityAttribute("Barrier Crash").setAbilityCooldown(5).setProjectileTicks(60).setProjectileModel(ModelType.CUBE).setProjectileColor("#87CEFA").setProjectileSize(9, 9, .3).setProjectileDamage(15).setProjectileCollisionSizes(1.5, 2, 1.5);
	public static AbilityAttribute BARRIER = new AbilityAttribute("Barrier").setAbilityPassive();
	
	public static AbilityAttribute YUKI_GAKI = new AbilityAttribute("Yuki Gaki").setAbilityCooldown(8);
	public static AbilityAttribute FUBUKI = new AbilityAttribute("Fubuki").setAbilityCooldown(12);
	public static AbilityAttribute TABIRA_YUKI = new AbilityAttribute("Tabira Yuki").setAbilityPassive(true);
//	public static AbilityAttribute MANNENYUKI = new AbilityAttribute("Mannen Yuki");
	public static AbilityAttribute KAMAKURA_JUSSOSHI = new AbilityAttribute("Kamakura Jussoshi").setAbilityCooldown(18);
	public static AbilityAttribute YUKI_RABI = new AbilityAttribute("Yuki Rabi").setAbilityCooldown(2).setProjectileColor(Color.WHITE).setProjectileDamage(5).setProjectileModel(ModelType.YUKI_RABI).setProjectileTexture("yukirabi").setProjectileSize(1, 1, 1).addEffects(EffectType.PROJECTILE, new EffectInstance(Effects.SLOWNESS, 50, 1)).setAbilityRepeater(2, 2);
	public static AbilityAttribute KAMAKURA = new AbilityAttribute("Kamakura").setAbilityCooldown(6);
	
//	public static AbilityAttribute SHINOKUNI = new AbilityAttribute("Shinokuni");
	public static AbilityAttribute KARAKUNI = new AbilityAttribute("Karakuni").setAbilityCooldown(20);
	public static AbilityAttribute BLUE_SWORD = new AbilityAttribute("Blue Sword").setAbilityPassive(true);
	public static AbilityAttribute GASTANET = new AbilityAttribute("Gastanet").setAbilityCooldown(6).setAbilityExplosion(5, false);
	public static AbilityAttribute GASTILLE = new AbilityAttribute("Gastille").setAbilityCooldown(7).setProjectileSpeed(2).setProjectileDamage(10).setProjectileModel(ModelType.CUBE).setProjectileColor("324AB2").setProjectileSize(1, 1, 2).addEffects(EffectType.PROJECTILE, new EffectInstance(Effects.POISON, 200, 1)).setAbilityRepeater().setProjectileExplosion(1, false);
	public static AbilityAttribute GAS_ROBE = new AbilityAttribute("Gas Robe").setAbilityCooldown(6).setProjectileSpeed(2).setProjectileDamage(10).setProjectileModel(ModelType.CUBE).setProjectileSize(0, 0, 0).setAbilityRepeater();
	
	public static AbilityAttribute DOKU_GUMO = new AbilityAttribute("Doku Gumo").setAbilityCooldown(30).setAbilityPassive();
	public static AbilityAttribute DOKU_FUGU = new AbilityAttribute("Doku Fugu").setAbilityCooldown(8).setProjectileDamage(15).setProjectileModel(ModelType.SPHERE).setProjectileColor("A020F0").setProjectileSize(5, 5, 5).addEffects(EffectType.PROJECTILE, new EffectInstance(Effects.POISON, 240, 1)).setAbilityRepeater(10, 3);
	public static AbilityAttribute VENOM_DEMON = new AbilityAttribute("Venom Demon").setAbilityCooldown(40).setAbilityPassive(true);
	public static AbilityAttribute VENOM_ROAD = new AbilityAttribute("Venom Road").setAbilityCooldown(8).setProjectileDamage(20).setProjectileModel(ModelType.HYDRA).setProjectileTexture("hydra").setProjectileSize(2, 2, 2.4).addEffects(EffectType.PROJECTILE, new EffectInstance(Effects.POISON, 500, 1));
	public static AbilityAttribute CHLORO_BALL = new AbilityAttribute("Chloro Ball").setAbilityCooldown(6).setProjectileDamage(10).setProjectileModel(ModelType.SPHERE).setProjectileColor("A020F0").setProjectileSize(5, 5, 5).addEffects(EffectType.PROJECTILE, new EffectInstance(Effects.POISON, 240, 1));
	public static AbilityAttribute HYDRA = new AbilityAttribute("Hydra").setAbilityCooldown(8).setProjectileDamage(30).setProjectileModel(ModelType.HYDRA).setProjectileTexture("hydra").setProjectileSize(2, 2, 2.4).setProjectileTicks(10).addEffects(EffectType.PROJECTILE, new EffectInstance(Effects.POISON, 500, 1));
	
	public static AbilityAttribute CANDLE_LOCK = new AbilityAttribute("Candle Lock").setAbilityCooldown(15).setProjectileDamage(6).setProjectileModel(ModelType.CUBE).setProjectileColor("A2ADD0").setProjectileSize(.5, .5, 1).addEffects(EffectType.PROJECTILE, new EffectInstance(Effects.SLOWNESS, 400, 100), new EffectInstance(Effects.MINING_FATIGUE, 400, 100), new EffectInstance(Effects.JUMP_BOOST, 400, -1));
	public static AbilityAttribute CANDLE_HOUSE = new AbilityAttribute("Candle House").setAbilityCooldown(30);
	public static AbilityAttribute CANDLE_WALL = new AbilityAttribute("Candle Wall").setAbilityCooldown(4);
	public static AbilityAttribute DORU_DORU_ARTS_KEN = new AbilityAttribute("Doru Doru Arts : Ken").setAbilityPassive(true);
	public static AbilityAttribute DORU_DORU_ARTS_MORI = new AbilityAttribute("Doru Doru Arts : Mori").setAbilityCooldown(3).setProjectileDamage(15).setProjectileModel(ModelType.SPEAR).setProjectileTexture("dorudoruartsmori").setProjectileSize(2, 2, 2).setModelOffsets(0, 1, 0);
	
	public static AbilityAttribute BAKURETSU_KAZAN = new AbilityAttribute("Bakuretsu Kazan").setAbilityCooldown(15);
	public static AbilityAttribute RYUSEI_KAZAN = new AbilityAttribute("Ryusei Kazan").setAbilityCooldown(12).setProjectileDamage(10).setAbilityRepeater();
	public static AbilityAttribute MEIGO = new AbilityAttribute("Meigo").setAbilityCooldown(10).setProjectileDamage(40).setProjectileModel(ModelType.MEIGO).setProjectileTexture("meigo").setProjectileSize(4, 4, 4).setModelOffsets(0, 1.2, 0).setProjectileTicks(3);
	public static AbilityAttribute DAI_FUNKA = new AbilityAttribute("Dai Funka").setAbilityCooldown(8).setProjectileDamage(20).setProjectileModel(ModelType.FIST).setProjectileTexture("daifunka").setProjectileSize(4, 4, 4).setModelOffsets(0, 1, 0);
	
	public static AbilityAttribute DESERT_GIRASOLE = new AbilityAttribute("Desert Girasole").setAbilityCooldown(25).setAbilityCharges(100);
	public static AbilityAttribute DESERT_ENCIERRO = new AbilityAttribute("Desert Encierro").setAbilityCooldown(10).setAbilityPunch().addEffects(EffectType.HIT, new EffectInstance(Effects.HUNGER, 100, 1), new EffectInstance(Effects.WEAKNESS, 100, 1), new EffectInstance(Effects.SLOWNESS, 100, 1), new EffectInstance(Effects.MINING_FATIGUE, 100, 1));
	public static AbilityAttribute GROUND_DEATH = new AbilityAttribute("Ground Death").setAbilityCooldown(15);
	public static AbilityAttribute BARJAN = new AbilityAttribute("Barjan").setAbilityCooldown(5).setProjectileDamage(15).setProjectileModel(ModelType.CUBE).setProjectileColor("967117").setProjectileSize(6, 0.4, 1.5).addEffects(EffectType.PROJECTILE, new EffectInstance(Effects.HUNGER, 500, 1)).setProjectileMoveThroughBlocks(true).setProjectileCollisionSizes(1.25, 0.3, 1.25);
	public static AbilityAttribute SABLES = new AbilityAttribute("Sables").setAbilityCooldown(3).setAbilityPassive().setAbilityPunch();
	public static AbilityAttribute DESERT_SPADA = new AbilityAttribute("Desert Spada").setAbilityCooldown(10);
	
	public static AbilityAttribute TSUNO_TOKAGE = new AbilityAttribute("Tsuno-Tokage").setAbilityCooldown(10);
//	public static AbilityAttribute SHADOWSASGARD = new AbilityAttribute("Shadow's Asgard").setAbilityCooldown(400).addTasks(Tasks.shadowsasgard);
	public static AbilityAttribute BLACK_BOX = new AbilityAttribute("Black Box").setAbilityCooldown(6).setProjectileModel(ModelType.CUBE).setProjectileColor(Color.black).setProjectileSize(2, 2, 2);
	public static AbilityAttribute KAGEMUSHA = new AbilityAttribute("Kagemusha").setAbilityCooldown(5);
	public static AbilityAttribute DOPPELMAN = new AbilityAttribute("Doppelman").setAbilityCooldown(15).setAbilityPassive();
	public static AbilityAttribute BRICK_BAT = new AbilityAttribute("Brick Bat").setAbilityCooldown(4).setProjectileDamage(5).setProjectileModel(ModelType.BRICK_BAT).setProjectileSize(1, 1, 1).setModelOffsets(0, 0.5, 0).setProjectileTexture("brickbat").setAbilityRepeater(3, 2);
	
	public static AbilityAttribute GEKISHIN = new AbilityAttribute("Gekishin").setAbilityCooldown(15).setAbilityPassive().setAbilityPunch();
	public static AbilityAttribute SHIMA_YURASHI = new AbilityAttribute("Shima Yurashi").setAbilityCooldown(15).setProjectileDamage(20).setProjectileExplosion(5, false).setProjectileMoveThroughBlocks(true);
	public static AbilityAttribute KABUTOWARI = new AbilityAttribute("Kabutowari").setAbilityCooldown(7).setAbilityExplosion(5, false);
	public static AbilityAttribute KAISHIN = new AbilityAttribute("Kaishin").setAbilityCooldown(7).setProjectileDamage(50).setProjectileModel(ModelType.CUBE).setProjectileSize(0, 0, 0).setProjectileExplosion(3, false, false);
	
	public static AbilityAttribute KICK_BOMB = new AbilityAttribute("Kick Bomb").setAbilityCooldown(7).setAbilityExplosion(7, false);
	public static AbilityAttribute NOSE_FANCY_CANNON = new AbilityAttribute("Nose Fancy Cannon").setAbilityCooldown(3).setProjectileModel(ModelType.CUBE).setProjectileColor("3D2B1F").setProjectileSize(.4, .4, .8).setProjectileDamage(10).setProjectileExplosion(3, false);
	
	public static AbilityAttribute URSUS_SHOCK = new AbilityAttribute("Ursus Shock").setAbilityCooldown(15).setProjectileModel(ModelType.PAW).setProjectileColor("F8F8FF").setProjectileAlpha(80).setProjectileSize(3.5, 3.5, 3.5).setProjectileDamage(50).setProjectileExplosion(8, false, true).setAbilityCharges(40).setProjectileCollisionSizes(1.5, 1.5, 1.5);
	public static AbilityAttribute PAD_HO = new AbilityAttribute("Pad Ho").setAbilityCooldown(8).setProjectileModel(ModelType.PAW).setProjectileColor("F8F8FF").setProjectileAlpha(80).setProjectileSize(1, 1, 1).setProjectileDamage(10).setProjectileExplosion(1, false, true);
	public static AbilityAttribute TSUPPARI_PAD_HO = new AbilityAttribute("Tsuppari Pad Ho").setAbilityCooldown(10).setProjectileDamage(15).setProjectileExplosion(1, false, true).setAbilityRepeater(5, 7);
	public static AbilityAttribute HANPATSU = new AbilityAttribute("Hanpatsu").setAbilityCooldown(4).setAbilityPassive().setAbilityPunch();

	public static AbilityAttribute WHITE_STRIKE = new AbilityAttribute("White Strike").setAbilityCooldown(35).addEffects(EffectType.AOE, new EffectInstance(Effects.SLOWNESS, 300, 1), new EffectInstance(Effects.MINING_FATIGUE, 300, 1), new EffectInstance(Effects.JUMP_BOOST, 300, -10)).setEffectRadius(30);
	public static AbilityAttribute WHITE_LAUNCHER = new AbilityAttribute("White Launcher").setAbilityCooldown(5).setAbilityCharges(20);
	public static AbilityAttribute WHITE_SNAKE = new AbilityAttribute("White Snake").setAbilityCooldown(5).setProjectileTicks(120).setProjectileModel(ModelType.CUBE).setProjectileSpeed(5).setProjectileSize(0, 0, 0).setProjectileDamage(30).addEffects(EffectType.PROJECTILE, new EffectInstance(Effects.POISON, 120, 0));
	public static AbilityAttribute WHITE_OUT = new AbilityAttribute("White Out").setAbilityCooldown(4).setProjectileModel(ModelType.CUBE).setProjectileSpeed(5).setProjectileSize(0, 0, 0).setProjectileDamage(5).addEffects(EffectType.PROJECTILE, new EffectInstance(Effects.SLOWNESS, 240, 1), new EffectInstance(Effects.MINING_FATIGUE, 240, 1), new EffectInstance(Effects.JUMP_BOOST, 240, -10));
	
	public static AbilityAttribute SANGO = new AbilityAttribute("Sango").setAbilityCooldown(10).setProjectileTicks(128).setProjectileModel(ModelType.CUBE).setProjectileSize(0, 0, 0).setProjectileColor("7CB9E8").setProjectileDamage(15).setAbilityRepeater();
	public static AbilityAttribute KARI = new AbilityAttribute("Kari").setAbilityCharges(7 * 20).setAbilityCooldown(15).setAbilityExplosion(10, false, false);
	public static AbilityAttribute RAIGO = new AbilityAttribute("Raigo").setAbilityCooldown(45).setProjectileTicks(256).setProjectileModel(ModelType.SPHERE).setProjectileColor("5D8AA8").setProjectileSize(50, 50, 50).setProjectileDamage(120).setProjectileExplosion(30, false).setProjectileCollisionSizes(2);
	public static AbilityAttribute VOLT_VARI = new AbilityAttribute("Volt Vari").setAbilityCooldown(3).setAbilityCharges(10 * 20, true);
	public static AbilityAttribute EL_THOR = new AbilityAttribute("El Thor").setAbilityCooldown(8).setAbilityCharges(6 * 20);
	public static AbilityAttribute SPARK_STEP = new AbilityAttribute("Spark Step").setAbilityCooldown(3);
	
	public static AbilityAttribute INJECTION_SHOT = new AbilityAttribute("Injection Shot").setAbilityCooldown(15);
	public static AbilityAttribute SHAMBLES = new AbilityAttribute("Shambles").setAbilityCooldown(8);
	public static AbilityAttribute TAKT = new AbilityAttribute("Takt").setAbilityCooldown(10).setAbilityPassive();
	public static AbilityAttribute GAMMA_KNIFE = new AbilityAttribute("Gamma Knife").setAbilityCooldown(30).setProjectileTicks(20).setProjectileModel(ModelType.CUBE).setProjectileColor("00AB66").setProjectileDamage(100).setProjectileSize(1, 1, 5);
	public static AbilityAttribute MES = new AbilityAttribute("Mes").setAbilityCooldown(5).setAbilityPassive().setAbilityPunch(1); 
	public static AbilityAttribute COUNTER_SHOCK = new AbilityAttribute("Counter Shock").setAbilityCooldown(10).setAbilityPassive().setAbilityPunch(40).setAbilityExplosion(1, false);
	public static AbilityAttribute ROOM = new AbilityAttribute("Room").setAbilityCooldown(1).setAbilityPassive(true);
	
	public static AbilityAttribute NORO_NORO_BEAM = new AbilityAttribute("Noro Noro Beam").setAbilityCooldown(5).setProjectileTicks(10).setProjectileModel(ModelType.NORO_NORO_BEAM).setProjectileTexture("noronorobeam").setProjectileSize(5, 5, 5).setProjectileSpeed(1.6F).setProjectileCollisionSizes(1);
	public static AbilityAttribute KYUBI_RUSH = new AbilityAttribute("Kyubi Rush").setAbilityCooldown(7).setAbilityPassive().setAbilityPunch();
	public static AbilityAttribute NORO_NORO_BEAM_SWORD = new AbilityAttribute("Noro Noro Beam Sword").setAbilityPassive(true);

	public static AbilityAttribute AIR_DOOR = new AbilityAttribute("Air Door").setAbilityPassive(true).setAbilityCooldown(40);
	public static AbilityAttribute DOOR = new AbilityAttribute("Door").setAbilityCooldown(8);

	public static AbilityAttribute SUKE_PUNCH = new AbilityAttribute("Suke Punch").setAbilityPassive().setAbilityPunch();
	public static AbilityAttribute SHISHA_NO_TE = new AbilityAttribute("Shisha no Te").setAbilityCooldown(3).setProjectileDamage(5).setProjectileModel(ModelType.CUBE).setProjectileSize(0, 0, 0).setProjectileExplosion(3, false);
	public static AbilityAttribute SKATTING = new AbilityAttribute("Skatting").addEffects(EffectType.USER, new EffectInstance(Effects.INVISIBILITY, 30, 5)).setAbilityPassive();  	
	 
	public static AbilityAttribute GEAR_SECOND = new AbilityAttribute("Gear Second").setAbilityCooldown(60).setAbilityPassive();
	public static AbilityAttribute GEAR_THIRD = new AbilityAttribute("Gear Third").setAbilityCooldown(90).setAbilityPassive();
	public static AbilityAttribute GEAR_FOURTH = new AbilityAttribute("Gear Fourth").setAbilityCooldown(300).setAbilityPassive();
	public static AbilityAttribute GOMU_GOMU_NO_ROCKET = new AbilityAttribute("Gomu Gomu no Rocket").setProjectileModel(ModelType.FIST).setProjectileTexture("gomugomunopistol").setProjectileSize(3, 3, 3).setModelOffsets(0, 1, 0).setProjectileDamage(6).setAbilityCooldown(8).setProjectilePhysical().setProjectileSpeed(2.5F);
	public static AbilityAttribute GOMU_GOMU_NO_BAZOOKA = new AbilityAttribute("Gomu Gomu no Bazooka").setAbilityCharges(10);
	public static AbilityAttribute GOMU_GOMU_NO_GATLING = new AbilityAttribute("Gomu Gomu no Gatling").setProjectileTicks(10);
	public static AbilityAttribute GOMU_GOMU_NO_PISTOL = new AbilityAttribute("Gomu Gomu no Pistol");
	
	public static AbilityAttribute FLASH = new AbilityAttribute("Flash").setAbilityCooldown(5).addEffects(EffectType.AOE, new EffectInstance(Effects.BLINDNESS, 7 * 20, 3), new EffectInstance(Effects.SLOWNESS, 7 * 20, 1)).setEffectRadius(10);
	public static AbilityAttribute AMA_NO_MURAKUMO = new AbilityAttribute("Ama no Murakumo").setAbilityPassive(true);
	public static AbilityAttribute AMATERASU = new AbilityAttribute("Amaterasu").setAbilityCooldown(15).setProjectileTicks(150).setProjectileModel(ModelType.CUBE).setProjectileSize(1, 1, 1).setProjectileColor("FFFF00").setProjectileSpeed(5).setProjectileDamage(35).setProjectileExplosion(6, false).setAbilityCharges(2 * 20);
	public static AbilityAttribute YASAKANI_NO_MAGATAMA = new AbilityAttribute("Yasakani no Magatama").setAbilityCooldown(2.5).setProjectileModel(ModelType.SPHERE).setProjectileSize(.5, .5, .5).setProjectileColor("FFFF00").setAbilityRepeater(2).setProjectileDamage(2).setProjectileExplosion(1, false).setProjectileSpeed(5);
	public static AbilityAttribute YATA_NO_KAGAMI = new AbilityAttribute("Yata no Kagami").setAbilityCooldown(4);
	 
	public static AbilityAttribute SPRING_DEATH_KNOCK = new AbilityAttribute("Spring Death Knock").setAbilityCooldown(6).setProjectileDamage(20).setProjectileModel(ModelType.FIST).setProjectileTexture("springdeathknock").setModelOffsets(0, 1.5, 0).setProjectileSize(7, 5, 5).setProjectileTicks(3).setProjectilePhysical();
	public static AbilityAttribute SPRING_SNIPE = new AbilityAttribute("Spring Snipe").setAbilityCooldown(5).setAbilityCharges(20);
	public static AbilityAttribute SPRING_HOPPER = new AbilityAttribute("Spring Hopper").setAbilityCooldown(0.6).setAbilityCharges(10);

	public static AbilityAttribute ICE_TIME_CAPSULE = new AbilityAttribute("Ice Time Capsule").setAbilityCooldown(15);
	public static AbilityAttribute ICE_SABER = new AbilityAttribute("Ice Saber").setAbilityPassive(true);
	public static AbilityAttribute ICE_BALL = new AbilityAttribute("Ice Ball").setAbilityCooldown(6).setProjectileDamage(5).setProjectileModel(ModelType.SPHERE).setProjectileColor("00FFFF").setProjectileSize(5, 5, 5).addEffects(EffectType.PROJECTILE, new EffectInstance(Effects.SLOWNESS, 100, 0), new EffectInstance(Effects.MINING_FATIGUE, 100, 0));
	public static AbilityAttribute ICE_AGE = new AbilityAttribute("Ice Age").setAbilityCooldown(15).addEffects(EffectType.AOE, new EffectInstance(Effects.SLOWNESS, 200, 100), new EffectInstance(Effects.MINING_FATIGUE, 200, 100)).setEffectRadius(20);
	public static AbilityAttribute ICE_BLOCK_PARTISAN = new AbilityAttribute("Ice Block : Partisan").setAbilityCooldown(7).setProjectileDamage(10).setProjectileModel(ModelType.TRIDENT).setProjectileTexture("iceblockpartisan").setProjectileSize(1.5, 1.5, 1.5).setModelOffsets(0, 1.0, 0).setAbilityRepeater().addEffects(EffectType.PROJECTILE, new EffectInstance(Effects.SLOWNESS, 100, 0), new EffectInstance(Effects.MINING_FATIGUE, 100, 0));
	public static AbilityAttribute ICE_BLOCK_PHEASANT = new AbilityAttribute("Ice Block : Pheasant").setAbilityCooldown(20).setProjectileDamage(45).setProjectileModel(ModelType.PHEASANT).setProjectileTexture("iceblockpheasant").setProjectileSize(5, 5, 5).setModelOffsets(0, 2.5, 0).addEffects(EffectType.PROJECTILE, new EffectInstance(Effects.SLOWNESS, 200, 100), new EffectInstance(Effects.MINING_FATIGUE, 200, 100)).setProjectileCollisionSizes(1.75, 1.5, 1.75);

	public static AbilityAttribute ENJOMO  = new AbilityAttribute("Enjomo").setAbilityCooldown(10);
	public static AbilityAttribute JUJIKA = new AbilityAttribute("Jujika").setAbilityCooldown(6).setProjectileDamage(5).setProjectileModel(ModelType.SPHERE).setProjectileColor("FF0000").setProjectileSize(.2, .2, .2);
	public static AbilityAttribute HIDARUMA = new AbilityAttribute("Hidaruma").setAbilityCooldown(6).setProjectileModel(ModelType.CUBE).setProjectileSize(0, 0, 0);
	public static AbilityAttribute DAI_ENKAI_ENTEI = new AbilityAttribute("Dai Enkai : Entei").setAbilityCooldown(25).setProjectileModel(ModelType.SPHERE).setProjectileDamage(45).setProjectileColor("FF0000").setProjectileSize(9, 9, 9).setProjectileExplosion(7).setAbilityCharges(4 * 20).setProjectileCollisionSizes(2);
	public static AbilityAttribute HIGAN = new AbilityAttribute("Higan").setAbilityCooldown(4).setProjectileModel(ModelType.CUBE).setProjectileDamage(5).setProjectileColor("FF0000").setProjectileSize(.3, .3, .3).setAbilityRepeater(4);
	public static AbilityAttribute HIKEN = new AbilityAttribute("Hiken").setAbilityCooldown(8).setProjectileModel(ModelType.FIST).setProjectileTexture("hiken").setModelOffsets(0, 0.5, 0).setProjectileDamage(20).setProjectileSize(1.5, 1.5, 1.5).setProjectileExplosion(2);

	public static AbilityAttribute GREAT_CAGE = new AbilityAttribute("Great Cage").setAbilityCooldown(20);
	public static AbilityAttribute PRISON_BREAK = new AbilityAttribute("Prison Break").setAbilityCooldown(3).setAbilityPassive();
	public static AbilityAttribute AWASE_BAORI = new AbilityAttribute("Awase Baori").setAbilityCooldown(12).setProjectileModel(ModelType.SPHERE).setProjectileColor("000000").setProjectileSize(7, 7, 7);
	public static AbilityAttribute BIND = new AbilityAttribute("Bind").setAbilityCooldown(10).setAbilityPassive().setAbilityPunch();

	public static AbilityAttribute SORU = new AbilityAttribute("Soru").addEffects(EffectType.USER, new EffectInstance(Effects.SPEED, 30, 6)).setAbilityPassive();
	public static AbilityAttribute TEKKAI = new AbilityAttribute("Tekkai").setAbilityCooldown(10).addEffects(EffectType.USER, new EffectInstance(Effects.RESISTANCE, 30, 100), new EffectInstance(Effects.SLOWNESS, 30, 100), new EffectInstance(Effects.MINING_FATIGUE, 30, 5), new EffectInstance(Effects.JUMP_BOOST, 30, -100)).setAbilityPassive();
	public static AbilityAttribute GEPPO = new AbilityAttribute("Geppo").setAbilityCooldown(0.9);
	public static AbilityAttribute RANKYAKU = new AbilityAttribute("Rankyaku").setAbilityCooldown(9).setProjectileTicks(100).setProjectileModel(ModelType.CUBE).setProjectileSize(6, 0.4, 1.5).setProjectileColor("69E3FF").setProjectileDamage(20).setProjectileExplosion(2, false).setProjectileCollisionSizes(1.5, 0.3, 1.5).setProjectileMoveThroughBlocks(true);
	public static AbilityAttribute SHIGAN = new AbilityAttribute("Shigan").setAbilityCooldown(5).setAbilityPassive().setAbilityPunch();
	public static AbilityAttribute KAMI_E = new AbilityAttribute("Kamie").setAbilityCooldown(10).addEffects(EffectType.USER, new EffectInstance(Effects.RESISTANCE, 20, 100)).setAbilityPassive();

	public static AbilityAttribute HOT_BOILING_SPECIAL = new AbilityAttribute("Hot Boiling Special").setAbilityPassive().setAbilityPunch(10).setAbilityCooldown(7);
	public static AbilityAttribute EVAPORATE = new AbilityAttribute("Evaporate").setAbilityCooldown(15);

	public static AbilityAttribute WEIGHTLESS = new AbilityAttribute("Weightless").setAbilityPassive(true);
	public static AbilityAttribute KICK_OFF_JUMP = new AbilityAttribute("Kick Off Jump").setAbilityPassive().setAbilityCooldown(4);
	public static AbilityAttribute HEAVY_PUNCH = new AbilityAttribute("Heavy Punch").setAbilityCooldown(20).setAbilityPassive().setAbilityPunch(20);
	public static AbilityAttribute KILO_PRESS = new AbilityAttribute("Kilo Press").setAbilityCooldown(10).setAbilityPassive();

	public static AbilityAttribute RUST_TOUCH = new AbilityAttribute("Rust Touch").setAbilityCooldown(19).setAbilityPunch().setAbilityPassive();

	public static AbilityAttribute UCHIMIZU = new AbilityAttribute("Uchimizu").setAbilityCooldown(5).setProjectileModel(ModelType.CUBE).setProjectileColor("00CED1").setProjectileSize(.5, .5, 1).setProjectileDamage(5).setAbilityRepeater(3, 2);
	public static AbilityAttribute MURASAME = new AbilityAttribute("Murasame").setAbilityCooldown(8).setProjectileModel(ModelType.SHARK).setProjectileTexture("murasame").setProjectileSize(.8, .8, 1.2).setProjectileDamage(25);
	public static AbilityAttribute KACHIAGE_HAISOKU = new AbilityAttribute("Kachiage Haisoku").setAbilityCooldown(15).setAbilityPassive().setAbilityPunch();
	public static AbilityAttribute SAMEHADA_SHOTEI = new AbilityAttribute("Samehada Shotei").addEffects(EffectType.USER, new EffectInstance(Effects.RESISTANCE, 10, 120), new EffectInstance(Effects.SLOWNESS, 10, 120), new EffectInstance(Effects.JUMP_BOOST, 30, -100)).setAbilityPassive();	
	public static AbilityAttribute KARAKUSAGAWARA_SEIKEN = new AbilityAttribute("Karakusagawara Seiken").setAbilityCooldown(25);
	
	public static AbilityAttribute KAEN_BOSHI = new AbilityAttribute("Kaen Boshi").setAbilityCooldown(10).setAbilityPassive().setProjectileModel(ModelType.SPHERE).setProjectileDamage(8).setProjectileSize(.5, .5, .5).setProjectileTicks(100).setProjectileColor("#D3D3D3");
	public static AbilityAttribute KEMURI_BOSHI = new AbilityAttribute("Kemuri Boshi").setAbilityCooldown(10).setAbilityPassive().setProjectileModel(ModelType.SPHERE).setProjectileDamage(6).setProjectileSize(.5, .5, .5).setProjectileTicks(100).setProjectileColor("#8741A8");
	public static AbilityAttribute RENPATSU_NAMARI_BOSHI = new AbilityAttribute("Renpatsu Namari Boshi").setAbilityCooldown(15).setAbilityPassive().setProjectileModel(ModelType.SPHERE).setProjectileDamage(6).setProjectileSize(.5, .5, .5).setProjectileTicks(100).setProjectileExplosion(1, false).setProjectileColor("#D3D3D3").setAbilityRepeater(10, 3);
	public static AbilityAttribute SAKURETSU_SABOTEN_BOSHI = new AbilityAttribute("Sakuretsu Saboten Boshi").setAbilityCooldown(12).setAbilityPassive().setProjectileModel(ModelType.SPHERE).setProjectileDamage(12).setProjectileExplosion(2, false, false).setProjectileSize(.5, .5, .5).setProjectileTicks(100).setProjectileColor("#D3D3D3");
	
	public static AbilityAttribute SHI_SHISHI_SONSON = new AbilityAttribute("Shi Shishi Sonson").setAbilityCooldown(7);
	public static AbilityAttribute SANBYAKUROKUJU_POUND_HO = new AbilityAttribute("Sanbyakurokuju Pound Ho").setAbilityCooldown(5).setProjectileTicks(100).setProjectileModel(ModelType.CUBE).setProjectileSize(6, 0.4, 1.5).setProjectileColor("bbf7b4").setProjectileDamage(18).setProjectileExplosion(3, false);
	public static AbilityAttribute YAKKODORI = new AbilityAttribute("Yakkodori").setAbilityCooldown(3).setAbilityCooldown(5).setProjectileTicks(20).setProjectileModel(ModelType.CUBE).setProjectileSize(0.4, 6, 0.4).setProjectileColor("bbf7b4").setProjectileDamage(10).setProjectileExplosion(1, false).setProjectileMoveThroughBlocks(true);
	public static AbilityAttribute O_TATSUMAKI = new AbilityAttribute("O Tatsumaki").setAbilityCooldown(7);
		
	//public static AbilityAttribute MURASAME = new AbilityAttribute("Murasame").setAbilityCooldown(250).setProjectileModel(ModelType.CUBE).setProjectileColor("00CED1").setProjectileSize(1.8, 1, 1).setProjectileDamage(15).setAbilityRepeater();
	//public static AbilityAttribute YARINAMI = new AbilityAttribute("Yarinami").setAbilityCooldown(150).setProjectileModel(ModelType.CUBE).setProjectileColor("00CED1").setProjectileSize(3, 1, 1).setProjectileDamage(15);
	
	public static AbilityAttribute FRESH_FIRE = new AbilityAttribute("Fresh Fire").setAbilityCooldown(1.5).setProjectileTicks(7).setProjectileSize(.01, .01, .01).setProjectileDamage(1);
	public static AbilityAttribute COLA_OVERDRIVE = new AbilityAttribute("Cola Overdrive").setAbilityCooldown(7).addEffects(EffectType.USER, new EffectInstance(Effects.SPEED, 200, 0), new EffectInstance(Effects.STRENGTH, 200, 1));
	public static AbilityAttribute RADICAL_BEAM = new AbilityAttribute("Radical Beam").setAbilityCooldown(10).setProjectileModel(ModelType.CUBE).setProjectileColor("FFFF00").setProjectileSize(.5, .5, 1).setProjectileDamage(25).setProjectileExplosion(4, false);
	public static AbilityAttribute STRONG_RIGHT = new AbilityAttribute("Strong Right").setAbilityCooldown(2.5).setProjectileModel(ModelType.CUBE).setProjectileColor("F5DEB3").setProjectileTicks(5).setProjectileSize(1, 1, 1.5).setProjectileDamage(20);
	public static AbilityAttribute COUP_DE_VENT = new AbilityAttribute("Coup de Vent").setAbilityCooldown(10).setProjectileTicks(7).setProjectileSize(.01, .01, .01).setProjectileDamage(10).setAbilityCharges(30);
	
	public static AbilityAttribute KENBUNSHOKU_HAKI = new AbilityAttribute("Kenbunshoku Haki").setAbilityPassive(true);
	public static AbilityAttribute BUSOSHOKU_HAKI = new AbilityAttribute("Busoshoku Haki").setAbilityPassive(true);
	public static AbilityAttribute HAOSHOKU_HAKI = new AbilityAttribute("Haoshoku Haki");

	public static AbilityAttribute NULL = new AbilityAttribute("n/a");
}