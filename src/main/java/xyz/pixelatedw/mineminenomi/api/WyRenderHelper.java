package xyz.pixelatedw.mineminenomi.api;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.ID;

public class WyRenderHelper
{	
	
	public static double[] generateAnimationArray(double startPos, double minPos, double maxPos, double frameSkip, int framesPerSlot)
	{				
		int framesCount = 0;
		double currentFrame = startPos;
		boolean hasReachedMaxPos = false;
		boolean hasReachedMinPos = false;
		
		for(double i = startPos; i <= maxPos; i += frameSkip)
			framesCount++;
		
		for(double i = maxPos; i > minPos; i -= frameSkip)
			framesCount++;
		
		for(double i = minPos; i <= startPos; i += frameSkip)
			framesCount++;
		
		framesCount *= framesPerSlot;
		
		framesCount -= 1 * framesPerSlot;
 		double[] animation = new double[framesCount];	
		
		for(int j = 0; j < framesCount; j++)
		{
			for(int i = 0; i < framesPerSlot; i++)
			{
				if(j + 1 < framesCount)
				{
					if(i > 0)
						j++;
					animation[j] = currentFrame;
				}
			}
			if(!hasReachedMaxPos && currentFrame < maxPos) currentFrame += frameSkip;			
			else if(!hasReachedMinPos && hasReachedMaxPos && currentFrame > minPos) currentFrame -= frameSkip;
			else if(hasReachedMinPos && currentFrame < startPos) currentFrame += frameSkip;
			
			if(currentFrame >= maxPos) hasReachedMaxPos = true;
			if(currentFrame <= minPos) hasReachedMinPos = true;
		}
		
 		return animation;
	}
	
	public static void drawColourOnScreen(int colour, int alpha, double posX, double posY, double width, double height, double zLevel)
	{
		int r = (colour >> 16 & 0xff);
		int g = (colour >> 8 & 0xff);
		int b = (colour & 0xff);
		drawColourOnScreen(r, g, b, alpha, posX, posY, width, height, zLevel);
	}

	public static void drawColourOnScreen(int r, int g, int b, int alpha, double posX, double posY, double width, double height, double zLevel)
	{
		if (width <= 0 || height <= 0)
		{
			return;
		}
		GL11.glColorMask(true, false, false, true);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
		bufferbuilder.pos(posX			, posY + height		, zLevel).color(r, g, b, alpha).endVertex();
		bufferbuilder.pos(posX + width	, posY + height		, zLevel).color(r, g, b, alpha).endVertex();
		bufferbuilder.pos(posX + width	, posY				, zLevel).color(r, g, b, alpha).endVertex();
		bufferbuilder.pos(posX			, posY				, zLevel).color(r, g, b, alpha).endVertex();
		Tessellator.getInstance().draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glColorMask(true, true, true, true);
	}

	public static void renderTestStencil()
	{
		// Basic stencil test
		Minecraft mc = Minecraft.getInstance();
		PlayerEntity player = mc.player;
		
		GL11.glEnable(GL11.GL_STENCIL_TEST);

		GL11.glColorMask(false, false, false, false);
		GL11.glDepthMask(false);

		GL11.glStencilFunc(GL11.GL_NEVER, 1, 0xFF);
		GL11.glStencilOp(GL11.GL_REPLACE, GL11.GL_KEEP, GL11.GL_KEEP);

		GL11.glStencilMask(0xFF);
		GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
		GL11.glColor3d(255, 0, 0);
		WyRenderHelper.drawEntityOnScreen(mc.mainWindow.getWidth() / 2, mc.mainWindow.getHeight() / 2, 64, 0, 0, mc.player);
		
		//drawColourOnScreen(0xffffff, 255, 0, 0, 60, 60, 0);

		GL11.glColorMask(true, true, true, true);
		GL11.glDepthMask(true);

		GL11.glStencilMask(0x00);

		GL11.glStencilFunc(GL11.GL_EQUAL, 0, 0xFF);

		GL11.glStencilFunc(GL11.GL_EQUAL, 1, 0xFF);
		GL11.glColor3d(255, 0, 0);
		WyRenderHelper.drawEntityOnScreen(mc.mainWindow.getWidth() / 2, mc.mainWindow.getHeight() / 2, 64, 0, 0, mc.player);

		//drawColourOnScreen(0xffffff, 255, 0, 0, reso.getScaledWidth_double(), reso.getScaledHeight_double(), 0);

		GL11.glDisable(GL11.GL_STENCIL_TEST);
	
	}
		
