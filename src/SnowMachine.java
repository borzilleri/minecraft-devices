/**
 *
 * @author jonathan
 */
/*
public class SnowMachine extends Mod {
	public static final int SNOW_MACHINE_MAX_HEIGHT = 3;

	public boolean parseCommand(Player player, String[] tokens) {
		String command = tokens[0];

		if( command.equalsIgnoreCase("!snow") ) {
			makeSnow(player);
			return true;
		}
		else if( command.equalsIgnoreCase("!ice") ) {
			makeIce(player);
			return true;
		}
		else if( command.equalsIgnoreCase("!snowball") ) {
			makeSnowBall(player);
			return true;
		}
		return false;
	}

	protected void makeSnowBall(Player player) {
		if( 0 >= player.getItemCount(EntityType.WATER_BUCKET) ) {
			player.sendChat("ERROR: Snowballs require a bucket of water.", Color.Red);
			return;
		}
		if( 0 >= player.getItemCount(EntityType.REDSTONE) ) {
			player.sendChat("ERROR: Snowballs require redstone.", Color.Red);
			return;
		}
		if( 1 > player.getItemCount(BlockType.AIR) ) {
			player.sendChat("ERROR: Snowball requires a free inventory slot", Color.Red);
			return;
		}

		player.removeItem(EntityType.WATER_BUCKET, 1);
		player.removeItem(EntityType.REDSTONE, 1);
		player.addToInventory(EntityType.BUCKET, 1);
		player.addToInventory(EntityType.SNOWBALL, 1);
	}

	protected void makeIce(Player player) {

		if( 0 >= player.getItemCount(EntityType.SNOWBALL) ) {
			player.sendChat("ERROR: Icemaker requires Snowballs.", Color.Red);
			return;
		}
		if( 0 >= player.getItemCount(EntityType.REDSTONE) ) {
			player.sendChat("ERROR: Icemaker requires Redstone.", Color.Red);
			return;
		}
		if( 0 >= player.getItemCount(EntityType.WATER_BUCKET) ) {
			player.sendChat("ERROR: Icemaker requires bucket of water.", Color.Red);
			return;
		}
		if( 27 >= player.getInventory().length ) {
			player.sendChat("ERROR: Icemaker requires a free inventory slot", Color.Red);
			return;
		}


		player.removeItem(EntityType.WATER_BUCKET, 1);
		player.removeItem(EntityType.REDSTONE, 1);
		player.removeItem(EntityType.SNOWBALL, 1);
		player.addToInventory(EntityType.BUCKET, 1);
		player.addToInventory(BlockType.ICE, 1);
	}
	
	protected void makeSnow(Player player) {
		boolean hasSnow = player.removeItem(BlockType.SNOW_BLOCK, 1);

		if( !hasSnow ) {
			player.sendChat("ERROR: Snow Block required to make snow.", Color.Red);
			return;
		}

		for(int x = -1; x <= 1; x++ ) {
			for( int z = -1; z <= 1; z++ ) {
				if( 0 != x*z ) continue;
				setBlockToSnow(fixLocation(player.getLocation().addToX(x).addToZ(z)));
			}
		}
	}

	protected boolean setBlockToIce(Location location) {
		if( World.getBlock(location).getBlockType() == BlockType.STATIONARY_WATER ) {
			World.setBlock(location, BlockType.ICE);
		}
		return false;
	}

	protected boolean setBlockToSnow(Location location) {
		Location thisLoc;

		for(int i = (-1*SNOW_MACHINE_MAX_HEIGHT); i < SNOW_MACHINE_MAX_HEIGHT+1; i++) {
			thisLoc = location.addToY(i);
			if( World.getBlock(thisLoc).getBlockType() == BlockType.AIR ) {
				if( isValidSnowBase(World.getBlock(thisLoc.addToY(-1)).getBlockType()) ) {
					World.setBlock(thisLoc, BlockType.SNOW);
					return true;
				}
			}
		}
		
		return false;
	}

	protected boolean isValidSnowBase(BlockType blockType) {
		switch(blockType) {
			case AIR:
			case BROWN_MUSHROOM:
			case COBBLESTONE_STAIRS:
			case CROPS:
			case FENCE:
			case FIRE:
			case IRON_DOOR:
			case LADDER:
			case LAVA:
			case LEVER:
			case MINECART_TRACKS:
			case MOB_SPAWNER:
			case PORTAL:
			case REDSTONE_TORCH_OFF:
			case REDSTONE_TORCH_ON:
			case RED_FLOWER:
			case RED_MUSHROOM:
			case SAPLING:
			case SIGN:
			case SNOW:
			case STATIONARY_LAVA:
			case STATIONARY_WATER:
			case STEP:
			case STONE_BUTTON:
			case STONE_PRESSURE_PLATE:
			case TORCH:
			case WALL_SIGN:
			case WATER:
			case WOODEN_DOOR:
			case WOODEN_PRESSURE_PLATE:
			case WOODEN_STAIRS:
			case YELLOW_FLOWER:
				return false;
			default:
				return true;
		}
	}

	protected Location fixLocation(Location loc) {
		if( 0 > loc.getX() ) loc = loc.addToX(-1);
		if( 0 > loc.getZ() ) loc = loc.addToZ(-1);
		return loc;
	}

	

	@Override
	public boolean onPlayerChat(Player player, String command) {
		String[] tokens = command.split(" ");
		return parseCommand(player, tokens);
	}
	@Override
	public boolean onPlayerCommand(Player player, String[] tokens) {
		return parseCommand(player, tokens);
	}
	@Override
	public String toString() {
		return "!snow, !ice, !snowball";
	}

}
*/