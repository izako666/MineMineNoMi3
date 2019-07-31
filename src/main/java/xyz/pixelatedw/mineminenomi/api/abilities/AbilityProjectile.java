package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion.Mode;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;

public class AbilityProjectile extends ThrowableEntity
{
	public int ticks, maxticks;
	private AbilityAttribute attr;
	private LivingEntity user;

	public AbilityProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public AbilityProjectile(EntityType type, World world, double x, double y, double z)
	{
		super(type, x, y, z, world);
	}

	public AbilityProjectile(EntityType type, World world, LivingEntity player, AbilityAttribute attr)
	{
		super(type, player, world);
		this.attr = attr;
		this.ticks = attr.getProjectileTicks();
		this.maxticks = ticks;
		this.user = player;

		if(this.getThrower() == null || !DevilFruitsHelper.checkForRestriction((PlayerEntity) this.getThrower()))
			this.remove();
		
		if (this.attr != null)
		{
			this.setLocationAndAngles(this.user.posX, this.user.posY + this.user.getEyeHeight(), this.user.posZ, this.user.rotationYaw, this.user.rotationPitch);
			double motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * 0.4;
			double motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * 0.4;
			double motionY = -MathHelper.sin((this.rotationPitch) / 180.0F * (float) Math.PI) * 0.4;
			this.setMotion(new Vec3d(motionX, motionY, motionZ));
			this.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		}

	}

	public AbilityAttribute getAttribute()
	{
		return this.attr;
	}

	public void tasksImapct(RayTraceResult hit) {};

	@Override
	public void tick()
	{
		super.tick();
		if (this.attr != null)
		{
			if (ticks <= 0)
			{
				ticks = maxticks;
				this.remove();
			}
			else
				ticks--;
		}
		
		if(this.attr != null)
		{
			/*Vec3d vec31 = new Vec3d(this.posX, this.posY, this.posZ);//.createVectorHelper(this.posX, this.posY, this.posZ);
			Vec3d vec3 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);//.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			RayTraceResult movingobjectposition = this.world.rayTraceBlocks(vec31, vec3, RayTraceFluidMode.ALWAYS);
			vec31 = new Vec3d(this.posX, this.posY, this.posZ);//.createVectorHelper(this.posX, this.posY, this.posZ);
			vec3 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);//.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			
			double sizeX = this.attr.getProjectileCollisionSizes()[0];
			double sizeY = this.attr.getProjectileCollisionSizes()[1];
			double sizeZ = this.attr.getProjectileCollisionSizes()[2];
			
			Entity entity = null;
			List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getBoundingBox().contract(this.motionX, this.motionY, this.motionZ).expand(sizeX, sizeY, sizeZ));
			double d0 = 0.0D;
			int i;
			float f1;
			
			for (i = 0; i < list.size(); ++i)
			{
				Entity entity1 = (Entity) list.get(i);
				
				if (entity1.canBeCollidedWith() && (entity1 != this.getThrower() || this.ticksExisted >= 5))
				{
					AxisAlignedBB axisalignedbb1 = entity1.getBoundingBox().expand(sizeX, sizeY, sizeZ);
					RayTraceResult movingobjectposition1 = axisalignedbb1.calculateIntercept(vec31, vec3);
					
					if (movingobjectposition1 != null)
					{
						double d1 = vec31.distanceTo(movingobjectposition1.getgetHitVec()());
						
						if (d1 < d0 || d0 == 0.0D)
						{
							entity = entity1;
							d0 = d1;
						}
					}
				}
			}
			
			if (entity != null)
				movingobjectposition = new RayTraceResult(entity);
			
			if (movingobjectposition != null && movingobjectposition.entity != null)
				this.onImpact(movingobjectposition);*/
		}
	}

	@Override
	protected void onImpact(RayTraceResult hit)
	{
		if (this.world.isRemote || this.attr == null)
			return;

		if(hit.getType() == Type.ENTITY)
		{
			EntityRayTraceResult entityHit = (EntityRayTraceResult) hit;

			if(entityHit.getEntity() instanceof LivingEntity)
			{		
				LivingEntity hitEntity = (LivingEntity) entityHit.getEntity();
				IDevilFruit hitDevilFruitProps = DevilFruitCapability.get(hitEntity);
				IHakiData throwerHakiDataProps = HakiDataCapability.get(this.getThrower());
				
				if(hitDevilFruitProps.isLogia() && this.getAttribute().isProjectilePhysical() && !throwerHakiDataProps.hasBusoHakiActive())
					return;
					
				if (this.attr.getPotionEffectsForProjectile() != null)
					for (EffectInstance p : this.attr.getPotionEffectsForProjectile())
						hitEntity.addPotionEffect(new EffectInstance(p));
				
				if (this.attr.getProjectileDamage() > 0)
					hitEntity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), this.attr.getProjectileDamage());
			}
		}
		else if(hit.getType() == Type.BLOCK)
		{
			BlockRayTraceResult blockHit = (BlockRayTraceResult) hit;

			Material hitMat = this.world.getBlockState(new BlockPos(blockHit.getHitVec().getX(), blockHit.getHitVec().getY(), blockHit.getHitVec().getZ())).getMaterial();

			if (!this.attr.canProjectileMoveThroughBlocks() && hitMat.isSolid())
			{
				this.tasksImapct(hit);
				this.remove();
			}
		}
		
		if (this.attr.getProjectileExplosionPower() > 0)
		{
			float power = this.attr.getProjectileExplosionPower();
			
			this.world.createExplosion(this.getThrower(), this.posX, this.posY, this.posZ, power, Mode.DESTROY);
		}

		this.tasksImapct(hit);		
		this.remove();
	}

	@Override
	protected float getGravityVelocity()
	{
		return 0.001F;
	}

	@Override
	protected void registerData()
	{
		
	}
	
	@Override
    public IPacket<?> createSpawnPacket() 
	{
		return NetworkHooks.getEntitySpawningPacket(this);
    }
	
	public static class Data
	{
		private EntityType type;
		private Class entityClass;
		
		public Data(EntityType type, Class<? extends Entity> clz)
		{
			this.type = type;
			this.entityClass = clz;
		}
		
		public EntityType getEntityType()
		{
			return type;
		}
		
		public Class getEntityClass()
		{
			return this.entityClass;
		}
	}

}