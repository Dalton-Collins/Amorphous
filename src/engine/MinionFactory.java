package engine;

import affects.DamageEnemyLifeAffect;
import affects.IncreaseThisAttackAffect;
import affects.SelectAndDestroyMinionAffect;
import triggers.OnSummonTrigger;
import triggers.WhenDamagedTrigger;

//this is where individual minions will be created
// each function will create a minion
public class MinionFactory {
	
	GameState gs;
	
	public MinionFactory(GameState gss){
		gs = gss;
		
	}
	
	public Minion makeRat(Player p, int idd){
		Minion m = new Minion(gs, p);
		
		m.id = idd;
		m.name = "Rat";
		m.atk = 20;
		m.health = 30;
		m.type = "Beast";
		
		OnSummonTrigger onsummon = new OnSummonTrigger();
		DamageEnemyLifeAffect del = new DamageEnemyLifeAffect(gs, m, 15);
		Effect E = new Effect(m, onsummon, del, 1);
		m.effect = E;
		return m;
	}
	
	public Minion makeBomber(Player p, int idd){
		Minion m = new Minion(gs, p);
		
		m.id = idd;
		m.name = "Bomber";
		m.atk = 10;
		m.health = 10;
		m.type = "Machine";
		
		OnSummonTrigger onsummon = new OnSummonTrigger();
		SelectAndDestroyMinionAffect sadma = new SelectAndDestroyMinionAffect(gs, m);
		Effect E = new Effect(m, onsummon, sadma, 1);
		m.effect = E;
		return m;
	}
	
	public Minion makeBerzerker(Player p, int idd){
		Minion m = new Minion(gs, p);
		
		m.id = idd;
		m.name = "Berzerker";
		m.atk = 20;
		m.health = 50;
		m.type = "Humanoid";
		
		WhenDamagedTrigger wdt = new WhenDamagedTrigger();
		IncreaseThisAttackAffect itaa = new IncreaseThisAttackAffect(m, 20);
		Effect E = new Effect(m, wdt, itaa, 2);
		m.effect = E;
		return m;
	}
}
