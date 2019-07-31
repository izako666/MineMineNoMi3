package xyz.pixelatedw.mineminenomi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.api.telemetry.WyTelemetry;
import xyz.pixelatedw.mineminenomi.commands.CommandBelly;
import xyz.pixelatedw.mineminenomi.commands.CommandBounty;
import xyz.pixelatedw.mineminenomi.commands.CommandDoriki;
import xyz.pixelatedw.mineminenomi.commands.CommandExtol;
import xyz.pixelatedw.mineminenomi.commands.CommandGetWantedPoster;
import xyz.pixelatedw.mineminenomi.commands.CommandIssueBounty;
import xyz.pixelatedw.mineminenomi.commands.CommandRemoveDF;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModCapabilities;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.proxy.ClientProxy;
import xyz.pixelatedw.mineminenomi.proxy.IProxy;
import xyz.pixelatedw.mineminenomi.proxy.ServerProxy;

@Mod(ID.PROJECT_ID)
public class ModMain
{
	public static ModMain instance;
	public static IProxy proxy;
	public static final Logger LOGGER = LogManager.getLogger();
	
	public ModMain()
	{
		String basicPath = System.getProperty("java.class.path");
		ID.PROJECT_RESOURCES_FOLDER = basicPath.substring(0, basicPath.indexOf("\\bin")).replace("file:/", "").replace("%20", " ") + "/src/main/resources";
		
		instance = this;
		proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
		
		CommonConfig.init();
		ModNetwork.init();
		
		if (!WyDebug.isDebug())
		{
			WyTelemetry.addMiscStat("onlinePlayers", "Online Players", 1);
			WyTelemetry.sendAllData();

			Runtime.getRuntime().addShutdownHook(new Thread()
			{
				@Override
				public void run()
				{
					try
					{
						WyTelemetry.addMiscStat("onlinePlayers", "Online Players", -1);
						WyTelemetry.sendAllData();
						Thread.sleep(100);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			});
		}
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ModMain::commonSetup);	
		MinecraftForge.EVENT_BUS.addListener(this::serverAboutToStart);
	}
	
	private static void commonSetup(FMLCommonSetupEvent event)
	{
		LOGGER.info("Common Setup Start");
		
		MinecraftForge.EVENT_BUS.register(new ModCapabilities());
		ModCapabilities.init();
		
		LOGGER.info("Common Setup End");
	}
	
	private void serverAboutToStart(FMLServerAboutToStartEvent event)
	{
		CommandDispatcher dispacher = event.getServer().getCommandManager().getDispatcher();
		CommandDoriki.register(dispacher);
		CommandBounty.register(dispacher);
		CommandBelly.register(dispacher);
		CommandExtol.register(dispacher);
		CommandIssueBounty.register(dispacher);
		CommandGetWantedPoster.register(dispacher);
		CommandRemoveDF.register(dispacher);
	}
}
