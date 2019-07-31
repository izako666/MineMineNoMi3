package xyz.pixelatedw.mineminenomi.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.pixelatedw.mineminenomi.ModMain;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.json.WyJSONHelper;
import xyz.pixelatedw.mineminenomi.events.EventsCombatMode;
import xyz.pixelatedw.mineminenomi.init.ModAbilityProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModKeybindings;

public class ClientProxy implements IProxy
{

	public ClientProxy()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientProxy::clientSetup);
		
		// Handles Combat Mode GUI (including extra hearts, cola bar and obviously the ability slots) and the FOV remover
		MinecraftForge.EVENT_BUS.register(new EventsCombatMode());

		// Custom keybindings
		MinecraftForge.EVENT_BUS.register(new ModKeybindings());	
		ModKeybindings.init();	
		
		// Static strings
		ModI18n.init();
	}
		
	public static void clientSetup(FMLClientSetupEvent event)
	{
		ModMain.LOGGER.info("Client Setup Start");
		ModAbilityProjectiles.registerRenderers();

		WyHelper.generateLangFiles();
		WyJSONHelper.generateJSONModels(false);
		//WebAppHelper.generateWebAppJSONs();
		ModMain.LOGGER.info("Client Setup End");
	}
	
	@Override
	public PlayerEntity getClientPlayer()
	{
		return Minecraft.getInstance().player;
	}

	@Override
	public World getClientWorld()
	{
		return Minecraft.getInstance().world;
	}

	@Override
	public void spawnLogiaParticles(World world, String fx, double posX, double posY, double posZ)
	{
		
	}

	@Override
	public boolean spawnParticles(PlayerEntity player, double posX, double posY, double posZ, String fx)
	{
		return false;
	}



}
