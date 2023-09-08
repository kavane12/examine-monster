package com.examine_monster;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
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
public class ExamineMonsterPlugin extends Plugin {
	@Inject
	private Client client;

	@Inject
	private ExamineMonsterConfig config;

	@Override
	protected void startUp() throws Exception {
		log.info("Example started!");
	}

	@Override
	protected void shutDown() throws Exception {
		log.info("Example stopped!");
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event) {
		MenuAction menuAction = event.getMenuAction();
		MenuEntry menuEntry = event.getMenuEntry();
		if (menuAction == MenuAction.EXAMINE_NPC && menuEntry.getNpc() != null) {
			NPC targetNpc = menuEntry.getNpc();
			if (targetNpc != null) {
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Examined" + targetNpc.getName(), null);
			}
		}

	}

	@Subscribe

	@Provides
	ExamineMonsterConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(ExamineMonsterConfig.class);
	}
}
