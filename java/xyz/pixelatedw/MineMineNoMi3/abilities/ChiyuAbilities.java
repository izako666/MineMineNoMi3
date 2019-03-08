package xyz.pixelatedw.MineMineNoMi3.abilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

public class ChiyuAbilities
{

	public static Ability[] abilitiesArray = new Ability[] {new HealingTouch(), new Chiyupopo()};

	public static class Chiyupopo extends Ability
	{
		public Chiyupopo() 
		{
			super(ListAttributes.CHIYUPOPO); 
		}	

		public void use(EntityPlayer player) 
		{
			
			for(EntityLivingBase entity : WyHelper.getEntitiesNear(player, 20))
			{
				entity.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 0));
			}
			
			super.use(player);
		}
	}
	
	public static class HealingTouch extends Ability
	{
		public HealingTouch() 
		{
			super(ListAttributes.HEALINGTOUCH); 
		}	

		public void hitEntity(EntityPlayer player, EntityLivingBase target) 
		{
			target.setHealth(target.getHealth() + 20);
			target.addPotionEffect(new PotionEffect(Potion.regeneration.id, 400, 1));

			passiveActive = false;
			startCooldown();
			this.startExtUpdate(player);
		}
	}
	
}
