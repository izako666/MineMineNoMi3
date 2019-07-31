package xyz.pixelatedw.mineminenomi.api.data.abilitydata;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import xyz.pixelatedw.mineminenomi.api.CapabilityProviderSerializable;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.AbilityManager;

public class AbilityDataCapability
{
	@CapabilityInject(IAbilityData.class)
	public static final Capability<IAbilityData> INSTANCE = null;

	public static void register()
	{
		CapabilityManager.INSTANCE.register(IAbilityData.class, new Capability.IStorage<IAbilityData>()
		{

			@Override
			public INBT writeNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side)
			{
				CompoundNBT props = new CompoundNBT();

				props.putBoolean("combatMode", instance.isInCombatMode());

				if (instance.getPreviouslyUsedAbility() != null)
					props.put("previouslyUsedAbility", saveNLOBData(instance.getPreviouslyUsedAbility()));

				for (int i = 0; i < instance.getAbilitiesInHotbar().length; i++)
				{
					Ability ability = instance.getAbilitiesInHotbar()[i];
					if (ability != null)
						props.put("hotbar_ability_" + i, saveNLOBData(ability));
				}

				for (int i = 0; i < instance.getDevilFruitAbilities().length; i++)
				{
					Ability ability = instance.getDevilFruitAbilities()[i];
					if (ability != null)
						props.put("devilfruits_ability_" + i, saveNLOBData(ability));
				}

				for (int i = 0; i < instance.getRacialAbilities().length; i++)
				{
					Ability ability = instance.getRacialAbilities()[i];
					if (ability != null)
						props.put("racial_ability_" + i, saveNLOBData(ability));
				}

				for (int i = 0; i < instance.getHakiAbilities().length; i++)
				{
					Ability ability = instance.getHakiAbilities()[i];
					if (ability != null)
						props.put("haki_ability_" + i, saveNLOBData(ability));
				}

				return props;
			}

			private CompoundNBT saveNLOBData(Ability abl)
			{
				CompoundNBT data = new CompoundNBT();

				data.putString("name", abl.getAttribute().getAttributeName());
				data.putBoolean("isOnCooldown", abl.isOnCooldown());
				data.putBoolean("isCharging", abl.isCharging());
				data.putBoolean("isPassiveActive", abl.isPassiveActive());

				return data;
			}

			@Override
			public void readNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side, INBT nbt)
			{
				CompoundNBT props = (CompoundNBT) nbt;

				try
				{
					instance.setCombatMode(props.getBoolean("combatMode"));

					instance.setPreviouslyUsedAbility(this.loadAbilityFromNLOB(props.getCompound("previouslyUsedAbility")));

					for (int i = 0; i < instance.getAbilitiesInHotbar().length; i++)
						instance.getAbilitiesInHotbar()[i] = this.loadAbilityFromNLOB(props.getCompound("hotbar_ability_" + i));

					for (int i = 0; i < instance.getDevilFruitAbilities().length; i++)
						instance.getDevilFruitAbilities()[i] = this.loadAbilityFromNLOB(props.getCompound("devilfruits_ability_" + i));

					for (int i = 0; i < instance.getRacialAbilities().length; i++)
						instance.getRacialAbilities()[i] = this.loadAbilityFromNLOB(props.getCompound("racial_ability_" + i));

					for (int i = 0; i < instance.getHakiAbilities().length; i++)
						instance.getHakiAbilities()[i] = this.loadAbilityFromNLOB(props.getCompound("haki_ability_" + i));
				}
				catch (Exception e)
				{
					Logger.getGlobal().log(Level.SEVERE, "Ability is not registered correctly or could not be found in the master list !");
					e.printStackTrace();
				}
			}

			private Ability loadAbilityFromNLOB(CompoundNBT props)
			{
				if (props == null)
					return null;

				String ablName = props.getString("name");

				Ability ability = null;
				try
				{
					if (AbilityManager.instance().getAbilityByName(WyHelper.getFancyName(ablName)) != null)
					{
						ability = AbilityManager.instance().getAbilityByName(WyHelper.getFancyName(ablName)).getClass().newInstance();
						ability.setCooldownActive(props.getBoolean("isOnCooldown"));
						ability.setChargeActive(props.getBoolean("isCharging"));
						ability.setPassiveActive(props.getBoolean("isPassiveActive"));
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

				return ability;
			}

		}, AbilityDataBase::new);
	}

	public static IAbilityData get(final LivingEntity entity)
	{
		return entity.getCapability(INSTANCE, null).orElse(null);
	}

	public static ICapabilityProvider createProvider(final IAbilityData data)
	{
		return new CapabilityProviderSerializable<>(INSTANCE, null, data);
	}
}
