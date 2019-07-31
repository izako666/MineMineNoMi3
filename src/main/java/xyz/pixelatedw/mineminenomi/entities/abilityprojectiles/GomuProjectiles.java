package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyHelper.Direction;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile.Data;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModExtraAttributes;

public class GomuProjectiles
{

	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();

	public static final EntityType GOMU_GOMU_NO_ROCKET = WyRegistry.registerEntityType("gomu_gomu_no_rocket", GomuGomuNoRocket::new, (spawnedEntity, world) -> new GomuGomuNoRocket(world));
	
	public static final EntityType GOMU_GOMU_NO_PISTOL = WyRegistry.registerEntityType("gomu_gomu_no_pistol", GomuGomuNoPistol::new, (spawnedEntity, world) -> new GomuGomuNoPistol(world));
	public static final EntityType GOMU_GOMU_NO_JET_PISTOL = WyRegistry.registerEntityType("gomu_gomu_no_jet_pistol", GomuGomuNoJetPistol::new, (spawnedEntity, world) -> new GomuGomuNoJetPistol(world));
	public static final EntityType GOMU_GOMU_NO_ELEPHANT_GUN = WyRegistry.registerEntityType("gomu_gomu_no_elephant_gun", GomuGomuNoElephantGun::new, (spawnedEntity, world) -> new GomuGomuNoElephantGun(world));
	public static final EntityType GOMU_GOMU_NO_KONG_GUN = WyRegistry.registerEntityType("gomu_gomu_no_kong_gun", GomuGomuNoKongGun::new, (spawnedEntity, world) -> new GomuGomuNoKongGun(world));
	
	public static final EntityType GOMU_GOMU_NO_BAZOOKA = WyRegistry.registerEntityType("gomu_gomu_no_bazooka", GomuGomuNoBazooka::new, (spawnedEntity, world) -> new GomuGomuNoBazooka(world));
	public static final EntityType GOMU_GOMU_NO_JET_BAZOOKA = WyRegistry.registerEntityType("gomu_gomu_no_jet_bazooka", GomuGomuNoJetBazooka::new, (spawnedEntity, world) -> new GomuGomuNoJetBazooka(world));
	public static final EntityType GOMU_GOMU_NO_GRIZZLY_MAGNUM = WyRegistry.registerEntityType("gomu_gomu_no_grizzly_magnum", GomuGomuNoGrizzlyMagnum::new, (spawnedEntity, world) -> new GomuGomuNoGrizzlyMagnum(world));
	public static final EntityType GOMU_GOMU_NO_LEO_BAZOOKA = WyRegistry.registerEntityType("gomu_gomu_no_leo_bazooka", GomuGomuNoLeoBazooka::new, (spawnedEntity, world) -> new GomuGomuNoLeoBazooka(world));

	public static final EntityType GOMU_GOMU_NO_GATLING = WyRegistry.registerEntityType("gomu_gomu_no_gatling", GomuGomuNoGatling::new, (spawnedEntity, world) -> new GomuGomuNoGatling(world));
	public static final EntityType GOMU_GOMU_NO_JET_GATLING = WyRegistry.registerEntityType("gomu_gomu_no_jet_gatling", GomuGomuNoJetGatling::new, (spawnedEntity, world) -> new GomuGomuNoJetGatling(world));
	public static final EntityType GOMU_GOMU_NO_ELEPHANT_GATLING = WyRegistry.registerEntityType("gomu_gomu_no_elephant_gatling", GomuGomuNoElephantGatling::new, (spawnedEntity, world) -> new GomuGomuNoElephantGatling(world));
	public static final EntityType GOMU_GOMU_NO_KONG_ORGAN = WyRegistry.registerEntityType("gomu_gomu_no_kong_organ", GomuGomuNoKongOrgan::new, (spawnedEntity, world) -> new GomuGomuNoKongOrgan(world));
	
