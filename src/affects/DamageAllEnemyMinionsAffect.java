package affects;

import engine.GameState;
import engine.Minion;
import engine.Player;

public class DamageAllEnemyMinionsAffect implements Affect{
	int damage;
	Minion owner;
	
	public DamageAllEnemyMinionsAffect(Minion ownerr, int damagee){
		damage = damagee;
		owner = ownerr;
	}
	public void applyAffect() {
		Player enemy = GameState.getGameState().getEnemy(owner.owner);
		for(Minion m : enemy.minions){
			m.damageMinion(damage, owner);
		}
		GameState.getGameState().affectStack.processStack();
	}
	@Override
	public String getDescription() {
		
		return "Deal " + damage + " damage to all enemy Minions";
	}
}
