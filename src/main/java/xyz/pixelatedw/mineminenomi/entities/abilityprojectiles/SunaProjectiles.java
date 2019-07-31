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

public class SunaProjectiles 
{
	
	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();

	public static final EntityType BARJAN = WyRegistry.registerEntityType("barjan", Barjan::new, (spawnedEntity, world) -> new Barjan(world));
	
	static
	{
		projectiles.put(ModAttributes.BARJAN, new Data(BARJAN, Barjan.class));
	}
	
	public static class Barjan extends AbilityProjectile
	{
		public Barjan(World world)
		{super(BARJAN, world);}
	
		public Barjan(EntityType type, World world)
		{super(type, world);}
		
		public Barjan(World world, double x, double y, double z)
		{super(BARJAN, world, x, y, z);}
		
		public Barjan(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(BARJAN, world, player, attr);		
		}
	}	

}
