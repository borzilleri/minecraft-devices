package com.asylumsw.bukkit.device;

import java.util.Calendar;
import java.util.HashMap;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author jonathan
 */
public class DivingHelmet {
	private final static int REED_USAGE_COOLDOWN = 10;
	private final static ItemStack HELMET_COST = new ItemStack(Material.SUGAR_CANE, 1);
	private static HashMap<String,Long> helmetUsage;


	public static EntityDamageByBlockEvent handleEvent(EntityDamageByBlockEvent event) {
		Device.server.broadcastMessage("handling entitydamageevent");
		// If our event is already cancelled, we're not going to un-cancel it
		// so just return.
		if( event.isCancelled() ) return event;
		// We're not a player, just return.
		if( Player.class != event.getEntity().getClass() ) return event;

		Player player = (Player)event.getEntity();

		player.sendMessage("player damage!");

		// Player is NOT wearing an iron helmet
		if( null == player.getInventory().getHelmet() ||
				Material.IRON_HELMET != player.getInventory().getHelmet().getType() ) {
			return event;
		}
		player.sendMessage("has iron helm");
		// Player does not have any sugar cane.
		if( !player.getInventory().contains(Material.SUGAR_CANE) ) return event;
		player.sendMessage("has reeds");

		if( helmetUsage.containsKey(player.getName()) &&
				(Calendar.getInstance().getTimeInMillis() - (REED_USAGE_COOLDOWN*1000)) <
							helmetUsage.get(player.getName()) ) {
			player.sendMessage("diving helmet in use!");
			event.setCancelled(true);
			return event;
		}

		player.sendMessage("removing reads");
		HashMap<Integer, ItemStack> unremoved = player.getInventory().removeItem(HELMET_COST);
		if( !unremoved.isEmpty() ) return event;

		player.sendMessage("using helmet!");
		helmetUsage.put(player.getName(), Calendar.getInstance().getTimeInMillis());
		event.setCancelled(true);
		
		return event;
	}

}
