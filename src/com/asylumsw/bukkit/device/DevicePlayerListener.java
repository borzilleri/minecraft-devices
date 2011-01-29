package com.asylumsw.bukkit.device;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 *
 * @author jonathan
 */
public class DevicePlayerListener extends PlayerListener {
	Device plugin;

	public DevicePlayerListener(Device instance) {
		plugin = instance;
	}

	@Override
	public void onPlayerCommand(PlayerChatEvent event) {
		String[] split = event.getMessage().split(" ");
		Player player = event.getPlayer();

		if (split[0].equalsIgnoreCase("/gps")) {
			event.setCancelled(true);
		}
	}
	
}
