package xyz.pixelatedw.mineminenomi.api.data.abilitydata;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;

public interface IAbilityData
{

	boolean addDevilFruitAbility(Ability abl);
	void removeDevilFruitAbility(Ability ablTemplate);
	boolean hasDevilFruitAbility(Ability ablTemplate);
	Ability[] getDevilFruitAbilities();
	void clearDevilFruitAbilities();
	
	boolean addRacialAbility(Ability abl);
	void removeRacialAbility(Ability ablTemplate);
	boolean hasRacialAbility(Ability ablTemplate);
	Ability[] getRacialAbilities();
	void clearRacialAbilities();
	
	boolean addHakiAbility(Ability abl);
	void removeHakiAbility(Ability ablTemplate);
	boolean hasHakiAbility(Ability ablTemplate);
	Ability[] getHakiAbilities();
	void clearHakiAbilities();
	
	Ability[] getAbilitiesInHotbar();
	boolean hasAbilityInHotbar(Ability ability);
	boolean hasAbilityInHotbar(String abilityName);
	void setAbilityInSlot(int slot, Ability abl);
	Ability getHotbarAbilityFromSlot(int slot);
	Ability getHotbarAbilityFromName(String name);
	int countAbilitiesInHotbar();
	void clearHotbar(PlayerEntity player);
	void clearHotbarFromList(PlayerEntity player, Ability[] list); 

	Ability getPreviouslyUsedAbility();
	void setPreviouslyUsedAbility(Ability abl);
	
	boolean isInCombatMode();
	void setCombatMode(boolean value);
}
