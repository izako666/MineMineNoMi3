package xyz.pixelatedw.MineMineNoMi3.items;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import scala.actors.threadpool.Arrays;
import xyz.pixelatedw.MineMineNoMi3.EnumFruitType;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.abilities.FishKarateAbilities;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityManager;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityProperties;
import xyz.pixelatedw.MineMineNoMi3.api.network.PacketAbilitySync;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.helpers.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.lists.ListCreativeTabs;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;

public class AkumaNoMi extends ItemFood
{

	public EnumFruitType type;
	public Item ability1, ability2, ability3, ability4;
	public Ability[] abilities;

	public AkumaNoMi(EnumFruitType type, Ability... abilitiesArray)
	{
		super(0, false);
		this.maxStackSize = 1;
		this.type = type;
		this.abilities = abilitiesArray;
		this.setCreativeTab(ListCreativeTabs.tabDevilFruits);
		this.setAlwaysEdible();
	}

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		player.setItemInUse(itemStack, itemUseDuration);
		return itemStack;
	}

	public void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
	{
		ExtendedEntityData props = ExtendedEntityData.get(player);
		AbilityProperties abilityProps = AbilityProperties.get(player);

		String eatenFruit = this.getUnlocalizedName().substring(5).replace("nomi", "").replace(":", "").replace(",", "").replace("model", "");

		boolean flag1 = !props.getUsedFruit().equalsIgnoreCase("n/a") && !props.hasYamiPower() && !eatenFruit.equalsIgnoreCase("yamiyami");
		boolean flag2 = props.hasYamiPower() && (!eatenFruit.equalsIgnoreCase(props.getUsedFruit()) && !props.getUsedFruit().equalsIgnoreCase("yamidummy"));

		if (flag1 || flag2)
		{
			WyHelper.sendMsgToPlayer(player, "DAMAGE");
			player.attackEntityFrom(DamageSource.wither, Float.POSITIVE_INFINITY);
			return;
		}

		if (!eatenFruit.equalsIgnoreCase("yamiyami"))
			props.setUsedFruit(eatenFruit);

		if (this.type == EnumFruitType.LOGIA)
			props.setIsLogia(true);

		if (eatenFruit.equalsIgnoreCase("yamiyami"))
		{
			props.setIsLogia(false);
			props.setYamiPower(true);
			if (props.getUsedFruit().equalsIgnoreCase("n/a"))
				props.setUsedFruit("yamidummy");
		}

		if (eatenFruit.equalsIgnoreCase("hitohito") && !player.worldObj.isRemote)
		{
			WyHelper.sendMsgToPlayer(player, "You've gained some enlightenment");
			if (props.isFishman())
			{
				props.setRace(ID.RACE_HUMAN);
				
				for (int i = 0; i < 8; i++)
				{
					Ability abl = abilityProps.getAbilityFromSlot(i);
					for (Ability fshAbl : FishKarateAbilities.abilitiesArray)
					{
						if (abl != null && abl.getAttribute().getAttributeName().equalsIgnoreCase(fshAbl.getAttribute().getAttributeName()))
							abilityProps.setAbilityInSlot(i, null);
					}
				}
				
				DevilFruitsHelper.validateStyleMoves(player);
				DevilFruitsHelper.validateRacialMoves(player);
				WyNetworkHelper.sendTo(new PacketAbilitySync(abilityProps), (EntityPlayerMP) player);
			}
		}

		if(!props.getUsedFruit().equalsIgnoreCase("yomiyomi"))
			for(Ability a : abilities)
				if(!DevilFruitsHelper.verifyIfAbilityIsBanned(a) && !abilityProps.hasDevilFruitAbility(a))
					abilityProps.addDevilFruitAbility(a);
		
		WyNetworkHelper.sendToAll(new PacketSyncInfo(player.getDisplayName(), props));
		if (!ID.DEV_EARLYACCESS && !world.isRemote && !player.capabilities.isCreativeMode)
			WyTelemetry.addStat("eaten_" + itemStack.getDisplayName(), 1);

	}

	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
	{
		for (int i = 0; i < this.abilities.length; i++)
			if (!DevilFruitsHelper.verifyIfAbilityIsBanned(this.abilities[i]) && this.abilities[i] != null)
				list.add(I18n.format("ability." + WyHelper.getFancyName(this.abilities[i].getAttribute().getAttributeName()) + ".name"));

		list.add("");
		list.add(type.getColor() + type.getName());
	}

	public EnumFruitType getType()
	{
		return type;
	}

}
