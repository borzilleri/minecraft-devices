/**
 *
 * @author jonathan
 */
/*
public class DivingHelmet extends Mod {
	@Override
	public void activate() {
		Server.log("Diving Helmet Activated");
	}

	@Override
	public boolean onEntityDamage(DamageType type, Entity attacker, Entity defender, int damage) {
		if( !defender.isPlayer() ) return false;
		try {
			Player player = defender.toPlayer();
			if( type != DamageType.WATER ) return false;

			player.sendChat(player.getArmorSlot(ArmorSlot.HEAD).getEntityType().getName());

			if( player.getArmorSlot(ArmorSlot.HEAD).getEntityType() == EntityType.IRON_HELMET ) {
				player.sendChat("found iron helm");
				return player.removeItem(EntityType.REED, 1);
			}

		}
		catch( TypeMissmatch e ) {
			return false;
		}

		return false;
	}

}
*/