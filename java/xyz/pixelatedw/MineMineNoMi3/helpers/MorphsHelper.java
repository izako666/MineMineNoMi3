package xyz.pixelatedw.MineMineNoMi3.helpers;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.EntityRendererZoanEyes;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.RenderZoanMorph;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelBisonPower;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelBisonSpeed;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelPhoenixFull;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelPhoenixHybrid;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelVenomDemon;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelYomi;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelZouFull;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelZouHybrid;

public class MorphsHelper
{
	
	private static HashMap<String, Object[][]> morphsMap = new HashMap<String, Object[][]>();
	
	public static HashMap<String, Object[][]> getMorphsMap()
	{
		return morphsMap;
	}

	static
	{
		morphsMap.put("ushiushibison", new Object[][] 
				{
						{ "power", new RenderZoanMorph(new ModelBisonPower(), "bisonpower", 1.4, new float[] { 0, 0.7f, 0 }), new EntityRendererZoanEyes(Minecraft.getMinecraft(), 0.6) },
						{ "speed", new RenderZoanMorph(new ModelBisonSpeed(), "bisonspeed", 1.4, new float[] { 0, 0.8f, 0 }), null }					
				});
		morphsMap.put("toritoriphoenix", new Object[][]
				{
						{ "full", new RenderZoanMorph(new ModelPhoenixFull(), "phoenixfull", 1.3, new float[] { 0, 0.3f, 0 }), null },
						{ "hybrid", new RenderZoanMorph(new ModelPhoenixHybrid(), "phoenixhybrid", 1, new float[] { 0, 0.2f, 0 }), null }		
				});
		morphsMap.put("zouzou", new Object[][]
				{
						{ "full", new RenderZoanMorph(new ModelZouFull(), "zoufull", 1.3, new float[] { 0, 0.65f, 0 }), new EntityRendererZoanEyes(Minecraft.getMinecraft(), 0.3) },
						{ "hybrid", new RenderZoanMorph(new ModelZouHybrid(), "zouhybrid", 1.0, new float[] { 0, 0.2f, 0 }), new EntityRendererZoanEyes(Minecraft.getMinecraft(), 0.8) }
				});
		morphsMap.put("dokudoku", new Object[][]
				{
						{ "venomDemon", new RenderZoanMorph(new ModelVenomDemon(), "venomdemon", 1.1, new float[] { 0, 0.5f, 0 }), new EntityRendererZoanEyes(Minecraft.getMinecraft(), 1.6) },
				});
		morphsMap.put("yomiyomi", new Object[][]
				{
						{ "yomi", new RenderZoanMorph(new ModelYomi(), "skeleton", 1.1, new float[] { 0, 0.3f, 0 }), null },
				});
	}
	
}
