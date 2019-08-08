package xyz.pixelatedw.mineminenomi.events.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.EntityEvent;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;

public class BountyEvent extends EntityEvent
{
	public PlayerEntity player;
	public IEntityStats props;
	public long amount;
	
	public BountyEvent(PlayerEntity entity) 
	{
		this(entity, 0);
	}
	
	public BountyEvent(PlayerEntity entity, long plusBounty) 
	{
		super(entity);
		this.player = entity;
		this.amount = plusBounty;
		this.props = EntityStatsCapability.get(player);
	}
}
