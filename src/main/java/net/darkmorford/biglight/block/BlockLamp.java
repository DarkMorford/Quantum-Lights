package net.darkmorford.biglight.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLamp extends Block
{
	public BlockLamp()
	{
		super(Material.ROCK);
		setUnlocalizedName("lamp");
		setRegistryName("lamp");

		setCreativeTab(CreativeTabs.DECORATIONS);

		setLightLevel(1.0F);
		setHardness(1.0F);
	}

	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}
