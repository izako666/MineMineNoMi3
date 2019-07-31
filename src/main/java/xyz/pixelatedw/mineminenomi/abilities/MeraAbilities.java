package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.MeraProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class MeraAbilities
{
	static
	{
		Values.abilityWebAppExtraParams.put("hiken", new String[] {"desc", "Turns the user's fist into flames and launches it towards the target."});
		Values.abilityWebAppExtraParams.put("higan", new String[] {"desc", "Turns the user's fingertips into flames and shoots bullets made of fire from them."});
		Values.abilityWebAppExtraParams.put("hidaruma", new String[] {"desc", "Creates small green fireballs that set the target on fire."});
		Values.abilityWebAppExtraParams.put("jujika", new String[] {"desc", "Launches a cross-shaped column of fire at the opponent, leaving a cross of fire."});
		Values.abilityWebAppExtraParams.put("enjomo", new String[] {"desc", "Creates a circle of fire around the user, burning everyone inside of it."});
		Values.abilityWebAppExtraParams.put("daienkaientei", new String[] {"desc", "Amasses the user's flames into a gigantic fireball that the user hurls at the opponent."});
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new Hiken(), new Higan(), new DaiEnkaiEntei(), new Hidaruma(), new Jujika(), new Enjomo()};
	
	public static class Hiken extends Ability
	{
		public Hiken()
		{
			super(ModAttributes.HIKEN);
		}
		
		@Override
		public void use(PlayerEntity player)
		{	
			this.projectile = new MeraProjectiles.Hiken(player.world, player, ModAttributes.HIKEN);			
			super.use(player);		
		}
	}
	
	public static class Higan extends Ability
	{
		public Higan() 
		{
			super(ModAttributes.HIGAN); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new MeraProjectiles.Higan(player.world, player, ModAttributes.HIGAN);
			super.use(player);
		};			
	}

	public static class DaiEnkaiEntei extends Ability
	{
		public DaiEnkaiEntei() 
		{
			super(ModAttributes.DAI_ENKAI_ENTEI); 
		}
		
		@Override
		public void startCharging(PlayerEntity player)
		{
			super.startCharging(player);
		}
		
		@Override
		public void duringCharging(PlayerEntity player, int currentCharge)
		{
			//WyNetworkHelper.sendToAllAround(new PacketParticles(ID.PARTICLEFX_DAIENKAI2, player), (ServerPlayerEntity) player);
		}
		
		@Override
		public void endCharging(PlayerEntity player)
		{						
			this.projectile = new MeraProjectiles.DaiEnkaiEntei(player.world, player, ModAttributes.DAI_ENKAI_ENTEI);
			super.endCharging(player);
		}
	
	}

	public static class Hidaruma extends Ability
	{
		public Hidaruma() 
		{
			super(ModAttributes.HIDARUMA); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new MeraProjectiles.Hidaruma(player.world, player, ModAttributes.HIDARUMA);
			super.use(player);
		};			
	}

	public static class Jujika extends Ability
	{
		public Jujika() 
		{
			super(ModAttributes.JUJIKA); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new MeraProjectiles.Jujika(player.world, player, ModAttributes.JUJIKA);
			super.use(player);
		};			
	}

	public static class Enjomo extends Ability
	{
		public Enjomo() 
		{
			super(ModAttributes.ENJOMO); 
		}
		
		@Override
		public void use(final PlayerEntity player)
		{
			if(!isOnCooldown)
			{
				WyHelper.createEmptySphere(player.world, (int)player.posX, (int)player.posY, (int)player.posZ, 13, Blocks.FIRE, "air", "foliage");
					
				for(LivingEntity l : WyHelper.getEntitiesNear(player, 12))
				{
					l.setFire(20);
				}
											
				super.use(player);
			}
		}	
	}

}

