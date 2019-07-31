package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile.Data;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.ExtraProjectiles.EntityCloud;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class SniperProjectiles
{
	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType KAEN_BOSHI = WyRegistry.registerEntityType("kaen_boshi", KaenBoshi::new, (spawnedEntity, world) -> new KaenBoshi(world));
	public static final EntityType KEMURI_BOSHI = WyRegistry.registerEntityType("kemuri_boshi", KemuriBoshi::new, (spawnedEntity, world) -> new KemuriBoshi(world));
	public static final EntityType RENPATSU_NAMARI_BOSHI = WyRegistry.registerEntityType("renpatsu_namari_boshi", RenpatsuNamariBoshi::new, (spawnedEntity, world) -> new RenpatsuNamariBoshi(world));
	public static final EntityType SAKURESTU_SABOTEN_BOSHI = WyRegistry.registerEntityType("sakuretsu_saboten_boshi", SakuretsuSabotenBoshi::new, (spawnedEntity, world) -> new SakuretsuSabotenBoshi(world));
	
	static
	{
		projectiles.put(ModAttributes.KAEN_BOSHI, new Data(KAEN_BOSHI, KaenBoshi.class));
		projectiles.put(ModAttributes.KEMURI_BOSHI, new Data(KEMURI_BOSHI, KemuriBoshi.class));
		projectiles.put(ModAttributes.RENPATSU_NAMARI_BOSHI, new Data(RENPATSU_NAMARI_BOSHI, RenpatsuNamariBoshi.class));
		projectiles.put(ModAttributes.SAKURETSU_SABOTEN_BOSHI, new Data(SAKURESTU_SABOTEN_BOSHI, SakuretsuSabotenBoshi.class));
	}
	
	public static class SakuretsuSabotenBoshi extends AbilityProjectile
	{
		public SakuretsuSabotenBoshi(World world)
		{super(SAKURESTU_SABOTEN_BOSHI, world);}
		
		public SakuretsuSabotenBoshi(EntityType type, World world)
		{super(type, world);}
		
		public SakuretsuSabotenBoshi(World world, double x, double y, double z)
		{super(SAKURESTU_SABOTEN_BOSHI, world, x, y, z);}
		
		public SakuretsuSabotenBoshi(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(SAKURESTU_SABOTEN_BOSHI, world, player, attr);		
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			for(int i = 0; i < 8; i++)
			{
				int a1 = world.rand.nextInt(10) - 5;
				int a2 = world.rand.nextInt(10) - 5;
				
				WyHelper.placeBlockIfAllowed(world, (int)posX + a1, (int)posY - 3 , (int)posZ + a2, Blocks.CACTUS, 2, "air");
				WyHelper.placeBlockIfAllowed(world, (int)posX + a1, (int)posY - 2 , (int)posZ + a2, Blocks.CACTUS, 2, "air");	
				WyHelper.placeBlockIfAllowed(world, (int)posX + a1, (int)posY - 1 , (int)posZ + a2, Blocks.CACTUS, 2, "air");	
				WyHelper.placeBlockIfAllowed(world, (int)posX + a1, (int)posY , (int)posZ + a2, Blocks.CACTUS, 2, "air");		
				WyHelper.placeBlockIfAllowed(world, (int)posX + a1, (int)posY + 1, (int)posZ + a2, Blocks.CACTUS, 2, "air");		
				WyHelper.placeBlockIfAllowed(world, (int)posX + a1, (int)posY + 2, (int)posZ + a2, Blocks.CACTUS, 2, "air");	
			}
		}
	}
	
	public static class RenpatsuNamariBoshi extends AbilityProjectile
	{
		public RenpatsuNamariBoshi(World world)
		{super(RENPATSU_NAMARI_BOSHI, world);}
		
		public RenpatsuNamariBoshi(EntityType type, World world)
		{super(type, world);}
		
		public RenpatsuNamariBoshi(World world, double x, double y, double z)
		{super(RENPATSU_NAMARI_BOSHI, world, x, y, z);}
		
		public RenpatsuNamariBoshi(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(RENPATSU_NAMARI_BOSHI, world, player, attr);		
		}
	}
	
	public static class KemuriBoshi extends AbilityProjectile
	{
		public KemuriBoshi(World world)
		{super(KEMURI_BOSHI, world);}
		
		public KemuriBoshi(EntityType type, World world)
		{super(type, world);}
		
		public KemuriBoshi(World world, double x, double y, double z)
		{super(KEMURI_BOSHI, world, x, y, z);}
		
		public KemuriBoshi(World world, LivingEntity player, AbilityAttribute attr) 
		{
			super(KEMURI_BOSHI, world, player, attr);		
		}

		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			EntityKemuriBoshiCloud smokeCloud = new EntityKemuriBoshiCloud(world);
			smokeCloud.setLife(100);
			smokeCloud.setLocationAndAngles(this.posX, (this.posY + 1), this.posZ, 0, 0);
			smokeCloud.setMotion(0, 0, 0);
			smokeCloud.setThrower((PlayerEntity) this.getThrower());
			this.world.addEntity(smokeCloud);
		}	
	}
	
	public static class EntityKemuriBoshiCloud extends EntityCloud
	{
		public EntityKemuriBoshiCloud(World world)
		{
			super(world);
		}
		
		@Override
		public void tick()
		{
			super.tick();
			if(!this.world.isRemote)
			{				
				for(LivingEntity target : WyHelper.getEntitiesNear(this, 5))
					target.addPotionEffect(new EffectInstance(Effects.POISON, 100, 1));
			}
			ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_KEMURIBOSHI, this.posX, this.posY, this.posZ), this.getThrower());
		}
	}
	
	public static class KaenBoshi extends AbilityProjectile
	{
		public KaenBoshi(World world)
		{super(KAEN_BOSHI, world);}
		
		public KaenBoshi(EntityType type, World world)
		{super(type, world);}
		
		public KaenBoshi(World world, double x, double y, double z)
		{super(KAEN_BOSHI, world, x, y, z);}
		
		public KaenBoshi(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(KAEN_BOSHI, world, player, attr);		
		}
		
		@Override
		public void tick()
		{								
			this.setFire(999);
			super.tick();
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			if(hit.hitInfo != null && hit.getType() == Type.ENTITY)
			{
				((Entity) hit.hitInfo).setFire(100);
			}
		}	
	}
}
