package engine;

import affects.DamageEnemyLife;
import triggers.OnSummonTrigger;

//this is where individual minions will be created
// each function will create a minion
public class MinionFactory {
	
	public Minion makeRat(GameState gs, Player p){
		Minion m = new Minion(gs, p);
		
		m.id = 2;
		m.name = "Rat";
		m.cost = 40;
		m.atk = 2;
		m.health = 2;
		m.type = "beast";
		
		OnSummonTrigger onsummon = new OnSummonTrigger();
		DamageEnemyLife del = new DamageEnemyLife();
		Effect E = new Effect(onsummon, del);
		m.effect = E;
		return m;
	}
}
