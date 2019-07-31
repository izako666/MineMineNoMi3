package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.GasuProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;

public class GasuAbilities 
{

	static
	{
		Values.abilityWebAppExtraParams.put("karakuni", new String[] {"desc", "Removes the oxygen around the user, suffocating everyone in the vicinity."});
		Values.abilityWebAppExtraParams.put("gastanet", new String[] {"desc", "The user fills castanets with unstable gas, which causes an explosion when slammed together."});
		Values.abilityWebAppExtraParams.put("gastille", new String[] {"desc", "Shoots a beam of poisonous gas from the user's mouth, that explodes on impact."});		
		Values.abilityWebAppExtraParams.put("gasrobe", new String[] {"desc", "Launches a cloud of poison at the opponent."});
		Values.abilityWebAppExtraParams.put("bluesword", new String[] {"desc", "The user fills a hilt with lamable gas, them sets it on fire to create a sword."});
	}
	
	public static Ability[] abilitiesArray = new Ability[] {new GasRobe(), new BlueSword(), new Gastille(), new Gastanet(), new Karakuni()};	
	
	public static class BlueSword extends Ability
	{
		public BlueSword()
		{
			super(ModAttributes.BLUE_SWORD); 
		}
		
		@Override
		public void startPassive(PlayerEntity player) 
		{
			if(player.getHeldItemMainhand() == ItemStack.EMPTY || player.getHeldItemMainhand().getItem() == Items.AIR)
				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ModWeapons.blueSword));
			else
			{
				WyHelper.sendMsgToPlayer(player, "Cannot equip " + this.getAttribute().getAttributeName() + " while holding another item in hand !");
				this.passive(player);
			}
		}
		
		@Override
		public void endPassive(PlayerEntity player) 
		{
			WyHelper.removeStackFromInventory(player, new ItemStack(ModWeapons.blueSword));
		}
	}
	
	public static class Karakuni extends Ability
	{
		public Karakuni() 
		{
			super(ModAttributes.KARAKUNI); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{	
			if(!player.world.isRemote)
			{
				if(!isOnCooldown)
				{
					for(LivingEntity e : WyHelper.getEntitiesNear(player, 25))
					{
						e.attackEntityFrom(DamageSource.causePlayerDamage(player), 20);
						e.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 1000, 2));
					}
					
					super.use(player);
				}
			}
		} 
	}
	
	public static class Gastanet extends Ability
	{
		public Gastanet() 
		{
			super(ModAttributes.GASTANET); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{	
			super.use(player);
		} 
	}
	
	public static class Gastille extends Ability
	{
		public Gastille() 
		{
			super(ModAttributes.GASTILLE); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{	
			this.projectile = new GasuProjectiles.Gastille(player.world, player, attr);
			super.use(player);
		} 
	}
	
	public static class GasRobe extends Ability
	{
		public GasRobe() 
		{
			super(ModAttributes.GAS_ROBE); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{	
			this.projectile = new GasuProjectiles.GasRobe(player.world, player, attr);
			super.use(player);
		} 
	}
	
}
