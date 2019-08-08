package xyz.pixelatedw.mineminenomi.events.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.EntityEvent;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;

public class YomiTriggerEvent extends EntityEvent
{
	public LivingEntity entity;
	public IEntityStats oldPlayerData, newPlayerData;
	
	public YomiTriggerEvent(LivingEntity entity, IEntityStats oldPlayerData, IEntityStats newPlayerData) 
	{
		super(entity);
		this.entity = entity;
		this.oldPlayerData = oldPlayerData;
		this.newPlayerData = newPlayerData;
	}
}
