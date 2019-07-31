package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile.Data;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModExtraAttributes;
import xyz.pixelatedw.mineminenomi.init.ModMiscBlocks;

public class KageProjectiles 
{
	
	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType TSUNOTOKAGE_PILLAR = WyRegistry.registerEntityType("tsunotokage_pillar", TsunotokagePillar::new, (spawnedEntity, world) -> new TsunotokagePillar(world));
	public static final EntityType BLACK_BOX = WyRegistry.registerEntityType("black_box", BlackBox::new, (spawnedEntity, world) -> new BlackBox(world));
	public static final EntityType BRICK_BAT = WyRegistry.registerEntityType("brick_bat", BrickBat::new, (spawnedEntity, world) -> new BrickBat(world));

	static
	{
		projectiles.put(ModExtraAttributes.TSUNOTOKAGE_PILLAR, new Data(TSUNOTOKAGE_PILLAR, TsunotokagePillar.class));
		projectiles.put(ModAttributes.BLACK_BOX, new Data(BLACK_BOX, BlackBox.class));
		projectiles.put(ModAttributes.BRICK_BAT, new Data(BRICK_BAT, BrickBat.class));
	}
	
	public static class BrickBat extends AbilityProjectile
	{
		public BrickBat(World world)
		{super(BRICK_BAT, world);}

		public BrickBat(EntityType type, World world)
		{super(type, world);}
		
		public BrickBat(World world, double x, double y, double z)
		{super(BRICK_BAT, world, x, y, z);}
		
		public BrickBat(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(BRICK_BAT, world, player, attr);		
		}
	}	

	public static class BlackBox extends AbilityProjectile
	{
		public BlackBox(World world)
		{super(BLACK_BOX, world);}

		public BlackBox(EntityType type, World world)
		{super(type, world);}
		
		public BlackBox(World world, double x, double y, double z)
		{super(BLACK_BOX, world, x, y, z);}
		
		public BlackBox(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(BLACK_BOX, world, player, attr);		
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			if(hit.hitInfo != null && hit.getType() == Type.ENTITY)
			{
				WyHelper.createFilledCube((Entity)hit.hitInfo, new int[] {2, 2, 2}, ModMiscBlocks.kageBlock, "air", "foliage");
			}
		}
	}	
	
	public static class TsunotokagePillar extends AbilityProjectile
	{
		public TsunotokagePillar(World world)
		{super(TSUNOTOKAGE_PILLAR, world);}

		public TsunotokagePillar(EntityType type, World world)
		{super(type, world);}
		
		public TsunotokagePillar(World world, double x, double y, double z)
		{super(TSUNOTOKAGE_PILLAR, world, x, y, z);}
		
		public TsunotokagePillar(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(TSUNOTOKAGE_PILLAR, world, player, attr);		
			this.getSize(Pose.STANDING).scale(10, 2);
		}
		
		@Override
		public void tick()
		{
			for(LivingEntity e : WyHelper.getEntitiesNear(this, 2))
				e.attackEntityFrom(DamageSource.causePlayerDamage((PlayerEntity) this.getThrower()), 30);
			super.tick();
		}
	}

}
