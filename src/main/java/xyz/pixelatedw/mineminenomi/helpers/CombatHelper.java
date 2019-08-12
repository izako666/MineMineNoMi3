package xyz.pixelatedw.mineminenomi.helpers;

import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;

public class CombatHelper
{

	public static boolean isPassiveActive(IAbilityData abilityProps, AbilityAttribute attr)
	{
		if(abilityProps == null)
			return false;
		
		Ability ability = abilityProps.getHotbarAbilityFromName(attr.getAttributeName());
		if (ability != null && ability.isPassiveActive())
			return true;

		return false;
	}
	
}
