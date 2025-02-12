package xyz.pixelatedw.MineMineNoMi3.api;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.abilities.CyborgAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.FishKarateAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.HakiAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.RokushikiAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.SniperAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.SwordsmanAbilities;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityManager;
import xyz.pixelatedw.MineMineNoMi3.api.math.ISphere;
import xyz.pixelatedw.MineMineNoMi3.api.math.Sphere;
import xyz.pixelatedw.MineMineNoMi3.helpers.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.items.AkumaNoMi;

public class WyHelper
{

	public enum Direction
	{
		SOUTH, SOUTH_EAST, EAST, NORTH, NORTH_EAST, NORTH_WEST, WEST, SOUTH_WEST;
	}

	public static AxisAlignedBB NULL_AABB = AxisAlignedBB.getBoundingBox(0, 0, 0, 0, 0, 0);
	
	public static boolean afterDate(String date)
	{
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Calendar target  = null;
		try
		{
			target = Calendar.getInstance();
			target.setTime(df.parse(date));
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		if(target == null) return false;
		
		Calendar now = Calendar.getInstance();
		return now.after(target);
	}
	
	public static AbilityExplosion newExplosion(Entity entity, double posX, double posY, double posZ, double size)
	{
		AbilityExplosion explosion = new AbilityExplosion(entity, posX, posY, posZ, size);
		return explosion;
	}
	
	public static void doExplosion(Entity entity, double posX, double posY, double posZ, double size)
	{
		AbilityExplosion explosion = new AbilityExplosion(entity, posX, posY, posZ, size);
		explosion.doExplosion();
	}

	public static boolean isBlockNearby(EntityLivingBase player, int radius, Block... blocks)
	{
		for (Block b : blocks)
		{
			for (int x = -radius; x < radius; x++)
				for (int y = -radius; y < radius; y++)
					for (int z = -radius; z < radius; z++)
					{
						if (player.worldObj.getBlock((int) player.posX + x, (int) player.posY + y, (int) player.posZ + z) == b)
						{
							return true;
						}
					}
		}

		return false;
	}

	public static Block getBlockNearby(EntityLivingBase entity, int radius, Block block)
	{
		for (int x = -radius; x < radius; x++)
			for (int y = -radius; y < radius; y++)
				for (int z = -radius; z < radius; z++)
				{
					if (entity.worldObj.getBlock((int) entity.posX + x, (int) entity.posY + y, (int) entity.posZ + z) == block)
					{
						return entity.worldObj.getBlock((int) entity.posX + x, (int) entity.posY + y, (int) entity.posZ + z);
					}
				}

		return null;
	}
	
	public static List<int[]> getBlockLocationsNearby(EntityLivingBase entity, int[] radius)
	{
		List<int[]> nearbyBlocks = new ArrayList<int[]>();
		
		for (int x = -radius[0]; x < radius[0]; x++)
			for (int y = -radius[1]; y < radius[1]; y++)
				for (int z = -radius[2]; z < radius[2]; z++)
				{
					nearbyBlocks.add(new int[] {(int) entity.posX + x, (int) entity.posY + y, (int) entity.posZ + z});
				}

		return nearbyBlocks;
	}
	
	public static List<int[]> getBlockLocationsNearby(EntityLivingBase entity, int radius)
	{
		List<int[]> nearbyBlocks = new ArrayList<int[]>();
		
		for (int x = -radius; x < radius; x++)
			for (int y = -radius; y < radius; y++)
				for (int z = -radius; z < radius; z++)
				{
					nearbyBlocks.add(new int[] {(int) entity.posX + x, (int) entity.posY + y, (int) entity.posZ + z});
				}

		return nearbyBlocks;
	}

	public static <K extends Comparable, V extends Comparable> Map<K, V> sortAlphabetically(Map<K, V> map)
	{
		List<Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K, V>>(map.entrySet());

		Collections.sort(entries, new Comparator<Map.Entry<K, V>>()
		{
			public int compare(Entry<K, V> o1, Entry<K, V> o2)
			{
				return o1.getKey().compareTo(o2.getKey());
			}
		});

		Map<K, V> sortedMap = new LinkedHashMap<K, V>();

		for (Map.Entry<K, V> entry : entries)
		{
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	public static void generateLangFiles()
	{
		Map<String, String> sorted = sortAlphabetically(WyRegistry.getLangMap());
		Set set = sorted.entrySet();
		Iterator i = set.iterator();

		Map.Entry prevEntry = null;

		File langFolder = new File(Values.RESOURCES_FOLDER + "/assets/" + ID.PROJECT_ID + "/lang/");
		langFolder.mkdirs();

		if (langFolder.exists())
		{
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Values.RESOURCES_FOLDER + "/assets/" + ID.PROJECT_ID + "/lang/en_US.lang"), "UTF-8")))
			{
				while (i.hasNext())
				{
					Map.Entry entry = (Map.Entry) i.next();

					if (prevEntry != null)
					{
						if (!((String) prevEntry.getKey()).substring(0, 2).equals(((String) entry.getKey()).substring(0, 2)))
						{
							writer.write("\n");
						}
					}

					writer.write(entry.getKey() + "=" + entry.getValue() + "\n");

					prevEntry = entry;
				}
				writer.close();
			}
			catch (Exception e)
			{
				e.getStackTrace();
			}
		}
	}

	/*
	 * LEGACY METHOD TO GENERATE 1.8+ JSON MODELS public void
	 * generateIngameModels() { Set set =
	 * WyPI.apiInstance.getItemsMap().entrySet(); Iterator i = set.iterator();
	 * 
	 * while (i.hasNext()) { Map.Entry entry = (Map.Entry) i.next(); if
	 * (entry.getKey() instanceof Item) { Item item = (Item) entry.getKey();
	 * Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(
	 * item, 0, new
	 * ModelResourceLocation(WyPI.apiInstance.getParentMod().getParentModID() +
	 * ":" + item.getUnlocalizedName().substring(5), "inventory")); } if
	 * (entry.getKey() instanceof Block) { Block block = (Block) entry.getKey();
	 * Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(
	 * Item.getItemFromBlock(block), 0, new
	 * ModelResourceLocation(WyPI.apiInstance.getParentMod().getParentModID() +
	 * ":" + block.getUnlocalizedName().substring(5), "inventory")); } } }
	 */

	/**
	 * 0) is burning; 1) is sneaking; 2) is riding something; 3) is sprinting;
	 * 4) is eating; 5) is invisible
	 */
	public static void setEntityFlag(Entity player, int id, boolean bool)
	{
		byte b0 = player.getDataWatcher().getWatchableObjectByte(0);

		if (bool)
		{
			player.getDataWatcher().updateObject(0, Byte.valueOf((byte) (b0 | 1 << id)));
		}
		else
		{
			player.getDataWatcher().updateObject(0, Byte.valueOf((byte) (b0 & ~(1 << id))));
		}
	}

	public static Color hslToColor(float h, float s, float l)
	{
		float[] hsl = new float[]
		{
				h, s, l
		};

		if (s < 0.0f || s > 100.0f)
		{
			String message = "Color parameter outside of expected range - Saturation";
			throw new IllegalArgumentException(message);
		}

		if (l < 0.0f || l > 100.0f)
		{
			String message = "Color parameter outside of expected range - Luminance";
			throw new IllegalArgumentException(message);
		}

		h = h % 360.0f;
		h /= 360f;
		s /= 100f;
		l /= 100f;

		float q = 0;

		if (l < 0.5)
			q = l * (1 + s);
		else
			q = (l + s) - (s * l);

		float p = 2 * l - q;

		float r = Math.max(0, hueToRGB(p, q, h + (1.0f / 3.0f)));
		float g = Math.max(0, hueToRGB(p, q, h));
		float b = Math.max(0, hueToRGB(p, q, h - (1.0f / 3.0f)));

		r = Math.min(r, 1.0f);
		g = Math.min(g, 1.0f);
		b = Math.min(b, 1.0f);

		return new Color(r, g, b);
	}

	private static float hueToRGB(float p, float q, float h)
	{
		if (h < 0)
			h += 1;

		if (h > 1)
			h -= 1;

		if (6 * h < 1)
			return p + ((q - p) * 6 * h);

		if (2 * h < 1)
			return q;

		if (3 * h < 2)
			return p + ((q - p) * 6 * ((2.0f / 3.0f) - h));

		return p;
	}

	public static Color hexToRGB(String hexColor)
	{
		if (hexColor.startsWith("#"))
			return Color.decode(hexColor);
		else
			return Color.decode("#" + hexColor);
	}

	public static void sendMsgToPlayer(EntityPlayer player, String text)
	{
		player.addChatComponentMessage(new ChatComponentText(text));
	}

	public static String getFancyName(String text)
	{
		return text.replaceAll("\\s+", "").toLowerCase().replaceAll("'", "").replaceAll("-", "").replaceAll(":", "").replaceAll("#", "").replace(",", "");
	}

	public static String getFancyNameNoLowerCase(String text)
	{
		return text.replaceAll("\\s+", "").replaceAll("'", "").replaceAll("-", "").replaceAll(":", "").replaceAll("#", "").replace(",", "");
	}

	public static List<EntityLivingBase> getEntitiesNear(int x, int y, int z, World world, double radius)
	{
		return getEntitiesNear(x, y, z, world, radius, EntityLivingBase.class);
	}

	public static List<EntityLivingBase> getEntitiesNear(int x, int y, int z, World world, double radius, Class<? extends Entity> classEntity)
	{
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1).expand(radius, radius, radius);
		List list = world.getEntitiesWithinAABB(classEntity, aabb);
		return list;
	}

	public static List<EntityLivingBase> getEntitiesNear(Entity e, double radius)
	{
		return getEntitiesNear(e, radius, EntityLivingBase.class);
	}

	public static List<EntityLivingBase> getEntitiesNear(Entity e, double radius, Class<? extends Entity>... classEntities)
	{
		try
		{
			AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(e.posX, e.posY, e.posZ, e.posX + 1, e.posY + 1, e.posZ + 1).expand(radius, radius, radius);			List list = new ArrayList();
			for(Class<? extends Entity> clz : classEntities)
				list.addAll(e.worldObj.getEntitiesWithinAABB(clz, aabb));
			list.remove(e);
			return list;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		return null;
	}

	public static List<EntityLivingBase> getEntitiesNear(TileEntity e, double radius)
	{
		return getEntitiesNear(e, radius, EntityLivingBase.class);
	}

	public static List<EntityLivingBase> getEntitiesNear(TileEntity e, double radius, Class<? extends Entity> classEntity)
	{
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox((double) e.xCoord, (double) e.yCoord, (double) e.zCoord, (double) (e.xCoord + 1), (double) (e.yCoord + 1), (double) (e.zCoord + 1)).expand(radius, radius, radius);
		List list = e.getWorldObj().getEntitiesWithinAABB(classEntity, aabb);
		return list;
	}

	public static Direction get4Directions(Entity e)
	{
		switch (MathHelper.floor_double(e.rotationYaw * 4.0F / 360.0F + 0.5D) & 3)
		{
			case 0:
				return Direction.SOUTH;
			case 1:
				return Direction.WEST;
			case 2:
				return Direction.NORTH;
			case 3:
				return Direction.EAST;
		}
		return null;
	}

	public static Direction get8Directions(Entity e)
	{
		switch (MathHelper.floor_double(e.rotationYaw * 8.0F / 360.0F + 0.5D) & 7)
		{
			case 0:
				return Direction.SOUTH;
			case 1:
				return Direction.SOUTH_WEST;
			case 2:
				return Direction.WEST;
			case 3:
				return Direction.NORTH_WEST;
			case 4:
				return Direction.NORTH;
			case 5:
				return Direction.NORTH_EAST;
			case 6:
				return Direction.EAST;
			case 7:
				return Direction.SOUTH_EAST;
		}
		return null;
	}

	public static MovingObjectPosition rayTraceBlocks(Entity e)
	{
		float f = 1.0F;
		float f1 = e.prevRotationPitch + (e.rotationPitch - e.prevRotationPitch) * f;
		float f2 = e.prevRotationYaw + (e.rotationYaw - e.prevRotationYaw) * f;
		double d = e.prevPosX + (e.posX - e.prevPosX) * (double) f;
		double d1 = (e.prevPosY + (e.posY - e.prevPosY) * (double) f + 1.6200000000000001D) - (double) e.getYOffset();
		double d2 = e.prevPosZ + (e.posZ - e.prevPosZ) * (double) f;
		Vec3 vec3d = Vec3.createVectorHelper(d, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
		float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
		float f5 = -MathHelper.cos(-f1 * 0.01745329F);
		float f6 = MathHelper.sin(-f1 * 0.01745329F);
		float f7 = f4 * f5;
		float f9 = f3 * f5;
		double d3 = 5000D;

		Vec3 vec3 = vec3d.addVector((double) f7 * d3, (double) f6 * d3, (double) f9 * d3);
		MovingObjectPosition ray = e.worldObj.rayTraceBlocks(vec3d, vec3, false);

		return ray;
	}

	public static List<int[]> createEmptyCube(Entity entity, int[] sizes, Block blockToPlace, String... blockRules)
	{
		return createEmptyCube(entity.worldObj, (int)entity.posX, (int)entity.posY, (int)entity.posZ, sizes, blockToPlace, blockRules);
	}
	
	public static List<int[]> createEmptyCube(World world, double posX, double posY, double posZ, int[] sizes, Block blockToPlace, String... blockRules)
	{
		List<int[]> blocks = new ArrayList<int[]>();
		for (int x = (sizes[0] * 0) - sizes[0]; x <= sizes[0]; x++)
		for (int y = (sizes[1] * 0) - sizes[1]; y <= sizes[1]; y++)
		for (int z = (sizes[2] * 0) - sizes[2]; z <= sizes[2]; z++)
		{
			if(x == -sizes[0] || x == sizes[0] || y == -sizes[1] || y == sizes[1] || z == -sizes[2] || z == sizes[2])
			{
				DevilFruitsHelper.placeBlockIfAllowed(world, (int)posX + x, (int)posY + y, (int)posZ + z, blockToPlace, blockRules);
				blocks.add(new int[] {(int)posX + x, (int)posY + y, (int)posZ + z} );
			}
		}
		
		return blocks;
	}
		
	public static List<int[]> createFilledCube(Entity entity, int[] sizes, Block blockToPlace, String... blockRules)
	{
		return createFilledCube(entity.worldObj, (int)entity.posX, (int)entity.posY, (int)entity.posZ, sizes, blockToPlace, blockRules);
	}
	
	public static List<int[]> createFilledCube(World world, double posX, double posY, double posZ, int[] sizes, Block blockToPlace, String... blockRules)
	{
		List<int[]> blocks = new ArrayList<int[]>();
		for (int x = (sizes[0] * 0) - sizes[0]; x <= sizes[0]; x++)
		for (int y = (sizes[1] * 0) - sizes[1]; y <= sizes[1]; y++)
		for (int z = (sizes[2] * 0) - sizes[2]; z <= sizes[2]; z++)
		{
			DevilFruitsHelper.placeBlockIfAllowed(world, (int)posX + x, (int)posY + y, (int)posZ + z, blockToPlace, blockRules);
			blocks.add(new int[] {(int)posX + x, (int)posY + y, (int)posZ + z} );
		}
		
		return blocks;
	}

	public static List<int[]> createEmptySphere(World world, int posX, int posY, int posZ, int size, final Block block, String... blockRules)
	{
		List<int[]> blocks = new ArrayList<int[]>();

	    try
		{
			Thread sphereGenerator = new Thread("Sphere Generator")
			{
		        @Override
		        public void run()
		        {
					Sphere.generate(posX, posY, posZ, size, new ISphere()
					{
						public void call(int x, int y, int z)
						{
							DevilFruitsHelper.placeBlockIfAllowed(world, x, y, z, block, blockRules);
							blocks.add(new int[] {x, y, z});
						}
					});
		        }
		    };
		    sphereGenerator.start();
			sphereGenerator.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	    
		return blocks;
	}

	public static List<int[]> createFilledSphere(World world, int posX, int posY, int posZ, int size, final Block block, String... blockRules)
	{
		List<int[]> blocks = new ArrayList<int[]>();

	    try
		{
			Thread sphereGenerator = new Thread("Sphere Generator")
			{
		        @Override
		        public void run()
		        {
					Sphere.generateFilled(posX, posY, posZ, size, new ISphere()
					{
						public void call(int x, int y, int z)
						{
							DevilFruitsHelper.placeBlockIfAllowed(world, x, y, z, block, blockRules);
							blocks.add(new int[] {x, y, z});
						}
					});
		        }
		    };
		    sphereGenerator.start();
			sphereGenerator.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	    
		return blocks;
	}
	
	public static void removeStackFromInventory(EntityPlayer player, ItemStack stack)
	{
		for (int i = 0; i < player.inventory.mainInventory.length; i++)
		{
			if (stack == player.inventory.getStackInSlot(i))
			{
				player.inventory.setInventorySlotContents(i, null);
				break;
			}
		}
	}

	public static void removeStackFromSlot(EntityPlayer player, int index)
	{
		if (player.inventory.mainInventory[index] != null)
			player.inventory.mainInventory[index] = null;
	}
	
	public static boolean isPatreon(EntityPlayer player)
	{
		boolean flag = false;
		
		try 
		{
			URL url = new URL("https://dl.dropboxusercontent.com/s/cs2cv9ezaatzgd3/earlyaccess.txt?dl=0");
			Scanner scanner = new Scanner(url.openStream());
			
			while(scanner.hasNextLine())
			{
				String uuid = scanner.nextLine();
				if(uuid.startsWith("$"))
					continue;
										
				if(player.getUniqueID().toString().equals(uuid) || (uuid.startsWith("&") && player.getDisplayName().equalsIgnoreCase(uuid.replace("& ", ""))))
				{
					flag = true;
					break;
				}
			}
			
			if(!flag)
				((EntityPlayerMP)player).playerNetServerHandler.kickPlayerFromServer(EnumChatFormatting.BOLD + "" + EnumChatFormatting.RED + "WARNING! \n\n " + EnumChatFormatting.RESET + "You don't have access to this version yet!");														
			
			scanner.close();
		} 
		catch (IOException e) 
		{
			((EntityPlayerMP)player).playerNetServerHandler.kickPlayerFromServer(EnumChatFormatting.BOLD + "" + EnumChatFormatting.RED + "WARNING! \n\n " + EnumChatFormatting.RESET + "You don't have access to this version yet!");						
			e.printStackTrace();
		}
		
		return flag;
	}
}
