/**
 *
 * @author jonathan
 */
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
		return false;
	}

	protected void makeIce(Player player) {
		boolean hasSnowball = player.removeItem(EntityTypeEnum.SNOWBALL, 1);

		if( !hasSnowball ) {
			player.sendChat("ERROR: Icemaker requires snowballs.");
			return;
		}

		for( int x = -1; x <= 1; x++ ) {
			for( int y = -1; y <= 1; y++ ) {
				for( int z = -1; z <= 1; z++ ) {
					setBlockToIce(fixLocation(player.getLocation().addToX(x).addToY(y).addToZ(z)));
				}
			}
		}
	}
	protected void makeSnow(Player player) {
		boolean hasSnow = player.removeItem(BlockTypeEnum.SNOW_BLOCK, 1);

		if( !hasSnow ) {
			player.sendChat("ERROR: Snow Block required to make snow.", ColorEnum.Red);
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
		if( location.getBlockAt().getEnum() == BlockTypeEnum.STATIONARY_WATER ) {
			location.setBlockAt(BlockTypeEnum.ICE);
		}
		return false;
	}

	protected boolean setBlockToSnow(Location location) {
		Location thisLoc;

		for(int i = (-1*SNOW_MACHINE_MAX_HEIGHT); i < SNOW_MACHINE_MAX_HEIGHT+1; i++) {
			thisLoc = location.addToY(i);
			if( thisLoc.getBlockAt().getEnum() == BlockTypeEnum.AIR ) {
				if( isValidSnowBase(thisLoc.addToY(-1).getBlockAt().getEnum()) ) {
					thisLoc.setBlockAt(BlockTypeEnum.SNOW);
					return true;
				}
			}
		}
		
		return false;
	}

	protected boolean isValidSnowBase(BlockTypeEnum blockType) {
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
		return "!snow, !ice";
	}

}
