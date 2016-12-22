package engine;

import affects.DamageEnemyLife;
import affects.IncreaseThisAttackAffect;
import affects.SelectAndDestroyMinionAffect;
import triggers.OnSummonTrigger;
import triggers.WhenDamagedTrigger;

//this is where individual minions will be created
// each function will create a minion
public class MinionFactory {
	
	public Minion makeRat(Player p, int idd){
		Minion m = new Minion( p);
		
		m.id = idd;
		m.name = "Rat";
		m.cost = 40;
		m.atk = 2;
		m.health = 2;
		m.type = "Beast";
		
		OnSummonTrigger onsummon = new OnSummonTrigger();
		DamageEnemyLife del = new DamageEnemyLife(m, 5);
		Effect E = new Effect(m, onsummon, del);
		m.effect = E;
		return m;
	}
	
	public Minion makeBomber(Player p, int idd){
		Minion m = new Minion(p);
		
		m.id = idd;
		m.name = "Bomber";
		m.cost = 70;
		m.atk = 1;
		m.health = 1;
		m.type = "Machine";
		
		OnSummonTrigger onsummon = new OnSummonTrigger();
		SelectAndDestroyMinionAffect sadma = new SelectAndDestroyMinionAffect(m);
		Effect E = new Effect(m, onsummon, sadma);
		m.effect = E;
		return m;
	}
	
	public Minion makeBerzerker(Player p, int idd){
		Minion m = new Minion(p);
		
		m.id = idd;
		m.name = "Berzerker";
		m.cost = 40;
		m.atk = 20;
		m.health = 60;
		m.type = "Humanoid";
		
		WhenDamagedTrigger wdt = new WhenDamagedTrigger();
		IncreaseThisAttackAffect itaa = new IncreaseThisAttackAffect(m, 20);
		Effect E = new Effect(m, wdt, itaa);
		m.effect = E;
		return m;
	}
}
