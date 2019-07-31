package xyz.pixelatedw.mineminenomi.abilities;

import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class KachiAbilities
{

	public static Ability[] abilitiesArray = new Ability[]
		{
				new HotBoilingSpecial(), new Evaporate()
		};

	public static class HotBoilingSpecial extends Ability
	{
		public HotBoilingSpecial()
		{
			super(ModAttributes.HOT_BOILING_SPECIAL);
		}

		@Override
		public void passive(PlayerEntity player)
		{
			if (!this.isOnCooldown())
			{
				super.passive(player);
			}

		}

		@Override
		public void duringPassive(PlayerEntity player, int timer)
		{
			player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 8, 2));
		}

		@Override
		public void endPassive(PlayerEntity player)
		{
			player.removePotionEffect(Effects.RESISTANCE);
			this.startExtUpdate(player);
			this.startCooldown();
		}

		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target)
		{
			super.hitEntity(player, target);
		}
	}

	public static class Evaporate extends Ability
	{
		public Evaporate()
		{
			super(ModAttributes.EVAPORATE);
		}

		@Override
		public void use(PlayerEntity player)
		{
			if (!this.isOnCooldown)
			{

				List<int[]> coords = WyHelper.createFilledSphere(player.getEntityWorld(), (int) player.posX, (int) player.posY, (int) player.posZ, 6, Blocks.AIR, "liquids");
				for (int count = 0; count < coords.size(); count++)
				{
					int[] ints = coords.get(count);
					if (player.getEntityWorld().getBlockState(new BlockPos(ints[0], ints[1], ints[2])).getBlock().equals(Blocks.AIR))
					{
						ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_EVAPORATE, ints[0], ints[1], ints[2]), player);
					}
				}
			}
			super.use(player);
		}
	}
}
