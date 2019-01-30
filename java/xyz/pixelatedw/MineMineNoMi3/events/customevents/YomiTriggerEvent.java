package xyz.pixelatedw.MineMineNoMi3.events.customevents;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;

public class YomiTriggerEvent extends EntityEvent
{
	public ExtendedEntityData oldPlayerData, newPlayerData;
	
	public YomiTriggerEvent(ExtendedEntityData oldPlayerData, ExtendedEntityData newPlayerData) 
	{
		super(null);
		this.oldPlayerData = oldPlayerData;
		this.newPlayerData = newPlayerData;
	}

}
