package xyz.pixelatedw.MineMineNoMi3.entities.zoan.renderers;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.RenderZoanMorph;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.models.ModelYomi;

public class RenderMorphYomi extends RenderZoanMorph
{

	private ModelYomi model;
	
	public RenderMorphYomi(RenderManager renderManager, ModelYomi model, String texture, double scale, float[] offset)
	{
		super(renderManager, model, texture, scale, offset);
		this.model = model;
	}

	public void doRender(Entity entity, double x, double y, double z, float u, float v)
	{
		super.doRender(entity, x, y, z, u, v);
		
		this.model.afro.isHidden = true;
		if(entity instanceof PlayerEntity)
		{
			/*int age = ((PlayerEntity) entity).ttick;
			if(age > 2000)
			{
				this.model.afro.isHidden = false;
			}*/
		}
	}
	
	public static class Factory implements IRenderFactory<PlayerEntity>
	{
		private ModelYomi model;
		private String texture;
		private double scale;
		private float[] offset;
		
		public Factory(ModelYomi model, String texture, double scale, float[] offset)
		{
			this.model = model;
			this.texture = texture;
			this.scale = scale;
			this.offset = offset;
		}
		
		@Override
		public Render<? super PlayerEntity> createRenderFor(RenderManager manager)
		{
			return new RenderMorphYomi(manager, this.model, this.texture, this.scale, this.offset);
		}
	}
}
