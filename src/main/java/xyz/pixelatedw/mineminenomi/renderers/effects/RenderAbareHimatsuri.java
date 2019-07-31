package xyz.pixelatedw.mineminenomi.renderers.effects;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.models.effects.ModelAbareHimatsuri;

@OnlyIn(Dist.CLIENT)
public class RenderAbareHimatsuri extends EntityRenderer
{
	private ResourceLocation texture = new ResourceLocation("minecraft:textures/blocks/dirt.png");;
	private int blockTint;
	private ModelAbareHimatsuri model;
	private double scale;
	
	public RenderAbareHimatsuri(EntityModel model)
	{
		super(Minecraft.getInstance().getRenderManager());
		this.shadowSize = 0;
		this.model = (ModelAbareHimatsuri) model;
		this.scale = 1;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float u, float v) 
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float)y , (float) z);

		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
    	GL11.glRotatef(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * v - 180.0F, 0.0F, 1.0F, 0.0F);
		
    	GL11.glScaled(1.5, 1, 1.5);

    	float red = (blockTint >> 16 & 255) / 255.0F;
        float green = (blockTint >> 8 & 255) / 255.0F;
        float blue = (blockTint & 255) / 255.0F;
        
        GL11.glColor3d(red, green, blue);
        
		Minecraft.getInstance().getTextureManager().bindTexture(this.texture);
		this.model.render(entity, 0.0F, 0.0F, 0F, 0.0F, 0.0F, 0.0625F);
		
		GL11.glColor3d(1, 1, 1);

		GL11.glPopMatrix();
	}

	public void setTextureAndTint(String texture, int tint)
	{
		this.texture = new ResourceLocation("minecraft:textures/blocks/" + texture + ".png");
		this.blockTint = tint;
	}
	
	@Override
	public ResourceLocation getEntityTexture(Entity entity) 
	{
		return texture;
	}

}