package xyz.pixelatedw.mineminenomi.models.entities.projectiles;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class ModelNoroNoroBeam extends EntityModel
{
	public RendererModel circle1;
	public RendererModel circle2;
	public RendererModel circle3;
	public RendererModel circle4;
	public RendererModel circle5;
	public RendererModel circle6;
	public RendererModel circle7;
	public RendererModel circle8;
	public RendererModel circle9;
	public RendererModel circle10;
	public RendererModel circle11;
	public RendererModel circle12;
	public RendererModel pellicle;

	public ModelNoroNoroBeam()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.circle3 = new RendererModel(this, 0, 0);
		this.circle3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.circle3.addBox(-1.5F, -5.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(circle3, 0.0F, -0.0F, 0.7853981852531433F);
		this.circle1 = new RendererModel(this, 0, 0);
		this.circle1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.circle1.addBox(-1.5F, -5.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(circle1, 0.0F, -0.0F, -0.2617993950843811F);
		this.circle11 = new RendererModel(this, 0, 0);
		this.circle11.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.circle11.addBox(-1.5F, -5.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(circle11, 0.0F, -0.0F, -1.3089969158172607F);
		this.circle12 = new RendererModel(this, 0, 0);
		this.circle12.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.circle12.addBox(-1.5F, -5.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(circle12, 0.0F, -0.0F, -0.7853981852531433F);
		this.pellicle = new RendererModel(this, 0, 3);
		this.pellicle.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.pellicle.addBox(-5.0F, -5.0F, 0.0F, 10, 10, 0, 0.0F);
		this.circle9 = new RendererModel(this, 0, 0);
		this.circle9.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.circle9.addBox(-1.5F, -5.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(circle9, 0.0F, -0.0F, -2.356194496154785F);
		this.circle2 = new RendererModel(this, 0, 0);
		this.circle2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.circle2.addBox(-1.5F, -5.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(circle2, 0.0F, -0.0F, 0.2617993950843811F);
		this.circle10 = new RendererModel(this, 0, 0);
		this.circle10.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.circle10.addBox(-1.5F, -5.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(circle10, 0.0F, -0.0F, -1.8325957059860232F);
		this.circle7 = new RendererModel(this, 0, 0);
		this.circle7.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.circle7.addBox(-1.5F, -5.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(circle7, 0.0F, -0.0F, 2.8972465991973877F);
		this.circle5 = new RendererModel(this, 0, 0);
		this.circle5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.circle5.addBox(-1.5F, -5.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(circle5, 0.0F, -0.0F, 1.8325957059860232F);
		this.circle8 = new RendererModel(this, 0, 0);
		this.circle8.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.circle8.addBox(-1.5F, -5.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(circle8, 0.0F, -0.0F, -2.8972465991973877F);
		this.circle4 = new RendererModel(this, 0, 0);
		this.circle4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.circle4.addBox(-1.5F, -5.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(circle4, 0.0F, -0.0F, 1.3089969158172607F);
		this.circle6 = new RendererModel(this, 0, 0);
		this.circle6.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.circle6.addBox(-1.5F, -5.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(circle6, 0.0F, -0.0F, 2.356194496154785F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.circle3.render(f5);
		this.circle1.render(f5);
		this.circle11.render(f5);
		this.circle12.render(f5);
		this.pellicle.render(f5);
		this.circle9.render(f5);
		this.circle2.render(f5);
		this.circle10.render(f5);
		this.circle7.render(f5);
		this.circle5.render(f5);
		this.circle8.render(f5);
		this.circle4.render(f5);
		this.circle6.render(f5);
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
