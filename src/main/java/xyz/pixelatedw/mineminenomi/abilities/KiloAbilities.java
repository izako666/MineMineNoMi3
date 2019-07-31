package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class KiloAbilities
{

	public static Ability[] abilitiesArray = new Ability[]
		{
				new Weightless(), new KickOffJump(), new HeavyPunch(), new KiloPress()
		};

	private static boolean hasStrength(PlayerEntity player)
	{
		Object[] effects = player.getActivePotionEffects().toArray();
		for (int i = 0; i < effects.length; i++)
		{
			EffectInstance currentEffect = (EffectInstance) effects[i];
			if (currentEffect.getPotion() == Effects.STRENGTH && currentEffect.getAmplifier() == 8)
			{
				return true;
			}
		}
		return false;
	}

	public static class Weightless extends Ability
	{

		public Weightless()
		{
			super(ModAttributes.WEIGHTLESS);
		}

		@Override
		public void passive(PlayerEntity player)
		{
			if (!this.isOnCooldown)
			{
				super.passive(player);
			}

		}

		@Override
		public void duringPassive(PlayerEntity player, int passiveTimer)
		{
			if (player.onGround)
			{
				replaceUmbrella(player);
				this.setPassiveActive(false);
				this.setCooldownActive(true);
				this.endPassive(player);
			}
			/*else if (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == ModMiscItems.UmbrellaOpen)
			{
				double mZ = 0;
				double mX = 0;
				WyHelper.Direction dir = WyHelper.get8Directions(player);
				if (dir == WyHelper.Direction.NORTH)
					mZ -= 0.25;
				if (dir == WyHelper.Direction.NORTH_WEST)
				{
					mZ -= 0.20;
					mX -= 0.20;
				}
				if (dir == WyHelper.Direction.SOUTH)
					mZ += 0.25;
				if (dir == WyHelper.Direction.NORTH_EAST)
				{
					mZ -= 0.20;
					mX += 0.20;
				}
				if (dir == WyHelper.Direction.WEST)
					mX -= 0.25;
				if (dir == WyHelper.Direction.SOUTH_WEST)
				{
					mZ += 0.20;
					mX -= 0.20;
				}
				if (dir == WyHelper.Direction.EAST)
					mX += 0.25;
				if (dir == WyHelper.Direction.SOUTH_EAST)
				{
					mZ += 0.20;
					mX += 0.20;
				}
				player.fallDistance = 0;
				DevilFruitsHelper.changeMotion("=", mX, -0.1, mZ, player);
			}
			else if (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == ListMisc.Umbrella)
			{
				int slot = player.inventory.currentItem;
				player.inventory.setInventorySlotContents(slot, new ItemStack(ListMisc.UmbrellaOpen));
			}
			else
			{
				replaceUmbrella(player);
			}*/

		}

		@Override
		public void endPassive(PlayerEntity player)
		{
			this.setPassiveActive(false);
			replaceUmbrella(player);

		}

		public void replaceUmbrella(PlayerEntity player)
		{
			for (int count = 0; count < player.inventory.getSizeInventory(); count++)
			{
				/*if (player.inventory.getStackInSlot(count) != null && player.inventory.getStackInSlot(count).getItem() == ListMisc.UmbrellaOpen)
				{
					player.inventory.setInventorySlotContents(count, new ItemStack(ListMisc.Umbrella));
				}*/
			}
		}

	}

	public static class KickOffJump extends Ability
	{
		private double initialY = 255;
		private boolean isFlying = false;
		private int countDoon = 0;

		public KickOffJump()
		{
			super(ModAttributes.KICK_OFF_JUMP);
		}

		@Override
		public void passive(PlayerEntity player)
		{
			if (!this.isOnCooldown)
			{
				this.initialY = player.posY;
				super.passive(player);
			}

		}

		@Override
		public void duringPassive(PlayerEntity player, int passiveTimer)
		{
			if (!isFlying && player.posY > this.initialY)
			{
				this.isFlying = true;
			}
			else if (isFlying && this.countDoon <= 10)
			{
				DevilFruitsHelper.changeMotion("=", player.getMotion().x, 2.5, player.getMotion().z, player);
				this.countDoon += 1;
			}
			else if (isFlying && this.countDoon >= 4)
			{
				this.setPassiveActive(false);
				this.setCooldownActive(true);
				this.endPassive(player);
			}
			else if (player.onGround)
			{
				this.initialY = player.posY;
			}
		}

		@Override
		public void endPassive(PlayerEntity player)
		{
			this.countDoon = 0;
			this.isFlying = false;
			this.startCooldown();
			this.startExtUpdate(player);
		}
	}

	public static class HeavyPunch extends Ability
	{
		public HeavyPunch()
		{
			super(ModAttributes.HEAVY_PUNCH);
		}
		/*
		 * public void passive(PlayerEntity player) {
		 * if (!this.isOnCooldown) {
		 * super.passive(player);
		 * }
		 * }
		 * public void duringPassive(PlayerEntity player, int timer) {
		 * player.addPotionEffect(new PotionEffect(2, 4, 40));
		 * movePlayer("=",0,-2,0,player);
		 * if (timer == 130) {
		 * player.addPotionEffect(new PotionEffect(5,40,8));
		 * WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_KILO, player.posX, player.posY, player.posZ), player.dimension, player.posX, player.posY, player.posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
		 * } else if (timer > 130 && hasStrength(player)) {
		 * player.addPotionEffect(new PotionEffect(5,40,8));
		 * } else if (timer > 130 && !hasStrength(player)) {
		 * this.setPassiveActive(false);
		 * this.setCooldownActive(true);
		 * this.endPassive(player);
		 * }
		 * }
		 * public void endPassive(PlayerEntity player) {
		 * this.startCooldown();
		 * this.startExtUpdate(player);
		 * }
		 */

	}

	public static class KiloPress extends Ability
	{

		private double initialY;

		public KiloPress()
		{
			super(ModAttributes.KILO_PRESS);
		}

		@Override
		public void passive(PlayerEntity player)
		{
			if (!this.isOnCooldown)
			{
				/*AbilityProperties abilityProps = AbilityProperties.get(player);
				Weightless weightless = (Weightless) abilityProps.getAbilityFromName(ModAttributes.WEIGHTLESS.getAttributeName());
				if (weightless != null && weightless.isPassiveActive())
				{
					weightless.endPassive(player);
					WyNetworkHelper.sendTo(new PacketAbilitySync(abilityProps), (ServerPlayerEntity) player);
				}
				this.initialY = player.posY;*/
				super.passive(player);
			}

		}

		@Override
		public void duringPassive(PlayerEntity player, int timer)
		{
			if (player.onGround)
			{
				this.setPassiveActive(false);
				this.setCooldownActive(true);
				this.endPassive(player);
			}
			else
			{
				DevilFruitsHelper.changeMotion("=", 0, -2, 0, player);
			}
		}

		@Override
		public void endPassive(PlayerEntity player)
		{
			double damage = this.initialY - player.posY;
			if (!(damage <= 0))
			{
				for (LivingEntity entity : WyHelper.getEntitiesNear(player, 5))
				{
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), (float) (damage * 0.75));
				}
			}

			ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_KILOPRESS, player.posX, player.posY, player.posZ), player);
			this.startExtUpdate(player);
		}
	}

}
