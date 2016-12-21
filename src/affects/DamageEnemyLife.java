package affects;

import engine.Affect;
import engine.GameState;
import engine.Minion;
import engine.Player;

public class DamageEnemyLife implements Affect{
	
	int damage;
	Minion owner;
	
	public DamageEnemyLife(Minion ownerr, int damagee){
		damage = damagee;
		owner = ownerr;
	}
	public void applyAffect() {
		Player enemy = GameState.getGameState().getEnemy(owner.owner);
		enemy.life-=damage;
	}

}
