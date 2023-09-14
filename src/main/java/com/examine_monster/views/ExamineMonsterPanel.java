package com.examine_monster.views;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import com.examine_monster.ExamineMonsterConfig;
import com.examine_monster.constants.PluginProperties;
import com.examine_monster.services.OsrsReboxedClient;
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
    private final MonsterInfoPanel infoPanel = new MonsterInfoPanel();
    private final PluginErrorPanel errorPanel = new PluginErrorPanel();

    @Inject
    ExamineMonsterPanel(ExamineMonsterConfig config)
    {
        this.config = config;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(ColorScheme.DARK_GRAY_COLOR);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initSearchField();
        add(searchField);

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

    public void lookupMonster(int id)
    {
        OsrsReboxedClient.lookupMonster(id).whenCompleteAsync((monster, error) ->
        {
            if (error != null)
                return;

            errorPanel.setVisible(false);
            infoPanel.update(monster);
        });
    }
}
