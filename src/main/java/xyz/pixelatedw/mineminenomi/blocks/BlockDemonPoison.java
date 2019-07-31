package xyz.pixelatedw.mineminenomi.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;

public class BlockDemonPoison extends BlockPoison
{

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
	   	if(entity instanceof LivingEntity)
    	{
    		IDevilFruit props = DevilFruitCapability.get((LivingEntity) entity);
    		
    		if(!props.getDevilFruit().equals("dokudoku"))
    		{
    			if(!((LivingEntity)entity).isPotionActive(Effects.POISON))
    			{
    				((LivingEntity)entity).addPotionEffect(new EffectInstance(Effects.POISON, 300, 2));
    			}
    		}
    		else
    		{
    			if(!((LivingEntity)entity).isPotionActive(Effects.REGENERATION))
    			{
    				((LivingEntity)entity).addPotionEffect(new EffectInstance(Effects.REGENERATION, 50, 1));
    			}
    		}
    	}
	}
	
}
