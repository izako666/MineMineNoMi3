package xyz.pixelatedw.mineminenomi.api;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.Values;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.api.math.ISphere;
import xyz.pixelatedw.mineminenomi.api.math.Sphere;

public class WyHelper
{

	public enum Direction
	{
		SOUTH, SOUTH_EAST, EAST, NORTH, NORTH_EAST, NORTH_WEST, WEST, SOUTH_WEST;
	}

	public static AxisAlignedBB NULL_AABB = new AxisAlignedBB(0, 0, 0, 0, 0, 0);

	private static HashMap<String, List<Block>> blockRules = createBlockRulesMap();

	private static HashMap<String, List<Block>> createBlockRulesMap()
	{
		HashMap<String, List<Block>> map = new HashMap<String, List<Block>>();

		map.put("core", Arrays.asList(new Block[]
			{
					Blocks.ICE, Blocks.PACKED_ICE, Blocks.STONE, Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.SNOW, Blocks.SNOW_BLOCK, Blocks.SAND, Blocks.SANDSTONE, Blocks.SANDSTONE_STAIRS, Blocks.ACACIA_DOOR, Blocks.BIRCH_DOOR, Blocks.DARK_OAK_DOOR, Blocks.JUNGLE_DOOR, Blocks.OAK_DOOR, Blocks.ACACIA_SLAB, Blocks.BIRCH_SLAB, Blocks.DARK_OAK_SLAB, Blocks.COBBLESTONE, Blocks.COBBLESTONE_SLAB, Blocks.ACACIA_LOG, Blocks.BIRCH_LOG, Blocks.DARK_OAK_LOG, Blocks.JUNGLE_LOG, Blocks.OAK_LOG, Blocks.SPRUCE_LOG, Blocks.CAKE, /*ModMiscBlocks.poison, ModMiscBlocks.demonPoison,*/ Blocks.TORCH, Blocks.REDSTONE_TORCH, Blocks.REDSTONE_WIRE, Blocks.FARMLAND, Blocks.FLOWER_POT, Blocks.CLAY, Blocks.GRAVEL
			}));

		map.put("air", Arrays.asList(new Block[]
			{
					Blocks.AIR
			}));

		map.put("foliage", Arrays.asList(new Block[]
			{
					Blocks.ACACIA_LEAVES, Blocks.BIRCH_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.JUNGLE_LEAVES, Blocks.OAK_LEAVES, Blocks.SPRUCE_LEAVES, Blocks.LILY_PAD, Blocks.TALL_GRASS, Blocks.CHORUS_FLOWER, Blocks.LILAC, Blocks.PEONY, Blocks.ROSE_BUSH, Blocks.BROWN_MUSHROOM, Blocks.BROWN_MUSHROOM_BLOCK, Blocks.RED_MUSHROOM, Blocks.RED_MUSHROOM_BLOCK, Blocks.POTATOES, Blocks.CARROTS, Blocks.CACTUS, Blocks.DEAD_BUSH, Blocks.GRASS, Blocks.AZURE_BLUET, Blocks.DANDELION, Blocks.VINE
			}));

		map.put("ores", Arrays.asList(new Block[]
			{
					Blocks.COAL_ORE, Blocks.COAL_BLOCK, Blocks.DIAMOND_ORE, Blocks.DIAMOND_BLOCK, Blocks.IRON_ORE, Blocks.IRON_BLOCK, Blocks.LAPIS_ORE, Blocks.LAPIS_BLOCK, Blocks.REDSTONE_ORE, Blocks.REDSTONE_BLOCK, Blocks.GOLD_ORE, Blocks.GOLD_BLOCK, //ModMiscBlocks.kairosekiOre, ModMiscBlocks.kairosekiBlock
			}));

		map.put("liquids", Arrays.asList(new Block[]
			{
					Blocks.WATER, Blocks.LAVA
			}));

		return map;
	}

	public static boolean isNullOrEmpty(String str)
	{
		if (str != null && !str.isEmpty() && !str.equalsIgnoreCase("n/a"))
			return false;
		return true;
	}

