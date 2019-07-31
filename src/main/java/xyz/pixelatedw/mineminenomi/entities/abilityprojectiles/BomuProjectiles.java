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

public class BomuProjectiles 
{

	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType NOSE_FANCY_CANNON = WyRegistry.registerEntityType("nose_fancy_cannon", NoseFancyCannon::new, (spawnedEntity, world) -> new NoseFancyCannon(world));
	
	static
	{
		projectiles.put(ModAttributes.NOSE_FANCY_CANNON, new Data(NOSE_FANCY_CANNON, NoseFancyCannon.class));
	}
	
	public static class NoseFancyCannon extends AbilityProjectile
	{
		public NoseFancyCannon(World world)
		{super(NOSE_FANCY_CANNON, world);}
		
		public NoseFancyCannon(EntityType type, World world)
		{super(type, world);}
		
		public NoseFancyCannon(World world, double x, double y, double z)
		{super(NOSE_FANCY_CANNON, world, x, y, z);}
		
		public NoseFancyCannon(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(NOSE_FANCY_CANNON, world, player, attr);		
		}
	}	
	
	
}
