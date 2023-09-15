package com.examine_monster.views;

import java.util.ArrayList;
import java.util.List;

import com.examine_monster.common.InfoTable;
import com.examine_monster.common.LabeledIcon;
import com.examine_monster.common.Monster;
import com.examine_monster.constants.Icon;

public class DefensiveStatsTable extends InfoTable
{
	public DefensiveStatsTable()
	{
		super("Defensive stats", Icon.DEFENSE_ICON_SM);
	}

	public void update(Monster monster)
	{
		List<LabeledIcon> iconTexts = new ArrayList<>();

		iconTexts.add(new LabeledIcon(Icon.STAB_ICON,
				String.valueOf(monster.getDefenseStab())));
		iconTexts.add(new LabeledIcon(Icon.SLASH_ICON,
				String.valueOf(monster.getDefenseSlash())));
		iconTexts.add(new LabeledIcon(Icon.CRUSH_ICON,
				String.valueOf(monster.getDefenseCrush())));
		iconTexts.add(new LabeledIcon(Icon.MAGIC_ICON,
				String.valueOf(monster.getDefenseMagic())));
		iconTexts.add(new LabeledIcon(Icon.RANGED_ICON,
				String.valueOf(monster.getDefenseRanged())));

		super.update(iconTexts);
	}
}