	static
	{
		projectiles.put(ModAttributes.GOMU_GOMU_NO_ROCKET, new Data(GOMU_GOMU_NO_ROCKET, GomuGomuNoRocket.class));
		
		projectiles.put(ModExtraAttributes.GOMU_GOMU_NO_PISTOL, new Data(GOMU_GOMU_NO_PISTOL, GomuGomuNoPistol.class));
		projectiles.put(ModExtraAttributes.GOMU_GOMU_NO_JET_PISTOL, new Data(GOMU_GOMU_NO_JET_PISTOL, GomuGomuNoJetPistol.class));
		projectiles.put(ModExtraAttributes.GOMU_GOMU_NO_ELEPHANT_GUN, new Data(GOMU_GOMU_NO_ELEPHANT_GUN, GomuGomuNoElephantGun.class));
		projectiles.put(ModExtraAttributes.GOMU_GOMU_NO_KONG_GUN, new Data(GOMU_GOMU_NO_KONG_GUN, GomuGomuNoKongGun.class));
		
		projectiles.put(ModExtraAttributes.GOMU_GOMU_NO_BAZOOKA, new Data(GOMU_GOMU_NO_BAZOOKA, GomuGomuNoBazooka.class));
		projectiles.put(ModExtraAttributes.GOMU_GOMU_NO_JET_BAZOOKA, new Data(GOMU_GOMU_NO_JET_BAZOOKA, GomuGomuNoJetBazooka.class));
		projectiles.put(ModExtraAttributes.GOMU_GOMU_NO_GRIZZLY_MAGNUM, new Data(GOMU_GOMU_NO_GRIZZLY_MAGNUM, GomuGomuNoGrizzlyMagnum.class));
		projectiles.put(ModExtraAttributes.GOMU_GOMU_NO_LEO_BAZOOKA, new Data(GOMU_GOMU_NO_LEO_BAZOOKA, GomuGomuNoLeoBazooka.class));
		
		projectiles.put(ModExtraAttributes.GOMU_GOMU_NO_GATLING, new Data(GOMU_GOMU_NO_GATLING, GomuGomuNoGatling.class));
		projectiles.put(ModExtraAttributes.GOMU_GOMU_NO_JET_GATLING, new Data(GOMU_GOMU_NO_JET_GATLING, GomuGomuNoJetGatling.class));
		projectiles.put(ModExtraAttributes.GOMU_GOMU_NO_ELEPHANT_GATLING, new Data(GOMU_GOMU_NO_ELEPHANT_GATLING, GomuGomuNoElephantGatling.class));
		projectiles.put(ModExtraAttributes.GOMU_GOMU_NO_KONG_ORGAN, new Data(GOMU_GOMU_NO_KONG_ORGAN, GomuGomuNoKongOrgan.class));
	}

	public static class GomuGomuNoRocket extends AbilityProjectile
	{
		public GomuGomuNoRocket(World world)
		{
			super(GOMU_GOMU_NO_ROCKET, world);
		}
		
		public GomuGomuNoRocket(EntityType type, World world)
		{super(type, world);}
		
		public GomuGomuNoRocket(World world, double x, double y, double z)
		{
			super(GOMU_GOMU_NO_ROCKET, world, x, y, z);
		}

