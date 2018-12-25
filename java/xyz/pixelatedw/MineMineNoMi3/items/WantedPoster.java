package xyz.pixelatedw.MineMineNoMi3.items;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.blocks.tileentities.TileEntityWantedPoster;
import xyz.pixelatedw.MineMineNoMi3.gui.GUIWantedPoster;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class WantedPoster extends Item
{

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if (!world.isRemote && !player.isSneaking() && itemStack.getTagCompound() != null)
			Minecraft.getMinecraft().displayGuiScreen(new GUIWantedPoster(itemStack.getTagCompound()));

		return itemStack;
	}

	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int blockFace, float f1, float f2, float f3)
	{
		if (player.isSneaking())
		{
			if (blockFace == 0 || blockFace == 1)
				return false;
			else if (!world.getBlock(posY, posY, posZ).getMaterial().isSolid())
				return false;
			else
			{
				if (blockFace == 2)
					--posZ;
				if (blockFace == 3)
					++posZ;
				if (blockFace == 4)
					--posX;
				if (blockFace == 5)
					++posX;

				if (!Blocks.standing_sign.canPlaceBlockAt(world, posX, posY, posZ))
					return false;
				else
				{
					world.setBlock(posX, posY, posZ, ListMisc.WantedPosterBlock, blockFace, 2);

					--itemStack.stackSize;
					TileEntityWantedPoster tileEntity = (TileEntityWantedPoster) world.getTileEntity(posX, posY, posZ);

					if (tileEntity != null)
					{
						tileEntity.setEntityName(itemStack.getTagCompound().getString("Name"));
						tileEntity.setPosterBounty(itemStack.getTagCompound().getString("Bounty"));
						tileEntity.setIssuedDate(itemStack.getTagCompound().getString("Date"));
						tileEntity.markDirty();
					}

					return true;
				}
			}
		}
		return false;
	}
}
