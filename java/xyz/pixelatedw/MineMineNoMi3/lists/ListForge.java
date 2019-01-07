package xyz.pixelatedw.MineMineNoMi3.lists;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.MineMineNoMi3.MainKeys;
import xyz.pixelatedw.MineMineNoMi3.events.EventsCore;
import xyz.pixelatedw.MineMineNoMi3.events.EventsDrops;
import xyz.pixelatedw.MineMineNoMi3.events.EventsEnchantments;
import xyz.pixelatedw.MineMineNoMi3.events.EventsMorphs;
import xyz.pixelatedw.MineMineNoMi3.events.EventsOnGain;
import xyz.pixelatedw.MineMineNoMi3.events.EventsQuestsProgress;
import xyz.pixelatedw.MineMineNoMi3.events.devilfruits.EventsDFWeaknesses;
import xyz.pixelatedw.MineMineNoMi3.events.devilfruits.EventsLogiaInvulnerability;
import xyz.pixelatedw.MineMineNoMi3.events.devilfruits.EventsPassives;
import xyz.pixelatedw.MineMineNoMi3.events.devilfruits.EventsSpecialFlying;
import xyz.pixelatedw.MineMineNoMi3.events.devilfruits.EventsZoanPassives;
import xyz.pixelatedw.MineMineNoMi3.gui.GUICombatMode;

public class ListForge 
{

	public static void init()
	{
		// Handles some core features of the mod, like IEEP registrations, update notifications or Early Access protection 		
		MinecraftForge.EVENT_BUS.register(new EventsCore());
		
		// Handles the drop events from different sources
		MinecraftForge.EVENT_BUS.register(new EventsDrops());
		
		// Handles the custom enchantment effects added by this mod
		MinecraftForge.EVENT_BUS.register(new EventsEnchantments());
		
		// Handles all the custom onGain events added by this mod
		MinecraftForge.EVENT_BUS.register(new EventsOnGain());

		// Handles the quest related stuff, accepting quests or progressing them throught different means
		MinecraftForge.EVENT_BUS.register(new EventsQuestsProgress());
		
		// Core devil fruits events
		MinecraftForge.EVENT_BUS.register(new EventsDFWeaknesses());
		MinecraftForge.EVENT_BUS.register(new EventsSpecialFlying());
		MinecraftForge.EVENT_BUS.register(new EventsLogiaInvulnerability());
		MinecraftForge.EVENT_BUS.register(new EventsZoanPassives());
		MinecraftForge.EVENT_BUS.register(new EventsPassives());
		
		if (FMLCommonHandler.instance().getEffectiveSide().isClient())
		{
			MinecraftForge.EVENT_BUS.register(new GUICombatMode(Minecraft.getMinecraft()));
			MinecraftForge.EVENT_BUS.register(new EventsMorphs(Minecraft.getMinecraft()));
			FMLCommonHandler.instance().bus().register(new MainKeys());
			MainKeys.init();
		}
	}
	
}
