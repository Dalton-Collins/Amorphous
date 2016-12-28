package engine;

import java.util.ArrayList;
import java.util.Random;

import triggers.DeclareAttackTrigger;
import triggers.OnSummonTrigger;
import triggers.Trigger;
import triggers.WhenAttackedTrigger;
import triggers.WhenDamagedTrigger;
import triggers.WhenDestroyedTrigger;
import triggers.WhenEnemyDrawsCards;
import affects.Affect;
import affects.DamageAllEnemyMinionsAffect;
import affects.DamageEnemyLifeAffect;
import affects.DrawCardAffect;
import affects.IncreaseThisAttackAffect;
import affects.SelectAndDestroyMinionAffect;

//this class handles the creation of new cards
public class MinionGenerator {
	
	int weight;
	ArrayList<Affect> affectList;
	ArrayList<Trigger> triggerList;
	GameState gs;
	
	MinionGenerator(GameState gss){
		gs = gss;
	}
	
	public Minion makeRandomMinion(Player p){
		//first try to make the minion randomly
		int targetWeight = randomWeight();
		
		weight = 0;
		Minion m = new Minion(gs, p);

		Affect af = randomAffect(m);
		Trigger tr = randomTrigger();
		Effect e = new Effect(m, tr, af, 1);
		m.effect = e;
		m.type = randomType();
		m.atk = randomAttack(targetWeight);
		m.baseAtk = m.atk;
		m.health = randomHealth(targetWeight);
		m.maxHealth = m.health;
		m.cost = calculateCost();
		m.name = randomName(m);
		
		while(weight > targetWeight){
			//if the weight is too high, try randomly again
			weight = 0;
			af = randomAffect(m);
			tr = randomTrigger();
			e = new Effect(m, tr, af, 1);
			m.effect = e;
			m.type = randomType();
			m.health = randomHealth(targetWeight);
			m.maxHealth = m.health;
			m.atk = randomAttack(targetWeight);
			m.baseAtk = m.atk;
			m.cost = calculateCost();
			m.name = randomName(m);
		}
		
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
		int i = rand.nextInt(5);
		if(i == 0){
			int damage = (rand.nextInt(7)+2)*5;
			weight+=damage*2;//average weight of 50
			DamageAllEnemyMinionsAffect daema = new DamageAllEnemyMinionsAffect(gs, m, damage);
			return daema;
		}else if(i == 1){
			int damage = (rand.nextInt(7)+2)*5;
			weight+=damage*2;//average weight of 50
			DamageEnemyLifeAffect dela = new DamageEnemyLifeAffect(gs, m, damage);
			return dela;
		}else if(i == 2){
			DrawCardAffect dca = new DrawCardAffect(m);
			weight+=40;
			return dca;
		}else if(i == 3){
			int atk = (rand.nextInt(8)+1)*5;//range 5 - 40
			weight+=atk*2;//average weight of ~40
			IncreaseThisAttackAffect itaa = new IncreaseThisAttackAffect(m, atk);
			return itaa;
		}else if(i == 4){
			SelectAndDestroyMinionAffect sadma = new SelectAndDestroyMinionAffect(gs, m);
			weight+=50;
			return sadma;
		}
		System.out.println("failed to get random affect");
		return null;
	}
	
	Trigger randomTrigger(){
		Random rand = new Random();
		int i = rand.nextInt(6);
		if(i == 0){
			DeclareAttackTrigger dat = new DeclareAttackTrigger();
			weight *=1;
			return dat;
		}else if(i == 1){
			OnSummonTrigger ost = new OnSummonTrigger();
			weight*=1.3;
			return ost;
		}else if(i == 2){
			WhenAttackedTrigger wat = new WhenAttackedTrigger();
			weight *=1.2;
			return wat;
		}else if(i == 3){
			WhenDamagedTrigger wdt = new WhenDamagedTrigger();
			weight*=1.3;
			return wdt;
		}else if(i == 4){
			WhenDestroyedTrigger wdt = new WhenDestroyedTrigger();
			weight*=1;
			return wdt;
		}else if(i == 5){
			int requiredCards = rand.nextInt(2) + 1;
			if(requiredCards == 1){
				weight*=1.3;
			}else{
				weight*=0.8;
			}
			WhenEnemyDrawsCards wedc = new WhenEnemyDrawsCards(requiredCards);
			return wedc;
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
	
	int randomHealth(int targetWeight){
		int max = (targetWeight - weight)/5;
		if(max < 2){
			weight = 1000;
			return -1;
		}
		Random rand = new Random();
		int i = rand.nextInt(max)+1;
		int health = (i)*5;
		weight+=health;
		return health;
	}
	
	int randomAttack(int targetWeight){
		int max = (targetWeight - weight)/5;
		if(max < 1){
			int attack = max*4;
			return attack;
		}
		Random rand = new Random();
		int i = rand.nextInt(max + 1);
		int attack = (i)*5;
		weight+=attack;
		return attack;
	}
	
	int calculateCost(){
		int cost = (int) (weight/2.5);
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