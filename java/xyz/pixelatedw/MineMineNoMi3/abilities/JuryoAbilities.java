package xyz.pixelatedw.MineMineNoMi3.abilities;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.abilities.MokuAbilities.WhiteLauncher;
import xyz.pixelatedw.MineMineNoMi3.abilities.MokuAbilities.WhiteOut;
import xyz.pixelatedw.MineMineNoMi3.abilities.MokuAbilities.WhiteSnake;
import xyz.pixelatedw.MineMineNoMi3.abilities.MokuAbilities.WhiteStrike;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.GoroProjectiles;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.JuryoProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketParticles;

public class JuryoAbilities
{
	public static Ability[] abilitiesArray = new Ability[] {new SagariNoRyusei(), new Juryoku()};
	
	
	public static class Juryoku extends Ability
	{
		public Juryoku() 
		{
			super(ListAttributes.JURYOKU); 
		}

		public void duringPassive(EntityPlayer player, int passiveTimer) 
		{
			if(passiveTimer > 400)
			{
				this.setPassiveActive(false);
				this.startCooldown();
				this.startExtUpdate(player);
			}
			
			for(EntityLivingBase entity : WyHelper.getEntitiesNear(player, 10))
			{
				entity.motionX = 0;
				entity.motionZ = 0;
				entity.motionY -= 5;
				entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 10));
				
				if(++passiveTimer % 100 == 0)
				{
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 8);	
					if(MainConfig.enableGriefing)
					{
						for(int x = -2; x < 2; x++)
						for(int z = -2; z < 2; z++)
						{
							int posX = (int)entity.posX + x;
							int posY = (int)entity.posY - 1;
							int posZ = (int)entity.posZ + z;
							
							player.worldObj.getBlock(posX, posY, posZ).dropBlockAsItem(player.worldObj, posX, posY, posZ, 0, 0);
							player.worldObj.setBlock(posX, posY, posZ, Blocks.air);
						}
					}
				}
			}	
		}
		
		public void endPassive(EntityPlayer player) 
		{
			this.startCooldown();
			this.startExtUpdate(player);
		}

	}
	
	public static class SagariNoRyusei extends Ability
	{
		public SagariNoRyusei() 
		{
			super(ListAttributes.SAGARINORYUSEI); 
		}
		
		public void use(EntityPlayer player)
		{	
			if(!this.isOnCooldown)		
			{
				MovingObjectPosition mop = WyHelper.rayTraceBlocks(player);	
				
				if(mop != null)
				{
					double x = mop.blockX;
					double y = mop.blockY;
					double z = mop.blockZ;

					AbilityProjectile proj = new JuryoProjectiles.SagariNoRyusei(player.worldObj, player, ListAttributes.SAGARINORYUSEI);	
					proj.setLocationAndAngles(x, (y + 90), z, 0, 0);
					proj.motionX = 0;
					proj.motionZ = 0;
					proj.motionY = -2.4;
					player.worldObj.spawnEntityInWorld(proj);
				}
			}
			super.use(player);
		}
	}
}
