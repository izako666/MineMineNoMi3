package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.abilities.effects.DFEffect;
import xyz.pixelatedw.mineminenomi.abilities.effects.DFEffectZushiAbareHimatsuri;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.JuryoProjectiles;
import xyz.pixelatedw.mineminenomi.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class JuryoAbilities
{
	public static Ability[] abilitiesArray = new Ability[] {new SagariNoRyusei(), new Juryoku(), new Moko(), new AbareHimatsuri()};
	
	public static class AbareHimatsuri extends Ability
	{
		private DFEffect extra = null;
		
		public AbareHimatsuri() 
		{
			super(ModAttributes.ABARE_HIMATSURI); 
		}

		@Override
		public void passive(PlayerEntity player) 
		{
			if(!player.abilities.isFlying && player.onGround)
			{
				if(extra == null)
					extra = new DFEffectZushiAbareHimatsuri(player, 99999);
				else
				{
					extra.forceStop();
					extra = null;
				}
				super.passive(player);
			}
			else if(!player.onGround)
			{
				if(extra != null)
				{
					extra.forceStop();
					extra = null;
				}
				super.passive(player);
			}
		}
		
		@Override
		public void startPassive(PlayerEntity player)
		{
			
		}
		
		@Override
		public void endPassive(PlayerEntity player)
		{
			
		}
	}
	
	public static class Moko extends Ability
	{
		public Moko() 
		{
			super(ModAttributes.MOKO); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			if(!this.isOnCooldown())
			{
				if(ItemsHelper.isSword(player.getHeldItemMainhand()))
				{	
					for(int j = 0; j < 50; j++)
					{
						AbilityProjectile proj = new JuryoProjectiles.Moko(player.world, player, ModAttributes.MOKO);
	
						proj.setLocationAndAngles(
								player.posX + WyMathHelper.randomWithRange(-5, 5) + player.world.rand.nextDouble(), 
								(player.posY + 0.3) + WyMathHelper.randomWithRange(0, 5) + player.world.rand.nextDouble(), 
								player.posZ + WyMathHelper.randomWithRange(-5, 5) + player.world.rand.nextDouble(), 
								0, 0);
						player.world.addEntity(proj);
					}
					if (player.world instanceof ServerWorld)
						((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf(player, new SAnimateHandPacket(player, 0));
					super.use(player);
				}
				else
					WyHelper.sendMsgToPlayer(player, "You need a sword to use this ability !");
			}
		}
	}
	
	public static class Juryoku extends Ability
	{
		public Juryoku() 
		{
			super(ModAttributes.JURYOKU); 
		}

		@Override
		public void duringPassive(PlayerEntity player, int passiveTimer) 
		{
			if(passiveTimer > 400)
			{
				this.setPassiveActive(false);
				this.startCooldown();
				this.startExtUpdate(player);
			}
			
			for(LivingEntity entity : WyHelper.getEntitiesNear(player, 10))
			{
				entity.setMotion(0, entity.getMotion().y - 5, 0);
				entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 10));
				
				if(++passiveTimer % 100 == 0)
				{
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 8);	
					if(CommonConfig.instance.getGriefing())
					{
						for(int x = -2; x < 2; x++)
						for(int z = -2; z < 2; z++)
						{
							int posX = (int)entity.posX + x;
							int posY = (int)entity.posY - 1;
							int posZ = (int)entity.posZ + z;
							
							WyHelper.placeBlockIfAllowed(player.world, posX, posY, posZ, Blocks.AIR, "all", "restricted", "ignore liquid");
						}
					}
				}
			}	
		}
		
		@Override
		public void endPassive(PlayerEntity player) 
		{
			this.startCooldown();
			this.startExtUpdate(player);
		}

	}
	
	public static class SagariNoRyusei extends Ability
	{
		public SagariNoRyusei() 
		{
			super(ModAttributes.SAGARI_NO_RYUSEI); 
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

					AbilityProjectile proj = new JuryoProjectiles.SagariNoRyusei(player.world, player, ModAttributes.SAGARI_NO_RYUSEI);	
					proj.setLocationAndAngles(x, (y + 90), z, 0, 0);
					proj.setMotion(0, -2.4, 0);
					player.world.addEntity(proj);
				}
			}
			super.use(player);
		}
	}
}
