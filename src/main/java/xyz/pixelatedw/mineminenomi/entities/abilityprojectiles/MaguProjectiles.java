package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile.Data;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class MaguProjectiles 
{

	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType MEIGO = WyRegistry.registerEntityType("meigo", Meigo::new, (spawnedEntity, world) -> new Meigo(world));
	public static final EntityType DAI_FUNKA = WyRegistry.registerEntityType("dai_funka", DaiFunka::new, (spawnedEntity, world) -> new DaiFunka(world));
	
	static
	{
		projectiles.put(ModAttributes.MEIGO, new Data(MEIGO, Meigo.class));
		projectiles.put(ModAttributes.DAI_FUNKA, new Data(DAI_FUNKA, DaiFunka.class));
	}
	
	public static class Meigo extends AbilityProjectile
	{
		public Meigo(World world)
		{super(MEIGO, world);}
		
		public Meigo(EntityType type, World world)
		{super(type, world);}
		
		public Meigo(World world, double x, double y, double z)
		{super(MEIGO, world, x, y, z);}
		
		public Meigo(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(MEIGO, world, player, attr);		
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			if(hit.hitInfo != null && hit.getType() == Type.ENTITY)
				((Entity) hit.hitInfo).setFire(200);
		};
		
		@Override
		public void tick()
		{	
			for (int i = 0; i < 20; i++)
			{
				double offsetX = (new Random().nextInt(40) + 1.0D - 20.0D) / 40.0D;
				double offsetY = (new Random().nextInt(40) + 1.0D - 20.0D) / 40.0D;
				double offsetZ = (new Random().nextInt(40) + 1.0D - 20.0D) / 40.0D;
		      
				this.world.addParticle(ParticleTypes.SMOKE, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, -0.08D, 0.0D);
			}		
			super.tick();
		}
	}
	
	public static class DaiFunka extends AbilityProjectile
	{
		public DaiFunka(World world)
		{super(DAI_FUNKA, world);}
		
		public DaiFunka(EntityType type, World world)
		{super(type, world);}
		
		public DaiFunka(World world, double x, double y, double z)
		{super(DAI_FUNKA, world, x, y, z);}
		
		public DaiFunka(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(DAI_FUNKA, world, player, attr);		
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			if(hit.hitInfo != null && hit.getType() == Type.ENTITY)
				((Entity) hit.hitInfo).setFire(100);
			
			if(CommonConfig.instance.getGriefing())
				this.world.setBlockState(new BlockPos(hit.getHitVec()), Blocks.LAVA.getDefaultState());
		};
		
		@Override
		public void tick()
		{	
			for (int i = 0; i < 13; i++)
			{
				double offsetX = (new Random().nextInt(40) + 1.0D - 20.0D) / 10.0D;
				double offsetY = (new Random().nextInt(40) + 1.0D - 20.0D) / 10.0D;
				double offsetZ = (new Random().nextInt(40) + 1.0D - 20.0D) / 10.0D;
		      
				this.world.addParticle(ParticleTypes.LAVA, this.posX + offsetX, this.posY + offsetY, this.posZ + offsetZ, 0.0D, 0.0D, 0.0D);
			}		
			super.tick();
		}
	}	
	
}
