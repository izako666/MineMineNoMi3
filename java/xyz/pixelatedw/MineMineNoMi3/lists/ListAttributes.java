package xyz.pixelatedw.MineMineNoMi3.lists;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.ModelCube;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.ModelSphere;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.EffectType;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelArrow;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelBrickBat;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelFist;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelHeart;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelHydra;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelMeigo;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelMiniHollow;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelNegativeHollow;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelNoroNoroBeam;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelPaw;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelPheasant;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelShark;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelSpear;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelTokuHollow;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelTrident;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models.ModelYukiRabi;

import java.awt.*;

public class ListAttributes 
{
	
	public static AbilityAttribute MINIMININOFULLREBOUND = new AbilityAttribute("Mini Mini no Full Rebound").setAbilityCooldown(3).setAbilityPassive(true);
	public static AbilityAttribute YANAGIMOCHI = new AbilityAttribute("Yanagi Mochi").setAbilityCooldown(5).setAbilityDisplayName("Yanagi Mochi").setProjectileTicks(15).setProjectileDamage(10);
	public static AbilityAttribute KAKUMOCHI = new AbilityAttribute("Kaku Mochi").setAbilityCooldown(5).setAbilityDisplayName("Kaku Mochi").setProjectileModel(new ModelCube()).setProjectileSize(.5, .5, 1).setProjectileColor("#dee1e5").setProjectileDamage(8).setProjectileTicks(10);
	public static AbilityAttribute YAKIMOCHI = new AbilityAttribute("Yaki Mochi").setAbilityCooldown(8).setAbilityDisplayName("Yaki Mochi").setProjectileModel(new ModelCube()).setProjectileSize(.5, .5, 1).setProjectileColor("#dee1e5").setProjectileDamage(12).setProjectileExplosion(3, true);
    public static AbilityAttribute MOCHITSUKI = new AbilityAttribute("Mochi Tsuki").setAbilityCooldown(12).setAbilityDisplayName("Mochi Tsuki").setProjectileModel(new ModelCube()).setProjectileSize(.5, .5, 1).setProjectileColor("#dee1e5").setProjectileDamage(15).setProjectileTicks(80);
	public static AbilityAttribute NAGAREMOCHI = new AbilityAttribute("Nagare Mochi").setAbilityCooldown(12).setAbilityDisplayName("Nagare Mochi");
	public static AbilityAttribute KARADAMOCHI = new AbilityAttribute("Karado Mochi").setAbilityDisplayName("Karada Mochi").setAbilityCooldown(12).setAbilityPassive();
	public static AbilityAttribute BIGAN = new AbilityAttribute("Bigan").setAbilityCooldown(8).setProjectileDamage(20).setProjectileModel(new ModelCube()).setProjectileColor("#6D5E24").setProjectileSize(1, 1, 2.4).setProjectileTicks(10).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.weakness.id, 600, 0), new PotionEffect(Potion.moveSlowdown.id, 600, 2)).setModelOffsets(0, 1.5, 0);
	public static AbilityAttribute GIRAFFE_SPEEDPOINT = new AbilityAttribute("Giraffe Speed Point").setAbilityDisplayName("Speed Point").setAbilityTexture("giraffefull").setAbilityCooldown(1).setAbilityPassive(true);
	public static AbilityAttribute GIRAFFE_POWERPOINT = new AbilityAttribute("Giraffe Power Point").setAbilityDisplayName("Power Point").setAbilityTexture("giraffehybrid").setAbilityCooldown(1).setAbilityPassive(true);
	
	public static AbilityAttribute MOGURATONPO = new AbilityAttribute("Mogura Tonpo: Mogugyo").setAbilityCooldown(10).setAbilityTexture("moguratonpo");
	public static AbilityAttribute MOGURABANANA = new AbilityAttribute("Mogura Banana").setAbilityCooldown(12).setAbilityPunch(10);
	public static AbilityAttribute MOGU_POWERPOINT = new AbilityAttribute("Mogu Power Point").setAbilityDisplayName("Mole Point").setAbilityTexture("molepoint").setAbilityCooldown(1).setAbilityPassive(true);
	
	public static AbilityAttribute GANMENSEICHOHORMONE = new AbilityAttribute("Ganmen Seicho Hormone").setAbilityCooldown(5).setAbilityPunch();
	public static AbilityAttribute TENSIONHORMONE = new AbilityAttribute("Tension Hormone").setAbilityCooldown(40).setAbilityPunch();
	public static AbilityAttribute CHIYUHORMONE = new AbilityAttribute("Chiyu Hormone").setAbilityCooldown(20).setAbilityPunch();
	public static AbilityAttribute ONNAHORMONE = new AbilityAttribute("Onna Hormone").setAbilityCooldown(15).setAbilityPunch();
	
	public static AbilityAttribute CHIYUPOPO = new AbilityAttribute("Chiyupopo").setAbilityCooldown(10);
	public static AbilityAttribute HEALINGTOUCH = new AbilityAttribute("Healing Touch").setAbilityCooldown(15).setAbilityPunch();
	
	public static AbilityAttribute ZOU_HYBRIDPOINT = new AbilityAttribute("Zou Hybrid Point").setAbilityCooldown(1).setAbilityDisplayName("Hybrid Point").setAbilityTexture("zouhybrid").setAbilityPassive(true);
	public static AbilityAttribute ZOU_FULLPOINT = new AbilityAttribute("Zou Point").setAbilityTexture("zoufull").setAbilityCooldown(1).setAbilityPassive(true);
	public static AbilityAttribute IVORYDART = new AbilityAttribute("Ivory Dart").setAbilityCooldown(10);
	public static AbilityAttribute GREATSTOMP = new AbilityAttribute("Great Stomp").setAbilityCooldown(15);
	public static AbilityAttribute IVORYSTOMP = new AbilityAttribute("Ivory Stomp").setAbilityCooldown(8).setAbilityPunch();
	public static AbilityAttribute TRUNKSHOT = new AbilityAttribute("Trunk Shot").setAbilityCooldown(7).setProjectileModel(new ModelCube()).setProjectileTexture("zouskin").setProjectileSize(2.5, 2.5, 4).setProjectileColor("838993").setProjectileDamage(10).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.weakness.id, 5 * 20)).setProjectilePhysical();
	
	public static AbilityAttribute SOULPARADE = new AbilityAttribute("Soul Parade").addEffects(EffectType.USER, new PotionEffect(Potion.resistance.id, 30, 100), new PotionEffect(Potion.moveSlowdown.id, 30, 100)).setAbilityCooldown(5).setAbilityPassive();
	public static AbilityAttribute KASURIUTAFUBUKIGIRI = new AbilityAttribute("Kasuriuta: Fubuki Giri").setAbilityCooldown(10);
	
	public static AbilityAttribute BAKUBAKUFACTORY = new AbilityAttribute("Baku Baku Factory");
	public static AbilityAttribute BAKUTSUIHO = new AbilityAttribute("Baku Tsuiho").setAbilityCooldown(10).setAbilityCharges(10 * 20).setProjectileModel(new ModelCube()).setProjectileSize(2, 2, 2).setProjectileColor("E3E3E3").setProjectileDamage(8);
	public static AbilityAttribute BEROCANNON = new AbilityAttribute("Bero Cannon").setAbilityCooldown(5).setProjectileModel(new ModelCube()).setProjectileSize(2, 2, 2).setProjectileColor("E3E3E3").setProjectileDamage(4);
	public static AbilityAttribute BAKUMUNCH = new AbilityAttribute("Baku Munch").setAbilityCooldown(5);
	
	public static AbilityAttribute PHOENIX_HYBRIDPOINT = new AbilityAttribute("Phoenix Hybrid Point").setAbilityCooldown(1).setAbilityDisplayName("Hybrid Point").setAbilityTexture("phoenixhybrid").setAbilityPassive(true);
	public static AbilityAttribute PHOENIX_FULLPOINT = new AbilityAttribute("Phoenix Point").setAbilityTexture("phoenixfull").setAbilityCooldown(1).setAbilityPassive(true);
	public static AbilityAttribute BLUEFLAMESOFRESURRECTION = new AbilityAttribute("Blue Flames of Resurrection").setAbilityCooldown(20).addEffects(EffectType.USER, new PotionEffect(Potion.regeneration.id, 3 * 20, 4));
	public static AbilityAttribute FLAMEOFRESTORATION = new AbilityAttribute("Flame of Restoration").setAbilityCooldown(3).setAbilityPassive().setAbilityPunch();
	public static AbilityAttribute PHOENIXGOEN = new AbilityAttribute("Phoenix Goen").setAbilityCooldown(10).setProjectileDamage(12);
	public static AbilityAttribute TENSEINOSOEN = new AbilityAttribute("Tensei no Soen").setAbilityCooldown(30).setAbilityCharges(5 * 20);
	
	public static AbilityAttribute BISON_POWERPOINT = new AbilityAttribute("Bison Power Point").setAbilityCooldown(1).setAbilityDisplayName("Hybrid Point").setAbilityTexture("bisonhybrid").setAbilityPassive(true);
	public static AbilityAttribute BISON_SPEEDPOINT = new AbilityAttribute("Bison Speed Point").setAbilityCooldown(1).setAbilityDisplayName("Bison Point").setAbilityTexture("bisonfull").setAbilityPassive(true);	
	public static AbilityAttribute FIDDLEBANFF = new AbilityAttribute("Fiddle Banff").setAbilityCooldown(7);
	public static AbilityAttribute KOKUTEICROSS = new AbilityAttribute("Kokutei Cross").setAbilityCooldown(7).setAbilityPassive().setAbilityPunch();
	
	public static AbilityAttribute SAGARINORYUSEI = new AbilityAttribute("Sagari no Ryusei").setAbilityCooldown(20).setProjectileTicks(256).setProjectileModel(new ModelSphere()).setProjectileColor("51585B").setProjectileSize(50, 50, 50).setProjectileDamage(50).setProjectileExplosion(20, false).setProjectileCollisionSizes(2, 2, 2);
	public static AbilityAttribute MOKO = new AbilityAttribute("Moko").setProjectileDamage(10).setAbilityCooldown(12).setProjectileModel(new ModelCube()).setProjectileSize(new double[] {0, 0, 0}).setProjectileMoveThroughBlocks(true);
	public static AbilityAttribute ABAREHIMATSURI = new AbilityAttribute("Abare Himatsuri").setAbilityCooldown(10).setAbilityPassive();
	public static AbilityAttribute JURYOKU = new AbilityAttribute("Juryoku").setAbilityDisplayName("Jigoku Tabi").setAbilityCooldown(12).setAbilityPassive();
	
	public static AbilityAttribute BLACKWORLD = new AbilityAttribute("Black World").setAbilityCooldown(25).addEffects(EffectType.AOE, new PotionEffect(Potion.moveSlowdown.id, 200, 100), new PotionEffect(Potion.digSlowdown.id, 200, 100), new PotionEffect(Potion.blindness.id, 200, 2)).setEffectRadius(20);
	public static AbilityAttribute DARKMATTER = new AbilityAttribute("Dark Matter").setAbilityCooldown(12).setProjectileModel(new ModelSphere()).setProjectileColor("000000").setProjectileSize(7, 7, 7);
	public static AbilityAttribute KUROUZU = new AbilityAttribute("Kurouzu").setAbilityCooldown(10).setAbilityCharges(3 * 20);;
	public static AbilityAttribute LIBERATION = new AbilityAttribute("Liberation").setAbilityCooldown(5);
	public static AbilityAttribute BLACKHOLE = new AbilityAttribute("Black Hole").setAbilityCooldown(7);
	
	public static AbilityAttribute TODOROKI = new AbilityAttribute("Todoroki").setAbilityCooldown(20).setProjectileModel(new ModelCube()).setProjectileColor("#87CEFA").setProjectileSize(2, 2, 4).setProjectileDamage(15).setAbilityRepeater(20, 3);
	
	public static AbilityAttribute PERFUMEFEMUR = new AbilityAttribute("Perfume Femur").setAbilityCooldown(10).setAbilityPunch(10);
	public static AbilityAttribute SLAVEARROW = new AbilityAttribute("Slave Arrow").setAbilityCooldown(10).setProjectileModel(new ModelArrow()).setProjectileColor("#FF69B4").setProjectileSize(1, 1, 2).setProjectileDamage(6).setAbilityRepeater().setAbilityCharges(7 * 20);
	public static AbilityAttribute PISTOLKISS = new AbilityAttribute("Pistol Kiss").setAbilityCooldown(5).setProjectileModel(new ModelHeart()).setProjectileSize(.5, .5, .5).setProjectileTexture("meromeromellow").setProjectileDamage(4).setModelOffsets(0, -0.5, 0).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.moveSlowdown.id, 100, 5), new PotionEffect(Potion.digSlowdown.id, 100, 5));
	public static AbilityAttribute MEROMEROMELLOW = new AbilityAttribute("Mero Mero Mellow").setAbilityCooldown(20).setProjectileModel(new ModelHeart()).setProjectileSize(3, 3, 3).setProjectileTexture("meromeromellow").setProjectileDamage(10).setModelOffsets(0, -1, 0).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.moveSlowdown.id, 400, 100), new PotionEffect(Potion.digSlowdown.id, 400, 100)).setProjectileCollisionSizes(1.25, 1.25, 1.25);
	
	public static AbilityAttribute SPARKLINGDAISY = new AbilityAttribute("Sparkling Daisy").setAbilityCooldown(12);
	public static AbilityAttribute SPIRALHOLLOW = new AbilityAttribute("Spiral Hollow").setAbilityCooldown(10).setProjectileDamage(20).setProjectileModel(new ModelCube()).setProjectileColor("#F8F8FF").setProjectileSize(3, 3, 5).setProjectileTicks(3).setProjectilePhysical();
	public static AbilityAttribute ATOMICSPURT = new AbilityAttribute("Atomic Spurt").setAbilityCooldown(25).setAbilityPassive();
	public static AbilityAttribute SPARCLAW = new AbilityAttribute("Spar Claw").setAbilityCooldown(5).setAbilityPassive(true).setAbilityPunch(10);
	public static AbilityAttribute SPIDER = new AbilityAttribute("Spider").addEffects(EffectType.USER, new PotionEffect(Potion.resistance.id, 30, 100), new PotionEffect(Potion.moveSlowdown.id, 30, 100), new PotionEffect(Potion.digSlowdown.id, 30, 5), new PotionEffect(Potion.jump.id, 30, -100)).setAbilityPassive();
	
	public static AbilityAttribute NEGATIVEHOLLOW = new AbilityAttribute("Negative Hollow").setAbilityCooldown(6).setProjectileModel(new ModelNegativeHollow()).setProjectileTexture("negativehollow").setProjectileAlpha(150).setProjectileSize(2, 2, 2).setProjectileDamage(10).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.confusion.id, 200, 1), new PotionEffect(Potion.moveSlowdown.id, 200, 1));
	public static AbilityAttribute MINIHOLLOW = new AbilityAttribute("Mini Hollow").setAbilityCooldown(3).setProjectileModel(new ModelMiniHollow()).setProjectileSize(0.4, 0.4, 0.4).setProjectileColor("#F8F8FF").setProjectileAlpha(150).setProjectileDamage(2).setProjectileExplosion(2, false).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.confusion.id, 120, 0), new PotionEffect(Potion.moveSlowdown.id, 120, 0)).setAbilityRepeater();
	public static AbilityAttribute TOKUHOLLOW = new AbilityAttribute("Toku Hollow").setAbilityCooldown(10).setProjectileModel(new ModelTokuHollow()).setProjectileTexture("tokuhollow").setProjectileAlpha(150).setProjectileSize(4, 4, 4).setProjectileDamage(10).setProjectileExplosion(7, false).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.confusion.id, 400, 1), new PotionEffect(Potion.moveSlowdown.id, 400, 1)).setProjectileCollisionSizes(1.5, 1.5, 1.5);
	
	public static AbilityAttribute BLACKKNIGHT = new AbilityAttribute("Black Knight").setAbilityCooldown(15).setAbilityPassive();
	public static AbilityAttribute KUMONOSUGAKI = new AbilityAttribute("Kumo no Sugaki").setAbilityCooldown(10).addEffects(EffectType.USER,new PotionEffect(Potion.resistance.id, 30, 100), new PotionEffect(Potion.moveSlowdown.id, 30, 100), new PotionEffect(Potion.digSlowdown.id, 30, 5), new PotionEffect(Potion.jump.id, 30, -100)).setAbilityPassive();;
	public static AbilityAttribute TORIKAGO = new AbilityAttribute("Torikago").setAbilityCooldown(2).setAbilityPassive(true);
	public static AbilityAttribute TAMAITO = new AbilityAttribute("Tamaito").setAbilityCooldown(5).setProjectileDamage(6).setProjectileModel(new ModelCube()).setProjectileSize(.5, .5, 1).setProjectileColor("#dee1e5");
	public static AbilityAttribute OVERHEAT = new AbilityAttribute("Overheat").setAbilityCooldown(8).setProjectileDamage(20).setProjectileModel(new ModelCube()).setProjectileSize(1, 1, 5).setProjectileExplosion(3, false).setProjectileColor("#f77c25");
	public static AbilityAttribute SORANOMICHI = new AbilityAttribute("Sora no Michi").setAbilityCooldown(1);
	public static AbilityAttribute PARASITE = new AbilityAttribute("Parasite").setAbilityCooldown(5);
	
	public static AbilityAttribute BARRIERBILITYSTAIRS = new AbilityAttribute("Barrierbility Stairs").setProjectileTicks(60).setProjectileModel(new ModelCube()).setProjectileSize(0, 0, 0).setProjectileMoveThroughBlocks(true).setAbilityPassive();
	public static AbilityAttribute BARIBARINOPISTOL = new AbilityAttribute("Bari Bari no Pistol").setAbilityCooldown(5).setAbilityPassive().setAbilityPunch();
	public static AbilityAttribute BARRIERBALL = new AbilityAttribute("Barrier Ball").setAbilityCooldown(0.5).setAbilityPassive();
	public static AbilityAttribute BARRIERCRASH = new AbilityAttribute("Barrier Crash").setAbilityCooldown(5).setProjectileTicks(60).setProjectileModel(new ModelCube()).setProjectileColor("#87CEFA").setProjectileSize(9, 9, .3).setProjectileDamage(15).setProjectileCollisionSizes(1.5, 2, 1.5);
	public static AbilityAttribute BARRIER = new AbilityAttribute("Barrier").setAbilityPassive();
	
	public static AbilityAttribute YUKIGAKI = new AbilityAttribute("Yuki Gaki").setAbilityCooldown(8);
	public static AbilityAttribute FUBUKI = new AbilityAttribute("Fubuki").setAbilityCooldown(12);
	public static AbilityAttribute TABIRAYUKI = new AbilityAttribute("Tabira Yuki").setAbilityPassive(true);
