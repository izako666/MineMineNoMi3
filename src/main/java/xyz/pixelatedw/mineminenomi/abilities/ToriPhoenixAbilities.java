package xyz.pixelatedw.mineminenomi.abilities;

import java.util.Random;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.ToriPhoenixProjectiles;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketClientSyncAll;
import xyz.pixelatedw.mineminenomi.packets.PacketDevilFruitSync;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class ToriPhoenixAbilities
{

	static
	{
		Values.abilityWebAppExtraParams.put("hybridpoint", new String[] {"desc", "The user transforms into a phoenix-human hybrid, which allows them to fly. Allows the user to use 'Phoenix Goen' "});
		Values.abilityWebAppExtraParams.put("phoenixpoint", new String[] {"desc", "The user fully transforms into a phoenix, allowing them to fly at great speed. Allows the user to use both 'Phoenix Goen' and 'Tensei no Soen'"});
		Values.abilityWebAppExtraParams.put("phoenixgoen", new String[] {"desc", "Launches a powerful fiery shockwave made of blue flames at the target."});
		Values.abilityWebAppExtraParams.put("tenseinosoen", new String[] {"desc", "While in the air, the user amasses spiraling flames, then slams into the ground, releasing a massive shockwave."});		
		Values.abilityWebAppExtraParams.put("blueflamesofresurrection", new String[] {"desc", "Blue phoenix flames grant the user regeneration."});
		Values.abilityWebAppExtraParams.put("flameofrestoration", new String[] {"desc", "Uses the blue flames to heal the target."});	
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new PhoenixPoint(), new HybridPoint(), new BlueFlamesOfResurrection(), new FlameOfRestoration(), new PhoenixGoen(), new TenseiNoSoen()};
	
	public static class HybridPoint extends Ability
	{
		public HybridPoint()
		{
			super(ModAttributes.PHOENIX_HYBRID_POINT);
		}

		@Override
		public void passive(PlayerEntity player)
		{
			IDevilFruit props = DevilFruitCapability.get(player);

			if (!this.isOnCooldown && (WyHelper.isNullOrEmpty(props.getZoanPoint()) || props.getZoanPoint().equalsIgnoreCase("hybrid")))
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

			props.setZoanPoint("hybrid");
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
	
	public static class TenseiNoSoen extends Ability
	{
		private int particlesSpawned = 0;
		
		public TenseiNoSoen() 
		{
			super(ModAttributes.TENSEI_NO_SOEN); 
		}
		
		@Override
		public void startCharging(PlayerEntity player)
		{
			IDevilFruit props = DevilFruitCapability.get(player);
			particlesSpawned = 0;
			
			if((props.getZoanPoint().equals("full")) && !this.isOnCooldown)
			{
				if(!player.onGround)
				{
					ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_TENSEINOSOEN1, player), player);
					super.startCharging(player);
				}
				else
					WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while airborne !");
			}
			else if(!props.getZoanPoint().equals("full"))
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Phoenix Point is active !");
		}
		
		@Override
		public void endCharging(PlayerEntity player)
		{
			player.fallDistance = 0;
			DevilFruitsHelper.changeMotion("=", 0, -100, 0, player);
			super.endCharging(player);
		}		
		
	    @Override
		public void duringCooldown(PlayerEntity player, int currentCooldown)
	    {
			if(currentCooldown > 28 * 20)
			{
				if(player.onGround && particlesSpawned < 10)
				{
					ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_TENSEINOSOEN2, player), player);
					particlesSpawned++;
				}			
				
				for(LivingEntity e : WyHelper.getEntitiesNear(player, 20))
				{
					e.attackEntityFrom(DamageSource.causePlayerDamage(player), 30);
				}
			}
	    }
	}
	
	public static class PhoenixGoen extends Ability
	{
		public PhoenixGoen() 
		{
			super(ModAttributes.PHOENIX_GOEN); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			IDevilFruit props = DevilFruitCapability.get(player);

			if((props.getZoanPoint().equals("full") || props.getZoanPoint().equals("hybrid")) && !this.isOnCooldown)
			{
				for (int i = 0; i < 100; i++)
				{
					double offsetX = (new Random().nextInt(20) + 1.0D - 5.0D) / 5.0D;
					double offsetY = (new Random().nextInt(20) + 1.0D - 20.0D) / 10.0D;
					double offsetZ = (new Random().nextInt(20) + 1.0D - 5.0D) / 5.0D;
						
					this.projectile = new ToriPhoenixProjectiles.PhoenixGoen(player.world, player, attr);
					this.projectile.setLocationAndAngles(player.posX - 1 + offsetX, player.posY + 2 + offsetY, player.posZ + offsetZ, player.rotationPitch, player.cameraYaw);
					player.world.addEntity(projectile);
					projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
				}
					
				ModNetwork.sendTo(new PacketDevilFruitSync(props), (ServerPlayerEntity) player);
				super.use(player);
			}
			else if(!props.getZoanPoint().equals("full") && !props.getZoanPoint().equals("hybrid"))
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Phoenix Point or Hybrid Point is active !");
		}	
	}
	
	public static class FlameOfRestoration extends Ability
	{
		public FlameOfRestoration() 
		{
			super(ModAttributes.FLAME_OF_RESTORATION); 
		}
		
		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target) 
		{
			super.hitEntity(player, target);
			
			if(target.getHealth() + 6 < target.getMaxHealth())
				target.setHealth(target.getHealth() + 6);
			else
				target.setHealth(target.getMaxHealth());
			
			ModNetwork.sendTo(new PacketParticles(ID.PARTICLEFX_BLUEFLAMES, player), (ServerPlayerEntity) player);
		}
	}	
	
	public static class BlueFlamesOfResurrection extends Ability
	{
		public BlueFlamesOfResurrection() 
		{
			super(ModAttributes.BLUE_FLAMES_OF_RESURRECTION); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			if(!isOnCooldown)
			{
				ModNetwork.sendTo(new PacketParticles(ID.PARTICLEFX_BLUEFLAMES, player), (ServerPlayerEntity) player);
			}
			super.use(player);
		}
	}	
	
	public static class PhoenixPoint extends Ability
	{
		public PhoenixPoint()
		{
			super(ModAttributes.PHOENIX_FULL_POINT);
		}

		@Override
		public void passive(PlayerEntity player)
		{
			IDevilFruit props = DevilFruitCapability.get(player);

			if (!this.isOnCooldown && (WyHelper.isNullOrEmpty(props.getZoanPoint()) || props.getZoanPoint().equalsIgnoreCase("full")))
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

			props.setZoanPoint("full");
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
	
}
