package com.asylumsw.bukkit.device;

import java.io.File;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.Server;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;

/**
 *
 * @author jonathan
 */
public class Device extends JavaPlugin {
	private final DevicePlayerListener playerListener = new DevicePlayerListener(this);
	private final DeviceEntityListener entityListener = new DeviceEntityListener(this);
	public static Server server;

	public Device(PluginLoader pluginLoader, Server instance, PluginDescriptionFile desc, File folder, File plugin, ClassLoader cLoader) {
		super(pluginLoader, instance, desc, folder, plugin, cLoader);
		server = instance;
	}

	@Override
	public void onEnable() {
		// Register our events
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_COMMAND, playerListener, Priority.Low, this);
		pm.registerEvent(Event.Type.ENTITY_DAMAGED, entityListener, Priority.Low, this);

		// EXAMPLE: Custom code, here we just output some info so we can check all is well
		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
	}

	@Override
	public void onDisable() {
		// EXAMPLE: Custom code, here we just output some info so we can check all is well
		System.out.println("Devices Disabled.");
	}
}
