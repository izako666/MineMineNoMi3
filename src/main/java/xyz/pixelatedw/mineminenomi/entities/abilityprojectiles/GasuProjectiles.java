package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile.Data;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;

public class GasuProjectiles 
{

	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType GASTILLE = WyRegistry.registerEntityType("gastille", Gastille::new, (spawnedEntity, world) -> new Gastille(world));
	public static final EntityType GAS_ROBE = WyRegistry.registerEntityType("gas_robe", GasRobe::new, (spawnedEntity, world) -> new GasRobe(world));
	
	static
	{
		projectiles.put(ModAttributes.GASTILLE, new Data(GASTILLE, Gastille.class));
		projectiles.put(ModAttributes.GAS_ROBE, new Data(GAS_ROBE, GasRobe.class));
	}
	
	public static class Gastille extends AbilityProjectile
	{
		public Gastille(World world)
		{super(GAS_ROBE, world);}
		
		public Gastille(EntityType type, World world)
		{super(type, world);}
		
		public Gastille(World world, double x, double y, double z)
		{super(GAS_ROBE, world, x, y, z);}
		
		public Gastille(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(GAS_ROBE, world, player, attr);		
		}	
	}
	
	public static class GasRobe extends AbilityProjectile
	{
		public GasRobe(World world)
		{super(GASTILLE, world);}
		
		public GasRobe(EntityType type, World world)
		{super(type, world);}
		
		public GasRobe(World world, double x, double y, double z)
		{super(GASTILLE, world, x, y, z);}
		
		public GasRobe(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(GASTILLE, world, player, attr);		
		}	
		
		@Override
		public void tick()
		{	
			if(this.world.isRemote)
			{
				double posXOffset = this.world.rand.nextGaussian() * 0.42D;
				double posYOffset = this.world.rand.nextGaussian() * 0.22D;
				double posZOffset = this.world.rand.nextGaussian() * 0.42D;		
				
				CustomParticle cp = new CustomParticle(world, ID.PARTICLE_ICON_GASU,
						posX + posXOffset, 
						posY + posYOffset,
						posZ + posZOffset, 
						0, 0, 0)
						.setParticleAge(2).setParticleScale(2F);
				Minecraft.getInstance().particles.addEffect(cp);
				
				posXOffset = this.world.rand.nextGaussian() * 0.12D;
				posYOffset = this.world.rand.nextGaussian() * 0.06D;
				posZOffset = this.world.rand.nextGaussian() * 0.12D;		
				
				cp = new CustomParticle(world, ID.PARTICLE_ICON_GASU2,
						posX + posXOffset, 
						posY + posYOffset,
						posZ + posZOffset, 
						0, 0, 0)
						.setParticleAge(2).setParticleScale(2F);
				Minecraft.getInstance().particles.addEffect(cp);
			}
			
			super.tick();
		}
	}
	
	
}