	public static boolean placeBlockIfAllowed(World world, BlockPos pos, Block toPlace, String... rules)
	{
		return placeBlockIfAllowed(world, pos.getX(), pos.getY(), pos.getZ(), toPlace, 3, rules);
	}

	public static boolean placeBlockIfAllowed(World world, double posX, double posY, double posZ, Block toPlace, String... rules)
	{
		return placeBlockIfAllowed(world, posX, posY, posZ, toPlace, 3, rules);
	}

	public static boolean placeBlockIfAllowed(World world, double posX, double posY, double posZ, Block toPlace, int flag, String... rules)
	{
		Block b = world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock();
		List<Block> bannedBlocks = new ArrayList<Block>();
		boolean noGriefFlag = Arrays.toString(rules).contains("nogrief");

		/*
		 * ExtendedWorldData worldData = ExtendedWorldData.get(world);
		 * if(worldData.isInsideRestrictedArea(posX, posY, posZ))
		 * return false;
		 */

		Arrays.stream(rules).forEach(rule ->
		{
			String formula;
			if (rule.split(" ").length > 1)
			{
				formula = rule.split(" ")[0];
				rule = rule.split(" ")[1];
			}
			else
				formula = "add";

			if (blockRules.containsKey(rule))
			{
				if (formula.equalsIgnoreCase("add"))
					bannedBlocks.addAll(blockRules.get(rule));
				else if (formula.equalsIgnoreCase("ignore"))
					bannedBlocks.removeAll(blockRules.get(rule));
			}

			if (rule.equalsIgnoreCase("all"))
			{
				if (formula.equalsIgnoreCase("add"))
				{
					ForgeRegistries.BLOCKS.forEach(block ->
					{
						bannedBlocks.add(block);
					});
				}
				else if (formula.equalsIgnoreCase("ignore"))
				{
					ForgeRegistries.BLOCKS.forEach(block ->
					{
						bannedBlocks.remove(block);
					});
				}
			}
			else if (rule.equalsIgnoreCase("restricted"))
			{

				bannedBlocks.remove(Blocks.BEDROCK);
				/*bannedBlocks.remove(ModMiscBlocks.ope);
				bannedBlocks.remove(ModMiscBlocks.opeMid);
				bannedBlocks.remove(ModMiscBlocks.stringMid);
				bannedBlocks.remove(ModMiscBlocks.stringWall);
				bannedBlocks.remove(ModMiscBlocks.darkness);*/

			}

		});

		/*if (CommonConfig.instance.getGriefing() || noGriefFlag)
		{
			for (Block blk : bannedBlocks)
			{
				if (b == blk)
				{
					LogicalSidedProvider.WORKQUEUE.<IThreadListener>get(LogicalSide.SERVER).addScheduledTask(() ->
					{
						world.setBlockState(new BlockPos(posX, posY, posZ), toPlace.getDefaultState());
					});
					// world.setBlockState(posX, posY, posZ, toPlace, 0, flag);
					return true;
				}
			}
		}*/

		return false;
	}

