package xyz.pixelatedw.MineMineNoMi3.data;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityManager;

public class ExtendedEntityData implements IExtendedEntityProperties 
{

	public final static String EXT_PROP_NAME = "IEEPShared";
	private final EntityLivingBase entity;

	private boolean isInCombatMode = false;
	
	private int doriki, dorikiCmd, belly, bellyCmd, extol, extolCmd, cola = 100, maxCola = 100, hakiTimer = 0, ultraCola = 0, gear = 1;
	private long bounty, bountyCmd;
	private String akumaNoMiUsed = "n/a", faction = "n/a", race = "n/a", fightStyle = "n/a", crew = "n/a", zoanPoint = "n/a";
	private boolean isLogia, hasShadow = true, hasHeart = true, firstTime = true, hasHakiActive = false, hasBusoHakiActive = false, hasKenHakiActive = false, kilo = false, hasYamiPower = false, hasColaBackpack = false;

	private String tempPreviousAbility = "";

	private String[] extraEffects = new String[32];
	
	public ExtendedEntityData(EntityLivingBase entity) 
	{
		this.entity = entity;	
	}
	
	public static final void register(EntityLivingBase entity) 
	{
		entity.registerExtendedProperties(ExtendedEntityData.EXT_PROP_NAME, new ExtendedEntityData(entity));
	}

	public static final ExtendedEntityData get(EntityLivingBase entity) 
	{
		return (ExtendedEntityData) entity.getExtendedProperties(EXT_PROP_NAME);
	}

	public EntityLivingBase getEntity()
	{
		return this.entity;
	}
	
	public void saveNBTData(NBTTagCompound compound) 
	{
		NBTTagCompound props = new NBTTagCompound();

		props.setInteger("Doriki", this.doriki);
		props.setInteger("DorikiCmd", this.dorikiCmd);
		props.setInteger("Belly", this.belly);
		props.setInteger("BellyCmd", this.bellyCmd);
		props.setInteger("Extol", this.extol);
		props.setInteger("ExtolCmd", this.extolCmd);
		props.setInteger("Cola", this.cola);
		props.setInteger("MaxCola", this.maxCola);	
		props.setInteger("UltraCola", this.ultraCola);
		props.setInteger("Gear", this.gear);
		
		props.setLong("Bounty", this.bounty);
		props.setLong("BountyCmd", this.bountyCmd);
		
		props.setString("AkumaNoMi", this.akumaNoMiUsed);
		props.setString("Faction", this.faction);
		props.setString("Race", this.race);
		props.setString("FightStyle", this.fightStyle);
		props.setString("Crew", this.crew);
		props.setString("ZoanPoint", this.zoanPoint);
		
		props.setBoolean("isLogia", this.isLogia);
		props.setBoolean("hasShadow", this.hasShadow);
		props.setBoolean("hasHeart", this.hasHeart);
		props.setBoolean("firstTime", this.firstTime);
		props.setBoolean("hasKiloActive", this.kilo);
		props.setBoolean("hasHakiActive", this.hasHakiActive);
		props.setBoolean("hasBusoHakiActive", this.hasBusoHakiActive);
		props.setBoolean("hasKenHakiActive", this.hasKenHakiActive);
		props.setBoolean("hasYamiPower", this.hasYamiPower);
		props.setBoolean("hasColaBackpack", this.hasColaBackpack);
		
		props.setBoolean("isInCombatMode", this.isInCombatMode);		
		
		for(int i = 0; i < this.extraEffects.length; i++)
			if(this.extraEffects[i] != null && !this.extraEffects[i].isEmpty())
				props.setString("extraEffect_" + i, this.extraEffects[i]);
		
		compound.setTag(EXT_PROP_NAME, props);
	}

	public void loadNBTData(NBTTagCompound compound) 
	{
		NBTTagCompound props = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);

		this.doriki = props.getInteger("Doriki");
		this.dorikiCmd = props.getInteger("DorikiCmd");
		this.belly = props.getInteger("Belly");
		this.bellyCmd = props.getInteger("BellyCmd");
		this.extol = props.getInteger("Extol");
		this.extolCmd = props.getInteger("ExtolCmd");
		this.cola = props.getInteger("Cola");
		this.maxCola = props.getInteger("MaxCola");
		this.ultraCola = props.getInteger("UltraCola");
		this.gear = props.getInteger("Gear");
		
