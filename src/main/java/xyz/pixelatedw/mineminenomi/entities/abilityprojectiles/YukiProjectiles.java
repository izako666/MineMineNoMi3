package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;
import java.util.Random;

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

public class YukiProjectiles 
{
	
	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType YUKI_RABI = WyRegistry.registerEntityType("yuki_rabi", YukiRabi::new, (spawnedEntity, world) -> new YukiRabi(world));
	
	static
	{
		projectiles.put(ModAttributes.YUKI_RABI, new Data(YUKI_RABI, YukiRabi.class));
	}
	
	public static class YukiRabi extends AbilityProjectile
	{
		public YukiRabi(World world)
		{super(YUKI_RABI, world);}
		
		public YukiRabi(EntityType type, World world)
		{super(type, world);}
		
		public YukiRabi(World world, double x, double y, double z)
		{super(YUKI_RABI, world, x, y, z);}
		
		public YukiRabi(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(YUKI_RABI, world, player, attr);		
		}	
		
		@Override
		public void tick()
		{	
			if(this.world.isRemote)
			{
				for (int i = 0; i < 1; i++)
				{
					double offsetX = (new Random().nextInt(4) + 2.0D - 2.0D) / 2.0D;
					double offsetY = (new Random().nextInt(4) + 2.0D - 2.0D) / 2.0D;
					double offsetZ = (new Random().nextInt(4) + 2.0D - 2.0D) / 2.0D;
	
					
					CustomParticle cp = new CustomParticle(world, ID.PARTICLE_ICON_YUKI,
							posX + offsetX, 
							posY + offsetY,
							posZ + offsetZ, 
							0, 0, 0)
							.setParticleAge(10).setParticleScale(1.5F);
					Minecraft.getInstance().particles.addEffect(cp);		
				}
			}
				
			super.tick();
		}
	}
	

}
