package xyz.pixelatedw.mineminenomi.models.entities.projectiles;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class ModelArrow extends EntityModel
{
	public RendererModel body;
	public RendererModel tail1;
	public RendererModel tail2;
	public RendererModel head1;
	public RendererModel head2;

	public ModelArrow()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.body = new RendererModel(this, 0, 0);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-0.5F, 0.0F, -5.0F, 1, 1, 10, 0.0F);
		this.tail1 = new RendererModel(this, 15, 0);
		this.tail1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail1.addBox(-5.0F, -1.7F, 0.4F, 4, 4, 0, 0.0F);
		this.setRotateAngle(tail1, 0.0F, 1.5707963267948966F, 0.7853981633974483F);
		this.tail2 = new RendererModel(this, 15, 5);
		this.tail2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail2.addBox(-5.0F, -1.8F, -0.3F, 4, 4, 0, 0.0F);
		this.setRotateAngle(tail2, 0.0F, 1.5707963267948966F, -0.7853981633974483F);
		this.head2 = new RendererModel(this, 0, 4);
		this.head2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head2.addBox(-0.5F, 0.0F, -7.0F, 1, 1, 1, 0.0F);
		this.head1 = new RendererModel(this, 0, 0);
		this.head1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head1.addBox(-1.0F, -0.5F, -6.0F, 2, 2, 1, 0.0F);
		this.body.addChild(this.tail1);
		this.body.addChild(this.tail2);
		this.head1.addChild(this.head2);
		this.body.addChild(this.head1);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.body.render(f5);
	}

	public void setRotateAngle(RendererModel RendererModel, float x, float y, float z)
	{
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}
}
