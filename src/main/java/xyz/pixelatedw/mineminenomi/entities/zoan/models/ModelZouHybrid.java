package xyz.pixelatedw.MineMineNoMi3.entities.zoan.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class ModelZouHybrid extends ModelZoanMorph
{
	public RendererModel body1;
	public RendererModel body2;
	public RendererModel rightleg1;
	public RendererModel rightleg2;
	public RendererModel leftleg1;
	public RendererModel leftleg2;
	public RendererModel rightarm1;
	public RendererModel rightarm2;
	public RendererModel leftarm1;
	public RendererModel leftarm2;
	public RendererModel tail1;
	public RendererModel tail2;
	public RendererModel head;
	public RendererModel rightear;
	public RendererModel leftear;
	public RendererModel snout;
	public RendererModel snout2;
	public RendererModel snout3;
	public RendererModel tuskA1;
	public RendererModel tuskA2;
	public RendererModel tuskB1;
	public RendererModel tuskB2;

	public ModelZouHybrid()
	{
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.leftleg1 = new RendererModel(this, 42, 46);
		this.leftleg1.setRotationPoint(5.0F, 11.100000381469727F, 1.0F);
		this.leftleg1.addBox(-2.5F, 0.0F, -2.5F, 5, 7, 5, 0.0F);
		this.setRotateAngle(leftleg1, -0.05235987901687623F, -0.0F, 0.0F);
		this.body1 = new RendererModel(this, 35, 0);
		this.body1.setRotationPoint(0.0F, -2.5999999046325684F, 2.700000047683716F);
		this.body1.addBox(-9.0F, -10.0F, -7.0F, 18, 14, 10, 0.0F);
		this.setRotateAngle(body1, 0.08726646006107329F, 0.0F, 0.0F);
		this.snout = new RendererModel(this, 112, 35);
		this.snout.setRotationPoint(-2.0F, -1.5F, -4.5F);
		this.snout.addBox(0.0F, 0.0F, 0.0F, 4, 7, 4, 0.0F);
		this.setRotateAngle(snout, -0.20943951023931953F, -0.0F, 0.0F);
		this.head = new RendererModel(this, 0, 0);
		this.head.setRotationPoint(0.0F, -12.199999809265137F, -3.0F);
		this.head.addBox(-4.0F, -9.0F, -4.5F, 8, 9, 9, 0.0F);
		this.setRotateAngle(head, 0.05235987901687623F, -0.0F, 0.0F);
		this.tail2 = new RendererModel(this, 119, 0);
		this.tail2.setRotationPoint(0.0F, 5.5F, 0.0F);
		this.tail2.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2, 0.0F);
		this.leftear = new RendererModel(this, 0, 19);
		this.leftear.mirror = true;
		this.leftear.setRotationPoint(3.5F, -3.8F, -0.0F);
		this.leftear.addBox(0.0F, -6.0F, 0.0F, 6, 8, 1, 0.0F);
		this.setRotateAngle(leftear, -0.23649211364523165F, -1.0306169233026516F, 0.27401669256310973F);
		this.tuskB1 = new RendererModel(this, 15, 19);
		this.tuskB1.setRotationPoint(2.3F, -1.0F, -3.5F);
		this.tuskB1.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(tuskB1, -0.3490658503988659F, -0.17453292519943295F, 0.0F);
		this.rightarm1 = new RendererModel(this, 0, 46);
		this.rightarm1.setRotationPoint(-9.0F, -9.600000381469727F, 1.0F);
		this.rightarm1.addBox(-5.0F, -2.0F, -2.5F, 5, 10, 5, 0.0F);
		this.setRotateAngle(rightarm1, 0.0F, -0.0F, 0.03490658476948738F);
		this.tuskA1 = new RendererModel(this, 15, 19);
		this.tuskA1.setRotationPoint(-2.3F, -1.0F, -3.5F);
		this.tuskA1.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(tuskA1, -0.3490658503988659F, 0.17453292519943295F, 0.0F);
		this.rightleg1 = new RendererModel(this, 42, 46);
		this.rightleg1.setRotationPoint(-5.0F, 11.100000381469727F, 1.0F);
		this.rightleg1.addBox(-2.5F, 0.0F, -2.5F, 5, 7, 5, 0.0F);
		this.setRotateAngle(rightleg1, -0.05235987901687623F, -0.0F, 0.0F);
		this.leftarm1 = new RendererModel(this, 0, 46);
		this.leftarm1.setRotationPoint(9.0F, -9.600000381469727F, 1.0F);
		this.leftarm1.addBox(0.0F, -2.0F, -2.5F, 5, 10, 5, 0.0F);
		this.setRotateAngle(leftarm1, 0.0F, -0.0F, -0.03490658476948738F);
		this.snout2 = new RendererModel(this, 116, 47);
		this.snout2.setRotationPoint(0.5F, 7.0F, 0.5F);
		this.snout2.addBox(0.0F, 0.0F, 0.0F, 3, 6, 3, 0.0F);
		this.setRotateAngle(snout2, 0.20943951023931953F, -0.0F, 0.0F);
		this.rightarm2 = new RendererModel(this, 21, 46);
		this.rightarm2.setRotationPoint(-2.5F, 7.8F, 0.0F);
		this.rightarm2.addBox(-2.5F, 0.0F, -2.5F, 5, 10, 5, 0.0F);
		this.setRotateAngle(rightarm2, 0.0F, -0.0F, -0.03490658503988659F);
		this.tuskB2 = new RendererModel(this, 15, 25);
		this.tuskB2.setRotationPoint(0.0F, 3.8F, 0.0F);
		this.tuskB2.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(tuskB2, -0.17453292519943295F, -0.017453292519943295F, 0.0F);
		this.rightleg2 = new RendererModel(this, 63, 46);
		this.rightleg2.setRotationPoint(0.0F, 6.8F, 0.0F);
		this.rightleg2.addBox(-2.5F, 0.0F, -2.5F, 5, 6, 5, 0.0F);
		this.setRotateAngle(rightleg2, 0.05235987755982988F, -0.0F, 0.0F);
		this.tuskA2 = new RendererModel(this, 15, 25);
		this.tuskA2.setRotationPoint(0.0F, 3.9F, 0.0F);
		this.tuskA2.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(tuskA2, -0.17453292519943295F, 0.017453292519943295F, 0.0F);
		this.leftarm2 = new RendererModel(this, 21, 46);
		this.leftarm2.setRotationPoint(2.5F, 7.8F, 0.0F);
		this.leftarm2.addBox(-2.5F, 0.0F, -2.5F, 5, 10, 5, 0.0F);
		this.setRotateAngle(leftarm2, 0.0F, -0.0F, 0.03490658503988659F);
		this.tail1 = new RendererModel(this, 114, 0);
		this.tail1.setRotationPoint(0.0F, -2.5F, 2.0F);
		this.tail1.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
		this.setRotateAngle(tail1, 0.4363323129985824F, -0.0F, 0.0F);
		this.body2 = new RendererModel(this, 35, 25);
		this.body2.setRotationPoint(0.0F, 11.199999809265137F, 3.0F);
		this.body2.addBox(-8.5F, -10.0F, -7.0F, 17, 10, 10, 0.0F);
		this.snout3 = new RendererModel(this, 120, 57);
		this.snout3.setRotationPoint(0.5F, 5.5F, 0.5F);
		this.snout3.addBox(0.0F, 0.0F, 0.0F, 2, 5, 2, 0.0F);
		this.setRotateAngle(snout3, 0.20943951023931953F, -0.0F, 0.0F);
		this.leftleg2 = new RendererModel(this, 63, 46);
		this.leftleg2.setRotationPoint(0.0F, 6.8F, 0.0F);
		this.leftleg2.addBox(-2.5F, 0.0F, -2.5F, 5, 6, 5, 0.0F);
		this.setRotateAngle(leftleg2, 0.05235987755982988F, -0.0F, 0.0F);
		this.rightear = new RendererModel(this, 0, 19);
		this.rightear.setRotationPoint(-3.5F, -3.8F, -1.0F);
		this.rightear.addBox(-6.0F, -6.0F, 0.0F, 6, 8, 1, 0.0F);
		this.setRotateAngle(rightear, -0.23649211364523165F, 1.0306169233026516F, -0.27401669256310973F);
		this.head.addChild(this.snout);
		this.tail1.addChild(this.tail2);
		this.head.addChild(this.leftear);
		this.head.addChild(this.tuskB1);
		this.head.addChild(this.tuskA1);
		this.snout.addChild(this.snout2);
		this.rightarm1.addChild(this.rightarm2);
		this.tuskB1.addChild(this.tuskB2);
		this.rightleg1.addChild(this.rightleg2);
		this.tuskA1.addChild(this.tuskA2);
		this.leftarm1.addChild(this.leftarm2);
		this.body2.addChild(this.tail1);
		this.snout2.addChild(this.snout3);
		this.leftleg1.addChild(this.leftleg2);
		this.head.addChild(this.rightear);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		this.leftleg1.render(f5);
		this.body1.render(f5);
		this.head.render(f5);
		this.rightarm1.render(f5);
		this.rightleg1.render(f5);
		this.leftarm1.render(f5);
		this.body2.render(f5);
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scaleFactor, Entity ent)
	{
		LivingEntity entity = ((LivingEntity) ent);
		
		this.head.rotateAngleY = headYaw / (270F / (float) Math.PI);
		this.head.rotateAngleX = headPitch / (360F / (float) Math.PI);
		
		this.leftleg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount;
		this.rightleg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount;

		this.rightarm1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.4F * limbSwingAmount;
		this.leftarm1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.4F * limbSwingAmount;

		if (entity.isSwingInProgress)
		{
			this.rightarm1.rotateAngleX = MathHelper.sin(entity.swingProgress * 3.0F + (float) Math.PI) * 1.2F;
			this.rightarm1.rotateAngleY = MathHelper.sin(entity.swingProgress * 3.0F + (float) Math.PI) * -0.2F;
			this.rightarm1.rotateAngleZ = -MathHelper.cos(entity.swingProgress * 4.0F + (float) Math.PI) * 0.5F;
		}

		if (ent.getDistance(ent.prevPosX, ent.prevPosY, ent.prevPosZ) <= 0.05F && !entity.isSwingInProgress)
		{
			this.rightarm1.rotateAngleX = 0;
			this.rightarm1.rotateAngleY = 0;
			this.rightarm1.rotateAngleZ = 0.025F;
		}
		else if (!entity.isSwingInProgress && ent.getDistance(ent.prevPosX, ent.prevPosY, ent.prevPosZ) > 0)
		{
			this.rightarm1.rotateAngleY = 0;
			this.rightarm1.rotateAngleZ = 0.025F;
		}
	}
	
	public void setRotateAngle(RendererModel RendererModel, float x, float y, float z)
	{
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}

	public RendererModel getHandRenderer()
	{
		GL11.glScaled(1.2, 1.2, 1);
		GL11.glTranslated(-0.1, -0.25, 0.05);
		GL11.glRotated(-5, 1, 0, 0);
		GL11.glRotated(1, 0, 0, 1);
		return this.rightarm2;
	}
}
