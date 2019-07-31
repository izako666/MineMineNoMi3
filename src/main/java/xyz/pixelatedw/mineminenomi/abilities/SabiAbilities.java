package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.abilities.effects.DFEffectRustOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class SabiAbilities
{
	public static Ability[] abilitiesArray = new Ability[]
		{
				new RustTouch()
		};

	public static class RustTouch extends Ability
	{
		public RustTouch()
		{
			super(ModAttributes.RUST_TOUCH);
		}

		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target)
		{
			if (!this.isOnCooldown())
			{
				target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 30 * 4, 2));
				target.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 30 * 4, 4));
				target.addPotionEffect(new EffectInstance(Effects.WITHER, 30 * 4, 1));
				new DFEffectRustOverlay(target, 30 * 4);
				ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_RUSTTOUCH, target), player);

				super.hitEntity(player, target);
			}
		}
	}
}
