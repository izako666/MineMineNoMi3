package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.KageProjectiles;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.KageProjectiles.TsunotokagePillar;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModExtraAttributes;

public class KageAbilities
{
	
	static
	{
		Values.abilityWebAppExtraParams.put("brickbat", new String[] {"desc", "Launches bats created from the user's shadow at the the opponent."});
		Values.abilityWebAppExtraParams.put("blackbox", new String[] {"desc", "Encases and suffocates the opponent in a box made of shadows."});
		Values.abilityWebAppExtraParams.put("tsunotokage", new String[] {"desc", "The user creates a lizard-like shadow under his opponent, which pierces them from below."});
		Values.abilityWebAppExtraParams.put("doppelman", new String[] {"desc", "Creates a living version of the user's shadow to help them fight."});
	}

	public static Ability[] abilitiesArray = new Ability[]
	{
			new Doppelman(), new Kagemusha(), new BrickBat(), new BlackBox(), new Tsunotokage()
	};

	public static class BrickBat extends Ability
	{
		public BrickBat()
		{
			super(ModAttributes.BRICK_BAT);
		}

		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new KageProjectiles.BrickBat(player.world, player, attr);
			super.use(player);
		}
	}

	public static class Kagemusha extends Ability
	{
		public Kagemusha()
		{
			super(ModAttributes.KAGEMUSHA);
		}

		@Override
		public void use(PlayerEntity player)
		{
			/*if (!WyHelper.getEntitiesNear(player, 20, EntityDoppelman.class).isEmpty())
			{
				EntityDoppelman dopp = (EntityDoppelman) WyHelper.getEntitiesNear(player, 20, EntityDoppelman.class).stream().findFirst().orElse(null);

				if (dopp != null)
				{
					int[] auxPos = new int[]
					{
							(int) player.posX, (int) player.posY, (int) player.posZ
					};
					player.setPositionAndUpdate(dopp.posX, dopp.posY, dopp.posZ);
					dopp.setPositionAndUpdate(auxPos[0], auxPos[1], auxPos[2]);
				}
			}
			else
			{
				WyHelper.sendMsgToPlayer(player, "Your Doppelman is too far away");
			}*/
			super.use(player);
		}
	}

	public static class Doppelman extends Ability
	{
		//private EntityDoppelman doppelman;

		public Doppelman()
		{
			super(ModAttributes.DOPPELMAN);
		}

/*		@Override
		public void passive(PlayerEntity player)
		{			
			if(this.passiveActive && player.isSneaking() && doppelman != null)
			{
				doppelman.isAggressive = !doppelman.isAggressive;
				WyHelper.sendMsgToPlayer(player, "Your Doppelman is now " + (doppelman.isAggressive ? "aggressive" : "defensive"));
			}
			else
				super.passive(player);
		}
		
		@Override
		public void startPassive(PlayerEntity player)
		{
			doppelman = new EntityDoppelman(player.world, player);
			doppelman.setPositionAndRotation(player.posX, player.posY, player.posZ, 180, 0);
			player.world.addEntity(doppelman);
		}

		@Override
		public void endPassive(PlayerEntity player)
		{
			if (!WyHelper.getEntitiesNear(player, 20, EntityDoppelman.class).isEmpty())
				WyHelper.getEntitiesNear(player, 20, EntityDoppelman.class).forEach(x -> x.remove());

			this.startCooldown();
			this.startExtUpdate(player);
		}*/

	}

	public static class BlackBox extends Ability
	{
		public BlackBox()
		{
			super(ModAttributes.BLACK_BOX);
		}

		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new KageProjectiles.BlackBox(player.world, player, attr);
			super.use(player);
		}
	}

	public static class Tsunotokage extends Ability
	{
		public Tsunotokage()
		{
			super(ModAttributes.TSUNO_TOKAGE);
		}

		@Override
		public void use(PlayerEntity player)
		{
			if (!this.isOnCooldown)
			{
				RayTraceResult mop = WyHelper.rayTraceBlocks(player);

				if (mop != null)
				{
					double i = mop.getHitVec().x;
					double j = mop.getHitVec().y;
					double k = mop.getHitVec().z;

					TsunotokagePillar pillar = new TsunotokagePillar(player.world, player, ModExtraAttributes.TSUNOTOKAGE_PILLAR);
					pillar.setLocationAndAngles(i, j + 1, k, 0, 0);
					pillar.setMotion(0, 0.7, 0);
					player.world.addEntity(pillar);
				}

				super.use(player);
			}
		}
	}
}
