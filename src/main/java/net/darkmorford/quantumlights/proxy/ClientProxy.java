package net.darkmorford.quantumlights.proxy;

import net.darkmorford.quantumlights.init.ModBlocks;
import net.darkmorford.quantumlights.init.ModItems;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event)
	{
		ModBlocks.initModels();
		ModItems.initModels();
	}
}
