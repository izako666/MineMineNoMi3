package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper.Direction;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.helpers.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketNewAABB;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;

public class MoguAbilities
{

	static
	{
	}

	public static Ability[] abilitiesArray = new Ability[] { new PowerPoint(), new MoguraBanana(), new MoguraTonpo() };

	public static class MoguraTonpo extends Ability
	{
		
		private int initialY;
		private boolean breakBlocks;

		public MoguraTonpo()
		{
			super(ListAttributes.MOGURATONPO);
		}

		public void use(EntityPlayer player)
		{
			if (!this.isOnCooldown)
			{
				if (MainConfig.enableGriefing)
				{
					if(player.isSneaking())
					{
						int i = 0;
						for (int x = -1; x < 1; x++)
							for (int y = 0; y < 10; y++)
								for (int z = -1; z < 1; z++)
								{
									int posX = (int) player.posX + x;
									int posY = (int) player.posY - y;
									int posZ = (int) player.posZ + z;
									
									player.addPotionEffect(new PotionEffect(Potion.resistance.id, 50, 100, true));
									player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 400, 2, true));
	
									Block tempBlock = player.worldObj.getBlock(posX, posY, posZ);
									if (DevilFruitsHelper.placeBlockIfAllowed(player.worldObj, posX, posY, posZ, Blocks.air, "all", "restricted", "ignore liquid"))
									{
										WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_BAKUMUNCH, posX, posY, posZ), player.dimension, posX, posY, posZ, ID.GENERIC_PARTICLES_RENDER_DISTANCE);
									}
								}
					}
					else
					{
						double mX = (double)(-MathHelper.sin(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 0.4);
						double mZ = (double)(MathHelper.cos(player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float)Math.PI) * 0.4);
						
						this.initialY = (int) player.posY;
						this.breakBlocks = true;
						
						double f2 = MathHelper.sqrt_double(mX * mX + player.motionY * player.motionY + mZ * mZ);
						mX /= (double)f2;
						mZ /= (double)f2;
						mX += player.worldObj.rand.nextGaussian() * 0.007499999832361937D * 1.0;
						mZ += player.worldObj.rand.nextGaussian() * 0.007499999832361937D * 1.0;
						mX *= 4;
						mZ *= 4;
					
						motion("=", mX, player.motionY, mZ, player);
					}				
				}
				super.use(player);
			}
		}
		
	    public void duringCooldown(EntityPlayer player, int currentCooldown)
	    {
	    	//System.out.println("" + currentCooldown);
			if(currentCooldown > 190 && player.posY >= this.initialY)
			{
				for(int[] location : WyHelper.getBlockLocationsNearby(player, 2))
				{
					if(location[1] >= player.posY)
					{
						if(DevilFruitsHelper.placeBlockIfAllowed(player.worldObj, location[0], location[1], location[2], Blocks.air, "core", "foliage"))
						{
							WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_BAKUMUNCH, location[0], location[1], location[2]), player.dimension, location[0], location[1], location[2], ID.GENERIC_PARTICLES_RENDER_DISTANCE);
						}
					}
				}
			}
			else if(currentCooldown < 10)
			{
				this.breakBlocks = false;
			}
	    }
	}

	public static class MoguraBanana extends Ability
	{
		public MoguraBanana()
		{
			super(ListAttributes.MOGURABANANA);
		}

		public void startPassive(EntityPlayer player)
		{
			ExtendedEntityData props = ExtendedEntityData.get(player);

			if (!props.getZoanPoint().equals("power"))
			{
				this.setPassiveActive(false);
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Power Point is active !");
			}
		}

		public void hitEntity(EntityPlayer player, EntityLivingBase target)
		{
			double newPosX = 0, newPosY = 0, newPosZ = 0;

			newPosY += 1;
			Direction dir = WyHelper.get4Directions(player);
			if (dir == WyHelper.Direction.SOUTH)
				newPosZ += WyMathHelper.randomWithRange(0, 20);
			else if (dir == WyHelper.Direction.EAST)
				newPosX += WyMathHelper.randomWithRange(0, 20);
			else if (dir == WyHelper.Direction.NORTH)
				newPosZ -= WyMathHelper.randomWithRange(0, 20);
			else if (dir == WyHelper.Direction.WEST)
				newPosX -= WyMathHelper.randomWithRange(0, 20);

			target.setPositionAndUpdate(target.posX + newPosX, target.posY + newPosY, target.posZ + newPosZ);

			super.hitEntity(player, target);

		}
	}

	public static class PowerPoint extends Ability
	{
		public PowerPoint()
		{
			super(ListAttributes.MOGU_POWERPOINT);
		}

		public void passive(EntityPlayer player)
		{
			ExtendedEntityData props = ExtendedEntityData.get(player);

			if (!this.isOnCooldown && (props.getZoanPoint().equalsIgnoreCase("n/a") || props.getZoanPoint().equalsIgnoreCase("power")))
			{
				super.passive(player);
			}
		}

		public void startPassive(EntityPlayer player)
		{
			ExtendedEntityData props = ExtendedEntityData.get(player);

			if (props.getZoanPoint().isEmpty())
				props.setZoanPoint("n/a");

			// player.addPotionEffect(new PotionEffect(Potion.digSpeed.id,
			// Integer.MAX_VALUE, 100, true));

			WyNetworkHelper.sendTo(new PacketNewAABB(0.5F, 1.5F), (EntityPlayerMP) player);

			props.setZoanPoint("power");
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
			WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
		}

		public void endPassive(EntityPlayer player)
		{
			ExtendedEntityData props = ExtendedEntityData.get(player);

			// player.removePotionEffect(Potion.digSpeed.id);

			WyNetworkHelper.sendTo(new PacketNewAABB(0.6F, 1.8F), (EntityPlayerMP) player);

			props.setZoanPoint("n/a");
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
			WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
		}
	}

	private static void motion(String c, double x, double y, double z, EntityPlayer p)
	{
		WyNetworkHelper.sendTo(new PacketPlayer("motion" + c, x, y, z), (EntityPlayerMP) p);
	}
}
