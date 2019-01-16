package xyz.pixelatedw.MineMineNoMi3.entities.zoan.models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelZouFull extends ModelZoanMorph
{
	public ModelRenderer head;
	public ModelRenderer rightear;
	public ModelRenderer leftear;
	public ModelRenderer body1;
	public ModelRenderer body2;
	public ModelRenderer leg1;
	public ModelRenderer leg2;
	public ModelRenderer leg3;
	public ModelRenderer leg4;
	public ModelRenderer snout;
	public ModelRenderer snout2;
	public ModelRenderer snout3;
	public ModelRenderer tuskA1;
	public ModelRenderer tuskA2;
	public ModelRenderer tuskB1;
	public ModelRenderer tuskB2;
	public ModelRenderer tail1;
	public ModelRenderer tail2;

	public ModelZouFull()
	{
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.rightear = new ModelRenderer(this, 0, 21);
		this.rightear.setRotationPoint(-3.0F, -2.0F, -14.5F);
		this.rightear.addBox(-6.0F, -4.5F, -0.5F, 6, 9, 1, 0.0F);
		this.setRotateAngle(rightear, -0.1367575307167758F, 0.47066009837182077F, -0.29463836784929F);
		this.body2 = new ModelRenderer(this, 36, 25);
		this.body2.setRotationPoint(0.0F, 8.0F, -4.0F);
		this.body2.addBox(-7.5F, -12.0F, -8.0F, 15, 15, 24, 0.0F);
		this.tuskA1 = new ModelRenderer(this, 15, 21);
		this.tuskA1.setRotationPoint(-2.299999952316284F, 3.0F, -19.0F);
		this.tuskA1.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(tuskA1, -0.34906584024429316F, 0.2094395160675049F, 0.0F);
		this.leg2 = new ModelRenderer(this, 0, 46);
		this.leg2.setRotationPoint(5.0F, 11.0F, 9.5F);
		this.leg2.addBox(-2.5F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
		this.snout = new ModelRenderer(this, 108, 8);
		this.snout.setRotationPoint(0.0F, 3.0F, -18.0F);
		this.snout.addBox(-2.0F, 0.0F, -2.0F, 4, 7, 4, 0.0F);
		this.setRotateAngle(snout, -0.17453292012214658F, -0.0F, 0.0F);
		this.leftear = new ModelRenderer(this, 0, 21);
		this.leftear.setRotationPoint(3.0F, -2.0F, -14.5F);
		this.leftear.addBox(0.0F, -4.5F, -0.5F, 6, 9, 1, 0.0F);
		this.setRotateAngle(leftear, -0.1367575307167758F, -0.47066009837182077F, 0.29463836784929F);
		this.tuskA2 = new ModelRenderer(this, 15, 27);
		this.tuskA2.setRotationPoint(-2.5F, 6.699999809265137F, -20.299999237060547F);
		this.tuskA2.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(tuskA2, -0.6108652353286742F, 0.2094395160675049F, 0.0F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, -2.0F, -14.0F);
		this.head.addBox(-4.0F, -4.0F, -6.0F, 8, 11, 9, 0.0F);
		this.tail1 = new ModelRenderer(this, 108, 0);
		this.tail1.setRotationPoint(0.0F, 0.0F, 11.5F);
		this.tail1.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
		this.setRotateAngle(tail1, 0.34906584024429316F, -0.0F, 0.0F);
		this.tail2 = new ModelRenderer(this, 113, 0);
		this.tail2.setRotationPoint(0.0F, 4.300000190734863F, 13.0F);
		this.tail2.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2, 0.0F);
		this.setRotateAngle(tail2, 0.34906584024429316F, -0.0F, 0.0F);
		this.leg3 = new ModelRenderer(this, 0, 46);
		this.leg3.setRotationPoint(-5.0F, 11.0F, -9.5F);
		this.leg3.addBox(-2.5F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
		this.leg4 = new ModelRenderer(this, 0, 46);
		this.leg4.setRotationPoint(5.0F, 11.0F, -9.5F);
		this.leg4.addBox(-2.5F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
		this.body1 = new ModelRenderer(this, 36, 0);
		this.body1.setRotationPoint(0.0F, 6.5F, -4.0F);
		this.body1.addBox(-6.5F, -12.0F, -7.0F, 13, 2, 22, 0.0F);
		this.tuskB1 = new ModelRenderer(this, 15, 21);
		this.tuskB1.setRotationPoint(2.299999952316284F, 3.0F, -19.0F);
		this.tuskB1.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(tuskB1, -0.34906584024429316F, -0.2094395160675049F, 0.0F);
		this.snout3 = new ModelRenderer(this, 108, 30);
		this.snout3.setRotationPoint(0.0F, 15.0F, -19.399999618530273F);
		this.snout3.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F);
		this.setRotateAngle(snout3, 0.17453292012214658F, -0.0F, 0.0F);
		this.leg1 = new ModelRenderer(this, 0, 46);
		this.leg1.setRotationPoint(-5.0F, 11.0F, 9.5F);
		this.leg1.addBox(-2.5F, 0.0F, -2.5F, 5, 13, 5, 0.0F);
		this.snout2 = new ModelRenderer(this, 108, 20);
		this.snout2.setRotationPoint(0.0F, 9.600000381469727F, -19.200000762939453F);
		this.snout2.addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3, 0.0F);
		this.tuskB2 = new ModelRenderer(this, 15, 27);
		this.tuskB2.setRotationPoint(2.5F, 6.699999809265137F, -20.299999237060547F);
		this.tuskB2.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(tuskB2, -0.6108652353286742F, -0.2094395160675049F, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.rightear.render(f5);
		this.body2.render(f5);
		this.tuskA1.render(f5);
		this.leg2.render(f5);
		this.snout.render(f5);
		this.leftear.render(f5);
		this.tuskA2.render(f5);
		this.head.render(f5);
		this.tail1.render(f5);
		this.tail2.render(f5);
		this.leg3.render(f5);
		this.leg4.render(f5);
		this.body1.render(f5);
		this.tuskB1.render(f5);
		this.snout3.render(f5);
		this.leg1.render(f5);
		this.snout2.render(f5);
		this.tuskB2.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public ModelRenderer getHandRenderer()
	{
		return null;
	}
}
