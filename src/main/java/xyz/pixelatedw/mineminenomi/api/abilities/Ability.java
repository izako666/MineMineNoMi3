package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion.Mode;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.api.network.PacketAbilityDataSync;
import xyz.pixelatedw.mineminenomi.api.telemetry.WyTelemetry;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;

public class Ability 
{
	
	protected AbilityProjectile projectile;
	protected String originalDisplayName = "n/a";
	protected AbilityAttribute attr;
	protected boolean isOnCooldown = false, isCharging = false, isRepeating = false, passiveActive = false, isDisabled = false;
	private int ticksForCooldown, ticksForCharge, ticksForRepeater, ticksForRepeaterFreq, currentSpawn = 0;

	public Ability(AbilityAttribute attr)
	{
		this.attr = new AbilityAttribute(attr);
		this.ticksForCooldown = this.attr.getAbilityCooldown();
		this.ticksForCharge = this.attr.getAbilityCharges();
		this.ticksForRepeater = this.attr.getAbilityCooldown();
		this.ticksForRepeaterFreq = this.attr.getAbilityRepeaterFrequency();
		this.originalDisplayName = this.attr.getAbilityDisplayName();
	}

	public AbilityAttribute getAttribute() { return attr; }
	
	public void use(PlayerEntity player)
	{
		if(!isOnCooldown)
		{
			if(projectile != null)
			{
				if(this.attr.isRepeater())
					startRepeater(player);
				else
				{
					player.world.addEntity(projectile);
					projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
				}
			}
			
			if(this.attr.getPotionEffectsForUser() != null)
				for(EffectInstance p : this.attr.getPotionEffectsForUser())				
					player.addPotionEffect(new EffectInstance(p));

			if(this.attr.getPotionEffectsForAoE() != null) 
				for(EffectInstance p : this.attr.getPotionEffectsForAoE())
					for(LivingEntity l : WyHelper.getEntitiesNear(player, this.attr.getEffectRadius())) 
						l.addPotionEffect(new EffectInstance(p));

			if(!(this.attr.getAbilityCharges() > 0) && this.attr.getAbilityExplosionPower() > 0)
			{
				AbilityExplosion explosion = WyHelper.newExplosion(player, player.posX, player.posY, player.posZ, this.attr.getAbilityExplosionPower());
				explosion.setDamageOwner(false);
				explosion.setFireAfterExplosion(this.attr.canAbilityExplosionSetFire());
				explosion.setDestroyBlocks(this.attr.canAbilityExplosionDestroyBlocks());
				explosion.doExplosion();
			}
			
	    	if(!player.abilities.isCreativeMode)
	    		WyTelemetry.addAbilityStat(this.getAttribute().getAbilityTexture(), this.getAttribute().getAttributeName(), 1);

	    	IAbilityData abilityProps = AbilityDataCapability.get(player);
	    	abilityProps.setPreviouslyUsedAbility(this);

	    	if(!this.attr.isPassive())
	    		this.sendShounenScream(player);
				
	    	duringRepeater(player);
			startCooldown();
			ModNetwork.sendTo(new PacketAbilityDataSync(abilityProps), (ServerPlayerEntity) player);
			(new Update(player, attr)).start();
		}
	}
	
