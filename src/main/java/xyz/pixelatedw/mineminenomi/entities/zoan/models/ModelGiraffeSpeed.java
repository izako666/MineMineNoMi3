package xyz.pixelatedw.mineminenomi.entities.zoan.models;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class ModelGiraffeSpeed extends ModelZoanMorph
{
	public RendererModel frontleg2;
	public RendererModel backleg2;
	public RendererModel backleg1;
	public RendererModel frontleg21;
	public RendererModel frontleg22;
	public RendererModel backleg21;
	public RendererModel backleg22;
	public RendererModel backleg11;
	public RendererModel backleg12;
	public RendererModel body;
	public RendererModel frontleg1;
	public RendererModel neck;
	public RendererModel column;
	public RendererModel tail1;
	public RendererModel frontleg11;
	public RendererModel frontleg12;
	public RendererModel mane;
	public RendererModel head;
	public RendererModel head2;
	public RendererModel horn1;
	public RendererModel horn2;
	public RendererModel rightear;
	public RendererModel leftear;

	public ModelGiraffeSpeed()
	{
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.frontleg21 = new RendererModel(this, 65, 20);
		this.frontleg21.setRotationPoint(-2.0F, 10.0F, -3.5F);
		this.frontleg21.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
		this.setRotateAngle(frontleg21, -0.12112585008840647F, -0.4883431247080134F, -0.03944444109507184F);
		this.head = new RendererModel(this, 17, 0);
		this.head.setRotationPoint(4.0F, 2.0F, 0.5F);
		this.head.addBox(-4.0F, -4.0F, -6.0F, 4, 3, 7, 0.0F);
		this.setRotateAngle(head, -0.7853981633974483F, -0.0F, 0.0F);
		this.frontleg11 = new RendererModel(this, 65, 20);
		this.frontleg11.setRotationPoint(-2.0F, 10.0F, -3.5F);
		this.frontleg11.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
		this.setRotateAngle(frontleg11, -0.12112585008840647F, -0.4883431247080134F, -0.03944444109507184F);
		this.backleg12 = new RendererModel(this, 65, 20);
		this.backleg12.setRotationPoint(-3.0F, 10.0F, -3.0F);
		this.backleg12.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
		this.setRotateAngle(backleg12, -0.12112585008840647F, 0.4883431247080134F, 0.03944444109507184F);
		this.backleg1 = new RendererModel(this, 0, 28);
		this.backleg1.setRotationPoint(-3.0F, 12.0F, 8.5F);
		this.backleg1.addBox(-3.0F, 0.0F, -3.0F, 2, 12, 2, 0.0F);
		this.horn1 = new RendererModel(this, 60, 20);
		this.horn1.setRotationPoint(4.0F, 3.0F, 6.0F);
		this.horn1.addBox(-4.0F, -5.0F, -4.0F, 1, 2, 1, 0.0F);
		this.frontleg22 = new RendererModel(this, 65, 20);
		this.frontleg22.setRotationPoint(-3.0F, 10.0F, -3.0F);
		this.frontleg22.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
		this.setRotateAngle(frontleg22, -0.12112585008840647F, 0.4883431247080134F, 0.03944444109507184F);
		this.tail1 = new RendererModel(this, 60, 28);
		this.tail1.setRotationPoint(-1.5F, 4.5F, -1.0F);
		this.tail1.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
		this.setRotateAngle(tail1, -0.6108652381980153F, -0.0F, 0.0F);
		this.mane = new RendererModel(this, 76, 0);
		this.mane.setRotationPoint(2.0F, 0.0F, 4.0F);
		this.mane.addBox(0.0F, -0.5F, 0.0F, 0, 21, 1, 0.0F);
		this.backleg2 = new RendererModel(this, 0, 28);
		this.backleg2.setRotationPoint(5.0F, 12.0F, 8.5F);
		this.backleg2.addBox(-3.0F, 0.0F, -3.0F, 2, 12, 2, 0.0F);
		this.backleg11 = new RendererModel(this, 65, 20);
		this.backleg11.setRotationPoint(-2.0F, 10.0F, -3.5F);
		this.backleg11.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
		this.setRotateAngle(backleg11, -0.12112585008840647F, -0.4883431247080134F, -0.03944444109507184F);
		this.horn2 = new RendererModel(this, 60, 20);
		this.horn2.setRotationPoint(0.0F, 3.0F, 6.0F);
		this.horn2.addBox(3.0F, -5.0F, -4.0F, 1, 2, 1, 0.0F);
		this.frontleg2 = new RendererModel(this, 0, 28);
		this.frontleg2.setRotationPoint(5.0F, 12.0F, -5.0F);
		this.frontleg2.addBox(-3.0F, 0.0F, -3.0F, 2, 12, 2, 0.0F);
		this.frontleg1 = new RendererModel(this, 0, 28);
		this.frontleg1.setRotationPoint(-3.0F, 12.0F, -5.0F);
		this.frontleg1.addBox(-3.0F, 0.0F, -3.0F, 2, 12, 2, 0.0F);
		this.backleg21 = new RendererModel(this, 65, 20);
		this.backleg21.setRotationPoint(-2.0F, 10.0F, -3.5F);
		this.backleg21.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
		this.setRotateAngle(backleg21, -0.12112585008840647F, -0.4883431247080134F, -0.03944444109507184F);
		this.rightear = new RendererModel(this, 60, 24);
		this.rightear.setRotationPoint(-2.0F, 0.0F, 3.0F);
		this.rightear.addBox(0.0F, 0.0F, 0.0F, 3, 2, 0, 0.0F);
		this.setRotateAngle(rightear, 0.0F, -0.0F, 0.2617993877991494F);
		this.head2 = new RendererModel(this, 18, 11);
		this.head2.setRotationPoint(-4.0F, -6.0F, -3.0F);
		this.head2.addBox(0.0F, 0.0F, 0.0F, 4, 2, 4, 0.0F);
		this.backleg22 = new RendererModel(this, 65, 20);
		this.backleg22.setRotationPoint(-3.0F, 10.0F, -3.0F);
		this.backleg22.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
		this.setRotateAngle(backleg22, -0.12112585008840647F, 0.4883431247080134F, 0.03944444109507184F);
		this.column = new RendererModel(this, 40, 0);
		this.column.setRotationPoint(-6.02F, -10.5F, 3.0F);
		this.column.addBox(0.0F, 0.0F, 0.0F, 10, 9, 7, 0.0F);
		this.setRotateAngle(column, -0.7853981633974483F, -0.0F, 0.0F);
		this.leftear = new RendererModel(this, 67, 24);
		this.leftear.mirror = true;
		this.leftear.setRotationPoint(3.5F, 0.7F, 3.0F);
		this.leftear.addBox(0.0F, 0.0F, 0.0F, 3, 2, 0, 0.0F);
		this.setRotateAngle(leftear, 0.0F, -0.0F, -0.2617993877991494F);
		this.body = new RendererModel(this, 18, 18);
		this.body.setRotationPoint(0.0F, 7.0F, 3.0F);
		this.body.addBox(-6.0F, -10.5F, -6.0F, 10, 16, 9, 0.0F);
		this.setRotateAngle(body, 1.483529806137085F, -0.0F, 0.0F);
		this.neck = new RendererModel(this, 0, 0);
		this.neck.setRotationPoint(-3.0F, -12.0F, -20.0F);
		this.neck.addBox(0.0F, 0.0F, 0.0F, 4, 21, 4, 0.0F);
		this.setRotateAngle(neck, 0.7853981852531433F, -0.0F, 0.0F);
		this.frontleg12 = new RendererModel(this, 65, 20);
		this.frontleg12.setRotationPoint(-3.0F, 10.0F, -3.0F);
		this.frontleg12.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
		this.setRotateAngle(frontleg12, -0.12112585008840647F, 0.4883431247080134F, 0.03944444109507184F);
		this.frontleg2.addChild(this.frontleg21);
		this.neck.addChild(this.head);
		this.frontleg1.addChild(this.frontleg11);
		this.backleg1.addChild(this.backleg12);
		this.head2.addChild(this.horn1);
		this.frontleg2.addChild(this.frontleg22);
		this.body.addChild(this.tail1);
		this.neck.addChild(this.mane);
		this.backleg1.addChild(this.backleg11);
		this.head2.addChild(this.horn2);
		this.backleg2.addChild(this.backleg21);
		this.head2.addChild(this.rightear);
		this.head.addChild(this.head2);
		this.backleg2.addChild(this.backleg22);
		this.body.addChild(this.column);
		this.head2.addChild(this.leftear);
		this.frontleg1.addChild(this.frontleg12);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		this.backleg1.render(f5);
		this.backleg2.render(f5);
		this.frontleg2.render(f5);
		this.frontleg1.render(f5);
		this.body.render(f5);
		this.neck.render(f5);
	}
	
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scaleFactor, Entity ent)
	{
		LivingEntity entity = ((LivingEntity) ent);

		this.frontleg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.3F * limbSwingAmount;
		this.backleg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.3F * limbSwingAmount;

		this.frontleg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.3F * limbSwingAmount;
		this.backleg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.3F * limbSwingAmount;
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
