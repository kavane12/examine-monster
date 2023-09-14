package com.examine_monster.common;

import javax.swing.ImageIcon;

import lombok.Getter;

/*
 * Represents a icon with optional text and tooltip.
 */
@Getter
public class LabeledIcon
{
    ImageIcon icon;
    String text;
    String tooltip;

    public LabeledIcon(ImageIcon icon)
    {
        this(icon, null, null);
    }

    public LabeledIcon(ImageIcon icon, String text)
    {
        this(icon, text, null);
    }

    public LabeledIcon(ImageIcon icon, String text, String tooltip)
    {
        this.icon = icon;
        this.text = text;
        this.tooltip = tooltip;
    }
}
