package xyz.pixelatedw.mineminenomi.items.weapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;

public class ItemAbilityWeapon extends ItemCoreWeapon
{

	public ItemAbilityWeapon(int damage)
	{
		super(new Properties().defaultMaxDamage(10), damage);
	}

	@Override
	public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		super.inventoryTick(itemStack, world, entity, itemSlot, isSelected);
		if(entity instanceof PlayerEntity)
		{
			PlayerEntity owner = (PlayerEntity) entity;		
			IDevilFruit devilFruitProps = DevilFruitCapability.get(owner);
			IAbilityData abilityDataProps = AbilityDataCapability.get(owner);

			if(devilFruitProps.getDevilFruit().equals("hiehie") || devilFruitProps.getDevilFruit().equals("pikapika") || devilFruitProps.getDevilFruit().equals("noronoro") 
					|| devilFruitProps.getDevilFruit().equals("dorudoru") || devilFruitProps.getDevilFruit().equals("gasugasu") || devilFruitProps.getDevilFruit().equals("yukiyuki"))
			{
				for(int i = 0; i < abilityDataProps.countAbilitiesInHotbar(); i++)
				{
					if(abilityDataProps.getHotbarAbilityFromSlot(i) != null && abilityDataProps.getHotbarAbilityFromSlot(i).getAttribute().isPassive())
					{
						if(!abilityDataProps.getHotbarAbilityFromSlot(i).isPassiveActive())
						{
							String ablName = WyHelper.getFancyName(abilityDataProps.getHotbarAbilityFromSlot(i).getAttribute().getAttributeName());
							if(ablName.equals(WyHelper.getFancyName(ModAttributes.ICE_SABER.getAttributeName())))
								WyHelper.removeStackFromInventory(owner, itemStack);
							else if(ablName.equals(WyHelper.getFancyName(ModAttributes.AMA_NO_MURAKUMO.getAttributeName())))
								WyHelper.removeStackFromInventory(owner, itemStack);
							else if(ablName.equals(WyHelper.getFancyName(ModAttributes.NORO_NORO_BEAM_SWORD.getAttributeName())))
								WyHelper.removeStackFromInventory(owner, itemStack);
							else if(ablName.equals(WyHelper.getFancyName(ModAttributes.DORU_DORU_ARTS_KEN.getAttributeName())))
								WyHelper.removeStackFromInventory(owner, itemStack);
							else if(ablName.equals(WyHelper.getFancyName(ModAttributes.BLUE_SWORD.getAttributeName())))
								WyHelper.removeStackFromInventory(owner, itemStack);
							else if(ablName.equals(WyHelper.getFancyName(ModAttributes.TABIRA_YUKI.getAttributeName())))
								WyHelper.removeStackFromInventory(owner, itemStack);
						}
					}
				}
			}
			else
				WyHelper.removeStackFromInventory(owner, itemStack);
		}
	}

	@Override
	public ItemAbilityWeapon setIsPoisonous()
	{
		this.isPoisonous = true;
		this.poisonTimer = 100;
		return this;
	}

	@Override
	public ItemAbilityWeapon setIsPoisonous(int timer)
	{
		this.isPoisonous = true;
		this.poisonTimer = timer;
		return this;
	}

	@Override
	public ItemAbilityWeapon setIsFireAspect()
	{
		this.isFireAspect = true;
		return this;
	}

	@Override
	public ItemAbilityWeapon setIsFireAspect(int timer)
	{
		this.isFireAspect = true;
		this.fireAspectTimer = timer;
		return this;
	}

	@Override
	public ItemAbilityWeapon setIsSlownessInducing()
	{
		this.isSlownessInducing = true;
		return this;
	}

	@Override
	public ItemAbilityWeapon setIsSlownessInducing(int timer)
	{
		this.isSlownessInducing = true;
		this.slownessTimer = timer;
		return this;
	}

	@Override
	public ItemAbilityWeapon setIsSlownessInducing(int timer, boolean isStackable)
	{
		this.isSlownessInducing = true;
		this.slownessTimer = timer;
		this.isStackable = isStackable;
		return this;
	}

	@Override
	public boolean isEnchantable(ItemStack stack)
	{
		return false;
	}
}
