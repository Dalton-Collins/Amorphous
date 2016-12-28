package affects;

import engine.GameState;
import engine.Minion;
import engine.Player;

public class DamageAllEnemyMinionsAffect implements Affect{
	int damage;
	Minion owner;
	GameState gs;
	
	public DamageAllEnemyMinionsAffect(GameState gss, Minion ownerr, int damagee){
		gs = gss;
		damage = damagee;
		owner = ownerr;
	}
	public void applyAffect() {
		owner.effect.activationsThisTurn+=1;
		Player enemy = gs.getEnemy(owner.owner);
		for(Minion m : enemy.minions){
			DamageMinionActionAffect dmaa = new DamageMinionActionAffect(m, owner, damage);
			gs.affectStack.addAction(dmaa);
		}
		gs.affectStack.processStack();
	}
	@Override
	public String getDescription() {
		
		return "Deal " + damage + " damage to all enemy Minions";
	}
}
