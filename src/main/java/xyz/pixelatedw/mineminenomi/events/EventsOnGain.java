package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.abilities.CyborgAbilities;
import xyz.pixelatedw.mineminenomi.abilities.FishKarateAbilities;
import xyz.pixelatedw.mineminenomi.abilities.HakiAbilities;
import xyz.pixelatedw.mineminenomi.abilities.HakiAbilities.BusoshokuHaki;
import xyz.pixelatedw.mineminenomi.abilities.HakiAbilities.KenbunshokuHaki;
import xyz.pixelatedw.mineminenomi.abilities.RokushikiAbilities;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.api.telemetry.WyTelemetry;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.events.custom.BountyEvent;
import xyz.pixelatedw.mineminenomi.events.custom.DorikiEvent;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketEntityStatsSync;

public class EventsOnGain
{

	@SubscribeEvent
	public void onDorikiGained(DorikiEvent event)
	{
		if (event.props.isHuman())
		{
			gainAbility(event.player, 500, RokushikiAbilities.SORU);
			gainAbility(event.player, 1500, RokushikiAbilities.TEKKAI);
			gainAbility(event.player, 3000, RokushikiAbilities.SHIGAN);
			gainAbility(event.player, 4500, RokushikiAbilities.GEPPO);
			gainAbility(event.player, 5000, HakiAbilities.KENBUNSHOKU_HAKI);
			gainAbility(event.player, 6000, RokushikiAbilities.KAMIE);
			gainAbility(event.player, 8500, RokushikiAbilities.RANKYAKU);
			gainAbility(event.player, 9000, HakiAbilities.BUSOSHOKU_HAKI);
			// HAOSHOKU - 9000 + other
		}
		else if (event.props.isFishman())
		{
			gainAbility(event.player, 800, FishKarateAbilities.UCHIMIZU);
			gainAbility(event.player, 2000, FishKarateAbilities.MURASAME);
			gainAbility(event.player, 2500, FishKarateAbilities.KACHIAGE_HAISOKU);
			gainAbility(event.player, 3000, FishKarateAbilities.SAMEHADA_SHOTEI);
			gainAbility(event.player, 4000, HakiAbilities.KENBUNSHOKU_HAKI);
			gainAbility(event.player, 7500, FishKarateAbilities.KARAKUSAGAWARA_SEIKEN);
			gainAbility(event.player, 9000, HakiAbilities.BUSOSHOKU_HAKI);
		}
		else if (event.props.isCyborg())
		{
			gainAbility(event.player, 0, CyborgAbilities.FRESH_FIRE);
			gainAbility(event.player, 0, CyborgAbilities.COLA_OVERDRIVE);
			gainAbility(event.player, 0, CyborgAbilities.STRONG_RIGHT);
			gainAbility(event.player, 0, CyborgAbilities.RADICAL_BEAM);
			gainAbility(event.player, 0, CyborgAbilities.COUP_DE_VENT);
			gainAbility(event.player, 5500, HakiAbilities.KENBUNSHOKU_HAKI);
			gainAbility(event.player, 8500, HakiAbilities.BUSOSHOKU_HAKI);
		}

		if (event.player != null && CommonConfig.instance.getExtraHearts())
		{
			IAttributeInstance maxHp = event.player.getAttribute(SharedMonsterAttributes.MAX_HEALTH);

			if (event.props.getDoriki() / 100 <= 20)
				event.player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
			else
				event.player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(event.props.getDoriki() / 100);
		}
	}

	private void gainAbility(PlayerEntity player, int doriki, Ability ability)
	{
		IEntityStats props = EntityStatsCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);

