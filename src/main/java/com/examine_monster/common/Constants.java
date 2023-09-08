package com.examine_monster.common;

import net.runelite.client.util.ImageUtil;

import java.awt.image.BufferedImage;

public class Constants
{
    // Plugin name (also update to/from runelite-plugin.properties)
    public final static String PLUGIN_NAME = "Examine Monster";

    // Images
    public final static BufferedImage PANEL_BUTTON = ImageUtil.loadImageResource(Constants.class,
            "../assets/panel_button.png");
}
