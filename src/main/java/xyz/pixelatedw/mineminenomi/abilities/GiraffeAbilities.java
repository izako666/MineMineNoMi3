package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.mineminenomi.packets.PacketNewAABB;
import xyz.pixelatedw.mineminenomi.packets.PacketSync;
import xyz.pixelatedw.mineminenomi.packets.PacketSyncInfo;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.network.WyNetworkHelper;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.UshiGiraffeProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class GiraffeAbilities
{

	public static Ability[] abilitiesArray = new Ability[] {new PowerPoint(), new SpeedPoint(), new Bigan()};

	public static class Bigan extends Ability
	{
		public Bigan()
		{
			super(ModAttributes.BIGAN);
		}
		
		@Override
		public void use(PlayerEntity player)
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
			super(ModAttributes.GIRAFFE_POWER_POINT);
		}
		
		@Override
		public void passive(PlayerEntity player)
		{
			ExtendedEntityData props = ExtendedEntityData.get(player);

			if (!this.isOnCooldown && (props.getZoanPoint().equalsIgnoreCase("n/a") || props.getZoanPoint().equalsIgnoreCase("power")))
			{
				super.passive(player);
			}
		}
		
		@Override
		public void startPassive(PlayerEntity player)
		{
			ExtendedEntityData props = ExtendedEntityData.get(player);

			if (props.getZoanPoint().isEmpty())
				props.setZoanPoint("n/a");

			WyNetworkHelper.sendTo(new PacketNewAABB(1.5F, 3.2F), (ServerPlayerEntity) player);
			
			props.setZoanPoint("power");
			WyNetworkHelper.sendTo(new PacketSync(props), (ServerPlayerEntity) player);
			WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
		}
		
		@Override
		public void endPassive(PlayerEntity player)
		{
			ExtendedEntityData props = ExtendedEntityData.get(player);

			WyNetworkHelper.sendTo(new PacketNewAABB(0.6F, 1.8F), (ServerPlayerEntity) player);
			
			props.setZoanPoint("n/a");
			WyNetworkHelper.sendTo(new PacketSync(props), (ServerPlayerEntity) player);
			WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
		}
	}

	public static class SpeedPoint extends Ability
	{		
		public SpeedPoint()
		{
			super(ModAttributes.GIRAFFE_SPEED_POINT);
		}

		@Override
		public void passive(PlayerEntity player)
		{
			ExtendedEntityData props = ExtendedEntityData.get(player);

			if (!this.isOnCooldown && (props.getZoanPoint().equalsIgnoreCase("n/a") || props.getZoanPoint().equalsIgnoreCase("speed")))
			{
				super.passive(player);
			}
		}
		
		@Override
		public void startPassive(PlayerEntity player)
		{
			ExtendedEntityData props = ExtendedEntityData.get(player);
			
			if (props.getZoanPoint().isEmpty())
				props.setZoanPoint("n/a");
			
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2 * 20, 1, true));
			player.addPotionEffect(new PotionEffect(Potion.jump.id, 2 * 20, 1, true));
			
			WyNetworkHelper.sendTo(new PacketNewAABB(1.2F, 3.1F), (ServerPlayerEntity) player);
			
			props.setZoanPoint("speed");
			WyNetworkHelper.sendTo(new PacketSync(props), (ServerPlayerEntity) player);
			WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
		}
		
		@Override
		public void endPassive(PlayerEntity player)
		{
			ExtendedEntityData props = ExtendedEntityData.get(player);

			player.removePotionEffect(Potion.moveSpeed.id);
			player.removePotionEffect(Potion.jump.id);

			WyNetworkHelper.sendTo(new PacketNewAABB(0.6F, 1.8F), (ServerPlayerEntity) player);
			
			props.setZoanPoint("n/a");
			WyNetworkHelper.sendTo(new PacketSync(props), (ServerPlayerEntity) player);
			WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
		}
	}
	
	
}
