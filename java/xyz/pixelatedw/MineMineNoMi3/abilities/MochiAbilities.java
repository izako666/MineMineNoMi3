package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.abilities.BariAbilities.Barrier;
import xyz.pixelatedw.MineMineNoMi3.abilities.BariAbilities.BarrierBall;
import xyz.pixelatedw.MineMineNoMi3.abilities.BariAbilities.BarrierCrash;
import xyz.pixelatedw.MineMineNoMi3.abilities.extra.effects.DFEffectHieSlowness;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.GoeProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.GomuProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.MochiProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.MochiProjectiles.KakuMochiProjectile;
import xyz.pixelatedw.MineMineNoMi3.helpers.ItemsHelper;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListExtraAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;

public class MochiAbilities {
	static {
		Values.abilityWebAppExtraParams.put("kaku mochi", new String[] { "desc", "Mochi hardened punch" });
		Values.abilityWebAppExtraParams.put("yaki mochi",
				new String[] { "desc", "Mochi fist thrown at speeds that light it on fire" });
		Values.abilityWebAppExtraParams.put("nagare mochi",
				new String[] { "desc", "turns the ground around to mochi." });
		Values.abilityWebAppExtraParams.put("yanagi mochi",
				new String[] { "desc", "Multiple leg like mochi projectiles at once." });
		Values.abilityWebAppExtraParams.put("mochi tsuki",
				new String[] { "desc", "Twists your mochi arm then thrusts the weapon with the rotational force." });
		Values.abilityWebAppExtraParams.put("karada mochi",
				new String[] { "desc", "Allows you to focus in for a bit dodging every attack." });

	}

	public static Ability[] abilitiesArray = new Ability[] { new KakuMochi(), new YakiMochi(), new NagareMochi(),
			new YanagiMochi(), new MochiTsuki(), new KaradaMochi() };

	public static class KakuMochi extends Ability {
		public KakuMochi() {
			super(ListAttributes.KAKUMOCHI);
		}

		public void endCharging(EntityPlayer player) {
			if (!this.isOnCooldown) {
				this.projectile = new MochiProjectiles.KakuMochiProjectile(player.worldObj, player,
						ListAttributes.KAKUMOCHI);
			}
			super.endCharging(player);
		}
	}

	public static class YakiMochi extends Ability {

		public YakiMochi() {
			super(ListAttributes.YAKIMOCHI);

		}

		public void use(EntityPlayer player) {
			if (!this.isOnCooldown) {
				this.projectile = new MochiProjectiles.YakiMochiProjectile(player.worldObj, player,
						ListAttributes.YAKIMOCHI);
			}
			super.use(player);
		}

	}

	public static class NagareMochi extends Ability {

		public NagareMochi() {
			super(ListAttributes.NAGAREMOCHI);

		}

		public void use(EntityPlayer player) {
			if (!this.isOnCooldown) {
				final World world = player.worldObj;

				if (MainConfig.enableGriefing) {
					for (int i = -15; i < 15; i++)
						for (int j = -10; j < 10; j++)
							for (int k = -15; k < 15; k++) {
								int posX = (int) (player.posX + i
										+ (i < -WyMathHelper.randomWithRange(8, 12)
												|| i > WyMathHelper.randomWithRange(8, 12)
														? WyMathHelper.randomWithRange(-5, 5)
														: 0));
								int posY = (int) player.posY + j;
								int posZ = (int) (player.posZ + k
										+ (k < -WyMathHelper.randomWithRange(8, 12)
												|| k > WyMathHelper.randomWithRange(8, 12)
														? WyMathHelper.randomWithRange(-5, 5)
														: 0));

								if (!player.worldObj.isAirBlock(posX, posY, posZ)
										&& player.worldObj.getBlock(posX, posY, posZ) != ListMisc.Ope
										&& player.worldObj.getBlock(posX, posY, posZ) != ListMisc.OpeMid
										&& player.worldObj.getBlock(posX, posY, posZ) != Blocks.bedrock)
									player.worldObj.setBlock(posX, posY, posZ, ListMisc.Mochi);
							}

				}

				super.use(player);
			}
		}
	}

	public static class YanagiMochi extends Ability {

		public YanagiMochi() {
			super(ListAttributes.YANAGIMOCHI);

		}
		int projectileSpace = 3;

		public void use(EntityPlayer player) {
			if (!isOnCooldown) {
				for(int i = 0; i < 25; i++) {
					AbilityProjectile proj = null;

				proj = new MochiProjectiles.KakuMochiProjectile(player.worldObj, player,
						ListAttributes.YANAGIMOCHI);
				proj.setLocationAndAngles(
						player.posX + WyMathHelper.randomWithRange(-projectileSpace, projectileSpace) + player.worldObj.rand.nextDouble(), 
						(player.posY + 0.3) + WyMathHelper.randomWithRange(0, projectileSpace) + player.worldObj.rand.nextDouble(), 
						player.posZ + WyMathHelper.randomWithRange(-projectileSpace, projectileSpace) + player.worldObj.rand.nextDouble(), 
						0, 0);
				player.worldObj.spawnEntityInWorld(proj);}
			}
			super.use(player);
		}

	}

	public static class MochiTsuki extends Ability {

		public MochiTsuki() {
			super(ListAttributes.MOCHITSUKI);
		}

		public void endCharging(EntityPlayer player) {
			if (player.getHeldItem() != null && ItemsHelper.isSword(player.getHeldItem())) {
				if (!isOnCooldown) {
					this.projectile = new MochiProjectiles.MochiTsukiProjectile(player.worldObj, player,
							ListAttributes.MOCHITSUKI);
				}
			} else {
				WyHelper.sendMsgToPlayer(player, "You need a sword to use this ability !");
			}
			super.endCharging(player);

		}

	}

	public static class KaradaMochi extends Ability {
		public KaradaMochi() {
			super(ListAttributes.KARADAMOCHI);
		}

	

		public void duringPassive(EntityPlayer player, int passivetimer) {
		
			this.setPassiveActive(true);
				if (passivetimer > 20 * 7) {
					this.setPassiveActive(false);
					this.setCooldownActive(true);
					this.endPassive(player);
				}
			}
		

		public void endPassive(EntityPlayer player) {
			this.startCooldown();
			this.startExtUpdate(player);


		}

	}

}
