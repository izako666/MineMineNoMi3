package xyz.pixelatedw.MineMineNoMi3.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.abilities.CyborgAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.FishKarateAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.HakiAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.RokushikiAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.HakiAbilities.BusoshokuHaki;
import xyz.pixelatedw.MineMineNoMi3.abilities.HakiAbilities.KenbunshokuHaki;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityProperties;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedWorldData;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.EntityNewMob;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.MarineData;
import xyz.pixelatedw.MineMineNoMi3.events.customevents.BountyEvent;
import xyz.pixelatedw.MineMineNoMi3.events.customevents.DorikiEvent;
import xyz.pixelatedw.MineMineNoMi3.helpers.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;

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
			gainAbility(event.player, 5000, HakiAbilities.KENBUNSHOKUHAKI);
			gainAbility(event.player, 6000, RokushikiAbilities.KAMIE);
			gainAbility(event.player, 8500, RokushikiAbilities.RANKYAKU);
			gainAbility(event.player, 9000, HakiAbilities.BUSOSHOKUHAKI);
			//HAOSHOKU - 9000 + other			
		}
		else if (event.props.isFishman())
		{
			gainAbility(event.player, 800, FishKarateAbilities.UCHIMIZU);
			gainAbility(event.player, 2000, FishKarateAbilities.MURASAME);
			gainAbility(event.player, 2500, FishKarateAbilities.KACHIAGEHAISOKU);
			gainAbility(event.player, 3000, FishKarateAbilities.SAMEHADASHOTEI);
			gainAbility(event.player, 4000, HakiAbilities.KENBUNSHOKUHAKI);
			gainAbility(event.player, 7500, FishKarateAbilities.KARAKUSAGAWARASEIKEN);
			gainAbility(event.player, 9000, HakiAbilities.BUSOSHOKUHAKI);
		}
		else if(event.props.isCyborg())
		{
			gainAbility(event.player, 0, CyborgAbilities.FRESHFIRE);
			gainAbility(event.player, 0, CyborgAbilities.COLAOVERDRIVE);
			gainAbility(event.player, 0, CyborgAbilities.STRONGRIGHT);
			gainAbility(event.player, 0, CyborgAbilities.RADICALBEAM);
			gainAbility(event.player, 0, CyborgAbilities.COUPDEVENT);
			gainAbility(event.player, 5500, HakiAbilities.KENBUNSHOKUHAKI);
			gainAbility(event.player, 8500, HakiAbilities.BUSOSHOKUHAKI);
		}
		
		if(event.player != null && MainConfig.enableExtraHearts)		
		{
			IAttributeInstance maxHp = event.player.getEntityAttribute(SharedMonsterAttributes.maxHealth);
						
			if(event.props.getDoriki() / 100 <= 20)
				event.player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
			else
				event.player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(event.props.getDoriki() / 100);
		}
	}	

	private void gainAbility(EntityPlayer player, int doriki, Ability ability)
	{
		ExtendedEntityData props = ExtendedEntityData.get(player);
		AbilityProperties abilityProps = AbilityProperties.get(player);

		if(ability instanceof KenbunshokuHaki || ability instanceof BusoshokuHaki)
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
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			ExtendedEntityData props = ExtendedEntityData.get(player);
			AbilityProperties abilityProps = AbilityProperties.get(player);

			for (int i = 0; i < 8; i++)
			{
				if (abilityProps.getAbilityFromSlot(i) != null)
					abilityProps.getAbilityFromSlot(i).reset();
			}

			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
		}

		if (event.source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			ExtendedEntityData props = ExtendedEntityData.get(player);
			EntityLivingBase target = event.entityLiving;

			IAttributeInstance attrAtk = target.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.attackDamage);
			IAttributeInstance attrHP = target.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth);

			int rng = player.worldObj.rand.nextInt(3) + 1;
			int plusBelly = 0;
			long plusBounty = 0;
			double plusDoriki = 0;

			boolean targetPlayer = false;

			if (target instanceof EntityPlayer)
			{
				ExtendedEntityData targetprops = ExtendedEntityData.get(target);

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
					if ((props.getDoriki() / 100) > ((EntityNewMob) target).getDorikiPower())
					{
						int x = (props.getDoriki() / 100) - ((EntityNewMob) target).getDorikiPower();
						if (x <= 0)
							x = 1;

						plusDoriki = 1 / x;
						if (plusDoriki < 1)
							plusDoriki = 1;
					}
					else
						plusDoriki = ((EntityNewMob) target).getDorikiPower();

					plusBounty = (((EntityNewMob) target).getDorikiPower() * 2) + rng;
					plusBelly = ((EntityNewMob) target).getBellyInPockets() + rng;

					if (!ID.DEV_EARLYACCESS && !player.worldObj.isRemote && !player.capabilities.isCreativeMode)
						WyTelemetry.addStat("defeated_" + WyHelper.getFancyName(target.getClass().getSimpleName()).replace("entity", ""), 1);
				}
				else
				{
					if (attrAtk != null && attrHP != null)
					{
						double i = attrAtk.getAttributeValue();
						double j = attrHP.getAttributeValue();

						plusDoriki = (int) Math.round(((i + j) / 10) / Math.PI) + rng;
						plusBounty = (int) Math.round((i + j) / 10) + rng;
						plusBelly = 1;

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
					if (props.getDoriki() + plusDoriki < Values.MAX_DORIKI)
					{
						props.alterDoriki((int) Math.round(plusDoriki));
						DorikiEvent e = new DorikiEvent(player);
						if (MinecraftForge.EVENT_BUS.post(e))
							return;
					}
				}

				if (props.isPirate()) // TODO Revo can gain bounties and such || props.getFaction().equals(ID.FACTION_REVOLUTIONARY))
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

			if (!ID.DEV_EARLYACCESS && !player.worldObj.isRemote && !player.capabilities.isCreativeMode)
			{
				if (!targetPlayer && MainConfig.enableMobRewards)
				{
					WyTelemetry.addStat("dorikiEarnedFromEntities", (int) Math.round(plusDoriki));
					WyTelemetry.addStat("bellyEarnedFromEntities", plusBelly);
					WyTelemetry.addStat("bountyEarnedFromEntities", plusBounty);
				}
				else
				{
					WyTelemetry.addStat("dorikiEarnedFromPlayers", (int) Math.round((ExtendedEntityData.get(target).getDoriki() - ExtendedEntityData.get(target).getDorikiFromCommand()) / 4));
					WyTelemetry.addStat("bellyEarnedFromPlayers", plusBelly - ExtendedEntityData.get(target).getBellyFromCommand());
					WyTelemetry.addStat("bountyEarnedFromPlayers", (int) Math.round((ExtendedEntityData.get(target).getBounty() - ExtendedEntityData.get(target).getBountyFromCommand()) / 2));
				}
			}

			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
		}
	}

}
