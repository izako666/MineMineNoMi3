package xyz.pixelatedw.MineMineNoMi3.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.relauncher.Side;

public class EventsBounty
{

	
	@SubscribeEvent
	public void onRenderTick(TickEvent.PlayerTickEvent event)
	{
		if(event.phase == Phase.END && event.side == Side.SERVER)
		{
			//36000 ticks, 30 minutes
			//System.out.println("" + event.player.ticksExisted);
		}
	}
	
	
}
