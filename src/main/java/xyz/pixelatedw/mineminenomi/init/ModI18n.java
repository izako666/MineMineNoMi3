package xyz.pixelatedw.mineminenomi.init;

import xyz.pixelatedw.mineminenomi.api.WyRegistry;

public class ModI18n
{

	public static void init()
	{
		// ItemGroups
		WyRegistry.registerName("itemGroup.devilfruits", "Devil Fruits");
		WyRegistry.registerName("itemGroup.weapons", "Weapons");
		
		// GUI
		WyRegistry.registerName("gui.faction.name", "Faction");
		WyRegistry.registerName("gui.race.name", "Race");
		WyRegistry.registerName("gui.style.name", "Fighting Style");
		WyRegistry.registerName("gui.abilities.name", "Abilities");
		WyRegistry.registerName("gui.quests.name", "Quests");
		
		// Faction | Race | Fighting Style
		WyRegistry.registerName("faction.empty.name", "No Faction");
		WyRegistry.registerName("race.empty.name", "No Race");
		WyRegistry.registerName("style.empty.name", "No Fighting Style");
		
		WyRegistry.registerName("faction.pirate.name", "Pirate");
		WyRegistry.registerName("faction.marine.name", "Marine");
		WyRegistry.registerName("faction.bountyhunter.name", "Bounty Hunter");
		
		WyRegistry.registerName("race.human.name", "Human");
		WyRegistry.registerName("race.fishman.name", "Fishman");
		WyRegistry.registerName("race.cyborg.name", "Cyborg");
		
		WyRegistry.registerName("style.swordsman.name", "Swordsman");
		WyRegistry.registerName("style.sniper.name", "Sniper");
		WyRegistry.registerName("style.medic.name", "Medic");
		
		// Keys
		WyRegistry.registerName("category.mmnmkeys", "Mine Mine no Mi Keys");
		WyRegistry.registerName("key.combatmode", "Combat Mode");
		WyRegistry.registerName("key.playerui", "Player UI");
		for(int i = 1; i < 9; i++)
			WyRegistry.registerName("key.combatslot." + i, "Ability Slot " + i);
	}
	
}
