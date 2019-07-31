package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile.Data;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModExtraAttributes;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;

public class GoroProjectiles 
{

	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType EL_THOR_THUNDER = WyRegistry.registerEntityType("el_thor_thunder", ElThorThunder::new, (spawnedEntity, world) -> new ElThorThunder(world));
	public static final EntityType SANGO = WyRegistry.registerEntityType("sango", Sango::new, (spawnedEntity, world) -> new Sango(world));
	public static final EntityType RAIGO = WyRegistry.registerEntityType("raigo", Raigo::new, (spawnedEntity, world) -> new Raigo(world));
	public static final EntityType VOLT_VARI_5_MILLION = WyRegistry.registerEntityType("volt_vari_5_million", VoltVari5Million::new, (spawnedEntity, world) -> new VoltVari5Million(world));
	public static final EntityType VOLT_VARI_20_MILLION = WyRegistry.registerEntityType("volt_vari_20_million", VoltVari20Million::new, (spawnedEntity, world) -> new VoltVari20Million(world));
	public static final EntityType VOLT_VARI_60_MILLION = WyRegistry.registerEntityType("volt_vari_60_million", VoltVari60Million::new, (spawnedEntity, world) -> new VoltVari60Million(world));
	public static final EntityType VOLT_VARI_200_MILLION = WyRegistry.registerEntityType("volt_vari_200_million", VoltVari200Million::new, (spawnedEntity, world) -> new VoltVari200Million(world));

	static
	{
		projectiles.put(ModExtraAttributes.EL_THOR_THUNDER, new Data(EL_THOR_THUNDER, ElThorThunder.class));
		projectiles.put(ModAttributes.SANGO, new Data(SANGO, Sango.class));
		projectiles.put(ModAttributes.RAIGO, new Data(RAIGO, Raigo.class));
		projectiles.put(ModExtraAttributes.VOLT_VARI_5_MILLION, new Data(VOLT_VARI_5_MILLION, VoltVari5Million.class));
		projectiles.put(ModExtraAttributes.VOLT_VARI_20_MILLION, new Data(VOLT_VARI_20_MILLION, VoltVari20Million.class));
		projectiles.put(ModExtraAttributes.VOLT_VARI_60_MILLION, new Data(VOLT_VARI_60_MILLION, VoltVari60Million.class));
		projectiles.put(ModExtraAttributes.VOLT_VARI_200_MILLION, new Data(VOLT_VARI_200_MILLION, VoltVari200Million.class));
	}
	
	public static class ElThorThunder extends AbilityProjectile
	{
		public ElThorThunder(World world)
		{super(EL_THOR_THUNDER, world);}
		
		public ElThorThunder(EntityType type, World world)
		{super(type, world);}
		
		public ElThorThunder(World world, double x, double y, double z)
		{super(EL_THOR_THUNDER, world, x, y, z);}
		
		public ElThorThunder(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(EL_THOR_THUNDER, world, player, attr);		
		}
	}	
	
	public static class Sango extends AbilityProjectile
	{
		public Sango(World world)
		{super(SANGO, world);}
		
		public Sango(EntityType type, World world)
		{super(type, world);}
		
		public Sango(World world, double x, double y, double z)
		{super(SANGO, world, x, y, z);}
		
		public Sango(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(SANGO, world, player, attr);		
		}

		@Override
		public void tick()
		{	
			if(this.world.isRemote)
			{
				for (int i = 0; i < 2; i++)
				{				    
					ResourceLocation particleToUse = i % 2 == 0 ? ID.PARTICLE_ICON_GORO2 : ID.PARTICLE_ICON_GORO;
					
					CustomParticle cp = new CustomParticle(world, particleToUse,
							posX + WyMathHelper.randomDouble(), 
							posY + WyMathHelper.randomDouble(),
							posZ + WyMathHelper.randomDouble(), 
							0, 0, 0)
							.setParticleAge(5).setParticleScale(4F);
					Minecraft.getInstance().particles.addEffect(cp);
				}			
			}
			
			super.tick();
		}
	}	
	
	public static class Raigo extends AbilityProjectile
	{
		public Raigo(World world)
		{super(RAIGO, world);}
		
		public Raigo(EntityType type, World world)
		{super(type, world);}
		
		public Raigo(World world, double x, double y, double z)
		{super(RAIGO, world, x, y, z);}
		
		public Raigo(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(RAIGO, world, player, attr);		
		}
		
