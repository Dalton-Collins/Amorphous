package engine;

import java.util.ArrayList;
import java.util.Random;

import triggers.DeclareAttackTrigger;
import triggers.OnSummonTrigger;
import triggers.Trigger;
import triggers.WhenAttackedTrigger;
import triggers.WhenDamagedTrigger;
import triggers.WhenDestroyedTrigger;
import affects.Affect;
import affects.DamageEnemyLifeAffect;
import affects.DrawCardAffect;
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
		
		int targetWeight = randomWeight();
		Affect af = randomAffect(m);
		Trigger tr = randomTrigger();
		Effect e = new Effect(m, tr, af, 1);
		m.effect = e;
		m.type = randomType();
		m.atk = randomAttack();
		m.baseAtk = m.atk;
		m.health = randomHealth();
		m.maxHealth = m.health;
		m.cost = calculateCost();
		m.name = randomName(m);
		return m;
	}
	
	public Deck makeRandomDeck(Player p){
		Deck deck = new Deck();
		for(int i = 0; i < 30; i++){
			Minion m = makeRandomMinion(p);
			deck.cards.add(m);
		}
		
		return deck;
	}
	
	Affect randomAffect(Minion m){
		Random rand = new Random();
		int i = rand.nextInt(3);
		if(i == 0){
			int damage = (rand.nextInt(7)+2)*5;
			weight+=damage*2;//average weight of 50
			DamageEnemyLifeAffect dela = new DamageEnemyLifeAffect(m, damage);
			return dela;
		}else if(i == 1){
			DrawCardAffect dca = new DrawCardAffect(m);
			weight+=40;
			return dca;
		}else if(i == 2){
			int atk = (rand.nextInt(8)+1)*5;//range 5 - 40
			weight+=atk*2;//average weight of ~40
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
			return 100;
		}else if(roll < 90){
			return 120;
		}else if(roll < 100){
			return 140;
		}
		return -1;
	}
	
	String randomType(){
		Random rand = new Random();
		int i = rand.nextInt(3);
		if(i == 0){
			return "Humanoid";
		}else if(i == 1){
			return "Beast";
		}else if(i == 2){
			return "Machine";
		}
		return null;
	}
	
	int randomHealth(){
		Random rand = new Random();
		int i = rand.nextInt(20);
		int health = (i+1)*5;
		weight+=health/2;//average weight of 25
		return health;
	}
	
	int randomAttack(){
		Random rand = new Random();
		int i = rand.nextInt(20);
		int attack = (i+1)*5;
		weight+=attack/2;//average weight of 25
		return attack;
	}
	
	int calculateCost(){
		int cost = weight/2;
		return cost;
	}
	
	String randomName(Minion m){
		String name = "";
		name+= m.cost + m.atk + m.health + m.effect.trigger.getDescription().substring(0, 3)
				+ m.effect.affect.getDescription().substring(0, 3);
		return name;
	}
}

//design work
/*
 which part of the minion is decided next should be randomized
 IE atk then effect then health or effect then cost then atk etc
 minions will have rarity decided first
 where common cards can have a weight of 80
 rare cards a weight of 100
 epic cards a weight of 120
 legendary cards a weight of 140
 
 40% common
 30% rare
 20% epic
 10% legenday
 
 
*/