package xyz.pixelatedw.mineminenomi.events.devilfruits;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.abilities.RokushikiAbilities;
import xyz.pixelatedw.mineminenomi.abilities.effects.DFEffectHieSlowness;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.EntityDoppelman;
import xyz.pixelatedw.mineminenomi.events.custom.YomiTriggerEvent;
import xyz.pixelatedw.mineminenomi.helpers.CombatHelper;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class EventsPassives
{
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof LivingEntity)
		{
			LivingEntity entity = event.getEntityLiving();
			IEntityStats propz = EntityStatsCapability.get(entity);

			if (!propz.hasShadow() && entity.getBrightness() > 0.8F)
				entity.setFire(3);
		}

		if (event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
			IEntityStats statProps = EntityStatsCapability.get(player);
			IAbilityData abilityProps = AbilityDataCapability.get(player);
			ItemStack heldItem = player.getHeldItemMainhand();

			Ability atomicSpurt = abilityProps.getHotbarAbilityFromName(ModAttributes.ATOMIC_SPURT.getAttributeName());
			if (devilFruitProps.getDevilFruit().equals("supasupa"))
			{
				if (atomicSpurt != null && atomicSpurt.isPassiveActive())
				{
					if (player.onGround)
					{
						if (Math.abs(player.getMotion().x) < 0.5 || Math.abs(player.getMotion().z) < 0.5)
							player.setMotion(player.getMotion().x * 1.6, player.getMotion().y, player.getMotion().z * 1.6);

						if (Math.abs(player.getMotion().x) > 0.15 || Math.abs(player.getMotion().z) > 0.15)
						{
							for (LivingEntity e : WyHelper.getEntitiesNear(player, 1.6))
								e.attackEntityFrom(DamageSource.causePlayerDamage(player), 2);
						}
					}
				}
			}

			if (devilFruitProps.getDevilFruit().equals("hiehie"))
			{
				if (!DevilFruitsHelper.isNearbyKairoseki(player) && (player.getHealth() > player.getMaxHealth() / 5 || player.abilities.isCreativeMode))
				{
					WyHelper.createFilledSphere(player.world, (int) player.posX - 1, (int) player.posY, (int) player.posZ - 1, 2, Blocks.ICE, "liquids");
				}
			}

			if (devilFruitProps.getDevilFruit().equals("gomugomu") || devilFruitProps.getDevilFruit().equals("banebane") || devilFruitProps.isLogia() || devilFruitProps.getDevilFruit().equals("kilokilo"))
			{
				player.fallDistance = 0;
			}

			if (devilFruitProps.getDevilFruit().equals("dokudoku"))
			{
				if (player.isPotionActive(Effects.POISON))
					player.removePotionEffect(Effects.POISON);
			}

			if (devilFruitProps.getDevilFruit().equals("yomiyomi") && devilFruitProps.getZoanPoint().equalsIgnoreCase("yomi"))
			{
				player.getFoodStats().addStats(9999, 9999);

				player.addPotionEffect(new EffectInstance(Effects.SPEED, 100, 0, true, true));

				if (player.world.getBlockState(new BlockPos(player.posX, player.getBoundingBox().minY, (int) player.posZ)) == Blocks.WATER.getDefaultState() && player.isSprinting())
				{
					player.onGround = true;
					if (player.getMotion().y < 0.0D)
					{
						player.setMotion(player.getMotion().x, 0, player.getMotion().z);
						player.fallDistance = 0.0F;
					}				
				}

				//if (WyHelper.getEntitiesNear(player, 100, PlayerEntity.class).size() > 0 && player.ticksExisted % 500 == 0)
				//	ModNetwork.sendToAll(new PacketDevilFruitSync(devilFruitProps));
			}

			if (player.isInLava() && !player.isCreative())
			{
				if (devilFruitProps.getDevilFruit().equals("magumagu"))
				{
					if ((player.getMotion().x >= 5.0D) || (player.getMotion().z >= 5.0D))
						player.setMotion(player.getMotion().x / 1.9, player.getMotion().y, player.getMotion().z / 1.9);
					else
						player.setMotion(player.getMotion().x * 1.9, player.getMotion().y, player.getMotion().z * 1.9);
				}
			}

			if (DevilFruitsHelper.isAffectedByWater(player))
			{
				if (statProps.isFishman() && devilFruitProps.getDevilFruit().equalsIgnoreCase("n/a"))
				{
					player.setAir(300);
					player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 300, 1));

					if ((player.getMotion().x >= 5.0D) || (player.getMotion().z >= 5.0D))
						player.setMotion(player.getMotion().x / 1.2, player.getMotion().y, player.getMotion().z / 1.2);
					else
						player.setMotion(player.getMotion().x * 1.2, player.getMotion().y, player.getMotion().z * 1.2);
				}
			}

			/*boolean hasColaBackpack = false;

			for (ItemStack armorStack : player.inventory.armorInventory)
			{
				if (armorStack != null && armorStack.getItem() instanceof ItemCoreArmor && ((ItemCoreArmor) armorStack.getItem()).getName().equals("colabackpack"))
				{
					hasColaBackpack = true;
				}
			}

			if (statProps.isCyborg())
			{
				if (hasColaBackpack)// && !props.hasColaBackpack())
				{
					props.setMaxCola(props.getMaxCola() + 400);
					props.setColaBackpack(true);

					if (!ID.DEV_EARLYACCESS && !player.isCreative() && !player.world.isRemote)
						WyTelemetry.addStat("colaBackpacksCurrentlyEquipped", 1);

					if (!player.world.isRemote)
						ModNetwork.sendTo(new PacketSync(props), (PlayerEntityMP) player);
				}
				else if (!hasColaBackpack && props.hasColaBackpack())
				{
					props.setMaxCola(props.getMaxCola() - 400);

					if (props.getCola() > props.getMaxCola())
						props.setCola(props.getMaxCola());

					props.setColaBackpack(false);

					if (!ID.DEV_EARLYACCESS && !player.isCreative() && !player.world.isRemote)
						WyTelemetry.addStat("colaBackpacksCurrentlyEquipped", -1);

					if (!player.world.isRemote)
						ModNetwork.sendTo(new PacketSync(props), (PlayerEntityMP) player);
				}
				
				if(hasColaBackpack && !player.world.isRemote)
				{
					if(props.getCola() + 10 <= props.getMaxCola())
					{
						if(player.ticksExisted % 100 == 0)
						{
							props.alterCola(10);
							ModNetwork.sendTo(new PacketSync(props), (PlayerEntityMP) player);
						}
					}
				}
			}*/
			
			if(CombatHelper.isPassiveActive(abilityProps, RokushikiAbilities.TEKKAI.getAttribute()))
				player.setMotion(player.getMotion().x, player.getMotion().y - 5, player.getMotion().z);

			/*if (props.hasHakiActive())
				props.addHakiTimer();
			else
			{
				if (props.getHakiTimer() > 0)
					props.decHakiTimer();
			}

			if (props.getHakiTimer() > 2400)
			{
				player.addPotionEffect(new EffectInstance(Potion.confusion.id, 100, 0));
				player.addPotionEffect(new EffectInstance(Potion.weakness.id, 100, 0));
				if (props.getHakiTimer() > 3600 + (props.getDoriki() / 15))
				{
					player.addPotionEffect(new EffectInstance(Potion.confusion.id, 100, 5));
					player.addPotionEffect(new EffectInstance(Potion.weakness.id, 100, 5));
					player.addPotionEffect(new EffectInstance(Potion.moveSlowdown.id, 100, 5));
					player.addPotionEffect(new EffectInstance(Potion.blindness.id, 100, 5));
					// player.attackEntityFrom(DamageSource.generic,
					// Integer.MAX_VALUE);
				}
			}

			if (props.getTempPreviousAbility().equals("geppo") || props.getTempPreviousAbility().equals("soranomichi"))
			{
				if (!player.onGround && player.world.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) == Blocks.air)
					player.fallDistance = 0;
				else
				{
					props.setTempPreviousAbility("n/a");
				}
			}*/
		}
	}

	@SubscribeEvent
	public void onEntityAttack(LivingHurtEvent event)
	{
		if (event.getSource().getTrueSource() instanceof LivingEntity && event.getEntityLiving() instanceof PlayerEntity)
		{
			LivingEntity attacker = (LivingEntity) event.getSource().getTrueSource();
			PlayerEntity attacked = (PlayerEntity) event.getEntityLiving();
			IDevilFruit devilFruitProps = DevilFruitCapability.get(attacked);
			IEntityStats statProps = EntityStatsCapability.get(attacked);
			IAbilityData abilityProps = AbilityDataCapability.get(attacked);

			if (devilFruitProps.getDevilFruit().equalsIgnoreCase("yomiyomi") && devilFruitProps.getZoanPoint().equalsIgnoreCase("yomi"))
			{
				if (CombatHelper.isPassiveActive(abilityProps, ModAttributes.SOUL_PARADE))
				{				
					attacker.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 1));
					attacker.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 100, 1));
					new DFEffectHieSlowness(attacker, 100);
					AbilityExplosion explosion = WyHelper.newExplosion(attacked, attacker.posX, attacker.posY, attacker.posZ, 2);
					explosion.setDamageOwner(false);
					explosion.setDestroyBlocks(false);
					explosion.setSmokeParticles(ID.PARTICLEFX_SOULPARADE);
					explosion.doExplosion();
				}
			}
		}

		if (event.getSource().getTrueSource() instanceof PlayerEntity)
		{
			PlayerEntity attacker = (PlayerEntity) event.getSource().getTrueSource();
			IDevilFruit devilFruitProps = DevilFruitCapability.get(attacker);
			IEntityStats statProps = EntityStatsCapability.get(attacker);
			IAbilityData abilityProps = AbilityDataCapability.get(attacker);
			LivingEntity attacked = event.getEntityLiving();
			IEntityStats statPropz = EntityStatsCapability.get(attacked);

			if (devilFruitProps.getDevilFruit().equalsIgnoreCase("kilokilo"))
			{
				if (attacker.isPotionActive(Effects.STRENGTH))
				{
					ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_KILO, attacked.posX, attacked.posY, attacked.posZ), attacker);
					attacker.removePotionEffect(Effects.STRENGTH);
				}
			}

			if (devilFruitProps.getDevilFruit().equalsIgnoreCase("kagekage"))
			{
				EntityDoppelman doppelman = (EntityDoppelman) WyHelper.getEntitiesNear(attacker, 20, EntityDoppelman.class).stream().findFirst().orElse(null);

				//if (doppelman != null)
				//	doppelman.forcedTargets.add(attacked);
			}

			if (devilFruitProps.getDevilFruit().equalsIgnoreCase("dokudoku") && devilFruitProps.getZoanPoint().equalsIgnoreCase("venomDemon"))
				attacked.addPotionEffect(new EffectInstance(Effects.POISON, 60, 0));

			if (CombatHelper.isPassiveActive(abilityProps, ModAttributes.BUSOSHOKU_HAKI))
			{
				int powerDifference = statProps.getDoriki() - statPropz.getDoriki();
				float damageFromDoriki = 2;
				if(powerDifference > 0)
					damageFromDoriki = (float) (Math.sqrt(powerDifference) / 2);
				event.setAmount(damageFromDoriki);
			}
		}
	}

	@SubscribeEvent
	public void onEntityShootProjectile(ArrowLooseEvent event)
	{
		if (event.getPlayer() != null)
		{
			PlayerEntity player = event.getPlayer();
			IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
			IEntityStats statProps = EntityStatsCapability.get(player);
			IAbilityData abilityProps = AbilityDataCapability.get(player);

			for (int i = 0; i < abilityProps.countAbilitiesInHotbar(); i++)
			{
				if (abilityProps.getHotbarAbilityFromSlot(i) != null && !abilityProps.getHotbarAbilityFromSlot(i).isOnCooldown() && abilityProps.getHotbarAbilityFromSlot(i).getAttribute().isPassive() && abilityProps.getHotbarAbilityFromSlot(i).isPassiveActive() && DevilFruitsHelper.isSniperAbility(abilityProps.getHotbarAbilityFromSlot(i)))
				{
					abilityProps.getHotbarAbilityFromSlot(i).use(player);
					event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public void onYomiDeath(YomiTriggerEvent event)
	{
		/*if (event.oldPlayerData.getUsedFruit().equalsIgnoreCase("yomiyomi") && !event.oldPlayerData.getZoanPoint().equalsIgnoreCase("yomi"))
		{

			event.newPlayerData.setUsedFruit("yomiyomi");
			event.newPlayerData.setZoanPoint("yomi");

			PlayerEntity player = (PlayerEntity) event.entity;

			ModNetwork.sendTo(new PacketSync(event.newPlayerData), (PlayerEntityMP) player);
			ModNetwork.sendToAll(new PacketSyncInfo(player.getDisplayName(), event.newPlayerData));
		}*/
	}

	@SubscribeEvent
	public void onPlayerAction(PlayerInteractEvent event)
	{
		IAbilityData abilityProps = AbilityDataCapability.get(event.getPlayer());

		if(CombatHelper.isPassiveActive(abilityProps, ModAttributes.AIR_DOOR))
			event.setCanceled(true);
	}

	@SubscribeEvent
	public void onAttack(AttackEntityEvent event)
	{
		IDevilFruit devilFruitProps = DevilFruitCapability.get(event.getPlayer());
		IAbilityData abilityProps = AbilityDataCapability.get(event.getPlayer());

		if(CombatHelper.isPassiveActive(abilityProps, ModAttributes.AIR_DOOR))
			event.setCanceled(true);
		
		if (devilFruitProps.getDevilFruit().equalsIgnoreCase("kachikachi"))
		{			
			if(CombatHelper.isPassiveActive(abilityProps, ModAttributes.HOT_BOILING_SPECIAL))
				event.getTarget().setFire(4);
		}

	}

	@SubscribeEvent
	public void onDamage(LivingAttackEvent event)
	{
		if (event.getEntityLiving() instanceof PlayerEntity)
		{
			IAbilityData abilityProps = AbilityDataCapability.get(event.getEntityLiving());

			if(CombatHelper.isPassiveActive(abilityProps, ModAttributes.AIR_DOOR))
				event.setCanceled(true);

			if (event.getSource().getTrueSource() instanceof LivingEntity)
			{
				if (event.getEntityLiving() instanceof PlayerEntity)
				{
					LivingEntity attacker = (LivingEntity) event.getSource().getTrueSource();
					PlayerEntity reciever = (PlayerEntity) event.getEntityLiving();
					IDevilFruit devilFruitPropz = DevilFruitCapability.get(reciever);

					if (attacker.getHeldItemMainhand() != null && ItemsHelper.isSword(attacker.getHeldItemMainhand()) && devilFruitPropz.getDevilFruit().equals("sabisabi") && !attacker.world.isRemote)
					{
						event.setCanceled(true);
						attacker.getHeldItemMainhand().damageItem(30, attacker, (entity) -> {});
						if (attacker instanceof PlayerEntity && attacker.getHeldItemMainhand().getDamage() <= 0)
							WyHelper.removeStackFromInventory((PlayerEntity) attacker, attacker.getHeldItemMainhand());
						else if(!(attacker instanceof PlayerEntity) && attacker.getHeldItemMainhand().getDamage() <= 0)
							attacker.setItemStackToSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
					}

				}
			}

		}
	}
	
	@SubscribeEvent
	public void onDrinkMilk(LivingEntityUseItemEvent.Finish event)
	{
		if(event.getItem().getItem() == Items.MILK_BUCKET)
		{
			IDevilFruit devilFruitPropz = DevilFruitCapability.get(event.getEntityLiving());

			if(devilFruitPropz.getDevilFruit().equalsIgnoreCase("yomiyomi") && devilFruitPropz.getZoanPoint().equalsIgnoreCase("yomi"))
			{
				event.getEntityLiving().heal(4);
			}
		}
	}
}
