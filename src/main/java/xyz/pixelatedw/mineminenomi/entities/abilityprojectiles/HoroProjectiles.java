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

public class HoroProjectiles 
{
	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType NEGATIVE_HOLLOW = WyRegistry.registerEntityType("negative_hollow", NegativeHollow::new, (spawnedEntity, world) -> new NegativeHollow(world));
	public static final EntityType MINI_HOLLOW = WyRegistry.registerEntityType("mini_hollow", MiniHollow::new, (spawnedEntity, world) -> new MiniHollow(world));
	public static final EntityType TOKU_HOLLOW = WyRegistry.registerEntityType("toku_hollow", TokuHollow::new, (spawnedEntity, world) -> new TokuHollow(world));

	
	static
	{
		projectiles.put(ModAttributes.NEGATIVE_HOLLOW, new Data(NEGATIVE_HOLLOW, NegativeHollow.class));
		projectiles.put(ModAttributes.MINI_HOLLOW, new Data(MINI_HOLLOW, MiniHollow.class));
		projectiles.put(ModAttributes.TOKU_HOLLOW, new Data(TOKU_HOLLOW, TokuHollow.class));
	}
	
	public static class TokuHollow extends AbilityProjectile
	{
		public TokuHollow(World world)
		{super(TOKU_HOLLOW, world);}
		
		public TokuHollow(EntityType type, World world)
		{super(type, world);}
		
		public TokuHollow(World world, double x, double y, double z)
		{super(TOKU_HOLLOW, world, x, y, z);}
		
		public TokuHollow(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(TOKU_HOLLOW, world, player, attr);		
		}
	}	
	
	public static class MiniHollow extends AbilityProjectile
	{
		public MiniHollow(World world)
		{super(MINI_HOLLOW, world);}
		
		public MiniHollow(EntityType type, World world)
		{super(type, world);}
		
		public MiniHollow(World world, double x, double y, double z)
		{super(MINI_HOLLOW, world, x, y, z);}
		
		public MiniHollow(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(MINI_HOLLOW, world, player, attr);		
		}
	}	
	
	public static class NegativeHollow extends AbilityProjectile
	{
		public NegativeHollow(World world)
		{super(TOKU_HOLLOW, world);}
		
		public NegativeHollow(EntityType type, World world)
		{super(type, world);}
		
		public NegativeHollow(World world, double x, double y, double z)
		{super(TOKU_HOLLOW, world, x, y, z);}
		
		public NegativeHollow(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(TOKU_HOLLOW, world, player, attr);		
		}
	}	

}
