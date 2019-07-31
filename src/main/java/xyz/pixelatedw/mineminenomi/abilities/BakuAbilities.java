package xyz.pixelatedw.mineminenomi.abilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.BakuProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class BakuAbilities
{

	public static Ability[] abilitiesArray = new Ability[] {new BakuMunch(), new BeroCannon(), new BakuTsuiho()};

	private static Block[] bakuPermittedBlocks = new Block[] 
			{ 
					Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.ACACIA_LEAVES, Blocks.BIRCH_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.JUNGLE_LEAVES, Blocks.OAK_LEAVES, Blocks.SPRUCE_LEAVES, 
					Blocks.ACACIA_PLANKS, Blocks.BIRCH_PLANKS, Blocks.DARK_OAK_PLANKS, Blocks.JUNGLE_PLANKS, Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS, Blocks.ACACIA_LOG, Blocks.BIRCH_LOG,
					Blocks.DARK_OAK_LOG, Blocks.JUNGLE_LOG, Blocks.OAK_LOG, Blocks.SPRUCE_LOG, Blocks.STONE, Blocks.COBBLESTONE, Blocks.SAND, Blocks.RED_SAND, Blocks.SANDSTONE,
					Blocks.GRAVEL, Blocks.PACKED_ICE, Blocks.CLAY, Blocks.CACTUS, Blocks.DEAD_BUSH
			};
	
	public static class BakuBakuFactory extends Ability
	{
		public BakuBakuFactory() 
		{
			super(ModAttributes.BAKU_BAKU_FACTORY); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			//Something something open a crafting GUI :)
			super.use(player);
		}
	}
	
	public static class BakuTsuiho extends Ability
	{
		private List<ItemStack> projectiles = new ArrayList<ItemStack>();
		private List<Block> loadedProjectiles = new ArrayList<Block>();
		
		public BakuTsuiho() 
		{
			super(ModAttributes.BAKU_TSUIHO); 
		}

		@Override
		public void startCharging(PlayerEntity player)
		{			
			loadedProjectiles.clear();
			projectiles.clear();
			
			for(ItemStack item : player.inventory.mainInventory)
			{
				if(item != null && item.getItem() instanceof BlockItem && Arrays.stream(bakuPermittedBlocks).anyMatch(p -> p == ((BlockItem)item.getItem()).getBlock() ))
					projectiles.add(item);
			}
			
			if(!projectiles.isEmpty())
			{
				super.startCharging(player);
			}
			else
				WyHelper.sendMsgToPlayer(player, "You don't have any blocks to use");
		}
		
		@Override
		public void duringCharging(PlayerEntity player, int chargeTime)
		{
			if(!projectiles.isEmpty())
			{
				if(chargeTime % 20 == 0)
				{
					ItemStack s = projectiles.stream().findAny().orElse(null);
					
					if(s != null)
					{
						if(s.getCount() > 1)
							s.setCount(s.getCount() - 1);
						else
						{
							WyHelper.removeStackFromInventory(player, s);
							projectiles.remove(s);
						}
						loadedProjectiles.add( ((BlockItem)s.getItem()).getBlock() );
					}
				}
			}
			else
				endCharging(player);
		}
		
		@Override
		public void endCharging(PlayerEntity player)
		{
			for(int j = 0; j < this.loadedProjectiles.size(); j++)
			{
				AbilityProjectile proj = new BakuProjectiles.BeroCannon(player.world, player, ModAttributes.BERO_CANNON);
				int distanceBetweenProjectiles = this.loadedProjectiles.size() / 7;
				
				proj.setLocationAndAngles(
						player.posX + WyMathHelper.randomWithRange(-distanceBetweenProjectiles, distanceBetweenProjectiles) + player.world.rand.nextDouble(), 
						(player.posY + 0.3) + WyMathHelper.randomWithRange(0, distanceBetweenProjectiles) + player.world.rand.nextDouble(), 
						player.posZ + WyMathHelper.randomWithRange(-distanceBetweenProjectiles, distanceBetweenProjectiles) + player.world.rand.nextDouble(), 
						0, 0);
				player.world.addEntity(proj);
				proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
			}
			super.endCharging(player);
		}
	}
	
	public static class BeroCannon extends Ability
	{
		public BeroCannon() 
		{
			super(ModAttributes.BERO_CANNON); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			if(!this.isOnCooldown)
			{
				List<ItemStack> projectiles = new ArrayList<ItemStack>();

				for(ItemStack item : player.inventory.mainInventory)
				{
					if(item != null && item.getItem() instanceof BlockItem && Arrays.stream(bakuPermittedBlocks).anyMatch(p -> p == ((BlockItem)item.getItem()).getBlock() ))
						projectiles.add(item);
				}
				
				if(!projectiles.isEmpty())
				{
					this.projectile = new BakuProjectiles.BeroCannon(player.world, player, attr);
					
					ItemStack s = projectiles.stream().findFirst().orElse(null);
					
					if(s.getCount() > 1)
						s.setCount(s.getCount() - 1);
					else
						WyHelper.removeStackFromInventory(player, s);
					
					super.use(player);
				}
				else
					WyHelper.sendMsgToPlayer(player, "You don't have any blocks to use");
			}
		}
	}
	
	public static class BakuMunch extends Ability
	{
		public BakuMunch() 
		{
			super(ModAttributes.BAKU_MUNCH); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			if(!this.isOnCooldown)
			{
				RayTraceResult mop = WyHelper.rayTraceBlocks(player);
				if(mop != null && MathHelper.sqrt(player.getDistanceSq(mop.getHitVec().x, mop.getHitVec().y, mop.getHitVec().z)) < 5)
				{
					if(CommonConfig.instance.getGriefing())
					{
						int i = 0;
						for(int x = -2; x < 2; x++)
						for(int y = 0; y < 3; y++)
						for(int z = -2; z < 2; z++)
						{
							int posX = (int)mop.getHitVec().x + x;
							int posY = (int)mop.getHitVec().y - y;
							int posZ = (int)mop.getHitVec().z + z;
							
							Block tempBlock = player.world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock();
							if(WyHelper.placeBlockIfAllowed(player.world, posX, posY, posZ, Blocks.AIR, "all", "restricted", "ignore liquids"))
							{
								player.inventory.addItemStackToInventory(new ItemStack(tempBlock));
								ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_BAKUMUNCH, posX, posY, posZ), player);
							}
						}
						super.use(player);
					}
				}
			}
		}
	}	
}
