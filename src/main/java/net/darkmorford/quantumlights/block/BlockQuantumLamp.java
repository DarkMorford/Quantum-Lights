package net.darkmorford.quantumlights.block;

import net.darkmorford.quantumlights.tileentity.TileEntityQuantumLamp;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockQuantumLamp extends Block implements ITileEntityProvider
{
	public BlockQuantumLamp()
	{
		super(Material.CIRCUITS);
		setUnlocalizedName("quantumlamp");
		setRegistryName("quantumlamp");

		setCreativeTab(CreativeTabs.DECORATIONS);

		setLightLevel(1.0F);
		setHardness(2.0F);
		setResistance(20.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityQuantumLamp();
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileEntity te = worldIn.getTileEntity(pos);
		if (te != null && te instanceof TileEntityQuantumLamp)
		{
			((TileEntityQuantumLamp)te).removeLightBlocks();
		}

		super.breakBlock(worldIn, pos, state);
	}

	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}
