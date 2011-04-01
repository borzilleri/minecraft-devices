package com.asylumsw.bukkit.device;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;

/**
 *
 * @author jonathan
 */
public class Device extends JavaPlugin {
	private final DeviceEntityListener entityListener = new DeviceEntityListener(this);
	public static Server server;

	@Override
	public void onEnable() {
		server = this.getServer();

		// Register our events
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Priority.Low, this);

		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
	}

	@Override
	public void onDisable() {
		System.out.println("Devices Disabled.");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if( cmd.getName().equalsIgnoreCase("snow") ) {
			SnowBlower.handleCommand((Player)sender);
			return true;
		}
		else if( cmd.getName().equalsIgnoreCase("ice") ) {
			IceMachine.handleCommand((Player)sender);
			return true;
		}
		return false;
	}
}
