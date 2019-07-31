package xyz.pixelatedw.mineminenomi.abilities;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.BariProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModMiscBlocks;

public class BariAbilities 
{
	static
	{
		Values.abilityWebAppExtraParams.put("barrier", new String[] {"desc", "The user creates an impenetrable wall that shields them from attacks."});
		Values.abilityWebAppExtraParams.put("barrierball", new String[] {"desc", "The user creates a spherical barrier around them, shielding them from attacks."});
		Values.abilityWebAppExtraParams.put("barriercrash", new String[] {"desc", "Launches a barrier towards the opponent, smashing it against them."});
		Values.abilityWebAppExtraParams.put("baribarinopistol", new String[] {"desc", "The user shapes a barrier aroud their fist, which greatly increases the power of a punch."});
		Values.abilityWebAppExtraParams.put("barrierbilitystairs", new String[] {"desc", "By shaping the Barrier, the user can create stairs."});
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new Barrier(), new BarrierBall(), new BarrierCrash(), new BariBariNoPistol(), new BarrierbilityStairs()};
	
	public static class BarrierbilityStairs extends Ability
	{
		private List<int[]> blockList = new ArrayList<int[]>();
		
		public BarrierbilityStairs() 
		{
			super(ModAttributes.BARRIERBILITY_STAIRS); 
		}	
		
		public void fillBlocksList(List<int[]> entityList)
		{
			this.blockList.addAll(entityList);
		}
		
		@Override
		public void passive(PlayerEntity player)
		{
			if(!this.isOnCooldown)
			{
				if(this.blockList.isEmpty())
				{
					AbilityProjectile proj = new BariProjectiles.BarrierbilityStairs(player.world, player, attr);
					player.world.addEntity(proj);
					proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
				}
				super.passive(player);
			}
		} 
		
		@Override
		public void endPassive(PlayerEntity player)
		{
			for(int[] blockPos : this.blockList)
			{
				if(player.world.getBlockState(new BlockPos(blockPos[0], blockPos[1], blockPos[2])).getBlock() == ModMiscBlocks.barrier)
					player.world.setBlockState(new BlockPos(blockPos[0], blockPos[1], blockPos[2]), Blocks.AIR.getDefaultState());
			}
            this.blockList = new ArrayList<int[]>();
		}
	}
	
	public static class BariBariNoPistol extends Ability
	{
		public BariBariNoPistol() 
		{
			super(ModAttributes.BARIBARI_NO_PISTOL); 
		}	
		
		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target) 
		{
			if(!this.isOnCooldown)
			{				
				super.hitEntity(player, target);
				
				target.attackEntityFrom(DamageSource.causePlayerDamage(player), 10);
			}		
		}
	}
	
	public static class BarrierCrash extends Ability
	{
		public BarrierCrash() 
		{
			super(ModAttributes.BARRIER_CRASH); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new BariProjectiles.BarrierCrash(player.world, player, attr);
			super.use(player);
		} 
	}
	
	public static class BarrierBall extends Ability
	{
		private List<int[]> blockList = new ArrayList<int[]>();
		
		public BarrierBall() 
		{
			super(ModAttributes.BARRIER_BALL); 
		}
		
		@Override
		public void passive(PlayerEntity player)
		{
			if(!this.isOnCooldown)
			{
				if(this.blockList.isEmpty())
				{
					RayTraceResult mop = WyHelper.rayTraceBlocks(player);
	
					World world = player.world;
					if(player.isSneaking())
					{		
						blockList.addAll(WyHelper.createEmptySphere(world, (int)player.posX, (int)player.posY, (int)player.posZ, 5, ModMiscBlocks.barrier, "air", "foliage", "nogrief"));
					}
					else
					{
						if(mop != null)
						{
							blockList.addAll(WyHelper.createEmptySphere(world, (int)mop.getHitVec().x, (int)mop.getHitVec().y, (int)mop.getHitVec().z, 5, ModMiscBlocks.barrier, "air", "foliage", "nogrief"));
						}
					}
				}
				
				super.passive(player);
			}
		} 
		
		@Override
		public void endPassive(PlayerEntity player) 
		{
			for(int[] blockPos : this.blockList)
			{
				if(player.world.getBlockState(new BlockPos(blockPos[0], blockPos[1], blockPos[2])).getBlock() == ModMiscBlocks.barrier)
					player.world.setBlockState(new BlockPos(blockPos[0], blockPos[1], blockPos[2]), Blocks.AIR.getDefaultState());
			}
            this.blockList = new ArrayList<int[]>();
            this.startCooldown();
            this.startExtUpdate(player);
		}
	}
	
	public static class Barrier extends Ability
	{
		private List<int[]> blockList = new ArrayList<int[]>();
		
		public Barrier() 
		{
			super(ModAttributes.BARRIER); 
		}
		
		@Override
		public void passive(PlayerEntity player) 
		{
			if(!this.isOnCooldown)
			{
				if(this.blockList.isEmpty())
				{
					if(WyHelper.get4Directions(player) == WyHelper.Direction.NORTH)
						this.blockList = WyHelper.createFilledCube(player.world, player.posX, player.posY, player.posZ - 3, new int[] {3, 4, 1}, ModMiscBlocks.barrier, "air", "foliage", "nogrief");
					if(WyHelper.get4Directions(player) == WyHelper.Direction.SOUTH)
						this.blockList = WyHelper.createFilledCube(player.world, player.posX, player.posY, player.posZ + 3, new int[] {3, 4, 1}, ModMiscBlocks.barrier, "air", "foliage", "nogrief");
					if(WyHelper.get4Directions(player) == WyHelper.Direction.EAST)
						this.blockList = WyHelper.createFilledCube(player.world, player.posX + 3, player.posY, player.posZ, new int[] {1, 4, 3}, ModMiscBlocks.barrier, "air", "foliage", "nogrief");
					if(WyHelper.get4Directions(player) == WyHelper.Direction.WEST)
						this.blockList = WyHelper.createFilledCube(player.world, player.posX - 3, player.posY, player.posZ, new int[] {1, 4, 3}, ModMiscBlocks.barrier, "air", "foliage", "nogrief");
				}
				super.passive(player);
			}
		}
		
		@Override
		public void endPassive(PlayerEntity player) 
		{
			for(int[] blockPos : this.blockList)
			{
				if(player.world.getBlockState(new BlockPos(blockPos[0], blockPos[1], blockPos[2])).getBlock() == ModMiscBlocks.barrier)
					player.world.setBlockState(new BlockPos(blockPos[0], blockPos[1], blockPos[2]), Blocks.AIR.getDefaultState());
			}
            this.blockList = new ArrayList<int[]>();
            this.startCooldown();
            this.startExtUpdate(player);
		}
	}
}
