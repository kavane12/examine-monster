package com.examine_monster.constants;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import net.runelite.client.util.ImageUtil;

public class Icon
{
	// Loaded images from assets folder. Not accessible outside of this class.
	final static BufferedImage hitpointsImage = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/hitpoints.png");
	final static BufferedImage hitsplatMax = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/hitsplat_max.png");
	final static BufferedImage hitsplatPoison = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/hitsplat_poison.png");
	final static BufferedImage hitsplatVenom = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/hitsplat_venom.png");
	final static BufferedImage hitsplatDisease = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/hitsplat_disease.png");
	final static BufferedImage hitsplatBleed = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/hitsplat_bleed.png");
	final static BufferedImage aggressiveImage = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/aggressive.png");
	final static BufferedImage passiveImage = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/passive.png");
	final static BufferedImage attackSpeedImage = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/attack_speed.png");
	final static BufferedImage combatImage = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/combat.png");
	final static BufferedImage attackImage = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/attack.png");
	final static BufferedImage strengthImage = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/strength.png");
	final static BufferedImage defenseImage = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/defense.png");
	final static BufferedImage magicImage = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/magic.png");
	final static BufferedImage rangedImage = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/ranged.png");
	final static BufferedImage magicStrengthImage = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/magic_strength.png");
	final static BufferedImage rangedStrengthImage = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/ranged_strength.png");
	final static BufferedImage stabImage = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/style_stab.png");
	final static BufferedImage slashImage = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/style_slash.png");
	final static BufferedImage crushImage = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/style_crush.png");

	// Images
	public final static BufferedImage PLUGIN_BUTTON = ImageUtil.loadImageResource(PluginProperties.class,
			"../assets/plugin.png");

	// Size constants
	public final static int SIZE_SM = 15;
	public final static int SIZE_MD = 20;
	public final static int SIZE_LG = 30;

	// Icons (small)
	public final static ImageIcon COMBAT_ICON = new ImageIcon(
			ImageUtil.resizeImage(combatImage, SIZE_SM, SIZE_SM, true));
	public final static ImageIcon ATTACK_ICON_SM = new ImageIcon(
			ImageUtil.resizeImage(attackImage, SIZE_SM, SIZE_SM, true));
	public final static ImageIcon DEFENSE_ICON_SM = new ImageIcon(
			ImageUtil.resizeImage(defenseImage, SIZE_SM, SIZE_SM, true));

	// Icons (medium)
	public final static ImageIcon HITPOINTS_ICON = new ImageIcon(
			ImageUtil.resizeImage(hitpointsImage, SIZE_MD, SIZE_MD, true));
	public final static ImageIcon ATTACK_ICON = new ImageIcon(
			ImageUtil.resizeImage(attackImage, SIZE_MD, SIZE_MD, true));
	public final static ImageIcon STRENGTH_ICON = new ImageIcon(
			ImageUtil.resizeImage(strengthImage, SIZE_MD, SIZE_MD, true));
	public final static ImageIcon DEFENSE_ICON = new ImageIcon(
			ImageUtil.resizeImage(defenseImage, SIZE_MD, SIZE_MD, true));
	public final static ImageIcon MAGIC_ICON = new ImageIcon(
			ImageUtil.resizeImage(magicImage, SIZE_MD, SIZE_MD, true));
	public final static ImageIcon RANGED_ICON = new ImageIcon(
			ImageUtil.resizeImage(rangedImage, SIZE_MD, SIZE_MD, true));
	public final static ImageIcon MAGIC_STRENGTH_ICON = new ImageIcon(
			ImageUtil.resizeImage(magicStrengthImage, SIZE_MD, SIZE_MD, true));
	public final static ImageIcon RANGED_STRENGTH_ICON = new ImageIcon(
			ImageUtil.resizeImage(rangedStrengthImage, SIZE_MD, SIZE_MD, true));
	public final static ImageIcon STAB_ICON = new ImageIcon(
			ImageUtil.resizeImage(stabImage, SIZE_MD, SIZE_MD, true));
	public final static ImageIcon SLASH_ICON = new ImageIcon(
			ImageUtil.resizeImage(slashImage, SIZE_MD, SIZE_MD, true));
	public final static ImageIcon CRUSH_ICON = new ImageIcon(
			ImageUtil.resizeImage(crushImage, SIZE_MD, SIZE_MD, true));

	// Icons (large)
	public final static ImageIcon AGGRESSIVE_ICON = new ImageIcon(
			ImageUtil.resizeImage(aggressiveImage, SIZE_LG, SIZE_LG, true));
	public final static ImageIcon PASSIVE_ICON = new ImageIcon(
			ImageUtil.resizeImage(passiveImage, SIZE_LG, SIZE_LG, true));
	public final static ImageIcon ATTACK_SPEED_ICON = new ImageIcon(
			ImageUtil.resizeImage(attackSpeedImage, SIZE_LG, SIZE_LG, true));
	public final static ImageIcon HITSPLAT_MAX = new ImageIcon(
			ImageUtil.resizeImage(hitsplatMax, SIZE_LG, SIZE_LG, true));
	public final static ImageIcon HITSPLAT_POISON = new ImageIcon(
			ImageUtil.resizeImage(hitsplatPoison, SIZE_LG, SIZE_LG, true));
	public final static ImageIcon HITSPLAT_VENOM = new ImageIcon(
			ImageUtil.resizeImage(hitsplatVenom, SIZE_LG, SIZE_LG, true));
	public final static ImageIcon HITSPLAT_DISEASE = new ImageIcon(
			ImageUtil.resizeImage(hitsplatDisease, SIZE_LG, SIZE_LG, true));
	public final static ImageIcon HITSPLAT_BLEED = new ImageIcon(
			ImageUtil.resizeImage(hitsplatBleed, SIZE_LG, SIZE_LG, true));
}
