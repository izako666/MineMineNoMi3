package xyz.pixelatedw.MineMineNoMi3.events.customevents;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;

public class YomiTriggerEvent extends EntityEvent
{
	public EntityLivingBase entity;
	public ExtendedEntityData oldPlayerData, newPlayerData;
	
	public YomiTriggerEvent(EntityLivingBase entity, ExtendedEntityData oldPlayerData, ExtendedEntityData newPlayerData) 
	{
		super(entity);
		this.entity = entity;
		this.oldPlayerData = oldPlayerData;
		this.newPlayerData = newPlayerData;
	}

}
