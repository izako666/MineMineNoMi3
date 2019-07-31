package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile.Data;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class GoeProjectiles 
{
	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
		
	public static final EntityType TODOROKI = WyRegistry.registerEntityType("todoroki", Todoroki::new, (spawnedEntity, world) -> new Todoroki(world));
	
	static
	{
		projectiles.put(ModAttributes.TODOROKI, new Data(TODOROKI, Todoroki.class));
	}
	
	public static class Todoroki extends AbilityProjectile
	{
		public Todoroki(World world)
		{super(TODOROKI, world);}
		
		public Todoroki(EntityType type, World world)
		{super(type, world);}
		
		public Todoroki(World world, double x, double y, double z)
		{super(TODOROKI, world, x, y, z);}
		
		public Todoroki(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(TODOROKI, world, player, attr);		
		}	
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			AbilityExplosion explosion = WyHelper.newExplosion(this.getThrower(), this.posX, this.posY, this.posZ, 3);
			explosion.setDamageOwner(false);
			explosion.setSmokeParticles("");
			explosion.doExplosion();
		}	
	}
}
