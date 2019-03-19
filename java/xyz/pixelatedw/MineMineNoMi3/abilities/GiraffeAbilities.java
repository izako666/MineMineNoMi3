package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.UshiGiraffeProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketNewAABB;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;

public class GiraffeAbilities
{

	public static Ability[] abilitiesArray = new Ability[] {new PowerPoint(), new SpeedPoint(), new Bigan()};

	public static class Bigan extends Ability
	{
		public Bigan()
		{
			super(ListAttributes.BIGAN);
		}
		
		public void use(EntityPlayer player)
		{
			ExtendedEntityData props = ExtendedEntityData.get(player);

			if((props.getZoanPoint().equals("power") || props.getZoanPoint().equals("speed") ) && !this.isOnCooldown)
			{
				this.projectile = new UshiGiraffeProjectiles.Bigan(player.worldObj, player, attr);
				super.use(player);
			}
			else if(!props.getZoanPoint().equals("power") && !props.getZoanPoint().equals("speed"))
			{
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Power Point or Speed Point is active !");
			}
		}
	}
	
	public static class PowerPoint extends Ability
	{
		public PowerPoint()
		{
			super(ListAttributes.GIRAFFE_POWERPOINT);
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

			WyNetworkHelper.sendTo(new PacketNewAABB(1.5F, 3.2F), (EntityPlayerMP) player);
			
			props.setZoanPoint("power");
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
			WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
		}
		
		public void endPassive(EntityPlayer player)
		{
			ExtendedEntityData props = ExtendedEntityData.get(player);

			WyNetworkHelper.sendTo(new PacketNewAABB(0.6F, 1.8F), (EntityPlayerMP) player);
			
			props.setZoanPoint("n/a");
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
			WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
		}
	}

	public static class SpeedPoint extends Ability
	{		
		public SpeedPoint()
		{
			super(ListAttributes.GIRAFFE_SPEEDPOINT);
		}

		public void passive(EntityPlayer player)
		{
			ExtendedEntityData props = ExtendedEntityData.get(player);

			if (!this.isOnCooldown && (props.getZoanPoint().equalsIgnoreCase("n/a") || props.getZoanPoint().equalsIgnoreCase("speed")))
			{
				super.passive(player);
			}
		}
		
		public void startPassive(EntityPlayer player)
		{
			ExtendedEntityData props = ExtendedEntityData.get(player);
			
			if (props.getZoanPoint().isEmpty())
				props.setZoanPoint("n/a");
			
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2 * 20, 1, true));
			player.addPotionEffect(new PotionEffect(Potion.jump.id, 2 * 20, 1, true));
			
			WyNetworkHelper.sendTo(new PacketNewAABB(1.2F, 3.1F), (EntityPlayerMP) player);
			
			props.setZoanPoint("speed");
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
			WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
		}
		
		public void endPassive(EntityPlayer player)
		{
			ExtendedEntityData props = ExtendedEntityData.get(player);

			player.removePotionEffect(Potion.moveSpeed.id);
			player.removePotionEffect(Potion.jump.id);

			WyNetworkHelper.sendTo(new PacketNewAABB(0.6F, 1.8F), (EntityPlayerMP) player);
			
			props.setZoanPoint("n/a");
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
			WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
		}
	}
	
	
}
