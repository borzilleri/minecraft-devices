package com.asylumsw.bukkit.device;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author jonathan
 */
public class IceMachine {
	public final static int RANGE = 5;
	public final static ItemStack[] COST = new ItemStack[] {
		new ItemStack(Material.REDSTONE, 1),
		new ItemStack(Material.WATER_BUCKET, 1)
	};
	public final static ItemStack[] COST_INVERSE = new ItemStack[] {
		new ItemStack(Material.BUCKET, 1)
	};

	public static void handleCommand(Player player) {
		Block targetBlock = player.getTargetBlock(null, RANGE);

		if( Material.SNOW_BLOCK != targetBlock.getType() ) {
			player.sendMessage(ChatColor.RED+"ERROR: The Ice Machine can only turn"
							+" snow into ice.");
			return;
		}

		checkCost(player, true);
		
		targetBlock.setType(Material.ICE);
	}

	private static boolean checkCost(Player player, boolean alsoRemove) {
		for( ItemStack item : COST ) {
			if( !player.getInventory().contains(item.getType(), item.getAmount()) ) {
				player.sendMessage(String.format("%sERROR: The IceMachine requires %s %s.",
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
}
