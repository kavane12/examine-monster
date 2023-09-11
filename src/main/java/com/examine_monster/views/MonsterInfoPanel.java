package com.examine_monster.views;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.examine_monster.common.Monster;

import net.runelite.client.ui.ColorScheme;

public class MonsterInfoPanel extends JPanel
{
    public MonsterInfoPanel()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(ColorScheme.DARK_GRAY_COLOR);
    }

    public void update(Monster monster)
    {
    }
}
