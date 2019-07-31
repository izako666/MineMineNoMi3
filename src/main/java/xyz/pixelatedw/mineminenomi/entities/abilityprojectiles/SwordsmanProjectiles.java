package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile.Data;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class SwordsmanProjectiles
{
	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType SANBYAKUROKUJU_POUND_HO = WyRegistry.registerEntityType("sanbyakurokuju_pound_ho", SanbyakurokujuPoundHo::new, (spawnedEntity, world) -> new SanbyakurokujuPoundHo(world));
	public static final EntityType YAKKODORI = WyRegistry.registerEntityType("yakkodori", Yakkodori::new, (spawnedEntity, world) -> new Yakkodori(world));

	static
	{
		projectiles.put(ModAttributes.SANBYAKUROKUJU_POUND_HO, new Data(SANBYAKUROKUJU_POUND_HO, SanbyakurokujuPoundHo.class));
		projectiles.put(ModAttributes.YAKKODORI, new Data(YAKKODORI, Yakkodori.class));
	}
	
	public static class Yakkodori extends AbilityProjectile
	{
		public Yakkodori(World world)
		{super(YAKKODORI, world);}
		
		public Yakkodori(EntityType type, World world)
		{super(type, world);}
		
		public Yakkodori(World world, double x, double y, double z)
		{super(YAKKODORI, world, x, y, z);}
		
		public Yakkodori(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(YAKKODORI,  world, player, attr);		
		}
	}
	
	public static class SanbyakurokujuPoundHo extends AbilityProjectile
	{
		public SanbyakurokujuPoundHo(World world)
		{super(SANBYAKUROKUJU_POUND_HO, world);}
		
		public SanbyakurokujuPoundHo(EntityType type, World world)
		{super(type, world);}
		
		public SanbyakurokujuPoundHo(World world, double x, double y, double z)
		{super(SANBYAKUROKUJU_POUND_HO, world, x, y, z);}
		
		public SanbyakurokujuPoundHo(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(SANBYAKUROKUJU_POUND_HO, world, player, attr);		
		}
	}
}
