package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile.Data;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.ExtraProjectiles.EntityCloud;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModMiscBlocks;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;

public class DokuProjectiles 
{

	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType CHLORO_BALL = WyRegistry.registerEntityType("chloro_ball", ChloroBall::new, (spawnedEntity, world) -> new ChloroBall(world));
	public static final EntityType HYDRA = WyRegistry.registerEntityType("hydra", Hydra::new, (spawnedEntity, world) -> new Hydra(world));
	public static final EntityType VENOM_ROAD = WyRegistry.registerEntityType("venom_road", VenomRoad::new, (spawnedEntity, world) -> new VenomRoad(world));

	static
	{
		projectiles.put(ModAttributes.CHLORO_BALL, new Data(CHLORO_BALL, ChloroBall.class));
		projectiles.put(ModAttributes.HYDRA, new Data(HYDRA, Hydra.class));
		projectiles.put(ModAttributes.VENOM_ROAD, new Data(VENOM_ROAD, VenomRoad.class));
	}
		
	public static class VenomRoad extends AbilityProjectile
	{
		public VenomRoad(World world)
		{super(VENOM_ROAD, world);}
		
		public VenomRoad(EntityType type, World world)
		{super(type, world);}
		
		public VenomRoad(World world, double x, double y, double z)
		{super(VENOM_ROAD, world, x, y, z);}
		
		public VenomRoad(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(VENOM_ROAD, world, player, attr);		
		}	
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{		
			if(hit != null && !world.isRemote)
			{
				double x = hit.getHitVec().x;
				double y = hit.getHitVec().y;
				double z = hit.getHitVec().z;
				
				if (this.getThrower().getRidingEntity() != null)
					this.getThrower().dismountEntity(this.getThrower().getRidingEntity());
				EnderTeleportEvent event = new EnderTeleportEvent(this.getThrower(), x, y, z, 5.0F);
				this.getThrower().setPositionAndUpdate(event.getTargetX(), event.getTargetY() + 1, event.getTargetZ());
				this.getThrower().fallDistance = 0.0F;
			}
		}
	}
	
	public static class ChloroBall extends AbilityProjectile
	{
		public ChloroBall(World world)
		{super(CHLORO_BALL, world);}
		
		public ChloroBall(EntityType type, World world)
		{super(type, world);}
		
		public ChloroBall(World world, double x, double y, double z)
		{super(CHLORO_BALL, world, x, y, z);}
		
		public ChloroBall(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(CHLORO_BALL, world, player, attr);		
		}	
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{	
			if(CommonConfig.instance.getGriefing())
			{
				for (int i = 0; i < 20; i++)
				{
					double offsetX = new Random().nextInt(5) - 3;
					double offsetZ = new Random().nextInt(5) - 3;
					
					WyHelper.placeBlockIfAllowed(world, (this.posX + offsetX), this.posY, (this.posZ + offsetZ), ModMiscBlocks.poison, "air", "foliage");
				}
				
				AbilityExplosion explosion = WyHelper.newExplosion(this.getThrower(), this.posX, this.posY, this.posZ, 2.2F);
				explosion.setExplosionSound(false);
				explosion.setDestroyBlocks(false);
				explosion.setSmokeParticles(ID.PARTICLEFX_CHLOROBALL);
				explosion.setDamageOwner(false);
				explosion.doExplosion();
				
				EntityChloroBallCloud smokeCloud = new EntityChloroBallCloud(world);
				smokeCloud.setLife(30);
				smokeCloud.setLocationAndAngles(this.posX, (this.posY + 1), this.posZ, 0, 0);
				smokeCloud.setMotion(0, 0, 0);
				smokeCloud.setThrower((PlayerEntity) this.getThrower());
				this.world.addEntity(smokeCloud);
			}
		}
		
		@Override
		public void tick()
		{	
			if(this.world.isRemote)
			{
				double posXOffset = this.world.rand.nextGaussian() * 0.42D;
				double posYOffset = this.world.rand.nextGaussian() * 0.22D;
				double posZOffset = this.world.rand.nextGaussian() * 0.42D;		
	
				CustomParticle cp = new CustomParticle(world, ID.PARTICLE_ICON_DOKU,
						posX + posXOffset, 
						posY + posYOffset,
						posZ + posZOffset, 
						0, 0, 0)
						.setParticleAge(6).setParticleScale(1.7F);
				Minecraft.getInstance().particles.addEffect(cp);
			}
			super.tick();
		}
	}
	
	public static class EntityChloroBallCloud extends EntityCloud
	{
		public EntityChloroBallCloud(World world)
		{
			super(world);
		}
		
		@Override
		public void tick()
		{
			super.tick();
			if(!this.world.isRemote)
			{				
				for(LivingEntity target : WyHelper.getEntitiesNear(this, 4))
					target.addPotionEffect(new EffectInstance(Effects.POISON, 200, 2));
			}
			ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_CHLOROBALLCLOUD, this.posX, this.posY, this.posZ), this.getThrower());
		}
	}
	
	public static class Hydra extends AbilityProjectile
	{
		public Hydra(World world)
		{super(HYDRA, world);}
		
		public Hydra(EntityType type, World world)
		{super(type, world);}
		
		public Hydra(World world, double x, double y, double z)
		{super(HYDRA, world, x, y, z);}
		
		public Hydra(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(HYDRA, world, player, attr);		
		}	
	}
	
}