		public GomuGomuNoRocket(World world, LivingEntity player, AbilityAttribute attr)
		{
			super(GOMU_GOMU_NO_ROCKET, world, player, attr);
		}

		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			PlayerEntity player = (PlayerEntity) this.getThrower();
			if (hit.hitInfo != null && hit.getType() == Type.BLOCK)
			{
				Direction dir = WyHelper.get8Directions(player);

				double mX = 0;
				double mY = 0;
				double mZ = 0;

				double powerX = Math.abs(hit.getHitVec().x - player.posX) / 5;
				double powerY = (hit.getHitVec().y - player.posY) / 4;
				double powerZ = Math.abs(hit.getHitVec().z - player.posZ) / 5;

				mY += powerY;

				if (dir == WyHelper.Direction.NORTH)
					mZ -= powerZ;
				if (dir == WyHelper.Direction.NORTH_WEST)
				{
					mZ -= powerZ;
					mX -= powerX;
				}
				if (dir == WyHelper.Direction.SOUTH)
					mZ += powerZ;
				if (dir == WyHelper.Direction.NORTH_EAST)
				{
					mZ -= powerZ;
					mX += powerX;
				}
				if (dir == WyHelper.Direction.WEST)
					mX -= powerX;
				if (dir == WyHelper.Direction.SOUTH_WEST)
				{
					mZ += powerZ;
					mX -= powerZ;
				}
				if (dir == WyHelper.Direction.EAST)
					mX += powerX;
				if (dir == WyHelper.Direction.SOUTH_EAST)
				{
					mZ += powerZ;
					mX += powerX;
				}

				DevilFruitsHelper.changeMotion("=", mX, mY, mZ, player);
			}
		}
	}

	public static class GomuGomuNoKongOrgan extends AbilityProjectile
	{
		public GomuGomuNoKongOrgan(World world)
		{
			super(GOMU_GOMU_NO_KONG_ORGAN, world);
		}
		
		public GomuGomuNoKongOrgan(EntityType type, World world)
		{super(type, world);}
		
		public GomuGomuNoKongOrgan(World world, double x, double y, double z)
		{
			super(GOMU_GOMU_NO_KONG_ORGAN, world, x, y, z);
		}

		public GomuGomuNoKongOrgan(World world, LivingEntity player, AbilityAttribute attr)
		{
			super(GOMU_GOMU_NO_KONG_ORGAN, world, player, attr);
		}
	}

	public static class GomuGomuNoElephantGatling extends AbilityProjectile
	{
		public GomuGomuNoElephantGatling(World world)
		{
			super(GOMU_GOMU_NO_ELEPHANT_GATLING, world);
		}
		
		public GomuGomuNoElephantGatling(EntityType type, World world)
		{super(type, world);}
		
		public GomuGomuNoElephantGatling(World world, double x, double y, double z)
		{
			super(GOMU_GOMU_NO_ELEPHANT_GATLING, world, x, y, z);
		}

		public GomuGomuNoElephantGatling(World world, LivingEntity player, AbilityAttribute attr)
		{
			super(GOMU_GOMU_NO_ELEPHANT_GATLING, world, player, attr);
		}
	}

	public static class GomuGomuNoJetGatling extends AbilityProjectile
	{
		public GomuGomuNoJetGatling(World world)
		{
			super(GOMU_GOMU_NO_JET_GATLING, world);
		}
		
		public GomuGomuNoJetGatling(EntityType type, World world)
		{super(type, world);}
		
		public GomuGomuNoJetGatling(World world, double x, double y, double z)
		{
			super(GOMU_GOMU_NO_JET_GATLING, world, x, y, z);
		}

		public GomuGomuNoJetGatling(World world, LivingEntity player, AbilityAttribute attr)
		{
			super(GOMU_GOMU_NO_JET_GATLING, world, player, attr);
		}

		@Override
		public void tick()
		{
			for (int i = 0; i < 2; i++)
			{
				double offsetX = (new Random().nextInt(5) + 1.0D - 2.5D) / 5.0D;
				double offsetY = (new Random().nextInt(5) + 1.0D - 2.5D) / 5.0D;
				double offsetZ = (new Random().nextInt(5) + 1.0D - 2.5D) / 5.0D;

				this.world.addParticle(ParticleTypes.SMOKE, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, 0.0D, 0.0D);
				this.world.addParticle(ParticleTypes.EXPLOSION, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, 0.0D, 0.0D);
			}
			super.tick();
		}
	}

	public static class GomuGomuNoGatling extends AbilityProjectile
	{
		public GomuGomuNoGatling(World world)
		{
			super(GOMU_GOMU_NO_GATLING, world);
		}
		
		public GomuGomuNoGatling(EntityType type, World world)
		{super(type, world);}
		
		public GomuGomuNoGatling(World world, double x, double y, double z)
		{
			super(GOMU_GOMU_NO_GATLING, world, x, y, z);
		}

		public GomuGomuNoGatling(World world, LivingEntity player, AbilityAttribute attr)
		{
			super(GOMU_GOMU_NO_GATLING, world, player, attr);
		}
	}

	public static class GomuGomuNoLeoBazooka extends AbilityProjectile
	{
		public GomuGomuNoLeoBazooka(World world)
		{
			super(GOMU_GOMU_NO_LEO_BAZOOKA, world);
		}
		
		public GomuGomuNoLeoBazooka(EntityType type, World world)
		{super(type, world);}
		
		public GomuGomuNoLeoBazooka(World world, double x, double y, double z)
		{
			super(GOMU_GOMU_NO_LEO_BAZOOKA, world, x, y, z);
		}

		public GomuGomuNoLeoBazooka(World world, LivingEntity player, AbilityAttribute attr)
		{
			super(GOMU_GOMU_NO_LEO_BAZOOKA, world, player, attr);
		}

		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			if (hit.hitInfo != null && hit.getType() == Type.ENTITY && hit.hitInfo instanceof LivingEntity)
			{
				LivingEntity entity = (LivingEntity) hit.hitInfo;
				double newPosX = 0, newPosY = 0, newPosZ = 0;

				int minPower = 13;
				int maxPower = 17;

				newPosY += 2;
				Direction dir = WyHelper.get4Directions(this.getThrower());
				if (dir == WyHelper.Direction.SOUTH)
					newPosZ += WyMathHelper.randomWithRange(minPower, maxPower);
				else if (dir == WyHelper.Direction.EAST)
					newPosX += WyMathHelper.randomWithRange(minPower, maxPower);
				else if (dir == WyHelper.Direction.NORTH)
					newPosZ -= WyMathHelper.randomWithRange(minPower, maxPower);
				else if (dir == WyHelper.Direction.WEST)
					newPosX -= WyMathHelper.randomWithRange(minPower, maxPower);

				entity.setPositionAndUpdate(entity.posX + newPosX, entity.posY + newPosY, entity.posZ + newPosZ);
			}
		}
	}

	public static class GomuGomuNoGrizzlyMagnum extends AbilityProjectile
	{
		public GomuGomuNoGrizzlyMagnum(World world)
		{
			super(GOMU_GOMU_NO_GRIZZLY_MAGNUM, world);
		}
		
		public GomuGomuNoGrizzlyMagnum(EntityType type, World world)
		{super(type, world);}
		
		public GomuGomuNoGrizzlyMagnum(World world, double x, double y, double z)
		{
			super(GOMU_GOMU_NO_GRIZZLY_MAGNUM, world, x, y, z);
		}

		public GomuGomuNoGrizzlyMagnum(World world, LivingEntity player, AbilityAttribute attr)
		{
			super(GOMU_GOMU_NO_GRIZZLY_MAGNUM, world, player, attr);
		}

		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			if (hit.hitInfo != null && hit.getType() == Type.ENTITY && hit.hitInfo instanceof LivingEntity)
			{
				LivingEntity entity = (LivingEntity) hit.hitInfo;
				double newPosX = 0, newPosY = 0, newPosZ = 0;

				int minPower = 8;
				int maxPower = 15;

				newPosY += 2;
				Direction dir = WyHelper.get4Directions(this.getThrower());
				if (dir == WyHelper.Direction.SOUTH)
					newPosZ += WyMathHelper.randomWithRange(minPower, maxPower);
				else if (dir == WyHelper.Direction.EAST)
					newPosX += WyMathHelper.randomWithRange(minPower, maxPower);
				else if (dir == WyHelper.Direction.NORTH)
					newPosZ -= WyMathHelper.randomWithRange(minPower, maxPower);
				else if (dir == WyHelper.Direction.WEST)
					newPosX -= WyMathHelper.randomWithRange(minPower, maxPower);

				entity.setPositionAndUpdate(entity.posX + newPosX, entity.posY + newPosY, entity.posZ + newPosZ);
			}
		}
	}

	public static class GomuGomuNoJetBazooka extends AbilityProjectile
	{
		public GomuGomuNoJetBazooka(World world)
		{
			super(GOMU_GOMU_NO_JET_BAZOOKA, world);
		}
		
		public GomuGomuNoJetBazooka(EntityType type, World world)
		{super(type, world);}
		
		public GomuGomuNoJetBazooka(World world, double x, double y, double z)
		{
			super(GOMU_GOMU_NO_JET_BAZOOKA, world, x, y, z);
		}

		public GomuGomuNoJetBazooka(World world, LivingEntity player, AbilityAttribute attr)
		{
			super(GOMU_GOMU_NO_JET_BAZOOKA, world, player, attr);
		}

		@Override
		public void tick()
		{
			for (int i = 0; i < 2; i++)
			{
				double offsetX = (new Random().nextInt(5) + 1.0D - 2.5D) / 5.0D;
				double offsetY = (new Random().nextInt(5) + 1.0D - 2.5D) / 5.0D;
				double offsetZ = (new Random().nextInt(5) + 1.0D - 2.5D) / 5.0D;

				this.world.addParticle(ParticleTypes.SMOKE, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, 0.0D, 0.0D);
				this.world.addParticle(ParticleTypes.EXPLOSION, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, 0.0D, 0.0D);
			}
			super.tick();
		}

		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			if (hit.hitInfo != null && hit.getType() == Type.ENTITY && hit.hitInfo instanceof LivingEntity)
			{
				LivingEntity entity = (LivingEntity) hit.hitInfo;
				double newPosX = 0, newPosY = 0, newPosZ = 0;

				int minPower = 5;
				int maxPower = 8;

				newPosY += 2;
				Direction dir = WyHelper.get4Directions(this.getThrower());
				if (dir == WyHelper.Direction.SOUTH)
					newPosZ += WyMathHelper.randomWithRange(minPower, maxPower);
				else if (dir == WyHelper.Direction.EAST)
					newPosX += WyMathHelper.randomWithRange(minPower, maxPower);
				else if (dir == WyHelper.Direction.NORTH)
					newPosZ -= WyMathHelper.randomWithRange(minPower, maxPower);
				else if (dir == WyHelper.Direction.WEST)
					newPosX -= WyMathHelper.randomWithRange(minPower, maxPower);

				entity.setPositionAndUpdate(entity.posX + newPosX, entity.posY + newPosY, entity.posZ + newPosZ);
			}
		}
	}

	public static class GomuGomuNoBazooka extends AbilityProjectile
	{
		public GomuGomuNoBazooka(World world)
		{
			super(GOMU_GOMU_NO_BAZOOKA, world);
		}
		
		public GomuGomuNoBazooka(EntityType type, World world)
		{super(type, world);}
		
		public GomuGomuNoBazooka(World world, double x, double y, double z)
		{
			super(GOMU_GOMU_NO_BAZOOKA, world, x, y, z);
		}

		public GomuGomuNoBazooka(World world, LivingEntity player, AbilityAttribute attr)
		{
			super(GOMU_GOMU_NO_BAZOOKA, world, player, attr);
		}

		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			if (hit.hitInfo != null && hit.getType() == Type.ENTITY && hit.hitInfo instanceof LivingEntity)
			{
				LivingEntity entity = (LivingEntity) hit.hitInfo;
				double newPosX = 0, newPosY = 0, newPosZ = 0;

				int minPower = 3;
				int maxPower = 8;

				newPosY += 2;
				Direction dir = WyHelper.get4Directions(this.getThrower());
				if (dir == WyHelper.Direction.SOUTH)
					newPosZ += WyMathHelper.randomWithRange(minPower, maxPower);
				else if (dir == WyHelper.Direction.EAST)
					newPosX += WyMathHelper.randomWithRange(minPower, maxPower);
				else if (dir == WyHelper.Direction.NORTH)
					newPosZ -= WyMathHelper.randomWithRange(minPower, maxPower);
				else if (dir == WyHelper.Direction.WEST)
					newPosX -= WyMathHelper.randomWithRange(minPower, maxPower);

				entity.setPositionAndUpdate(entity.posX + newPosX, entity.posY + newPosY, entity.posZ + newPosZ);
			}
		}
	}

	public static class GomuGomuNoPistol extends AbilityProjectile
	{
		public GomuGomuNoPistol(World world)
		{
			super(GOMU_GOMU_NO_PISTOL, world);
		}
		
		public GomuGomuNoPistol(EntityType type, World world)
		{super(type, world);}
		
		public GomuGomuNoPistol(World world, double x, double y, double z)
		{
			super(GOMU_GOMU_NO_PISTOL, world, x, y, z);
		}

		public GomuGomuNoPistol(World world, LivingEntity player, AbilityAttribute attr)
		{
			super(GOMU_GOMU_NO_PISTOL, world, player, attr);
		}
	}

	public static class GomuGomuNoJetPistol extends AbilityProjectile
	{
		public GomuGomuNoJetPistol(World world)
		{
			super(GOMU_GOMU_NO_JET_PISTOL, world);
		}
		
		public GomuGomuNoJetPistol(EntityType type, World world)
		{super(type, world);}
		
		public GomuGomuNoJetPistol(World world, double x, double y, double z)
		{
			super(GOMU_GOMU_NO_JET_PISTOL, world, x, y, z);
		}

		public GomuGomuNoJetPistol(World world, LivingEntity player, AbilityAttribute attr)
		{
			super(GOMU_GOMU_NO_JET_PISTOL, world, player, attr);
		}

		@Override
		public void tick()
		{
			for (int i = 0; i < 2; i++)
			{
				double offsetX = (new Random().nextInt(5) + 1.0D - 2.5D) / 5.0D;
				double offsetY = (new Random().nextInt(5) + 1.0D - 2.5D) / 5.0D;
				double offsetZ = (new Random().nextInt(5) + 1.0D - 2.5D) / 5.0D;

				this.world.addParticle(ParticleTypes.SMOKE, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, 0.0D, 0.0D);
				this.world.addParticle(ParticleTypes.EXPLOSION, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, 0.0D, 0.0D);
			}
			super.tick();
		}
	}

	public static class GomuGomuNoElephantGun extends AbilityProjectile
	{
		public GomuGomuNoElephantGun(World world)
		{
			super(GOMU_GOMU_NO_ELEPHANT_GUN, world);
		}
		
		public GomuGomuNoElephantGun(EntityType type, World world)
		{super(type, world);}
		
		public GomuGomuNoElephantGun(World world, double x, double y, double z)
		{
			super(GOMU_GOMU_NO_ELEPHANT_GUN, world, x, y, z);
		}

		public GomuGomuNoElephantGun(World world, LivingEntity player, AbilityAttribute attr)
		{
			super(GOMU_GOMU_NO_ELEPHANT_GUN, world, player, attr);
		}
	}

	public static class GomuGomuNoKongGun extends AbilityProjectile
	{
		public GomuGomuNoKongGun(World world)
		{
			super(GOMU_GOMU_NO_KONG_GUN, world);
		}
		
		public GomuGomuNoKongGun(EntityType type, World world)
		{super(type, world);}
		
		public GomuGomuNoKongGun(World world, double x, double y, double z)
		{
			super(GOMU_GOMU_NO_KONG_GUN, world, x, y, z);
		}

		public GomuGomuNoKongGun(World world, LivingEntity player, AbilityAttribute attr)
		{
			super(GOMU_GOMU_NO_KONG_GUN, world, player, attr);
		}
	}
}
