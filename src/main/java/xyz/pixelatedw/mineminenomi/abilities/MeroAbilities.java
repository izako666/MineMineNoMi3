package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.abilities.effects.DFEffectMeroPetrification;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.MeroProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class MeroAbilities
{
	static
	{
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new MeroMeroMellow(), new PistolKiss(), new SlaveArrow(), new PerfumeFemur()};
	
	public static class PerfumeFemur extends Ability
	{
		public PerfumeFemur() 
		{
			super(ModAttributes.PERFUME_FEMUR); 
		}
		
		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target)
		{
			super.hitEntity(player, target);
			
			new DFEffectMeroPetrification(target, 400);
			
			AbilityExplosion explosion = WyHelper.newExplosion(player, target.posX, target.posY, target.posZ, 2);
			explosion.setExplosionSound(false);
			explosion.setDamageOwner(false);
			explosion.setDestroyBlocks(false);
			explosion.setSmokeParticles(ID.PARTICLEFX_PERFUMEFEMUR);
			explosion.doExplosion();
		}
	}
	
	public static class SlaveArrow extends Ability
	{
		public SlaveArrow() 
		{
			super(ModAttributes.SLAVE_ARROW); 
		}
		
		@Override
		public void startCharging(PlayerEntity player)
		{
			if(!this.isOnCooldown)
				ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_SLAVEARROW, player), player);
			super.startCharging(player);				
		}

		@Override
		public void endCharging(PlayerEntity player)
		{						
			this.projectile = new MeroProjectiles.SlaveArrow(player.world, player, attr);
			super.endCharging(player);
		}

	}
	
	public static class PistolKiss extends Ability
	{
		public PistolKiss()
		{
			super(ModAttributes.PISTOL_KISS);
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new MeroProjectiles.PistolKiss(player.world, player, attr);
			super.use(player);
		}
	}
	
	public static class MeroMeroMellow extends Ability
	{
		public MeroMeroMellow()
		{
			super(ModAttributes.MERO_MERO_MELLOW);
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new MeroProjectiles.MeroMeroMellow(player.world, player, attr);
			super.use(player);
		}
	}
}
