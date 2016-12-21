package affects;

import engine.Affect;
import engine.GameState;
import engine.Player;

public class DamageEnemyLife implements Affect{
	
	public void applyAffect(GameState gs, int damage, Player owner) {
		Player enemy = gs.getEnemy(owner);
		enemy.life-=damage;
	}

}
