package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class DoaAbilities
{

	public static Ability[] abilitiesArray = new Ability[]
		{
				new AirDoor(), new Door()
		};

	public static class AirDoor extends Ability
	{

		public AirDoor()
		{
			super(ModAttributes.AIR_DOOR);
		}

		@Override
		public void passive(PlayerEntity player)
		{
			super.passive(player);
		}

		@Override
		public void duringPassive(PlayerEntity player, int timer)
		{
			if (timer > 45 * 8)
			{
				this.setPassiveActive(false);
				this.setCooldownActive(true);
				this.endPassive(player);
			}
		}

		@Override
		public void endPassive(PlayerEntity player)
		{
			this.startCooldown();
			this.startExtUpdate(player);
		}

	}

	public static class Door extends Ability
	{
		public Door()
		{
			super(ModAttributes.DOOR);
		}

		public static boolean isBlock(int[] coords, PlayerEntity player)
		{
			if (player.getEntityWorld().getBlockState(new BlockPos(coords[0], coords[1], coords[2])).getBlock() == Blocks.AIR && player.getEntityWorld().getBlockState(new BlockPos(coords[0], (coords[1] + 1), coords[2])).getBlock() == Blocks.AIR)
			{
				return true;
			}
			return false;
		}

		@Override
		public void use(PlayerEntity player)
		{
			if (!this.isOnCooldown())
			{
				RayTraceResult mop = WyHelper.rayTraceBlocks(player);

				if (mop != null && (mop.getHitVec().y >= (player.posY + 1)))
				{
					int checkX = (int) (mop.getHitVec().x - (int) player.posX);
					int checkZ = (int) (mop.getHitVec().z - (int) player.posZ);

					if ((checkX > -3 && checkX < 3) && (checkZ > -3 && checkZ < 3))
					{

						int[] coords = new int[]
							{
									(int) mop.getHitVec().x, (int) player.posY, (int) mop.getHitVec().z
							};
						int timer = 0;
						while (!isBlock(coords, player))
						{
							coords = WyMathHelper.moveAway(player, coords);
							timer += 1;
							if (timer >= 100)
							{
								break;
							}

						}
						WyMathHelper.moveAway(player, coords);

						if (timer < 100)
						{
							player.setPositionAndUpdate(coords[0], coords[1], coords[2]);
							super.use(player);
						}

					}
				}
			}
		}
	}

}
