package xyz.pixelatedw.MineMineNoMi3.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.relauncher.Side;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.EntityWantedPostersPackage;
import xyz.pixelatedw.MineMineNoMi3.helpers.BountyHelper;

public class EventsBounty
{

	
	@SubscribeEvent
	public void onRenderTick(TickEvent.PlayerTickEvent event)
	{
		if(MainConfig.enableWantedPostersPackages)
		{
			if(event.phase == Phase.END && event.side == Side.SERVER)
			{
				//System.out.println("" + event.player.ticksExisted);
				
				// Every ~15 minutes
				if(event.player.ticksExisted % 18000 == 0)
				{
					if(BountyHelper.issueBountyForPlayer(event.player))
					{				
						EntityWantedPostersPackage pkg = new EntityWantedPostersPackage(event.player.worldObj);
						pkg.setLocationAndAngles(event.player.posX + WyMathHelper.randomWithRange(-10, 10), event.player.posY + 30, event.player.posZ + WyMathHelper.randomWithRange(-10, 10), 0, 0);
						event.player.worldObj.spawnEntityInWorld(pkg);
					}
				}
			}
		}
	}
	
	
}
