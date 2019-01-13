package xyz.pixelatedw.MineMineNoMi3.abilities;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.math.ISphere;
import xyz.pixelatedw.MineMineNoMi3.api.math.Sphere;
import xyz.pixelatedw.MineMineNoMi3.blocks.BlockBarrier;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.BariProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class BariAbilities 
{
	public static Ability[] abilitiesArray = new Ability[] {new Barrier(), new BarrierBall(), new BarrierCrash(), new BariBariNoPistol(), new BarrierbilityStairs()};
	
	
	public static class BarrierbilityStairs extends Ability
	{
		private List<int[]> blockList = new ArrayList<int[]>();
		
		public BarrierbilityStairs() 
		{
			super(ListAttributes.BARRIERBILITYSTAIRS); 
		}	
		
		public void fillBlocksList(List<int[]> entityList)
		{
			this.blockList.addAll(entityList);
		}
		
		public void passive(EntityPlayer player)
		{
			if(!this.isOnCooldown)
			{
				if(this.blockList.isEmpty())
					player.worldObj.spawnEntityInWorld(new BariProjectiles.BarrierbilityStairs(player.worldObj, player, attr));
				super.passive(player);
			}
		} 
		
		public void endPassive(EntityPlayer player)
		{
			for(int[] blockPos : this.blockList)
			{
				if(player.worldObj.getBlock(blockPos[0], blockPos[1], blockPos[2]) == ListMisc.Barrier)
					player.worldObj.setBlock(blockPos[0], blockPos[1], blockPos[2], Blocks.air);
			}
            this.blockList = new ArrayList<int[]>();
		}
	}
	
	public static class BariBariNoPistol extends Ability
	{
		public BariBariNoPistol() 
		{
			super(ListAttributes.BARIBARINOPISTOL); 
		}	
		
		public void hitEntity(EntityPlayer player, EntityLivingBase target) 
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
			super(ListAttributes.BARRIERCRASH); 
		}
		
		public void use(EntityPlayer player)
		{
			this.projectile = new BariProjectiles.BarrierCrash(player.worldObj, player, attr);
			super.use(player);
		} 
	}
	
	public static class BarrierBall extends Ability
	{
		public BarrierBall() 
		{
			super(ListAttributes.BARRIERBALL); 
		}
		
		public void use(EntityPlayer player)
		{
			if(!this.isOnCooldown)
			{
				MovingObjectPosition rtr = WyHelper.rayTraceBlocks(player);

				final World world = player.worldObj;
				Sphere.generate((int)player.posX, (int)player.posY, (int)player.posZ, 5, new ISphere()
				{
					public void call(int x, int y, int z)
					{
						if(world.getBlock(x, y ,z) == Blocks.air)
							world.setBlock(x, y ,z, ListMisc.Barrier);
					}
				});
				
				super.use(player);
			}
		} 
	}
	
	public static class Barrier extends Ability
	{
		private List<int[]> blockList = new ArrayList<int[]>();
		
		public Barrier() 
		{
			super(ListAttributes.BARRIER); 
		}
		
		public void passive(EntityPlayer player) 
		{		
			if(!this.isOnCooldown)
			{
				if(this.blockList.isEmpty())
				{
					if(WyHelper.get4Directions(player) == WyHelper.Direction.NORTH)
						this.blockList = WyHelper.createFilledCube(player.worldObj, player.posX, player.posY, player.posZ - 4, new int[] {3, 4, 1}, ListMisc.Barrier, "air", "foliage");
					if(WyHelper.get4Directions(player) == WyHelper.Direction.SOUTH)
						this.blockList = WyHelper.createFilledCube(player.worldObj, player.posX, player.posY, player.posZ + 4, new int[] {3, 4, 1}, ListMisc.Barrier, "air", "foliage");
					if(WyHelper.get4Directions(player) == WyHelper.Direction.EAST)
						this.blockList = WyHelper.createFilledCube(player.worldObj, player.posX + 4, player.posY, player.posZ, new int[] {1, 4, 3}, ListMisc.Barrier, "air", "foliage");
					if(WyHelper.get4Directions(player) == WyHelper.Direction.WEST)
						this.blockList = WyHelper.createFilledCube(player.worldObj, player.posX - 4, player.posY, player.posZ, new int[] {1, 4, 3}, ListMisc.Barrier, "air", "foliage");
				}
				super.passive(player);
			}
		}
		
		public void endPassive(EntityPlayer player) 
		{
			for(int[] blockPos : this.blockList)
			{
				if(player.worldObj.getBlock(blockPos[0], blockPos[1], blockPos[2]) == ListMisc.Barrier)
					player.worldObj.setBlock(blockPos[0], blockPos[1], blockPos[2], Blocks.air);
			}
            this.blockList = new ArrayList<int[]>();
            this.startCooldown();
            this.startExtUpdate(player);
		}
		
		/*public void use(EntityPlayer player)
		{
			if(!this.isOnCooldown)
			{
				if(WyHelper.get4Directions(player) == WyHelper.Direction.NORTH)
					WyHelper.createFilledCube(player.worldObj, player.posX, player.posY, player.posZ - 4, new int[] {3, 4, 1}, ((BlockBarrier)ListMisc.Barrier).setTimer(2), "air", "foliage");
				if(WyHelper.get4Directions(player) == WyHelper.Direction.SOUTH)
					WyHelper.createFilledCube(player.worldObj, player.posX, player.posY, player.posZ + 4, new int[] {3, 4, 1}, ((BlockBarrier)ListMisc.Barrier).setTimer(2), "air", "foliage");
				if(WyHelper.get4Directions(player) == WyHelper.Direction.EAST)
					WyHelper.createFilledCube(player.worldObj, player.posX + 4, player.posY, player.posZ, new int[] {1, 4, 3}, ((BlockBarrier)ListMisc.Barrier).setTimer(2), "air", "foliage");
				if(WyHelper.get4Directions(player) == WyHelper.Direction.WEST)
					WyHelper.createFilledCube(player.worldObj, player.posX - 4, player.posY, player.posZ, new int[] {1, 4, 3}, ((BlockBarrier)ListMisc.Barrier).setTimer(2), "air", "foliage");
				
				super.use(player);
			}
		} */
	}
}
