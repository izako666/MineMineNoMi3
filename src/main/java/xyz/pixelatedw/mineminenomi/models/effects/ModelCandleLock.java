package xyz.pixelatedw.mineminenomi.models.effects;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class ModelCandleLock extends EntityModel
{
	public RendererModel CandleLock1;
	public RendererModel CandleLock2;
	public RendererModel CandleLock3;

	public ModelCandleLock()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.CandleLock3 = new RendererModel(this, 0, 17);
		this.CandleLock3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.CandleLock3.addBox(7.5F, -4.0F, -4.0F, 4, 8, 8, 0.0F);
		this.CandleLock1 = new RendererModel(this, 0, 0);
		this.CandleLock1.setRotationPoint(0.0F, 18.9F, 0.0F);
		this.CandleLock1.addBox(-7.5F, -3.5F, -3.5F, 15, 7, 7, 0.0F);
		this.CandleLock2 = new RendererModel(this, 0, 36);
		this.CandleLock2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.CandleLock2.addBox(-11.5F, -4.0F, -4.0F, 4, 8, 8, 0.0F);
		this.CandleLock1.addChild(this.CandleLock3);
		this.CandleLock1.addChild(this.CandleLock2);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.CandleLock1.render(f5);
	}

	public void setRotateAngle(RendererModel RendererModel, float x, float y, float z)
	{
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}
}
