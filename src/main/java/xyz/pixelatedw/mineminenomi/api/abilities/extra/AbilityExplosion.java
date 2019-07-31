package xyz.pixelatedw.mineminenomi.api.abilities.extra;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.PacketParticles;

public class AbilityExplosion extends Explosion
{

	private World world;
	private Entity exploder;
	private double explosionX;
	private double explosionY;
	private double explosionZ;
	private float explosionSize;
	private String smokeParticles = ID.PARTICLEFX_COMMONEXPLOSION;

	public List<BlockPos> affectedBlockPositions = new ArrayList<BlockPos>();
	private final Map<PlayerEntity, Vec3d> playerKnockbackMap = Maps.newHashMap();
	private final Random random = new Random();

	private boolean canStartFireAfterExplosion = false;
	private boolean canDestroyBlocks = true;
	private boolean canDropBlocksAfterExplosion = false;
	private boolean canDamageEntities = true;
	private boolean canDamageOwner = true;
	private boolean canProduceExplosionSound = true;

	public AbilityExplosion(Entity entity, double posX, double posY, double posZ, float power)
	{
		super(entity.world, entity, power, power, power, power, false, Mode.DESTROY);
		this.world = entity.world;
		this.exploder = entity;
		this.explosionSize = power;
		this.explosionX = posX;
		this.explosionY = posY;
		this.explosionZ = posZ;
	}

	public void setDamageOwner(boolean damageOwner)
	{
		this.canDamageOwner = damageOwner;
	}

	public void setDamageEntities(boolean damageEntities)
	{
		this.canDamageEntities = damageEntities;
	}

	public void setDropBlocksAfterExplosion(boolean canDrop)
	{
		this.canDropBlocksAfterExplosion = canDrop;
	}

	public void setFireAfterExplosion(boolean hasFire)
	{
		this.canStartFireAfterExplosion = hasFire;
	}

	public void setDestroyBlocks(boolean canDestroyBlocks)
	{
		this.canDestroyBlocks = canDestroyBlocks;
	}

	public void setSmokeParticles(String particle)
	{
		this.smokeParticles = particle;
	}

	public boolean hasSmokeParticles()
	{
		return !WyHelper.isNullOrEmpty(this.smokeParticles);
	}

	public void setExplosionSound(boolean hasSound)
	{
		this.canProduceExplosionSound = hasSound;
	}

	public void doExplosion()
	{

		Set<BlockPos> set = Sets.newHashSet();
		int i = 16;

		for (int j = 0; j < 16; ++j)
		{
			for (int k = 0; k < 16; ++k)
			{
				for (int l = 0; l < 16; ++l)
				{
					if (j == 0 || j == 15 || k == 0 || k == 15 || l == 0 || l == 15)
					{
						double d0 = j / 15.0F * 2.0F - 1.0F;
						double d1 = k / 15.0F * 2.0F - 1.0F;
						double d2 = l / 15.0F * 2.0F - 1.0F;
						double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
						d0 = d0 / d3;
						d1 = d1 / d3;
						d2 = d2 / d3;
						float f = this.explosionSize * (0.7F + this.world.rand.nextFloat() * 0.6F);
						double d4 = this.explosionX;
						double d6 = this.explosionY;
						double d8 = this.explosionZ;

						for (float f1 = 0.3F; f > 0.0F; f -= 0.22500001F)
						{
							BlockPos blockpos = new BlockPos(d4, d6, d8);
							BlockState BlockState = this.world.getBlockState(blockpos);
							IFluidState ifluidstate = this.world.getFluidState(blockpos);
							if (!BlockState.isAir(world, blockpos) || !ifluidstate.isEmpty())
							{
								float f2 = Math.max(BlockState.getExplosionResistance(world, blockpos, exploder, this), ifluidstate.getExplosionResistance(world, blockpos, exploder, this));
								if (this.exploder != null)
								{
									f2 = this.exploder.getExplosionResistance(this, this.world, blockpos, BlockState, ifluidstate, f2);
								}

								f -= (f2 + 0.3F) * 0.3F;
							}

							if (f > 0.0F && (this.exploder == null || this.exploder.canExplosionDestroyBlock(this, this.world, blockpos, BlockState, f)))
							{
								set.add(blockpos);
							}

							d4 += d0 * 0.3F;
							d6 += d1 * 0.3F;
							d8 += d2 * 0.3F;
						}
					}
				}
			}
		}

		this.affectedBlockPositions.addAll(set);
		float f3 = this.explosionSize * 2.0F;
		int k1 = MathHelper.floor(this.explosionX - f3 - 1.0D);
		int l1 = MathHelper.floor(this.explosionX + f3 + 1.0D);
		int i2 = MathHelper.floor(this.explosionY - f3 - 1.0D);
		int i1 = MathHelper.floor(this.explosionY + f3 + 1.0D);
		int j2 = MathHelper.floor(this.explosionZ - f3 - 1.0D);
		int j1 = MathHelper.floor(this.explosionZ + f3 + 1.0D);
		List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this.exploder, new AxisAlignedBB(k1, i2, j2, l1, i1, j1));
		net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(this.world, this, list, f3);
		Vec3d vec3d = new Vec3d(this.explosionX, this.explosionY, this.explosionZ);

