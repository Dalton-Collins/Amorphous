package engine;

import java.util.Random;

//this class handles the creation of new cards
public class MinionGenerator {
	
	int weight;
	public Minion makeRandomMinion(Player p){
		weight = 0;
		Minion m = new Minion(p);
		
		return m;
	}
	
	Effect randomEffect(){
		
		//Effect e = new Effect();
		return null;
	}
	
	Affect randomAffect(){
		return null;
	}
	
	Trigger randomTrigger(){
		return null;
	}
	
	int randomWeight(){
		Random rand = new Random();
		int roll = rand.nextInt(100);
		if(roll < 45){
			return 80;
		}else if(roll < 75){
			return 110;
		}else if(roll < 95){
			return 140;
		}else if(roll < 99){
			return 170;
		}else if(roll >=99){
			return 200;
		}
		return -1;
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