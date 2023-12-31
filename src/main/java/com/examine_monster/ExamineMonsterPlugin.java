package com.examine_monster;

import com.examine_monster.constants.Icon;
import com.examine_monster.constants.PluginProperties;
import com.examine_monster.views.ExamineMonsterPanel;
import com.google.inject.Provides;

import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import javax.inject.Inject;
import javax.swing.SwingUtilities;

import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.MenuEntry;
import net.runelite.api.NPC;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;

@Slf4j
@PluginDescriptor(name = PluginProperties.PLUGIN_NAME, description = PluginProperties.PLUGIN_DESCRIPTION)
public class ExamineMonsterPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ExamineMonsterConfig config;

	@Inject
	private ClientToolbar clientToolbar;
	private ExamineMonsterPanel panel;
	private NavigationButton navButton;

	@Override
	protected void startUp() throws Exception
	{
		panel = injector.getInstance(ExamineMonsterPanel.class);
		navButton = NavigationButton.builder()
				.tooltip(PluginProperties.PLUGIN_NAME)
				.icon(Icon.PLUGIN_BUTTON)
				.panel(panel)
				.priority(5)
				.build();

		clientToolbar.addNavigation(navButton);
	}

	@Override
	protected void shutDown() throws Exception
	{
		clientToolbar.removeNavigation(navButton);
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event)
	{
		MenuEntry[] rightClickMenuEntries = client.getMenuEntries();
		NPC targetNpc = event.getMenuEntry().getNpc();

		// Examined an attackable NPC with combat level > 0.
		if (event.getMenuAction() == MenuAction.EXAMINE_NPC
				&& targetNpc != null
				&& targetNpc.getCombatLevel() > 0
				&& Arrays.asList(rightClickMenuEntries).stream()
						.anyMatch(menuEntry -> menuEntry.getOption().equals("Attack")))
		{
			SwingUtilities.invokeLater(() ->
			{
				if (!navButton.isSelected())
				{
					navButton.getOnSelect().run();
				}
			});

			panel.lookupMonster(targetNpc.getId());
		}
	}

	@Provides
	ExamineMonsterConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ExamineMonsterConfig.class);
	}
}
