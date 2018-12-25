package xyz.pixelatedw.MineMineNoMi3.events.customevents;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;

public class BountyEvent extends EntityEvent
{
	public EntityPlayer player;
	public ExtendedEntityStats props;
	public int amount;
	
	public BountyEvent(EntityPlayer entity) 
	{
		this(entity, 0);
	}
	
	public BountyEvent(EntityPlayer entity, int plusBounty) 
	{
		super(entity);
		this.player = entity;
		this.amount = plusBounty;
		this.props = ExtendedEntityStats.get(player);
	}

}