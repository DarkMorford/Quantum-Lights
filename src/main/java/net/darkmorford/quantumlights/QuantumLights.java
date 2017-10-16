package net.darkmorford.quantumlights;

import net.darkmorford.quantumlights.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(
		modid = QuantumLights.MODID,
		name = QuantumLights.MODNAME,
		version = QuantumLights.VERSION,
		acceptedMinecraftVersions = "[1.12,1.13)",
		useMetadata = true
)
public class QuantumLights
{
	public static final String MODID = "quantumlights";
	public static final String MODNAME = "Quantum Lights";
	public static final String VERSION = "1.12.1-0.0.0.1";

	@Mod.Instance
	public static QuantumLights instance;

	@SidedProxy(clientSide = "net.darkmorford.quantumlights.proxy.ClientProxy", serverSide = "net.darkmorford.quantumlights.proxy.ServerProxy")
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