		@Override
		public void tick()
		{	
			if(this.world.isRemote)
			{
				for (int i = 0; i < 35; i++)
				{
					double offsetX = WyMathHelper.randomWithRange(-8, 8);
					double offsetY = WyMathHelper.randomWithRange(-10, 20);
					double offsetZ = WyMathHelper.randomWithRange(-8, 8);
				      
					CustomParticle cp = new CustomParticle(world, ID.PARTICLE_ICON_GORO2,
							posX + offsetX + WyMathHelper.randomDouble(), 
							posY + offsetY + WyMathHelper.randomDouble(),
							posZ + offsetZ + WyMathHelper.randomDouble(), 
							0, 0, 0)
							.setParticleAge(10).setParticleScale(10);
					Minecraft.getInstance().particles.addEffect(cp);				
				}			
			}
			
			super.tick();
		}
	}
	
	public static class VoltVari5Million extends AbilityProjectile
	{
		public VoltVari5Million(World world)
		{super(VOLT_VARI_5_MILLION, world);}
		
		public VoltVari5Million(EntityType type, World world)
		{super(type, world);}
		
		public VoltVari5Million(World world, double x, double y, double z)
		{super(VOLT_VARI_5_MILLION, world, x, y, z);}
		
		public VoltVari5Million(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(VOLT_VARI_5_MILLION, world, player, attr);		
		}
		
		@Override
		public void tick()
		{	
			if(this.world.isRemote)
			{
				for (int i = 0; i < 2; i++)
				{
					CustomParticle cp = new CustomParticle(world, ID.PARTICLE_ICON_GORO2,
							posX + WyMathHelper.randomDouble(), 
							posY + WyMathHelper.randomDouble(),
							posZ + WyMathHelper.randomDouble(), 
							0, 0, 0)
							.setParticleAge(10).setParticleScale(2);
					Minecraft.getInstance().particles.addEffect(cp);			
				}			
			}
			
			super.tick();
		}
	}
	
	public static class VoltVari20Million extends AbilityProjectile
	{
		public VoltVari20Million(World world)
		{super(VOLT_VARI_20_MILLION, world);}
		
		public VoltVari20Million(EntityType type, World world)
		{super(type, world);}
		
		public VoltVari20Million(World world, double x, double y, double z)
		{super(VOLT_VARI_20_MILLION, world, x, y, z);}
		
		public VoltVari20Million(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(VOLT_VARI_20_MILLION, world, player, attr);		
		}
		
		@Override
		public void tick()
		{	
			if(this.world.isRemote)
			{
				for (int i = 0; i < 2; i++)
				{
					CustomParticle cp = new CustomParticle(world, ID.PARTICLE_ICON_GORO2,
							posX + WyMathHelper.randomDouble(), 
							posY + WyMathHelper.randomDouble(),
							posZ + WyMathHelper.randomDouble(), 
							0, 0, 0)
							.setParticleAge(10).setParticleScale(2);
					Minecraft.getInstance().particles.addEffect(cp);		
				}			
			}
			
			super.tick();
		}
	}
	
	public static class VoltVari60Million extends AbilityProjectile
	{
		public VoltVari60Million(World world)
		{super(VOLT_VARI_60_MILLION, world);}
		
		public VoltVari60Million(EntityType type, World world)
		{super(type, world);}
		
		public VoltVari60Million(World world, double x, double y, double z)
		{super(VOLT_VARI_60_MILLION, world, x, y, z);}
		
		public VoltVari60Million(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(VOLT_VARI_60_MILLION, world, player, attr);		
		}
		
		@Override
		public void tick()
		{	
			if(this.world.isRemote)
			{
				for (int i = 0; i < 2; i++)
				{
					CustomParticle cp = new CustomParticle(world, ID.PARTICLE_ICON_GORO2,
							posX + WyMathHelper.randomDouble(), 
							posY + WyMathHelper.randomDouble(),
							posZ + WyMathHelper.randomDouble(), 
							0, 0, 0)
							.setParticleAge(10).setParticleScale(2);
					Minecraft.getInstance().particles.addEffect(cp);			
				}			
			}
			
			super.tick();
		}
	}
	
	public static class VoltVari200Million extends AbilityProjectile
	{
		public VoltVari200Million(World world)
		{super(VOLT_VARI_200_MILLION, world);}
		
		public VoltVari200Million(EntityType type, World world)
		{super(type, world);}
		
		public VoltVari200Million(World world, double x, double y, double z)
		{super(VOLT_VARI_200_MILLION, world, x, y, z);}
		
		public VoltVari200Million(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(VOLT_VARI_200_MILLION, world, player, attr);		
		}
		
		@Override
		public void tick()
		{	
			if(this.world.isRemote)
			{
				for (int i = 0; i < 2; i++)
				{
					CustomParticle cp = new CustomParticle(world, ID.PARTICLE_ICON_GORO2,
							posX + WyMathHelper.randomDouble(), 
							posY + WyMathHelper.randomDouble(),
							posZ + WyMathHelper.randomDouble(), 
							0, 0, 0)
							.setParticleAge(10).setParticleScale(2);
					Minecraft.getInstance().particles.addEffect(cp);				
				}			
			}
			
			super.tick();
		}
	}
	
}
