package xyz.pixelatedw.mineminenomi.abilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyHelper.Direction;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.SunaProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModMiscBlocks;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class SunaAbilities 
{
	static
	{
		Values.abilityWebAppExtraParams.put("barjan", new String[] {"desc", "Launches a crescent-shaped wave of sand at the opponent, that dehydrates them."});
		Values.abilityWebAppExtraParams.put("grounddeath", new String[] {"desc", "Dries out the surroundings and suffucates all nearby opponents in sand."});
		Values.abilityWebAppExtraParams.put("sables", new String[] {"desc", "The user launches a compressed sandstorm at the opponent, which sends them flying."});		
		Values.abilityWebAppExtraParams.put("desertspada", new String[] {"desc", "The user extends their sand along the ground, splitting it and suffocating everything it its path. "});		
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new Barjan(), new Sables(), new GroundDeath(), new DesertSpada(), new DesertEncierro(), new DesertGirasole()};

	public static class DesertGirasole extends Ability
	{
		private List<BlockPos> positions = new ArrayList<BlockPos>();
		
		public DesertGirasole() 
		{
			super(ModAttributes.DESERT_GIRASOLE); 
		}
		
		@Override
		public void startCharging(PlayerEntity player)
		{
			if(!this.isOnCooldown)
			{
				ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_DESERTGIRASOLE, player), (ServerPlayerEntity) player);
				
				if(CommonConfig.instance.getGriefing())
				{
					for(int i = -15; i < 15; i++)
					for(int j = -5; j < 5; j++)
					for(int k = -15; k < 15; k++)
					{
						int posX = (int) (player.posX + i + (i < -WyMathHelper.randomWithRange(8, 12) || i > WyMathHelper.randomWithRange(8, 12) ? WyMathHelper.randomWithRange(-5, 5) : 0));
						int posY = (int) player.posY + j;
						int posZ = (int) (player.posZ + k + (k < -WyMathHelper.randomWithRange(8, 12) || k > WyMathHelper.randomWithRange(8, 12) ? WyMathHelper.randomWithRange(-5, 5) : 0));
						
						BlockPos pos = new BlockPos(posX, posY, posZ);
						
						this.positions.add(pos);
					}
					
				}
			}		
			super.startCharging(player);
		}
		
		@Override
		public void endCharging(PlayerEntity player)
		{		
			if(!this.isOnCooldown)
			{
				if(CommonConfig.instance.getGriefing())
				{
					ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_DESERTGIRASOLE2, player), player);				

					Iterator<BlockPos> i = this.positions.iterator();
						
					while(i.hasNext())
					{
						BlockPos pos = i.next();
							
						WyHelper.placeBlockIfAllowed(player.world, pos.getX(), pos.getY(), pos.getZ(), ModMiscBlocks.sunaSand, "core", "ores", "liquids", "foliage");		
					}					
					
				}

				super.endCharging(player);
			}	
		}
	}
	
	public static class DesertEncierro extends Ability
	{
		public DesertEncierro() 
		{
			super(ModAttributes.DESERT_ENCIERRO); 
		}
		
		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target)
		{
			ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_DESERTENCIERRO, target), player);
			super.hitEntity(player, target);
		}
	}
	
	public static class Barjan extends Ability
	{
		public Barjan() 
		{
			super(ModAttributes.BARJAN); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{		
			this.projectile = new SunaProjectiles.Barjan(player.world, player, attr);
			super.use(player);
		}
	}
	
	public static class Sables extends Ability
	{
		public Sables() 
		{
			super(ModAttributes.SABLES); 
		}	
		
		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target) 
		{	
			double newPosX = 0, newPosY = 0, newPosZ = 0;
			
			newPosY += 25;
			target.addPotionEffect(new EffectInstance(Effects.HUNGER, 500, 1));
			Direction dir = WyHelper.get4Directions(player);
			if(dir == WyHelper.Direction.SOUTH)
				newPosX += WyMathHelper.randomWithRange(-10, 10);
			else if(dir == WyHelper.Direction.EAST)
				newPosX -= WyMathHelper.randomWithRange(-10, 10);
			else if(dir == WyHelper.Direction.NORTH)
				newPosZ += WyMathHelper.randomWithRange(-10, 10);
			else if(dir == WyHelper.Direction.WEST)  
				newPosZ -= WyMathHelper.randomWithRange(-10, 10);

			ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_SABLES, target), player);
			target.setPositionAndUpdate(target.posX + newPosX, target.posY + newPosY, target.posZ + newPosZ);
			
			super.hitEntity(player, target);
		}
	}
	
	public static class GroundDeath extends Ability
	{
		public GroundDeath() 
		{
			super(ModAttributes.GROUND_DEATH); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{	
			if(!this.isOnCooldown())
			{
				if(CommonConfig.instance.getGriefing())
				{
					for(LivingEntity LivingEntity : WyHelper.getEntitiesNear(player, 25))
					{
						WyHelper.createFilledCube(LivingEntity, new int[] {2, 2, 2}, Blocks.SAND, "air");
					}	
					
					ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_GROUNDDEATH, player.posX, player.posY, player.posZ), player);			
				}
				
				super.use(player);
			}
		}
	}
	
	public static class DesertSpada extends Ability
	{
		public DesertSpada() 
		{
			super(ModAttributes.DESERT_SPADA); 
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
						for(int i = -4; i < 6; i++)
						for(int j = 0; j < 5; j++)
						for(int k = 0; k < 30; k++)		
						{
							int posX = (int) player.posX + i;
							int posY = (int) player.posY - (j + 1);
							int posZ = (int) player.posZ - (k + 2);
							
							WyHelper.placeBlockIfAllowed(player.world, posX, posY, posZ, ModMiscBlocks.sunaSand, "core");
						}
					}
					else if(WyHelper.get4Directions(player) == WyHelper.Direction.SOUTH)
					{
						for(int i = -4; i < 6; i++)
						for(int j = 0; j < 5; j++)
						for(int k = 0; k < 30; k++)		
						{
							int posX = (int) player.posX + i;
							int posY = (int) player.posY - (j + 1);
							int posZ = (int) player.posZ + (k + 2);
							
							WyHelper.placeBlockIfAllowed(player.world, posX, posY, posZ, ModMiscBlocks.sunaSand, "core");
						}
					}
					else if(WyHelper.get4Directions(player) == WyHelper.Direction.EAST)
					{
						for(int i = 0; i < 30; i++)
						for(int j = 0; j < 5; j++)
						for(int k = -4; k < 6; k++)		
						{
							int posX = (int) player.posX + (i + 2);
							int posY = (int) player.posY - (j + 1);
							int posZ = (int) player.posZ + k;
							
							WyHelper.placeBlockIfAllowed(player.world, posX, posY, posZ, ModMiscBlocks.sunaSand, "core");
						}
					}
					else if(WyHelper.get4Directions(player) == WyHelper.Direction.WEST)
					{
						for(int i = 0; i < 30; i++)
						for(int j = 0; j < 5; j++)
						for(int k = -4; k < 6; k++)
						{
							int posX = (int) player.posX - (i + 2);
							int posY = (int) player.posY - (j + 1);
							int posZ = (int) player.posZ + k;
							
							WyHelper.placeBlockIfAllowed(player.world, posX, posY, posZ, ModMiscBlocks.sunaSand, "core");
						}	
					}
					
					ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_DESERTSPADA, player), player);			

				}
				super.use(player);
			}
		}
	}
	
}
