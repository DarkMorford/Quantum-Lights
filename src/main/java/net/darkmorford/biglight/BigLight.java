package net.darkmorford.biglight;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = BigLight.MODID, version = BigLight.VERSION)
public class BigLight
{
	public static final String MODID = "biglight";
	public static final String VERSION = "1.12.1-0.0.0.0";

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		// some example code
		System.out.println("DIRT BLOCK >> "+Blocks.DIRT.getUnlocalizedName());
	}
}
