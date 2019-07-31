package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class ModParticles
{
	//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registry
	{
		public static final BasicParticleType MERA_FIRE = new BasicParticleType(false);
		
		@SubscribeEvent
		public static void registerEntityTypes(RegistryEvent.Register<ParticleType<?>> event)
		{
			if (!event.getName().equals(ForgeRegistries.PARTICLE_TYPES.getRegistryName()))
				return;

			// Register particle types
			event.getRegistry().registerAll
			(
				MERA_FIRE
			);
			
			//Minecraft.getInstance().particles.registerFactory(particleTypeIn, particleMetaFactoryIn);
		}
	}
}
