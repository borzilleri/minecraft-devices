package com.asylumsw.bukkit.device;

import java.util.Calendar;
import java.util.HashMap;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author jonathan
 */
public class DivingHelmet {
	private final static int REED_USAGE_COOLDOWN = 10;
	private final static ItemStack HELMET_COST = new ItemStack(Material.SUGAR_CANE, 1);
	private static HashMap<String,Long> helmetUsage = new HashMap<String, Long>();


	public static EntityDamageEvent handleEvent(EntityDamageEvent event) {
		if( event.isCancelled() ) return event;
		if( !(event.getEntity() instanceof Player) ) return event;
		Player player = (Player)event.getEntity();

		// Player is NOT wearing an iron helmet
		if( null == player.getInventory().getHelmet() ||
				Material.PUMPKIN != player.getInventory().getHelmet().getType() )
			return event;

		// The player has "used" their diving helmet successfully within the
		// within the cooldown period, so prevent the damage and return;
		if( helmetUsage.containsKey(player.getName()) &&
				(Calendar.getInstance().getTimeInMillis() - (REED_USAGE_COOLDOWN*1000)) <
							helmetUsage.get(player.getName()) ) {
			event.setCancelled(true);
			return event;
		}

		// Player does not have any sugar cane.
		if( !player.getInventory().contains(Material.SUGAR_CANE) ) return event;

		// We have reeds, so remove the cost of using the helmet.
		HashMap<Integer, ItemStack> unremoved = player.getInventory().removeItem(HELMET_COST);

		// If we unsuccessfully removed any of our cost, just return out.
		if( !unremoved.isEmpty() ) return event;

		// Finally, make an entry in the cooldown map, and cancel the event.
		helmetUsage.put(player.getName(), Calendar.getInstance().getTimeInMillis());
		event.setCancelled(true);
		
		return event;
	}

}