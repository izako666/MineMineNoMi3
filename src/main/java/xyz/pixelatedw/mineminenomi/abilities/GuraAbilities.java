package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.GuraProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class GuraAbilities 
{

	static
	{
		Values.abilityWebAppExtraParams.put("kaishin", new String[] {"desc", "The user cracks the air, which launches a small but powerful explosion towards the opponent."});
		Values.abilityWebAppExtraParams.put("kabutowari", new String[] {"desc", "The user punches the air and creates a massive explosion around themselves."});
		Values.abilityWebAppExtraParams.put("shimayurashi", new String[] {"desc", "Launches a powerful explosion which spreads towards the opponent."});
		Values.abilityWebAppExtraParams.put("gekishin", new String[] {"desc", "The user creates a tremor while punching the enemy, inflicting massive damage."});
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new Kaishin(), new Kabutowari(), new ShimaYurashi(), new Gekishin()};

	public static class Gekishin extends Ability
	{
		public Gekishin() 
		{
			super(ModAttributes.GEKISHIN); 
		}
		
		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target) 
		{			
			super.hitEntity(player, target);
			target.attackEntityFrom(DamageSource.causePlayerDamage(player), 100);
			AbilityExplosion explosion = WyHelper.newExplosion(player, target.posX, target.posY, target.posZ, 3);
			explosion.setDamageOwner(false);
			explosion.setSmokeParticles("");
			explosion.doExplosion();
			ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_GEKISHIN, player), (ServerPlayerEntity) player);
		}
	}
	
	public static class Kaishin extends Ability
	{
		public Kaishin() 
		{
			super(ModAttributes.KAISHIN); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{			
			this.projectile = new GuraProjectiles.Kaishin(player.world, player, attr);
			super.use(player);
		} 
	}
	
	public static class Kabutowari extends Ability
	{
		public Kabutowari() 
		{
			super(ModAttributes.KABUTOWARI); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{			
			super.use(player);
		} 
	}
	
	public static class ShimaYurashi extends Ability
	{
		public ShimaYurashi() 
		{
			super(ModAttributes.SHIMA_YURASHI); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{	
			this.projectile = new GuraProjectiles.ShimaYurashi(player.world, player, attr);
			
			/*for(int[] loc : WyHelper.getBlockLocationsNearby(player, 5))
			{
				Block block = player.worldObj.getBlock(loc[0], loc[1], loc[2]);
                EntityFallingBlock entityfallingblock = new EntityFallingBlock(player.worldObj, (double)((float)loc[0] + 0.5F), (double)((float)loc[1] + 0.5F), (double)((float)loc[2] + 1.5F), block, player.worldObj.getBlockMetadata(loc[0], loc[1], loc[2]));
               
                entityfallingblock.motionY = 0.5;
                entityfallingblock.motionZ = 1;
                
                player.worldObj.spawnEntityInWorld(entityfallingblock);
			}*/
			
			super.use(player);
		} 
	}
}
