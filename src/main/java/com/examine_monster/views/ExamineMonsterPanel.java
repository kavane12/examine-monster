package com.examine_monster.views;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.examine_monster.ExamineMonsterConfig;
import com.examine_monster.common.Constants;
import com.google.inject.Inject;

import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.components.IconTextField;
import net.runelite.client.ui.components.PluginErrorPanel;

public class ExamineMonsterPanel extends PluginPanel
{
    private final ExamineMonsterConfig config;

    // Children of this panel (PluginPanel)
    private IconTextField searchField = new IconTextField();
    private final JPanel infoPanel = new JPanel();
    private final PluginErrorPanel errorPanel = new PluginErrorPanel();

    @Inject
    ExamineMonsterPanel(ExamineMonsterConfig config)
    {
        this.config = config;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(ColorScheme.DARK_GRAY_COLOR);

        initSearchField();
        add(searchField);

        initInfoPanel();
        add(infoPanel);

        initErrorPanel();
        add(errorPanel);
    }

    private void initSearchField()
    {
        searchField.setIcon(IconTextField.Icon.SEARCH);
        searchField.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        searchField.setHoverBackgroundColor(ColorScheme.DARK_GRAY_HOVER_COLOR);
        searchField.setPreferredSize(new Dimension(PluginPanel.PANEL_WIDTH - 20, 30));
        searchField.setMinimumSize(new Dimension(0, 30));
    }

    private void initInfoPanel()
    {
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(ColorScheme.DARK_GRAY_COLOR);
    }

    private void initErrorPanel()
    {
        errorPanel.setContent(Constants.PLUGIN_NAME, "Search for a monster or right-click examine one in-game.");
    }

    public void lookup(int id, String name, int combatLevel)
    {
        errorPanel.setContent("[" + id + "] " + name + " (level-" + combatLevel + ")", "");
    }
}
