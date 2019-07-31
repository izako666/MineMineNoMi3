package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.math.ISphere;
import xyz.pixelatedw.mineminenomi.api.math.Sphere;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.YukiProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class YukiAbilities 
{

	static
	{
		Values.abilityWebAppExtraParams.put("fubuki", new String[] {"desc", "The user releases an extremely cold stream of snow that spreads into the air around them."});
		Values.abilityWebAppExtraParams.put("kamakura", new String[] {"desc", "Creates an igloo-like snow barrier around the user."});
		Values.abilityWebAppExtraParams.put("kamakurajussoshi", new String[] {"desc", "Like 'Kamakura', but creates a multi-layered snow barrier."});		
		Values.abilityWebAppExtraParams.put("yukirabi", new String[] {"desc", "Launches numerous hardened snowballs, that have the shape of a rabbit's head."});
		Values.abilityWebAppExtraParams.put("tabirayuki", new String[] {"desc", "The user creates a sword made of solid hardened snow."});
		Values.abilityWebAppExtraParams.put("yukigaki", new String[] {"desc", "The user creates a huge wall of snow."});
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new Kamakura(), new TabiraYuki(), new YukiRabi(), new KamakuraJussoshi(), new Fubuki(), new YukiGaki()};		

	public static class YukiGaki extends Ability
	{
		public YukiGaki() 
		{
			super(ModAttributes.YUKI_GAKI); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{		
			if(!isOnCooldown)
			{
				if(CommonConfig.instance.getGriefing())
				{
					if(WyHelper.get4Directions(player) == WyHelper.Direction.NORTH)
					{
						for(int x = -3; x <  3; x++)
						for(int y = 0; y <= 3; y++)
						for(int z = -1; z <= 1; z++)
							WyHelper.placeBlockIfAllowed(player.world, player.posX - x, player.posY + y, (player.posZ - 3) - z, Blocks.SNOW_BLOCK, "air", "foliage");
					}
					if(WyHelper.get4Directions(player) == WyHelper.Direction.SOUTH)
					{
						for(int x = -3; x <  3; x++)
						for(int y = 0; y <= 3; y++)
						for(int z = -1; z <= 1; z++)
							WyHelper.placeBlockIfAllowed(player.world, player.posX - x, player.posY + y, (player.posZ + 2) - z, Blocks.SNOW_BLOCK, "air", "foliage");
					}
					if(WyHelper.get4Directions(player) == WyHelper.Direction.EAST)
					{
						for(int x = -1; x < 1; x++)
						for(int y = 0; y <= 3; y++)
						for(int z = -3; z <= 3; z++)
							WyHelper.placeBlockIfAllowed(player.world, (player.posX + 2) - x, player.posY + y, player.posZ - z, Blocks.SNOW_BLOCK, "air", "foliage");
					}
					if(WyHelper.get4Directions(player) == WyHelper.Direction.WEST)
					{
						for(int x = -1; x < 1; x++)
						for(int y = 0; y <= 3; y++)
						for(int z = -3; z <= 3; z++)
							WyHelper.placeBlockIfAllowed(player.world, (player.posX - 3) - x, player.posY + y, player.posZ - z, Blocks.SNOW_BLOCK, "air", "foliage");
					}
				}
					
				super.use(player);
			}
		}
	}
	
	public static class TabiraYuki extends Ability
	{
		public TabiraYuki()
		{
			super(ModAttributes.TABIRA_YUKI); 
		}
		
		@Override
		public void startPassive(PlayerEntity player) 
		{
			if(player.getHeldItemMainhand() == ItemStack.EMPTY || player.getHeldItemMainhand().getItem() == Items.AIR)
				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ModWeapons.tabiraYuki));
			else
			{
				WyHelper.sendMsgToPlayer(player, "Cannot equip " + this.getAttribute().getAttributeName() + " while holding another item in hand !");
				this.passive(player);
			}
		}
		
		@Override
		public void endPassive(PlayerEntity player) 
		{
			WyHelper.removeStackFromInventory(player, new ItemStack(ModWeapons.tabiraYuki));
		}
	}
	
	public static class Fubuki extends Ability
	{
		public Fubuki() 
		{
			super(ModAttributes.FUBUKI); 
		}
		
		@Override
		public void use(final PlayerEntity player)
		{				
			if(!isOnCooldown)
			{
				if(CommonConfig.instance.getGriefing())
				{
					Sphere.generate((int) player.posX, (int) player.posY, (int) player.posZ, 25, new ISphere()
				    { 
						@Override
						public void call(int x, int y, int z)
						{
					    	if( player.world.isAirBlock(new BlockPos(x, y, z)) && player.world.getBlockState(new BlockPos(x, y - 1, z)).isSolid() )
					    		player.world.setBlockState(new BlockPos(x, y, z), Blocks.SNOW.getDefaultState());
						}
				    });
				}
				
				for(LivingEntity e : WyHelper.getEntitiesNear(player, 25))
				{
					e.attackEntityFrom(DamageSource.causePlayerDamage(player), 8);
					e.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 2));
				}
				
				ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_FUBUKI, player.posX, player.posY, player.posZ), player);
				super.use(player);
			}
		}
	}
	
	public static class KamakuraJussoshi extends Ability
	{
		public KamakuraJussoshi() 
		{
			super(ModAttributes.KAMAKURA_JUSSOSHI); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{	
			if(!isOnCooldown)
			{			
				if(CommonConfig.instance.getGriefing())
				{
					RayTraceResult mop = WyHelper.rayTraceBlocks(player);				
					World world = player.world;
					
					if(player.isSneaking())
					{		
						WyHelper.createEmptySphere(world, (int)player.posX, (int)player.posY, (int)player.posZ, 4, Blocks.SNOW_BLOCK, "air", "foliage", "liquids");
						WyHelper.createEmptySphere(world, (int)player.posX, (int)player.posY, (int)player.posZ, 6, Blocks.SNOW_BLOCK, "air", "foliage", "liquids");
						WyHelper.createEmptySphere(world, (int)player.posX, (int)player.posY, (int)player.posZ, 8, Blocks.SNOW_BLOCK, "air", "foliage", "liquids");
					}
					else
					{
						if(mop != null)
						{
							WyHelper.createEmptySphere(world, (int)mop.getHitVec().x, (int)mop.getHitVec().y, (int)mop.getHitVec().z, 4, Blocks.SNOW_BLOCK, "air", "foliage", "liquids");
							WyHelper.createEmptySphere(world, (int)mop.getHitVec().x, (int)mop.getHitVec().y, (int)mop.getHitVec().z, 6, Blocks.SNOW_BLOCK, "air", "foliage", "liquids");
							WyHelper.createEmptySphere(world, (int)mop.getHitVec().x, (int)mop.getHitVec().y, (int)mop.getHitVec().z, 8, Blocks.SNOW_BLOCK, "air", "foliage", "liquids");
						}
					}
				}
				
				super.use(player);
			}
		}
	}
	
	public static class YukiRabi extends Ability
	{
		public YukiRabi() 
		{
			super(ModAttributes.YUKI_RABI); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{	
			this.projectile = new YukiProjectiles.YukiRabi(player.world, player, attr);
			super.use(player);
		}
	}
	
	public static class Kamakura extends Ability
	{
		public Kamakura() 
		{
			super(ModAttributes.KAMAKURA); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			if(!isOnCooldown)
			{
				if(CommonConfig.instance.getGriefing())
				{
					RayTraceResult mop = WyHelper.rayTraceBlocks(player);				
					World world = player.world;
					
					if(player.isSneaking())
					{		
						WyHelper.createEmptySphere(world, (int)player.posX, (int)player.posY, (int)player.posZ, 4, Blocks.SNOW_BLOCK, "air", "foliage", "liquids");
					}
					else
					{
						if(mop != null)
						{
							WyHelper.createEmptySphere(world, (int)mop.getHitVec().x, (int)mop.getHitVec().y, (int)mop.getHitVec().z, 4, Blocks.SNOW_BLOCK, "air", "foliage", "liquids");
						}
					}
				}

				super.use(player);
			}
		} 
	}
	
}
