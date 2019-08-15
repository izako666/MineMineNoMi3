package xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.models;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class  ModelFoot extends ModelBase {
	private final ModelRenderer Foot;

	public ModelFoot() {
		textureWidth = 16;
		textureHeight = 16;

		Foot = new ModelRenderer(this);
		Foot.setRotationPoint(0.0F, 24.0F, 0.0F);
		Foot.cubeList.add(new ModelBox(Foot, 0, 0, -9.0F, -13.0F, -4.0F, 15, 6, 8, 0.0F));
		Foot.cubeList.add(new ModelBox(Foot, 0, 0, -6.0F, -7.0F, -3.0F, 9, 2, 6, 0.0F));
		Foot.cubeList.add(new ModelBox(Foot, 0, 0, -8.0F, -7.0F, -3.0F, 2, 1, 6, 0.0F));
		Foot.cubeList.add(new ModelBox(Foot, 0, 0, 3.0F, -7.0F, -3.0F, 2, 1, 6, 0.0F));
		Foot.cubeList.add(new ModelBox(Foot, 0, 0, -6.0F, -7.0F, -4.0F, 9, 1, 1, 0.0F));
		Foot.cubeList.add(new ModelBox(Foot, 0, 0, -6.0F, -7.0F, 3.0F, 9, 1, 1, 0.0F));
		Foot.cubeList.add(new ModelBox(Foot, 0, 0, -13.0F, -19.0F, -4.0F, 4, 12, 8, 0.0F));
		Foot.cubeList.add(new ModelBox(Foot, 0, 0, -12.0F, -7.0F, -3.0F, 3, 1, 6, 0.0F));
		Foot.cubeList.add(new ModelBox(Foot, 0, 0, -9.0F, -15.0F, -4.0F, 1, 2, 8, 0.0F));
		Foot.cubeList.add(new ModelBox(Foot, 0, 0, -8.0F, -14.0F, -4.0F, 1, 1, 8, 0.0F));
		Foot.cubeList.add(new ModelBox(Foot, 0, 0, -12.0F, -22.0F, -4.0F, 3, 3, 2, 0.0F));
		Foot.cubeList.add(new ModelBox(Foot, 0, 0, -12.0F, -22.0F, 2.0F, 3, 3, 2, 0.0F));
		Foot.cubeList.add(new ModelBox(Foot, 0, 0, -12.0F, -22.0F, -1.0F, 3, 3, 2, 0.0F));
		Foot.cubeList.add(new ModelBox(Foot, 0, 0, -12.0F, -20.0F, 1.0F, 3, 1, 1, 0.0F));
		Foot.cubeList.add(new ModelBox(Foot, 0, 0, -12.0F, -20.0F, -2.0F, 3, 1, 1, 0.0F));
		Foot.cubeList.add(new ModelBox(Foot, 0, 0, -14.0F, -11.0F, -3.0F, 1, 3, 6, 0.0F));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Foot.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}