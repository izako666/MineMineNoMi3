package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.effects.DFEffectDoruLock;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile.Data;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class DoruProjectiles 
{

	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType DORU_DORU_ARTS_MORI = WyRegistry.registerEntityType("doru_doru_arts_mori", DoruDoruArtsMori::new, (spawnedEntity, world) -> new DoruDoruArtsMori(world));
	public static final EntityType CANDLE_LOCK = WyRegistry.registerEntityType("candle_lock", CandleLock::new, (spawnedEntity, world) -> new CandleLock(world));

	static
	{
		projectiles.put(ModAttributes.DORU_DORU_ARTS_MORI, new Data(DORU_DORU_ARTS_MORI, DoruDoruArtsMori.class));
		projectiles.put(ModAttributes.CANDLE_LOCK, new Data(CANDLE_LOCK, CandleLock.class));
	}
	
	public static class DoruDoruArtsMori extends AbilityProjectile
	{
		public DoruDoruArtsMori(World world)
		{super(DORU_DORU_ARTS_MORI, world);}
		
		public DoruDoruArtsMori(EntityType type, World world)
		{super(type, world);}
		
		public DoruDoruArtsMori(World world, double x, double y, double z)
		{super(DORU_DORU_ARTS_MORI, world, x, y, z);}
		
		public DoruDoruArtsMori(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(DORU_DORU_ARTS_MORI, world, player, attr);		
		}
	}
	
	public static class CandleLock extends AbilityProjectile
	{
		public CandleLock(World world)
		{super(CANDLE_LOCK, world);}
		
		public CandleLock(EntityType type, World world)
		{super(type, world);}
		
		public CandleLock(World world, double x, double y, double z)
		{super(CANDLE_LOCK, world, x, y, z);}
		
		public CandleLock(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(CANDLE_LOCK, world, player, attr);		
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{			
			if(hit.hitInfo != null && hit.getType() == Type.ENTITY && hit.hitInfo instanceof LivingEntity)
			{
				LivingEntity target = (LivingEntity) hit.hitInfo;

				new DFEffectDoruLock(target, 400);
			}
		}
	}
	
}
