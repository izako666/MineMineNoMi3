package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.SwordsmanProjectiles;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class SwordsmanAbilities 
{
	static
	{
		Values.abilityWebAppExtraParams.put("shishishisonson", new String[] {"desc", "The user dashes forward and rapidly slashes the opponent."});
		Values.abilityWebAppExtraParams.put("yakkodori", new String[] {"desc", "Launches a crescent moon-shaped slash, which destroys everything in its path."});
		Values.abilityWebAppExtraParams.put("sanbyakurokujupoundho", new String[] {"desc", "The user launches a powerful slash, causing great destruction."});
		Values.abilityWebAppExtraParams.put("otatsumaki", new String[] {"desc", "By spinning, the user creates a small tornado, which slashes and weakens nearby opponents."});
	}
	
	public static Ability SHI_SHISHI_SONSON = new ShiShishiSonson();
	public static Ability SANBYAKUROKUJU_POUND_HO = new SanbyakurokujuPoundHo();
	public static Ability YAKKODORI = new Yakkodori();
	public static Ability O_TATSUMAKI = new OTatsumaki();
	
	public static Ability[] abilitiesArray = new Ability[] {SHI_SHISHI_SONSON, SANBYAKUROKUJU_POUND_HO, YAKKODORI, O_TATSUMAKI};
	
	public static class OTatsumaki extends Ability
	{
		public OTatsumaki() 
		{
			super(ModAttributes.O_TATSUMAKI); 
		}
			
		@Override
		public void use(PlayerEntity player)
		{
			if((player.getHeldItemMainhand() != null && ItemsHelper.isSword(player.getHeldItemMainhand())) || DevilFruitsHelper.canUseSwordsmanAbilities(player))
			{	
				if(!this.isOnCooldown)
				{
					for(LivingEntity e : WyHelper.getEntitiesNear(player, 2.5))
					{
						e.attackEntityFrom(DamageSource.causePlayerDamage(player), 12);
						
						e.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 10 * 20, 1, true, true));
					}
					
					ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_KOKUTEICROSS, player), player);
					
					if (player.world instanceof ServerWorld)
						((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf(player, new SAnimateHandPacket(player, 0));
				}
				super.use(player);
			}
			else
				WyHelper.sendMsgToPlayer(player, "You need a sword to use this ability !");
		}
	}
	
	public static class Yakkodori extends Ability
	{
		public Yakkodori() 
		{
			super(ModAttributes.YAKKODORI); 
		}
			
		@Override
		public void use(PlayerEntity player)
		{
			if((player.getHeldItemMainhand() != null && ItemsHelper.isSword(player.getHeldItemMainhand())) || DevilFruitsHelper.canUseSwordsmanAbilities(player))
			{
				this.projectile = new SwordsmanProjectiles.Yakkodori(player.world, player, ModAttributes.YAKKODORI);
				if(!this.isOnCooldown)
					if (player.world instanceof ServerWorld)
						((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf(player, new SAnimateHandPacket(player, 0));
				super.use(player);
			}
			else
				WyHelper.sendMsgToPlayer(player, "You need a sword to use this ability !");
		}
	}
	
	public static class ShiShishiSonson extends Ability
	{
		public ShiShishiSonson() 
		{
			super(ModAttributes.SHI_SHISHI_SONSON); 
		}
			
		@Override
		public void use(PlayerEntity player)
		{
			if((player.getHeldItemMainhand() != null && ItemsHelper.isSword(player.getHeldItemMainhand())) || DevilFruitsHelper.canUseSwordsmanAbilities(player))
			{
				if(!this.isOnCooldown)
				{
					double[] speed = DevilFruitsHelper.propulsion(player, 3, 3);
				
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
			if(currentCooldown > 4 * 20)
			{
				for(LivingEntity e : WyHelper.getEntitiesNear(player, 1.6))
					e.attackEntityFrom(DamageSource.causePlayerDamage(player), 8);
			}
	    }
	}
	
	public static class SanbyakurokujuPoundHo extends Ability
	{
		public SanbyakurokujuPoundHo() 
		{
			super(ModAttributes.SANBYAKUROKUJU_POUND_HO); 
		}
			
		@Override
		public void use(PlayerEntity player)
		{
			if((player.getHeldItemMainhand() != null && ItemsHelper.isSword(player.getHeldItemMainhand())) || DevilFruitsHelper.canUseSwordsmanAbilities(player))
			{
				this.projectile = new SwordsmanProjectiles.SanbyakurokujuPoundHo(player.world, player, ModAttributes.SANBYAKUROKUJU_POUND_HO);
				if(!this.isOnCooldown)
					if (player.world instanceof ServerWorld)
						((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf(player, new SAnimateHandPacket(player, 0));
				super.use(player);
			}
			else
				WyHelper.sendMsgToPlayer(player, "You need a sword to use this ability !");
		}
	}
	
}
