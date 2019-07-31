package xyz.pixelatedw.mineminenomi.data.entity.extraeffects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import xyz.pixelatedw.mineminenomi.api.CapabilityProviderSerializable;

public class ExtraEffectCapability
{
	@CapabilityInject(IExtraEffect.class)
	public static final Capability<IExtraEffect> INSTANCE = null;

	public static void register()
	{
		CapabilityManager.INSTANCE.register(IExtraEffect.class, new Capability.IStorage<IExtraEffect>()
		{

			@Override
			public INBT writeNBT(Capability<IExtraEffect> capability, IExtraEffect instance, Direction side)
			{
				CompoundNBT props = new CompoundNBT();
				
				for(int i = 0; i < instance.getExtraEffects().length; i++)
					if(instance.getExtraEffects()[i] != null && !instance.getExtraEffects()[i].isEmpty())
						props.putString("extraEffect_" + i, instance.getExtraEffects()[i]);

				return props;
			}

			@Override
			public void readNBT(Capability<IExtraEffect> capability, IExtraEffect instance, Direction side, INBT nbt)
			{
				CompoundNBT props = (CompoundNBT) nbt;

				for(int i = 0; i < instance.getExtraEffects().length; i++)
					instance.getExtraEffects()[i] = props.getString("extraEffect_" + i);
			}
			
		}, () -> new ExtraEffectBase());
	}
	
	public static IExtraEffect get(final LivingEntity entity)
	{
		return entity.getCapability(INSTANCE, null).orElse(null);
	}

	public static ICapabilityProvider createProvider(final IExtraEffect data)
	{
		return new CapabilityProviderSerializable<>(INSTANCE, null, data);
	}
}
