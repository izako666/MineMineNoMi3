package xyz.pixelatedw.mineminenomi.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xyz.pixelatedw.mineminenomi.entities.zoan.RenderZoanMorph;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoBisonPower;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoBisonSpeed;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoMoguPower;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoPhoenixFull;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoPhoenixHybrid;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoVenomDemon;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoZouFull;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoZouHybrid;
import xyz.pixelatedw.mineminenomi.entities.zoan.models.ModelGiraffePower;
import xyz.pixelatedw.mineminenomi.entities.zoan.models.ModelGiraffeSpeed;
import xyz.pixelatedw.mineminenomi.entities.zoan.models.ModelYomi;
import xyz.pixelatedw.mineminenomi.entities.zoan.renderers.RenderMorphYomi;

public class MorphsHelper
{
	
	private static List<ZoanInfo> zoanInfoList = new ArrayList<ZoanInfo>();
	
	private static HashMap<String, Object[][]> oldMorphsMap = new HashMap<String, Object[][]>();
	
	@Deprecated
	public static HashMap<String, Object[][]> getMorphsMap()
	{
		return oldMorphsMap;
	}

	public static List<ZoanInfo> getZoanInfoList()
	{
		return zoanInfoList;
	}
	
	static
	{
		// Bison Zoan Points
		zoanInfoList.add(new ZoanInfoBisonPower());
		zoanInfoList.add(new ZoanInfoBisonSpeed());
		
		// Phoenix Zoan Points
		zoanInfoList.add(new ZoanInfoPhoenixFull());
		zoanInfoList.add(new ZoanInfoPhoenixHybrid());
		
		// Zou Zoan Points
		zoanInfoList.add(new ZoanInfoZouFull());
		zoanInfoList.add(new ZoanInfoZouHybrid());
		
		// Mogu Zoan Points
		zoanInfoList.add(new ZoanInfoMoguPower());
	
		// Non-zoan Morphs
		zoanInfoList.add(new ZoanInfoVenomDemon());

		
		/*oldMorphsMap.put("ushiushibison", new Object[][] 
				{
						{ 
							"power", 
							new RenderZoanMorph.Factory(new ModelBisonPower(), "bisonpower", 1.4, new float[] { 0, 2.3f, 0 }),
							new Object[] {ListAttributes.BISON_POWER_POINT, 1.5, 2.5},
							null
						},
						{ 
							"speed", 
							new RenderZoanMorph.Factory(new ModelBisonSpeed(), "bisonspeed", 1.4, new float[] { 0, 2.4f, 0 }), 
							new Object[] {ListAttributes.BISON_SPEED_POINT, 1.3, 1.45},
							null
						}					
				});
		oldMorphsMap.put("toritoriphoenix", new Object[][]
				{
						{ 
							"full", 
							new RenderZoanMorph.Factory(new ModelPhoenixFull(), "phoenixfull", 1.3, new float[] { 0, 0.3f, 0 }), 
							null,
							null
						},
						{ 
							"hybrid", 
							new RenderZoanMorph.Factory(new ModelPhoenixHybrid(), "phoenixhybrid", 1, new float[] { 0, 0.2f, 0 }), 
							null,
							null
						}
				});*/
		/*oldMorphsMap.put("zouzou", new Object[][]
				{
						{ 
							"full", 
							new RenderZoanMorph.Factory(new ModelZouFull(), "zoufull", 1.3, new float[] { 0, 0.65f, 0 }), 
							//new EntityRendererZoanEyes(Minecraft.getInstance(), 0.3),
							null
						},
						{ 
							"hybrid", 
							new RenderZoanMorph.Factory(new ModelZouHybrid(), "zouhybrid", 1.0, new float[] { 0, 0.2f, 0 }), 
							//new EntityRendererZoanEyes(Minecraft.getInstance(), 0.8),
							null
						}
				});*/
		/*oldMorphsMap.put("dokudoku", new Object[][]
				{
						{ 
							"venomDemon", 
							new RenderZoanMorph.Factory(new ModelVenomDemon(), "venomdemon", 1.1, new float[] { 0, 1.7f, 0 }), 
							//new EntityRendererZoanEyes(Minecraft.getInstance(), 1.6),
							null
						},
				});*/
		oldMorphsMap.put("yomiyomi", new Object[][]
				{
						{ 
							"yomi", 
							new RenderMorphYomi.Factory(new ModelYomi(), "skeleton", 1.1, new float[] { 0, 0.3f, 0 }), 
							null,
							new float[] {-0.25F, 0.6F, -0.05F}
						},
				});
		/*oldMorphsMap.put("mogumogu", new Object[][]
				{
						{ 
							"power",
							new RenderZoanMorph.Factory(new ModelMoguPower(), "mogu", 0.9, new float[] { 0, 0.1f, 0 }),
							//new EntityRendererZoanEyes(Minecraft.getInstance(), -0.2),
							null
						},
				});*/
		oldMorphsMap.put("ushiushigiraffe", new Object[][]
				{
						{ 
							"power", 
							new RenderZoanMorph.Factory(new ModelGiraffePower(), "giraffehybrid", 1.4, new float[] { 0, 0.7f, 0 }), 
							//new EntityRendererZoanEyes(Minecraft.getInstance(), 1.8),
							null
						},
						{ 
							"speed", 
							new RenderZoanMorph.Factory(new ModelGiraffeSpeed(), "giraffefull", 1.55, new float[] { 0, 0.95f, 0 }), 
							//new EntityRendererZoanEyes(Minecraft.getInstance(), 1.9),
							null
						},
				});
		/*oldMorphsMap.put("minimini", new Object[][]
				{
						{ 
							"mini", 
							new RenderZoanMorph.Factory(new ModelBiped(), "$playerskin", 0.15, new float[] { 0, -0.9f, 0 }), 
							//new EntityRendererZoanEyes(Minecraft.getInstance(), -0.8),
							null
						},
				});*/
	}
	
}
