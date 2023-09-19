package com.examine_monster.views;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

// import org.w3c.dom.events.MouseEvent;

import com.examine_monster.common.Item;
import com.examine_monster.common.Monster;
import com.examine_monster.constants.Icon;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.input.MouseAdapter;
import net.runelite.client.input.MouseListener;
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
            for (Item item : monster.getDrops())
            {
                JPanel container = new JPanel();
                container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

                JLabel itemLabel = new JLabel(Icon.DEFENSE_ICON, SwingConstants.CENTER);
                itemLabel.setToolTipText(
                        "<html>%s<br>GE: %s<br>HA: %s</html>".formatted(item.getName(), item.getGeValue(),
                                item.getHaValue()));

                itemLabel.addMouseListener(new java.awt.event.MouseListener()
                {
                    public void mouseClicked(MouseEvent e)
                    {
                        try
                        {
                            Desktop.getDesktop().browse(new URI(item.getWikiUrl()));
                        }
                        catch (URISyntaxException | IOException ex)
                        {
                            // Guess we won't be opening the wiki link.
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent mouseEvent)
                    {
                    }

                    @Override
                    public void mouseReleased(MouseEvent mouseEvent)
                    {
                    }

                    @Override
                    public void mouseEntered(MouseEvent mouseEvent)
                    {
                    }

                    @Override
                    public void mouseExited(MouseEvent mouseEvent)
                    {
                    }
                });
                container.add(itemLabel);

                add(container);
            }
        });
    }
}
