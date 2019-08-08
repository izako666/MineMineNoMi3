package xyz.pixelatedw.mineminenomi.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.json.WyJSONHelper;
import xyz.pixelatedw.mineminenomi.events.EventsCombatMode;
import xyz.pixelatedw.mineminenomi.events.EventsMorphs;
import xyz.pixelatedw.mineminenomi.events.devilfruits.EventsEffectOverlay;
import xyz.pixelatedw.mineminenomi.helpers.WebAppHelper;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModKeybindings;
import xyz.pixelatedw.mineminenomi.init.ModModels;
import xyz.pixelatedw.mineminenomi.particles.CustomParticle;
import xyz.pixelatedw.mineminenomi.particles.CustomParticleData;

public class ClientProxy implements IProxy
{

	public ClientProxy()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientProxy::clientSetup);
		
		// Devil Fruit related client-sided events
		MinecraftForge.EVENT_BUS.register(new EventsEffectOverlay());

		// Morphing related code including both full body model and 1st person hand
		MinecraftForge.EVENT_BUS.register(new EventsMorphs());
		
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
		ModModels.registerRenderers();

		WyHelper.generateLangFiles();
		WyJSONHelper.generateJSONModels(false);
		WebAppHelper.generateWebAppJSONs();
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
	public void spawnParticles(World world, CustomParticleData data)
	{
		CustomParticle cp = new CustomParticle(world, data.getTexture(),
				data.getPosX(), 
				data.getPosY(),
				data.getPosZ(), 
				data.getMotionX(),
				data.getMotionY(),
				data.getMotionZ());
		
		if(data.hasCustomGravity())
			cp.setParticleGravity(data.getGravity());
		
		if(data.hasCustomScale())
			cp.setParticleScale(data.getScale());
		
		if(data.hasCustomMaxAge())
			cp.setParticleAge(data.getMaxAge());

		Minecraft.getInstance().particles.addEffect(cp);
	}
	
	@Override
	public boolean spawnParticleEffects(PlayerEntity player, double posX, double posY, double posZ, String fx)
	{
		return false;
	}



}
