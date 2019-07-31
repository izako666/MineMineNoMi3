package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile.Data;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;

public class GuraProjectiles 
{

	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType SHIMA_YURASHI = WyRegistry.registerEntityType("shima_yurashi", ShimaYurashi::new, (spawnedEntity, world) -> new ShimaYurashi(world));
	public static final EntityType KAISHIN = WyRegistry.registerEntityType("kaishin", Kaishin::new, (spawnedEntity, world) -> new Kaishin(world));

	static
	{
		projectiles.put(ModAttributes.SHIMA_YURASHI, new Data(SHIMA_YURASHI, ShimaYurashi.class));
		projectiles.put(ModAttributes.KAISHIN, new Data(KAISHIN, Kaishin.class));
	}
	
	public static class ShimaYurashi extends AbilityProjectile
	{
		public ShimaYurashi(World world)
		{super(SHIMA_YURASHI, world);}
	
		public ShimaYurashi(EntityType type, World world)
		{super(type, world);}
		
		public ShimaYurashi(World world, double x, double y, double z)
		{super(SHIMA_YURASHI, world, x, y, z);}
		
		public ShimaYurashi(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(SHIMA_YURASHI, world, player, attr);		
		}
		
		@Override
		public void tick()
		{		
			if(this.world.isRemote)
			{
				for (int i = 0; i < 2; i++)
				{
					if(i % 2 == 0)
						this.world.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
					else
					{
						CustomParticle cp = new CustomParticle(this.world, ID.PARTICLE_ICON_GURA2,
								posX + (WyMathHelper.randomDouble() * 2), 
								posY + (WyMathHelper.randomDouble() * 2),
								posZ + (WyMathHelper.randomDouble() * 2), 
								this.getMotion().x,
								this.getMotion().y,
								this.getMotion().z)
								.setParticleScale(3)
								.setParticleAge(10);
						Minecraft.getInstance().particles.addEffect(cp);
					}
				}
			}
				
			super.tick();
		}
	}	
	
	public static class Kaishin extends AbilityProjectile
	{
		public Kaishin(World world)
		{super(KAISHIN, world);}
		
		public Kaishin(EntityType type, World world)
		{super(type, world);}
		
		public Kaishin(World world, double x, double y, double z)
		{super(KAISHIN, world, x, y, z);}
		
		public Kaishin(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(KAISHIN, world, player, attr);		
		}
		
		@Override
		public void tick()
		{		
			if(this.world.isRemote)
			{
				for (int i = 0; i < 3; i++)
				{
					if(i % 2 == 0)
						this.world.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
					else
					{
						CustomParticle cp = new CustomParticle(this.world, ID.PARTICLE_ICON_GURA2,
								posX + (WyMathHelper.randomDouble() * 2), 
								posY + (WyMathHelper.randomDouble() * 2),
								posZ + (WyMathHelper.randomDouble() * 2), 
								this.getMotion().x,
								this.getMotion().y,
								this.getMotion().z)
								.setParticleScale(3)
								.setParticleAge(10);
						Minecraft.getInstance().particles.addEffect(cp);
					}
				}
			}
			
			super.tick();
		}
	}	
}
