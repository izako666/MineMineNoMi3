package xyz.pixelatedw.mineminenomi.events.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.EntityEvent;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;

public class DorikiEvent extends EntityEvent
{
	public PlayerEntity player;
	public IEntityStats props;
	public int doriki;
	
	public DorikiEvent(PlayerEntity entity) 
	{
		super(entity);
		this.player = entity;
		this.props = EntityStatsCapability.get(player);
	}
}
