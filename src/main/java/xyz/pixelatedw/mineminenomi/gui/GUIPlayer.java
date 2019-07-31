package xyz.pixelatedw.mineminenomi.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModDevilFruits;

@OnlyIn(Dist.CLIENT)
public class GUIPlayer extends Screen
{
	private PlayerEntity player;

	public GUIPlayer(PlayerEntity player)
	{
		super(new StringTextComponent(""));
		this.player = player;
	}

	@Override
	public void render(int x, int y, float f)
	{
		this.renderBackground();

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		IEntityStats entityStatsProps = EntityStatsCapability.get(player);
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);

		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;

		String factionGUI = I18n.format(ID.LANG_GUI_FACTION);
		String raceGUI = I18n.format(ID.LANG_GUI_RACE);
		String styleGUI = I18n.format(ID.LANG_GUI_STYLE);

		String faction = WyHelper.getFancyName(entityStatsProps.getFaction().toLowerCase());
		if(WyHelper.isNullOrEmpty(faction))
			faction = "empty";
		
		String race = WyHelper.getFancyName(entityStatsProps.getRace().toLowerCase());
		if(WyHelper.isNullOrEmpty(race))
			race = "empty";
		
		String style = WyHelper.getFancyName(entityStatsProps.getFightingStyle().toLowerCase());
		if(WyHelper.isNullOrEmpty(style))
			style = "empty";
		
		String factionActual = I18n.format("faction." + faction + ".name");
		String raceActual = I18n.format("race." + race + ".name");
		String styleActual = I18n.format("style." + style + ".name");

		// TODO DORIKI and COLA I18n strings
		if (entityStatsProps.isCyborg())
			this.minecraft.fontRenderer.drawStringWithShadow(TextFormatting.BOLD + "COLA: " + TextFormatting.RESET + entityStatsProps.getCola() + " / " + entityStatsProps.getMaxCola(), posX - 30, posY + 50, -1);
		this.minecraft.fontRenderer.drawStringWithShadow(TextFormatting.BOLD + "DORIKI: " + TextFormatting.RESET + entityStatsProps.getDoriki(), posX - 30, posY + 70, -1);
		this.minecraft.fontRenderer.drawStringWithShadow(TextFormatting.BOLD + factionGUI + ": " + TextFormatting.RESET + factionActual, posX - 30, posY + 90, -1);
		this.minecraft.fontRenderer.drawStringWithShadow(TextFormatting.BOLD + raceGUI + ": " + TextFormatting.RESET + raceActual, posX - 30, posY + 110, -1);
		this.minecraft.fontRenderer.drawStringWithShadow(TextFormatting.BOLD + styleGUI + ": " + TextFormatting.RESET + styleActual, posX - 30, posY + 130, -1);

		if (entityStatsProps.getBelly() > 0)
		{
			this.minecraft.fontRenderer.drawStringWithShadow("" + entityStatsProps.getBelly(), posX + 215, posY + 72, -1);
			this.minecraft.textureManager.bindTexture(ID.TEXTURE_CURRENCIES);		
			GuiUtils.drawTexturedModalRect(posX + 190, posY + 60, 0, 32, 32, 64, 1);
		}

		if (entityStatsProps.getExtol() > 0)
		{
			this.minecraft.fontRenderer.drawStringWithShadow("" + entityStatsProps.getExtol(), posX + 215, posY + 102, -1);
			this.minecraft.textureManager.bindTexture(ID.TEXTURE_CURRENCIES);
			GuiUtils.drawTexturedModalRect(posX + 190, posY + 90, 34, 32, 64, 86, 1);
		}

		if (!WyHelper.isNullOrEmpty(devilFruitProps.getDevilFruit()))
		{
			ItemStack yamiFruit = new ItemStack(ModDevilFruits.YamiYamiNoMi);
			ItemStack df;
			if (!devilFruitProps.getDevilFruit().equals("yamidummy"))
			{
				df = DevilFruitsHelper.getDevilFruitItem(devilFruitProps.getDevilFruit());
				//System.out.println();
				if (devilFruitProps.hasYamiPower())
					this.minecraft.fontRenderer.drawStringWithShadow(TextFormatting.BOLD + "" + yamiFruit.getDisplayName() + " + " + df.getDisplayName(), posX - 28, posY + 194, -1);
				else
					this.minecraft.fontRenderer.drawStringWithShadow(TextFormatting.BOLD + "" + df.getDisplayName().getFormattedText(), posX - 28, posY + 194, -1);

				if (devilFruitProps.hasYamiPower())
					this.drawItemStack(yamiFruit, posX - 56, posY + 187, "");
				this.drawItemStack(df, posX - 50, posY + 190, "");
			}
			else
			{
				this.minecraft.fontRenderer.drawStringWithShadow(TextFormatting.BOLD + "" + yamiFruit.getDisplayName(), posX - 28, posY + 194, -1);
				this.drawItemStack(yamiFruit, posX - 50, posY + 190, "");
			}

		}

		//WyRenderHelper.drawEntityOnScreen();
		//GuiInventory.drawEntityOnScreen(posX + 140, posY + 180, 68, 0, 0, this.player);

		super.render(x, y, f);
	}

	@Override
	public void init()
	{
		int posX = (this.width - 256) / 2;
		int posY = (this.height - 256) / 2;

		this.addButton(new Button(posX - 23, posY + 210, 80, 20, I18n.format(ID.LANG_GUI_ABILITIES), b -> 
		{
			Minecraft.getInstance().displayGuiScreen(new GUISelectHotbarAbilities(player));
		}));
		
		if (CommonConfig.instance.getQuests())
		{
			this.addButton(new Button(posX + 63, posY + 210, 80, 20, I18n.format(ID.LANG_GUI_QUESTS), b ->
			{
				
			}));
		}
	}

	@Override
	public boolean isPauseScreen()
	{
		return false;
	}

	@SuppressWarnings("resource")
	private void drawItemStack(ItemStack itemStack, int x, int y, String string)
	{
		GL11.glTranslatef(0.0F, 0.0F, 32.0F);
		this.itemRenderer.zLevel = 200.0F;
		FontRenderer font = null;
		if (itemStack != null)
			font = itemStack.getItem().getFontRenderer(itemStack);
		if (font == null)
			font = this.font;
		this.itemRenderer.renderItemAndEffectIntoGUI(itemStack, x, y);
		this.itemRenderer.zLevel = 0.0F;
	}
}
