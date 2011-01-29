package com.asylumsw.bukkit.device;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

/**
 *
 * @author jonathan
 */
public class DeviceEntityListener extends EntityListener {
	private Device plugin;

	public DeviceEntityListener(Device instance) {
		plugin = instance;
	}

	@Override
	public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
		Device.server.broadcastMessage("on damage from block");
		event = DivingHelmet.handleEvent(event);
	}

	@Override
	public void onEntityDamage(EntityDamageEvent event) {
		Device.server.broadcastMessage("on damage");
	}

}
