package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile.Data;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class OpeProjectiles 
{

	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType GAMMA_KNIFE = WyRegistry.registerEntityType("gamma_knife", GammaKnife::new, (spawnedEntity, world) -> new GammaKnife(world));
	
	static
	{
		projectiles.put(ModAttributes.GAMMA_KNIFE, new Data(GAMMA_KNIFE, GammaKnife.class));
	}
	
	
	public static class GammaKnife extends AbilityProjectile
	{
		public GammaKnife(World world)
		{super(GAMMA_KNIFE, world);}
		
		public GammaKnife(EntityType type, World world)
		{super(type, world);}
		
		public GammaKnife(World world, double x, double y, double z)
		{super(GAMMA_KNIFE, world, x, y, z);}
		
		public GammaKnife(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(GAMMA_KNIFE, world, player, attr);		
		}
	}	
	
	
}
