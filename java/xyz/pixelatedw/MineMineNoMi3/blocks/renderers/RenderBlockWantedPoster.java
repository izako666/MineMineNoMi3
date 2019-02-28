package xyz.pixelatedw.MineMineNoMi3.blocks.renderers;

import java.text.DecimalFormat;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyRenderHelper;
import xyz.pixelatedw.MineMineNoMi3.blocks.models.ModelWantedPoster;
import xyz.pixelatedw.MineMineNoMi3.blocks.tileentities.TileEntityWantedPoster;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedWorldData;

@SideOnly(Side.CLIENT)
public class RenderBlockWantedPoster extends TileEntitySpecialRenderer
{
	private static final ResourceLocation texture = new ResourceLocation(ID.PROJECT_ID + ":textures/models/wantedposter.png");
	private ModelWantedPoster posterModel;

	public RenderBlockWantedPoster()
	{
		this.posterModel = new ModelWantedPoster();
	}

	public void renderTileEntityAt(TileEntity te, double posX, double posY, double posZ, float timeSinceLastTick)
	{
		ExtendedWorldData worldData = ExtendedWorldData.get(te.getWorldObj());

		if(te == null)
			return;
		
		TileEntityWantedPoster te2 = (TileEntityWantedPoster) te;

		int rawRot = te2.getBlockMetadata();

		double posterX = 0, textX = 0;
		double posterY = 0, textY = 0;
		double posterZ = 0, textZ = 0;
		int posterRotation = 90, textRotation = 90;

		if (rawRot == 2)
		{
			posterX = 0.90;
			posterZ = 0.16;
			posterRotation = 180;
			
			textX = 0.38;
			textZ = 0.55;
			textRotation = 0;
		}
		else if (rawRot == 3)
		{
			posterX = 0.18;
			posterZ = -0.9;
			posterRotation = 0;
			
			textX = 0.57;
			textY = 0.01;
			textZ = -0.41;
			textRotation = 180;
		}
		else if (rawRot == 5)
		{
			posterX = 1.1;
			posterZ = -0.74;
			posterRotation = -90;
			
			textX = 0.98;
			textY = 0.005;
			textZ = 0.165;
			textRotation = -90;
		}

		bindTexture(texture);

		GL11.glPushMatrix();
		{
			GL11.glDepthMask(false);

			GL11.glTranslated(posX + 1.05, posY + 1.2, posZ + 0.87);
			GL11.glRotatef(180, 0F, 0F, 1F);
			GL11.glRotatef(0, 0F, 1F, 0F);

			GL11.glPushMatrix();
			{
				GL11.glTranslated(posterX, posterY, posterZ);
				GL11.glRotatef(posterRotation, 0F, 1F, 0F);
				GL11.glScaled(.6, .6, 1);
				this.posterModel.render();
			}
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			{
				GL11.glNormal3f(0.0F, 1.0F, -1.0F);

				GL11.glTranslated(textX + 0.065, textY + 0.83, textZ - 0.45);
				GL11.glRotatef(-textRotation, 0F, 1F, 0F);
				GL11.glScalef(.007F, .007F, .007F);
				Minecraft.getMinecraft().fontRenderer.drawString(EnumChatFormatting.BOLD + te2.getEntityName(), 3 - Minecraft.getMinecraft().fontRenderer.getStringWidth(te2.getEntityName()) / 2, 0, WyHelper.hexToRGB("513413").getRGB());
				GL11.glScalef(1.2F, 1.2F, 1.2F);
				DecimalFormat decimalFormat = new DecimalFormat("#,##0");
				if (te2.getPosterBounty() == null)
					te2.setPosterBounty("0");
				String bounty = "0";
				try
				{
					bounty = decimalFormat.format(Long.parseLong(te2.getPosterBounty().replace("L", "")));
				}
				catch(Exception e)
				{
					bounty = "0";
					e.printStackTrace();
				}
				
				boolean flag = bounty.length() > 10;

				if(flag)
				{
					GL11.glPushMatrix();
					GL11.glTranslated(-40, -13.5, 0);	
					GL11.glTranslated(128, 128, 512);
					GL11.glScaled(.72, 0.89, 1.005);	
					GL11.glTranslated(-128, -128, -512);
				}
				Minecraft.getMinecraft().fontRenderer.drawString(EnumChatFormatting.BOLD + bounty, -20, 13, WyHelper.hexToRGB("#513413").getRGB());
				if(flag)
					GL11.glPopMatrix();
				
				GL11.glScalef(0.7F, 0.9F, 0.8F);
				Minecraft.getMinecraft().fontRenderer.drawString(EnumChatFormatting.BOLD + te2.getIssuedDate(), -40, 30, WyHelper.hexToRGB("#513413").getRGB());
			}
			GL11.glPopMatrix();
			
			GL11.glDepthMask(true);
		}
		GL11.glPopMatrix();

	}
}