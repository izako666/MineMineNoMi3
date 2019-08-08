package xyz.pixelatedw.mineminenomi.entities.zoan.models;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelZouFull extends ModelZoanMorph
{
	public RendererModel head;
	public RendererModel rightear;
	public RendererModel leftear;
	public RendererModel body1;
	public RendererModel body2;
	public RendererModel leg1;
	public RendererModel leg2;
	public RendererModel leg3;
	public RendererModel leg4;
	public RendererModel snout;
	public RendererModel snout2;
	public RendererModel snout3;
	public RendererModel tuskA1;
	public RendererModel tuskA2;
	public RendererModel tuskB1;
	public RendererModel tuskB2;
	public RendererModel tail1;
	public RendererModel tail2;

	public ModelZouFull()
	{
		this.textureWidth = 128;
		this.textureHeight = 64;
        this.snout = new RendererModel(this, 108, 8);
        this.snout.setRotationPoint(0.0F, 6.0F, -4.5F);
        this.snout.addBox(-2.0F, 0.0F, -2.0F, 4, 7, 4, 0.0F);
        this.setRotateAngle(snout, -0.17453292519943295F, -0.0F, 0.0F);
        this.tail1 = new RendererModel(this, 108, 0);
        this.tail1.setRotationPoint(0.0F, -7.0F, 15.5F);
        this.tail1.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
        this.setRotateAngle(tail1, 0.3490658503988659F, -0.0F, 0.0F);
        this.leg4 = new RendererModel(this, 0, 46);
        this.leg4.setRotationPoint(5.0F, 11.0F, -9.5F);
        this.leg4.addBox(-2.5F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
        this.head = new RendererModel(this, 0, 0);
        this.head.setRotationPoint(0.0F, -1.0F, -14.0F);
        this.head.addBox(-4.0F, -4.0F, -6.0F, 8, 11, 9, 0.0F);
        this.tuskA2 = new RendererModel(this, 15, 27);
        this.tuskA2.setRotationPoint(0.0F, 3.8F, 0.0F);
        this.tuskA2.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(tuskA2, -0.17453292519943295F, 0.0F, 0.0F);
        this.snout2 = new RendererModel(this, 108, 20);
        this.snout2.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.snout2.addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F);
        this.setRotateAngle(snout2, 0.17453292519943295F, -0.0F, 0.0F);
        this.body2 = new RendererModel(this, 36, 25);
        this.body2.setRotationPoint(0.0F, 9.0F, -4.0F);
        this.body2.addBox(-7.5F, -12.0F, -8.0F, 15, 15, 24, 0.0F);
        this.tuskB1 = new RendererModel(this, 15, 21);
        this.tuskB1.setRotationPoint(2.3F, 5.0F, -5.0F);
        this.tuskB1.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(tuskB1, -0.3490658503988659F, -0.20943951023931953F, 0.0F);
        this.leg3 = new RendererModel(this, 0, 46);
        this.leg3.setRotationPoint(-5.0F, 11.0F, -9.5F);
        this.leg3.addBox(-2.5F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
        this.tuskA1 = new RendererModel(this, 15, 21);
        this.tuskA1.setRotationPoint(-2.3F, 5.0F, -5.0F);
        this.tuskA1.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(tuskA1, -0.3490658503988659F, 0.20943951023931953F, 0.0F);
        this.tail2 = new RendererModel(this, 113, 0);
        this.tail2.setRotationPoint(0.0F, 5.5F, 0.0F);
        this.tail2.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2, 0.0F);
        this.body1 = new RendererModel(this, 36, 0);
        this.body1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body1.addBox(-6.5F, -13.0F, -7.0F, 13, 2, 22, 0.0F);
        this.leg2 = new RendererModel(this, 0, 46);
        this.leg2.setRotationPoint(5.0F, 11.0F, 9.5F);
        this.leg2.addBox(-2.5F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
        this.tuskB2 = new RendererModel(this, 15, 27);
        this.tuskB2.setRotationPoint(0.0F, 3.8F, 0.0F);
        this.tuskB2.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(tuskB2, -0.17453292519943295F, 0.0F, 0.0F);
        this.leg1 = new RendererModel(this, 0, 46);
        this.leg1.setRotationPoint(-5.0F, 11.0F, 9.5F);
        this.leg1.addBox(-2.5F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
        this.leftear = new RendererModel(this, 0, 21);
        this.leftear.mirror = true;
        this.leftear.setRotationPoint(3.0F, -0.5F, -2.5F);
        this.leftear.addBox(0.0F, -4.5F, -0.5F, 6, 9, 1, 0.0F);
        this.setRotateAngle(leftear, -0.13683381335635544F, -0.47071529926287065F, 0.29461157773664276F);
        this.rightear = new RendererModel(this, 0, 21);
        this.rightear.setRotationPoint(-3.0F, -0.5F, -2.5F);
        this.rightear.addBox(-6.0F, -4.5F, -0.5F, 6, 9, 1, 0.0F);
        this.setRotateAngle(rightear, -0.13683381335635544F, 0.47071529926287065F, -0.29461157773664276F);
        this.snout3 = new RendererModel(this, 108, 30);
        this.snout3.setRotationPoint(0.0F, 5.5F, 0.5F);
        this.snout3.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(snout3, 0.17453292519943295F, -0.0F, 0.0F);
        this.head.addChild(this.snout);
        this.body2.addChild(this.tail1);
        this.tuskA1.addChild(this.tuskA2);
        this.snout.addChild(this.snout2);
        this.head.addChild(this.tuskB1);
        this.head.addChild(this.tuskA1);
        this.tail1.addChild(this.tail2);
        this.body2.addChild(this.body1);
        this.tuskB1.addChild(this.tuskB2);
        this.head.addChild(this.leftear);
        this.head.addChild(this.rightear);
        this.snout2.addChild(this.snout3);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		this.tuskA2.render(f5);
		this.head.render(f5);
		this.body2.render(f5);
		this.leg3.render(f5);
		this.leg1.render(f5);
		this.tuskB2.render(f5);
		this.leg4.render(f5);
		this.tuskB1.render(f5);
		this.leg2.render(f5);
		this.tuskA1.render(f5);
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scaleFactor, Entity ent)
	{
		this.head.rotateAngleY = headYaw / (270F / (float) Math.PI);
		this.head.rotateAngleX = headPitch / (360F / (float) Math.PI);
		
		this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.3F * limbSwingAmount;
		this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.3F * limbSwingAmount;

		this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.4F * limbSwingAmount;
		this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.4F * limbSwingAmount;
	}

	public void setRotateAngle(RendererModel RendererModel, float x, float y, float z)
	{
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}

	@Override
	public RendererModel getHandRenderer()
	{
		return null;
	}
}
