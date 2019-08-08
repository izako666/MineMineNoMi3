package xyz.pixelatedw.MineMineNoMi3.entities.mobs;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.MineMineNoMi3.ID;

@OnlyIn(Dist.CLIENT)
public class MobRenderer extends RenderBiped
{

	private ResourceLocation texture;
	private float scale;

	public MobRenderer(RenderManager renderManager, ModelBiped model, float scale, String tex)
	{
		super(renderManager, model, 0.0F);
		this.scale = scale;
		this.texture = new ResourceLocation(ID.PROJECT_ID, "textures/models/" + tex + ".png");
	}

	public MobRenderer(RenderManager renderManager, ModelBiped model, String tex)
	{
		super(renderManager, model, 0.0F);
		this.scale = 1.0F;
		this.texture = new ResourceLocation(ID.PROJECT_ID, "textures/models/" + tex + ".png");
	}

	public MobRenderer(RenderManager renderManager, ModelBiped model)
	{
		super(renderManager, model, 0.0F);
		this.scale = 1.0F;
		this.texture = null;
	}

	public void doRender(EntityLivingBase entity, double x, double y, double z, float u, float v)
	{
		super.doRender((EntityLiving) entity, x, y, z, u, v);
	}

	protected void preRenderCallback(EntityLivingBase livingBase, float f)
	{
		GL11.glScalef(this.scale, this.scale, this.scale);
	}

	protected void renderEquippedItems(EntityLiving entity, float f)
	{
		GL11.glPushMatrix();
		if (entity instanceof IDynamicRenderer)
		{
			GL11.glScaled(((IDynamicRenderer) entity).itemScale()[0], ((IDynamicRenderer) entity).itemScale()[1], ((IDynamicRenderer) entity).itemScale()[2]);
			GL11.glTranslated(((IDynamicRenderer) entity).itemOffset()[0], ((IDynamicRenderer) entity).itemOffset()[1], ((IDynamicRenderer) entity).itemOffset()[2]);
		}

		/*ExtendedEntityData props = ExtendedEntityData.get((EntityLivingBase) entity);
		boolean hasHaki = props.hasBusoHakiActive();

		if(hasHaki)
		{
			this.bindTexture(ID.HANDTEXTURE_ZOANMORPH_BUSO);
			GL11.glColor3d(0.5, 0, 0.5);
			super.renderEquippedItems(entity, f);
		}
		else
		{
			super.renderEquippedItems(entity, f);
		}*/
		GL11.glPopMatrix();
	}

	protected ResourceLocation getEntityTexture(Entity entity)
	{
		if ((this.texture == null && entity instanceof IDynamicRenderer) || this.texture.equals(new ResourceLocation(ID.PROJECT_ID + ":textures/models/null.png")))
			return new ResourceLocation(ID.PROJECT_ID + ":textures/models/" + ((IDynamicRenderer) entity).getMobTexture() + ".png");
		else
			return this.texture;
	}

}
