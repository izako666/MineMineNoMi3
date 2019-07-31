package xyz.pixelatedw.mineminenomi.abilities;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectile;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.YamiProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModExtraAttributes;
import xyz.pixelatedw.mineminenomi.init.ModMiscBlocks;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class YamiAbilities 
{

	static
	{
		Values.abilityWebAppExtraParams.put("darkmatter", new String[] {"desc", "Launches a ball of darkness that engulfs the opponent."});
		Values.abilityWebAppExtraParams.put("kurouzu", new String[] {"desc", "Creates a strong gravitational force, that pulls the opponent towards the user."});
		Values.abilityWebAppExtraParams.put("blackhole", new String[] {"desc", "The user spreads darkness over the target area, which engulfs anyone and anything inside of it."});
		Values.abilityWebAppExtraParams.put("liberation", new String[] {"desc", "The user expells everything sucked up by the 'Black Hole' at the target location."});
		Values.abilityWebAppExtraParams.put("blackworld", new String[] {"desc", "The user spreads darkness to the surroundings, blinding enemies and creating pillars made of Darkness."});
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new Kurouzu(), new DarkMatter(), new Liberation(), new BlackHole(), new BlackWorld()};	
	
	public static class BlackWorld extends Ability
	{
		public BlackWorld() 
		{
			super(ModAttributes.BLACK_WORLD); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			if(!this.isOnCooldown)
			{
				ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_BLACKWORLD, player), player);

				if(CommonConfig.instance.getGriefing())
				{	
					for(int i = 0; i < 8; i++)
					{
						int a1 = player.getRNG().nextInt(20) - 10;
						int a2 = player.getRNG().nextInt(20) - 10;
						
						WyHelper.placeBlockIfAllowed(player.world, (int)player.posX + a1, (int)player.posY , (int)player.posZ + a2, ModMiscBlocks.darkness, "core", "foliage", "liquid", "air");		
						WyHelper.placeBlockIfAllowed(player.world, (int)player.posX + a1, (int)player.posY + 1, (int)player.posZ + a2, ModMiscBlocks.darkness, "core", "foliage", "liquid", "air");		
						WyHelper.placeBlockIfAllowed(player.world, (int)player.posX + a1, (int)player.posY + 2, (int)player.posZ + a2, ModMiscBlocks.darkness, "core", "foliage", "liquid", "air");	
						WyHelper.placeBlockIfAllowed(player.world, (int)player.posX + a1, (int)player.posY + 3, (int)player.posZ + a2, ModMiscBlocks.darkness, "core", "foliage", "liquid", "air");	
						WyHelper.placeBlockIfAllowed(player.world, (int)player.posX + a1, (int)player.posY + 4, (int)player.posZ + a2, ModMiscBlocks.darkness, "core", "foliage", "liquid", "air");
						
						WyHelper.placeBlockIfAllowed(player.world, (int)player.posX + a1 + 1, (int)player.posY, (int)player.posZ + a2, ModMiscBlocks.darkness, "core", "foliage", "liquid", "air");		
						WyHelper.placeBlockIfAllowed(player.world, (int)player.posX + a1 + 1, (int)player.posY + 1, (int)player.posZ + a2, ModMiscBlocks.darkness, "core", "foliage", "liquid", "air");		
						WyHelper.placeBlockIfAllowed(player.world, (int)player.posX + a1 + 1, (int)player.posY + 2, (int)player.posZ + a2, ModMiscBlocks.darkness, "core", "foliage", "liquid", "air");	
						WyHelper.placeBlockIfAllowed(player.world, (int)player.posX + a1 + 1, (int)player.posY + 3, (int)player.posZ + a2, ModMiscBlocks.darkness, "core", "foliage", "liquid", "air");	
						WyHelper.placeBlockIfAllowed(player.world, (int)player.posX + a1 + 1, (int)player.posY + 4, (int)player.posZ + a2, ModMiscBlocks.darkness, "core", "foliage", "liquid", "air");	
						
						WyHelper.placeBlockIfAllowed(player.world, (int)player.posX + a1, (int)player.posY, (int)player.posZ + a2 + 1, ModMiscBlocks.darkness, "core", "foliage", "liquid", "air");		
						WyHelper.placeBlockIfAllowed(player.world, (int)player.posX + a1, (int)player.posY + 1, (int)player.posZ + a2 + 1, ModMiscBlocks.darkness, "core", "foliage", "liquid", "air");		
						WyHelper.placeBlockIfAllowed(player.world, (int)player.posX + a1, (int)player.posY + 2, (int)player.posZ + a2 + 1, ModMiscBlocks.darkness, "core", "foliage", "liquid", "air");	
						WyHelper.placeBlockIfAllowed(player.world, (int)player.posX + a1, (int)player.posY + 3, (int)player.posZ + a2 + 1, ModMiscBlocks.darkness, "core", "foliage", "liquid", "air");	
						WyHelper.placeBlockIfAllowed(player.world, (int)player.posX + a1, (int)player.posY + 4, (int)player.posZ + a2 + 1, ModMiscBlocks.darkness, "core", "foliage", "liquid", "air");	
	
						WyHelper.placeBlockIfAllowed(player.world, (int)player.posX + a1 + 1, (int)player.posY, (int)player.posZ + a2 + 1, ModMiscBlocks.darkness, "core", "foliage", "liquid", "air");		
						WyHelper.placeBlockIfAllowed(player.world, (int)player.posX + a1 + 1, (int)player.posY + 1, (int)player.posZ + a2 + 1, ModMiscBlocks.darkness, "core", "foliage", "liquid", "air");		
						WyHelper.placeBlockIfAllowed(player.world, (int)player.posX + a1 + 1, (int)player.posY + 2, (int)player.posZ + a2 + 1, ModMiscBlocks.darkness, "core", "foliage", "liquid", "air");	
						WyHelper.placeBlockIfAllowed(player.world, (int)player.posX + a1 + 1, (int)player.posY + 3, (int)player.posZ + a2 + 1, ModMiscBlocks.darkness, "core", "foliage", "liquid", "air");	
						WyHelper.placeBlockIfAllowed(player.world, (int)player.posX + a1 + 1, (int)player.posY + 4, (int)player.posZ + a2 + 1, ModMiscBlocks.darkness, "core", "foliage", "liquid", "air");	
	
					}
				}
				
				super.use(player);
			}
		}
	}
	
	public static class BlackHole extends Ability
	{
		public BlackHole() 
		{
			super(ModAttributes.BLACK_HOLE); 
		}
		
		@Override
		public void use(final PlayerEntity player)
		{				
			if(!this.isOnCooldown)
			{
				if(CommonConfig.instance.getGriefing())
				{
					WyHelper.createFilledSphere(player.world, (int)player.posX, (int)player.posY, (int)player.posZ, 10, ModMiscBlocks.darkness, "core", "foliage", "liquids", "ores");
					ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_BLACKHOLE, player), player);
				}
				
				super.use(player);
			}		
		}
	}
	
	public static class Liberation extends Ability
	{
		public int liberationCount = 0;
		
		public Liberation() 
		{
			super(ModAttributes.LIBERATION); 
		}
		
		/** TODO Explosion particles */
		@Override
		public void use(final PlayerEntity player)
		{
			int libCount = 0;
			
			if(!this.isOnCooldown)
			{
				if(liberationCount > 0)
				{
					RayTraceResult mop = WyHelper.rayTraceBlocks(player);	
					
					if(mop != null)
					{
						double x = mop.getHitVec().x;
						double y = mop.getHitVec().y;
						double z = mop.getHitVec().z;

						while(liberationCount > 0)
						{
							AbilityProjectile proj = new YamiProjectiles.Liberation(player.world, player, ModExtraAttributes.LIBERATION_BLOCK);	
							proj.setLocationAndAngles(x + WyMathHelper.randomWithRange(-3, 3), (y + 14) + WyMathHelper.randomWithRange(0, 4), z + WyMathHelper.randomWithRange(-3, 3), 0, 0);
							proj.setMotion(0, -0.7 - player.world.rand.nextDouble(), 0);
							player.world.addEntity(proj);
							liberationCount--;	
						}					
					}
				}
				else
				{
					for(int x = -40; x < 40; x++)
					for(int y = -40; y < 40; y++)
					for(int z = -40; z < 40; z++)
					{
						if( player.world.getBlockState(new BlockPos(player.posX + x, player.posY + y, player.posZ + z)).getBlock() == ModMiscBlocks.darkness)
						{
							player.world.setBlockState(new BlockPos(player.posX + x, player.posY + y, player.posZ + z), Blocks.AIR.getDefaultState());
							libCount++;
							if(libCount % 10 == 0)
								liberationCount++;
						}
					}
				}
				
				super.use(player);
			}		
		}
	}
	
	public static class Kurouzu extends Ability
	{
		private List<LivingEntity> entities = new ArrayList<LivingEntity>();
		
		public Kurouzu() 
		{
			super(ModAttributes.KUROUZU); 
		}
		
		@Override
		public void startCharging(PlayerEntity player)
		{
			super.startCharging(player);				
		}
		
		@Override
		public void duringCharging(PlayerEntity player, int currentCharge)
		{
			RayTraceResult mop = WyHelper.rayTraceBlocks(player);	
			
			this.entities.clear();
			if(mop != null)
			{
				double i = mop.getHitVec().x;
				double j = mop.getHitVec().y;
				double k = mop.getHitVec().z;

				ModNetwork.sendToAllAround(new PacketParticles(ID.PARTICLEFX_KOROUZU, i, j, k), player);
				
				for(LivingEntity target : WyHelper.getEntitiesNear(new BlockPos(i, j, k), player.world, 5))
				{
					this.entities.add(target);
				}
			}
		}
		
		@Override
		public void endCharging(PlayerEntity player)
		{
			if(this.entities.contains(player))
				this.entities.remove(player);
			for(LivingEntity target : this.entities)
			{
				target.setPositionAndUpdate(player.posX, player.posY, player.posZ);
				target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 5));
				target.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 100, 5));
				target.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 100, 5));
				if(target instanceof PlayerEntity)
				{
					PlayerEntity playerTarget = (PlayerEntity)target;
					//ModNetwork.sendTo(new PacketAbilityReset(false), (ServerPlayerEntity) playerTarget);
				}
			}
			super.endCharging(player);
		}
	}
	
	public static class DarkMatter extends Ability
	{
		public DarkMatter() 
		{
			super(ModAttributes.DARK_MATTER);
		}
		
		@Override
		public void use(final PlayerEntity player)
		{				
			this.projectile = new YamiProjectiles.DarkMatter(player.world, player, attr);
			attr.setAbilityCooldown(0);
			super.use(player);
		}
	}
	
}
