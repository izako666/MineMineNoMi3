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

public class ItoProjectiles 
{
	
	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType TAMAITO = WyRegistry.registerEntityType("tamaito", Tamaito::new, (spawnedEntity, world) -> new Tamaito(world));
	public static final EntityType OVERHEAT = WyRegistry.registerEntityType("overheat", Overheat::new, (spawnedEntity, world) -> new Overheat(world));

	static
	{
		projectiles.put(ModAttributes.TAMAITO, new Data(TAMAITO, Tamaito.class));
		projectiles.put(ModAttributes.OVERHEAT, new Data(OVERHEAT, Overheat.class));
	}
	
	public static class Tamaito extends AbilityProjectile
	{
		public Tamaito(World world)
		{super(TAMAITO, world);}
		
		public Tamaito(EntityType type, World world)
		{super(type, world);}
		
		public Tamaito(World world, double x, double y, double z)
		{super(TAMAITO, world, x, y, z);}
		
		public Tamaito(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(TAMAITO, world, player, attr);		
		}
	}
	
	public static class Overheat extends AbilityProjectile
	{
		public Overheat(World world)
		{super(OVERHEAT, world);}
		
		public Overheat(EntityType type, World world)
		{super(type, world);}
		
		public Overheat(World world, double x, double y, double z)
		{super(OVERHEAT, world, x, y, z);}
		
		public Overheat(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(OVERHEAT, world, player, attr);		
		}
	}	
	

}
