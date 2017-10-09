package net.darkmorford.quantumlights.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Items {
	@GameRegistry.ObjectHolder("quantumlights:luminescentplate")
	public static Item itemLuminescentPlate;

	@SideOnly(Side.CLIENT)
	public static void initModels()
	{
		ModelLoader.setCustomModelResourceLocation(itemLuminescentPlate, 0, new ModelResourceLocation(itemLuminescentPlate.getRegistryName(), "inventory"));
	}
}
