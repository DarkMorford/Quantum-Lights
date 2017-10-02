package net.darkmorford.biglight.init;

import net.darkmorford.biglight.block.BlockLamp;
import net.darkmorford.biglight.block.BlockLightAir;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Blocks
{
	@GameRegistry.ObjectHolder("biglight:lamp")
	public static BlockLamp blockLamp;

	@GameRegistry.ObjectHolder("biglight:lightair")
	public static BlockLightAir blockLightAir;

	@SideOnly(Side.CLIENT)
	public static void initModels()
	{
		blockLamp.initModel();
	}
}
