package net.darkmorford.biglight.proxy;

import net.darkmorford.biglight.BigLight;
import net.darkmorford.biglight.config.GeneralConfig;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;

import java.io.File;

public class CommonProxy
{
	private static Configuration config;

	public void preInit(FMLPreInitializationEvent event)
	{
		// Process configuration file
		File configDir = event.getModConfigurationDirectory();
		config = new Configuration(new File(configDir.getPath(), "biglight.cfg"));
		try
		{
			config.load();

			GeneralConfig.readConfig(config);
		}
		catch (Exception e)
		{
			BigLight.logger.log(Level.ERROR, "Error loading config file!", e);
		}
		finally
		{
			if (config.hasChanged())
			{
				config.save();
			}
		}
	}

	public void init(FMLInitializationEvent event)
	{
	}

	public void postInit(FMLPostInitializationEvent event)
	{
		// Just in case something changed our configuration
		if (config.hasChanged())
		{
			config.save();
		}
	}
}
