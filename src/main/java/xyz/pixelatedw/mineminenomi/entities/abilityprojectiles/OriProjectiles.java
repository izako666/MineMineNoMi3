package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile.Data;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModMiscBlocks;

public class OriProjectiles
{

	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();

	public static final EntityType AWASE_BAORI = WyRegistry.registerEntityType("awase_baori", AwaseBaori::new, (spawnedEntity, world) -> new AwaseBaori(world));
	
	static
	{
		projectiles.put(ModAttributes.AWASE_BAORI, new Data(AWASE_BAORI, AwaseBaori.class));
	}

	public static class AwaseBaori extends AbilityProjectile
	{
		public AwaseBaori(World world)
		{
			super(AWASE_BAORI, world);
		}
		
		public AwaseBaori(EntityType type, World world)
		{super(type, world);}
		
		public AwaseBaori(World world, double x, double y, double z)
		{
			super(AWASE_BAORI, world, x, y, z);
		}

		public AwaseBaori(World world, LivingEntity player, AbilityAttribute attr)
		{
			super(AWASE_BAORI, world, player, attr);
		}

		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			if (CommonConfig.instance.getGriefing())
			{
				if (hit.hitInfo != null && hit.getType() == Type.ENTITY)
				{
					WyHelper.createEmptyCube((Entity)hit.hitInfo, new int[]
					{
							2, 3, 2
					}, ModMiscBlocks.oriBars, "air", "foliage", "liquids");
				}
			}
		}
	}
}
