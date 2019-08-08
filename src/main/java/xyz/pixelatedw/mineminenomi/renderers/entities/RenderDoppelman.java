package xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.renderers;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.MineMineNoMi3.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.MobRenderer;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.models.ModelMarine;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.misc.EntityDoppelman;

@OnlyIn(Dist.CLIENT)
public class RenderDoppelman extends MobRenderer
{

	public RenderDoppelman(RenderManager renderManager)
	{
		super(renderManager, new ModelMarine(), "doppelman");
	}
	
	protected void preRenderCallback(EntityLivingBase livingBase, float f) 
	{
		float scale = 1 + ((float)EntityStatsCapability.get(livingBase).getDoriki() / 7);

		if(scale < 1)
			scale = 1;
		
		GL11.glScalef(scale, scale, scale);
	}
	
	public static class Factory implements IRenderFactory<EntityDoppelman>
	{
		public Factory() {}
		
		@Override
		public Render<? super EntityDoppelman> createRenderFor(RenderManager manager)
		{
			return new RenderDoppelman(manager);
		}
	}
}
