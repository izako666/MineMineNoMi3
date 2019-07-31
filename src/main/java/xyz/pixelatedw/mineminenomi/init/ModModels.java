package xyz.pixelatedw.mineminenomi.init;

import java.util.HashMap;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityRenderer;

public class ModModels
{
    public static void registerRenderers() 
    {
    	for(HashMap<AbilityAttribute, AbilityProjectile.Data> map : ModDevilFruits.ALL_PROJECTILES)
    	{
    		map.forEach((key, value) -> 
    		{
    			RenderingRegistry.registerEntityRenderingHandler(value.getEntityClass(), new AbilityRenderer.Factory(key) );			
    		});
    	}
    }
}