	public void duringRepeater(PlayerEntity player)
	{
		if(isRepeating)
		{	
			try 
			{
				if(!player.world.isRemote && currentSpawn % ticksForRepeaterFreq == 0)
					player.world.addEntity(projectile.getClass().getDeclaredConstructor(World.class, LivingEntity.class, AbilityAttribute.class).newInstance(player.world, player, attr));
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			currentSpawn++;
		}
	}
	
	protected void startRepeater(PlayerEntity player)
	{
		this.isRepeating = true;
	}
	
	public boolean isRepeating()
	{
		return this.isRepeating;
	}
	
	public void passive(PlayerEntity player)
	{
		if(!isOnCooldown)
		{
			if(passiveActive)
			{
				passiveActive = false;
				ModNetwork.sendTo(new PacketAbilityDataSync(AbilityDataCapability.get(player)), (ServerPlayerEntity) player);
				if(this.attr.getPotionEffectsForUser() != null)
					for(EffectInstance p : this.attr.getPotionEffectsForUser())	
						player.removePotionEffect(p.getPotion());
				
				endPassive(player);
			}
			else
			{
				passiveActive = true;
				ModNetwork.sendTo(new PacketAbilityDataSync(AbilityDataCapability.get(player)), (ServerPlayerEntity) player);
				if(this.attr.getPotionEffectsForUser() != null)
					for(EffectInstance p : this.attr.getPotionEffectsForUser())				
						player.addPotionEffect(new EffectInstance(p.getPotion(), Integer.MAX_VALUE, p.getAmplifier(), true, false));
				
				this.sendShounenScream(player);
				
				startPassive(player);
				(new Update(player, attr)).start();
			}			
		}
	}
	
	public boolean isDisabled()
	{
		return isDisabled;
	}
	
	public void disable(PlayerEntity player, boolean bool) 
	{
		//if(bool)
		//	(new ResetDisable(player, attr)).start();
		isDisabled = bool;
		ModNetwork.sendTo(new PacketAbilityDataSync(AbilityDataCapability.get(player)), (ServerPlayerEntity) player);
	}
	
	/** Only use super. if the ability is also using passive potion effects, otherwise there's really no plus */
	public void endPassive(PlayerEntity player)
	{
		if(this.attr.getPotionEffectsForUser() != null)
			for(EffectInstance p : this.attr.getPotionEffectsForUser())	
				player.removePotionEffect(p.getPotion());
	}
	
	public void startPassive(PlayerEntity player) {}
		
	public void duringPassive(PlayerEntity player, int passiveTimer) {}
		
	public boolean isPassiveActive()
	{
		return this.passiveActive;
	}

	public void setPassiveActive(boolean b)
	{
		this.passiveActive = b;
	}
	
	public void setChargeActive(boolean b)
	{
		this.isCharging = b;
	}
	
	public void setCooldownActive(boolean b)
	{
		this.isOnCooldown = b;
	}
	
	
	public void duringCharging(PlayerEntity player, int currentCharge) {}
	
	public void startCharging(PlayerEntity player)
	{
		if(!isOnCooldown)
		{
			this.sendShounenScream(player, 1);
			
			isCharging = true;
			ModNetwork.sendTo(new PacketAbilityDataSync(AbilityDataCapability.get(player)), (ServerPlayerEntity) player);
			(new Update(player, attr)).start();
		}
	}
	
	public void endCharging(PlayerEntity player)
	{
		isCharging = false;
		isOnCooldown = true;
		if(player instanceof ServerPlayerEntity)
			ModNetwork.sendTo(new PacketAbilityDataSync(AbilityDataCapability.get(player)), (ServerPlayerEntity) player);
		
		if(projectile != null)
		{
			if(this.attr.isRepeater())
				startRepeater(player);
			else
				player.world.addEntity(projectile);
		}
		
		this.sendShounenScream(player, 2);
		
		if(this.attr.getAbilityExplosionPower() > 0)
			player.world.createExplosion(player, player.posX, player.posY, player.posZ, this.attr.getAbilityExplosionPower(), this.attr.canAbilityExplosionSetFire(), CommonConfig.instance.getGriefing() ? Mode.DESTROY : Mode.NONE);		
				
    	if(!player.abilities.isCreativeMode)
    		WyTelemetry.addAbilityStat(this.getAttribute().getAbilityTexture(), this.getAttribute().getAttributeName(), 1);

		(new Update(player, attr)).start();
	}
	
	public boolean isCharging()
	{
		return isCharging;
	}
	
	public boolean isOnCooldown()
	{
		return isOnCooldown;
	}
	
	public void duringCooldown(PlayerEntity player, int currentCooldown) {}
	
	public void hitEntity(PlayerEntity player, LivingEntity target) 
	{
		if(this.attr.getPotionEffectsForHit() != null)
			for(EffectInstance p : this.attr.getPotionEffectsForHit())				
				target.addPotionEffect(new EffectInstance(p.getPotion(), p.getDuration(), p.getAmplifier(), true, false)); 

		if(this.attr.getAbilityExplosionPower() > 0)
			player.world.createExplosion(target, target.posX, target.posY, target.posZ, this.attr.getAbilityExplosionPower(), this.attr.canAbilityExplosionSetFire(), CommonConfig.instance.getGriefing() ? Mode.DESTROY : Mode.NONE);		

		passiveActive = false;
		startCooldown();
		ModNetwork.sendTo(new PacketAbilityDataSync(AbilityDataCapability.get(player)), (ServerPlayerEntity) player);

		target.attackEntityFrom(DamageSource.causePlayerDamage(player), this.attr.getPunchDamage());
		
		(new Update(player, attr)).start();
	}
	
	protected void startCooldown()
	{
		isOnCooldown = true;
	}
	
	protected void startExtUpdate(PlayerEntity player)
	{
		ModNetwork.sendTo(new PacketAbilityDataSync(AbilityDataCapability.get(player)), (ServerPlayerEntity) player);
		(new Update(player, attr)).start();
	}
	
	public void startUpdate(PlayerEntity player)
	{
		this.setCooldownActive(true);
		if(player instanceof ServerPlayerEntity)
			ModNetwork.sendTo(new PacketAbilityDataSync(AbilityDataCapability.get(player)), (ServerPlayerEntity) player);
		(new Update(player, attr)).start();
	}
	
	protected void sendShounenScream(PlayerEntity player)
	{
		this.sendShounenScream(player, 0);
	}
	
	protected void sendShounenScream(PlayerEntity player, int part)
	{
		//if(CommonConfig.instance.getAnimeScreaming())
    	//	ModNetwork.sendToAllAround(new PacketShounenScream(player.getCommandSource().getDisplayName().toString(), this.attr.getAbilityDisplayName(), part), player.dimension, player.posX, player.posY, player.posZ, 15);
	}
	
	public void reset()
	{
		isOnCooldown = false;
		isCharging = false;
		isRepeating = false;
		passiveActive = false;			
	}
	
	class ResetDisable extends Thread
	{
		private PlayerEntity player;
		private AbilityAttribute attr;
		
		public ResetDisable(PlayerEntity user, AbilityAttribute attribute)
		{
			this.player = user;
			this.attr = attribute;
			this.setName("ResetThread Thread for " + attr.getAttributeName());
		}
		
		@Override
		public void run()
		{
			while(isDisabled)
			{
				if( !DevilFruitsHelper.isNearbyKairoseki(player)  )
				{
			    	disable(player, false);
					setCooldownActive(false);
					try
					{
						return;
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
				try 
				{
					Thread.sleep(24);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		private boolean abilityCounterpart(String ablNameForCheck)
		{
			return WyHelper.getFancyName(this.attr.getAttributeName()).equals(WyHelper.getFancyName(ablNameForCheck));
		}
	}
	
	
	class Update extends Thread
	{
		private PlayerEntity player;
		private AbilityAttribute attr;
		
		public Update(PlayerEntity user, AbilityAttribute attribute)
		{
			this.player = user;
			this.attr = attribute;
			this.setName("Update Thread for " + attr.getAttributeName());
			ticksForCooldown = this.attr.getAbilityCooldown();
			ticksForCharge = this.attr.getAbilityCharges();
		}
		
		@Override
		public void run()
		{
			if(passiveActive)
			{
				int passiveTimer = 0;
				while(passiveActive)
				{					
					try 
					{
						duringPassive(player, passiveTimer++);
						Thread.sleep(20);
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
			}
			
			if(isOnCooldown)
			{
				while(isOnCooldown)
				{
					if(ticksForCooldown > 0)
					{
						ticksForCooldown--;
						if(isRepeating)
						{
							ticksForRepeater--;
							if(ticksForRepeater > this.attr.getAbilityCooldown() - (this.attr.getAbilityCooldown() / this.attr.getAbilityRepeaterTime()) && projectile != null) {}
							else
							{
								isRepeating = false;
								ticksForRepeater = attr.getAbilityCooldown();
							}
						}
						duringCooldown(player, ticksForCooldown);
						try 
						{
							Thread.sleep(20);
						} 
						catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
					}
					else
					{
						ticksForCooldown = this.attr.getAbilityCooldown();
						currentSpawn = 0;
						isOnCooldown = false;
						if(player instanceof ServerPlayerEntity)
							ModNetwork.sendTo(new PacketAbilityDataSync(AbilityDataCapability.get(player)), (ServerPlayerEntity) player);
						return;
					}
				}	
			}
			else if(isCharging)
			{
				while(isCharging)
				{
					if(ticksForCharge > 0)
					{
						ticksForCharge--;	
						duringCharging(player, ticksForCharge);
						try 
						{
							Thread.sleep(20);
						} 
						catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
					}
					else
					{
						ticksForCharge = this.attr.getAbilityCharges();
						endCharging(player);
					}
				}
			}
		}
	}
}
