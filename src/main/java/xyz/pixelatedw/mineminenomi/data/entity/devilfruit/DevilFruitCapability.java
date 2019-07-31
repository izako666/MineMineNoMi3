package xyz.pixelatedw.mineminenomi.data.entity.devilfruit;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import xyz.pixelatedw.mineminenomi.api.CapabilityProviderSerializable;

public class DevilFruitCapability
{
	@CapabilityInject(IDevilFruit.class)
	public static final Capability<IDevilFruit> INSTANCE = null;

	public static void register()
	{
		CapabilityManager.INSTANCE.register(IDevilFruit.class, new Capability.IStorage<IDevilFruit>()
		{

			@Override
			public INBT writeNBT(Capability<IDevilFruit> capability, IDevilFruit instance, Direction side)
			{
				CompoundNBT props = new CompoundNBT();

				props.putString("devilFruit", instance.getDevilFruit());
				props.putBoolean("isLogia", instance.isLogia());
				props.putBoolean("hasYamiPower", instance.hasYamiPower());
				props.putString("zoanPoint", instance.getZoanPoint());

				return props;
			}

			@Override
			public void readNBT(Capability<IDevilFruit> capability, IDevilFruit instance, Direction side, INBT nbt)
			{
				CompoundNBT props = (CompoundNBT) nbt;

				instance.setDevilFruit(props.getString("devilFruit"));
				instance.setLogia(props.getBoolean("isLogia"));
				instance.setYamiPower(props.getBoolean("hasYamiPower"));
				instance.setZoanPoint(props.getString("zoanPoint"));
			}

		}, () -> new DevilFruitBase());
	}
	
	public static IDevilFruit get(final LivingEntity entity)
	{
		return entity.getCapability(INSTANCE, null).orElse(null);
	}

	public static ICapabilityProvider createProvider(final IDevilFruit data)
	{
		return new CapabilityProviderSerializable<>(INSTANCE, null, data);
	}
}