		for (int k2 = 0; k2 < list.size(); ++k2)
		{
			Entity entity = list.get(k2);
			if (!entity.isImmuneToExplosions())
			{
				double d12 = entity.getDistanceSq(this.explosionX, this.explosionY, this.explosionZ) / f3;
				if (d12 <= 1.0D)
				{
					double d5 = entity.posX - this.explosionX;
					double d7 = entity.posY + entity.getEyeHeight() - this.explosionY;
					double d9 = entity.posZ - this.explosionZ;
					double d13 = MathHelper.sqrt(d5 * d5 + d7 * d7 + d9 * d9);
					if (d13 != 0.0D)
					{
						d5 = d5 / d13;
						d7 = d7 / d13;
						d9 = d9 / d13;
						double d14 = Explosion.func_222259_a(vec3d, entity);
						double d10 = (1.0D - d12) * d14;
						entity.attackEntityFrom(this.getDamageSource(), ((int) ((d10 * d10 + d10) / 2.0D * 7.0D * f3 + 1.0D)));
						double d11 = d10;
						if (entity instanceof LivingEntity)
						{
							d11 = ProtectionEnchantment.getBlastDamageReduction((LivingEntity) entity, d10);
						}

						entity.setMotion(entity.getMotion().add(d5 * d11, d7 * d11, d9 * d11));
						if (entity instanceof PlayerEntity)
						{
							PlayerEntity PlayerEntity = (PlayerEntity) entity;
							if (!PlayerEntity.isSpectator() && (!PlayerEntity.isCreative() || !PlayerEntity.abilities.isFlying))
							{
								this.playerKnockbackMap.put(PlayerEntity, new Vec3d(d5 * d10, d7 * d10, d9 * d10));
							}
						}
					}
				}
			}
		}

		if (this.canProduceExplosionSound)
			this.world.playSound((PlayerEntity) null, this.explosionX, this.explosionY, this.explosionZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);

		if (this.hasSmokeParticles())
			ModNetwork.sendToAllAround(new PacketParticles(this.smokeParticles, this.explosionX, this.explosionY, this.explosionZ), (ServerPlayerEntity) this.exploder);

		if (this.canDestroyBlocks && CommonConfig.instance.getGriefing())
		{
			for (BlockPos blockpos : this.affectedBlockPositions)
			{
				BlockState blockstate = this.world.getBlockState(blockpos);
				Block block = blockstate.getBlock();

				if (!blockstate.isAir(world, blockpos))
				{
					if (this.world instanceof ServerWorld && blockstate.canDropFromExplosion(this.world, blockpos, this))
					{
						TileEntity tileentity = blockstate.hasTileEntity() ? this.world.getTileEntity(blockpos) : null;
						LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld) this.world)).withRandom(this.world.rand).withParameter(LootParameters.POSITION, blockpos).withParameter(LootParameters.TOOL, ItemStack.EMPTY).withNullableParameter(LootParameters.BLOCK_ENTITY, tileentity);
						lootcontext$builder.withParameter(LootParameters.EXPLOSION_RADIUS, this.explosionSize);

						Block.spawnDrops(blockstate, lootcontext$builder);
					}

					blockstate.onBlockExploded(this.world, blockpos, this);
				}
			}
		}

		if (this.canStartFireAfterExplosion && CommonConfig.instance.getGriefing())
		{
			for (BlockPos blockpos1 : this.affectedBlockPositions)
			{
				if (this.world.getBlockState(blockpos1).isAir(world, blockpos1) && this.world.getBlockState(blockpos1.down()).isOpaqueCube(this.world, blockpos1.down()) && this.random.nextInt(3) == 0)
				{
					this.world.setBlockState(blockpos1, Blocks.FIRE.getDefaultState());
				}
			}
		}
	}

}
