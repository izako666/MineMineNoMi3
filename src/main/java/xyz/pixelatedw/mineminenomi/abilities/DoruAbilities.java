package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.DoruProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModMiscBlocks;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;

public class DoruAbilities 
{
	static
	{
		Values.abilityWebAppExtraParams.put("candlewall", new String[] {"desc", "Creates a wax wall to protect the user."});
		Values.abilityWebAppExtraParams.put("candlehouse", new String[] {"desc", "Creates a big house-like structure made of wax, to protect the user."});
		Values.abilityWebAppExtraParams.put("dorudoruartsmori", new String[] {"desc", "The user fires a harpoon made of wax at the opponent."});
		Values.abilityWebAppExtraParams.put("dorudoruartsken", new String[] {"desc", "The user uses hardened wax to create a sword."});
		Values.abilityWebAppExtraParams.put("candlelock", new String[] {"desc", "Traps the opponent's feet in hardened wax, which makes them unable to move."});
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new DoruDoruArtsMori(), new DoruDoruArtsKen(), new CandleWall(), new CandleHouse(), new CandleLock()};

	public static class CandleLock extends Ability
	{
		public CandleLock() 
		{
			super(ModAttributes.CANDLE_LOCK); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{		
			this.projectile = new DoruProjectiles.CandleLock(player.world, player, attr);
			super.use(player);
		} 
	}
	
	public static class DoruDoruArtsKen extends Ability
	{
		public DoruDoruArtsKen()
		{
			super(ModAttributes.DORU_DORU_ARTS_KEN); 
		}
		
		@Override
		public void startPassive(PlayerEntity player) 
		{
			if(player.getHeldItemMainhand().isEmpty())
				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ModWeapons.doruDoruArtsKen));
			else
			{
				WyHelper.sendMsgToPlayer(player, "Cannot equip " + this.getAttribute().getAttributeName() + " while holding another item in hand !");
				this.passive(player);
			}
		}
		
		@Override
		public void endPassive(PlayerEntity player) 
		{
			WyHelper.removeStackFromInventory(player, new ItemStack(ModWeapons.doruDoruArtsKen));
		}
	}
	
	public static class CandleHouse extends Ability
	{
		public CandleHouse() 
		{
			super(ModAttributes.CANDLE_HOUSE); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{		
			if(!isOnCooldown)
			{
				if(CommonConfig.instance.getGriefing())
				{
					for(int y = 0; y <= 3; y++)
					{
						for(int x = 0; x < 1; x++)
							for(int z = -5; z < 5; z++)
								WyHelper.placeBlockIfAllowed(player.world, (player.posX + 6) - x, player.posY + y, player.posZ - z, ModMiscBlocks.waxBlock, "air", "foliage");
						for(int x = 0; x < 1; x++)
							for(int z = -5; z < 5; z++)
								WyHelper.placeBlockIfAllowed(player.world, (player.posX - 5) - x, player.posY + y, player.posZ - z, ModMiscBlocks.waxBlock, "air", "foliage");
						for(int x = -5; x < 5; x++)
							for(int z = 0; z < 1; z++)
								WyHelper.placeBlockIfAllowed(player.world, player.posX - x, player.posY + y, (player.posZ + 6) - z, ModMiscBlocks.waxBlock, "air", "foliage");
						for(int x = -5; x < 5; x++)
							for(int z = 0; z < 1; z++)
								WyHelper.placeBlockIfAllowed(player.world, player.posX - x, player.posY + y, (player.posZ - 5) - z, ModMiscBlocks.waxBlock, "air", "foliage");
					}
					for(int x = -5; x < 5; x++)
						for(int z = -5; z < 5; z++)
							WyHelper.placeBlockIfAllowed(player.world, player.posX - x, (player.posY + 4), player.posZ - z, ModMiscBlocks.waxBlock, "air", "foliage");
				}
				
				super.use(player);
			}
		} 
	}
	
	public static class CandleWall extends Ability
	{
		public CandleWall() 
		{
			super(ModAttributes.CANDLE_WALL); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{		
			if(!isOnCooldown)
			{
				if(CommonConfig.instance.getGriefing())
				{
					if(WyHelper.get4Directions(player) == WyHelper.Direction.NORTH)
						WyHelper.createFilledCube(player.world, player.posX, player.posY, player.posZ - 3, new int[] {3, 4, 1}, ModMiscBlocks.waxBlock, "air", "foliage");
					if(WyHelper.get4Directions(player) == WyHelper.Direction.SOUTH)
						WyHelper.createFilledCube(player.world, player.posX, player.posY, player.posZ + 3, new int[] {3, 4, 1}, ModMiscBlocks.waxBlock, "air", "foliage");
					if(WyHelper.get4Directions(player) == WyHelper.Direction.EAST)
						WyHelper.createFilledCube(player.world, player.posX + 3, player.posY, player.posZ, new int[] {1, 4, 3}, ModMiscBlocks.waxBlock, "air", "foliage");
					if(WyHelper.get4Directions(player) == WyHelper.Direction.WEST)
						WyHelper.createFilledCube(player.world, player.posX - 3, player.posY, player.posZ, new int[] {1, 4, 3}, ModMiscBlocks.waxBlock, "air", "foliage");
				}
					
				super.use(player);
			}
		} 
	}
	
	public static class DoruDoruArtsMori extends Ability
	{
		public DoruDoruArtsMori() 
		{
			super(ModAttributes.DORU_DORU_ARTS_MORI); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{		
			this.projectile = new DoruProjectiles.DoruDoruArtsMori(player.world, player, attr);
			super.use(player);
		} 
	}
	
}
