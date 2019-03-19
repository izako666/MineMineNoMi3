package xyz.pixelatedw.MineMineNoMi3.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.EnumFruitType;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.lists.ListDevilFruits;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class AkumaNoMiBox extends Item
{
	
	private int tier;
	/*private AkumaNoMi[] tier1Fruits = new AkumaNoMi[] 
			{ListDevilFruits.BaneBaneNoMi, ListDevilFruits.SukeSukeNoMi, ListDevilFruits.NoroNoroNoMi, ListDevilFruits.BomuBomuNoMi, ListDevilFruits.DoruDoruNoMi, ListDevilFruits.BariBariNoMi, 
			 ListDevilFruits.HoroHoroNoMi, ListDevilFruits.GoeGoeNoMi, ListDevilFruits.KiloKiloNoMi};
	private AkumaNoMi[] tier2Fruits = new AkumaNoMi[] 
			{ListDevilFruits.GomuGomuNoMi, ListDevilFruits.OpeOpeNoMi, ListDevilFruits.NikyuNikyuNoMi, ListDevilFruits.KageKageNoMi,
			 ListDevilFruits.DokuDokuNoMi, ListDevilFruits.ItoItoNoMi, ListDevilFruits.UshiUshiNoMiBison};
	private AkumaNoMi[] tier3Fruits = new AkumaNoMi[] 
			{ListDevilFruits.MeraMeraNoMi, ListDevilFruits.HieHieNoMi, ListDevilFruits.PikaPikaNoMi, ListDevilFruits.GoroGoroNoMi, ListDevilFruits.SunaSunaNoMi, ListDevilFruits.MaguMaguNoMi, 
			 ListDevilFruits.GasuGasuNoMi, ListDevilFruits.MokuMokuNoMi, ListDevilFruits.YukiYukiNoMi, ListDevilFruits.YamiYamiNoMi, ListDevilFruits.ToriToriNoMiPhoenix, ListDevilFruits.GuraGuraNoMi};*/
	
	private List<AkumaNoMi> tier1Fruits = new ArrayList<AkumaNoMi>();
	private List<AkumaNoMi> tier2Fruits = new ArrayList<AkumaNoMi>();
	private List<AkumaNoMi> tier3Fruits = new ArrayList<AkumaNoMi>();
	
	public AkumaNoMiBox(int tier)
	{
		this.tier = tier;
		
		for(AkumaNoMi df : Values.devilfruits)
		{
			double score = 0;
			
			double typeModifier = 1;
			if(df.getType() == EnumFruitType.PARAMECIA)
				typeModifier = 0.5;
			else if(df.getType() == EnumFruitType.LOGIA)
				typeModifier = 1.4;
			else if(df.getType() == EnumFruitType.ZOAN)
				typeModifier = 1.1;
			else if(df.getType() == EnumFruitType.MYTHICALZOAN || df.getType() == EnumFruitType.ANCIENTZOAN)
				typeModifier = 1.5;
			
			double totalDamage = 0;
			double totalCooldown = 0;
			double totalPower = 0;
			for(Ability a : df.abilities)
			{
				AbilityAttribute attr = a.getAttribute();
				
				totalCooldown += attr.getAbilityCooldown() - attr.getAbilityCharges();
				totalDamage += attr.getAbilityExplosionPower() + attr.getProjectileDamage() + attr.getProjectileExplosionPower();
				
				totalPower += (totalCooldown + totalDamage) / 2;
			}
			
			totalPower *= typeModifier;
			
			if (df.getType() == EnumFruitType.ANCIENTZOAN || df.getType() == EnumFruitType.MYTHICALZOAN || df.getUnlocalizedName().equalsIgnoreCase("item.guraguranomi"))
			{
				this.tier3Fruits.add(df);
			}
			else if(df.getType() == EnumFruitType.PARAMECIA)
			{
				if(totalPower < 500)
					this.tier1Fruits.add(df);
				else
					this.tier2Fruits.add(df);
			}
			else
			{
				if(totalPower < 800)
					this.tier2Fruits.add(df);
				else
					this.tier3Fruits.add(df);
			}
		}
		
	}

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if(!world.isRemote)
		{			
			for(int i = 0; i < player.inventory.mainInventory.length; i++)
			{
				if(player.inventory.getStackInSlot(i) != null && player.inventory.getStackInSlot(i).getItem() == ListMisc.Key)
				{
					player.inventory.decrStackSize(i, 1);
					WyHelper.removeStackFromInventory(player, itemStack);	
					return new ItemStack(roulette());
				}				
			}	

			WyHelper.sendMsgToPlayer(player, "You need a key !");
		}

		return itemStack;
	}
	
	
	private AkumaNoMi roulette()
	{
		Random rand = new Random();
		
		if(rand.nextInt(100) + rand.nextDouble() <= 98)
		{
			if(tier == 1)
			{
				if(rand.nextInt(100) + rand.nextDouble() < 10)
					return tier2Fruits.get(rand.nextInt(tier2Fruits.size()));				
				else
					return tier1Fruits.get(rand.nextInt(tier1Fruits.size()));
			}
			else if(tier == 2)
			{
				if(rand.nextInt(100) + rand.nextDouble() < 10)
					return tier3Fruits.get(rand.nextInt(tier3Fruits.size()));				
				else
					return tier2Fruits.get(rand.nextInt(tier2Fruits.size()));
			}
			else if(tier == 3)
			{
				return tier3Fruits.get(rand.nextInt(tier3Fruits.size()));
			}
		}
		
		return null;
	}

}
