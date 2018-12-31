package xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class EntityWantedPostersPackage extends EntityMob
{

	public EntityWantedPostersPackage(World world)
	{
		super(world);
	}

	public void applyEntityAttributes()
	{ 
		super.applyEntityAttributes(); 
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4);
	}
	
	protected void entityInit() 
	{
		super.entityInit();
	}

    protected boolean isAIEnabled()
    {
        return false;
    }
	
    public void setDead()
    {
    	if(!this.onGround)
    		DevilFruitsHelper.dropWantedPosters(this.worldObj, (int)posX, (int)posY, (int)posZ);
    	super.setDead();
    }
    
	public void onEntityUpdate()
	{
		this.motionY /= 1.5 + this.worldObj.rand.nextDouble();
		this.fallDistance = 0;
		
		if(this.onGround && !this.worldObj.isRemote)
		{
			this.worldObj.setBlock((int)this.posX, (int)this.posY, (int)this.posZ, ListMisc.WantedPostersPackage);
			this.setDead();
		}
		
		if(this.isInWater() || this.isInsideOfMaterial(Material.lava))
			this.setDead();
		
		super.onEntityUpdate();
	}
}
