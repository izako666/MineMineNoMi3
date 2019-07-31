package xyz.pixelatedw.mineminenomi.models.effects;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class ModelChains extends EntityModel
{
	public RendererModel shape15;

	public ModelChains()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.shape15 = new RendererModel(this, 0, 0);
		this.shape15.setRotationPoint(0.0F, 5.8F, 0.0F);
		this.shape15.addBox(-9.0F, 0.0F, -6.0F, 18, 5, 12, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.shape15.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(RendererModel RendererModel, float x, float y, float z)
	{
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}
}