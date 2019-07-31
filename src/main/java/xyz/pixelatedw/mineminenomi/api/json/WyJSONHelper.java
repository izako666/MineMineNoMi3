package xyz.pixelatedw.mineminenomi.api.json;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;

public class WyJSONHelper
{
	
	public static void generateJSONModels(boolean override)
	{
		if (!WyDebug.isDebug())
			return;
		
		Iterator i = WyRegistry.items.keySet().iterator();
		File itemFolder = new File(ID.PROJECT_RESOURCES_FOLDER + "/assets/" + ID.PROJECT_ID + "/models/item/");

		if (!itemFolder.exists())
			itemFolder.mkdirs();
		
		while (i.hasNext())
		{
			Item item = (Item) i.next();
			String itemName = WyHelper.getFancyName(item.getRegistryName().getPath());

			File jsonModel = new File(ID.PROJECT_RESOURCES_FOLDER + "/assets/" + ID.PROJECT_ID + "/models/item/" + itemName + ".json");
			if (jsonModel.exists() && !override)
				continue;
			
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(jsonModel), "UTF-8")))
			{
				String[] model = WyRegistry.items.get(item).getModel();
				
				if(model == null)
				{
					writer.close();
					Files.delete(jsonModel.toPath());
					continue;
				}
				
				for(String line : model)
				{
					writer.write(line + "\n");
				}
				writer.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		i = WyRegistry.blocks.keySet().iterator();
		
		File blockFolder = new File(ID.PROJECT_RESOURCES_FOLDER + "/assets/" + ID.PROJECT_ID + "/models/block/");
		if (!blockFolder.exists())
			blockFolder.mkdirs();
		
		File blockStatesFolder = new File(ID.PROJECT_RESOURCES_FOLDER + "/assets/" + ID.PROJECT_ID + "/blockstates/");
		if (!blockStatesFolder.exists())
			blockStatesFolder.mkdirs();
		
		while (i.hasNext())
		{
			Block block = (Block) i.next();
			String blockName = WyHelper.getFancyName(block.getRegistryName().getPath());

			File jsonModel = new File(ID.PROJECT_RESOURCES_FOLDER + "/assets/" + ID.PROJECT_ID + "/models/block/" + blockName + ".json");
			if (!jsonModel.exists() || override)
			{
				try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(jsonModel), "UTF-8")))
				{
					String[] model = WyRegistry.blocks.get(block).getModel();
					
					if(model == null)
					{
						writer.close();
						Files.delete(jsonModel.toPath());
					}
					else
					{
						for(String line : model)
						{
							writer.write(line + "\n");
						}
						writer.close();
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
			jsonModel = new File(ID.PROJECT_RESOURCES_FOLDER + "/assets/" + ID.PROJECT_ID + "/blockstates/" + blockName + ".json");
			if (!jsonModel.exists() || override)
			{
				try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(jsonModel), "UTF-8")))
				{
					String[] model = WyRegistry.blocks.get(block).getBlockStateModel();
					
					if(model == null)
					{
						writer.close();
						Files.delete(jsonModel.toPath());
						continue;
					}
					else
					{
						for(String line : model)
						{
							writer.write(line + "\n");
						}
						writer.close();
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
