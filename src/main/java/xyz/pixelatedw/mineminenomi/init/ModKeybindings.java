package xyz.pixelatedw.mineminenomi.init;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.gui.GUIPlayer;
import xyz.pixelatedw.mineminenomi.packets.PacketCombatModeTrigger;
import xyz.pixelatedw.mineminenomi.packets.PacketForceSync;
import xyz.pixelatedw.mineminenomi.packets.PacketUseAbility;

public class ModKeybindings
{
	public static KeyBinding guiPlayer, enterCombatMode, combatSlot1, combatSlot2, combatSlot3, combatSlot4, combatSlot5, combatSlot6, combatSlot7, combatSlot8;

	private static KeyBinding[] keyBindsCombatbar;

	public static void init()
	{
		guiPlayer = new KeyBinding(ID.LANG_KEY_PLAYER, GLFW.GLFW_KEY_R, ID.LANG_KEYS_CATEGORY);
		ClientRegistry.registerKeyBinding(guiPlayer);

		enterCombatMode = new KeyBinding(ID.LANG_KEY_COMBATMODE, GLFW.GLFW_KEY_LEFT_ALT, ID.LANG_KEYS_CATEGORY);
		ClientRegistry.registerKeyBinding(enterCombatMode);

		combatSlot1 = new KeyBinding(ID.LANG_KEY_COMBATSLOT1, GLFW.GLFW_KEY_1, ID.LANG_KEYS_CATEGORY);
		ClientRegistry.registerKeyBinding(combatSlot1);
		combatSlot2 = new KeyBinding(ID.LANG_KEY_COMBATSLOT2, GLFW.GLFW_KEY_2, ID.LANG_KEYS_CATEGORY);
		ClientRegistry.registerKeyBinding(combatSlot2);
		combatSlot3 = new KeyBinding(ID.LANG_KEY_COMBATSLOT3, GLFW.GLFW_KEY_3, ID.LANG_KEYS_CATEGORY);
		ClientRegistry.registerKeyBinding(combatSlot3);
		combatSlot4 = new KeyBinding(ID.LANG_KEY_COMBATSLOT4, GLFW.GLFW_KEY_4, ID.LANG_KEYS_CATEGORY);
		ClientRegistry.registerKeyBinding(combatSlot4);
		combatSlot5 = new KeyBinding(ID.LANG_KEY_COMBATSLOT5, GLFW.GLFW_KEY_5, ID.LANG_KEYS_CATEGORY);
		ClientRegistry.registerKeyBinding(combatSlot5);
		combatSlot6 = new KeyBinding(ID.LANG_KEY_COMBATSLOT6, GLFW.GLFW_KEY_6, ID.LANG_KEYS_CATEGORY);
		ClientRegistry.registerKeyBinding(combatSlot6);
		combatSlot7 = new KeyBinding(ID.LANG_KEY_COMBATSLOT7, GLFW.GLFW_KEY_7, ID.LANG_KEYS_CATEGORY);
		ClientRegistry.registerKeyBinding(combatSlot7);
		combatSlot8 = new KeyBinding(ID.LANG_KEY_COMBATSLOT8, GLFW.GLFW_KEY_8, ID.LANG_KEYS_CATEGORY);
		ClientRegistry.registerKeyBinding(combatSlot8);

		keyBindsCombatbar = new KeyBinding[]
			{
					combatSlot1, combatSlot2, combatSlot3, combatSlot4, combatSlot5, combatSlot6, combatSlot7, combatSlot8
			};
	}

	public static boolean isShiftKeyDown()
	{
		return InputMappings.isKeyDown(Minecraft.getInstance().mainWindow.getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT) || InputMappings.isKeyDown(Minecraft.getInstance().mainWindow.getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT);
	}

	@SuppressWarnings("resource")
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event)
	{
		Minecraft minecraft = Minecraft.getInstance();
		PlayerEntity player = minecraft.player;
		ClientWorld world = minecraft.world;

		if (player == null)
			return;

		IAbilityData abilityDataProps = AbilityDataCapability.get(player);

		if (guiPlayer.isPressed())
		{
			byte sync = 0b000000111;
			ModNetwork.sendToServer(new PacketForceSync(sync));

			Minecraft.getInstance().displayGuiScreen(new GUIPlayer(player));
		}

		if (enterCombatMode.isPressed())
		{
			abilityDataProps.setCombatMode(!abilityDataProps.isInCombatMode());
			if (abilityDataProps.isInCombatMode())
			{
				for (KeyBinding kb : Minecraft.getInstance().gameSettings.keyBindsHotbar)
				{
					kb.bind(InputMappings.getInputByCode(GLFW.GLFW_KEY_UNKNOWN, 0));
				}

				int keyId = GLFW.GLFW_KEY_1;
				for (KeyBinding kb : keyBindsCombatbar)
				{
					if (kb.getKey().getKeyCode() < GLFW.GLFW_KEY_8)
						kb.bind(InputMappings.getInputByCode(keyId, 0));
					keyId++;
				}

				KeyBinding.resetKeyBindingArrayAndHash();
			}
			else
			{
				for (KeyBinding kb : keyBindsCombatbar)
				{
					if (kb.getKey().getKeyCode() < GLFW.GLFW_KEY_8)
						kb.bind(InputMappings.getInputByCode(GLFW.GLFW_KEY_UNKNOWN, 0));
				}

				int keyId = GLFW.GLFW_KEY_1;
				for (KeyBinding kb : Minecraft.getInstance().gameSettings.keyBindsHotbar)
				{
					kb.bind(InputMappings.getInputByCode(keyId, 0));
					keyId++;
				}

				KeyBinding.resetKeyBindingArrayAndHash();
			}
			ModNetwork.sendToServer(new PacketCombatModeTrigger());
		}

		int j = keyBindsCombatbar.length;

		for (int i = 0; i < j; i++)
		{
			if (keyBindsCombatbar[i].isPressed())
			{
				if (abilityDataProps.isInCombatMode())
					ModNetwork.sendToServer(new PacketUseAbility(i));
				else
					player.inventory.currentItem = i;
			}
		}
	}
}
