package xyz.pixelatedw.MineMineNoMi3.events.devilfruits;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;

public class EventsEffectOverlay
{

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
	}
	
}
