package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.abilities.effects.DFEffectSpiderOverlay;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.ExtraEffectCapability;
import xyz.pixelatedw.mineminenomi.data.entity.extraeffects.IExtraEffect;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.SupaProjectiles;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketClientSyncAll;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class SupaAbilities
{
	static
	{
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new Spider(), new SparClaw(), new AtomicSpurt(), new SpiralHollow(), new SparklingDaisy()};
	
	public static class SparklingDaisy extends Ability
	{
		private int initialY;
		
		public SparklingDaisy() 
		{
			super(ModAttributes.SPARKLING_DAISY); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{	
			if(!this.isOnCooldown())
			{
				this.initialY = (int) player.posY;
					
				double[] speed = DevilFruitsHelper.propulsion(player, 3, 3);
				
				DevilFruitsHelper.changeMotion("=", speed[0], player.getMotion().y, speed[1], player);
					
				super.use(player);
			}
		}
			
	    @Override
		public void duringCooldown(PlayerEntity player, int currentCooldown)
	    {
			if(currentCooldown > 180 && player.posY >= this.initialY)
			{
				for(LivingEntity e : WyHelper.getEntitiesNear(player, 1.6))
					e.attackEntityFrom(DamageSource.causePlayerDamage(player), 18);
				
				for(int[] location : WyHelper.getBlockLocationsNearby(player, 4))
				{
					if(location[1] >= player.posY)
					{
						if(WyHelper.placeBlockIfAllowed(player.world, location[0], location[1], location[2], Blocks.AIR, "core", "foliage", "ores"))
						{
							ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_BAKUMUNCH, location[0], location[1], location[2]), player);
						}
					}
				}
			}
	    }
	}
	
	public static class SpiralHollow extends Ability
	{
		public SpiralHollow()
		{
			super(ModAttributes.SPIRAL_HOLLOW);
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new SupaProjectiles.SpiralHollow(player.world, player, attr);
			super.use(player);
		}
	}
	
	public static class AtomicSpurt extends Ability
	{
		public AtomicSpurt()
		{
			super(ModAttributes.ATOMIC_SPURT);
		}
		
		@Override
		public void duringPassive(PlayerEntity player, int passiveTime)
		{
			if(passiveTime > 1000)
			{
				this.setPassiveActive(false);
				this.startCooldown();
				this.startExtUpdate(player);
				super.endPassive(player);
			}

	    	ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_ATOMICSPURT, player), player);
		}
		
		@Override
		public void endPassive(PlayerEntity player)
		{
			this.startCooldown();
			this.startExtUpdate(player);
		}
	}
	
	public static class SparClaw extends Ability
	{
		public SparClaw()
		{
			super(ModAttributes.SPAR_CLAW);
		}
		
		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target)
		{
			super.hitEntity(player, target);
		}
	}
	
	public static class Spider extends Ability
	{
		public Spider()
		{
			super(ModAttributes.SPIDER);
		}
		
		@Override
		public void startPassive(PlayerEntity player)
		{
			new DFEffectSpiderOverlay(player, 30);
		}
		
		@Override
		public void endPassive(PlayerEntity player)
		{
			IExtraEffect extraEffectsData = ExtraEffectCapability.get(player);
			extraEffectsData.removeExtraEffect(ID.EXTRAEFFECT_SPIDEROVERLAY);
			byte syncedData = 0b0010000;
			ModNetwork.sendToAll(new PacketClientSyncAll(player.getEntityId(), extraEffectsData, syncedData));
			super.endPassive(player);
		}
	}
	
}
