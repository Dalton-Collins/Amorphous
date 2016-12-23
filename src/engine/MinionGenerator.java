package engine;

import java.util.ArrayList;
import java.util.Random;

import triggers.DeclareAttackTrigger;
import triggers.OnSummonTrigger;
import triggers.WhenAttackedTrigger;
import triggers.WhenDamagedTrigger;
import triggers.WhenDestroyedTrigger;
import affects.DamageEnemyLifeAffect;
import affects.IncreaseThisAttackAffect;
import affects.SelectAndDestroyMinionAffect;

//this class handles the creation of new cards
public class MinionGenerator {
	
	int weight;
	ArrayList<Affect> affectList;
	ArrayList<Trigger> triggerList;
	
	MinionGenerator(){
	}
	
	public Minion makeRandomMinion(Player p){
		weight = 0;
		Minion m = new Minion(p);
		
		Affect af = randomAffect(m);
		Trigger tr = randomTrigger();
		Effect e = new Effect(m, tr, af, 1);
		m.effect = e;
		return m;
	}
	
	
	
	Affect randomAffect(Minion m){
		Random rand = new Random();
		int i = rand.nextInt(3);
		if(i == 0){
			int damage = rand.nextInt(31)+10;
			weight+=damage*2;//average weight of 50
			DamageEnemyLifeAffect dela = new DamageEnemyLifeAffect(m, damage);
			return dela;
		}else if(i == 1){
			SelectAndDestroyMinionAffect dma = new SelectAndDestroyMinionAffect(m);
			weight+=60;//set weight of 60
			return dma;
		}else if(i == 2){
			int atk = rand.nextInt(36)+5;
			weight+=atk*2;//average weight of 40
			IncreaseThisAttackAffect itaa = new IncreaseThisAttackAffect(m, atk);
			return itaa;
		}
		System.out.println("failed to get random affect");
		return null;
	}
	
	Trigger randomTrigger(){
		Random rand = new Random();
		int i = rand.nextInt(5);
		if(i == 0){
			DeclareAttackTrigger dat = new DeclareAttackTrigger();
			weight *=.8;
			return dat;
		}else if(i == 1){
			OnSummonTrigger ost = new OnSummonTrigger();
			weight*=1.6;
			return ost;
		}else if(i == 2){
			WhenAttackedTrigger wat = new WhenAttackedTrigger();
			weight *=1.2;
			return wat;
		}else if(i == 3){
			WhenDamagedTrigger wdt = new WhenDamagedTrigger();
			weight*=1.4;
			return wdt;
		}else if(i == 4){
			WhenDestroyedTrigger wdt = new WhenDestroyedTrigger();
			weight*=1.2;
			return wdt;
		}
		return null;
	}
	
	int randomWeight(){
		Random rand = new Random();
		int roll = rand.nextInt(100);
		if(roll < 40){
			return 80;
		}else if(roll < 70){
			return 110;
		}else if(roll < 90){
			return 140;
		}else if(roll < 99){
			return 170;
		}else if(roll >=99){
			return 200;
		}
		return -1;
	}
	
	String randomType(){
		Random rand = new Random();
		int i = rand.nextInt(3);
		return null;
	}
}

//design work
/*
 which part of the minion is decided next should be randomized
 IE atk then effect then health or effect then cost then atk etc
 minions will have rarity decided first
 where common cards can have a weight of 80
 rare cards a weight of 110
 epic cards a weight of 140
 legendary cards a weight of 170
 god cards a weight of 200
 
 45% common
 30% rare
 20% epic
 4% legenday
 1% god
 
 
*/