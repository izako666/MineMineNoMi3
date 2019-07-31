package xyz.pixelatedw.mineminenomi.init;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;

public class ModEntities
{
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registry
	{
		@SubscribeEvent
		public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> event)
		{
			if (!event.getName().equals(ForgeRegistries.ENTITIES.getRegistryName()))
				return;

			// Register projectiles
			for (HashMap<AbilityAttribute, AbilityProjectile.Data> map : ModDevilFruits.ALL_PROJECTILES)
			{
				map.forEach((key, value) ->
				{
					event.getRegistry().register(value.getEntityType());
				});
			}
		}
	}

}
