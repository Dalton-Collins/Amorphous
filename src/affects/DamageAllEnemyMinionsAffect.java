package affects;

import java.util.Iterator;

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
		owner.effect.activationsThisTurn+=1;
		Player enemy = GameState.getGameState().getEnemy(owner.owner);
		for(Minion m : enemy.minions){
			DamageMinionActionAffect dmaa = new DamageMinionActionAffect(m, owner, damage);
			GameState.getGameState().affectStack.addAction(dmaa);
		}
		GameState.getGameState().affectStack.processStack();
	}
	@Override
	public String getDescription() {
		
		return "Deal " + damage + " damage to all enemy Minions";
	}
}
