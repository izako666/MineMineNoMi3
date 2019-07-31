package xyz.pixelatedw.mineminenomi.gui.extra;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyHelper;

public class GUIButtonNoTexture extends Button
{
	public GUIButtonNoTexture(int posX, int posY, int width, int height, String string, IPressable onPress)
	{
		super(posX, posY, width, height, string, onPress);
	}

	@SuppressWarnings("resource")
	@Override
	public void render(int mouseX, int mouseY, float partialTicks)
	{
		if (this.visible)
		{
			Minecraft minecraft = Minecraft.getInstance();
			FontRenderer fontrenderer = minecraft.fontRenderer;
			minecraft.getTextureManager().bindTexture(new ResourceLocation(ID.PROJECT_ID, "textures/gui/empty.png"));
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
			GlStateManager.enableBlend();
			GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			GuiUtils.drawTexturedModalRect(this.x, this.y, 0, 46 * 20, this.width / 2, this.height, 1);
			GuiUtils.drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 * 20, this.width / 2, this.height, 1);
			this.renderBg(minecraft, mouseX, mouseY);
			int l;

			if (isHovered)
				l = WyHelper.hexToRGB("FFD700").getRGB();
			else
				l = 14737632;
		}
	}

}
