package xyz.pixelatedw.mineminenomi.abilities.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.ExtraEffectCapability;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.IExtraEffect;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketClientSyncAll;
import xyz.pixelatedw.mineminenomi.packets.PacketExtraEffectSync;

public abstract class DFEffect
{

	private String effect;
	private IExtraEffect props;
	private LivingEntity entity;
	private Update updateThread;
	
	protected int timer;
	
	public DFEffect(LivingEntity entity, int timer, String effect)
	{
		this.entity = entity;
		this.effect = effect;
		this.timer = timer;
		this.props = ExtraEffectCapability.get(entity);
				
		if(!props.hasExtraEffect(effect))
		{
			props.addExtraEffect(effect);
			if(entity instanceof ServerPlayerEntity)
				ModNetwork.sendTo(new PacketExtraEffectSync(props), (ServerPlayerEntity) entity);
			byte syncedData = 0b0010000;
			ModNetwork.sendToAll(new PacketClientSyncAll(entity.getEntityId(), props, syncedData));
			updateThread = (new Update(props, timer));
			updateThread.start();
		}
	}
	
	public abstract void onEffectStart(LivingEntity entity);
	public abstract void onEffectEnd(LivingEntity entity);
	
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
		private IExtraEffect props;
		public int timer;
		
		public Update(IExtraEffect props, int timer)
		{
			this.setName("Update Thread for " + effect.toUpperCase() +  " effect");
			this.props = props;
			this.timer = (timer * 2) + 100;
		}
		
		@Override
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
			
			props.removeExtraEffect(effect);
			if(entity instanceof ServerPlayerEntity)
				ModNetwork.sendTo(new PacketExtraEffectSync(props), (ServerPlayerEntity) entity);
			//WyNetworkHelper.sendToAll(new PacketSyncInfo(entity.getEntityId(), props));
			
			onEffectEnd(entity);
		}
	}
	
}
