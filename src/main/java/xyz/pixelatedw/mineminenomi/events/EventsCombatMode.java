package xyz.pixelatedw.mineminenomi.events;

import java.awt.Color;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GLX;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyHelper.Direction;
import xyz.pixelatedw.mineminenomi.api.WyRenderHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;

@OnlyIn(Dist.CLIENT)
public class EventsCombatMode extends Screen
{
	private int trackDistance = 15;
	private LivingEntity trackMob = null;
	
	public EventsCombatMode()
	{
		super(new StringTextComponent(""));
	}

	@SubscribeEvent
	public void onRenderUI(RenderGameOverlayEvent event)
	{
		Minecraft mc = Minecraft.getInstance();
		PlayerEntity player = mc.player;
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IAbilityData abilityDataProps = AbilityDataCapability.get(player);
		IEntityStats entityStatsProps = EntityStatsCapability.get(player);
		IHakiData hakiDataProps = HakiDataCapability.get(player);

		int posX = mc.mainWindow.getScaledWidth();
		int posY = mc.mainWindow.getScaledHeight();

		//uiIngameForge.left_height += 1;

		if (abilityDataProps == null)
			return;

		/*
		 * GL11.glDisable(GL11.GL_TEXTURE_2D);
		 * GL11.glEnable(GL11.GL_BLEND);
		 * GL11.glDisable(GL11.GL_ALPHA_TEST);
		 * OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		 * GL11.glShadeModel(GL11.GL_SMOOTH);
		 * WyRenderHelper.drawColourOnScreen(WyHelper.hexToRGB("#FF69B4").getRGB(), 7, 0, 0, 512, 512, 0);
		 * GL11.glShadeModel(GL11.GL_FLAT);
		 * GL11.glDisable(GL11.GL_BLEND);
		 * GL11.glEnable(GL11.GL_ALPHA_TEST);
		 * GL11.glEnable(GL11.GL_TEXTURE_2D);
		 */

		if (event.getType() == ElementType.FOOD && devilFruitProps.getDevilFruit().equalsIgnoreCase("yomiyomi") && devilFruitProps.getZoanPoint().equalsIgnoreCase("yomi"))
			event.setCanceled(true);

		if (event.getType() == ElementType.HEALTH)
		{
			event.setCanceled(true);
			double maxHealth = player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getValue();
			double health = player.getHealth();

			this.drawCenteredString(mc.fontRenderer, (int) health + "", posX / 2 - 20, posY - 39, Color.RED.getRGB());

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

			mc.getTextureManager().bindTexture(Widget.GUI_ICONS_LOCATION);
			double f2 = player.getAbsorptionAmount();

			for (int i = MathHelper.ceil((maxHealth) / 2.0F) - 1; i >= 0; i--)
			{
				int k = (posX / 2 - 91) + i % 10 * 6;

				GuiUtils.drawTexturedModalRect(k, posY - 39, 16, 0, 9, 9, 0);
			}

			for (int i = 0; i < (100 - (((maxHealth - health) / maxHealth)) * 100) / 10; i++)
			{
				int k = (posX / 2 - 91) + i % 10 * 6;

				GuiUtils.drawTexturedModalRect(k, posY - 39, 16 + 36, 9 * 0, 9, 9, 0);
			}
		}

		if (abilityDataProps.isInCombatMode() && event.getType() == ElementType.HOTBAR)
		{
			event.setCanceled(true);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			mc.getTextureManager().bindTexture(ID.TEXTURE_COMBATMODE);

			for (int i = 0; i < 8; i++)
			{
				// if(abilityProps.getAbilityFromSlot(0) != null)
				// System.out.println("" + abilityProps.getAbilityFromSlot(0).isOnCooldown());
				GL11.glEnable(GL11.GL_BLEND);
				if (abilityDataProps.getHotbarAbilityFromSlot(i) != null && abilityDataProps.getHotbarAbilityFromSlot(i).isOnCooldown() && !abilityDataProps.getHotbarAbilityFromSlot(i).isDisabled())
					GuiUtils.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 23, 24, 0, 23, 23, 0);
				else if (abilityDataProps.getHotbarAbilityFromSlot(i) != null && abilityDataProps.getHotbarAbilityFromSlot(i).isCharging())
					GuiUtils.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 23, 72, 0, 23, 23, 0);
				else if (abilityDataProps.getHotbarAbilityFromSlot(i) != null && abilityDataProps.getHotbarAbilityFromSlot(i).isPassiveActive())
					GuiUtils.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 23, 48, 0, 23, 23, 0);
				else if (abilityDataProps.getHotbarAbilityFromSlot(i) != null && abilityDataProps.getHotbarAbilityFromSlot(i).isDisabled())
					GuiUtils.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 23, 96, 0, 23, 23, 0);
				else
					GuiUtils.drawTexturedModalRect((posX - 200 + (i * 50)) / 2, posY - 23, 0, 0, 23, 23, 0);
			}

			if (entityStatsProps.isCyborg())
			{
				GuiUtils.drawTexturedModalRect((posX - 260) / 2, posY - 42, 0, 52, 23, 40, 0);
				int barHeight = (int) (((float) entityStatsProps.getCola() / entityStatsProps.getMaxCola()) * 30) + 23;

				if (barHeight > 0 && barHeight < 24)
					barHeight = 24;
				else if (barHeight > 52)
					barHeight = 52;

				GuiUtils.drawTexturedModalRect((posX - 252) / 2, posY - 42, 32, barHeight, 16, 32, 0);
				this.drawCenteredString(mc.fontRenderer, entityStatsProps.getCola() + "", (posX - 237) / 2, posY - 12, Color.WHITE.getRGB());
			}

			for (int i = 0; i < 8; i++)
			{
				GLX.glBlendFuncSeparate(770, 771, 1, 0);
				if (abilityDataProps.getHotbarAbilityFromSlot(i) != null)
				{
					AbilityAttribute attr = abilityDataProps.getHotbarAbilityFromSlot(i).getAttribute();
					WyRenderHelper.drawAbilityIcon(WyHelper.getFancyName(attr.getAbilityTexture()), (posX - 192 + (i * 50)) / 2, posY - 19, 16, 16);
				}
			}

			int trackDistance = 15;
			if (hakiDataProps.hasKenHakiActive())
			{
				List<LivingEntity> nearbyEnemies = WyHelper.getEntitiesNear(player, 15);
				for (LivingEntity elb : nearbyEnemies)
				{
					if (trackMob == null)
					{
						trackMob = elb;
					}
					else
					{
						if (player.getDistance(elb) <= player.getDistance(trackMob))
							trackMob = elb;
						else if (trackMob.getHealth() <= 0 || !trackMob.isAlive())
							trackMob = null;
						if (trackMob != null && player.getDistance(trackMob) < trackDistance)
						{
							trackDistance = (int) player.getDistance(trackMob);
							float angle = (float) Math.toDegrees(Math.atan2(trackMob.posZ - player.posZ, trackMob.posX - player.posX));
							String text = "";

							text += trackDistance + " blocks";

							Minecraft.getInstance().getTextureManager().bindTexture(ID.ICON_HARROW);
							GL11.glPushMatrix();

							// GL11.glTranslated(270, 60, 0);

							int posX2 = (posX - 256) / 2;
							int posY2 = (posY - 256);

							GL11.glTranslated(posX2 + 190, posY2 + 60, 0);

							GL11.glTranslated(128, 128, 128);
							GL11.glScaled(0.2, 0.2, 0);

							Direction playerDir = WyHelper.get8Directions(player);

							if (playerDir == Direction.SOUTH)
								GL11.glRotated(angle - 90, 0.0, 0.0, 1.0);
							else if (playerDir == Direction.SOUTH_EAST)
								GL11.glRotated(angle - 45, 0.0, 0.0, 1.0);
							if (playerDir == Direction.EAST)
								GL11.glRotated(angle, 0.0, 0.0, 1.0);
							else if (playerDir == Direction.NORTH_EAST)
								GL11.glRotated(angle + 45, 0.0, 0.0, 1.0);
							else if (playerDir == Direction.NORTH)
								GL11.glRotated(angle + 90, 0.0, 0.0, 1.0);
							else if (playerDir == Direction.NORTH_WEST)
								GL11.glRotated(angle + 135, 0.0, 0.0, 1.0);
							else if (playerDir == Direction.WEST)
								GL11.glRotated(angle + 180, 0.0, 0.0, 1.0);
							else if (playerDir == Direction.SOUTH_WEST)
								GL11.glRotated(angle + 225, 0.0, 0.0, 1.0);

							GL11.glTranslated(-128, -128, -128);
							GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 0);

							GL11.glPopMatrix();

							WyRenderHelper.drawEntityOnScreen((posX + 320) / 2, posY - 42, 40, 40, 0, trackMob);
							this.drawCenteredString(mc.fontRenderer, text, (posX + 320) / 2, posY - 32, Color.WHITE.getRGB());
						}
					}
				}
			}

			GL11.glDisable(GL11.GL_BLEND);
		}
	}

	@SubscribeEvent
	public void updateFOV(FOVUpdateEvent event)
	{
		if (CommonConfig.instance.getFOVRemover())
		{
			if (event.getEntity().isPotionActive(Effects.SLOWNESS))
				event.setNewfov(1.0F);

			if (event.getEntity().isPotionActive(Effects.SPEED))
				event.setNewfov(1.0F);

			if ((event.getEntity().isPotionActive(Effects.SPEED)) && (event.getEntity().isSprinting()))
				event.setNewfov(1.1F);
		}
	}

}
