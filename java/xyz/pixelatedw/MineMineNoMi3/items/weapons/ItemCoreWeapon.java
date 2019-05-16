package xyz.pixelatedw.MineMineNoMi3.items.weapons;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;

public class ItemCoreWeapon extends Item
{
	private double damage = 1;
	private double multiplier = 1;
	private boolean canUseSpecial = false;
	protected boolean isPoisonous = false, isFireAspect = false, isSlownessInducing = false, isStackable = false;
	protected int poisonTimer = 100, fireAspectTimer = 100, slownessTimer = 100;
	
	private IIcon baseIcon, sheathedIcon, hakiImbuedIcon;

	public ItemCoreWeapon(int damage)
	{
		this.damage = damage;
		this.maxStackSize = 1;
		this.setFull3D();
	}

	public ItemCoreWeapon(int damage, boolean canUseSpecial)
	{
		this.damage = damage;
		this.canUseSpecial = canUseSpecial;
		this.maxStackSize = 1;
		this.setFull3D();
	}
	
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5) 
	{
		if(!itemStack.hasTagCompound())
		{
			itemStack.setTagCompound(new NBTTagCompound());
			itemStack.getTagCompound().setInteger("metadata", 0); // 0 - normal, 1 - sheathed, 2 - haki imbued
			itemStack.getTagCompound().setDouble("multiplier", 1);
		}
		
		if(!world.isRemote)
		{
			ExtendedEntityData props = ExtendedEntityData.get((EntityLivingBase) entity);
			
			double multiplier = 1;
			
			if(props.hasBusoHakiActive())
				multiplier += 0.5;
			
			if(props.isSwordsman())
				multiplier += 0.25;
			
			itemStack.getTagCompound().setDouble("multiplier", multiplier);
		}
	}
	
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        return itemStack;
    }
	
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 72000;
    }

    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.block;
    }
	
    public int getItemEnchantability()
    {
        return 22;
    }

	public ItemCoreWeapon setMaxDamage(int maxDamage)
	{
		super.setMaxDamage(maxDamage);
		return this;
	}

	public ItemCoreWeapon setQuality()
	{
		return this;
	}

	public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase attacker)
	{
		ExtendedEntityData props = ExtendedEntityData.get(attacker);

		if(!props.hasBusoHakiActive())
			itemStack.damageItem(1, attacker);
		
		if(isPoisonous)
			target.addPotionEffect(new PotionEffect(Potion.poison.id, this.poisonTimer, 0));
		
		if(isFireAspect)
			target.setFire(this.fireAspectTimer);
		
		if(isSlownessInducing)
		{
			if(isStackable)
			{
				if(target.isPotionActive(Potion.moveSlowdown))
				{
					int timer = target.getActivePotionEffect(Potion.moveSlowdown).getDuration();				
					target.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, timer + this.slownessTimer, 0));
				}
				else
					target.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, this.slownessTimer, 0));
			}
			else
				target.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, this.slownessTimer, 0));
		}
		
		return true;
	}

	public ItemCoreWeapon setIsPoisonous()
	{
		this.isPoisonous = true;
		this.poisonTimer = 100;
		return this;
	}
	
	public ItemCoreWeapon setIsPoisonous(int timer)
	{
		this.isPoisonous = true;
		this.poisonTimer = timer;
		return this;
	}
	
	public ItemCoreWeapon setIsFireAspect()
	{
		this.isFireAspect = true;
		return this;
	}
	
	public ItemCoreWeapon setIsFireAspect(int timer)
	{
		this.isFireAspect = true;
		this.fireAspectTimer = timer;
		return this;
	}
	
	public ItemCoreWeapon setIsSlownessInducing()
	{
		this.isSlownessInducing = true;
		return this;
	}
	
	public ItemCoreWeapon setIsSlownessInducing(int timer)
	{
		this.isSlownessInducing = true;
		this.slownessTimer = timer;
		return this;
	}
	
	public ItemCoreWeapon setIsSlownessInducing(int timer, boolean isStackable)
	{
		this.isSlownessInducing = true;
		this.slownessTimer = timer;
		this.isStackable = isStackable;
		return this;
	}
		
	public Multimap getAttributeModifiers(ItemStack stack)
	{
		Multimap multimap = super.getAttributeModifiers(stack);
		double multiplier;
		if( stack.getTagCompound() != null)
			multiplier = stack.getTagCompound().getDouble("multiplier");
		else
			multiplier = 1;
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", this.damage * multiplier, 0));
		return multimap;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.itemIcon = ir.registerIcon(ID.PROJECT_ID + ":" + this.getUnlocalizedName().substring(5));
		
		try
		{ Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(ID.PROJECT_ID + ":textures/items/" + this.getUnlocalizedName().substring(5) + "_1.png")); }
		catch(Exception e)
		{ this.sheathedIcon = this.itemIcon; }
		
		try
		{ Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(ID.PROJECT_ID + ":textures/items/" + this.getUnlocalizedName().substring(5) + "_haki.png")); }
		catch(Exception e)
		{ this.hakiImbuedIcon = this.itemIcon; }
		
		if(this.sheathedIcon != this.itemIcon)
			this.sheathedIcon = ir.registerIcon(ID.PROJECT_ID + ":" + this.getUnlocalizedName().substring(5) + "_1");
		if(this.hakiImbuedIcon != this.itemIcon)
			this.hakiImbuedIcon = ir.registerIcon(ID.PROJECT_ID + ":" + this.getUnlocalizedName().substring(5) + "_haki");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{	
		ExtendedEntityData props = ExtendedEntityData.get(player);

		if(stack.getTagCompound() != null)
    	{
			if (player.getHeldItem() != null && player.getHeldItem().equals(stack))
			{
				if(props.hasBusoHakiActive())
				{
					stack.getTagCompound().setInteger("metadata", 2);
					return this.hakiImbuedIcon;
				}
				
				stack.getTagCompound().setInteger("metadata", 0);
				return this.itemIcon;
			}
			else
			{
				stack.getTagCompound().setInteger("metadata", 1);
				return this.sheathedIcon;
			}
    	}

		return this.itemIcon;
	}
	
    @SideOnly(Side.CLIENT)
    public boolean isFull3D() 
    {
    	return true;
    }
	
    @SideOnly(Side.CLIENT)
    public IIcon getIconIndex(ItemStack stack)
    {
    	if(stack.getTagCompound() != null)
    	{
			if(stack.getTagCompound().getInteger("metadata") == 1)
				return this.sheathedIcon;
			else if(stack.getTagCompound().getInteger("metadata") == 2)
				return this.hakiImbuedIcon;
			else
				return itemIcon;
    	}
    	
    	return itemIcon;
	}
    
    public float func_150893_a(ItemStack itemStack, Block block)
    {
        if (block == Blocks.web)
        {
            return 25.0F;
        }
        else
        {
            Material material = block.getMaterial();
            return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.gourd ? 1.0F : 1.5F;
        }
    }
}
