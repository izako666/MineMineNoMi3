package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.abilities.effects.DFEffectHieSlowness;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class YomiAbilities
{
	public static Ability[] abilitiesArray = new Ability[] {new KasuriutaFubukiGiri(), new SoulParade()};
	
	public static class SoulParade extends Ability
	{
		public SoulParade() 
		{
			super(ModAttributes.SOUL_PARADE); 
		}
		
		@Override
		public void passive(PlayerEntity player)
		{
			if(!player.getHeldItemMainhand().isEmpty() && ItemsHelper.isSword(player.getHeldItemMainhand()))
				super.passive(player);
			else
				WyHelper.sendMsgToPlayer(player, "You need a sword to use this ability !");
		}
		
		@Override
		public void duringPassive(PlayerEntity player, int passiveTimer)
		{
			if(player.getHeldItemMainhand().isEmpty() || !ItemsHelper.isSword(player.getHeldItemMainhand()))
				super.passive(player);
		}
	}
	
	public static class KasuriutaFubukiGiri extends Ability
	{
		public KasuriutaFubukiGiri() 
		{
			super(ModAttributes.KASURIUTA_FUBUKI_GIRI); 
		}
			
		@Override
		public void use(PlayerEntity player)
		{
			if(!player.getHeldItemMainhand().isEmpty() && ItemsHelper.isSword(player.getHeldItemMainhand()))
			{
				if(!this.isOnCooldown)
				{
					double[] speed = DevilFruitsHelper.propulsion(player, 5, 5);
				
					DevilFruitsHelper.changeMotion("=", speed[0], player.getMotion().y, speed[1], player);
					
					if (player.world instanceof ServerWorld)
						((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf(player, new SAnimateHandPacket(player, 0));
				}
				
				super.use(player);
			}
			else
				WyHelper.sendMsgToPlayer(player, "You need a sword to use this ability !");
		}
		
	    @Override
		public void duringCooldown(PlayerEntity player, int currentCooldown)
	    {
			if(currentCooldown > 6 * 20)
			{			
				for(LivingEntity e : WyHelper.getEntitiesNear(player, 1.6))
				{
					e.attackEntityFrom(DamageSource.causePlayerDamage(player), 8);
					e.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 5));
					e.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 100, 5));		
					new DFEffectHieSlowness(e, 100);
					AbilityExplosion explosion = WyHelper.newExplosion(player, e.posX, e.posY, e.posZ, 2);
					explosion.setExplosionSound(false);
					explosion.setDamageOwner(false);
					explosion.setDestroyBlocks(false);
					explosion.setSmokeParticles(ID.PARTICLEFX_SOULPARADE);
					explosion.doExplosion(); 
				}
		    	ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_KASURIUTAFUBUKIGIRI, player), player);
			}
	    }
	}
}
