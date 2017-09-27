package net.darkmorford.biglight;

import net.darkmorford.biglight.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(
		modid = BigLight.MODID,
		name = BigLight.MODNAME,
		version = BigLight.VERSION,
		acceptedMinecraftVersions = "[1.12,1.13)",
		useMetadata = true
)
public class BigLight
{
	public static final String MODID = "biglight";
	public static final String MODNAME = "Big Light";
	public static final String VERSION = "1.12.1-0.0.0.0";

	@Mod.Instance
	public static BigLight instance;

	@SidedProxy(clientSide = "net.darkmorford.biglight.proxy.ClientProxy", serverSide = "net.darkmorford.biglight.proxy.ServerProxy")
	public static CommonProxy proxy;

	public static Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}
}
