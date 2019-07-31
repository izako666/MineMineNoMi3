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

public class ToriPhoenixProjectiles
{
	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType PHOENIX_GOEN = WyRegistry.registerEntityType("phoenix_goen", PhoenixGoen::new, (spawnedEntity, world) -> new PhoenixGoen(world));

	static
	{
		projectiles.put(ModAttributes.PHOENIX_GOEN, new Data(PHOENIX_GOEN, PhoenixGoen.class));
	}
	
	
	public static class PhoenixGoen extends AbilityProjectile
	{
		public PhoenixGoen(World world)
		{super(PHOENIX_GOEN, world);}
		
		public PhoenixGoen(EntityType type, World world)
		{super(type, world);}
		
		public PhoenixGoen(World world, double x, double y, double z)
		{super(PHOENIX_GOEN, world, x, y, z);}
		
		public PhoenixGoen(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(PHOENIX_GOEN, world, player, attr);		
		}
		
		@Override
		public void tick()
		{		
			if(this.world.isRemote)
			{
				CustomParticle particle = new CustomParticle(this.world, ID.PARTICLE_ICON_BLUEFLAME, 
						posX, 
						posY, 
						posZ, 
						0, 0, 0)
						.setParticleAge(1).setParticleScale(1.2F);
				Minecraft.getInstance().particles.addEffect(particle);
			}
			super.tick();
		}
	}
}
