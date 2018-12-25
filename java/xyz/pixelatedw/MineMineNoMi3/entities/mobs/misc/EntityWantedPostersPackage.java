package xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class EntityWantedPostersPackage extends EntityMob
{

	public EntityWantedPostersPackage(World world)
	{
		super(world);
	}

	protected void entityInit() 
	{
		super.entityInit();
	}

    protected boolean isAIEnabled()
    {
        return false;
    }
	
	public void onEntityUpdate()
	{
		this.motionY /= 2;
		this.fallDistance = 0;
		
		if(this.onGround)
		{
			if(!this.worldObj.isRemote)
				this.worldObj.setBlock((int)this.posX, (int)this.posY, (int)this.posZ, ListMisc.WantedPostersPackage);
			this.setDead();
		}
		
		super.onEntityUpdate();
	}
}
