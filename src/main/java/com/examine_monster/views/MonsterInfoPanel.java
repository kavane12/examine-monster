package com.examine_monster.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.examine_monster.common.Monster;

import net.runelite.client.ui.ColorScheme;

public class MonsterInfoPanel extends JPanel
{
    GridBagConstraints constraints = new GridBagConstraints();

    private final JLabel nameLabel = new JLabel();
    private final JLabel attributesLabel = new JLabel();
    private final JLabel attackStylesLabel = new JLabel();
    private final AttributesTable combatAttributesTable = new AttributesTable();
    private final CombatStatsTable combatStatsTable = new CombatStatsTable();
    private final AggressiveStatsTable aggressiveStatsTable = new AggressiveStatsTable();
    private final DefensiveStatsTable defensiveStatsTable = new DefensiveStatsTable();

    public MonsterInfoPanel()
    {
        setLayout(new GridBagLayout());
        setBackground(ColorScheme.DARK_GRAY_COLOR);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(0, 0, 0, 0);

        initNameLabel();
        constraints.gridy = 0;
        add(nameLabel, constraints);

        initAttributesLabel();
        constraints.gridy += 1;
        constraints.insets.top = 0;
        add(attributesLabel, constraints);

        constraints.gridy += 1;
        constraints.insets.top = 10;
        add(combatAttributesTable, constraints);

        initAttackStylesLabel();
        constraints.gridy += 1;
        constraints.insets.top = 0;
        add(attackStylesLabel, constraints);

        constraints.insets.top = 10;

        constraints.gridy += 1;
        add(combatStatsTable, constraints);
        constraints.gridy += 1;
        add(aggressiveStatsTable, constraints);
        constraints.gridy += 1;
        add(defensiveStatsTable, constraints);
    }

    private void initNameLabel()
    {
        nameLabel.setOpaque(true);
        nameLabel.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void initAttributesLabel()
    {
        attributesLabel.setVisible(false); // possible to not have attributes, so hide
        attributesLabel.setOpaque(true);
        attributesLabel.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        attributesLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
        attributesLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void initAttackStylesLabel()
    {
        attackStylesLabel.setVisible(false); // possible to not have attack styles, so hide
        attackStylesLabel.setOpaque(true);
        attackStylesLabel.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        attackStylesLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        attackStylesLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void update(Monster monster)
    {
        reset();

        SwingUtilities.invokeLater(() ->
        {
            // Update name label
            nameLabel.setText("%s (level-%d)".formatted(monster.getName(), monster.getCombatLevel()));

            // Update attributes list
            if (monster.getAttributes() != null && !monster.getAttributes().isEmpty())
            {
                attributesLabel.setText(String.join(", ", monster.getAttributes()));
                attributesLabel.setVisible(true);
            }

            // Update attack styles list
            if (monster.getAttackStyles() != null && !monster.getAttackStyles().isEmpty())
            {
                attackStylesLabel.setText(String.join(", ", monster.getAttackStyles()));
                attackStylesLabel.setVisible(true);
            }

            // Update Combat InfoTables
            combatAttributesTable.update(monster);
            combatStatsTable.update(monster);
            aggressiveStatsTable.update(monster);
            defensiveStatsTable.update(monster);
        });
    }

    public void reset()
    {
        attributesLabel.setVisible(false); // hide since can be null
        attackStylesLabel.setVisible(false); // hide since can be null

        combatAttributesTable.reset();
        combatStatsTable.reset();
        aggressiveStatsTable.reset();
        defensiveStatsTable.reset();
    }
}