		this.bounty = props.getLong("Bounty");
		this.bountyCmd = props.getLong("BountyCmd");
		
		this.akumaNoMiUsed = props.getString("AkumaNoMi");
		this.faction = props.getString("Faction");
		this.race = props.getString("Race");
		this.fightStyle = props.getString("FightStyle");
		this.crew = props.getString("Crew");
		this.zoanPoint = props.getString("ZoanPoint");
		
		this.isLogia = props.getBoolean("isLogia");
		this.hasShadow = props.getBoolean("hasShadow");
		this.hasHeart = props.getBoolean("hasHeart");
		this.firstTime = props.getBoolean("firstTime");
		this.kilo = props.getBoolean("hasKiloActive");
		this.hasHakiActive = props.getBoolean("hasHakiActive");
		this.hasBusoHakiActive = props.getBoolean("hasBusoHakiActive");
		this.hasKenHakiActive = props.getBoolean("hasKenHakiActive");
		this.hasYamiPower = props.getBoolean("hasYamiPower");
		this.hasColaBackpack = props.getBoolean("hasColaBackpack");
		
		this.isInCombatMode = props.getBoolean("isInCombatMode");
		
		for(int i = 0; i < this.extraEffects.length; i++)
			this.extraEffects[i] = props.getString("extraEffect_" + i);
	}

	public void resetNBTData(NBTTagCompound compound) 
	{
		NBTTagCompound props = new NBTTagCompound();

		props.setInteger("Doriki", 0);
		props.setInteger("DorikiCmd", 0);
		props.setInteger("Bounty", 0);
		props.setInteger("BountyCmd", 0);
		props.setInteger("Belly", 0);
		props.setInteger("BellyCmd", 0);
		props.setInteger("Extol", 0);
		props.setInteger("ExtolCmd", 0);
		props.setInteger("Cola", 0);
		props.setInteger("MaxCola", 0);	
		props.setInteger("UltraCola",0);
		props.setInteger("Gear", 0);
		
		props.setString("AkumaNoMi", "N/A");
		props.setString("Faction", "N/A");
		props.setString("Race", "N/A");
		props.setString("FightStyle", "N/A");
		props.setString("Crew", "N/A");
		props.setString("ZoanPoint", "N/A");
		
		props.setBoolean("isLogia", false);
		props.setBoolean("hasShadow", true);
		props.setBoolean("hasHeart", true);
		props.setBoolean("firstTime", false);
		props.setBoolean("hasKiloActive", false);
		props.setBoolean("hasHakiActive", false);
		props.setBoolean("hasBusoHakiActive", false);
		props.setBoolean("hasKenHakiActive", false);
		props.setBoolean("hasYamiPower", false);
		props.setBoolean("hasColaBackpack", false);
		props.setBoolean("isCandleLocked", false);
		props.setBoolean("isTaktBlocked", false);
		
		props.setBoolean("isInCombatMode", false);		
		
		compound.setTag(EXT_PROP_NAME, props);
	}
	
	public void printFancyData(NBTTagCompound compound)
	{
		NBTTagCompound props = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		
		System.out.println("=====EXTENDED DATA");
		System.out.println(" > Used Fruit : " + props.getString("AkumaNoMi"));
		System.out.println(" > Faction : " + props.getString("Faction"));
		System.out.println(" > Race : " + props.getString("Race"));
		System.out.println(" > Style : " + props.getString("FightStyle"));
		System.out.println(" > Zoan Point : " + props.getString("ZoanPoint"));
		System.out.println("");
		System.out.println(" > Is Logia : " + props.getBoolean("isLogia"));
		System.out.println(" > Has Shadow : " + props.getBoolean("hasShadow"));
		System.out.println(" > Has Heart : " + props.getBoolean("hasHeart"));
		System.out.println(" > Has Yami Power : " + props.getBoolean("hasYamiPower"));
		System.out.println(" > Has Haki Active : " + props.getBoolean("hasHakiActive"));
		System.out.println(" > Has Buso Haki Active : " + props.getBoolean("hasBusoHakiActive"));
		System.out.println(" > Has Ken Haki Active : " + props.getBoolean("hasKenHakiActive"));
		System.out.println("");
		System.out.println(" > Doriki : " + props.getInteger("Doriki"));
		System.out.println(" > Bounty : " + props.getInteger("Bounty"));
		System.out.println(" > Belly : " + props.getInteger("Belly"));
		System.out.println("");
	}

	public void init(Entity entity, World world) {}
	
	public void setCombatMode(boolean value) { this.isInCombatMode = value; }
	public boolean isInCombatMode() { return this.isInCombatMode; }
	
	public int getDoriki() {return doriki;}
	public void alterDoriki(int i)
	{
		if(doriki + i < 0) doriki = 0;
		else doriki = doriki + i;
	}
	public void setDoriki(int i) {doriki = i;}
	
	public int getDorikiFromCommand() {return dorikiCmd;}
	public void alterDorikiFromCommand(int i)
	{
		if(dorikiCmd + i < 0) dorikiCmd = 0;
		else dorikiCmd = dorikiCmd + i;
	}
	public void setDorikiFromCommand(int i) {dorikiCmd = i;}
	
	public int getExtol() {return this.extol;}
	public void alterExtol(int i)
	{
		if(extol + i < 0) extol = 0;
		else extol = extol + i;
	}
	public void setExtol(int i) {this.extol = i;}
	
	public int getExtolFromCommand() {return this.extolCmd;}
	public void alterExtolFromCommand(int i)
	{
		if(extolCmd + i < 0) extolCmd = 0;
		else extolCmd = extolCmd + i;
	}
	public void setExtolFromCommand(int i) {this.extolCmd = i;}
	
	public int getBelly() {return this.belly;}
	public void alterBelly(int i)
	{
		if(belly + i < 0) belly = 0;
		else belly = belly + i;
	}
	public void setBelly(int i)	{this.belly = i;}
	
	public int getBellyFromCommand() {return this.bellyCmd;}
	public void alterBellyFromCommand(int i)
	{
		if(bellyCmd + i < 0) bellyCmd = 0;
		else bellyCmd = bellyCmd + i;
	}
	public void setBellyFromCommand(int i)	{this.bellyCmd = i;}
	
	public long getBounty() {return this.bounty;}
	public void alterBounty(long i)
	{
		if(bounty + i < 0) bounty = 0;
		else bounty = bounty + i;
	}
	public void setBounty(long i) {this.bounty = i;}

	public long getBountyFromCommand() {return this.bountyCmd;}
	public void alterBountyFromCommand(long i)
	{
		if(bountyCmd + i < 0) bountyCmd = 0;
		else bountyCmd = bountyCmd + i;
	}
	public void setBountyFromCommand(long i) {this.bountyCmd = i;}
	
	public int getCola() {return this.cola;}
	public void alterCola(int i)
	{
		if(cola + i < 0) cola = 0;
		else if(cola + i > getMaxCola()) cola = getMaxCola();
		else cola = cola + i;
	}
	public void setCola(int i) {this.cola = i;}
	
	public int getUltraColaConsumed() { return this.ultraCola; }
	public void setUltraCola(int i) { this.ultraCola = i; }
	public void addUltraCola() { this.ultraCola++; }
	
	public int getMaxCola() { return this.maxCola; }
	public void setMaxCola(int maxCola) { this.maxCola = maxCola; }
	
	public boolean isLogia() {return this.isLogia;}
	public void setIsLogia(boolean i) {this.isLogia = i;}
	
	public String getUsedFruit() {return this.akumaNoMiUsed;}
	public boolean hasDevilFruit() { return !this.akumaNoMiUsed.isEmpty() && !this.akumaNoMiUsed.equalsIgnoreCase("n/a"); }
	public void setUsedFruit(String name) {this.akumaNoMiUsed = name;}
	
	public boolean hasHeart() {return this.hasHeart;}
	public void setHasHeart(boolean b) {this.hasHeart = b;}
	
	public boolean hasShadow() {return this.hasShadow;}
	public void setHasShadow(boolean b) {this.hasShadow = b;}
	
	public void setGear(int i) {this.gear = i;}
	public int getGear() {return this.gear;}
	
	public String getFightStyle() {return this.fightStyle;}
	public boolean isSwordsman() { return this.fightStyle.equalsIgnoreCase("swordsman"); }
	public boolean isSniper() { return this.fightStyle.equalsIgnoreCase("sniper"); }
	public boolean isMedic() { return this.fightStyle.equalsIgnoreCase("medic"); }
	public boolean hasFightingStyle() { return !this.fightStyle.equalsIgnoreCase("n/a"); }
	public void setFightStyle(String i) {this.fightStyle = i;}
	
	public String getRace() {return this.race;}
	public boolean isHuman() { return this.race.equalsIgnoreCase("human"); }
	public boolean isFishman() { return this.race.equalsIgnoreCase("fishman"); }
	public boolean isCyborg() { return this.race.equalsIgnoreCase("cyborg"); }
	public boolean hasRace() { return !this.race.equalsIgnoreCase("n/a"); }
	public void setRace(String i) {this.race = i;}

	public String getFaction() {return this.faction;}
	public boolean isPirate() { return this.faction.equalsIgnoreCase("pirate"); }
	public boolean isMarine() { return this.faction.equalsIgnoreCase("marine"); }
	public boolean isBountyHunter() { return this.faction.equalsIgnoreCase("bountyhunter"); }
	public boolean hasFaction() { return !this.faction.equalsIgnoreCase("n/a"); }
	public void setFaction(String i) {this.faction = i;}
	
	public String getCrew() {return this.crew;}
	public void setCrew(String crewName) {this.crew = crewName;}
	
	public String getZoanPoint() {return this.zoanPoint;}
	public void setZoanPoint(String i) {this.zoanPoint = i;}
	
	public boolean isFirstTime() {return this.firstTime;}
	public void setFirstTime(boolean firstTime) {this.firstTime = firstTime;}
	public void firstTimePass() {this.firstTime = false;}
	
	public boolean hasBusoHakiActive() { return hasBusoHakiActive; }
	public void triggerBusoHaki(boolean isBusoHakiActive) { this.hasBusoHakiActive = isBusoHakiActive; }
	public boolean hasKenHakiActive() { return hasKenHakiActive; }
	public void triggerKenHaki(boolean isKenHakiActive) { this.hasKenHakiActive = isKenHakiActive; }
	public boolean hasHakiActive() { return hasHakiActive; }
	public void triggerActiveHaki(boolean isHakiActive) { this.hasHakiActive = isHakiActive; }
	public int getHakiTimer() { return hakiTimer; }
	public void addHakiTimer() { hakiTimer++; }
	public void decHakiTimer() { hakiTimer--; }
	public void resetHakiTimer() { hakiTimer = 0; }

	public void setKilo(boolean kilo) { this.kilo = kilo; }
	public boolean getKilo() { return kilo; } 
	
	public void setYamiPower(boolean bool) { this.hasYamiPower = bool; }
	public boolean hasYamiPower() { return hasYamiPower; } 
	
	public void setColaBackpack(boolean bool) { this.hasColaBackpack = bool; }
	public boolean hasColaBackpack() { return hasColaBackpack; }
	
	public void setTempPreviousAbility(String temp) { this.tempPreviousAbility = temp; }
	public String getTempPreviousAbility() { return this.tempPreviousAbility; }
	
	public boolean addExtraEffect(String eff)
	{
		for(int i = 0; i < this.extraEffects.length; i++)
		{
			if(this.extraEffects[i] == null || this.extraEffects[i].isEmpty())
			{
				this.extraEffects[i] = eff;
				return true;
			}
		}
		
		return false;
	}
	public void removeExtraEffects(String eff)
	{
		for(int i = 0; i < this.extraEffects.length; i++)
		{
			if(this.extraEffects[i] != null && !this.extraEffects[i].isEmpty())
			{
				this.extraEffects[i] = null;
				break;
			}
		}
	}	
	public boolean hasExtraEffects(String eff)
	{
		for(int i = 0; i < this.extraEffects.length; i++)
		{
			if(this.extraEffects[i] != null && !this.extraEffects[i].isEmpty() && this.extraEffects[i].equalsIgnoreCase(eff))
			{
				return true;
			}
		}
		
		return false;
	}	
	public String[] getExtraEffects()
	{ 
		return this.extraEffects; 
	}
}