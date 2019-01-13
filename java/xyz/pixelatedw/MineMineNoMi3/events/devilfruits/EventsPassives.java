package xyz.pixelatedw.MineMineNoMi3.events.devilfruits;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityProperties;
import xyz.pixelatedw.MineMineNoMi3.api.math.ISphere;
import xyz.pixelatedw.MineMineNoMi3.api.math.Sphere;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.EntityDoppelman;
import xyz.pixelatedw.MineMineNoMi3.helpers.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.items.ItemCoreArmor;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;

public class EventsPassives
{

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityLivingBase)
		{
			EntityLivingBase entity = (EntityLivingBase) event.entityLiving;
			ExtendedEntityData propz = ExtendedEntityData.get(entity);

			if (!entity.worldObj.isRemote)
			{
				if (propz.isCandleLocked() && !entity.isPotionActive(Potion.moveSlowdown.id))
				{
					propz.setIsCandleLocked(false);
					WyNetworkHelper.sendToAll(new PacketSyncInfo(event.entityLiving.getEntityId(), propz));
				}
			}
				
			if (!propz.hasShadow() && entity.getBrightness(1.0F) > 0.8F && !entity.canRenderOnFire())
				entity.setFire(3);
		}

		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			ExtendedEntityData props = ExtendedEntityData.get(player);
			AbilityProperties abilityProps = AbilityProperties.get(player);
			ItemStack heldItem = player.getHeldItem();

			if (props.getUsedFruit().equals("hiehie"))
			{
				if (DevilFruitsHelper.isNearbyKairoseki(player) && (player.getHealth() > player.getMaxHealth() / 5 || player.capabilities.isCreativeMode))
				{
					final EntityLivingBase finalPlayer = player;
					for (int x1 = -1; x1 < 2; x1++)
						for (int y1 = -1; y1 < 0; y1++)
							for (int z1 = -1; z1 < 2; z1++)
							{
								Sphere.generate((int) player.posX - 1 + x1, (int) player.posY + y1, (int) player.posZ + z1, 1, new ISphere()
								{
									public void call(int x, int y, int z)
									{
										if (finalPlayer.worldObj.getBlock(x, y, z) == Blocks.water)
											finalPlayer.worldObj.setBlock(x, y, z, Blocks.ice);
									}
								});
							}
				}
			}

			if (props.getUsedFruit().equals("gomugomu") || props.getUsedFruit().equals("banebane") || props.isLogia() || props.getUsedFruit().equals("kilokilo"))
			{
				player.fallDistance = 0;
			}

			if (props.getUsedFruit().equals("dokudoku"))
			{
				if (player.isPotionActive(Potion.poison.id))
					player.removePotionEffect(Potion.poison.id);
			}

			if ((player.isInsideOfMaterial(Material.water) || (player.isWet() && (player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) == Blocks.water || player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) == Blocks.flowing_water) && !player.isRiding())))
			{
				if (props.isFishman() && props.getUsedFruit().equals("N/A"))
				{
					player.setAir(300);
					player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 300, 1));

					if ((player.motionX >= 5.0D) || (player.motionZ >= 5.0D))
					{
						player.motionX /= 1.2D;
						player.motionZ /= 1.2D;
					}
					else
					{
						player.motionX *= 1.2D;
						player.motionZ *= 1.2D;
					}
				}
			}

			if (player.isInsideOfMaterial(Material.lava) && !player.capabilities.isCreativeMode)
			{
				if (props.getUsedFruit().equals("magumagu"))
				{
					if ((player.motionX >= 5.0D) || (player.motionZ >= 5.0D))
					{
						player.motionX /= 1.9D;
						player.motionZ /= 1.9D;
					}
					else
					{
						player.motionX *= 1.9D;
						player.motionZ *= 1.9D;
					}
				}
			}

			boolean hasColaBackpack = false;

			for (ItemStack armorStack : player.inventory.armorInventory)
			{
				if (armorStack != null && armorStack.getItem() instanceof ItemCoreArmor && ((ItemCoreArmor) armorStack.getItem()).getName().equals("colabackpack"))
				{
					hasColaBackpack = true;
				}
			}

			if (props.isCyborg())
			{
				if (hasColaBackpack && !props.hasColaBackpack())
				{
					props.setMaxCola(props.getMaxCola() + 200);
					props.setColaBackpack(true);

					if (!ID.DEV_EARLYACCESS && !player.capabilities.isCreativeMode && !player.worldObj.isRemote)
						WyTelemetry.addStat("colaBackpacksCurrentlyEquipped", 1);

					if (!player.worldObj.isRemote)
						WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				}
				else if (!hasColaBackpack && props.hasColaBackpack())
				{
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
			else
			{
				if (props.getHakiTimer() > 0)
					props.decHakiTimer();
			}

			if (props.getHakiTimer() > 2400)
			{
				player.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 0));
				player.addPotionEffect(new PotionEffect(Potion.weakness.id, 100, 0));
				if (props.getHakiTimer() > 3600 + (props.getDoriki() / 15))
				{
					player.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 5));
					player.addPotionEffect(new PotionEffect(Potion.weakness.id, 100, 5));
					player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 5));
					player.addPotionEffect(new PotionEffect(Potion.blindness.id, 100, 5));
					// player.attackEntityFrom(DamageSource.generic,
					// Integer.MAX_VALUE);
				}
			}

			if (props.getTempPreviousAbility().equals("geppo") || props.getTempPreviousAbility().equals("soranomichi"))
			{
				if (!player.onGround && player.worldObj.getBlock((int) player.posX, (int) player.posY - 1, (int) player.posZ) == Blocks.air)
					player.fallDistance = 0;
				else
				{
					props.setTempPreviousAbility("n/a");
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityAttack(LivingHurtEvent event)
	{
		if (event.source.getSourceOfDamage() instanceof EntityPlayer)
		{
			EntityPlayer attacker = (EntityPlayer) event.source.getSourceOfDamage();
			ExtendedEntityData props = ExtendedEntityData.get(attacker);
			EntityLivingBase attacked = event.entityLiving;
			
			if(props.getUsedFruit().equalsIgnoreCase("kilokilo"))
			{
				if(attacker.isPotionActive(Potion.damageBoost))
				{
					WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_KILO, attacked.posX, attacked.posY, attacked.posZ), attacker.dimension, attacker.posX, attacker.posY, attacker.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
					attacker.removePotionEffect(Potion.damageBoost.id);
				}
			}
			
			if(props.getUsedFruit().equalsIgnoreCase("kagekage"))
			{
				EntityDoppelman doppelman = (EntityDoppelman) WyHelper.getEntitiesNear(attacker, 20, EntityDoppelman.class).stream().findFirst().orElse(null);
				
				if(doppelman != null)
				{
					doppelman.forcedTargets.add(attacked);
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityShootProjectile(ArrowLooseEvent event)
	{
		if (event.entityPlayer != null)
		{
			ExtendedEntityData props = ExtendedEntityData.get(event.entityPlayer);
			AbilityProperties abilityProps = AbilityProperties.get(event.entityPlayer);

			for (int i = 0; i < abilityProps.countAbilitiesInHotbar(); i++)
			{
				if (abilityProps.getAbilityFromSlot(i) != null && !abilityProps.getAbilityFromSlot(i).isOnCooldown() && abilityProps.getAbilityFromSlot(i).getAttribute().isPassive() && abilityProps.getAbilityFromSlot(i).isPassiveActive() && DevilFruitsHelper.isSniperAbility(abilityProps.getAbilityFromSlot(i)))
				{
					abilityProps.getAbilityFromSlot(i).use(event.entityPlayer);
					event.setCanceled(true);
				}
			}
		}
	}

}
