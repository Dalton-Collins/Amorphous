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
import affects.RemoveAllAlliesSummoningSickness;
import affects.SelectAndDestroyMinionAffect;

//this class handles the creation of new cards
public class MinionGenerator {
	
	ArrayList<Affect> affectList;
	ArrayList<Trigger> triggerList;
	GameState gs;
	
	int redCost;
	int orangeCost;
	int yellowCost;
	int greenCost;
	int blueCost;
	int purpleCost;
	
	MinionGenerator(GameState gss){
		gs = gss;
	}
	
	public Commander makeOzai(Player p){
		
		Commander m = new Commander(gs, p);

		Affect af = new RemoveAllAlliesSummoningSickness(gs, m);
		Trigger tr = new WhenDestroyedTrigger();
		Effect e = new Effect(m, tr, af, 1);
		m.effect = e;
		m.type = "Demonoid";
		m.setAttack(3);
		m.health = 2;
		m.maxHealth = m.health;
		m.name = "Ozai";
		
		m.Rush = true;
		
		m.redCost = 3;
		m.orangeCost = 0;
		m.yellowCost = 0;
		m.greenCost = 0;
		m.blueCost = 0;
		m.purpleCost = 0;
		
		return m;
	}
	
	public Minion makeRandomMinion(Player p){
		//first try to make the minion randomly
		redCost = 0;
		orangeCost = 0;
		yellowCost = 0;
		greenCost = 0;
		blueCost = 0;
		purpleCost = 0;
		
		Minion m = new Minion(gs, p);

		Affect af = randomAffect(m);
		Trigger tr = randomTrigger();
		Effect e = new Effect(m, tr, af, 1);
		m.effect = e;
		m.type = randomType();
		m.setAttack(randomAttack());
		m.health = randomHealth(m);
		m.maxHealth = m.health;
		m.name = randomName(m);
		
		m.redCost = redCost;
		m.orangeCost = orangeCost;
		m.yellowCost = yellowCost;
		m.greenCost = greenCost;
		m.blueCost = blueCost;
		m.purpleCost = purpleCost;
		
		
		return m;
	}
	
	public Deck makeRandomDeck(Player p){
		Deck deck = new Deck();
		deck.commander = makeOzai(p);
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
			int damage = (rand.nextInt(6)+1);
			yellowCost += 2 + damage;
			DamageAllEnemyMinionsAffect daema = new DamageAllEnemyMinionsAffect(gs, m, damage);
			return daema;
		}else if(i == 1){
			int damage = (rand.nextInt(5)+1)*2;
			redCost += damage/2;
			DamageEnemyLifeAffect dela = new DamageEnemyLifeAffect(gs, m, damage);
			return dela;
		}else if(i == 2){
			blueCost += 2;
			DrawCardAffect dca = new DrawCardAffect(m);
			return dca;
		}else if(i == 3){
			int atk = (rand.nextInt(4)+1)*2;//range 1 - 8
			orangeCost += 1+ atk/2;
			IncreaseThisAttackAffect itaa = new IncreaseThisAttackAffect(m, atk);
			return itaa;
		}else if(i == 4){
			purpleCost += 3;
			SelectAndDestroyMinionAffect sadma = new SelectAndDestroyMinionAffect(gs, m);
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
			return dat;
		}else if(i == 1){
			OnSummonTrigger ost = new OnSummonTrigger();
			return ost;
		}else if(i == 2){
			WhenAttackedTrigger wat = new WhenAttackedTrigger();
			return wat;
		}else if(i == 3){
			WhenDamagedTrigger wdt = new WhenDamagedTrigger();
			return wdt;
		}else if(i == 4){
			WhenDestroyedTrigger wdt = new WhenDestroyedTrigger();
			return wdt;
		}else if(i == 5){
			WhenEnemyDrawsCards wedc = new WhenEnemyDrawsCards(1);
			yellowCost+=1;
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
	
	int randomHealth(Minion m){
		int health = m.getAttack();
		return health;
	}
	
	int randomAttack(){
		Random rand = new Random();
		int atk = rand.nextInt(8);
		
		return atk;
	}
	
	String randomName(Minion m){
		String name = "";
		name+= m.getAttack() + m.health + m.effect.trigger.getDescription().substring(0, 3)
				+ m.effect.affect.getDescription().substring(0, 3);
		return name;
	}
	
	void addDistributedCost(int cost){
		int topCost = 0;
		int topColor = -1;//0 red 1 orange 2 yellow etc
		int tieColor = -1;
		if(redCost > topCost){
			topColor = 0;
			topCost = redCost;
		}
		if(orangeCost > topCost){
			topColor = 1;
			topCost = orangeCost;
		}
		if(yellowCost > topCost){
			topColor = 2;
			topCost = yellowCost;
		}
		if(greenCost > topCost){
			topColor = 3;
			topCost = greenCost;
		}
		if(blueCost > topCost){
			topColor = 4;
			topCost = blueCost;
		}
		if(purpleCost > topCost){
			topColor = 5;
			topCost = purpleCost;
		}
		
		
		if(redCost == topCost){
			tieColor = 0;
			topCost = redCost;
		}
		if(orangeCost == topCost){
			tieColor = 1;
			topCost = orangeCost;
		}
		if(yellowCost == topCost){
			tieColor = 2;
			topCost = yellowCost;
		}
		if(greenCost == topCost){
			tieColor = 3;
			topCost = greenCost;
		}
		if(blueCost == topCost){
			tieColor = 4;
			topCost = blueCost;
		}
		if(purpleCost == topCost){
			tieColor = 5;
			topCost = purpleCost;
		}
		
		
		if(tieColor != topColor){
			//do stuff for multicolor minions
		}
		
		
		if(topColor == 0){
			redCost += cost;
		}else if(topColor == 1){
			orangeCost += cost;
		}else if(topColor == 2){
			yellowCost += cost;
		}else if(topColor == 3){
			greenCost += cost;
		}else if(topColor == 4){
			blueCost += cost;
		}else if(topColor == 5){
			purpleCost += cost;
		}
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