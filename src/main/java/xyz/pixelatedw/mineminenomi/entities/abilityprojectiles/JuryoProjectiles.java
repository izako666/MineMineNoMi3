package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;

import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
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
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;

public class JuryoProjectiles
{
	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType SAGARI_NO_RYUSEI = WyRegistry.registerEntityType("sagari_no_ryusei", SagariNoRyusei::new, (spawnedEntity, world) -> new SagariNoRyusei(world));
	public static final EntityType MOKO = WyRegistry.registerEntityType("moko", Moko::new, (spawnedEntity, world) -> new Moko(world));

	static
	{
		projectiles.put(ModAttributes.SAGARI_NO_RYUSEI, new Data(SAGARI_NO_RYUSEI, SagariNoRyusei.class));
		projectiles.put(ModAttributes.MOKO, new Data(MOKO, Moko.class));
	}
	
	public static class Moko extends AbilityProjectile
	{
		public Moko(World world)
		{super(MOKO, world);}
		
		public Moko(EntityType type, World world)
		{super(type, world);}
		
		public Moko(World world, double x, double y, double z)
		{super(MOKO, world, x, y, z);}
		
		public Moko(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(MOKO, world, player, attr);		
		}
		
		@Override
		public void tick()
		{
			if(this.world.isRemote)
			{
				double posXOffset = this.world.rand.nextGaussian() * 0.52D;
				double posYOffset = this.world.rand.nextGaussian() * 0.52D;
				double posZOffset = this.world.rand.nextGaussian() * 0.52D;		
		
				CustomParticle cp = new CustomParticle(this.world, ID.PARTICLE_ICON_GASU,
						posX + posXOffset, 
						posY + posYOffset,
						posZ + posZOffset, 
						0, 0, 0)
						.setParticleAge(20).setParticleScale(0.7F);
				Minecraft.getInstance().particles.addEffect(cp);
			}
			super.tick();
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			if(hit.hitInfo != null && hit.getType() == Type.ENTITY && hit.hitInfo instanceof LivingEntity)
			{
				LivingEntity entity = (LivingEntity) hit.hitInfo;
				for(int x = -1; x < 1; x++)
				for(int z = -1; z < 1; z++)
				{
					int posX = (int)entity.posX + x;
					int posY = (int)entity.posY - 1;
					int posZ = (int)entity.posZ + z;
					
					if(WyHelper.placeBlockIfAllowed(this.world, posX, posY, posZ, Blocks.AIR, "all", "restricted", "ignore liquid"))
					{
						entity.setMotion(0, entity.getMotion().y - 5, 0);
						entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 10));
					}
				}
			}
			else
			{
				WyHelper.placeBlockIfAllowed(this.world, hit.getHitVec().x, hit.getHitVec().y, hit.getHitVec().z, Blocks.AIR, "all", "restricted", "ignore liquid");
			}
		}
	}
	
	/**FORGOLD Some particle effects, maybe some dark smoke */
	public static class SagariNoRyusei extends AbilityProjectile
	{
		public SagariNoRyusei(World world)
		{super(SAGARI_NO_RYUSEI, world);}
		
		public SagariNoRyusei(EntityType type, World world)
		{super(type, world);}
		
		public SagariNoRyusei(World world, double x, double y, double z)
		{super(SAGARI_NO_RYUSEI, world, x, y, z);}
		
		public SagariNoRyusei(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(SAGARI_NO_RYUSEI, world, player, attr);		
		}
	}
}
