package xyz.pixelatedw.mineminenomi.data.entity.haki;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import xyz.pixelatedw.mineminenomi.api.CapabilityProviderSerializable;

public class HakiDataCapability
{
	@CapabilityInject(IHakiData.class)
	public static final Capability<IHakiData> INSTANCE = null;

	public static void register()
	{
		CapabilityManager.INSTANCE.register(IHakiData.class, new Capability.IStorage<IHakiData>()
		{

			@Override
			public INBT writeNBT(Capability<IHakiData> capability, IHakiData instance, Direction side)
			{
				CompoundNBT props = new CompoundNBT();

				props.putBoolean("hasKenHakiActive", instance.hasKenHakiActive());
				props.putBoolean("hasBusoHakiActive", instance.hasBusoHakiActive());
				
				return props;
			}

			@Override
			public void readNBT(Capability<IHakiData> capability, IHakiData instance, Direction side, INBT nbt)
			{
				CompoundNBT props = (CompoundNBT) nbt;

				instance.setKenHakiActive(props.getBoolean("hasKenHakiActive"));
				instance.setBusoHakiActive(props.getBoolean("hasBusoHakiActive"));
			}

		}, HakiDataBase::new);
	}
	
	public static IHakiData get(final LivingEntity entity)
	{
		return entity.getCapability(INSTANCE, null).orElse(null);
	}

	public static ICapabilityProvider createProvider(final IHakiData data)
	{
		return new CapabilityProviderSerializable<>(INSTANCE, null, data);
	}
}
