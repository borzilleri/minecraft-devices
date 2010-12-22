/**
 *
 * @author jonathan
 */
public class DivingHelmet extends Mod {
	@Override
	public void activate() {
		Server.log("Diving Helmet Activated");
	}

	@Override
	public boolean onEntityDamage(DamageType type, Entity attacker, Entity defender, int damage) {
		Server.log(" took " + damage + " " + type.toString() + " damage");

		if( !defender.isPlayer() ) return false;
		Player player = defender.getPlayer();

		player.sendChat("took damage: "+type.toString() + ":" + damage);

		if( 0 != type.compareTo(DamageType.WATER) ) return false;		

		for( Item a: defender.getPlayer().getArmor() ) {
			if( a.getId() == EntityType.IRON_HELMET ) {
				player.sendChat("found Iron Helm");
				return defender.getPlayer().removeItem(EntityTypeEnum.REED, 1);
			}
		}
		return false;
	}

}
