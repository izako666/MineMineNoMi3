package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModCreativeTabs
{
	public static final ItemGroup DEVIL_FRUITS = new ItemGroup("devilfruits")
	{
		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack createIcon()
		{
			return new ItemStack(ModDevilFruits.MeraMeraNoMi);
		}
	};

	public static final ItemGroup WEAPONS = new ItemGroup("weapons")
	{
		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack createIcon()
		{
			return new ItemStack(ModWeapons.yoru);
		}
	};

	public static final ItemGroup MISC = new ItemGroup("misc")
	{
		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack createIcon()
		{
			return new ItemStack(ModMiscItems.kairoseki);
		}
	};
}
