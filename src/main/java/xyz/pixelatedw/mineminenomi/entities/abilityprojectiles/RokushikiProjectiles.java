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

public class RokushikiProjectiles 
{

	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType RANKYAKU = WyRegistry.registerEntityType("rankyaku", Rankyaku::new, (spawnedEntity, world) -> new Rankyaku(world));
	
	static
	{
		projectiles.put(ModAttributes.RANKYAKU, new Data(RANKYAKU, Rankyaku.class));
	}
	
	public static class Rankyaku extends AbilityProjectile
	{
		public Rankyaku(World world)
		{super(RANKYAKU, world);}
		
		public Rankyaku(EntityType type, World world)
		{super(type, world);}
		
		public Rankyaku(World world, double x, double y, double z)
		{super(RANKYAKU, world, x, y, z);}
		
		public Rankyaku(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(RANKYAKU, world, player, attr);		
		}
	}
}
