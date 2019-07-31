package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.GomuProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModExtraAttributes;

public class GomuAbilities 
{
	
	static
	{
		Values.abilityWebAppExtraParams.put("gomugomunopistol", new String[] {"desc", "The user stretches their arm to hit the opponent."});
		Values.abilityWebAppExtraParams.put("gomugomunobazooka", new String[] {"desc", "The user stretches their arms to send the opponent flying by hitting them with both palms"});
		Values.abilityWebAppExtraParams.put("gearsecond", new String[] {"desc", "By speding up their blood flow, the user gains strength, speed and mobility."});
		Values.abilityWebAppExtraParams.put("gearthird", new String[] {"desc", "By blowing air and inflating their body, the user's attacks get bigger and gain incredible strength."});
		Values.abilityWebAppExtraParams.put("gearforth", new String[] {"desc", "The user inflates their muscle structure to tremendously increase the power of their attacks."});
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new GomuGomuNoPistol(), new GomuGomuNoBazooka(), new GomuGomuNoRocket(), new GomuGomuNoGatling(), new GearSecond(), new GearThird(), new GearFourth()};

	public static class GearFourth extends Ability
	{
		public GearFourth() 
		{
			super(ModAttributes.GEAR_FOURTH); 
		}
		
		@Override
		public void passive(PlayerEntity player)
		{
			IAbilityData abilityProps = AbilityDataCapability.get(player); 
			
			if(abilityProps.hasHakiAbility(HakiAbilities.BUSOSHOKU_HAKI))
				super.passive(player);
			else
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be activated while Busoshoku Haki is active !");
		}

		@Override
		public void duringPassive(PlayerEntity player, int passiveTimer)
		{
			player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 25, 2, false, false));
			
