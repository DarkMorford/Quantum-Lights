package net.darkmorford.biglight.block;

import net.minecraft.block.BlockAir;

public class BlockLightAir extends BlockAir
{
	public BlockLightAir()
	{
		super();
		setUnlocalizedName("lightair");
		setRegistryName("lightair");

		setLightLevel(1.0F);
	}
}
