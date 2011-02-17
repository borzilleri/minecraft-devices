package com.asylumsw.bukkit.device;

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
	public void onEntityDamage(EntityDamageEvent event) {
		if( event.isCancelled() ) return;
		
		event = DivingHelmet.handleEvent(event);
	}

}
