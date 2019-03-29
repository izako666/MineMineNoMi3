package xyz.pixelatedw.MineMineNoMi3.helpers;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.EntityRendererZoanEyes;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.RenderZoanMorph;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelBisonPower;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelBisonSpeed;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelGiraffePower;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelGiraffeSpeed;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelMoguPower;
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
						{ "speed", new RenderZoanMorph(new ModelBisonSpeed(), "bisonspeed", 1.4, new float[] { 0, 0.8f, 0 }), new EntityRendererZoanEyes(Minecraft.getMinecraft(), -0.3) }					
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
		morphsMap.put("mogumogu", new Object[][]
				{
						{ "power", new RenderZoanMorph(new ModelMoguPower(), "mogu", 0.9, new float[] { 0, 0.1f, 0 }), new EntityRendererZoanEyes(Minecraft.getMinecraft(), -0.2) },
				});
		morphsMap.put("ushiushigiraffe", new Object[][]
				{
						{ "power", new RenderZoanMorph(new ModelGiraffePower(), "giraffehybrid", 1.4, new float[] { 0, 0.7f, 0 }), new EntityRendererZoanEyes(Minecraft.getMinecraft(), 1.8) },
						{ "speed", new RenderZoanMorph(new ModelGiraffeSpeed(), "giraffefull", 1.55, new float[] { 0, 0.95f, 0 }), new EntityRendererZoanEyes(Minecraft.getMinecraft(), 1.9) },
				});
		morphsMap.put("minimini", new Object[][]
				{
						{ "mini", new RenderZoanMorph(new ModelBiped(), "$playerskin", 0.15, new float[] { 0, -0.9f, 0 }), new EntityRendererZoanEyes(Minecraft.getMinecraft(), -0.8) },
				});
	}
	
}
