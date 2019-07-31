package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.abilities.effects.DFEffectHieSlowness;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.HieProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModMiscBlocks;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class HieAbilities
{
	static
	{
		Values.abilityWebAppExtraParams.put("iceblockpartisan", new String[] {"desc", "Creates several spears of ice that the user hurls at the enemy."});
		Values.abilityWebAppExtraParams.put("iceball", new String[] {"desc", "Creates a sphere of ice that surrounds the opponent."});
		Values.abilityWebAppExtraParams.put("iceage", new String[] {"desc", "Freezes a large area around the user and everyone inside of it."});
		Values.abilityWebAppExtraParams.put("icetimecapsule", new String[] {"desc", "A wave of ice is sent along the ground and freezes every enemy it hits."});
		Values.abilityWebAppExtraParams.put("iceblockpheasant", new String[] {"desc", "Releases a massive wave of ice in the shape of a pheasant."});
		Values.abilityWebAppExtraParams.put("icesaber", new String[] {"desc", "Creates a sharp blade made of solid ice."});
	}

	public static Ability[] abilitiesArray = new Ability[] {new IceBlockPartisan(), new IceSaber(), new IceAge(), new IceBall(), new IceTimeCapsule(), new IceBlockPheasant()};
	
	public static class IceSaber extends Ability
	{
		public IceSaber()
		{
			super(ModAttributes.ICE_SABER); 
		}
		
		@Override
		public void startPassive(PlayerEntity player) 
		{
			if(player.getHeldItemMainhand() == ItemStack.EMPTY || player.getHeldItemMainhand().getItem() == Items.AIR)
				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ModWeapons.iceSaber));
			else
			{
				WyHelper.sendMsgToPlayer(player, "Cannot equip " + this.getAttribute().getAttributeName() + " while holding another item in hand !");
				this.passive(player);
			}
		}
		
		@Override
		public void endPassive(PlayerEntity player) 
		{
			WyHelper.removeStackFromInventory(player, new ItemStack(ModWeapons.iceSaber));
		}
	}
		
	
	public static class IceBlockPartisan extends Ability
	{
		public IceBlockPartisan() 
		{
			super(ModAttributes.ICE_BLOCK_PARTISAN); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new HieProjectiles.IceBlockPartisan(player.world, player, ModAttributes.ICE_BLOCK_PARTISAN);
			super.use(player);
		};	
	}

	public static class IceAge extends Ability
	{
		public IceAge() 
		{
			super(ModAttributes.ICE_AGE); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			if(!this.isOnCooldown)
			{		
				final World world = player.world;

				if(CommonConfig.instance.getGriefing())
				{
					for(int i = -15; i < 15; i++)
					for(int j = -10; j < 10; j++)
					for(int k = -15; k < 15; k++)
					{
						int posX = (int) (player.posX + i + (i < -WyMathHelper.randomWithRange(8, 12) || i > WyMathHelper.randomWithRange(8, 12) ? WyMathHelper.randomWithRange(-5, 5) : 0));
						int posY = (int) player.posY + j;
						int posZ = (int) (player.posZ + k + (k < -WyMathHelper.randomWithRange(8, 12) || k > WyMathHelper.randomWithRange(8, 12) ? WyMathHelper.randomWithRange(-5, 5) : 0));
						
						if(!player.world.isAirBlock(new BlockPos(posX, posY, posZ)) && player.world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock() != ModMiscBlocks.ope
								&& player.world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock() != ModMiscBlocks.opeMid && player.world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock() != Blocks.BEDROCK)
							player.world.setBlockState(new BlockPos(posX, posY, posZ), Blocks.PACKED_ICE.getDefaultState());				
					}
					
					ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_ICEAGE, player), (ServerPlayerEntity) player);
				}
				
				for(LivingEntity target : WyHelper.getEntitiesNear(player, 15))
				{
					new DFEffectHieSlowness(target, 200);
				}
				
				super.use(player);
			}
		}
	}
	
	public static class IceBall extends Ability
	{
		public IceBall() 
		{
			super(ModAttributes.ICE_BALL); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new HieProjectiles.IceBall(player.world, player, ModAttributes.ICE_BALL);
			super.use(player);
		};	
	}
	
	public static class IceTimeCapsule extends Ability
	{
		public IceTimeCapsule() 
		{
			super(ModAttributes.ICE_TIME_CAPSULE); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			if(!this.isOnCooldown())
			{
				if(CommonConfig.instance.getGriefing())
				{
					for(LivingEntity l : WyHelper.getEntitiesNear(player, 25))
					{
						WyHelper.createFilledCube(l, new int[] {2, 4, 2}, Blocks.PACKED_ICE, "air", "foliage");
					}	
				}
			
				super.use(player);
			}
		};	
	}
	
	public static class IceBlockPheasant extends Ability
	{
		public IceBlockPheasant() 
		{
			super(ModAttributes.ICE_BLOCK_PHEASANT); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new HieProjectiles.IceBlockPheasant(player.world, player, ModAttributes.ICE_BLOCK_PHEASANT);
			super.use(player);
		};		
	}
	
}
