package xyz.pixelatedw.mineminenomi.packets;

import java.util.function.Supplier;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class PacketMotion
{

	private int entityId;
	private String operation;
	private double motionX, motionY, motionZ;
	
	public PacketMotion() {}
	
	public PacketMotion(int entityId, String string, double x, double y, double z)
	{
		this.entityId = entityId;
		this.operation = string;
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
	}
	
	public PacketMotion(String string, double x, double y, double z)
	{
		this.operation = string;
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.entityId);
		buffer.writeInt(this.operation.length());
		buffer.writeString(this.operation);
		buffer.writeDouble(this.motionX);
		buffer.writeDouble(this.motionY);
		buffer.writeDouble(this.motionZ);
	}
	
	public static PacketMotion decode(PacketBuffer buffer)
	{
		PacketMotion msg = new PacketMotion();
		msg.entityId = buffer.readInt();
		int len = buffer.readInt();
		msg.operation = buffer.readString(len);
		msg.motionX = buffer.readDouble();
		msg.motionY = buffer.readDouble();
		msg.motionZ = buffer.readDouble();
		return msg;
	}
	
	public static void handle(PacketMotion message, final Supplier<NetworkEvent.Context> ctx)
	{
		/*if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ModMain.proxy.getClientPlayer();		
				Entity target = null;
				
				if(message.entityId != 0)
				{
					for (Object e : player.world.loadedEntityList)
					{
						if(e instanceof LivingEntity)
						{
							if ( ((LivingEntity)e).getEntityId() == message.entityId)
							{
								target = (Entity) e;
							}
						}
					}	
				}
				else
				{
					target = player;
				}
				
				//System.out.println(target);
				
				if(target == null)
					return;
				
				if(message.operation.contains("+"))
				{
					target.motionX += message.motionX;
					target.motionY += message.motionY;
					target.motionZ += message.motionZ;			
				}
				if(message.operation.contains("-"))
				{
					target.motionX -= message.motionX;
					target.motionY -= message.motionY;
					target.motionZ -= message.motionZ;			
				}
				if(message.operation.contains("="))
				{
					target.motionX = message.motionX;
					target.motionY = message.motionY;
					target.motionZ = message.motionZ;						
				}
				if(message.operation.contains("*"))
				{
					target.motionX *= message.motionX;
					target.motionY *= message.motionY;
					target.motionZ *= message.motionZ;						
				}
			});
		}
		else if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{	
				PlayerEntity player = ctx.get().getSender();
				
				if(message.entityId <= 0)
					return;
				
				Entity target = null;
				
				for (Object e : player.world.loadedEntityList)
				{
					if(e instanceof LivingEntity)
					{
						if ( ((LivingEntity)e).getEntityId() == message.entityId)
						{
							target = (Entity) e;
						}
					}
				}
				
				if(target == null)
					return;
				
				if(message.operation.contains("+"))
				{
					target.motionX += message.motionX;
					target.motionY += message.motionY;
					target.motionZ += message.motionZ;			
				}
				if(message.operation.contains("-"))
				{
					target.motionX -= message.motionX;
					target.motionY -= message.motionY;
					target.motionZ -= message.motionZ;			
				}
				if(message.operation.contains("="))
				{
					target.motionX = message.motionX;
					target.motionY = message.motionY;
					target.motionZ = message.motionZ;						
				}
				if(message.operation.contains("*"))
				{
					target.motionX *= message.motionX;
					target.motionY *= message.motionY;
					target.motionZ *= message.motionZ;						
				}
			});
		}*/
		
		ctx.get().setPacketHandled(true);
	}
}
