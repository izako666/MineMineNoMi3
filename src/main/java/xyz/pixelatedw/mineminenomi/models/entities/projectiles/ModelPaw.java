package xyz.pixelatedw.mineminenomi.models.entities.projectiles;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class ModelPaw extends EntityModel
{
	public RendererModel pawA1;
	public RendererModel pawA2;
	public RendererModel pawA3;
	public RendererModel pawA4;
	public RendererModel pawA5;
	public RendererModel pawB1;
	public RendererModel pawB2;
	public RendererModel pawB3;
	public RendererModel pawB4;
	public RendererModel pawC1;
	public RendererModel pawC2;
	public RendererModel pawC3;
	public RendererModel pawC4;
	public RendererModel pawD1;
	public RendererModel pawD2;
	public RendererModel pawD3;
	public RendererModel pawD4;
	public RendererModel pawE1;
	public RendererModel pawE2;
	public RendererModel pawE3;
	public RendererModel pawE4;

	public ModelPaw()
	{
		this.textureWidth = 160;
		this.textureHeight = 80;
		this.pawE2 = new RendererModel(this, 94, 34);
		this.pawE2.setRotationPoint(9.5F, -10.0F, 0.0F);
		this.pawE2.addBox(-1.5F, -1.5F, -2.5F, 3, 3, 5, 0.0F);
		this.setRotateAngle(pawE2, 0.0F, -0.0F, 0.7853981852531433F);
		this.pawA4 = new RendererModel(this, 0, 25);
		this.pawA4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.pawA4.addBox(-8.0F, -7.0F, -4.0F, 16, 14, 8, 0.0F);
		this.pawC3 = new RendererModel(this, 111, 25);
		this.pawC3.setRotationPoint(-3.0F, -12.0F, 0.0F);
		this.pawC3.addBox(-2.5F, -1.5F, -1.5F, 5, 3, 3, 0.0F);
		this.setRotateAngle(pawC3, 0.0F, -0.0F, -0.08726646006107329F);
		this.pawD1 = new RendererModel(this, 94, 25);
		this.pawD1.setRotationPoint(3.0F, -12.0F, 0.0F);
		this.pawD1.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F);
		this.setRotateAngle(pawD1, 0.0F, -0.0F, 0.08726646006107329F);
		this.pawB1 = new RendererModel(this, 94, 25);
		this.pawB1.setRotationPoint(-9.5F, -10.0F, 0.0F);
		this.pawB1.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F);
		this.setRotateAngle(pawB1, 0.0F, -0.0F, -0.7853981852531433F);
		this.pawA5 = new RendererModel(this, 49, 25);
		this.pawA5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.pawA5.addBox(-7.0F, -8.0F, -4.0F, 14, 16, 8, 0.0F);
		this.pawD2 = new RendererModel(this, 94, 34);
		this.pawD2.setRotationPoint(3.0F, -12.0F, 0.0F);
		this.pawD2.addBox(-1.5F, -1.5F, -2.5F, 3, 3, 5, 0.0F);
		this.setRotateAngle(pawD2, 0.0F, -0.0F, 0.08726646006107329F);
		this.pawD4 = new RendererModel(this, 111, 34);
		this.pawD4.setRotationPoint(3.0F, -12.0F, 0.0F);
		this.pawD4.addBox(-1.5F, -2.5F, -1.5F, 3, 5, 3, 0.0F);
		this.setRotateAngle(pawD4, 0.0F, -0.0F, 0.08726646006107329F);
		this.pawC1 = new RendererModel(this, 94, 25);
		this.pawC1.setRotationPoint(-3.0F, -12.0F, 0.0F);
		this.pawC1.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F);
		this.setRotateAngle(pawC1, 0.0F, -0.0F, -0.08726646006107329F);
		this.pawA1 = new RendererModel(this, 0, 0);
		this.pawA1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.pawA1.addBox(-7.5F, -7.5F, -4.5F, 15, 15, 9, 0.0F);
		this.pawA2 = new RendererModel(this, 49, 0);
		this.pawA2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.pawA2.addBox(-7.0F, -7.0F, -5.0F, 14, 14, 10, 0.0F);
		this.pawE3 = new RendererModel(this, 111, 25);
		this.pawE3.setRotationPoint(9.5F, -10.0F, 0.0F);
		this.pawE3.addBox(-2.5F, -1.5F, -1.5F, 5, 3, 3, 0.0F);
		this.setRotateAngle(pawE3, 0.0F, -0.0F, 0.7853981852531433F);
		this.pawC2 = new RendererModel(this, 94, 34);
		this.pawC2.setRotationPoint(-3.0F, -12.0F, 0.0F);
		this.pawC2.addBox(-1.5F, -1.5F, -2.5F, 3, 3, 5, 0.0F);
		this.setRotateAngle(pawC2, 0.0F, -0.0F, -0.08726646006107329F);
		this.pawB4 = new RendererModel(this, 111, 34);
		this.pawB4.setRotationPoint(-9.5F, -10.0F, 0.0F);
		this.pawB4.addBox(-1.5F, -2.5F, -1.5F, 3, 5, 3, 0.0F);
		this.setRotateAngle(pawB4, 0.0F, -0.0F, -0.7853981852531433F);
		this.pawD3 = new RendererModel(this, 111, 25);
		this.pawD3.setRotationPoint(3.0F, -12.0F, 0.0F);
		this.pawD3.addBox(-2.5F, -1.5F, -1.5F, 5, 3, 3, 0.0F);
		this.setRotateAngle(pawD3, 0.0F, -0.0F, 0.08726646006107329F);
		this.pawC4 = new RendererModel(this, 111, 34);
		this.pawC4.setRotationPoint(-3.0F, -12.0F, 0.0F);
		this.pawC4.addBox(-1.5F, -2.5F, -1.5F, 3, 5, 3, 0.0F);
		this.setRotateAngle(pawC4, 0.0F, -0.0F, -0.08726646006107329F);
		this.pawB3 = new RendererModel(this, 111, 25);
		this.pawB3.setRotationPoint(-9.5F, -10.0F, 0.0F);
		this.pawB3.addBox(-2.5F, -1.5F, -1.5F, 5, 3, 3, 0.0F);
		this.setRotateAngle(pawB3, 0.0F, -0.0F, -0.7853981852531433F);
		this.pawB2 = new RendererModel(this, 94, 34);
		this.pawB2.setRotationPoint(-9.5F, -10.0F, 0.0F);
		this.pawB2.addBox(-1.5F, -1.5F, -2.5F, 3, 3, 5, 0.0F);
		this.setRotateAngle(pawB2, 0.0F, -0.0F, -0.7853981852531433F);
		this.pawE1 = new RendererModel(this, 94, 25);
		this.pawE1.setRotationPoint(9.5F, -10.0F, 0.0F);
		this.pawE1.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F);
		this.setRotateAngle(pawE1, 0.0F, -0.0F, 0.7853981852531433F);
		this.pawA3 = new RendererModel(this, 98, 0);
		this.pawA3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.pawA3.addBox(-6.5F, -6.5F, -5.5F, 13, 13, 11, 0.0F);
		this.pawE4 = new RendererModel(this, 111, 34);
		this.pawE4.setRotationPoint(9.5F, -10.0F, 0.0F);
		this.pawE4.addBox(-1.5F, -2.5F, -1.5F, 3, 5, 3, 0.0F);
		this.setRotateAngle(pawE4, 0.0F, -0.0F, 0.7853981852531433F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.pawE2.render(f5);
		this.pawA4.render(f5);
		this.pawC3.render(f5);
		this.pawD1.render(f5);
		this.pawB1.render(f5);
		this.pawA5.render(f5);
		this.pawD2.render(f5);
		this.pawD4.render(f5);
		this.pawC1.render(f5);
		this.pawA1.render(f5);
		this.pawA2.render(f5);
		this.pawE3.render(f5);
		this.pawC2.render(f5);
		this.pawB4.render(f5);
		this.pawD3.render(f5);
		this.pawC4.render(f5);
		this.pawB3.render(f5);
		this.pawB2.render(f5);
		this.pawE1.render(f5);
		this.pawA3.render(f5);
		this.pawE4.render(f5);
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
