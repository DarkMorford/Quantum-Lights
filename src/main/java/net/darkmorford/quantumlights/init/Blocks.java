package net.darkmorford.quantumlights.init;

import net.darkmorford.quantumlights.block.BlockQuantumLamp;
import net.darkmorford.quantumlights.block.BlockLightAir;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Blocks
{
	@GameRegistry.ObjectHolder("quantumlights:quantumlamp")
	public static BlockQuantumLamp blockQuantumLamp;

	@GameRegistry.ObjectHolder("quantumlights:lightair")
	public static BlockLightAir blockLightAir;

	@SideOnly(Side.CLIENT)
	public static void initModels()
	{
		blockQuantumLamp.initModel();
	}
}
