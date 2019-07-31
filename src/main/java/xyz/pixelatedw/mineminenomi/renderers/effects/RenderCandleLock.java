package xyz.pixelatedw.mineminenomi.renderers.effects;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.models.effects.ModelCandleLock;

@OnlyIn(Dist.CLIENT)
public class RenderCandleLock extends EntityRenderer
{
	private ResourceLocation texture = new ResourceLocation(ID.PROJECT_ID, "textures/models/zoanmorph/candlelock.png");
	private ModelCandleLock model;
	private double scale;
	private float offset[] = new float[3];
	
	public RenderCandleLock(EntityModel model)
	{
		super(Minecraft.getInstance().getRenderManager());
		this.shadowSize = 0;
		this.model = (ModelCandleLock) model;
		this.scale = 1;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float u, float v) 
	{
		GL11.glPushMatrix();
		
		GL11.glTranslatef((float) x + this.offset[0], (float)y + 1.3F + this.offset[1], (float) z + this.offset[2]);

		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
    	GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * v - 180.0F, 0.0F, 1.0F, 0.0F);
		
    	GL11.glScaled(this.scale, this.scale, this.scale);
		GL11.glScalef(1.0F, 1.0F, 1.0F);
		
		Minecraft.getInstance().getTextureManager().bindTexture(this.getEntityTexture(entity));
		this.model.render(entity, 0.0F, 0.0F, 0F, 0.0F, 0.0F, 0.0625F);
		
		GL11.glPopMatrix();
	}

	@Override
	public ResourceLocation getEntityTexture(Entity p_110775_1_) 
	{
		return texture;
	}

}