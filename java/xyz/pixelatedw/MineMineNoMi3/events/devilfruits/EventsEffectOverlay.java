package xyz.pixelatedw.MineMineNoMi3.events.devilfruits;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.abilities.extra.models.ModelCandleLock;
import xyz.pixelatedw.MineMineNoMi3.abilities.extra.renderers.RenderCandleLock;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyRenderHelper;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;

public class EventsEffectOverlay
{

	private RenderCandleLock candleLock = new RenderCandleLock(new ModelCandleLock());

	@SubscribeEvent
	public void onEntityRendered(RenderLivingEvent.Pre event)
	{
		ExtendedEntityData props = ExtendedEntityData.get(event.entity);
		
		if (props.hasExtraEffects(ID.EXTRAEFFECT_MERO))
		{
			GL11.glPushMatrix();

			Color c = WyHelper.hexToRGB("#5d6060");
			GL11.glColor3d((double) c.getRed() / 255, (double) c.getGreen() / 255, (double) c.getBlue() / 255);

			GL11.glPopMatrix();
		}
		else if (props.hasExtraEffects(ID.EXTRAEFFECT_HIE))
		{
			GL11.glPushMatrix();

			Color c = WyHelper.hexToRGB("#1be2e2");
			GL11.glColor3d((double) c.getRed() / 255, (double) c.getGreen() / 255, (double) c.getBlue() / 255);

			GL11.glPopMatrix();
		}
		else if (props.hasExtraEffects(ID.EXTRAEFFECT_NORO))
		{
			GL11.glPushMatrix();

			Color c = WyHelper.hexToRGB("#ce83d3");
			GL11.glColor3d((double) c.getRed() / 255, (double) c.getGreen() / 255, (double) c.getBlue() / 255);

			GL11.glPopMatrix();
		}
		else if (props.hasExtraEffects(ID.EXTRAEFFECT_DORULOCK))
		{
			candleLock.doRender(event.entity, event.x, event.y, event.z, 0F, 0.0625F);
		}
		else if (props.hasExtraEffects(ID.EXTRAEFFECT_RUSTOVERLAY))
		{
			GL11.glPushMatrix();

			Color c = WyHelper.hexToRGB("#a04921");
			GL11.glColor3d((double) c.getRed() / 255, (double) c.getGreen() / 255, (double) c.getBlue() / 255);

			GL11.glPopMatrix();
		}
		else if (props.hasExtraEffects(ID.EXTRAEFFECT_SPIDEROVERLAY))
		{
			GL11.glPushMatrix();

			Color c = WyHelper.hexToRGB("#606875");
			GL11.glColor3d((double) c.getRed() / 255, (double) c.getGreen() / 255, (double) c.getBlue() / 255);

			GL11.glPopMatrix();
		}
	}
	
	@SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event)
	{
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.thePlayer;

		if (player == null)
			return;

		ExtendedEntityData props = ExtendedEntityData.get(player);
		
		if (props.hasExtraEffects(ID.EXTRAEFFECT_MERO))
			WyRenderHelper.drawColourOnScreen(WyHelper.hexToRGB("#5d6060").getRGB(), 100, 0, 0, mc.displayWidth, mc.displayHeight, 100);
		else if (props.hasExtraEffects(ID.EXTRAEFFECT_HIE))
			WyRenderHelper.drawColourOnScreen(WyHelper.hexToRGB("#1be2e2").getRGB(), 100, 0, 0, mc.displayWidth, mc.displayHeight, 100);
		else if (props.hasExtraEffects(ID.EXTRAEFFECT_NORO))
			WyRenderHelper.drawColourOnScreen(WyHelper.hexToRGB("#ce83d3").getRGB(), 100, 0, 0, mc.displayWidth, mc.displayHeight, 100);
		else if (props.hasExtraEffects(ID.EXTRAEFFECT_RUSTOVERLAY))
			WyRenderHelper.drawColourOnScreen(WyHelper.hexToRGB("#a04921").getRGB(), 100, 0, 0, mc.displayWidth, mc.displayHeight, 100);
		else if (props.hasExtraEffects(ID.EXTRAEFFECT_SPIDEROVERLAY))
			WyRenderHelper.drawColourOnScreen(WyHelper.hexToRGB("#3e4247").getRGB(), 100, 0, 0, mc.displayWidth, mc.displayHeight, 200);
		if(props.isInAirWorld())
			WyRenderHelper.drawColourOnScreen(0, 50, 0, 100, 0, 0, 500, 500, 100);
	}
	
}
