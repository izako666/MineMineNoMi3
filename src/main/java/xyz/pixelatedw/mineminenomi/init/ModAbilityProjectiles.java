package xyz.pixelatedw.mineminenomi.init;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.ModMain;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityRenderer;

public class ModAbilityProjectiles
{	
		
    public static void registerRenderers() 
    {
    	ModMain.LOGGER.info("Register Renderers Start");
    	
    	for(HashMap<AbilityAttribute, AbilityProjectile.Data> map : ModDevilFruits.ALL_PROJECTILES)
    	{
    		map.forEach((key, value) -> 
    		{
    			System.out.println(value.getEntityClass());
    			RenderingRegistry.registerEntityRenderingHandler(value.getEntityClass(), new AbilityRenderer.Factory(key) );			
    		});
    	}
    	
		ModMain.LOGGER.info("Register Renderers End");
    }

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class Registry 
	{
		
		@SubscribeEvent
		public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> event)
		{
	        if (!event.getName().equals(ForgeRegistries.ENTITIES.getRegistryName())) return;
	        ModMain.LOGGER.info("Register Entities Start");
	        
			// Register projectiles
	    	for(HashMap<AbilityAttribute, AbilityProjectile.Data> map : ModDevilFruits.ALL_PROJECTILES)
	    	{
	    		map.forEach((key, value) -> 
	    		{
	    			System.out.println(value.getEntityType());
	    			event.getRegistry().register(value.getEntityType());
	    		});
	    	}
	    	
			ModMain.LOGGER.info("Register Entities End");

		}
	}

}
