package xyz.pixelatedw.mineminenomi.abilities;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.abilities.effects.DFEffectOriBind;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.OriProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModMiscBlocks;

public class OriAbilities
{

	public static Ability[] abilitiesArray = new Ability[]
		{
				new GreatCage(), new PrisonBreak(), new AwaseBaori(), new Bind()
		};

	private static List<int[]> makeShapeSquare(PlayerEntity player, int blockX, int blockY, int blockZ, Block blockCheck, Block blockReplace)
	{

		List<int[]> blockList = new ArrayList<int[]>();
		
		for (int count = blockX - 1; count < blockX + 2; count++)
		{
			Block blockToReplace = player.world.getBlockState(new BlockPos(count, blockY, blockZ)).getBlock();
			if (blockToReplace == blockCheck)
			{
				player.world.setBlockState(new BlockPos(count, blockY, blockZ), blockReplace.getDefaultState());
				blockList.add(new int[] { count, blockY, blockZ });
			}

		}
		for (int count = blockY - 1; count < blockY + 2; count++)
		{
			Block blockToReplace = player.world.getBlockState(new BlockPos(blockX, count, blockZ)).getBlock();
			if (blockToReplace == blockCheck)
			{
				player.world.setBlockState(new BlockPos(blockX, count, blockZ), blockReplace.getDefaultState());
				blockList.add(new int[] { blockX, count, blockZ });
			}

		}
		for (int count = blockZ - 1; count < blockZ + 2; count++)
		{
			Block blockToReplace = player.world.getBlockState(new BlockPos(blockX, blockY, count)).getBlock();
			if (blockToReplace == blockCheck)
			{
				player.world.setBlockState(new BlockPos(blockX, blockY, count), blockReplace.getDefaultState());
				blockList.add(new int[] { blockX, blockY, count });

			}

		}
		return blockList;
	}

	public static class GreatCage extends Ability
	{
		public GreatCage()
		{
			super(ModAttributes.GREAT_CAGE);
		}

		@Override
		public void use(PlayerEntity player)
		{
			if (!isOnCooldown)
			{
				if (CommonConfig.instance.getGriefing())
				{
					WyHelper.createEmptyCube(player, new int[] { 5, 3, 5 }, ModMiscBlocks.oriBars, "air");
				}

				super.use(player);
			}
		}
	}

	public static class PrisonBreak extends Ability
	{

		private List<int[]> blockList;

		public PrisonBreak()
		{
			super(ModAttributes.PRISON_BREAK);
		}

		@Override
		public void passive(PlayerEntity player)
		{
			RayTraceResult point = WyHelper.rayTraceBlocks(player);
			if (!this.isOnCooldown && point != null)
			{
				if (this.blockList == null)
				{
					this.blockList = makeShapeSquare(player, (int)point.getHitVec().x - 1, (int)point.getHitVec().y, (int)point.getHitVec().z - 1, ModMiscBlocks.oriBars, Blocks.AIR);
				}
				super.passive(player);
			}
		}

		@Override
		public void endPassive(PlayerEntity player)
		{
			if (blockList != null)
			{
				for (int count = 0; count < blockList.size(); count++)
				{
					int[] currentArray = blockList.get(count);
					if (player.world.getBlockState(new BlockPos(currentArray[0], currentArray[1], currentArray[2])).getBlock() == Blocks.AIR)
					{
						player.world.setBlockState(new BlockPos(currentArray[0], currentArray[1], currentArray[2]), ModMiscBlocks.oriBars.getDefaultState());
					}
				}
			}
			this.blockList = null;
			this.startCooldown();
			this.startExtUpdate(player);
		}
	}

	public static class AwaseBaori extends Ability
	{

		public AwaseBaori()
		{
			super(ModAttributes.AWASE_BAORI);
		}

		@Override
		public void use(final PlayerEntity player)
		{
			this.projectile = new OriProjectiles.AwaseBaori(player.world, player, attr);
			super.use(player);
		}
	}

	public static class Bind extends Ability
	{
		public Bind()
		{
			super(ModAttributes.BIND);
		}

		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target)
		{
			target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20 * 8, 30));
			new DFEffectOriBind(target, 20 * 8);
			super.hitEntity(player, target);
		}
	}
}
