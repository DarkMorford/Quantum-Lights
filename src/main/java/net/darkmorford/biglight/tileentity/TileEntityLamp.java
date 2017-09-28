package net.darkmorford.biglight.tileentity;

import net.darkmorford.biglight.config.GeneralConfig;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileEntityLamp extends TileEntity
{
	private static final Block LIGHT_BLOCK      = Blocks.GLOWSTONE;
	private static final int   LIGHT_BLOCK_META = 0;

	public void removeLightBlocks()
	{
		int radius = GeneralConfig.searchRadius;

		for (int posX = -radius; posX < radius; ++posX)
		{
			for (int posY = 0; posY < radius; ++posY)
			{
				for (int posZ = -radius; posZ < radius; ++posZ)
				{
					tryRemoveLightBlock(pos.add(posX, posY, posZ));
				}
			}
		}
	}

	private void tryRemoveLightBlock(int x, int y, int z)
	{
		tryRemoveLightBlock(new BlockPos(x, y, z));
	}

	private void tryRemoveLightBlock(BlockPos pos)
	{
		IBlockState blockState = world.getBlockState(pos);

		if (blockState.getBlock() == LIGHT_BLOCK && LIGHT_BLOCK.getMetaFromState(blockState) == LIGHT_BLOCK_META)
		{
			world.setBlockToAir(pos);
		}
	}
}
