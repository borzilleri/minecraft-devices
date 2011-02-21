package com.asylumsw.bukkit.device;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author jonathan
 */
public class SnowBlower {
	private final static int MAX_SNOW_HEIGHT = 3;
	private final static ItemStack[] COST = new ItemStack[] {
		new ItemStack(Material.REDSTONE, 4)
	};
	private final static ItemStack[] COST_INVERSE = new ItemStack[] {
	};

	public static void handleCommand(Player player) {
		// Make sure the player is standing on a snow block
		if (Material.SNOW != player.getLocation().getBlock().getType()) {
			player.sendMessage(ChatColor.RED + "Error: Must be standing on snow for the"
							+ " snowblower to work.");
			return;
		}

		if( !checkCost(player, false) ) return;
		
		// Turn the n/e/w/s blocks into snow.
		int newSnow = 0;
		Location loc;
		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				if (0 != x * z || 0 == x + z) {
					continue;
				}
				loc = player.getLocation().clone();
				loc.setX(player.getLocation().getBlockX() + x);
				loc.setZ(player.getLocation().getBlockZ() + z);
				newSnow += setBlockToSnow(loc) ? 1 : 0;
			}
		}
		
		if( 0 < newSnow ) {
			checkCost(player, true);
			player.sendMessage(ChatColor.BLUE + "SnowBlower created " + newSnow + " new snow!");
		}
		else {
			player.sendMessage(ChatColor.DARK_GRAY+"SnowBlower could not create any new snow.");
		}
	}

	private static boolean checkCost(Player player, boolean alsoRemove) {
		for( ItemStack item : COST ) {
			if( !player.getInventory().contains(item.getType(), item.getAmount()) ) {
				player.sendMessage(String.format("%sERROR: The SnowBlower requires %s %s.",
								ChatColor.RED, item.getAmount(), item.getType().toString() ) );
				return false;
			}
		}

		if( alsoRemove ) {
			player.getInventory().removeItem(COST);
			player.getInventory().addItem(COST_INVERSE);
		}
		return true;
	}


	private static boolean setBlockToSnow(Location loc) {
		Location baseLoc, thisLoc;
		thisLoc = loc.clone();
		for(int i = (-1*MAX_SNOW_HEIGHT); i < MAX_SNOW_HEIGHT+1; i++) {
			thisLoc = loc.clone();
			thisLoc.setY(loc.getBlockY()+i);
			if( thisLoc.getBlock().getType() != Material.AIR ) continue;
			
			baseLoc = thisLoc.clone();
			baseLoc.setY(thisLoc.getBlockY()-1);
			if( !isValidSnowBase(baseLoc.getBlock().getType()) ) continue;

			thisLoc.getBlock().setType(Material.SNOW);
			return true;
		}
		return false;
	}

	protected static boolean isValidSnowBase(Material mat) {
		switch (mat) {
			case AIR:
			case BROWN_MUSHROOM:
			case CAKE_BLOCK:
			case COBBLESTONE_STAIRS:
			case CROPS:
			case FENCE:
			case FIRE:
			case IRON_DOOR:
			case LADDER:
			case LAVA:
			case LEVER:
			case PORTAL:
			case RAILS:
			case REDSTONE_TORCH_OFF:
			case REDSTONE_TORCH_ON:
			case RED_MUSHROOM:
			case RED_ROSE:
			case SAPLING:
			case SIGN:
			case SNOW:
			case STATIONARY_LAVA:
			case STATIONARY_WATER:
			case STEP:
			case STONE_BUTTON:
			case TORCH:
			case WALL_SIGN:
			case WATER:
			case WOODEN_DOOR:
			case YELLOW_FLOWER:
				return false;
			default:
				return true;
		}
	}
}