//	public static AbilityAttribute MANNENYUKI = new AbilityAttribute("Mannen Yuki");
	public static AbilityAttribute KAMAKURAJUSSOSHI = new AbilityAttribute("Kamakura Jussoshi").setAbilityCooldown(18);
	public static AbilityAttribute YUKIRABI = new AbilityAttribute("Yuki Rabi").setAbilityCooldown(2).setProjectileColor(Color.WHITE).setProjectileDamage(5).setProjectileModel(new ModelYukiRabi()).setProjectileTexture("yukirabi").setProjectileSize(1, 1, 1).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.moveSlowdown.id, 50, 1)).setAbilityRepeater(2, 2);
	public static AbilityAttribute KAMAKURA = new AbilityAttribute("Kamakura").setAbilityCooldown(6);
	
//	public static AbilityAttribute SHINOKUNI = new AbilityAttribute("Shinokuni");
	public static AbilityAttribute KARAKUNI = new AbilityAttribute("Karakuni").setAbilityCooldown(20);
	public static AbilityAttribute BLUESWORD = new AbilityAttribute("Blue Sword").setAbilityPassive(true);
	public static AbilityAttribute GASTANET = new AbilityAttribute("Gastanet").setAbilityCooldown(6).setAbilityExplosion(5, false);
	public static AbilityAttribute GASTILLE = new AbilityAttribute("Gastille").setAbilityCooldown(7).setProjectileSpeed(2).setProjectileDamage(10).setProjectileModel(new ModelCube()).setProjectileColor("324AB2").setProjectileSize(1, 1, 2).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.poison.id, 200, 1)).setAbilityRepeater().setProjectileExplosion(1, false);
	public static AbilityAttribute GASROBE = new AbilityAttribute("Gas Robe").setAbilityCooldown(6).setProjectileSpeed(2).setProjectileDamage(10).setProjectileModel(new ModelCube()).setProjectileSize(0, 0, 0).setAbilityRepeater();
	
	public static AbilityAttribute DOKUGUMO = new AbilityAttribute("Doku Gumo").setAbilityCooldown(30).setAbilityPassive();
	public static AbilityAttribute DOKUFUGU = new AbilityAttribute("Doku Fugu").setAbilityCooldown(8).setProjectileDamage(15).setProjectileModel(new ModelSphere()).setProjectileColor("A020F0").setProjectileSize(5, 5, 5).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.poison.id, 240, 1)).setAbilityRepeater(10, 3);
	public static AbilityAttribute VENOMDEMON = new AbilityAttribute("Venom Demon").setAbilityCooldown(40).setAbilityPassive(true);
	public static AbilityAttribute VENOMROAD = new AbilityAttribute("Venom Road").setAbilityCooldown(8).setProjectileDamage(20).setProjectileModel(new ModelHydra()).setProjectileTexture("hydra").setProjectileSize(2, 2, 2.4).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.poison.id, 500, 1));
	public static AbilityAttribute CHLOROBALL = new AbilityAttribute("Chloro Ball").setAbilityCooldown(6).setProjectileDamage(10).setProjectileModel(new ModelSphere()).setProjectileColor("A020F0").setProjectileSize(5, 5, 5).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.poison.id, 240, 1));
	public static AbilityAttribute HYDRA = new AbilityAttribute("Hydra").setAbilityCooldown(8).setProjectileDamage(30).setProjectileModel(new ModelHydra()).setProjectileTexture("hydra").setProjectileSize(2, 2, 2.4).setProjectileTicks(10).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.poison.id, 500, 1));
	
	public static AbilityAttribute CANDLELOCK = new AbilityAttribute("Candle Lock").setAbilityCooldown(15).setProjectileDamage(6).setProjectileModel(new ModelCube()).setProjectileColor("A2ADD0").setProjectileSize(.5, .5, 1).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.moveSlowdown.id, 400, 100), new PotionEffect(Potion.digSlowdown.id, 400, 100), new PotionEffect(Potion.jump.id, 400, -1));
	public static AbilityAttribute CANDLEHOUSE = new AbilityAttribute("Candle House").setAbilityCooldown(30);
	public static AbilityAttribute CANDLEWALL = new AbilityAttribute("Candle Wall").setAbilityCooldown(4);
	public static AbilityAttribute DORUDORUARTSKEN = new AbilityAttribute("Doru Doru Arts : Ken").setAbilityPassive(true);
	public static AbilityAttribute DORUDORUARTSMORI = new AbilityAttribute("Doru Doru Arts : Mori").setAbilityCooldown(3).setProjectileDamage(15).setProjectileModel(new ModelSpear()).setProjectileTexture("dorudoruartsmori").setProjectileSize(2, 2, 2).setModelOffsets(0, 1, 0);
	
	public static AbilityAttribute BAKURETSUKAZAN = new AbilityAttribute("Bakuretsu Kazan").setAbilityCooldown(15);
	public static AbilityAttribute RYUSEIKAZAN = new AbilityAttribute("Ryusei Kazan").setAbilityCooldown(12).setProjectileDamage(10).setAbilityRepeater();
	public static AbilityAttribute MEIGO = new AbilityAttribute("Meigo").setAbilityCooldown(10).setProjectileDamage(40).setProjectileModel(new ModelMeigo()).setProjectileTexture("meigo").setProjectileSize(4, 4, 4).setModelOffsets(0, 1.2, 0).setProjectileTicks(3);
	public static AbilityAttribute DAIFUNKA = new AbilityAttribute("Dai Funka").setAbilityCooldown(8).setProjectileDamage(20).setProjectileModel(new ModelFist()).setProjectileTexture("daifunka").setProjectileSize(4, 4, 4).setModelOffsets(0, 1, 0);
	
	public static AbilityAttribute DESERTGIRASOLE = new AbilityAttribute("Desert Girasole").setAbilityCooldown(25).setAbilityCharges(100);
	public static AbilityAttribute DESERTENCIERRO = new AbilityAttribute("Desert Encierro").setAbilityCooldown(10).setAbilityPunch().addEffects(EffectType.HIT, new PotionEffect(Potion.hunger.id, 100, 1), new PotionEffect(Potion.weakness.id, 100, 1), new PotionEffect(Potion.moveSlowdown.id, 100, 1), new PotionEffect(Potion.digSlowdown.id, 100, 1));
	public static AbilityAttribute GROUNDDEATH = new AbilityAttribute("Ground Death").setAbilityCooldown(15);
	public static AbilityAttribute BARJAN = new AbilityAttribute("Barjan").setAbilityCooldown(5).setProjectileDamage(15).setProjectileModel(new ModelCube()).setProjectileColor("967117").setProjectileSize(6, 0.4, 1.5).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.hunger.id, 500, 1)).setProjectileMoveThroughBlocks(true).setProjectileCollisionSizes(1.25, 0.3, 1.25);
	public static AbilityAttribute SABLES = new AbilityAttribute("Sables").setAbilityCooldown(3).setAbilityPassive().setAbilityPunch();
	public static AbilityAttribute DESERTSPADA = new AbilityAttribute("Desert Spada").setAbilityCooldown(10);
	
	public static AbilityAttribute TSUNOTOKAGE = new AbilityAttribute("Tsuno-Tokage").setAbilityCooldown(10);
