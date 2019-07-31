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

public class BaneProjectiles 
{

	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType SPRING_DEATH_KNOCK = WyRegistry.registerEntityType("spring_death_knock", SpringDeathKnock::new, (spawnedEntity, world) -> new SpringDeathKnock(world));
	
	static
	{
		projectiles.put(ModAttributes.SPRING_DEATH_KNOCK, new Data(SPRING_DEATH_KNOCK, SpringDeathKnock.class));
	}
	
	public static class SpringDeathKnock extends AbilityProjectile
	{
		public SpringDeathKnock(World world)
		{super(SPRING_DEATH_KNOCK, world);}
		
		public SpringDeathKnock(EntityType type, World world)
		{super(type, world);}
		
		public SpringDeathKnock(World world, double x, double y, double z)
		{super(SPRING_DEATH_KNOCK, world, x, y, z);}
		
		public SpringDeathKnock(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(SPRING_DEATH_KNOCK, world, player, attr);		
		}
	}	
	
	
}
