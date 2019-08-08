package xyz.pixelatedw.mineminenomi.events.devilfruits;

import java.util.Arrays;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketSpecialFlying;

public class EventsSpecialFlying
{
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			IDevilFruit props = DevilFruitCapability.get(player);
			IAbilityData abilityProps = AbilityDataCapability.get(player);

			Ability abareHimatsuri = abilityProps.getHotbarAbilityFromName(ModAttributes.ABARE_HIMATSURI.getAttributeName());
			boolean hasAbareHimatsuri = props.getDevilFruit().equalsIgnoreCase("zushizushi") && abareHimatsuri != null && abareHimatsuri.isPassiveActive();
			
			boolean hasToriFruit = props.getDevilFruit().equalsIgnoreCase("toritoriphoenix") && !props.getZoanPoint().toLowerCase().equals("n/a");
			
			boolean hasFlyingFruit = Arrays.stream(DevilFruitsHelper.flyingFruits).anyMatch(p ->
			{				
				return props.getDevilFruit().equalsIgnoreCase(p);
			});	
			
			if(!player.abilities.isCreativeMode)
			{
				if(!player.world.isRemote)
				{
					if((CommonConfig.instance.getSpecialFlying() && hasFlyingFruit) || hasToriFruit || hasAbareHimatsuri)		
					{
						ModNetwork.sendTo(new PacketSpecialFlying(true), (ServerPlayerEntity) player);
						player.fallDistance = 0;
					}
					else
						ModNetwork.sendTo(new PacketSpecialFlying(false), (ServerPlayerEntity) player);
				}
			
				if(player.abilities.isFlying && player.world.isRemote)
				{
					double extraOffset = 0;
					
					ResourceLocation particleToUse = null;
					if(props.getDevilFruit().equalsIgnoreCase("mokumoku") )
						particleToUse = ID.PARTICLE_ICON_MOKU;
					else if(props.getDevilFruit().equalsIgnoreCase("gasugasu") )
						particleToUse = ID.PARTICLE_ICON_GASU;
					else if(props.getDevilFruit().equalsIgnoreCase("sunasuna") )
						particleToUse = ID.PARTICLE_ICON_SUNA2;
					else if(props.getDevilFruit().equalsIgnoreCase("toritoriphoenix") )
					{
						particleToUse = ID.PARTICLE_ICON_BLUEFLAME;
						extraOffset = 1;
					}
					
					if(particleToUse != null)
					{
						/*for (int i = 0; i < 5; i++)
						{							
							double offsetX = 0.5 - player.world.rand.nextDouble();
							double offsetY = player.world.rand.nextDouble();
							double offsetZ = 0.5 - player.world.rand.nextDouble();
							
							CustomParticle cp = new CustomParticle(player.world, ID.PARTICLE_ICON_MERA,
									player.posX + offsetX, 
									player.posY - 2 + offsetY + extraOffset,
									player.posZ + offsetZ, 
									0, 0, 0)
									.setParticleScale(1.3F).setParticleGravity(-0.05F).setParticleAge(5);
							Minecraft.getInstance().particles.addEffect(cp);
						}*/
					}
				}
			}
		}
	}
}
