package xyz.pixelatedw.mineminenomi.data.entity.entitystats;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import xyz.pixelatedw.mineminenomi.api.CapabilityProviderSerializable;

public class EntityStatsCapability
{
	@CapabilityInject(IEntityStats.class)
	public static final Capability<IEntityStats> INSTANCE = null;

	public static void register()
	{
		CapabilityManager.INSTANCE.register(IEntityStats.class, new Capability.IStorage<IEntityStats>()
		{

			@Override
			public INBT writeNBT(Capability<IEntityStats> capability, IEntityStats instance, Direction side)
			{
				CompoundNBT props = new CompoundNBT();
				
				props.putInt("doriki", instance.getDoriki());
				props.putInt("belly", instance.getBelly());
				props.putInt("extol", instance.getExtol());
				props.putLong("bounty", instance.getBounty());
				props.putInt("cola", instance.getCola());
				props.putInt("maxCola", instance.getMaxCola());
				props.putInt("ultraCola", instance.getUltraCola());
				props.putString("faction", instance.getFaction());
				props.putString("race", instance.getRace());
				props.putString("fightingStyle", instance.getFightingStyle());				
				props.putBoolean("hasShadow", instance.hasShadow());
				props.putBoolean("hasHeart", instance.hasHeart());
				
				return props;
			}

			@Override
			public void readNBT(Capability<IEntityStats> capability, IEntityStats instance, Direction side, INBT nbt)
			{
				CompoundNBT props = (CompoundNBT) nbt;

				instance.setDoriki(props.getInt("doriki"));
				instance.setBelly(props.getInt("belly"));
				instance.setExtol(props.getInt("extol"));
				instance.setBounty(props.getLong("bounty"));
				instance.setCola(props.getInt("cola"));
				instance.setMaxCola(props.getInt("maxCola"));
				instance.setUltraCola(props.getInt("ultraCola"));
				instance.setFaction(props.getString("faction"));
				instance.setRace(props.getString("race"));
				instance.setFightingStyle(props.getString("fightingStyle"));
				instance.setShadow(props.getBoolean("hasShadow"));
				instance.setHeart(props.getBoolean("hasHeart"));
			}

		}, () -> new EntityStatsBase());
	}
	
	public static IEntityStats get(final LivingEntity entity)
	{
		return entity.getCapability(INSTANCE, null).orElse(new EntityStatsBase());
	}

	public static ICapabilityProvider createProvider(final IEntityStats data)
	{
		return new CapabilityProviderSerializable<>(INSTANCE, null, data);
	}
}