			if(passiveTimer >= 600)
			{
				super.setPassiveActive(false);
				super.use(player);
			}
		}
		
		@Override
		public void endPassive(PlayerEntity player)
		{
			super.use(player);
		} 
	}	
	
	public static class GearThird extends Ability
	{
		public GearThird() 
		{
			super(ModAttributes.GEAR_THIRD); 
		}
		
		@Override
		public void duringPassive(PlayerEntity player, int passiveTimer)
		{
			if(passiveTimer >= 1200)
			{
				super.setPassiveActive(false);
				super.use(player);
			}			
		} 
		
		@Override
		public void endPassive(PlayerEntity player)
		{
			super.use(player);
		} 
	}		
	
	
	public static class GearSecond extends Ability
	{
		public GearSecond() 
		{
			super(ModAttributes.GEAR_SECOND); 
		}

		@Override
		public void duringPassive(PlayerEntity player, int passiveTimer)
		{
			IEntityStats props = EntityStatsCapability.get(player);

			player.addPotionEffect(new EffectInstance(Effects.SPEED, 25, 1, false, false));
			//if(!player.world.isRemote)
	    	//	WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_GEARSECOND, player), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
			
			if(passiveTimer >= 1200)
			{
				super.setPassiveActive(false);
				super.use(player);
			}			
		} 
		
		@Override
		public void endPassive(PlayerEntity player)
		{
			super.use(player);
		} 
	}
	
	protected static boolean hasGearSecondActive(IAbilityData abilityProps)
	{
		GearSecond gearSecond = (GearSecond) abilityProps.getHotbarAbilityFromName(ModAttributes.GEAR_SECOND.getAttributeName());
		return gearSecond != null && gearSecond.isPassiveActive();
	}
	
	protected static boolean hasGearThirdActive(IAbilityData abilityProps)
	{
		GearThird gearThird = (GearThird) abilityProps.getHotbarAbilityFromName(ModAttributes.GEAR_THIRD.getAttributeName());
		return gearThird != null && gearThird.isPassiveActive();
	}
	
	protected static boolean hasGearFourthActive(IAbilityData abilityProps)
	{
		GearFourth gearFourth = (GearFourth) abilityProps.getHotbarAbilityFromName(ModAttributes.GEAR_FOURTH.getAttributeName());
		return gearFourth != null && gearFourth.isPassiveActive();
	}
	
	public static class GomuGomuNoGatling extends Ability
	{
		public GomuGomuNoGatling() 
		{
			super(ModAttributes.GOMU_GOMU_NO_GATLING); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{		
			if(!this.isOnCooldown)
			{
				IAbilityData abilityProps = AbilityDataCapability.get(player);
				
				int type = 0;
				int projectileSpace = 3;

				if(hasGearSecondActive(abilityProps))
				{
					type = 1;
					if(CommonConfig.instance.getAnimeScreaming()) 
						this.attr.setAbilityDisplayName("Gomu Gomu no Jet Gatling");
					this.attr.setAbilityCooldown(2);
				}
				else if(hasGearThirdActive(abilityProps))
				{
					type = 2;
					projectileSpace = 7;
					if(CommonConfig.instance.getAnimeScreaming()) 
						this.attr.setAbilityDisplayName("Gomu Gomu no Elephant Gatling");
					this.attr.setAbilityCooldown(6);
				}
				else if(hasGearFourthActive(abilityProps))
				{
					type = 3;
					projectileSpace = 7;
					if(CommonConfig.instance.getAnimeScreaming()) 
						this.attr.setAbilityDisplayName("Gomu Gomu no Kong Organ");
					this.attr.setAbilityCooldown(7);
				}
				else
				{
					type = 0;
					if(CommonConfig.instance.getAnimeScreaming()) 
						this.attr.setAbilityDisplayName("Gomu Gomu no Gatling");
					this.attr.setAbilityCooldown(4);
				}
				
				for(int j = 0; j < 25; j++)
				{
					AbilityProjectile proj = null;
					if(type == 0)
						proj = new GomuProjectiles.GomuGomuNoGatling(player.world, player, ModExtraAttributes.GOMU_GOMU_NO_GATLING);
					else if(type == 1)
						proj = new GomuProjectiles.GomuGomuNoJetGatling(player.world, player, ModExtraAttributes.GOMU_GOMU_NO_JET_GATLING);
					else if(type == 2)
						proj = new GomuProjectiles.GomuGomuNoElephantGatling(player.world, player, ModExtraAttributes.GOMU_GOMU_NO_ELEPHANT_GATLING);
					else if(type == 3)
						proj = new GomuProjectiles.GomuGomuNoKongOrgan(player.world, player, ModExtraAttributes.GOMU_GOMU_NO_KONG_ORGAN);
					
					proj.setLocationAndAngles(
							player.posX + WyMathHelper.randomWithRange(-projectileSpace, projectileSpace) + player.world.rand.nextDouble(), 
							(player.posY + 0.3) + WyMathHelper.randomWithRange(0, projectileSpace) + player.world.rand.nextDouble(), 
							player.posZ + WyMathHelper.randomWithRange(-projectileSpace, projectileSpace) + player.world.rand.nextDouble(), 
							0, 0);
					player.world.addEntity(proj);
					proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
				}
				
				super.use(player);
			}
		} 
	}
	
	public static class GomuGomuNoRocket extends Ability
	{
		public GomuGomuNoRocket() 
		{
			super(ModAttributes.GOMU_GOMU_NO_ROCKET); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new GomuProjectiles.GomuGomuNoRocket(player.world, player, ModAttributes.GOMU_GOMU_NO_ROCKET);
			super.use(player);
		} 
	}
	
	public static class GomuGomuNoBazooka extends Ability
	{
		public GomuGomuNoBazooka() 
		{
			super(ModAttributes.GOMU_GOMU_NO_BAZOOKA); 
		}

		@Override
		public void startCharging(PlayerEntity player)
		{
			IAbilityData abilityProps = AbilityDataCapability.get(player);
			
			if(CommonConfig.instance.getAnimeScreaming())
			{
				if(hasGearSecondActive(abilityProps))
					this.attr.setAbilityDisplayName("Gomu Gomu no Jet Bazooka");
				else if(hasGearThirdActive(abilityProps))
					this.attr.setAbilityDisplayName("Gomu Gomu no Grizzly Magnum");
				else if(hasGearFourthActive(abilityProps))
					this.attr.setAbilityDisplayName("Gomu Gomu no Leo Bazooka");
				else
					this.attr.setAbilityDisplayName("Gomu Gomu no Bazooka");			
			}
			
			super.startCharging(player);
		}
		
		@Override
		public void endCharging(PlayerEntity player)
		{
			IAbilityData abilityProps = AbilityDataCapability.get(player);

			if(hasGearSecondActive(abilityProps))
			{
				this.projectile = new GomuProjectiles.GomuGomuNoJetBazooka(player.world, player, ModExtraAttributes.GOMU_GOMU_NO_JET_BAZOOKA);
				this.attr.setAbilityCooldown(6);
				this.attr.setAbilityCharges(13);
			}
			else if(hasGearThirdActive(abilityProps))
			{
				this.projectile = new GomuProjectiles.GomuGomuNoGrizzlyMagnum(player.world, player, ModExtraAttributes.GOMU_GOMU_NO_GRIZZLY_MAGNUM);
				this.attr.setAbilityCooldown(20);
				this.attr.setAbilityCharges(40);
			}
			else if(hasGearFourthActive(abilityProps))
			{
				this.projectile = new GomuProjectiles.GomuGomuNoLeoBazooka(player.world, player, ModExtraAttributes.GOMU_GOMU_NO_LEO_BAZOOKA);
				this.attr.setAbilityCooldown(30);
				this.attr.setAbilityCharges(40);
			}
			else
			{
				this.projectile = new GomuProjectiles.GomuGomuNoBazooka(player.world, player, ModExtraAttributes.GOMU_GOMU_NO_BAZOOKA);
				this.attr.setAbilityCooldown(12);
				this.attr.setAbilityCharges(18);
			}
			
			super.endCharging(player);
		} 
	}
	
	public static class GomuGomuNoPistol extends Ability
	{
		public GomuGomuNoPistol() 
		{
			super(ModAttributes.GOMU_GOMU_NO_PISTOL); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			IAbilityData abilityProps = AbilityDataCapability.get(player);

			if(hasGearSecondActive(abilityProps))
			{
				if(CommonConfig.instance.getAnimeScreaming())
					this.attr.setAbilityDisplayName("Gomu Gomu no Jet Pistol");
				this.projectile = new GomuProjectiles.GomuGomuNoJetPistol(player.world, player, ModExtraAttributes.GOMU_GOMU_NO_JET_PISTOL);
				this.attr.setAbilityCooldown(5);
			}
			else if(hasGearThirdActive(abilityProps))
			{
				if(CommonConfig.instance.getAnimeScreaming())
					this.attr.setAbilityDisplayName("Gomu Gomu no Elephant Gun");
				this.projectile = new GomuProjectiles.GomuGomuNoElephantGun(player.world, player, ModExtraAttributes.GOMU_GOMU_NO_ELEPHANT_GUN);
				this.attr.setAbilityCooldown(15);
			}
			else if(hasGearFourthActive(abilityProps))
			{
				if(CommonConfig.instance.getAnimeScreaming())
					this.attr.setAbilityDisplayName("Gomu Gomu no Kong Gun");
				this.projectile = new GomuProjectiles.GomuGomuNoKongGun(player.world, player, ModExtraAttributes.GOMU_GOMU_NO_KONG_GUN);
				this.attr.setAbilityCooldown(30);
			}
			else
			{
				if(CommonConfig.instance.getAnimeScreaming()) 
					this.attr.setAbilityDisplayName("Gomu Gomu no Pistol");
				this.projectile = new GomuProjectiles.GomuGomuNoPistol(player.world, player, ModExtraAttributes.GOMU_GOMU_NO_PISTOL);
				this.attr.setAbilityCooldown(8);
			}

			super.use(player);
		} 
	}
}
