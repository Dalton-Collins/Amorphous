package affects;

import engine.GameState;
import engine.Minion;
import engine.Player;

public class DamageEnemyLifeAffect implements Affect{
	
	GameState gs;
	int damage;
	Minion owner;
	
	public DamageEnemyLifeAffect(GameState gss, Minion ownerr, int damagee){
		gs = gss;
		damage = damagee;
		owner = ownerr;
	}
	public void applyAffect() {
		owner.effect.activationsThisTurn+=1;
		Player enemy = gs.getEnemy(owner.owner);
		enemy.damagePlayer(damage, owner);
	}
	@Override
	public String getDescription() {
		
		return "Deal " + damage + " damage to the enemy Player";
	}

}
