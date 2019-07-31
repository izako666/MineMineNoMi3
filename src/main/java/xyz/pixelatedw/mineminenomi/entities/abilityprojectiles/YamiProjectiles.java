package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile.Data;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModExtraAttributes;
import xyz.pixelatedw.mineminenomi.init.ModMiscBlocks;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class YamiProjectiles 
{
	public static HashMap<AbilityAttribute, AbilityProjectile.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectile.Data>();
	
	public static final EntityType LIBERATION = WyRegistry.registerEntityType("liberation", Liberation::new, (spawnedEntity, world) -> new Liberation(world));
	public static final EntityType DARK_MATTER = WyRegistry.registerEntityType("dark_matter", DarkMatter::new, (spawnedEntity, world) -> new DarkMatter(world));

	static
	{
		projectiles.put(ModExtraAttributes.LIBERATION_BLOCK, new Data(LIBERATION, Liberation.class));
		projectiles.put(ModAttributes.DARK_MATTER, new Data(DARK_MATTER, DarkMatter.class));
	}
	
	public static class Liberation extends AbilityProjectile
	{
		private Block[] randomBlocks = new Block[] {Blocks.COBBLESTONE, Blocks.RED_SAND, Blocks.SAND, Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRAVEL, Blocks.CLAY, Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE};
		
		public Liberation(World world)
		{super(LIBERATION, world);}
		
		public Liberation(EntityType type, World world)
		{super(type, world);}
		
		public Liberation(World world, double x, double y, double z)
		{super(LIBERATION, world, x, y, z);}
		
		public Liberation(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(LIBERATION, world, player, attr);		
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			this.world.setBlockState(new BlockPos(this.posX, this.posY, this.posZ), randomBlocks[this.rand.nextInt(randomBlocks.length)].getDefaultState());
		}
	}	
	
	public static class DarkMatter extends AbilityProjectile
	{
		public DarkMatter(World world)
		{super(DARK_MATTER, world);}
		
		public DarkMatter(EntityType type, World world)
		{super(type, world);}
		
		public DarkMatter(World world, double x, double y, double z)
		{super(DARK_MATTER, world, x, y, z);}
		
		public DarkMatter(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(DARK_MATTER, world, player, attr);		
		}	

		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			if(CommonConfig.instance.getGriefing())
			{
				WyHelper.createFilledSphere(this.world, (int)this.posX, (int)this.posY, (int)this.posZ, 3, ModMiscBlocks.darkness, "air", "foliage");
					
				ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_DARKMATTER, this.posX, this.posY, this.posZ), (PlayerEntity) this.getThrower());
			}
		}
	}
}
