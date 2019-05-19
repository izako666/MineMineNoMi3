package xyz.pixelatedw.MineMineNoMi3.events;

import java.util.HashMap;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.EntityWantedPostersPackage;
import xyz.pixelatedw.MineMineNoMi3.helpers.BountyHelper;

public class EventsBounty
{

	private HashMap<EntityPlayer, double[]> cachedPositions = new HashMap<EntityPlayer, double[]>();
	
	@SubscribeEvent
	public void onRenderTick(TickEvent.PlayerTickEvent event)
	{
		if(MainConfig.enableWantedPostersPackages)
		{
			if(event.phase == Phase.END && event.side == Side.SERVER)
			{
				EntityPlayer player = event.player;
				
				double currentPosX = player.posX;
				double currentPosZ = player.posZ;

				// Every ~15 minutes, by default
				if(player.ticksExisted % MainConfig.rateWantedPostersPackagesSpawn == 0)
				{
					if(!this.cachedPositions.containsKey(player))
						this.cachedPositions.put(player, new double[] {currentPosX, currentPosZ});
					else
					{
						double[] positions = this.cachedPositions.get(player);
						double cachedPosX = positions[0];
						double cachedPosZ = positions[1];
						
						boolean flagPosX = Math.abs(currentPosX - cachedPosX) > 100;
						boolean flagPosZ = Math.abs(currentPosZ - cachedPosZ) > 100;
						
						
						if(flagPosX || flagPosZ)
						{						
							if(BountyHelper.issueBountyForPlayer(event.player))
							{				
								EntityWantedPostersPackage pkg = new EntityWantedPostersPackage(player.worldObj);
								pkg.setLocationAndAngles(player.posX + WyMathHelper.randomWithRange(-10, 10), player.posY + 30, player.posZ + WyMathHelper.randomWithRange(-10, 10), 0, 0);
								player.worldObj.spawnEntityInWorld(pkg);
							}
							
							this.cachedPositions.remove(player);					
							this.cachedPositions.put(player, new double[] {currentPosX, currentPosZ});
						}
					}
				}
			}
		}
	}
	
	
}
