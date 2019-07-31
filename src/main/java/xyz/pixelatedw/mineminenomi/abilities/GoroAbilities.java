package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.network.PacketAbilityDataSync;
import xyz.pixelatedw.mineminenomi.api.telemetry.WyTelemetry;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.GoroProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.GoroProjectiles.VoltVari200Million;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.GoroProjectiles.VoltVari20Million;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.GoroProjectiles.VoltVari5Million;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.GoroProjectiles.VoltVari60Million;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModExtraAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class GoroAbilities
{
	
	static
	{
		Values.abilityWebAppExtraParams.put("elthor", new String[] {"desc", "Focuses the user's electricity to strike the opponent with lightning from above."});
		Values.abilityWebAppExtraParams.put("voltvari", new String[] {"desc", "Creates a concentrated ball of lightning, which varies in power."});
		Values.abilityWebAppExtraParams.put("raigo", new String[] {"desc", "Creates a huge cloud filled with electricity, which causes massive damage."});
		Values.abilityWebAppExtraParams.put("kari", new String[] {"desc", "Creates an electrical current around the user, which then explodes."});
		Values.abilityWebAppExtraParams.put("sango", new String[] {"desc", "Launches a huge concentrated chunk of electricity at the opponent."});
	}

	public static Ability[] abilitiesArray = new Ability[] {new ElThor(), new VoltVari(), new Raigo(), new Kari(), new Sango(), new SparkStep()};
	
	public static class ElThor extends Ability
	{
		public ElThor() 
		{
			super(ModAttributes.EL_THOR); 
		}
		
		@Override
		public void startCharging(PlayerEntity player)
		{
			super.startCharging(player);				
		}
		
		@Override
		public void duringCharging(PlayerEntity player, int currentCharge)
		{
			RayTraceResult mop = WyHelper.rayTraceBlocks(player);	
			
			if(mop != null)
			{
				double i = mop.getHitVec().x;
				double j = mop.getHitVec().y;
				double k = mop.getHitVec().z;

				ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_ELTHOR, i, j, k), (ServerPlayerEntity) player);
			}
		}
		
		@Override
		public void endCharging(PlayerEntity player)
		{						
			RayTraceResult mop = WyHelper.rayTraceBlocks(player);	
			
			if(mop != null)
			{
				double i = mop.getHitVec().x;
				double j = mop.getHitVec().y;
				double k = mop.getHitVec().z;

				if(player.world instanceof ClientWorld)
				{
					((ClientWorld) player.world).addLightning(new LightningBoltEntity(player.world, i + 1, j, k, false));
					((ClientWorld) player.world).addLightning(new LightningBoltEntity(player.world, i, j, k + 1, false));
					((ClientWorld) player.world).addLightning(new LightningBoltEntity(player.world, i - 1, j, k, false));
					((ClientWorld) player.world).addLightning(new LightningBoltEntity(player.world, i, j, k - 1, false));
				}
				AbilityExplosion exp = WyHelper.newExplosion(player, i, j, k, 10);
				exp.setFireAfterExplosion(true);
				exp.doExplosion();
			}
			
			super.endCharging(player);
		}
	}
	
	public static class VoltVari extends Ability
	{
		private int power = 0;
		
		public VoltVari() 
		{
			super(ModAttributes.VOLT_VARI); 
		}
		
		@Override
		public void startCharging(PlayerEntity player)
		{
			if(!this.isOnCooldown)
			{
				isCharging = true;
				this.startExtUpdate(player);
			}
		}
		
		@Override
		public void duringCharging(PlayerEntity player, int currentCharge)
		{		
			power = currentCharge;
			double truePower = Math.abs(power - this.attr.getAbilityCharges());

			if(truePower % 25 == 0 && CommonConfig.instance.getAnimeScreaming())
			{
				int voltVariType = (int) Math.floor(truePower / 25);
				switch(voltVariType)
				{
					case 1:
						this.attr.setAbilityDisplayName("1 Million Volt Vari");
						break;
					case 2:
						this.attr.setAbilityDisplayName("5 Million Volt Vari");
						break;
					case 3:
						this.attr.setAbilityDisplayName("10 Million Volt Vari");
						break;
					case 4:
						this.attr.setAbilityDisplayName("20 Million Volt Vari");
						break;
					case 5:
						this.attr.setAbilityDisplayName("50 Million Volt Vari");
						break;
					case 6:
						this.attr.setAbilityDisplayName("60 Million Volt Vari");
						break;
					case 7:
						this.attr.setAbilityDisplayName("100 Million Volt Vari");
						break;
				}
				
				if(voltVariType < 8)
					this.sendShounenScream(player);
			}
		}
		
		@Override
		public void endCharging(PlayerEntity player)
		{
			double truePower = Math.abs(power - this.attr.getAbilityCharges());
			double trueCooldown = (truePower / 20) * 3;

			if(truePower > 0 && truePower <= 50)
			{
				if(CommonConfig.instance.getAnimeScreaming())
				{
					if(truePower > 0 && truePower <= 25)
						this.attr.setAbilityDisplayName("1 Million Volt Vari");
					else
						this.attr.setAbilityDisplayName("5 Million Volt Vari");
				}
				this.projectile = new VoltVari5Million(player.world, player, ModExtraAttributes.VOLT_VARI_5_MILLION);
			}
			else if(truePower > 50 && truePower <= 100)
			{
				if(CommonConfig.instance.getAnimeScreaming())
				{
					if(truePower > 50 && truePower <= 75)
						this.attr.setAbilityDisplayName("10 Million Volt Vari");
					else
						this.attr.setAbilityDisplayName("20 Million Volt Vari");
				}
				this.projectile = new VoltVari20Million(player.world, player, ModExtraAttributes.VOLT_VARI_20_MILLION);
			}
			else if(truePower > 100 && truePower <= 150)
			{
				if(CommonConfig.instance.getAnimeScreaming())
				{
					if(truePower > 100 && truePower <= 125)
						this.attr.setAbilityDisplayName("50 Million Volt Vari");
					else
						this.attr.setAbilityDisplayName("60 Million Volt Vari");
				}
				this.projectile = new VoltVari60Million(player.world, player, ModExtraAttributes.VOLT_VARI_60_MILLION);
			}
			else if(truePower > 150 && truePower <= 200)
			{
				if(CommonConfig.instance.getAnimeScreaming())
				{
					if(truePower > 150 && truePower <= 175)
						this.attr.setAbilityDisplayName("100 Million Volt Vari");
					else
						this.attr.setAbilityDisplayName("Max 200 Million Volt Vari");
				}
				this.projectile = new VoltVari200Million(player.world, player, ModExtraAttributes.VOLT_VARI_200_MILLION);
			}
			
			this.sendShounenScream(player);
				
			this.attr.setAbilityCooldown(trueCooldown);

			this.isCharging = false;
			this.isOnCooldown = true;	
			ModNetwork.sendTo(new PacketAbilityDataSync(AbilityDataCapability.get(player)), (ServerPlayerEntity) player);

	    	if(!player.abilities.isCreativeMode)
	    		WyTelemetry.addAbilityStat(this.getAttribute().getAbilityTexture(), this.getAttribute().getAttributeName(), 1);

			if(projectile != null)
			{
				player.world.addEntity(projectile);
				projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
			}
			
			this.startExtUpdate(player);
		}
	}
	
	public static class Raigo extends Ability
	{
		public Raigo() 
		{
			super(ModAttributes.RAIGO); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{			
			if(!this.isOnCooldown)		
			{
				RayTraceResult mop = WyHelper.rayTraceBlocks(player);	
				
				if(mop != null)
				{
					double x = mop.getHitVec().x;
					double y = mop.getHitVec().y;
					double z = mop.getHitVec().z;
					
					ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_RAIGO, x, player.posY, z), (ServerPlayerEntity) player);
					
					AbilityProjectile proj = new GoroProjectiles.Raigo(player.world, player, ModAttributes.RAIGO);	
					proj.setLocationAndAngles(x, (y + 90), z, 0, 0);
					proj.setMotion(0, -1.4, 0);
					player.world.addEntity(proj);
				}
			}
			super.use(player);
		} 
	}
	
	public static class Kari extends Ability
	{
		public Kari() 
		{
			super(ModAttributes.KARI); 
		}
		
		@Override
		public void startCharging(PlayerEntity player)
		{
			if(!this.isOnCooldown)		
				ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_KARI, player), (ServerPlayerEntity) player);
			super.startCharging(player);				
		}
		
		@Override
		public void endCharging(PlayerEntity player)
		{						
			super.endCharging(player);
		}
	}
	
	public static class Sango extends Ability
	{
		public Sango() 
		{
			super(ModAttributes.SANGO); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new GoroProjectiles.Sango(player.world, player, attr);
			super.use(player);
		} 
	}

	public static class SparkStep extends Ability
	{
		public SparkStep() {
			super(ModAttributes.SPARK_STEP);
		}
		@Override
		public void use(PlayerEntity player) {
			if (!this.isOnCooldown) {
				if(WyHelper.rayTraceBlocks(player) != null)
				{
					RayTraceResult blockTracer = WyHelper.rayTraceBlocks(player);
					int[] blockLocation = new int[]{(int) blockTracer.getHitVec().x, (int) blockTracer.getHitVec().y, (int) blockTracer.getHitVec().z};
					while (!(player.getEntityWorld().getBlockState(new BlockPos(blockLocation[0],(blockLocation[1]),blockLocation[2])) == Blocks.AIR.getDefaultState())) {
						blockLocation[1] += 1;
					}
					EnderTeleportEvent event = new EnderTeleportEvent(player, blockLocation[0], blockLocation[1], blockLocation[2], 0);
					ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_ELTHOR, player), (ServerPlayerEntity) player);
					player.setPositionAndUpdate(event.getTargetX(), event.getTargetY() + 1, event.getTargetZ());
					ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_ELTHOR, player), (ServerPlayerEntity) player);
					player.fallDistance = 0.0F;


				}
				super.use(player);
			}
		}
	}
	
}
