package com.examine_monster.views;

import java.util.ArrayList;
import java.util.List;

import com.examine_monster.common.InfoTable;
import com.examine_monster.common.LabeledIcon;
import com.examine_monster.common.Monster;
import com.examine_monster.constants.Icon;

public class MonsterAggressiveStatsInfoTable extends InfoTable
{
	public MonsterAggressiveStatsInfoTable()
	{
		super("Aggressive stats", Icon.ATTACK_ICON_SM);
	}

	public void update(Monster monster)
	{
		List<LabeledIcon> iconTexts = new ArrayList<>();

		iconTexts.add(new LabeledIcon(Icon.ATTACK_ICON,
				String.valueOf(monster.getAttackBonus())));
		iconTexts.add(new LabeledIcon(Icon.STRENGTH_ICON,
				String.valueOf(monster.getStrengthBonus())));
		iconTexts.add(new LabeledIcon(Icon.MAGIC_ICON,
				String.valueOf(monster.getMagicAttack())));
		iconTexts.add(new LabeledIcon(Icon.MAGIC_STRENGTH_ICON,
				String.valueOf(monster.getMagicBonus())));
		iconTexts.add(new LabeledIcon(Icon.RANGED_ICON,
				String.valueOf(monster.getRangedAttack())));
		iconTexts.add(new LabeledIcon(Icon.RANGED_STRENGTH_ICON,
				String.valueOf(monster.getRangedBonus())));

		super.update(iconTexts);
	}
}
