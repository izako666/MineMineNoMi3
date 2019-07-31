package xyz.pixelatedw.mineminenomi.models.entities.projectiles;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class ModelHeart extends EntityModel
{
	public RendererModel heart1;
	public RendererModel heart2;
	public RendererModel heart3;
	public RendererModel heart4;
	public RendererModel heart5;
	public RendererModel heart6;
	public RendererModel heart7;
	public RendererModel heart8;
	public RendererModel heart9;
	public RendererModel heart10;
	public RendererModel heart11;
	public RendererModel heart12;
	public RendererModel heart13;
	public RendererModel heart14;
	public RendererModel heart15;
	public RendererModel heart16;
	public RendererModel heart17;
	public RendererModel heart18;
	public RendererModel pellicle;

	public ModelHeart()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.heart1 = new RendererModel(this, 0, 0);
		this.heart1.mirror = true;
		this.heart1.setRotationPoint(0.3F, 0.0F, 0.0F);
		this.heart1.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(heart1, 0.0F, -0.0F, 2.3387411976724017F);
		this.heart2 = new RendererModel(this, 0, 0);
		this.heart2.setRotationPoint(-0.3F, 0.0F, 0.0F);
		this.heart2.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(heart2, 0.0F, -0.0F, -2.3387411976724017F);
		this.heart4 = new RendererModel(this, 0, 0);
		this.heart4.setRotationPoint(3.2F, -3.3F, 0.0F);
		this.heart4.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(heart4, 0.0F, -0.0F, -2.495820830351891F);
		this.heart6 = new RendererModel(this, 0, 0);
		this.heart6.setRotationPoint(6.2F, -7.1F, 0.0F);
		this.heart6.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(heart6, 0.0F, -0.0F, -2.652900463031381F);
		this.heart14 = new RendererModel(this, 10, 0);
		this.heart14.setRotationPoint(7.1F, -20.4F, 0.0F);
		this.heart14.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(heart14, 0.0F, -0.0F, 1.413716694115407F);
		this.heart16 = new RendererModel(this, 10, 0);
		this.heart16.setRotationPoint(4.4F, -20.0F, 0.0F);
		this.heart16.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(heart16, 0.0F, -0.0F, 1.0122909661567112F);
		this.heart5 = new RendererModel(this, 0, 0);
		this.heart5.setRotationPoint(-6.2F, -7.1F, 0.0F);
		this.heart5.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(heart5, 0.0F, -0.0F, 2.652900463031381F);
		this.heart15 = new RendererModel(this, 10, 0);
		this.heart15.setRotationPoint(-4.4F, -20.0F, 0.0F);
		this.heart15.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(heart15, 0.0F, -0.0F, -1.0122909661567112F);
		this.heart17 = new RendererModel(this, 10, 0);
		this.heart17.setRotationPoint(-2.0F, -18.5F, 0.0F);
		this.heart17.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(heart17, 0.0F, -0.0F, -0.8726646259971648F);
		this.heart8 = new RendererModel(this, 0, 0);
		this.heart8.setRotationPoint(8.6F, -11.3F, 0.0F);
		this.heart8.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(heart8, 0.0F, -0.0F, -2.8623399732707004F);
		this.heart9 = new RendererModel(this, 5, 0);
		this.heart9.setRotationPoint(-9.8F, -14.9F, 0.0F);
		this.heart9.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(heart9, 0.0F, -0.0F, -2.897246558310587F);
		this.pellicle = new RendererModel(this, 15, 0);
		this.pellicle.setRotationPoint(0.0F, -12.0F, 0.0F);
		this.pellicle.addBox(-9.5F, -9.0F, 0.0F, 19, 21, 0, 0.0F);
		this.heart11 = new RendererModel(this, 10, 0);
		this.heart11.setRotationPoint(-8.9F, -18.5F, 0.0F);
		this.heart11.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(heart11, 0.0F, -0.0F, -2.321287905152458F);
		this.heart13 = new RendererModel(this, 10, 0);
		this.heart13.mirror = true;
		this.heart13.setRotationPoint(-7.1F, -20.4F, 0.0F);
		this.heart13.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(heart13, 0.0F, -0.0F, -1.413716694115407F);
		this.heart10 = new RendererModel(this, 5, 0);
		this.heart10.setRotationPoint(9.8F, -14.9F, 0.0F);
		this.heart10.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(heart10, 0.0F, -0.0F, 2.897246558310587F);
		this.heart7 = new RendererModel(this, 0, 0);
		this.heart7.setRotationPoint(-8.6F, -11.3F, 0.0F);
		this.heart7.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(heart7, 0.0F, -0.0F, 2.8623399732707004F);
		this.heart3 = new RendererModel(this, 0, 0);
		this.heart3.setRotationPoint(-3.2F, -3.3F, 0.0F);
		this.heart3.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(heart3, 0.0F, -0.0F, 2.495820830351891F);
		this.heart12 = new RendererModel(this, 10, 0);
		this.heart12.setRotationPoint(8.9F, -18.5F, 0.0F);
		this.heart12.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(heart12, 0.0F, -0.0F, 2.321287905152458F);
		this.heart18 = new RendererModel(this, 10, 0);
		this.heart18.setRotationPoint(2.0F, -18.5F, 0.0F);
		this.heart18.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(heart18, 0.0F, -0.0F, 0.8726646259971648F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.heart1.render(f5);
		this.heart2.render(f5);
		this.heart4.render(f5);
		this.heart6.render(f5);
		this.heart14.render(f5);
		this.heart16.render(f5);
		this.heart5.render(f5);
		this.heart15.render(f5);
		this.heart17.render(f5);
		this.heart8.render(f5);
		this.heart9.render(f5);
		this.pellicle.render(f5);
		this.heart11.render(f5);
		this.heart13.render(f5);
		this.heart10.render(f5);
		this.heart7.render(f5);
		this.heart3.render(f5);
		this.heart12.render(f5);
		this.heart18.render(f5);
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
