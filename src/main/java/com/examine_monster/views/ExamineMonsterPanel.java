package com.examine_monster.views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.examine_monster.ExamineMonsterConfig;
import com.examine_monster.constants.PluginProperties;
import com.examine_monster.services.OsrsReboxedClient;
import com.google.inject.Inject;

import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.components.IconTextField;
import net.runelite.client.ui.components.PluginErrorPanel;
import net.runelite.client.ui.components.materialtabs.MaterialTab;
import net.runelite.client.ui.components.materialtabs.MaterialTabGroup;

public class ExamineMonsterPanel extends PluginPanel
{
    private final ExamineMonsterConfig config;

    private IconTextField searchField = new IconTextField();
    private final PluginErrorPanel errorPanel = new PluginErrorPanel();

    private final MonsterInfoPanel infoPanel = new MonsterInfoPanel();
    private final MonsterDropsPanel dropsPanel = new MonsterDropsPanel();

    private final JPanel tabContent = new JPanel();
    private final MaterialTabGroup tabs = new MaterialTabGroup(tabContent);
    private final MaterialTab infoTab = new MaterialTab("Info", tabs, infoPanel);
    private final MaterialTab dropsTab = new MaterialTab("Drops", tabs, dropsPanel);

    @Inject
    ExamineMonsterPanel(ExamineMonsterConfig config)
    {
        this.config = config;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(ColorScheme.DARK_GRAY_COLOR);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initSearchField();
        add(searchField);

        initErrorPanel();
        add(errorPanel);

        initTabs();
        add(tabs);
        add(tabContent);
    }

    private void initSearchField()
    {
        searchField.setIcon(IconTextField.Icon.SEARCH);
        searchField.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        searchField.setHoverBackgroundColor(ColorScheme.DARK_GRAY_HOVER_COLOR);
        searchField.setPreferredSize(new Dimension(PluginPanel.PANEL_WIDTH - 20, 30));
        searchField.setMinimumSize(new Dimension(0, 30));

        // TODO: Remove temp listener used for testing.
        searchField.addKeyListener(
                new KeyListener()
                {

                    @Override
                    public void keyTyped(KeyEvent event)
                    {
                    }

                    @Override
                    public void keyPressed(KeyEvent event)
                    {
                        if (event.getKeyCode() == KeyEvent.VK_ENTER)
                        {
                            if (searchField.getText().isBlank())
                                lookupMonster(8060);
                            else
                            {
                                try
                                {
                                    lookupMonster(Integer.valueOf(searchField.getText()));
                                }
                                catch (NumberFormatException e)
                                {

                                }
                            }

                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent event)
                    {
                    }

                }

        );
    }

    private void initErrorPanel()
    {
        errorPanel.setContent(PluginProperties.PLUGIN_NAME, "Search for a monster or right-click examine one in-game.");
    }

    private void initTabs()
    {
        tabs.setVisible(false);
        tabContent.setVisible(false);

        tabs.setLayout(new GridLayout(1, 2, 10, 10));
        tabs.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        initTab(infoTab);
        tabs.addTab(infoTab);

        initTab(dropsTab);
        tabs.addTab(dropsTab);
    }

    private void initTab(MaterialTab tab)
    {
        tab.setOpaque(true);
        tab.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        tab.setHorizontalAlignment(SwingConstants.CENTER);
        tab.setVerticalAlignment(SwingConstants.CENTER);
    }

    public void lookupMonster(int id)
    {
        searchField.setIcon(IconTextField.Icon.LOADING);
        OsrsReboxedClient.lookupMonster(id).whenCompleteAsync((monster, error) ->
        {
            if (error != null)
            {
                searchField.setIcon(IconTextField.Icon.ERROR);
                return;
            }

            searchField.setIcon(IconTextField.Icon.SEARCH);
            errorPanel.setVisible(false);
            tabs.setVisible(true);
            tabContent.setVisible(true);

            tabs.select(infoTab);

            infoPanel.update(monster);
            dropsPanel.update(monster);
        });
    }
}
