package com.examine_monster.views;

import java.util.ArrayList;
import java.util.List;

import com.examine_monster.common.InfoTable;
import com.examine_monster.common.LabeledIcon;
import com.examine_monster.common.Monster;
import com.examine_monster.constants.Icon;

public class CombatStatsTable extends InfoTable
{
	public CombatStatsTable()
	{
		super("Combat stats", Icon.COMBAT_ICON);
	}

	public void update(Monster monster)
	{
		List<LabeledIcon> iconTexts = new ArrayList<>();

		iconTexts.add(new LabeledIcon(Icon.HITPOINTS_ICON,
				monster.getHitpoints() == 0 ? "?" : String.valueOf(monster.getHitpoints())));
		iconTexts.add(new LabeledIcon(Icon.ATTACK_ICON,
				String.valueOf(monster.getAttackLevel())));
		iconTexts.add(new LabeledIcon(Icon.STRENGTH_ICON,
				String.valueOf(monster.getStrengthLevel())));
		iconTexts.add(new LabeledIcon(Icon.DEFENSE_ICON,
				String.valueOf(monster.getDefenseLevel())));
		iconTexts.add(new LabeledIcon(Icon.MAGIC_ICON,
				String.valueOf(monster.getMagicLevel())));
		iconTexts.add(new LabeledIcon(Icon.RANGED_ICON,
				String.valueOf(monster.getRangedLevel())));

		super.update(iconTexts);
	}
}
