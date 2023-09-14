package com.examine_monster.common;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;

public class InfoTable extends JPanel
{
    GridBagConstraints constraints = new GridBagConstraints();

    JLabel headerLabel = new JLabel();
    JPanel iconGrid = new JPanel();

    public InfoTable()
    {
        this(null, null);
    }

    public InfoTable(String header)
    {
        this(header, null);
    }

    public InfoTable(String header, ImageIcon headerIcon)
    {
        setLayout(new GridBagLayout());
        setBackground(ColorScheme.DARKER_GRAY_COLOR);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;

        initHeaderLabel(header, headerIcon);
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 0, 0, 0);
        add(headerLabel, constraints);

        initIconGrid();
        constraints.gridy = 1;
        constraints.insets = new Insets(5, 0, 5, 0);
        add(iconGrid, constraints);
    }

    private void initHeaderLabel(String header, ImageIcon headerIcon)
    {
        if (header != null)
        {
            headerLabel.setText(header);
            headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
            if (headerIcon != null)
            {
                headerLabel.setIcon(headerIcon);
            }
        }
    }

    private void initIconGrid()
    {
        iconGrid.setLayout(new GridLayout());
        iconGrid.setBackground(ColorScheme.DARKER_GRAY_COLOR);
    }

    public void update(List<LabeledIcon> iconTexts)
    {
        this.update(iconTexts, JLabel.BOTTOM);
    }

    public void update(List<LabeledIcon> iconTexts, int textLocation)
    {
        if (iconTexts.isEmpty())
        {
            setVisible(false);
        }
        else
        {
            setVisible(true);
        }

        for (LabeledIcon iconText : iconTexts)
        {
            JLabel label = new JLabel(iconText.getIcon(), SwingConstants.CENTER);
            label.setFont(FontManager.getRunescapeSmallFont());
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setVerticalTextPosition(textLocation);

            if (iconText.getText() != null)
            {
                label.setText(iconText.getText());

            }

            if (iconText.getTooltip() != null)
            {
                label.setToolTipText(iconText.getTooltip());

            }

            iconGrid.add(label, constraints);
        }
    }

    public void reset()
    {
        iconGrid.removeAll();
    }
}
