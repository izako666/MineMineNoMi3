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

public class UshiGiraffeProjectiles
{
	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType BIGAN = WyRegistry.registerEntityType("bigan", Bigan::new, (spawnedEntity, world) -> new Bigan(world));
	
	static
	{
		projectiles.put(ModAttributes.BIGAN, new Data(BIGAN, Bigan.class));
	}
		
	public static class Bigan extends AbilityProjectile
	{
		public Bigan(World world)
		{super(BIGAN, world);}
		
		public Bigan(EntityType type, World world)
		{super(type, world);}
		
		public Bigan(World world, double x, double y, double z)
		{super(BIGAN, world, x, y, z);}
		
		public Bigan(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(BIGAN, world, player, attr);		
		}	
	}
}
