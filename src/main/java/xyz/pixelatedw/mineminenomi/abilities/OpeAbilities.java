package xyz.pixelatedw.mineminenomi.abilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.OpeProjectiles;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModMiscBlocks;
import xyz.pixelatedw.mineminenomi.init.ModMiscItems;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.items.ItemHeart;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class OpeAbilities
{

	static
	{
		Values.abilityWebAppExtraParams.put("room", new String[] {"desc", "Creates a spherical space around the user, in which they can manipulate anything with other skills."});
		Values.abilityWebAppExtraParams.put("countershock", new String[] {"desc", "Releases a strong electrical current, which shocks the opponent."});
		Values.abilityWebAppExtraParams.put("mes", new String[] {"desc", "Removes the heart of the user's target, which they can then damage to hurt the opponent."});
		Values.abilityWebAppExtraParams.put("gammaknife", new String[] {"desc", "Creates a blade of gamma radiation, which massively damages the opponent's organs"});
		Values.abilityWebAppExtraParams.put("shambles", new String[] {"desc", "The user swaps place with the closest entity within the ROOM."});
		Values.abilityWebAppExtraParams.put("takt", new String[] {"desc", "Lifts all entities inside ROOM, making them unable to move."});	
		Values.abilityWebAppExtraParams.put("injectionshot", new String[] {"desc", "While holding a weapon, the user charges at the enemy, leaving them poisoned and confused."});	
	}
	
	public static Ability[] abilitiesArray = new Ability[]
	{
			new Room(), new Mes(), new CounterShock(), new GammaKnife(), new Takt(), new Shambles(), new InjectionShot()
	};

	public static class InjectionShot extends Ability
	{
		public InjectionShot()
		{
			super(ModAttributes.INJECTION_SHOT);
		}

		@Override
		public void use(PlayerEntity player)
		{
			if (DevilFruitsHelper.isEntityInRoom(player))
			{
				if (ItemsHelper.isSword(player.getHeldItemMainhand()))
				{
					if (!this.isOnCooldown)
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
			else
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used inside ROOM !");
		}

		@Override
		public void duringCooldown(PlayerEntity player, int currentCooldown)
		{
			if (currentCooldown > 13 * 20)
			{
				for (LivingEntity e : WyHelper.getEntitiesNear(player, 1.6))
				{
					e.attackEntityFrom(DamageSource.causePlayerDamage(player), 20);

					e.addPotionEffect(new EffectInstance(Effects.POISON, 10 * 20, 0));
					e.addPotionEffect(new EffectInstance(Effects.NAUSEA, 10 * 20, 0));
				}
			}
		}
	}

	public static class Takt extends Ability
	{
		private HashMap<LivingEntity, Double> entitiesInRoom = new HashMap<LivingEntity, Double>();

		public Takt()
		{
			super(ModAttributes.TAKT);
		}

		@Override
		public void passive(PlayerEntity player)
		{
			if (!this.isPassiveActive())
			{
				if (DevilFruitsHelper.isEntityInRoom(player))
				{
					for (LivingEntity entity : WyHelper.getEntitiesNear(player, 40))
					{
						if (DevilFruitsHelper.isEntityInRoom(entity) && !entity.equals(player))
						{
							entitiesInRoom.put(entity, entity.posY + 3);
						}
					}
					super.passive(player);
				}
				else
					WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used inside ROOM !");
			}
			else
			{
				super.passive(player);
			}
		}

		@Override
		public void duringPassive(PlayerEntity player, int passiveTimer)
		{
			if (!DevilFruitsHelper.isEntityInRoom(player))
			{
				this.setPassiveActive(false);
				this.setCooldownActive(true);
				this.endPassive(player);
			}

			if (passiveTimer >= 160)
			{
				this.setPassiveActive(false);
				this.setCooldownActive(true);
				this.endPassive(player);
			}

			Iterator it = entitiesInRoom.entrySet().iterator();
			while (it.hasNext())
			{
				Map.Entry<LivingEntity, Double> pair = (Map.Entry)it.next();
				pair.getKey().setPositionAndUpdate(pair.getKey().posX, pair.getValue(), pair.getKey().posZ);
				pair.getKey().fallDistance = 0;
			}
		}

		@Override
		public void endPassive(PlayerEntity player)
		{
			this.startCooldown();
			this.startExtUpdate(player);
			this.entitiesInRoom.clear();
		}
	}

	public static class Shambles extends Ability
	{
		public Shambles()
		{
			super(ModAttributes.SHAMBLES);
		}

		@Override
		public void use(PlayerEntity player)
		{
			if (DevilFruitsHelper.isEntityInRoom(player))
			{
				if (!this.isOnCooldown)
				{
					RayTraceResult mop = WyHelper.rayTraceBlocks(player);	
					
					if(mop != null)
					{
						double i = mop.getHitVec().x;
						double j = mop.getHitVec().y;
						double k = mop.getHitVec().z;
						
						List<LivingEntity> entityList = WyHelper.getEntitiesNear(new BlockPos(i, j, k), player.world, 4, LivingEntity.class);
						
						if(entityList.size() <= 0) return;
						
						LivingEntity target = entityList.get(0);
						
						double[] beforeCoords = new double[] { player.posX, player.posY, player.posZ };
						player.setPositionAndRotation(target.posX, target.posY, target.posZ, target.rotationYaw, target.rotationPitch);
						player.setPositionAndUpdate(target.posX, target.posY, target.posZ);
						target.setPositionAndUpdate(beforeCoords[0],beforeCoords[1],beforeCoords[2]);
						
					}
				}
				super.use(player);
			}
			else
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used inside ROOM !");
		}
	}

	public static class GammaKnife extends Ability
	{
		public GammaKnife()
		{
			super(ModAttributes.GAMMA_KNIFE);
		}

		@Override
		public void use(PlayerEntity player)
		{
			if (DevilFruitsHelper.isEntityInRoom(player))
			{
				this.projectile = new OpeProjectiles.GammaKnife(player.world, player, attr);
				super.use(player);
			}
			else
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used inside ROOM !");
		}
	}

	public static class Mes extends Ability
	{
		public Mes()
		{
			super(ModAttributes.MES);
		}

		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target)
		{
			IEntityStats targetProps = EntityStatsCapability.get(target);
			
			if (targetProps.hasHeart())
			{
				ItemStack heart = new ItemStack(ModMiscItems.heart);
				((ItemHeart) heart.getItem()).setHeartOwner(heart, target);
				heart.setDisplayName(new StringTextComponent(target.getCommandSource().getName() + "'s Heart"));

				player.inventory.addItemStackToInventory(heart);

				targetProps.setHeart(false);
			}

			super.hitEntity(player, target);
		}
	}

	public static class CounterShock extends Ability
	{
		public CounterShock()
		{
			super(ModAttributes.COUNTER_SHOCK);
		}

		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target)
		{
			super.hitEntity(player, target);
			ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_ELTHOR, target.posX, target.posY, target.posZ), (ServerPlayerEntity) player);
		}
	}


	public static class Room extends Ability
	{
		private List<int[]> blockList = new ArrayList<int[]>();

		public Room()
		{
			super(ModAttributes.ROOM);
		}

		@Override
		public void passive(PlayerEntity player)
		{
			if(!this.isOnCooldown)
			{
				if(this.blockList.isEmpty())
				{
					this.blockList.addAll(WyHelper.createEmptySphere(player.world, (int)player.posX, (int)player.posY, (int)player.posZ, 20, ModMiscBlocks.ope, "air", "foliage", "liquids", "nogrief"));
					player.world.setBlockState(new BlockPos(player.posX, player.posY, player.posZ), ModMiscBlocks.opeMid.getDefaultState());
					this.blockList.add(new int[] {(int) player.posX, (int) player.posY, (int) player.posZ});
				}
				
				super.passive(player);
			}
		}
		
		@Override
		public void endPassive(PlayerEntity player) 
		{
			for(int[] blockPos : this.blockList)
			{
				if(player.world.getBlockState(new BlockPos(blockPos[0], blockPos[1], blockPos[2])) == ModMiscBlocks.ope.getDefaultState() || player.world.getBlockState(new BlockPos(blockPos[0], blockPos[1], blockPos[2])) == ModMiscBlocks.opeMid.getDefaultState())
					player.world.setBlockState(new BlockPos(blockPos[0], blockPos[1], blockPos[2]), Blocks.AIR.getDefaultState());
			}
            this.blockList = new ArrayList<int[]>();
            this.startCooldown();
            this.startExtUpdate(player);
		}
	}
}
