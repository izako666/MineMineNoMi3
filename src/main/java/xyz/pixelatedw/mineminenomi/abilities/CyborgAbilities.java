package xyz.pixelatedw.mineminenomi.abilities;

import java.util.Random;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.CyborgProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketEntityStatsSync;

public class CyborgAbilities 
{
	static
	{
		Values.abilityWebAppExtraParams.put("freshfire", new String[] {"desc", "The user heats up and breathes fire like a flamethrower at the opponent.", "dorikiRequiredForCyborgs", "0"});
		Values.abilityWebAppExtraParams.put("colaoverdrive", new String[] {"desc", "The user absorbs all of their cola at once to boost their physical abilities.", "dorikiRequiredForCyborgs", "0"});
		Values.abilityWebAppExtraParams.put("radicalbeam", new String[] {"desc", "After charging, the user launches a powerful beam of energy at the opponent.", "dorikiRequiredForCyborgs", "0"});
		Values.abilityWebAppExtraParams.put("strongright", new String[] {"desc", "The user punches the opponent with a metal fist.", "dorikiRequiredForCyborgs", "0"});
		Values.abilityWebAppExtraParams.put("coupdevent", new String[] {"desc", "Launches a powerful blast of compressed air that blows the opponent away.", "dorikiRequiredForCyborgs", "0"});
	}
	
	public static Ability FRESH_FIRE = new FreshFire();
	public static Ability COLA_OVERDRIVE = new ColaOverdrive();
	public static Ability RADICAL_BEAM = new RadicalBeam();
	public static Ability STRONG_RIGHT = new StrongRight();
	public static Ability COUP_DE_VENT = new CoupDeVent();
	
	public static Ability[] abilitiesArray = new Ability[] {FRESH_FIRE, COLA_OVERDRIVE, RADICAL_BEAM, STRONG_RIGHT, COUP_DE_VENT};
	
	public static class CoupDeVent extends Ability
	{
		public CoupDeVent() 
		{
			super(ModAttributes.COUP_DE_VENT); 
		}
		
		@Override
		public void startCharging(PlayerEntity player)
		{
			IEntityStats statsProps = EntityStatsCapability.get(player);

			if(!isOnCooldown && statsProps.getCola() >= 25)
				super.startCharging(player);
			else if(statsProps.getCola() < 25)
				WyHelper.sendMsgToPlayer(player, "Not enough Cola !");					
		}
		
		@Override
		public void duringCharging(PlayerEntity player, int currentCharge)
		{		
			player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 10, 1000));
			player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 10, 1000));	
		}
		
		@Override
		public void endCharging(PlayerEntity player)
		{
			IEntityStats statsProps = EntityStatsCapability.get(player);

			statsProps.alterCola(-25);
			isCharging = false;
			isOnCooldown = true;	
					
			for (int i = 0; i < 100; i++)
			{
				double offsetX = (new Random().nextInt(50) + 1.0D - 25.0D) / 20.0D;
				double offsetY = (new Random().nextInt(50) + 1.0D - 25.0D) / 20.0D;
				double offsetZ = (new Random().nextInt(50) + 1.0D - 25.0D) / 20.0D;
						
				this.projectile = new CyborgProjectiles.CoupDeVent(player.world, player, attr);
				this.projectile.setLocationAndAngles(player.posX + offsetX, player.posY + 2 + offsetY, player.posZ + offsetZ, player.rotationPitch, player.prevRotationYaw);
				player.world.addEntity(projectile);
				projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
			}
				
			ModNetwork.sendTo(new PacketEntityStatsSync(statsProps), (ServerPlayerEntity) player);
			startCooldown();
			startExtUpdate(player);
		}	
	}
	
	public static class StrongRight extends Ability
	{
		public StrongRight() 
		{
			super(ModAttributes.STRONG_RIGHT); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			IEntityStats props = EntityStatsCapability.get(player);

			if(!player.world.isRemote)
			{				
				if(!this.isOnCooldown && props.getCola() >= 10)
				{					
					this.projectile = new CyborgProjectiles.StrongRight(player.world, player, attr);
					player.world.addEntity(projectile);

					props.alterCola(-10);					
					ModNetwork.sendTo(new PacketEntityStatsSync(props), (ServerPlayerEntity) player);
					super.use(player);
				}
				else if(props.getCola() < 10)
					WyHelper.sendMsgToPlayer(player, "Not enough Cola !");
			}
		}			
	}
	
	public static class RadicalBeam extends Ability
	{
		public RadicalBeam() 
		{
			super(ModAttributes.RADICAL_BEAM); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			IEntityStats props = EntityStatsCapability.get(player);
			
			if(!player.world.isRemote)
			{
				if(!this.isOnCooldown && props.getCola() >= 15)
				{
					this.projectile = new CyborgProjectiles.RadicalBeam(player.world, player, attr);
					player.world.addEntity(projectile);

					props.alterCola(-15);
					ModNetwork.sendTo(new PacketEntityStatsSync(props), (ServerPlayerEntity) player);
					super.use(player);
				}
				else if(props.getCola() < 15)
					WyHelper.sendMsgToPlayer(player, "Not enough Cola !");
			}
		}			
	}
	
	public static class FreshFire extends Ability
	{
		public FreshFire() 
		{
			super(ModAttributes.FRESH_FIRE); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			IEntityStats props = EntityStatsCapability.get(player);

			if(!this.isOnCooldown && props.getCola() >= 5)
			{
				for (int i = 0; i < 100; i++)
				{
					double offsetX = (new Random().nextInt(50) + 1.0D - 25.0D) / 20.0D;
					double offsetY = (new Random().nextInt(50) + 1.0D - 25.0D) / 20.0D;
					double offsetZ = (new Random().nextInt(50) + 1.0D - 25.0D) / 20.0D;
						
					this.projectile = new CyborgProjectiles.FreshFire(player.world, player, attr);
					this.projectile.setLocationAndAngles(player.posX + offsetX, player.posY + 2 + offsetY, player.posZ + offsetZ, player.rotationPitch, player.prevRotationYaw);
					player.world.addEntity(projectile);
				}
					
				props.alterCola(-5);
				ModNetwork.sendTo(new PacketEntityStatsSync(props), (ServerPlayerEntity) player);
				super.use(player);
			}
			else if(props.getCola() < 5)
				WyHelper.sendMsgToPlayer(player, "Not enough Cola !");
		}	
	}
	
	public static class ColaOverdrive extends Ability
	{
		public ColaOverdrive() 
		{
			super(ModAttributes.COLA_OVERDRIVE); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			IEntityStats props = EntityStatsCapability.get(player);
			
			if(!isOnCooldown && props.getCola() > 0)
			{
				double r = (props.getCola() / props.getMaxCola()) * 100;
				
				props.setCola(0);
				
				player.setHealth((float) (player.getHealth() + ((r / 100) * player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue()) ));	
				
				ModNetwork.sendTo(new PacketEntityStatsSync(props), (ServerPlayerEntity) player);
				super.use(player);
			}
			else if(props.getCola() <= 0)
				WyHelper.sendMsgToPlayer(player, "Not enough Cola !");
		}		
	}
}