//	public static AbilityAttribute SHADOWSASGARD = new AbilityAttribute("Shadow's Asgard").setAbilityCooldown(400).addTasks(Tasks.shadowsasgard);
	public static AbilityAttribute BLACKBOX = new AbilityAttribute("Black Box").setAbilityCooldown(6).setProjectileModel(new ModelCube()).setProjectileColor(Color.black).setProjectileSize(2, 2, 2);
	public static AbilityAttribute KAGEMUSHA = new AbilityAttribute("Kagemusha").setAbilityCooldown(5);
	public static AbilityAttribute DOPPELMAN = new AbilityAttribute("Doppelman").setAbilityCooldown(15).setAbilityPassive();
	public static AbilityAttribute BRICKBAT = new AbilityAttribute("Brick Bat").setAbilityCooldown(4).setProjectileDamage(5).setProjectileModel(new ModelBrickBat()).setProjectileSize(1, 1, 1).setModelOffsets(0, 0.5, 0).setProjectileTexture("brickbat").setAbilityRepeater(3, 2);
	
	public static AbilityAttribute GEKISHIN = new AbilityAttribute("Gekishin").setAbilityCooldown(15).setAbilityPassive().setAbilityPunch();
	public static AbilityAttribute SHIMAYURASHI = new AbilityAttribute("Shima Yurashi").setAbilityCooldown(15).setProjectileDamage(20).setProjectileExplosion(5, false).setProjectileMoveThroughBlocks(true);
	public static AbilityAttribute KABUTOWARI = new AbilityAttribute("Kabutowari").setAbilityCooldown(7).setAbilityExplosion(5, false);
	public static AbilityAttribute KAISHIN = new AbilityAttribute("Kaishin").setAbilityCooldown(7).setProjectileDamage(50).setProjectileModel(new ModelCube()).setProjectileSize(0, 0, 0).setProjectileExplosion(3, false, false);
	
	public static AbilityAttribute KICKBOMB = new AbilityAttribute("Kick Bomb").setAbilityCooldown(7).setAbilityExplosion(7, false);
	public static AbilityAttribute NOSEFANCYCANNON = new AbilityAttribute("Nose Fancy Cannon").setAbilityCooldown(3).setProjectileModel(new ModelCube()).setProjectileColor("3D2B1F").setProjectileSize(.4, .4, .8).setProjectileDamage(10).setProjectileExplosion(3, false);
	
	public static AbilityAttribute URSUSSHOCK = new AbilityAttribute("Ursus Shock").setAbilityCooldown(15).setProjectileModel(new ModelPaw()).setProjectileColor("F8F8FF").setProjectileAlpha(80).setProjectileSize(3.5, 3.5, 3.5).setProjectileDamage(50).setProjectileExplosion(8, false, true).setAbilityCharges(40).setProjectileCollisionSizes(1.5, 1.5, 1.5);
	public static AbilityAttribute PADHO = new AbilityAttribute("Pad Ho").setAbilityCooldown(8).setProjectileModel(new ModelPaw()).setProjectileColor("F8F8FF").setProjectileAlpha(80).setProjectileSize(1, 1, 1).setProjectileDamage(10).setProjectileExplosion(1, false, true);
	public static AbilityAttribute TSUPPARIPADHO = new AbilityAttribute("Tsuppari Pad Ho").setAbilityCooldown(10).setProjectileDamage(15).setProjectileExplosion(1, false, true).setAbilityRepeater(5, 7);
	public static AbilityAttribute HANPATSU = new AbilityAttribute("Hanpatsu").setAbilityCooldown(4).setAbilityPassive().setAbilityPunch();

	public static AbilityAttribute WHITESTRIKE = new AbilityAttribute("White Strike").setAbilityCooldown(35).addEffects(EffectType.AOE, new PotionEffect(Potion.moveSlowdown.id, 300, 1), new PotionEffect(Potion.digSlowdown.id, 300, 1), new PotionEffect(Potion.jump.id, 300, -10)).setEffectRadius(30);
	public static AbilityAttribute WHITELAUNCHER = new AbilityAttribute("White Launcher").setAbilityCooldown(5).setAbilityCharges(20);
	public static AbilityAttribute WHITESNAKE = new AbilityAttribute("White Snake").setAbilityCooldown(5).setProjectileTicks(120).setProjectileModel(new ModelCube()).setProjectileSpeed(5).setProjectileSize(0, 0, 0).setProjectileDamage(30).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.poison.id, 120, 0));
	public static AbilityAttribute WHITEOUT = new AbilityAttribute("White Out").setAbilityCooldown(4).setProjectileModel(new ModelCube()).setProjectileSpeed(5).setProjectileSize(0, 0, 0).setProjectileDamage(5).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.moveSlowdown.id, 240, 1), new PotionEffect(Potion.digSlowdown.id, 240, 1), new PotionEffect(Potion.jump.id, 240, -10));
	
	public static AbilityAttribute SANGO = new AbilityAttribute("Sango").setAbilityCooldown(10).setProjectileTicks(128).setProjectileModel(new ModelCube()).setProjectileSize(0, 0, 0).setProjectileColor("7CB9E8").setProjectileDamage(15).setAbilityRepeater();
	public static AbilityAttribute KARI = new AbilityAttribute("Kari").setAbilityCharges(7 * 20).setAbilityCooldown(15).setAbilityExplosion(10, false, false);
	public static AbilityAttribute RAIGO = new AbilityAttribute("Raigo").setAbilityCooldown(45).setProjectileTicks(256).setProjectileModel(new ModelSphere()).setProjectileColor("5D8AA8").setProjectileSize(50, 50, 50).setProjectileDamage(120).setProjectileExplosion(30, false).setProjectileCollisionSizes(2);
	public static AbilityAttribute VOLTVARI = new AbilityAttribute("Volt Vari").setAbilityCooldown(3).setAbilityCharges(10 * 20, true);
	public static AbilityAttribute ELTHOR = new AbilityAttribute("El Thor").setAbilityCooldown(8).setAbilityCharges(6 * 20);
	public static AbilityAttribute SPARKSTEP = new AbilityAttribute("Spark Step").setAbilityCooldown(3);
	
	public static AbilityAttribute INJECTIONSHOT = new AbilityAttribute("Injection Shot").setAbilityCooldown(15);
	public static AbilityAttribute SHAMBLES = new AbilityAttribute("Shambles").setAbilityCooldown(8);
	public static AbilityAttribute TAKT = new AbilityAttribute("Takt").setAbilityCooldown(10).setAbilityPassive();
	public static AbilityAttribute GAMMAKNIFE = new AbilityAttribute("Gamma Knife").setAbilityCooldown(30).setProjectileTicks(20).setProjectileModel(new ModelCube()).setProjectileColor("00AB66").setProjectileDamage(100).setProjectileSize(1, 1, 5);
	public static AbilityAttribute MES = new AbilityAttribute("Mes").setAbilityCooldown(5).setAbilityPassive().setAbilityPunch(1); 
	public static AbilityAttribute COUNTERSHOCK = new AbilityAttribute("Counter Shock").setAbilityCooldown(10).setAbilityPassive().setAbilityPunch(40).setAbilityExplosion(1, false);
	public static AbilityAttribute ROOM = new AbilityAttribute("Room").setAbilityCooldown(1).setAbilityPassive(true);
	
	public static AbilityAttribute NORONOROBEAM = new AbilityAttribute("Noro Noro Beam").setAbilityCooldown(5).setProjectileTicks(10).setProjectileModel(new ModelNoroNoroBeam()).setProjectileTexture("noronorobeam").setProjectileSize(5, 5, 5).setProjectileSpeed(1.6F).setProjectileCollisionSizes(1);
	public static AbilityAttribute KYUBIRUSH = new AbilityAttribute("Kyubi Rush").setAbilityCooldown(7).setAbilityPassive().setAbilityPunch();
	public static AbilityAttribute NORONOROBEAMSWORD = new AbilityAttribute("Noro Noro Beam Sword").setAbilityPassive(true);

	public static AbilityAttribute AIRDOOR = new AbilityAttribute("Air Door").setAbilityPassive(true).setAbilityCooldown(40);
	public static AbilityAttribute DOOR = new AbilityAttribute("Door").setAbilityCooldown(8);

	public static AbilityAttribute SUKEPUNCH = new AbilityAttribute("Suke Punch").setAbilityPassive().setAbilityPunch();
	public static AbilityAttribute SHISHANOTE = new AbilityAttribute("Shisha no Te").setAbilityCooldown(3).setProjectileDamage(5).setProjectileModel(new ModelCube()).setProjectileSize(0, 0, 0).setProjectileExplosion(3, false);
	public static AbilityAttribute SKATTING = new AbilityAttribute("Skatting").addEffects(EffectType.USER, new PotionEffect(Potion.invisibility.id, 30, 5)).setAbilityPassive();  	
	 
	public static AbilityAttribute GEARSECOND = new AbilityAttribute("Gear Second").setAbilityCooldown(60).setAbilityPassive();
	public static AbilityAttribute GEARTHIRD = new AbilityAttribute("Gear Third").setAbilityCooldown(90).setAbilityPassive();
	public static AbilityAttribute GEARFOURTH = new AbilityAttribute("Gear Fourth").setAbilityCooldown(300).setAbilityPassive();
	public static AbilityAttribute GOMUGOMUNOROCKET = new AbilityAttribute("Gomu Gomu no Rocket").setProjectileModel(new ModelFist()).setProjectileTexture("gomugomunopistol").setProjectileSize(3, 3, 3).setModelOffsets(0, 1, 0).setProjectileDamage(6).setAbilityCooldown(8).setProjectilePhysical().setProjectileSpeed(2.5F);
	public static AbilityAttribute GOMUGOMUNOBAZOOKA = new AbilityAttribute("Gomu Gomu no Bazooka").setAbilityCharges(10);
	public static AbilityAttribute GOMUGOMUNOGATLING = new AbilityAttribute("Gomu Gomu no Gatling").setProjectileTicks(10);
	public static AbilityAttribute GOMUGOMUNOPISTOL = new AbilityAttribute("Gomu Gomu no Pistol");
	
	public static AbilityAttribute FLASH = new AbilityAttribute("Flash").setAbilityCooldown(5).addEffects(EffectType.AOE, new PotionEffect(Potion.blindness.id, 7 * 20, 3), new PotionEffect(Potion.moveSlowdown.id, 7 * 20, 1)).setEffectRadius(10);
	public static AbilityAttribute AMANOMURAKUMO = new AbilityAttribute("Ama no Murakumo").setAbilityPassive(true);
	public static AbilityAttribute AMATERASU = new AbilityAttribute("Amaterasu").setAbilityCooldown(15).setProjectileTicks(150).setProjectileModel(new ModelCube()).setProjectileSize(1, 1, 1).setProjectileColor("FFFF00").setProjectileSpeed(5).setProjectileDamage(35).setProjectileExplosion(6, false).setAbilityCharges(2 * 20);
	public static AbilityAttribute YASAKANINOMAGATAMA = new AbilityAttribute("Yasakani no Magatama").setAbilityCooldown(2.5).setProjectileModel(new ModelSphere()).setProjectileSize(.5, .5, .5).setProjectileColor("FFFF00").setAbilityRepeater(2).setProjectileDamage(2).setProjectileExplosion(1, false).setProjectileSpeed(5);
	public static AbilityAttribute YATANOKAGAMI = new AbilityAttribute("Yata no Kagami").setAbilityCooldown(4);
	 
	public static AbilityAttribute SPRINGDEATHKNOCK = new AbilityAttribute("Spring Death Knock").setAbilityCooldown(6).setProjectileDamage(20).setProjectileModel(new ModelFist()).setProjectileTexture("springdeathknock").setModelOffsets(-1, 1.5, 0).setProjectileSize(7, 5, 5).setProjectileTicks(3).setProjectilePhysical();
	public static AbilityAttribute SPRINGSNIPE = new AbilityAttribute("Spring Snipe").setAbilityCooldown(5).setAbilityCharges(20);
	public static AbilityAttribute SPRINGHOPPER = new AbilityAttribute("Spring Hopper").setAbilityCooldown(0.6).setAbilityCharges(10);

	public static AbilityAttribute ICETIMECAPSULE = new AbilityAttribute("Ice Time Capsule").setAbilityCooldown(15);
	public static AbilityAttribute ICESABER = new AbilityAttribute("Ice Saber").setAbilityPassive(true);
	public static AbilityAttribute ICEBALL = new AbilityAttribute("Ice Ball").setAbilityCooldown(6).setProjectileDamage(5).setProjectileModel(new ModelSphere()).setProjectileColor("00FFFF").setProjectileSize(5, 5, 5).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.moveSlowdown.id, 100, 0), new PotionEffect(Potion.digSlowdown.id, 100, 0));
	public static AbilityAttribute ICEAGE = new AbilityAttribute("Ice Age").setAbilityCooldown(15).addEffects(EffectType.AOE, new PotionEffect(Potion.moveSlowdown.id, 200, 100), new PotionEffect(Potion.digSlowdown.id, 200, 100)).setEffectRadius(20);
	public static AbilityAttribute ICEBLOCKPARTISAN = new AbilityAttribute("Ice Block : Partisan").setAbilityCooldown(7).setProjectileDamage(10).setProjectileModel(new ModelTrident()).setProjectileTexture("iceblockpartisan").setProjectileSize(1.5, 1.5, 1.5).setModelOffsets(0, 1.0, 0).setAbilityRepeater().addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.moveSlowdown.id, 100, 0), new PotionEffect(Potion.digSlowdown.id, 100, 0));
	public static AbilityAttribute ICEBLOCKPHEASANT = new AbilityAttribute("Ice Block : Pheasant").setAbilityCooldown(20).setProjectileDamage(45).setProjectileModel(new ModelPheasant()).setProjectileTexture("iceblockpheasant").setProjectileSize(5, 5, 5).setModelOffsets(0, 2.5, 0).addEffects(EffectType.PROJECTILE, new PotionEffect(Potion.moveSlowdown.id, 200, 100), new PotionEffect(Potion.digSlowdown.id, 200, 100)).setProjectileCollisionSizes(1.75, 1.5, 1.75);
	
	public static AbilityAttribute ENJOMO  = new AbilityAttribute("Enjomo").setAbilityCooldown(10);
	public static AbilityAttribute JUJIKA = new AbilityAttribute("Jujika").setAbilityCooldown(6).setProjectileDamage(5).setProjectileModel(new ModelSphere()).setProjectileColor("FF0000").setProjectileSize(.2, .2, .2);
	public static AbilityAttribute HIDARUMA = new AbilityAttribute("Hidaruma").setAbilityCooldown(6).setProjectileModel(new ModelCube()).setProjectileSize(0, 0, 0);
	public static AbilityAttribute DAIENKAIENTEI = new AbilityAttribute("Dai Enkai : Entei").setAbilityCooldown(25).setProjectileModel(new ModelSphere()).setProjectileDamage(45).setProjectileColor("FF0000").setProjectileSize(9, 9, 9).setProjectileExplosion(7).setAbilityCharges(4 * 20).setProjectileCollisionSizes(2);
	public static AbilityAttribute HIGAN = new AbilityAttribute("Higan").setAbilityCooldown(4).setProjectileModel(new ModelCube()).setProjectileDamage(5).setProjectileColor("FF0000").setProjectileSize(.3, .3, .3).setAbilityRepeater(4);
	public static AbilityAttribute HIKEN = new AbilityAttribute("Hiken").setAbilityCooldown(8).setProjectileModel(new ModelFist()).setProjectileTexture("hiken").setModelOffsets(0, 0.5, 0).setProjectileDamage(20).setProjectileSize(1.5, 1.5, 1.5).setProjectileExplosion(2);

	public static AbilityAttribute GREATCAGE = new AbilityAttribute("Great Cage").setAbilityCooldown(20);
	public static AbilityAttribute PRISONBREAK = new AbilityAttribute("Prison Break").setAbilityCooldown(3).setAbilityPassive();
	public static AbilityAttribute AWASEBAORI = new AbilityAttribute("Awase Baori").setAbilityCooldown(12).setProjectileModel(new ModelSphere()).setProjectileColor("000000").setProjectileSize(7, 7, 7);
	public static AbilityAttribute BIND = new AbilityAttribute("Bind").setAbilityCooldown(10).setAbilityPassive().setAbilityPunch();

	public static AbilityAttribute SORU = new AbilityAttribute("Soru").addEffects(EffectType.USER, new PotionEffect(Potion.moveSpeed.id, 30, 6)).setAbilityPassive();
	public static AbilityAttribute TEKKAI = new AbilityAttribute("Tekkai").setAbilityCooldown(10).addEffects(EffectType.USER, new PotionEffect(Potion.resistance.id, 30, 100), new PotionEffect(Potion.moveSlowdown.id, 30, 100), new PotionEffect(Potion.digSlowdown.id, 30, 5), new PotionEffect(Potion.jump.id, 30, -100)).setAbilityPassive();
	public static AbilityAttribute GEPPO = new AbilityAttribute("Geppo").setAbilityCooldown(0.9);
	public static AbilityAttribute RANKYAKU = new AbilityAttribute("Rankyaku").setAbilityCooldown(9).setProjectileTicks(100).setProjectileModel(new ModelCube()).setProjectileSize(6, 0.4, 1.5).setProjectileColor("69E3FF").setProjectileDamage(20).setProjectileExplosion(2, false).setProjectileCollisionSizes(1.5, 0.3, 1.5).setProjectileMoveThroughBlocks(true);
	public static AbilityAttribute SHIGAN = new AbilityAttribute("Shigan").setAbilityCooldown(5).setAbilityPassive().setAbilityPunch();
	public static AbilityAttribute KAMIE = new AbilityAttribute("Kamie").setAbilityCooldown(10).addEffects(EffectType.USER, new PotionEffect(Potion.resistance.id, 20, 100)).setAbilityPassive();

	public static AbilityAttribute HOTBOILINGSPECIAL = new AbilityAttribute("Hot Boiling Special").setAbilityPassive().setAbilityPunch(10).setAbilityCooldown(7);
	public static AbilityAttribute EVAPORATE = new AbilityAttribute("Evaporate").setAbilityCooldown(15);

	public static AbilityAttribute WEIGHTLESS = new AbilityAttribute("Weightless").setAbilityPassive(true);
	public static AbilityAttribute KICKOFFJUMP = new AbilityAttribute("Kick Off Jump").setAbilityPassive().setAbilityCooldown(4);
	public static AbilityAttribute HEAVYPUNCH = new AbilityAttribute("Heavy Punch").setAbilityCooldown(20).setAbilityPassive().setAbilityPunch(20);
	public static AbilityAttribute KILOPRESS = new AbilityAttribute("Kilo Press").setAbilityCooldown(10).setAbilityPassive();

	public static AbilityAttribute RUSTTOUCH = new AbilityAttribute("Rust Touch").setAbilityCooldown(19).setAbilityPunch().setAbilityPassive();

	public static AbilityAttribute UCHIMIZU = new AbilityAttribute("Uchimizu").setAbilityCooldown(5).setProjectileModel(new ModelCube()).setProjectileColor("00CED1").setProjectileSize(.5, .5, 1).setProjectileDamage(5).setAbilityRepeater(3, 2);
	public static AbilityAttribute MURASAME = new AbilityAttribute("Murasame").setAbilityCooldown(8).setProjectileModel(new ModelShark()).setProjectileTexture("murasame").setProjectileSize(.8, .8, 1.2).setProjectileDamage(25);
	public static AbilityAttribute KACHIAGEHAISOKU = new AbilityAttribute("Kachiage Haisoku").setAbilityCooldown(15).setAbilityPassive().setAbilityPunch();
	public static AbilityAttribute SAMEHADASHOTEI = new AbilityAttribute("Samehada Shotei").addEffects(EffectType.USER, new PotionEffect(Potion.resistance.id, 10, 120), new PotionEffect(Potion.moveSlowdown.id, 10, 120), new PotionEffect(Potion.jump.id, 30, -100)).setAbilityPassive();	
	public static AbilityAttribute KARAKUSAGAWARASEIKEN = new AbilityAttribute("Karakusagawara Seiken").setAbilityCooldown(25);
	
	public static AbilityAttribute KAENBOSHI = new AbilityAttribute("Kaen Boshi").setAbilityCooldown(10).setAbilityPassive().setProjectileModel(new ModelSphere()).setProjectileDamage(8).setProjectileSize(.5, .5, .5).setProjectileTicks(100).setProjectileColor("#D3D3D3");
	public static AbilityAttribute KEMURIBOSHI = new AbilityAttribute("Kemuri Boshi").setAbilityCooldown(10).setAbilityPassive().setProjectileModel(new ModelSphere()).setProjectileDamage(6).setProjectileSize(.5, .5, .5).setProjectileTicks(100).setProjectileColor("#8741A8");
	public static AbilityAttribute RENPATSUNAMARIBOSHI = new AbilityAttribute("Renpatsu Namari Boshi").setAbilityCooldown(15).setAbilityPassive().setProjectileModel(new ModelSphere()).setProjectileDamage(6).setProjectileSize(.5, .5, .5).setProjectileTicks(100).setProjectileExplosion(1, false).setProjectileColor("#D3D3D3").setAbilityRepeater(10, 3);
	public static AbilityAttribute SAKURETSUSABOTENBOSHI = new AbilityAttribute("Sakuretsu Saboten Boshi").setAbilityCooldown(12).setAbilityPassive().setProjectileModel(new ModelSphere()).setProjectileDamage(12).setProjectileExplosion(2, false, false).setProjectileSize(.5, .5, .5).setProjectileTicks(100).setProjectileColor("#D3D3D3");
	
	public static AbilityAttribute SHISHISHISONSON = new AbilityAttribute("Shi Shishi Sonson").setAbilityCooldown(7);
	public static AbilityAttribute SANBYAKUROKUJUPOUNDHO = new AbilityAttribute("Sanbyakurokuju Pound Ho").setAbilityCooldown(5).setProjectileTicks(100).setProjectileModel(new ModelCube()).setProjectileSize(6, 0.4, 1.5).setProjectileColor("bbf7b4").setProjectileDamage(18).setProjectileExplosion(3, false);
	public static AbilityAttribute YAKKODORI = new AbilityAttribute("Yakkodori").setAbilityCooldown(3).setAbilityCooldown(5).setProjectileTicks(20).setProjectileModel(new ModelCube()).setProjectileSize(0.4, 6, 0.4).setProjectileColor("bbf7b4").setProjectileDamage(10).setProjectileExplosion(1, false).setProjectileMoveThroughBlocks(true);
	public static AbilityAttribute OTATSUMAKI = new AbilityAttribute("O Tatsumaki").setAbilityCooldown(7);
		
	//public static AbilityAttribute MURASAME = new AbilityAttribute("Murasame").setAbilityCooldown(250).setProjectileModel(new ModelCube()).setProjectileColor("00CED1").setProjectileSize(1.8, 1, 1).setProjectileDamage(15).setAbilityRepeater();
	//public static AbilityAttribute YARINAMI = new AbilityAttribute("Yarinami").setAbilityCooldown(150).setProjectileModel(new ModelCube()).setProjectileColor("00CED1").setProjectileSize(3, 1, 1).setProjectileDamage(15);
	
	public static AbilityAttribute FRESHFIRE = new AbilityAttribute("Fresh Fire").setAbilityCooldown(1.5).setProjectileTicks(7).setProjectileSize(.01, .01, .01).setProjectileDamage(1);
	public static AbilityAttribute COLAOVERDRIVE = new AbilityAttribute("Cola Overdrive").setAbilityCooldown(7).addEffects(EffectType.USER, new PotionEffect(Potion.moveSpeed.id, 200, 0), new PotionEffect(Potion.damageBoost.id, 200, 1));
	public static AbilityAttribute RADICALBEAM = new AbilityAttribute("Radical Beam").setAbilityCooldown(10).setProjectileModel(new ModelCube()).setProjectileColor("FFFF00").setProjectileSize(.5, .5, 1).setProjectileDamage(25).setProjectileExplosion(4, false);
	public static AbilityAttribute STRONGRIGHT = new AbilityAttribute("Strong Right").setAbilityCooldown(2.5).setProjectileModel(new ModelCube()).setProjectileColor("F5DEB3").setProjectileTicks(5).setProjectileSize(1, 1, 1.5).setProjectileDamage(20);
	public static AbilityAttribute COUPDEVENT = new AbilityAttribute("Coup de Vent").setAbilityCooldown(10).setProjectileTicks(7).setProjectileSize(.01, .01, .01).setProjectileDamage(10).setAbilityCharges(30);
	
	public static AbilityAttribute KENBUNSHOKUHAKI = new AbilityAttribute("Kenbunshoku Haki").setAbilityPassive(true);
	public static AbilityAttribute BUSOSHOKUHAKI = new AbilityAttribute("Busoshoku Haki").setAbilityPassive(true);
	public static AbilityAttribute HAOSHOKUHAKI = new AbilityAttribute("Haoshoku Haki");

	public static AbilityAttribute NULL = new AbilityAttribute("n/a");
}