	public static void drawAbilityIcon(String iconName, int x, int y, int u, int v)
	{
        Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(ID.PROJECT_ID, "textures/abilities/" + WyHelper.getFancyName(iconName) + ".png"));        
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(x			, y + v			, 1).tex(0.0, 1.0).endVertex();
		bufferbuilder.pos(x + u		, y + v			, 1).tex(1.0, 1.0).endVertex();
		bufferbuilder.pos(x + u		, y        		, 1).tex(1.0, 0.0).endVertex();
		bufferbuilder.pos(x			, y				, 1).tex(0.0, 0.0).endVertex();
		Tessellator.getInstance().draw();   
	}
	
	public static void drawDevilFruitIcon(String iconName, int x, int y, int u, int v)
	{
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(ID.PROJECT_ID, "textures/items/" + WyHelper.getFancyName(iconName) + ".png"));        
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(x			, y + v			, 1).tex(0.0, 1.0).endVertex();
		bufferbuilder.pos(x + u		, y + v			, 1).tex(1.0, 1.0).endVertex();
		bufferbuilder.pos(x + u		, y        		, 1).tex(1.0, 0.0).endVertex();
		bufferbuilder.pos(x			, y				, 1).tex(0.0, 0.0).endVertex();
		Tessellator.getInstance().draw();
	}
	
	/*public static void drawColorOnScreen(int r, int g, int b, int alpha, double posX, double posY, double width, double height)
	{
		GlStateManager.disableTexture2D();
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vexBuffer = tessellator.getBuffer();	
		 
		vexBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
		
		vexBuffer.pos(posX, posY + height, 100).color(r, g, b, alpha).endVertex();
		vexBuffer.pos(posX + width, posY + height, 100).color(r, g, b, alpha).endVertex();
		vexBuffer.pos(posX + width, posY, 100).color(r, g, b, alpha).endVertex();
		vexBuffer.pos(posX, posY, 100).color(r, g, b, alpha).endVertex();

		tessellator.draw();
		GlStateManager.enableTexture2D();		
	}

	public static void drawTextureOnScreen()
	{
		
	}*/
	
	public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, LivingEntity entity)
	{
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		GL11.glTranslatef(posX, posY, 50.0F);
		GL11.glScalef((-scale), scale, scale);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		float f2 = entity.renderYawOffset;
		float f3 = entity.rotationYaw;
		float f4 = entity.rotationPitch;
		float f5 = entity.prevRotationYawHead;
		float f6 = entity.rotationYawHead;
		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-((float) Math.atan(mouseY / 40.0F)) * 20.0F, 1.0F, 0.0F, 0.0F);
		entity.renderYawOffset = (float) Math.atan(mouseX / 40.0F) * 20.0F;
		entity.rotationYaw = (float) Math.atan(mouseX / 40.0F) * 40.0F;
		entity.rotationPitch = -((float) Math.atan(mouseY / 40.0F)) * 20.0F;
		entity.rotationYawHead = entity.rotationYaw;
		entity.prevRotationYawHead = entity.rotationYaw;
		GL11.glTranslatef(0.0F, (float) entity.getYOffset(), 0.0F);
		Minecraft.getInstance().getRenderManager().playerViewY = 180.0F;
		Minecraft.getInstance().getRenderManager().renderEntityStatic(entity, 0, true);
		entity.renderYawOffset = f2;
		entity.rotationYaw = f3;
		entity.rotationPitch = f4;
		entity.prevRotationYawHead = f5;
		entity.rotationYawHead = f6;
		GL11.glPopMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		//OpenGlHelper.glActiveTexture(OpenGlHelper.lightmapTexUnit);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		//OpenGlHelper.glActiveTexture(OpenGlHelper.defaultTexUnit);
	}

    public static void startGlScissor(int x, int y, int width, int height)
    {
        Minecraft mc = Minecraft.getInstance();

        double scaleW = (double)mc.mainWindow.getWidth() / mc.mainWindow.getScaledWidth();
        double scaleH = (double)mc.mainWindow.getHeight() / mc.mainWindow.getScaledHeight();

        if(width <= 0 || height <= 0)
        {
            return;
        }
        if(x < 0)
        {
            x = 0;
        }
        if(y < 0)
        {
            y = 0;
        }

        GL11.glEnable(GL11.GL_SCISSOR_TEST);

        GL11.glScissor((int)Math.floor(x * scaleW), (int)Math.floor(mc.mainWindow.getHeight() - ((y + height) * scaleH)), (int)Math.floor((x + width) * scaleW) - (int)Math.floor(x * scaleW), (int)Math.floor(mc.mainWindow.getHeight() - (y * scaleH)) - (int)Math.floor(mc.mainWindow.getHeight() - ((y + height) * scaleH))); //starts from lower left corner (minecraft starts from upper left)
    }
    
    public static void endGlScissor()
    {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

}
