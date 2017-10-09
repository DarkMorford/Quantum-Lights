package net.darkmorford.quantumlights.proxy;

import net.darkmorford.quantumlights.QuantumLights;
import net.darkmorford.quantumlights.block.BlockQuantumLamp;
import net.darkmorford.quantumlights.block.BlockLightAir;
import net.darkmorford.quantumlights.config.GeneralConfig;
import net.darkmorford.quantumlights.init.Blocks;
import net.darkmorford.quantumlights.init.Items;
import net.darkmorford.quantumlights.tileentity.TileEntityQuantumLamp;
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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
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
		config = new Configuration(new File(configDir.getPath(), "quantumlights.cfg"));
		try
		{
			config.load();

			GeneralConfig.readConfig(config);
		}
		catch (Exception e)
		{
			QuantumLights.logger.log(Level.ERROR, "Error loading config file!", e);
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
		OreDictionary.registerOre("plateLumium", Items.itemLuminescentPlate);
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

		REGISTRY.register(new BlockQuantumLamp());
		GameRegistry.registerTileEntity(TileEntityQuantumLamp.class, QuantumLights.MODID + ":quantumlamp");

		REGISTRY.register(new BlockLightAir());
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		IForgeRegistry<Item> REGISTRY = event.getRegistry();

		REGISTRY.register(new ItemBlock(Blocks.blockQuantumLamp).setRegistryName(Blocks.blockQuantumLamp.getRegistryName()));

		REGISTRY.register(new Item().setRegistryName("luminescentplate").setUnlocalizedName("luminescentplate"));
	}
}
