package affects;

import engine.GameState;
import engine.Minion;
import engine.Player;

public class DamageEnemyLifeAffect implements Affect{
	
	int damage;
	Minion owner;
	
	public DamageEnemyLifeAffect(Minion ownerr, int damagee){
		damage = damagee;
		owner = ownerr;
	}
	public void applyAffect() {
		owner.effect.activationsThisTurn+=1;
		Player enemy = GameState.getGameState().getEnemy(owner.owner);
		enemy.damagePlayer(damage, owner);
	}
	@Override
	public String getDescription() {
		
		return "Deal " + damage + " damage to the enemy Player";
	}

}
