package com.examine_monster;

import com.google.inject.Provides;

import java.util.Arrays;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.MenuEntry;
import net.runelite.api.NPC;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(name = "TODO Examine Monster")
public class ExamineMonsterPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ExamineMonsterConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Example started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event)
	{
		MenuEntry[] rightClickMenuEntries = client.getMenuEntries();
		MenuAction menuAction = event.getMenuAction();
		MenuEntry clickedMenuEntry = event.getMenuEntry();

		NPC targetNpc;

		// Examined an NPC with combat level > 0 that is attackable.
		if (menuAction == MenuAction.EXAMINE_NPC
				&& (targetNpc = clickedMenuEntry.getNpc()) != null
				&& targetNpc.getCombatLevel() > 0
				&& Arrays.asList(rightClickMenuEntries).stream()
						.anyMatch(menuEntry -> menuEntry.getOption().equals("Attack")))
		{

			log.info(targetNpc.getId() + ": " + targetNpc.getName() + " (" + targetNpc.getCombatLevel() + ")");

		}

	}

	@Provides
	ExamineMonsterConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ExamineMonsterConfig.class);
	}
}