		if (ability instanceof KenbunshokuHaki || ability instanceof BusoshokuHaki)
		{
			if (props.getDoriki() >= doriki && !abilityProps.hasHakiAbility(ability) && !DevilFruitsHelper.verifyIfAbilityIsBanned(ability))
				abilityProps.addHakiAbility(ability);
			if ((props.getDoriki() < doriki || DevilFruitsHelper.verifyIfAbilityIsBanned(ability)) && abilityProps.hasHakiAbility(ability))
				abilityProps.removeHakiAbility(ability);
		}
		else
		{
			if (props.getDoriki() >= doriki && !abilityProps.hasRacialAbility(ability) && !DevilFruitsHelper.verifyIfAbilityIsBanned(ability))
				abilityProps.addRacialAbility(ability);
			if ((props.getDoriki() < doriki || DevilFruitsHelper.verifyIfAbilityIsBanned(ability)) && abilityProps.hasRacialAbility(ability))
				abilityProps.removeRacialAbility(ability);
		}
	}

	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event)
	{
		if (event.getEntity() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntity();
			IEntityStats props = EntityStatsCapability.get(player);
			IAbilityData abilityProps = AbilityDataCapability.get(player);

			for (int i = 0; i < 8; i++)
			{
				if (abilityProps.getHotbarAbilityFromSlot(i) != null)
					abilityProps.getHotbarAbilityFromSlot(i).reset();
			}

			ModNetwork.sendTo(new PacketEntityStatsSync(props), (ServerPlayerEntity) player);
		}

		if (event.getSource().getTrueSource() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
			IEntityStats props = EntityStatsCapability.get(player);
			LivingEntity target = event.getEntityLiving();

			IAttributeInstance attrAtk = target.getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);
			IAttributeInstance attrHP = target.getAttributes().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);

			int rng = player.world.rand.nextInt(3) + 1;
			int plusBelly = 0;
			long plusBounty = 0;
			double plusDoriki = 0;

			boolean targetPlayer = false;

			if (target instanceof PlayerEntity)
			{
				IEntityStats targetprops = EntityStatsCapability.get(player);

				plusDoriki = (targetprops.getDoriki() / 4) + rng;
				plusBounty = (targetprops.getBounty() / 2) + rng;
				plusBelly = targetprops.getBelly();

				targetPlayer = true;
			}
			else
			{
				if (props.isMarine() && target instanceof MarineData)
					return;

				if (target instanceof EntityNewMob)
				{
					/*EntityNewMob entity = (EntityNewMob) target;

					if ((props.getDoriki() / 100) > entity.getDoriki())
					{
						int x = (props.getDoriki() / 100) - entity.getDoriki();
						if (x <= 0)
							x = 1;

						plusDoriki = 1 / x;
						if (plusDoriki < 1)
							plusDoriki = 1;
					}
					else
						plusDoriki = entity.getDoriki();

					plusDoriki *= MainConfig.modifierDorikiReward;

					plusBounty = (entity.getDoriki() * 2) + rng;
					plusBelly = entity.getBelly() + rng;*/

					if (!player.world.isRemote && !player.isCreative())
						WyTelemetry.addKillStat(WyHelper.getFancyName(target.getClass().getSimpleName()).replace("entity", ""), target.getClass().getSimpleName().replace("Entity", ""), 1);
				}
				else
				{
					if (attrAtk != null && attrHP != null)
					{
						double i = attrAtk.getBaseValue();
						double j = attrHP.getBaseValue();

						plusDoriki = (int) Math.round(((i + j) / 10) / Math.PI) + rng;
						plusBounty = (int) Math.round((i + j) / 10) + rng;
						plusBelly = 1;

						plusDoriki *= CommonConfig.instance.getDorikiRewardMultiplier();

					}
					else
					{
						plusDoriki = 0;
						plusBounty = 0;
						plusBelly = 1;
					}
				}

				if (plusDoriki > 0)
				{
					if (props.getDoriki() + plusDoriki <= Values.MAX_DORIKI)
					{
						props.alterDoriki((int) Math.round(plusDoriki));
						DorikiEvent e = new DorikiEvent(player);
						if (MinecraftForge.EVENT_BUS.post(e))
							return;
					}
				}

				if (props.isPirate())
					if (plusBounty > 0)
						if (props.getBounty() + plusBounty < Values.MAX_GENERAL)
						{
							props.alterBounty(plusBounty);
							BountyEvent e = new BountyEvent(player, plusBounty);
							if (MinecraftForge.EVENT_BUS.post(e))
								return;
						}

				if (props.getBelly() + plusBelly < Values.MAX_GENERAL)
					props.alterBelly(plusBelly);

			}

			ModNetwork.sendTo(new PacketEntityStatsSync(props), (ServerPlayerEntity) player);
		}
	}

}
