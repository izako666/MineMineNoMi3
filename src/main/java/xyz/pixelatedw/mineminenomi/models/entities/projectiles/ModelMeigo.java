package xyz.pixelatedw.mineminenomi.models.entities.projectiles;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class ModelMeigo extends EntityModel
{
	public RendererModel leftarm1;
	public RendererModel leftarm2;
	public RendererModel leftarm3;
	public RendererModel leftarm4;
	public RendererModel leftarm5;
	public RendererModel leftarm6;
	public RendererModel leftarm7;
	public RendererModel lefthand;
	public RendererModel leftfinger10;
	public RendererModel leftfinger11;
	public RendererModel leftfinger20;
	public RendererModel leftfinger21;
	public RendererModel leftfinger30;
	public RendererModel leftfinger31;
	public RendererModel leftfinger40;
	public RendererModel leftfinger41;
	public RendererModel leftfinger50;
	public RendererModel leftfinger51;

	public ModelMeigo()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.leftarm5 = new RendererModel(this, 13, 21);
		this.leftarm5.setRotationPoint(2.299999952316284F, 7.0F, 4.0F);
		this.leftarm5.addBox(-0.8999999761581421F, 5.400000095367432F, -2.0999999046325684F, 3, 5, 3, 0.0F);
		this.setRotateAngle(leftarm5, -1.5707963705062866F, -0.0F, 0.0F);
		this.leftfinger31 = new RendererModel(this, 17, 10);
		this.leftfinger31.setRotationPoint(1.899999976158142F, 4.0F, -11.899999618530273F);
		this.leftfinger31.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(leftfinger31, -0.8028514385223389F, -0.0F, -0.01745329238474369F);
		this.leftfinger21 = new RendererModel(this, 17, 10);
		this.leftfinger21.setRotationPoint(2.5F, 4.0F, -11.899999618530273F);
		this.leftfinger21.addBox(0.0F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(leftfinger21, -0.8028514385223389F, -0.0F, 0.01745329238474369F);
		this.lefthand = new RendererModel(this, 17, 0);
		this.lefthand.setRotationPoint(2.299999952316284F, 7.0F, -10.0F);
		this.lefthand.addBox(-2.0F, 0.0F, -2.0F, 4, 1, 4, 0.0F);
		this.setRotateAngle(lefthand, -1.483529806137085F, -0.0F, 0.0F);
		this.leftarm6 = new RendererModel(this, 26, 21);
		this.leftarm6.setRotationPoint(2.299999952316284F, 7.0F, 4.0F);
		this.leftarm6.addBox(-0.800000011920929F, 9.600000381469727F, -0.8999999761581421F, 3, 4, 3, 0.0F);
		this.setRotateAngle(leftarm6, -1.5707963705062866F, -0.0F, 0.0F);
		this.leftfinger20 = new RendererModel(this, 17, 6);
		this.leftfinger20.setRotationPoint(2.4000000953674316F, 5.0F, -10.399999618530273F);
		this.leftfinger20.addBox(0.0F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(leftfinger20, -1.9547687768936162F, -0.03490658476948738F, 0.0F);
		this.leftfinger51 = new RendererModel(this, 22, 10);
		this.leftfinger51.setRotationPoint(0.0F, 8.199999809265137F, -12.100000381469727F);
		this.leftfinger51.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(leftfinger51, -1.5707963705062866F, -0.10471975803375246F, 0.0F);
		this.leftarm1 = new RendererModel(this, 0, 0);
		this.leftarm1.setRotationPoint(2.299999952316284F, 7.0F, 6.0F);
		this.leftarm1.addBox(-2.0F, 0.0F, -2.0F, 4, 16, 4, 0.0F);
		this.setRotateAngle(leftarm1, -1.5707963705062866F, -0.0F, 0.0F);
		this.leftarm3 = new RendererModel(this, 0, 21);
		this.leftarm3.setRotationPoint(2.299999952316284F, 7.0F, 4.0F);
		this.leftarm3.addBox(-0.800000011920929F, 1.0F, -0.8999999761581421F, 3, 5, 3, 0.0F);
		this.setRotateAngle(leftarm3, -1.5707963705062866F, -0.0F, 0.0F);
		this.leftfinger40 = new RendererModel(this, 17, 6);
		this.leftfinger40.setRotationPoint(0.699999988079071F, 5.0F, -10.399999618530273F);
		this.leftfinger40.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(leftfinger40, -1.9153444785557112F, -0.01685852849418689F, -0.261837476707124F);
		this.leftarm2 = new RendererModel(this, 0, 21);
		this.leftarm2.setRotationPoint(2.299999952316284F, 7.0F, 4.0F);
		this.leftarm2.addBox(-2.200000047683716F, 0.0F, -2.0999999046325684F, 3, 5, 3, 0.0F);
		this.setRotateAngle(leftarm2, -1.5707963705062866F, -0.0F, 0.0F);
		this.leftfinger10 = new RendererModel(this, 17, 6);
		this.leftfinger10.setRotationPoint(3.4000000953674316F, 5.0F, -10.399999618530273F);
		this.leftfinger10.addBox(0.0F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(leftfinger10, -1.9225927181580402F, -0.01723839196158952F, 0.15710317294030107F);
		this.leftfinger11 = new RendererModel(this, 17, 10);
		this.leftfinger11.setRotationPoint(3.5999999046325684F, 4.0F, -11.899999618530273F);
		this.leftfinger11.addBox(0.0F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(leftfinger11, -0.8028514385223389F, -0.0F, 0.13962633907794952F);
		this.leftfinger41 = new RendererModel(this, 17, 10);
		this.leftfinger41.setRotationPoint(0.5F, 4.199999809265137F, -11.899999618530273F);
		this.leftfinger41.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(leftfinger41, -0.8028514385223389F, -0.0F, -0.2617993950843811F);
		this.leftfinger30 = new RendererModel(this, 17, 6);
		this.leftfinger30.setRotationPoint(1.7999999523162842F, 5.0F, -10.399999618530273F);
		this.leftfinger30.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(leftfinger30, -1.9547687768936162F, -0.03490658476948738F, 0.0F);
		this.leftarm4 = new RendererModel(this, 13, 21);
		this.leftarm4.setRotationPoint(2.299999952316284F, 7.0F, 4.0F);
		this.leftarm4.addBox(-2.299999952316284F, 4.0F, -1.600000023841858F, 3, 5, 3, 0.0F);
		this.setRotateAngle(leftarm4, -1.5707963705062866F, -0.0F, 0.0F);
		this.leftarm7 = new RendererModel(this, 26, 21);
		this.leftarm7.setRotationPoint(2.299999952316284F, 7.0F, 4.0F);
		this.leftarm7.addBox(-2.0999999046325684F, 9.300000190734863F, -2.200000047683716F, 3, 4, 3, 0.0F);
		this.setRotateAngle(leftarm7, -1.5707963705062866F, -0.0F, 0.0F);
		this.leftfinger50 = new RendererModel(this, 22, 6);
		this.leftfinger50.setRotationPoint(0.699999988079071F, 8.199999809265137F, -10.5F);
		this.leftfinger50.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(leftfinger50, -1.5707963705062866F, 0.3839724361896515F, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.leftarm5.render(f5);
		this.leftfinger31.render(f5);
		this.leftfinger21.render(f5);
		this.lefthand.render(f5);
		this.leftarm6.render(f5);
		this.leftfinger20.render(f5);
		this.leftfinger51.render(f5);
		this.leftarm1.render(f5);
		this.leftarm3.render(f5);
		this.leftfinger40.render(f5);
		this.leftarm2.render(f5);
		this.leftfinger10.render(f5);
		this.leftfinger11.render(f5);
		this.leftfinger41.render(f5);
		this.leftfinger30.render(f5);
		this.leftarm4.render(f5);
		this.leftarm7.render(f5);
		this.leftfinger50.render(f5);
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
