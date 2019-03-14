package xyz.pixelatedw.MineMineNoMi3.events.devilfruits;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.abilities.extra.effects.DFEffectHieSlowness;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityProperties;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.EntityDoppelman;
import xyz.pixelatedw.MineMineNoMi3.events.customevents.YomiTriggerEvent;
import xyz.pixelatedw.MineMineNoMi3.helpers.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.helpers.ItemsHelper;
import xyz.pixelatedw.MineMineNoMi3.items.ItemCoreArmor;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;

public class EventsPassives {

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event) {
		if (event.entityLiving instanceof EntityLivingBase) {
			EntityLivingBase entity = (EntityLivingBase) event.entityLiving;
			ExtendedEntityData propz = ExtendedEntityData.get(entity);

			if (!propz.hasShadow() && entity.getBrightness(1.0F) > 0.8F)
				entity.setFire(3);

			if (propz.isSetChained() && !entity.isPotionActive(Potion.moveSlowdown.id)) {
				propz.setIsChained(false);
				WyNetworkHelper.sendToAll(new PacketSyncInfo(event.entityLiving.getEntityId(), propz));
			}
		}


		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			ExtendedEntityData props = ExtendedEntityData.get(player);
			AbilityProperties abilityProps = AbilityProperties.get(player);
			ItemStack heldItem = player.getHeldItem();

			Ability atomicSpurt = abilityProps.getAbilityFromName(ListAttributes.ATOMICSPURT.getAttributeName());
			if (props.getUsedFruit().equals("supasupa")) {
				if (atomicSpurt != null && atomicSpurt.isPassiveActive()) {
					if (player.onGround) {
						if (Math.abs(player.motionX) < 0.5 || Math.abs(player.motionZ) < 0.5) {
							player.motionX *= 1.6D;
							player.motionZ *= 1.6D;
						}

						if (Math.abs(player.motionX) > 0.15 || Math.abs(player.motionZ) > 0.15) {
							for (EntityLivingBase e : WyHelper.getEntitiesNear(player, 1.6))
								e.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player), 2);
						}
					}
				}
			}


			if (props.getUsedFruit().equals("hiehie")) {
				if (!DevilFruitsHelper.isNearbyKairoseki(player) && (player.getHealth() > player.getMaxHealth() / 5 || player.capabilities.isCreativeMode)) {
					WyHelper.createFilledSphere(player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ, 2, Blocks.ice, "liquids");
				}
			}

			if (props.getUsedFruit().equals("gomugomu") || props.getUsedFruit().equals("banebane") || props.isLogia() || props.getUsedFruit().equals("kilokilo")) {
				player.fallDistance = 0;
			}

			if (props.getUsedFruit().equals("dokudoku")) {
				if (player.isPotionActive(Potion.poison.id))
					player.removePotionEffect(Potion.poison.id);
			}

			if (props.getUsedFruit().equals("yomiyomi") && props.getZoanPoint().equalsIgnoreCase("yomi")) {
				player.getFoodStats().setFoodSaturationLevel(Float.MAX_VALUE);
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 100, 0, true));

				if (player.worldObj.getBlock((int) player.posX, (int) player.boundingBox.minY, (int) player.posZ) == Blocks.water && player.isSprinting()) {
					player.onGround = true;
					if (player.motionY < 0.0D) {
						player.motionY = 0.0D;
						player.fallDistance = 0.0F;
					}
					WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_BAKUMUNCH, player.posX, player.posY - 0.5, player.posZ), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
				}
			}

			if (player.isInsideOfMaterial(Material.lava) && !player.capabilities.isCreativeMode) {
				if (props.getUsedFruit().equals("magumagu")) {
					if ((player.motionX >= 5.0D) || (player.motionZ >= 5.0D)) {
						player.motionX /= 1.9D;
						player.motionZ /= 1.9D;
					} else {
						player.motionX *= 1.9D;
						player.motionZ *= 1.9D;
					}
				}
			}

			if ((player.isInsideOfMaterial(Material.water) || (player.isWet() && (player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) == Blocks.water || player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) == Blocks.flowing_water) && !player.isRiding()))) {
				if (props.isFishman() && props.getUsedFruit().equals("N/A")) {
					player.setAir(300);
					player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 300, 1));

					if ((player.motionX >= 5.0D) || (player.motionZ >= 5.0D)) {
						player.motionX /= 1.2D;
						player.motionZ /= 1.2D;
					} else {
						player.motionX *= 1.2D;
						player.motionZ *= 1.2D;
					}
				}
			}

			boolean hasColaBackpack = false;

			for (ItemStack armorStack : player.inventory.armorInventory) {
				if (armorStack != null && armorStack.getItem() instanceof ItemCoreArmor && ((ItemCoreArmor) armorStack.getItem()).getName().equals("colabackpack")) {
					hasColaBackpack = true;
				}
			}

			if (props.isCyborg()) {
				if (hasColaBackpack && !props.hasColaBackpack()) {
					props.setMaxCola(props.getMaxCola() + 200);
					props.setColaBackpack(true);

					if (!ID.DEV_EARLYACCESS && !player.capabilities.isCreativeMode && !player.worldObj.isRemote)
						WyTelemetry.addStat("colaBackpacksCurrentlyEquipped", 1);

					if (!player.worldObj.isRemote)
						WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				} else if (!hasColaBackpack && props.hasColaBackpack()) {
					props.setMaxCola(props.getMaxCola() - 200);

					if (props.getCola() > props.getMaxCola())
						props.setCola(props.getMaxCola());

					props.setColaBackpack(false);

					if (!ID.DEV_EARLYACCESS && !player.capabilities.isCreativeMode && !player.worldObj.isRemote)
						WyTelemetry.addStat("colaBackpacksCurrentlyEquipped", -1);

					if (!player.worldObj.isRemote)
						WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				}

			}

			if (props.hasHakiActive())
				props.addHakiTimer();
			else {
				if (props.getHakiTimer() > 0)
					props.decHakiTimer();
			}

			if (props.getHakiTimer() > 2400) {
				player.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 0));
				player.addPotionEffect(new PotionEffect(Potion.weakness.id, 100, 0));
				if (props.getHakiTimer() > 3600 + (props.getDoriki() / 15)) {
					player.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 5));
					player.addPotionEffect(new PotionEffect(Potion.weakness.id, 100, 5));
					player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 5));
					player.addPotionEffect(new PotionEffect(Potion.blindness.id, 100, 5));
					// player.attackEntityFrom(DamageSource.generic,
					// Integer.MAX_VALUE);
				}
			}

			if (props.getTempPreviousAbility().equals("geppo") || props.getTempPreviousAbility().equals("soranomichi")) {
				if (!player.onGround && player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) == Blocks.air)
					player.fallDistance = 0;
				else {
					props.setTempPreviousAbility("n/a");
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityAttack(LivingHurtEvent event) {
		if (event.source.getSourceOfDamage() instanceof EntityLivingBase && event.entityLiving instanceof EntityPlayer) {
			EntityLivingBase attacker = (EntityLivingBase) event.source.getSourceOfDamage();
			EntityPlayer attacked = (EntityPlayer) event.entityLiving;
			ExtendedEntityData props = ExtendedEntityData.get(attacked);
			AbilityProperties abilityProps = AbilityProperties.get(attacked);

			if (props.getUsedFruit().equalsIgnoreCase("yomiyomi") && props.getZoanPoint().equalsIgnoreCase("yomi")) {
				Ability soulParade = abilityProps.getAbilityFromName(ListAttributes.SOULPARADE.getAttributeName());

				if (soulParade != null && soulParade.isPassiveActive()) {
					attacker.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 1));
					attacker.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 100, 1));
					new DFEffectHieSlowness(attacker, 100);
					AbilityExplosion explosion = WyHelper.newExplosion(attacked, attacker.posX, attacker.posY, attacker.posZ, 2);
					explosion.setDamageOwner(false);
					explosion.setDestroyBlocks(false);
					explosion.setSmokeParticles(ID.PARTICLEFX_SOULPARADE);
					explosion.doExplosion();
				}
			}
		}

		if (event.source.getSourceOfDamage() instanceof EntityPlayer) {
			EntityPlayer attacker = (EntityPlayer) event.source.getSourceOfDamage();
			ExtendedEntityData props = ExtendedEntityData.get(attacker);
			AbilityProperties abilityProps = AbilityProperties.get(attacker);
			EntityLivingBase attacked = event.entityLiving;

			if (props.getUsedFruit().equalsIgnoreCase("kilokilo")) {
				if (attacker.isPotionActive(Potion.damageBoost)) {
					WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_KILO, attacked.posX, attacked.posY, attacked.posZ), attacker.dimension, attacker.posX, attacker.posY, attacker.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
					attacker.removePotionEffect(Potion.damageBoost.id);
				}
			}

			if (props.getUsedFruit().equalsIgnoreCase("kagekage")) {
				EntityDoppelman doppelman = (EntityDoppelman) WyHelper.getEntitiesNear(attacker, 20, EntityDoppelman.class).stream().findFirst().orElse(null);

				if (doppelman != null)
					doppelman.forcedTargets.add(attacked);
			}

			if (props.getUsedFruit().equalsIgnoreCase("dokudoku") && props.getZoanPoint().equalsIgnoreCase("venomDemon"))
				attacked.addPotionEffect(new PotionEffect(Potion.poison.id, 60, 0));

			if (props.hasBusoHakiActive()) {
				double power = props.getDoriki() / 500;
				event.ammount += power;
			}
		}
	}

	@SubscribeEvent
	public void onEntityShootProjectile(ArrowLooseEvent event) {
		if (event.entityPlayer != null) {
			ExtendedEntityData props = ExtendedEntityData.get(event.entityPlayer);
			AbilityProperties abilityProps = AbilityProperties.get(event.entityPlayer);

			for (int i = 0; i < abilityProps.countAbilitiesInHotbar(); i++) {
				if (abilityProps.getAbilityFromSlot(i) != null && !abilityProps.getAbilityFromSlot(i).isOnCooldown() && abilityProps.getAbilityFromSlot(i).getAttribute().isPassive() && abilityProps.getAbilityFromSlot(i).isPassiveActive() && DevilFruitsHelper.isSniperAbility(abilityProps.getAbilityFromSlot(i))) {
					abilityProps.getAbilityFromSlot(i).use(event.entityPlayer);
					event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public void onYomiDeath(YomiTriggerEvent event) {
		if (event.oldPlayerData.getUsedFruit().equalsIgnoreCase("yomiyomi") && !event.oldPlayerData.getZoanPoint().equalsIgnoreCase("yomi")) {
			event.newPlayerData.setUsedFruit("yomiyomi");
			event.newPlayerData.setZoanPoint("yomi");
		}
	}

	@SubscribeEvent
	public void onRenderPlayerEvent(RenderPlayerEvent.Pre event) {
		ExtendedEntityData propz = ExtendedEntityData.get(event.entityPlayer);
		if (propz.isInAirWorld()) {
			event.setCanceled(true);

		}

	}

	@SubscribeEvent
	public void onPlayerAction(PlayerInteractEvent event) {
		ExtendedEntityData propz = ExtendedEntityData.get(event.entityPlayer);
		if (propz.isInAirWorld()) {
			event.setCanceled(true);
		}

	}

	@SubscribeEvent
	public void onAttack(AttackEntityEvent event) {
		ExtendedEntityData propz = ExtendedEntityData.get(event.entityPlayer);
		if (propz.isInAirWorld()) {
			event.setCanceled(true);
		}
		if (propz.getUsedFruit().equalsIgnoreCase("kachikachi")) {
			AbilityProperties abilityProps = AbilityProperties.get(event.entityPlayer);
			Ability fireFist = abilityProps.getAbilityFromName(ListAttributes.HOTBOILINGSPECIAL.getAttributeName());
			if(fireFist != null && fireFist.isPassiveActive()) {
				event.target.setFire(4);
			}
		}


	}

	@SubscribeEvent
	public void onDamage(LivingAttackEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			ExtendedEntityData props = ExtendedEntityData.get((EntityPlayer) event.entity);
			if (props.isInAirWorld()) {
				event.setCanceled(true);
			}

			if (event.source.getSourceOfDamage() instanceof EntityPlayer) {
				if (event.entityLiving instanceof EntityPlayer) {
					EntityPlayer attacker = (EntityPlayer) event.source.getSourceOfDamage();
					EntityPlayer reciever = (EntityPlayer) event.entityLiving;
					ExtendedEntityData propz = ExtendedEntityData.get(reciever);
					if (attacker.getHeldItem() != null && propz.getUsedFruit().equals("sabisabi")) {
						if (ItemsHelper.isSword(attacker.getHeldItem())) {
							event.setCanceled(true);
							attacker.getHeldItem().damageItem(50, attacker);
						}
					}

				}
			}


		}
	}
}
