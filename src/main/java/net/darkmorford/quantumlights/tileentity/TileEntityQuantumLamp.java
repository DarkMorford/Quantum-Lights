package net.darkmorford.quantumlights.tileentity;

import net.darkmorford.quantumlights.config.GeneralConfig;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

import java.util.HashSet;
import java.util.Set;

public class TileEntityQuantumLamp extends TileEntity implements ITickable
{
	private static final Block LIGHT_BLOCK      = net.darkmorford.quantumlights.init.Blocks.blockLightAir;
	private static final int   LIGHT_BLOCK_META = 0;

	private Set<BlockPos> createdLights = new HashSet<>();

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		createdLights.clear();

		NBTTagList lightList = compound.getTagList("lights", 4);
		for (NBTBase tag : lightList)
		{
			createdLights.add(BlockPos.fromLong(((NBTTagLong)tag).getLong()));
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		NBTTagList lightList = new NBTTagList();
		for (BlockPos light : createdLights)
		{
			lightList.appendTag(new NBTTagLong(light.toLong()));
		}
		compound.setTag("lights", lightList);

		return compound;
	}

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

		// Put lights near ground level
		int maxLightHeight = world.getHeight(posX, posZ) + 4;
		if (posY > maxLightHeight)
		{
			posY = maxLightHeight;
		}

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
		for (BlockPos pos : createdLights)
		{
			tryRemoveLightBlock(pos);
		}

		createdLights.clear();
	}

	private void tryPlaceLightBlock(int x, int y, int z)
	{
		tryPlaceLightBlock(new BlockPos(x, y, z));
	}

	@SuppressWarnings("deprecation")
	private void tryPlaceLightBlock(BlockPos pos)
	{
		IBlockState blockState = world.getBlockState(pos);

		if (world.isAirBlock(pos) && blockState.getBlock() != LIGHT_BLOCK && world.getLight(pos, true) < GeneralConfig.maximumLight)
		{
			world.setBlockState(pos, LIGHT_BLOCK.getStateFromMeta(LIGHT_BLOCK_META), 2 | 16);
			createdLights.add(pos);
			markDirty();
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
			world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2 | 16);
		}
	}
}
