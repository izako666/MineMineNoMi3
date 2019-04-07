package xyz.pixelatedw.MineMineNoMi3.abilities.extra.effects;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.abilities.extra.effects.DFEffect.Update;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;

public abstract class DFEffect
{

	private String effect;
	private ExtendedEntityData props;
	private EntityLivingBase entity;
	private Update updateThread;
	
	protected int timer;
	
	public DFEffect(EntityLivingBase entity, int timer, String effect)
	{
		this.entity = entity;
		this.effect = effect;
		this.timer = timer;
		this.props = ExtendedEntityData.get(entity);
				
		if(!props.hasExtraEffects(effect))
		{
			props.addExtraEffect(effect);
			if(entity instanceof EntityPlayerMP)
				WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) entity);
			WyNetworkHelper.sendToAll(new PacketSyncInfo(entity.getEntityId(), props));
			updateThread = (new Update(props, timer));
			updateThread.start();
		}
	}
	
	public abstract void onEffectStart(EntityLivingBase entity);
	public abstract void onEffectEnd(EntityLivingBase entity);
	
	public void forceStop()
	{
		try
		{
			updateThread.timer = -1;
			//updateThread.join();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	class Update extends Thread
	{
		private ExtendedEntityData props;
		public int timer;
		
		public Update(ExtendedEntityData props, int timer)
		{
			this.setName("Update Thread for " + effect.toUpperCase() +  " effect");
			this.props = props;
			this.timer = (timer * 2) + 100;
		}
		
		public void run()
		{
			onEffectStart(entity);
			
			while(timer > 0)
			{
				//if(!Minecraft.getMinecraft().isGamePaused())
				//{
					try
					{
						timer--;
						Thread.sleep(20);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				//}
			}
			
			props.removeExtraEffects(effect);
			if(entity instanceof EntityPlayerMP)
				WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) entity);
			WyNetworkHelper.sendToAll(new PacketSyncInfo(entity.getEntityId(), props));
			
			onEffectEnd(entity);
		}
	}
	
}
