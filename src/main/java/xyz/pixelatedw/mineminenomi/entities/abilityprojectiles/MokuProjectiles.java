package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile.Data;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;

public class MokuProjectiles 
{

	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType WHITE_SNAKE = WyRegistry.registerEntityType("white_snake", WhiteSnake::new, (spawnedEntity, world) -> new WhiteSnake(world));
	public static final EntityType WHITE_OUT = WyRegistry.registerEntityType("white_out", WhiteOut::new, (spawnedEntity, world) -> new WhiteOut(world));
	
	static
	{
		projectiles.put(ModAttributes.WHITE_SNAKE, new Data(WHITE_SNAKE, WhiteSnake.class));
		projectiles.put(ModAttributes.WHITE_OUT, new Data(WHITE_OUT, WhiteOut.class));
	}
	
	public static class WhiteSnake extends AbilityProjectile
	{
		public WhiteSnake(World world)
		{super(WHITE_SNAKE, world);}
		
		public WhiteSnake(EntityType type, World world)
		{super(type, world);}
		
		public WhiteSnake(World world, double x, double y, double z)
		{super(WHITE_SNAKE, world, x, y, z);}
		
		public WhiteSnake(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(WHITE_SNAKE, world, player, attr);		
		}
		
		@Override
		public void tick()
		{	
			if(this.world.isRemote)
			{
				for(int i = 0; i < 5; i++)
				{
					double offsetX = (new Random().nextInt(20) + 1.0D - 10.0D) / 18.0D;
					double offsetY = (new Random().nextInt(20) + 1.0D - 10.0D) / 18.0D;
					double offsetZ = (new Random().nextInt(20) + 1.0D - 10.0D) / 18.0D;
				    
					CustomParticle cp = new CustomParticle(world, ID.PARTICLE_ICON_MOKU,
							posX + offsetX, 
							posY + offsetY,
							posZ + offsetZ, 
							0, 0, 0)
							.setParticleAge(15).setParticleScale(3F);
					cp.setColor(0.3F, 0.3F, 0.3F);
					Minecraft.getInstance().particles.addEffect(cp);			
				}
			}

			super.tick();
		}
	}	
	
	public static class WhiteOut extends AbilityProjectile
	{
		public WhiteOut(World world)
		{super(WHITE_OUT, world);}
		
		public WhiteOut(EntityType type, World world)
		{super(type, world);}
		
		public WhiteOut(World world, double x, double y, double z)
		{super(WHITE_OUT, world, x, y, z);}
		
		public WhiteOut(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(WHITE_OUT, world, player, attr);		
		}
		
		@Override
		public void tick()
		{	
			if(this.world.isRemote)
			{
				for(int i = 0; i < 5; i++)
				{
					double offsetX = WyMathHelper.randomDouble();
					double offsetY = WyMathHelper.randomDouble();
					double offsetZ = WyMathHelper.randomDouble();
					   
					CustomParticle cp = new CustomParticle(world, ID.PARTICLE_ICON_MOKU,
							posX + offsetX, 
							posY + offsetY,
							posZ + offsetZ, 
							0, 0, 0)
							.setParticleAge(15).setParticleScale(3F);
					Minecraft.getInstance().particles.addEffect(cp);
				}
			}
			
			super.tick();
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			if(hit.hitInfo != null && hit.getType() == Type.ENTITY && hit.hitInfo instanceof LivingEntity && !((LivingEntity) hit.hitInfo).isAlive())
				((LivingEntity) hit.hitInfo).setPosition(this.getThrower().posX, this.getThrower().posY, this.getThrower().posZ);
		}
	}
}
