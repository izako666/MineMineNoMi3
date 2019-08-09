package xyz.pixelatedw.mineminenomi.helpers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.abilities.CyborgAbilities;
import xyz.pixelatedw.mineminenomi.abilities.FishKarateAbilities;
import xyz.pixelatedw.mineminenomi.abilities.HakiAbilities;
import xyz.pixelatedw.mineminenomi.abilities.RokushikiAbilities;
import xyz.pixelatedw.mineminenomi.abilities.SniperAbilities;
import xyz.pixelatedw.mineminenomi.abilities.SwordsmanAbilities;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModMiscBlocks;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.items.ItemAkumaNoMi;
import xyz.pixelatedw.mineminenomi.packets.PacketMotion;

public class DevilFruitsHelper
{

	private static String[][] zoanModels = new String[][]
	{
			{
				"ushiushibison", "bison"
			},
			{
				"toritoriphoenix", "phoenix"
			},
			{
				"ushiushigiraffe", "giraffe"
			},
	};

	public static String[] flyingFruits = new String[]
	{
			"gasugasu", "sunasuna", "mokumoku"
	};
	
	public static double[] propulsion(LivingEntity entity, double extraMX, double extraMZ)
	{
		double mX = -MathHelper.sin(entity.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(entity.rotationPitch / 180.0F * (float)Math.PI) * 0.4;
		double mZ = MathHelper.cos(entity.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(entity.rotationPitch / 180.0F * (float)Math.PI) * 0.4;
			
		double f2 = MathHelper.sqrt(mX * mX + entity.getMotion().y * entity.getMotion().y + mZ * mZ);
		mX /= f2;
		mZ /= f2;
		mX += entity.world.rand.nextGaussian() * 0.007499999832361937D * 1.0;
		mZ += entity.world.rand.nextGaussian() * 0.007499999832361937D * 1.0;
		mX *= extraMX;
		mZ *= extraMZ;
		
		return new double[] {mX, mZ};
	}
	
	public static void changeMotion(String c, double x, double y, double z, PlayerEntity p)
	{
		ModNetwork.sendTo(new PacketMotion(c, x, y, z), (ServerPlayerEntity) p);
	}
	
	public static boolean canUseSwordsmanAbilities(PlayerEntity player)
	{
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		Ability sparClaw = abilityProps.getHotbarAbilityFromName(ModAttributes.SPAR_CLAW.getAttributeName());
		
		if(devilFruitProps.getDevilFruit().equalsIgnoreCase("supasupa") && sparClaw != null && sparClaw.isPassiveActive())
		{
			return true;
		}
		
		
		return false;
	}
	
	public static boolean checkForRestriction(PlayerEntity player)
	{
		ExtendedWorldData worldData = ExtendedWorldData.get(player.world);

		if(worldData.isInsideRestrictedArea((int)player.posX, (int)player.posY, (int)player.posZ))
		{
			WyHelper.sendMsgToPlayer(player, "Cannot use abilites in a restricted area !");
			return true;
		}

		return true;
	}
		
	public static boolean isDevilFruitInWorld(World world, String devilFruitName)
	{
		ExtendedWorldData worldData = ExtendedWorldData.get(world);

		if(worldData.isDevilFruitInWorld(devilFruitName))
			return true;
		
		return false;
	}
	
	public static boolean isDevilFruitInWorld(World world, ItemAkumaNoMi devilFruit)
	{
		if(devilFruit == null)
			return true;
		
		ExtendedWorldData worldData = ExtendedWorldData.get(world);
		String name = WyHelper.getFancyName(devilFruit.getName().getFormattedText()).replace("nomi", "").replace(":", "").replace(",", "").replace("model", "");
		
		if(worldData.isDevilFruitInWorld(name))
			return true;
		
		return false;
	}
	
	public static boolean isAffectedByWater(LivingEntity entity)
	{
		Block level1Block = entity.world.getBlockState(new BlockPos(entity.posX, entity.posY - 0, entity.posZ)).getBlock();
		Block level2Block = entity.world.getBlockState(new BlockPos(entity.posX, entity.posY + 1, entity.posZ)).getBlock();

		if(entity.isInWater() && entity.getRidingEntity() == null && level1Block == Blocks.WATER && level2Block != Blocks.AIR)
		{
			return true;
		}
		
		return false;
	}
	
	public static boolean isNearbyKairoseki(PlayerEntity player)
	{
		if (WyHelper.isBlockNearby(player, 4, ModMiscBlocks.kairosekiBlock, ModMiscBlocks.kairosekiOre, ModMiscBlocks.kairosekiBars)
				|| ItemsHelper.hasKairosekiItem(player) 
				|| isAffectedByWater(player))
		{
			return true;
		}

		return false;
	}

	public static boolean verifyIfAbilityIsBanned(Ability a)
	{
		for (String str : CommonConfig.instance.getBannedAbilities())
		{
			if (WyHelper.getFancyName(str).contains(WyHelper.getFancyName(a.getAttribute().getAttributeName())))
				return true;
		}

		return false;
	}

	public static void validateRacialMoves(PlayerEntity player)
	{
		IEntityStats props = EntityStatsCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);

		//DorikiEvent e = new DorikiEvent(player);
		//if (MinecraftForge.EVENT_BUS.post(e))
		//	return;

		List<Ability> tempAblList = new ArrayList<Ability>();

		if (props.isHuman())
			for (Ability a : RokushikiAbilities.abilitiesArray)
				if (abilityProps.hasRacialAbility(a) && !verifyIfAbilityIsBanned(a))
					tempAblList.add(a);

		if (props.isFishman())
			for (Ability a : FishKarateAbilities.abilitiesArray)
				if (abilityProps.hasRacialAbility(a) && !verifyIfAbilityIsBanned(a))
					tempAblList.add(a);

		if (props.isCyborg())
			for (Ability a : CyborgAbilities.abilitiesArray)
				if (abilityProps.hasRacialAbility(a) && !verifyIfAbilityIsBanned(a))
					tempAblList.add(a);

		abilityProps.clearRacialAbilities();

		for (Ability a : tempAblList)
			abilityProps.addRacialAbility(a);

		tempAblList.clear();

		for (Ability a : HakiAbilities.abilitiesArray)
			if (abilityProps.hasHakiAbility(a) && !verifyIfAbilityIsBanned(a))
				tempAblList.add(a);

		abilityProps.clearHakiAbilities();

		for (Ability a : tempAblList)
			abilityProps.addHakiAbility(a);
	}

	public static void validateStyleMoves(PlayerEntity player)
	{
		//QuestProperties questProps = QuestProperties.get(player);
		IEntityStats props = EntityStatsCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		
		if (props.isSwordsman())
		{
			if (!verifyIfAbilityIsBanned(SwordsmanAbilities.SHI_SHISHI_SONSON))
				abilityProps.addRacialAbility(SwordsmanAbilities.SHI_SHISHI_SONSON);

			if (CommonConfig.instance.getQuestProgression())
			{
				//if (questProps.hasQuestCompleted(ListQuests.swordsmanProgression04) && !verifyIfAbilityIsBanned(SwordsmanAbilities.SANBYAKUROKUJUPOUNDHO))
				//	abilityProps.addRacialAbility(SwordsmanAbilities.SANBYAKUROKUJUPOUNDHO);
			}
			else
			{
				if (!verifyIfAbilityIsBanned(SwordsmanAbilities.SANBYAKUROKUJU_POUND_HO))
					abilityProps.addRacialAbility(SwordsmanAbilities.SANBYAKUROKUJU_POUND_HO);
				if (!verifyIfAbilityIsBanned(SwordsmanAbilities.YAKKODORI))
					abilityProps.addRacialAbility(SwordsmanAbilities.YAKKODORI);
				if (!verifyIfAbilityIsBanned(SwordsmanAbilities.O_TATSUMAKI))
					abilityProps.addRacialAbility(SwordsmanAbilities.O_TATSUMAKI);
			}
		}
		else if (props.isSniper())
		{
			if (!verifyIfAbilityIsBanned(SniperAbilities.KAENBOSHI))
				abilityProps.addRacialAbility(SniperAbilities.KAENBOSHI);

			if (CommonConfig.instance.getQuestProgression())
			{

			}
			else
			{
				if (!verifyIfAbilityIsBanned(SniperAbilities.KEMURIBOSHI))
					abilityProps.addRacialAbility(SniperAbilities.KEMURIBOSHI);
				if (!verifyIfAbilityIsBanned(SniperAbilities.RENPATSUNAMARIBOSHI))
					abilityProps.addRacialAbility(SniperAbilities.RENPATSUNAMARIBOSHI);
				if (!verifyIfAbilityIsBanned(SniperAbilities.SAKURETSUSABOTENBOSHI))
					abilityProps.addRacialAbility(SniperAbilities.SAKURETSUSABOTENBOSHI);
			}
		}
	}

	public static boolean isSniperAbility(Ability abl)
	{
		for (Ability a : SniperAbilities.abilitiesArray)
		{
			if (abl.getAttribute().getAttributeName().equalsIgnoreCase(a.getAttribute().getAttributeName()))
				return true;
		}

		return false;
	}

	public static ItemStack getDevilFruitItem(String fullName)
	{
		String model = "";
		String fullModel = "";

		for (String[] s : zoanModels)
		{
			if (fullName.equals(s[0]))
			{
				model = s[1];
				fullModel = "model" + model;
				break;
			}
		}

		if (fullName.equals("yamidummy"))
			fullName = "yamiyami";

		return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ID.PROJECT_ID, fullName.replace(model, "") + "nomi" + fullModel)));
	}

	public static boolean isEntityInRoom(LivingEntity entity)
	{
		/*for (int i = -20; i < 20; i++)
			for (int j = -20; j < 20; j++)
				for (int k = -20; k < 20; k++)
				{
					if (entity.world.getBlockState(new BlockPos(entity.posX + i, entity.posY + j, entity.posZ + k)) == ModMiscBlocks.opeMid)
						return true;
				}
		*/
		return false;
	}

	public static int getRegenFromPoision(LivingEntity entity)
	{
		return entity.getActivePotionEffect(Effects.POISON).getAmplifier() / 5;
	}

}
