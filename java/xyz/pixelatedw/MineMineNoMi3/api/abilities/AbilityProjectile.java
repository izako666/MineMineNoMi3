package xyz.pixelatedw.MineMineNoMi3.api.abilities;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

public class AbilityProjectile extends EntityThrowable
{
	public int ticks, maxticks;
	private AbilityAttribute attr;
	private EntityLivingBase user;
	
	public AbilityProjectile(World world)
	{
		super(world);
	}
	
	public AbilityProjectile(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}
	
	public AbilityProjectile(World world, EntityLivingBase player, AbilityAttribute attr)
	{
		super(world, player);
		this.attr = attr;
		this.ticks = attr.getProjectileTicks();
		this.maxticks = ticks;
		this.user = player;

		if(this.attr != null)
		{
			this.setLocationAndAngles(this.user.posX, this.user.posY + (double)this.user.getEyeHeight(), this.user.posZ, this.user.rotationYaw, this.user.rotationPitch);
	        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * 0.4);
	        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * 0.4);
	        this.motionY = (double)(-MathHelper.sin((this.rotationPitch + this.func_70183_g()) / 180.0F * (float)Math.PI) * 0.4);        
	        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, attr.getProjectileSpeed(), 1.0F);
		}
	}

	public AbilityAttribute getAttribute()
	{return this.attr;}

	public void tasksImapct(MovingObjectPosition hit){};
	
	public void onEntityUpdate()
	{			
		if(this.attr != null)
		{						
			if(ticks <= 0)
			{
				ticks = maxticks;
				this.setDead();
			}
			else
				ticks--;
		}
	}
	
	public void onUpdate()
	{super.onUpdate();}

	protected void onImpact(MovingObjectPosition hit)
	{
		if (!this.worldObj.isRemote)
		{			
			if(this.attr != null)
			{
				if (hit.entityHit != null && hit.entityHit instanceof EntityLivingBase)
				{
					if(this.attr.getPotionEffectsForProjectile() != null)
						for(PotionEffect p : this.attr.getPotionEffectsForProjectile())
							((EntityLivingBase)hit.entityHit).addPotionEffect(new PotionEffect(p));
				
					if(this.attr.getProjectileExplosionPower() > 0)
					{
						AbilityExplosion explosion = WyHelper.newExplosion(this.getThrower(), this.posX, this.posY, this.posZ, this.attr.getProjectileExplosionPower());
						explosion.setDamageOwner(false);
						explosion.setFireAfterExplosion(this.attr.canProjectileExplosionSetFire());
						explosion.setDestroyBlocks(this.attr.canProjectileExplosionDestroyBlocks());
						explosion.doExplosion();
					}

					if(this.attr.getProjectileDamage() > 0)
						hit.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), this.attr.getProjectileDamage());
					
					tasksImapct(hit);

					this.setDead();
				}
				else
				{
					if(this.attr.getProjectileExplosionPower() > 0)
					{
						AbilityExplosion explosion = WyHelper.newExplosion(this.getThrower(), this.posX, this.posY, this.posZ, this.attr.getProjectileExplosionPower());
						explosion.setDamageOwner(false);
						explosion.setFireAfterExplosion(this.attr.canProjectileExplosionSetFire());
						explosion.setDestroyBlocks(this.attr.canProjectileExplosionDestroyBlocks());
						explosion.doExplosion();
					}
					
					tasksImapct(hit);

					Material hitMat = this.worldObj.getBlock(hit.blockX, hit.blockY, hit.blockZ).getMaterial();
					
					if(!this.attr.canProjectileMoveThroughBlocks() && (hitMat != Material.plants && hitMat != Material.vine && hitMat != Material.water))
						this.setDead();
				}  
			}
			else
				this.setDead();
		}
	}
    
	protected float getGravityVelocity()
	{return 0.001F;}

}