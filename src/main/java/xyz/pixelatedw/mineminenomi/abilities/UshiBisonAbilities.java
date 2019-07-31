package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.api.network.PacketAbilityDataSync;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketClientSyncAll;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class UshiBisonAbilities 
{

	static
	{
		Values.abilityWebAppExtraParams.put("bisonpowerpoint", new String[] {"desc", "The user transforms into a half-bison hybrid, which focuses on strength, Allows the user to use 'Kokutei Cross' and 'Fiddle Banff'"});
		Values.abilityWebAppExtraParams.put("bisonspeedpoint", new String[] {"desc", ""});
		Values.abilityWebAppExtraParams.put("kokuteicross", new String[] {"desc", "The transformed user crosses their hooves to hit the opponent."});
		Values.abilityWebAppExtraParams.put("fiddlebanff", new String[] {"desc", "The transformed user dashes towards the opponent to crash against them with great power."});
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new PowerPoint(), new SpeedPoint(), new FiddleBanff(), new KokuteiCross()};

	public static class PowerPoint extends Ability
	{
		public PowerPoint()
		{
			super(ModAttributes.BISON_POWER_POINT);
		}
		
		@Override
		public void passive(PlayerEntity player)
		{
			IDevilFruit props = DevilFruitCapability.get(player);

			if (!this.isOnCooldown && ( WyHelper.isNullOrEmpty(props.getZoanPoint()) || props.getZoanPoint().equalsIgnoreCase("power")))
			{
				super.passive(player);
			}
		}
		
		@Override
		public void startPassive(PlayerEntity player)
		{
			IDevilFruit props = DevilFruitCapability.get(player);
			
			if (props.getZoanPoint().isEmpty())
				props.setZoanPoint("");
			
			props.setZoanPoint("power");
			byte syncedData = 0b0000001;
			ModNetwork.sendToAll(new PacketClientSyncAll(player.getEntityId(), props, syncedData));
		}
		
		@Override
		public void endPassive(PlayerEntity player)
		{
			IDevilFruit props = DevilFruitCapability.get(player);

			props.setZoanPoint("");
			byte syncedData = 0b0000001;
			ModNetwork.sendToAll(new PacketClientSyncAll(player.getEntityId(), props, syncedData));
		}
	}

	public static class SpeedPoint extends Ability
	{		
		public SpeedPoint()
		{
			super(ModAttributes.BISON_SPEED_POINT);
		}

		@Override
		public void passive(PlayerEntity player)
		{
			IDevilFruit props = DevilFruitCapability.get(player);

			if (!this.isOnCooldown && ( WyHelper.isNullOrEmpty(props.getZoanPoint()) || props.getZoanPoint().equalsIgnoreCase("speed")))
			{
				super.passive(player);
			}
		}
		
		@Override
		public void startPassive(PlayerEntity player)
		{
			IDevilFruit props = DevilFruitCapability.get(player);
			
			if (props.getZoanPoint().isEmpty())
				props.setZoanPoint("n/a");
				
			props.setZoanPoint("speed");
			byte syncedData = 0b0000001;
			ModNetwork.sendToAll(new PacketClientSyncAll(player.getEntityId(), props, syncedData));
		}
		
		@Override
		public void endPassive(PlayerEntity player)
		{
			IDevilFruit props = DevilFruitCapability.get(player);

			props.setZoanPoint("n/a");
			byte syncedData = 0b0000001;
			ModNetwork.sendToAll(new PacketClientSyncAll(player.getEntityId(), props, syncedData));
		}
	}
	
	public static class FiddleBanff extends Ability
	{
		public FiddleBanff() 
		{
			super(ModAttributes.FIDDLE_BANFF); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{	
			IDevilFruit props = DevilFruitCapability.get(player);

			if((props.getZoanPoint().equals("power") || props.getZoanPoint().equals("speed") ) && !this.isOnCooldown)
			{
				double[] speed = DevilFruitsHelper.propulsion(player, 5, 5);
			
				DevilFruitsHelper.changeMotion("=", speed[0], player.getMotion().y, speed[1], player);
				
				super.use(player);
			}
			else if(!props.getZoanPoint().equals("power") && !props.getZoanPoint().equals("speed"))
			{
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Power Point or Speed Point is active !");
			}
		}
		
	    @Override
		public void duringCooldown(PlayerEntity player, int currentCooldown)
	    {
			if(currentCooldown > 110)
				for(LivingEntity e : WyHelper.getEntitiesNear(player, 1.6))
					e.attackEntityFrom(DamageSource.causePlayerDamage(player), 6);
	    }
	}
	
	public static class KokuteiCross extends Ability
	{
		public KokuteiCross() 
		{
			super(ModAttributes.KOKUTEI_CROSS); 
		}	
		
		@Override
		public void startPassive(PlayerEntity player) 
		{
			IDevilFruit props = DevilFruitCapability.get(player);
			IAbilityData abilityProps = AbilityDataCapability.get(player);
			
			if(!props.getZoanPoint().equals("power"))
			{
				this.setPassiveActive(false);
				ModNetwork.sendTo(new PacketAbilityDataSync(abilityProps), (ServerPlayerEntity) player);					
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Power Point is active !");
			}
		}
		
		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target) 
		{
			IDevilFruit props = DevilFruitCapability.get(player);

			if(props.getZoanPoint().equals("power"))
			{
				super.hitEntity(player, target);
				target.attackEntityFrom(DamageSource.causePlayerDamage(player), 20);
				ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_KOKUTEICROSS, target), player);
				
				double[] speed = DevilFruitsHelper.propulsion(player, -0.7, -0.7);
						
				DevilFruitsHelper.changeMotion("=", speed[0], player.getMotion().y, speed[1], player);
			}
			else
			{
				this.setPassiveActive(false);
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Power Point is active !");
			}
		}
	}
		
}
