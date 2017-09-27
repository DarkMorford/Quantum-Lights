package net.darkmorford.biglight.proxy;

import net.darkmorford.biglight.BigLight;
import net.darkmorford.biglight.block.BlockLamp;
import net.darkmorford.biglight.config.GeneralConfig;
import net.darkmorford.biglight.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.Level;

import java.io.File;

@Mod.EventBusSubscriber
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

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		IForgeRegistry<Block> REGISTRY = event.getRegistry();

		REGISTRY.register(new BlockLamp());
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		IForgeRegistry<Item> REGISTRY = event.getRegistry();

		REGISTRY.register(new ItemBlock(Blocks.blockLamp).setRegistryName(Blocks.blockLamp.getRegistryName()));
	}
}
