package xyz.pixelatedw.mineminenomi.gui.extra;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.gui.ScrollPanel;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyRenderHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.AbilityManager;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.api.network.PacketAbilityDataSync;
import xyz.pixelatedw.mineminenomi.gui.GUISelectHotbarAbilities;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;

public class GUIAbilitiesList extends ScrollPanel
{

	private GUISelectHotbarAbilities parent;
	private IAbilityData props;
	private List<Entry> entries = new ArrayList<Entry>();
	private static final int ENTRY_HEIGHT = 20;

	public GUIAbilitiesList(GUISelectHotbarAbilities parent, IAbilityData abilityProps, Ability[] abilities)
	{
		super(parent.getMinecraft(), 215, 130, parent.height / 2 - 98, parent.width / 2 - 109);

		this.parent = parent;
		this.props = abilityProps;

		for (int i = 0; i < abilities.length - 1; i++)
		{
			if (abilities[i] != null)
				entries.add(new Entry(abilities[i]));
		}
	}

	@Override
	protected int getContentHeight()
	{
		return (this.entries.size()) * ENTRY_HEIGHT + 30;
	}

	@Override
	protected int getScrollAmount()
	{
		return 5;
	}

	@Override
	protected void drawPanel(int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY)
	{
		for (Entry entry : this.entries)
		{
			float y = relativeY;
			float x = (parent.width / 2 - 109) + 40;
			boolean flag = false;

			if (entry != null)
			{
				if(props.hasAbilityInHotbar(entry.ability))
					flag = true;

				Minecraft.getInstance().fontRenderer.drawStringWithShadow(I18n.format("ability." + WyHelper.getFancyName(entry.ability.getAttribute().getAttributeName()) + ".name"), x, y + 4, flag ? 0xFF0000 : 0xFFFFFF);
				WyRenderHelper.drawAbilityIcon(WyHelper.getFancyName(entry.attribute.getAbilityTexture()), MathHelper.floor(x) - 30, MathHelper.floor(y), 16, 16);
			}

			relativeY += ENTRY_HEIGHT * 1.25;
		}
	}

	private Entry findAbilityEntry(final int mouseX, final int mouseY)
	{
		double offset = (mouseY - top) + scrollDistance;

		if (offset <= 0)
			return null;

		int lineIdx = (int) (offset / (ENTRY_HEIGHT * 1.25));
		if (lineIdx >= entries.size())
			return null;

		Entry entry = entries.get(lineIdx);
		if (entry != null)
		{
			return entry;
		}

		return null;
	}

	@Override
	public boolean mouseClicked(final double mouseX, final double mouseY, final int button)
	{
		Entry entry = findAbilityEntry((int) mouseX, (int) mouseY);

		if (parent.slotSelected < 0 || entry == null)
			return false;

		boolean flag = true;
		
		for (int i = 0; i < props.countAbilitiesInHotbar(); i++)
		{
			if (props.getHotbarAbilityFromSlot(i) != null && props.getHotbarAbilityFromSlot(i).getAttribute().getAttributeName().equalsIgnoreCase(entry.ability.getAttribute().getAttributeName()))
			{
				flag = false;
			}
		}
		
		if (flag)
		{
			props.setAbilityInSlot(parent.slotSelected, AbilityManager.instance().getAbilityByName(WyHelper.getFancyName(entry.ability.getAttribute().getAttributeName())));
			ModNetwork.sendToServer(new PacketAbilityDataSync(props));
		}

		return super.mouseClicked(mouseX, mouseY, button);
	}

	class Entry
	{

		private Ability ability;
		private AbilityAttribute attribute;

		public Entry(Ability ability)
		{
			this.ability = ability;
			this.attribute = ability.getAttribute();
		}

	}
}
