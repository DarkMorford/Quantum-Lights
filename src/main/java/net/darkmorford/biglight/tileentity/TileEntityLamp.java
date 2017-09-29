package net.darkmorford.biglight.tileentity;

import net.darkmorford.biglight.config.GeneralConfig;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;

public class TileEntityLamp extends TileEntity implements ITickable
{
	private static final Block LIGHT_BLOCK      = Blocks.GLOWSTONE;
	private static final int   LIGHT_BLOCK_META = 0;

	@Override
	public void update()
	{
		int radius = GeneralConfig.searchRadius;

		// Select a random block inside our range
		int offsetX = world.rand.nextInt(radius);
		int offsetY = world.rand.nextInt(radius);
		int offsetZ = world.rand.nextInt(radius);

		if (world.rand.nextBoolean())
		{
			offsetX *= -1;
		}

		if (world.rand.nextBoolean())
		{
			offsetZ *= -1;
		}

		int posX = pos.getX() + offsetX;
		int posY = pos.getY() - offsetY;
		int posZ = pos.getZ() + offsetZ;

		// Don't place lights too close to bedrock
		if (posY < 5)
		{
			posY = 5;
		}

		// Try to put down a light source
		tryPlaceLightBlock(posX, posY, posZ);
	}

	public void removeLightBlocks()
	{
		int radius = GeneralConfig.searchRadius - 1;

		for (int posX = -radius; posX <= radius; ++posX)
		{
			for (int posY = -radius; posY <= radius; ++posY)
			{
				for (int posZ = -radius; posZ <= radius; ++posZ)
				{
					tryRemoveLightBlock(pos.add(posX, posY, posZ));
				}
			}
		}
	}

	private void tryPlaceLightBlock(int x, int y, int z)
	{
		tryPlaceLightBlock(new BlockPos(x, y, z));
	}

	@SuppressWarnings("deprecation")
	private void tryPlaceLightBlock(BlockPos pos)
	{
		IBlockState blockState = world.getBlockState(pos);

		if (world.isAirBlock(pos) && blockState.getBlock() != LIGHT_BLOCK && world.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, pos) < GeneralConfig.maximumLight)
		{
			world.setBlockState(pos, LIGHT_BLOCK.getStateFromMeta(LIGHT_BLOCK_META), 2 | 16);
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
