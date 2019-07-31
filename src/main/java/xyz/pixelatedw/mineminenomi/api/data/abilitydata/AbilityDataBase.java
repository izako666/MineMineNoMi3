package xyz.pixelatedw.mineminenomi.api.data.abilitydata;

import java.util.Arrays;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;

public class AbilityDataBase implements IAbilityData
{

	private Ability[] hotbarAbilities = new Ability[8];
	private Ability[] devilFruitAbilities = new Ability[128];
	private Ability[] racialAbilities = new Ability[32];
	private Ability[] styleAbilities = new Ability[32];
	private Ability[] hakiAbilities = new Ability[3];
	
	private Ability previouslyUsedAbility;
	private boolean isInCombatMode = false;
	
	/*
	 * Devil Fruits
	 */
	@Override
	public boolean addDevilFruitAbility(Ability abl)
	{
		for(int i = 0; i < devilFruitAbilities.length; i++)
		{
			if(this.devilFruitAbilities[i] == null && !this.hasDevilFruitAbility(abl))
			{
				this.devilFruitAbilities[i] = abl;
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void removeDevilFruitAbility(Ability ablTemplate)
	{
		for(int i = 0; i < devilFruitAbilities.length; i++)
		{
			if(this.devilFruitAbilities[i] != null && this.devilFruitAbilities[i].getAttribute().getAttributeName().equalsIgnoreCase(ablTemplate.getAttribute().getAttributeName()))
			{
				this.devilFruitAbilities[i] = null;
				break;
			}
		}
	}
	
	@Override
	public boolean hasDevilFruitAbility(Ability ablTemplate)
	{
		for(int i = 0; i < devilFruitAbilities.length; i++)
		{
			if(this.devilFruitAbilities[i] != null && this.devilFruitAbilities[i].getAttribute().getAttributeName().equalsIgnoreCase(ablTemplate.getAttribute().getAttributeName()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Ability[] getDevilFruitAbilities()
	{ 
		return this.devilFruitAbilities; 
	}
	
	@Override
	public void clearDevilFruitAbilities()
	{
		for(int i = 0; i < this.devilFruitAbilities.length; i++)
			if(this.devilFruitAbilities[i] != null)
				this.devilFruitAbilities[i] = null;
	}
	
	
	/*
	 * Racial
	 */
	@Override
	public boolean addRacialAbility(Ability abl)
	{
		for(int i = 0; i < racialAbilities.length; i++)
		{
			if(this.racialAbilities[i] == null && !this.hasRacialAbility(abl))
			{
				this.racialAbilities[i] = abl;
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void removeRacialAbility(Ability ablTemplate)
	{
		for(int i = 0; i < racialAbilities.length; i++)
		{
			if(this.racialAbilities[i] != null && this.racialAbilities[i].getAttribute().getAttributeName().equalsIgnoreCase(ablTemplate.getAttribute().getAttributeName()))
			{
				this.racialAbilities[i] = null;
				break;
			}
		}
	}
	
	@Override
	public boolean hasRacialAbility(Ability ablTemplate)
	{
		if(ablTemplate != null)
		{
			for(int i = 0; i < racialAbilities.length; i++)
			{
				if(this.racialAbilities[i] != null && this.racialAbilities[i].getAttribute().getAttributeName().equalsIgnoreCase(ablTemplate.getAttribute().getAttributeName()))
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	@Override
	public Ability[] getRacialAbilities()
	{ 
		return this.racialAbilities; 
	}
	
	@Override
	public void clearRacialAbilities()
	{
		for(int i = 0; i < this.racialAbilities.length; i++)
			if(this.racialAbilities[i] != null)
				this.racialAbilities[i] = null;
	}
	
	
	/*
	 * Haki
	 */
	@Override
	public boolean addHakiAbility(Ability abl)
	{
		for(int i = 0; i < hakiAbilities.length; i++)
		{
			if(this.hakiAbilities[i] == null && !this.hasHakiAbility(abl))
			{
				this.hakiAbilities[i] = abl;
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void removeHakiAbility(Ability ablTemplate)
	{
		for(int i = 0; i < hakiAbilities.length; i++)
		{
			if(this.hakiAbilities[i] != null && this.hakiAbilities[i].getAttribute().getAttributeName().equalsIgnoreCase(ablTemplate.getAttribute().getAttributeName()))
			{
				this.hakiAbilities[i] = null;
				break;
			}
		}
	}
	
	@Override
	public boolean hasHakiAbility(Ability ablTemplate)
	{
		return Arrays.stream(this.hakiAbilities).filter(x -> x != null && x.getAttribute().getAttributeName().equalsIgnoreCase(ablTemplate.getAttribute().getAttributeName())).findFirst().orElse(null) != null;
	}
	
	@Override
	public Ability[] getHakiAbilities()
	{ 
		return this.hakiAbilities; 
	}
	
	@Override
	public void clearHakiAbilities()
	{
		for(int i = 0; i < this.hakiAbilities.length; i++)
			if(this.hakiAbilities[i] != null)
				this.hakiAbilities[i] = null;
	}
	
	
	/*
	 * Hotbar
	 */
	@Override
	public Ability[] getAbilitiesInHotbar()
	{
		return this.hotbarAbilities;
	}
	
	@Override
	public boolean hasAbilityInHotbar(Ability ability)
	{
		return this.hasAbilityInHotbar(ability.getAttribute().getAttributeName());
	}
	
	@Override
	public boolean hasAbilityInHotbar(String abilityName)
	{
		return Arrays.stream(this.hotbarAbilities).filter(x -> x != null && x.getAttribute().getAttributeName().equalsIgnoreCase(abilityName)).findFirst().orElse(null) != null;
	}
	
	@Override
	public void setAbilityInSlot(int slot, Ability abl)
	{
		this.hotbarAbilities[slot] = abl;
	}
	
	@Override
	public Ability getHotbarAbilityFromSlot(int slot)
	{
		return this.hotbarAbilities[slot];
	}
	
	@Override
	public Ability getHotbarAbilityFromName(String name)
	{
		return Arrays.stream(this.getAbilitiesInHotbar()).filter(x -> 
		{
			return x != null && x.getAttribute() != null && x.getAttribute().getAttributeName().equalsIgnoreCase(name);
		}).findFirst().orElse(null);     
	}
	
	@Override
	public int countAbilitiesInHotbar()
	{
		return this.hotbarAbilities.length;
	}
	
	public void clearHotbar(PlayerEntity player)
	{
		for(int i = 0; i < this.hotbarAbilities.length; i++)
			if(this.hotbarAbilities[i] != null)
			{
				if(this.hotbarAbilities[i].isPassiveActive())
					this.hotbarAbilities[i].endPassive(player);
				
				this.hotbarAbilities[i] = null;
			}
	}
	
	public void clearHotbarFromList(PlayerEntity player, Ability[] list)
	{
		for (int i = 0; i < this.hotbarAbilities.length; i++)
		{
			Ability abilityToRemove = this.getHotbarAbilityFromSlot(i);
			for (Ability ability : list)
			{
				if (abilityToRemove != null && abilityToRemove.getAttribute().getAttributeName().equalsIgnoreCase(ability.getAttribute().getAttributeName()))
					this.setAbilityInSlot(i, null);
			}
		}
	}

	@Override
	public Ability getPreviouslyUsedAbility()
	{
		return this.previouslyUsedAbility;
	}

	@Override
	public void setPreviouslyUsedAbility(Ability abl)
	{
		this.previouslyUsedAbility = abl;
	}

	@Override
	public boolean isInCombatMode()
	{
		return this.isInCombatMode;
	}

	@Override
	public void setCombatMode(boolean value)
	{
		this.isInCombatMode = value;
	}
}
