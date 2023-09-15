package com.examine_monster.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;

import com.examine_monster.common.InfoTable;
import com.examine_monster.common.LabeledIcon;
import com.examine_monster.common.Monster;
import com.examine_monster.constants.Icon;
import com.examine_monster.constants.Npc;

public class AttributesTable extends InfoTable
{
	public void update(Monster monster)
	{
		List<LabeledIcon> iconTexts = new ArrayList<>();

		// Aggression
		iconTexts.add(monster.isAggressive() ? new LabeledIcon(Icon.AGGRESSIVE_ICON)
				: new LabeledIcon(Icon.PASSIVE_ICON, null, monster.isAggressive() ? "Aggressive" : "Passive"));
		// Max hit
		iconTexts.add(new LabeledIcon(Icon.HITSPLAT_MAX,
				String.valueOf(monster.getMaxHit()), "Max hit: %d".formatted(monster.getMaxHit())));
		// Attack speed
		iconTexts.add(new LabeledIcon(Icon.ATTACK_SPEED_ICON,
				String.valueOf(monster.getAttackSpeed()),
				"Attack speed: %d (%.1fs interval)".formatted(monster.getAttackSpeed(),
						monster.getAttackSpeed() * 0.6)));
		// Poison, Venom, Disease, and Bleed
		if (Arrays.stream(Npc.INFLICT_DISEASE_NPCS).anyMatch(id -> id == monster.getId()))
			iconTexts.add(new LabeledIcon(Icon.HITSPLAT_DISEASE, null, "Inflicts disease"));
		else if (Arrays.stream(Npc.INFLICT_BLEED_NPCS).anyMatch(id -> id == monster.getId()))
			iconTexts.add(new LabeledIcon(Icon.HITSPLAT_BLEED, null, "Inflicts bleed"));
		else if (monster.isVenomous())
			iconTexts.add(new LabeledIcon(Icon.HITSPLAT_VENOM, null, "Inflicts venom"));
		else if (monster.isPoisonous())
			iconTexts.add(new LabeledIcon(Icon.HITSPLAT_POISON, null, "Inflicts poison"));

		super.update(iconTexts, JLabel.CENTER);
	}
}
