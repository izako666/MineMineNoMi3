package xyz.pixelatedw.MineMineNoMi3.events;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Project;
import org.lwjgl.util.glu.Sphere;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.abilities.extra.models.ModelCandleLock;
import xyz.pixelatedw.MineMineNoMi3.abilities.extra.renderers.RenderCandleLock;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.WyRenderHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityProperties;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.RenderZoanMorph;
import xyz.pixelatedw.MineMineNoMi3.helpers.HandRendererHelper;
import xyz.pixelatedw.MineMineNoMi3.helpers.MorphsHelper;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncInfo;

@SideOnly(Side.CLIENT)
public class EventsMorphs
{

	private Minecraft mc;

	private RenderCandleLock candleLock = new RenderCandleLock(new ModelCandleLock());

	public EventsMorphs(Minecraft mc)
	{
		this.mc = mc;
	}

	@SubscribeEvent
	public void onEntityRendered(RenderLivingEvent.Pre event)
	{
		ExtendedEntityData props = ExtendedEntityData.get(event.entity);

		if (!props.getZoanPoint().toLowerCase().equals("n/a"))
		{
			if(event.entity.hurtTime > 0)
			{
				GL11.glPushMatrix();
				GL11.glColor3f(1.0f, 0, 0);	        
				GL11.glPopMatrix();
			}
			
			event.setCanceled(true);
			
			if(MorphsHelper.getMorphsMap().containsKey(props.getUsedFruit()))
			{
				Arrays.stream(MorphsHelper.getMorphsMap().get(props.getUsedFruit())).forEach(x -> 
				{
					if(props.getZoanPoint().equalsIgnoreCase((String) x[0]))
						this.doRenderZoanMorph((RenderZoanMorph)x[1], event.x, event.y, event.z, event.entity);
				});
			}
		}
		
		if(props.isCandleLocked())
		{
			if (Minecraft.getMinecraft().thePlayer.equals(event.entity))
				candleLock.doRender(event.entity, 0D, -1.625D, 0D, 0F, 0.0625F);
			else
				candleLock.doRender(event.entity, event.x, event.y, event.z, 0F, 0.0625F);	
		}
		
		
/*		if(event.entity instanceof EntityDojoSensei)
		{
			GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glClearStencil(0);
			GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
			GL11.glEnable(GL11.GL_STENCIL_TEST);
			GL11.glStencilFunc(GL11.GL_ALWAYS, 1, 0xFFFF);
			GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
			GL11.glColor3f(1.0f, 1.0f, 1.0f);
			
			// Render original.
			//new RenderZoanMorph(new ModelBiped(), "null", 2).doRender(event.entity, event.x, event.y, event.z, 0, 0);
			
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glStencilFunc(GL11.GL_NOTEQUAL, 1, 0xFFFF);
			GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);
			GL11.glLineWidth(6.5f);
			GL11.glPolygonMode(GL11.GL_FRONT, GL11.GL_LINE);
			GL11.glColor4f(1.0F, 1.0F, 0.0F, 1.0F);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			
			// Render stencil.
			//new RenderZoanMorph(new ModelBiped(), "null", 1.35).doRender(event.entity, event.x, event.y + 1.3, event.z, 0, 0);
			zoanBisonPower.doRender(event.entity, event.x, event.y, event.z, 0F, 0F);

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glColor4f(1F, 1F, 1F, 1F);
			GL11.glPopAttrib();
			
			//event.setCanceled(true);
		}*/
		
	}

	private void doRenderZoanMorph(RenderZoanMorph render, double x, double y, double z, EntityLivingBase entity)
	{
		if (Minecraft.getMinecraft().thePlayer.equals(entity))
			render.doRender(entity, 0D, -1.625D, 0D, 0F, 0.0625F);
		else
			render.doRender(entity, x, y, z, 0F, 0.0625F);
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer owner = (EntityPlayer) event.entity;
			ExtendedEntityData props = ExtendedEntityData.get(owner);

			if (!props.getZoanPoint().toLowerCase().equals("n/a") && !props.getZoanPoint().toLowerCase().equals("yomi"))
			{
				props.setZoanPoint("n/a");

				WyNetworkHelper.sendToServer(new PacketSync(props));
				WyNetworkHelper.sendToAll(new PacketSyncInfo(owner.getDisplayName(), props));
			}
		}
	}

	@SubscribeEvent
	public void morphHandRendering(RenderHandEvent event)
	{
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		ExtendedEntityData props = ExtendedEntityData.get(player);
		AbilityProperties abilityProps = AbilityProperties.get(player);
		
		boolean renderHandFlag = false;
		boolean renderHandEffectFlag = false;
		
		if(player.getHeldItem() == null && props.hasBusoHakiActive())
		{
			renderHandFlag = true;
		}
	
		if(MorphsHelper.getMorphsMap().containsKey(props.getUsedFruit()))
		{
			for(Object[] x : MorphsHelper.getMorphsMap().get(props.getUsedFruit()))
			{
				if(props.getZoanPoint().equalsIgnoreCase((String) x[0]))
				{
					renderHandFlag = true;
					break;
				}
			}
		}
		
		GL11.glPushMatrix();
		{			
			int x = 0, y = 0, u = 16, v = 16;

/*			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			
			Project.gluPerspective(mc.gameSettings.fovSetting, (float) mc.displayWidth / (float) mc.displayHeight, 0.20F, (float) (mc.gameSettings.renderDistanceChunks * 16) * 2.0F);
			
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glLoadIdentity();
			
			RenderHelper.enableStandardItemLighting();
			Minecraft.getMinecraft().entityRenderer.enableLightmap(0);
			
	        int i2 = mc.theWorld.getLightBrightnessForSkyBlocks(MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY), MathHelper.floor_double(player.posZ), 0);
	        int j = i2 % 65536;
	        int k = i2 / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);*/		
			
			/*if (mc.gameSettings.thirdPersonView == 0 && !mc.renderViewEntity.isPlayerSleeping() && !mc.gameSettings.hideGUI)
			{
				GL11.glRotated(180, 0, 0, 1);
				GL11.glScaled(0.05, 0.05, 0.05);
				GL11.glTranslated(0, 0, 4);
				
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(ID.PROJECT_ID, "textures/abilities/" + WyHelper.getFancyName("baribarinopistol") + ".png"));        
				Tessellator tessellator = Tessellator.instance;
					
				tessellator.startDrawingQuads();
				tessellator.addVertexWithUV(x			, y + v			, 0, 0.0, 1.0);
				tessellator.addVertexWithUV(x + u		, y + v			, 0, 1.0, 1.0);
				tessellator.addVertexWithUV(x + u		, y        		, 0, 1.0, 0.0);
				tessellator.addVertexWithUV(x			, y         	, 0, 0.0, 0.0);			    
				tessellator.draw();
				    
				tessellator.startDrawingQuads();
				tessellator.addVertexWithUV(x + u		, y				, 0, 1.0, 0.0);
				tessellator.addVertexWithUV(x + u		, y + v			, 0, 1.0, 1.0);
				tessellator.addVertexWithUV(x			, y + v       	, 0, 0.0, 1.0);
				tessellator.addVertexWithUV(x			, y         	, 0, 0.0, 0.0);			    
				tessellator.draw();
			}*/
		}
		GL11.glPopMatrix();
		
		if(renderHandFlag)
		{
			event.setCanceled(true);
			HandRendererHelper.renderHand((EntityClientPlayerMP) player);
		}
		
		/*for(Object[] x : HandEffectsHelper.getMap().get(props.getUsedFruit()))
		{
			
		}*/
	}

}
