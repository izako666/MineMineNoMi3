package xyz.pixelatedw.MineMineNoMi3.gui;

import java.text.DecimalFormat;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.MainConfig;
import xyz.pixelatedw.MineMineNoMi3.MainMod;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;

public class GUIWantedPoster extends GuiScreen
{
	private NBTTagCompound wantedData;
	private RenderItem renderItem;

	public GUIWantedPoster(NBTTagCompound nbtTagCompound)
	{
		this.wantedData = nbtTagCompound;
	}

	public void drawScreen(int x, int y, float f)
	{
		drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;
		
		// Scaling the entire "context" x2 and positioning it correctly
		GL11.glTranslated(posX + 60, posY + 10, 0);
		GL11.glTranslated(128, 128, 512);
		GL11.glScaled(1, .9, 0);	
		GL11.glTranslated(-128, -128, -512);
		
		// Bounty Poster Texture
		this.mc.renderEngine.bindTexture(ID.TEXTURE_BOUNTYPOSTER_LARGE);
		this.drawTexturedModalRect(0, 0, 0, 0, 220, 250);
		
		// Scaling down the entire thing (with wanted poster texture and name) to x0.71
		GL11.glTranslated(67, 150, 0);	
		GL11.glTranslated(128, 128, 512);
		GL11.glScaled(1.5, 1.6, 0);	
		GL11.glTranslated(-128, -128, -512);
		
		// Rendering the actualy bounty + bounty symbol + wanted name
		this.mc.renderEngine.bindTexture(ID.TEXTURE_CURRENCIES);
		this.drawTexturedModalRect(-1, 63, 0, 0, 32, 32);
		DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        String bounty = decimalFormat.format(this.wantedData.getInteger("Bounty"));
		mc.fontRenderer.drawString(EnumChatFormatting.BOLD + bounty, 22, 76, WyHelper.hexToRGB("513413").getRGB());
		mc.fontRenderer.drawString(EnumChatFormatting.BOLD + this.wantedData.getString("Name"), 50 - mc.fontRenderer.getStringWidth(this.wantedData.getString("Name")) / 2, 62, WyHelper.hexToRGB("513413").getRGB());
				
		// Scaling down the entire thing so the date could fit
		GL11.glTranslated(-26, -2, 0);	
		GL11.glTranslated(128, 128, 512);
		GL11.glScaled(.74, .92, 0);	
		GL11.glTranslated(-128, -128, -512);
		
		mc.fontRenderer.drawString(EnumChatFormatting.BOLD + this.wantedData.getString("Date"), 36 - mc.fontRenderer.getStringWidth(this.wantedData.getString("Date")) / 2, 90, WyHelper.hexToRGB("513413").getRGB());
		
		super.drawScreen(x, y, f);
	}
	
	public void initGui()
	{
		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;
		
		
	}
	
	public void actionPerformed(GuiButton button)
	{
		switch(button.id)
		{

		}
	}
	
	public boolean doesGuiPauseGame()
	{
		return false;
	}
}