	public static boolean afterDate(String date)
	{
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Calendar target = null;
		try
		{
			target = Calendar.getInstance();
			target.setTime(df.parse(date));
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		if (target == null)
			return false;

		Calendar now = Calendar.getInstance();
		return now.after(target);
	}

	public static AbilityExplosion newExplosion(Entity entity, double posX, double posY, double posZ, float size)
	{
		AbilityExplosion explosion = new AbilityExplosion(entity, posX, posY, posZ, size);
		return explosion;
	}

	public static void doExplosion(Entity entity, double posX, double posY, double posZ, float size)
	{
		AbilityExplosion explosion = new AbilityExplosion(entity, posX, posY, posZ, size);
		explosion.doExplosion();
	}

	public static boolean isBlockNearby(LivingEntity player, int radius, Block... blocks)
	{
		for (Block b : blocks)
		{
			for (int x = -radius; x < radius; x++)
				for (int y = -radius; y < radius; y++)
					for (int z = -radius; z < radius; z++)
					{
						if (player.world.getBlockState(new BlockPos((int) player.posX + x, (int) player.posY + y, (int) player.posZ + z)).getBlock() == b)
						{
							return true;
						}
					}
		}

		return false;
	}

	public static Block getBlockNearby(LivingEntity entity, int radius, Block block)
	{
		for (int x = -radius; x < radius; x++)
			for (int y = -radius; y < radius; y++)
				for (int z = -radius; z < radius; z++)
				{
					if (entity.world.getBlockState(new BlockPos((int) entity.posX + x, (int) entity.posY + y, (int) entity.posZ + z)).getBlock() == block)
					{
						return entity.world.getBlockState(new BlockPos((int) entity.posX + x, (int) entity.posY + y, (int) entity.posZ + z)).getBlock();
					}
				}

		return null;
	}

	public static List<int[]> getBlockLocationsNearby(LivingEntity entity, int[] radius)
	{
		List<int[]> nearbyBlocks = new ArrayList<int[]>();

		for (int x = -radius[0]; x < radius[0]; x++)
			for (int y = -radius[1]; y < radius[1]; y++)
				for (int z = -radius[2]; z < radius[2]; z++)
				{
					nearbyBlocks.add(new int[]
						{
								(int) entity.posX + x, (int) entity.posY + y, (int) entity.posZ + z
						});
				}

		return nearbyBlocks;
	}

	public static List<int[]> getBlockLocationsNearby(LivingEntity entity, int radius)
	{
		List<int[]> nearbyBlocks = new ArrayList<int[]>();

		for (int x = -radius; x < radius; x++)
			for (int y = -radius; y < radius; y++)
				for (int z = -radius; z < radius; z++)
				{
					nearbyBlocks.add(new int[]
						{
								(int) entity.posX + x, (int) entity.posY + y, (int) entity.posZ + z
						});
				}

		return nearbyBlocks;
	}

	public static void generateLangFiles()
	{
		if (!WyDebug.isDebug())
			return;

		Map<String, String> sorted = sortAlphabetically(WyRegistry.langMap);
		Set set = sorted.entrySet();
		Iterator i = set.iterator();

		Map.Entry prevEntry = null;

		File langFolder = new File(ID.PROJECT_RESOURCES_FOLDER + "/assets/" + ID.PROJECT_ID + "/lang/");
		langFolder.mkdirs();

		if (langFolder.exists())
		{
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ID.PROJECT_RESOURCES_FOLDER + "/assets/" + ID.PROJECT_ID + "/lang/en_US.json"), "UTF-8")))
			{
				writer.write("{\n");
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

					if (i.hasNext())
						writer.write("\t\"" + entry.getKey() + "\": \"" + entry.getValue() + "\",\n");
					else
						writer.write("\t\"" + entry.getKey() + "\": \"" + entry.getValue() + "\"\n");

					prevEntry = entry;
				}
				writer.write("}\n");
				writer.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public static <K extends Comparable, V extends Comparable> Map<K, V> sortAlphabetically(Map<K, V> map)
	{
		List<Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K, V>>(map.entrySet());

		Collections.sort(entries, new Comparator<Map.Entry<K, V>>()
		{
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
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

	public static String formatBytes(long bytes)
	{
		int unit = 1024;
		if (bytes < unit)
			return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = ("KMGTPE").charAt(exp - 1) + "";
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

	public static void sendMsgToPlayer(PlayerEntity player, String text)
	{
		player.sendMessage(new StringTextComponent(text));
	}

	public static String getFancyName(String text)
	{
		return text.replaceAll("\\s+", "").toLowerCase().replaceAll("'", "").replaceAll("-", "").replaceAll(":", "").replaceAll("#", "").replace(",", "");
	}

	public static String getFancyNameNoLowerCase(String text)
	{
		return text.replaceAll("\\s+", "").replaceAll("'", "").replaceAll("-", "").replaceAll(":", "").replaceAll("#", "").replace(",", "");
	}

	public static List<LivingEntity> getEntitiesNear(BlockPos pos, World world, double radius)
	{
		return getEntitiesNear(pos, world, radius, LivingEntity.class);
	}

	public static List<LivingEntity> getEntitiesNear(BlockPos pos, World world, double radius, Class<? extends Entity> classEntity)
	{
		AxisAlignedBB aabb = new AxisAlignedBB(pos.add(1, 1, 1)).grow(radius, radius, radius);
		List list = world.getEntitiesWithinAABB(classEntity, aabb);
		return list;
	}

	public static List<LivingEntity> getEntitiesNear(Entity e, double radius)
	{
		return getEntitiesNear(e, radius, LivingEntity.class);
	}

	public static List<LivingEntity> getEntitiesNear(Entity e, double radius, Class<? extends Entity>... classEntities)
	{
		try
		{
			AxisAlignedBB aabb = new AxisAlignedBB(e.posX, e.posY, e.posZ, e.posX + 1, e.posY + 1, e.posZ + 1).grow(radius, radius, radius);
			List list = new ArrayList();
			for (Class<? extends Entity> clz : classEntities)
				list.addAll(e.world.getEntitiesWithinAABB(clz, aabb));
			list.remove(e);
			return list;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}

		return null;
	}

	public static List<LivingEntity> getEntitiesNear(TileEntity e, double radius)
	{
		return getEntitiesNear(e, radius, LivingEntity.class);
	}

	public static List<LivingEntity> getEntitiesNear(TileEntity e, double radius, Class<? extends Entity> classEntity)
	{
		AxisAlignedBB aabb = new AxisAlignedBB(e.getPos().getX(), e.getPos().getY(), e.getPos().getZ(), e.getPos().getX() + 1, e.getPos().getY() + 1, e.getPos().getZ() + 1).expand(radius, radius, radius);
		List list = e.getWorld().getEntitiesWithinAABB(classEntity, aabb);
		return list;
	}

	public static Direction get4Directions(Entity e)
	{
		switch (MathHelper.floor(e.rotationYaw * 4.0F / 360.0F + 0.5D) & 3)
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
		switch (MathHelper.floor(e.rotationYaw * 8.0F / 360.0F + 0.5D) & 7)
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

	public static RayTraceResult rayTraceBlocks(Entity e)
	{
		float f = 1.0F;
		float f1 = e.prevRotationPitch + (e.rotationPitch - e.prevRotationPitch) * f;
		float f2 = e.prevRotationYaw + (e.rotationYaw - e.prevRotationYaw) * f;
		double d = e.prevPosX + (e.posX - e.prevPosX) * f;
		double d1 = (e.prevPosY + (e.posY - e.prevPosY) * f + 1.6200000000000001D) - e.getYOffset();
		double d2 = e.prevPosZ + (e.posZ - e.prevPosZ) * f;
		Vec3d vec3d = new Vec3d(d, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
		float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
		float f5 = -MathHelper.cos(-f1 * 0.01745329F);
		float f6 = MathHelper.sin(-f1 * 0.01745329F);
		float f7 = f4 * f5;
		float f9 = f3 * f5;
		double d3 = 5000D;

		Vec3d vec3 = vec3d.add(f7 * d3, f6 * d3, f9 * d3);
		RayTraceResult ray = null;//e.world.rayTraceBlocks(vec3d, vec3, RayTraceContext.FluidMode.ANY);

		return ray;
	}

	public static List<int[]> createEmptyCube(Entity entity, int[] sizes, Block blockToPlace, String... blockRules)
	{
		return createEmptyCube(entity.world, (int) entity.posX, (int) entity.posY, (int) entity.posZ, sizes, blockToPlace, blockRules);
	}

	public static List<int[]> createEmptyCube(World world, double posX, double posY, double posZ, int[] sizes, Block blockToPlace, String... blockRules)
	{
		List<int[]> blocks = new ArrayList<int[]>();
		for (int x = (sizes[0] * 0) - sizes[0]; x <= sizes[0]; x++)
			for (int y = (sizes[1] * 0) - sizes[1]; y <= sizes[1]; y++)
				for (int z = (sizes[2] * 0) - sizes[2]; z <= sizes[2]; z++)
				{
					if (x == -sizes[0] || x == sizes[0] || y == -sizes[1] || y == sizes[1] || z == -sizes[2] || z == sizes[2])
					{
						placeBlockIfAllowed(world, (int) posX + x, (int) posY + y, (int) posZ + z, blockToPlace, blockRules);
						blocks.add(new int[]
							{
									(int) posX + x, (int) posY + y, (int) posZ + z
							});
					}
				}

		return blocks;
	}

	public static List<int[]> createFilledCube(Entity entity, int[] sizes, Block blockToPlace, String... blockRules)
	{
		return createFilledCube(entity.world, (int) entity.posX, (int) entity.posY, (int) entity.posZ, sizes, blockToPlace, blockRules);
	}

	public static List<int[]> createFilledCube(World world, double posX, double posY, double posZ, int[] sizes, Block blockToPlace, String... blockRules)
	{
		List<int[]> blocks = new ArrayList<int[]>();
		for (int x = (sizes[0] * 0) - sizes[0]; x <= sizes[0]; x++)
			for (int y = (sizes[1] * 0) - sizes[1]; y <= sizes[1]; y++)
				for (int z = (sizes[2] * 0) - sizes[2]; z <= sizes[2]; z++)
				{
					placeBlockIfAllowed(world, (int) posX + x, (int) posY + y, (int) posZ + z, blockToPlace, blockRules);
					blocks.add(new int[]
						{
								(int) posX + x, (int) posY + y, (int) posZ + z
						});
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
						@Override
						public void call(int x, int y, int z)
						{
							placeBlockIfAllowed(world, x, y, z, block, blockRules);
							blocks.add(new int[]
								{
										x, y, z
								});
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
						@Override
						public void call(int x, int y, int z)
						{
							placeBlockIfAllowed(world, x, y, z, block, blockRules);
							blocks.add(new int[]
								{
										x, y, z
								});
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

	public static void removeStackFromInventory(PlayerEntity player, ItemStack stack)
	{
		player.inventory.deleteStack(stack);
	}

	public static void removeStackFromSlot(PlayerEntity player, int index)
	{
		if (player.inventory.mainInventory.get(index) != null)
			player.inventory.mainInventory.set(index, ItemStack.EMPTY);
	}

	public static boolean isPatreon(PlayerEntity player)
	{
		boolean flag = false;
		
		String url = "/isPatreon";

		try
		{
			String uuid = player.getUniqueID().toString();
			String json = Values.gson.toJson(uuid);
						
			HttpPost post = new HttpPost(Values.urlConnection + "" + url);	
			StringEntity postingString;
			postingString = new StringEntity(json);
			post.setEntity(postingString);
			post.setHeader("Content-Type", "application/json");
			
			HttpResponse response = Values.httpClient.execute(post);
			ResponseHandler<String> handler = new BasicResponseHandler();

			String body = handler.handleResponse(response);
			if(body.isEmpty())
				((ServerPlayerEntity)player).connection.disconnect(new StringTextComponent(TextFormatting.BOLD + "" + TextFormatting.RED + "WARNING! \n\n " + TextFormatting.RESET + "You don't have access to this version yet!"));
			else
			{
				int patreonLevel = Integer.parseInt(body);
				
				if(patreonLevel <= 2)
					((ServerPlayerEntity)player).connection.disconnect(new StringTextComponent(TextFormatting.BOLD + "" + TextFormatting.RED + "WARNING! \n\n " + TextFormatting.RESET + "You don't have access to this version yet!"));
				else
					flag = true;
			}
			
		}
		catch(Exception e)
		{
			((ServerPlayerEntity)player).connection.disconnect(new StringTextComponent(TextFormatting.BOLD + "" + TextFormatting.RED + "WARNING! \n\n " + TextFormatting.RESET + "You don't have access to this version yet!"));						
			e.printStackTrace();
		}
		
		return flag;
	}
}
