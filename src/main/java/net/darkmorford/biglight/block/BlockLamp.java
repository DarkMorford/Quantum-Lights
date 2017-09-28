package net.darkmorford.biglight.block;

import net.darkmorford.biglight.tileentity.TileEntityLamp;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
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

public class BlockLamp extends Block implements ITileEntityProvider
{
	public BlockLamp()
	{
		super(Material.CIRCUITS);
		setUnlocalizedName("lamp");
		setRegistryName("lamp");

		setCreativeTab(CreativeTabs.DECORATIONS);

		setLightLevel(1.0F);
		setHardness(2.0F);
		setResistance(20.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityLamp();
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileEntity te = worldIn.getTileEntity(pos);
		if (te != null && te instanceof TileEntityLamp)
		{
			((TileEntityLamp)te).removeLightBlocks();
		}

		super.breakBlock(worldIn, pos, state);
	}

	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}
