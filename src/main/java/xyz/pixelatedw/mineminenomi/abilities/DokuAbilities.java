package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.DokuProjectiles;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModMiscBlocks;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketClientSyncAll;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class DokuAbilities 
{
	static
	{
		Values.abilityWebAppExtraParams.put("hydra", new String[] {"desc", "Launches a dragon made out of liqiud poison at the opponent."});
		Values.abilityWebAppExtraParams.put("chloroball", new String[] {"desc", "The user spits a bubble made of poison towards the enemy, which leaves poison on the ground."});
		Values.abilityWebAppExtraParams.put("dokufugu", new String[] {"desc", "Shoots multiple poisonous bullets at the opponent."});
		Values.abilityWebAppExtraParams.put("dokugumo", new String[] {"desc", "Creates a dense cloud of poisonous smoke, which moves along with the user and poisons and blinds everyone inside."});
		Values.abilityWebAppExtraParams.put("venomroad", new String[] {"desc", "The user fires a Hydra at the target location and transports there through its path."});
		Values.abilityWebAppExtraParams.put("venomdemon", new String[] {"desc", "The user coats himself in layers of strong corrosive venom, becoming a Venom Demon and leaving a highly poisonou trail."});
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new Hydra(), new ChloroBall(), new DokuFugu(), new VenomRoad(), new DokuGumo(), new VenomDemon()};		
	
	public static class DokuGumo extends Ability
	{
		public DokuGumo() 
		{
			super(ModAttributes.DOKU_GUMO); 
		}

		@Override
		public void duringPassive(PlayerEntity player, int passiveTimer) 
		{
			if(passiveTimer > 400)
			{
				this.setPassiveActive(false);
				this.startCooldown();
				this.startExtUpdate(player);
			}

			ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_DOKUGOMU, player), player);

			for(LivingEntity enemy : WyHelper.getEntitiesNear(player, 10))
			{
				if(!enemy.isPotionActive(Effects.BLINDNESS))
					enemy.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 10 * 20, 0));
				if(!enemy.isPotionActive(Effects.POISON))
					enemy.addPotionEffect(new EffectInstance(Effects.POISON, 10 * 20, 1));
				if(!enemy.isPotionActive(Effects.WEAKNESS))
					enemy.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 10 * 20, 1));
			}	
		}	
		
		@Override
		public void endPassive(PlayerEntity player) 
		{
			this.startCooldown();
			this.startExtUpdate(player);
		}

	}
	
	public static class VenomDemon extends Ability
	{
		public VenomDemon() 
		{
			super(ModAttributes.VENOM_DEMON); 
		}
		
		@Override
		public void startPassive(PlayerEntity player) 
		{
			IDevilFruit props = DevilFruitCapability.get(player);

			if (props.getZoanPoint().isEmpty())
				props.setZoanPoint("");

			props.setZoanPoint("venomDemon");
			byte syncedData = 0b0000001;
			ModNetwork.sendToAll(new PacketClientSyncAll(player.getEntityId(), props, syncedData));
		}
		
		@Override
		public void duringPassive(PlayerEntity player, int passiveTimer) 
		{
			if(passiveTimer >= 800)
			{
				this.setPassiveActive(false);
				this.setCooldownActive(true);
				this.endPassive(player);
			}
			
			if(!DevilFruitsHelper.isNearbyKairoseki(player))
			{
				for(int x = -1; x < 1; x++)
				for(int z = -1; z < 1; z++)
				{
					WyHelper.placeBlockIfAllowed(player.world, player.posX + x, player.posY, player.posZ + z, ModMiscBlocks.demonPoison, "core", "foliage", "air");
				}
			}
			
			ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_VENOMDEMON, player), player);
		}		

		@Override
		public void endPassive(PlayerEntity player) 
		{
			IDevilFruit props = DevilFruitCapability.get(player);

			props.setZoanPoint("");	
			byte syncedData = 0b0000001;
			ModNetwork.sendToAll(new PacketClientSyncAll(player.getEntityId(), props, syncedData));
			
			this.startCooldown();
			this.startExtUpdate(player);
		}
	}
	
	public static class DokuFugu extends Ability
	{
		public DokuFugu() 
		{
			super(ModAttributes.DOKU_FUGU); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{	
			this.projectile = new DokuProjectiles.ChloroBall(player.world, player, attr);
			super.use(player);
		} 
	}
	
	public static class VenomRoad extends Ability
	{
		public VenomRoad() 
		{
			super(ModAttributes.VENOM_ROAD); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{	
			this.projectile = new DokuProjectiles.VenomRoad(player.world, player, attr);
			super.use(player);
		} 
	}
	
	public static class ChloroBall extends Ability
	{
		public ChloroBall() 
		{
			super(ModAttributes.CHLORO_BALL); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{	
			this.projectile = new DokuProjectiles.ChloroBall(player.world, player, attr);
			super.use(player);
		} 
	}
	
	public static class Hydra extends Ability
	{
		public Hydra() 
		{
			super(ModAttributes.HYDRA); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{	
			this.projectile = new DokuProjectiles.Hydra(player.world, player, attr);
			super.use(player);
		} 
	}
	
}
