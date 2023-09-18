package com.examine_monster.views;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.examine_monster.common.Item;
import com.examine_monster.common.Monster;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.ColorScheme;

@Slf4j
public class MonsterDropsPanel extends JPanel
{
    public MonsterDropsPanel()
    {
        setLayout(new GridLayout(0, 4, 2, 2));
        setBackground(ColorScheme.DARKER_GRAY_COLOR);
    }

    public void update(Monster monster)
    {
        SwingUtilities.invokeLater(() ->
        {
            // TODO this is for testing!
            for (Item item : monster.getDrops())
            {
                log.info(item.getHaValue() + "");
            }
        });
    }
}
