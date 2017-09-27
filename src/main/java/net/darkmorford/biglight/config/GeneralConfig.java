package net.darkmorford.biglight.config;

import net.minecraftforge.common.config.Configuration;

public class GeneralConfig
{
	private static final String CATEGORY_NAME = "general";

	public static int searchRadius = 16;
	public static int maximumLight = 9;

	public static void readConfig(Configuration cfg)
	{
		cfg.addCustomCategoryComment(CATEGORY_NAME, "General configuration");

		searchRadius = cfg.getInt("searchRadius", CATEGORY_NAME, searchRadius, 1, 64, "How far out to place light sources");
		maximumLight = cfg.getInt("maximumLight", CATEGORY_NAME, maximumLight, 0, 15, "Maximum light level to place a light source");
	}
}
