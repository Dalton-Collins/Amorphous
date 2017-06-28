package engine;

import java.util.ArrayList;

import triggers.OnSummonTrigger;
import triggers.Trigger;
import triggers.WhenDestroyedTrigger;
import affects.Affect;
import affects.RemoveAllAlliesSummoningSickness;
import affects.SummonMinionAffect;

public class CardDatabase {
	
	GameState gs;
	
	ArrayList<CardMaker> cardMakers = new ArrayList<CardMaker>();
	
	CardDatabase(GameState gss){
		gs = gss;
	}
	
	
	
	public Minion getMinion(int id, Player owner){
		
		cardMakers.add(new AnabolicFungus());
		cardMakers.add(new Sprout());
		cardMakers.add(new SilnymphMother());
		
		for(CardMaker cm : cardMakers){
			if(cm.getId() == id){
				return cm.makeCard(owner);
			}
		}
		System.out.println("requested invalid card id to make");
		return null;
	}
	
	class AnabolicFungus implements CardMaker{

		@Override
		public Minion makeCard(Player owner) {
			
			Minion m = new Minion(gs, owner, gs.getUniqueMinionId());
			
			m.id = 4001;
			m.name = "Anabolic Fungus";
			
			m.redCost = 0;
			m.orangeCost = 0;
			m.yellowCost = 0;
			m.greenCost = 1;
			m.blueCost = 0;
			m.purpleCost = 0;
			
			m.maxHealth = 3;
			m.type = "Plant";
			
			m.setAttack(2);
			m.setHealth(3);
			
			SummonMinionAffect sma = new SummonMinionAffect(gs, m, 4002, "Sprout");
			OnSummonTrigger ost = new OnSummonTrigger();
			Effect ef = new Effect(m, ost, sma, 1);
			m.effect = ef;
			
			return m;
		}
		
		@Override
		public int getId(){
			return 4001;
		}
		
		@Override
		public String getName(){
			return "Anabolic Mushroom";
		}
		
	}
	
	class Sprout implements CardMaker{
		
		@Override
		public Minion makeCard(Player owner){
			
			Minion m = new Minion(gs, owner, gs.getUniqueMinionId());
			
			m.id = 4002;
			m.name = "Sprout";
			
			m.redCost = 0;
			m.orangeCost = 0;
			m.yellowCost = 0;
			m.greenCost = 1;
			m.blueCost = 0;
			m.purpleCost = 0;
			
			m.maxHealth = 1;
			m.type = "Plant";
			
			m.setAttack(1);
			m.setHealth(1);
			
			return m;
		}
		
		@Override
		public int getId(){
			return 4002;
		}
		
		@Override
		public String getName(){
			return "Sprout";
		}
	}
	
	class SilnymphMother implements CardMaker{

		@Override
		public Minion makeCard(Player owner) {
			
			Commander m = new Commander(gs, owner, gs.getUniqueMinionId());
			
			/*
			Affect af = 
			Trigger tr = 
			Effect e = new Effect(m, tr, af, 1);
			m.effect = e;
			*/
			m.id = 4003;
			m.type = "Nymph";
			m.setAttack(3);
			m.setHealth(4);
			m.maxHealth = m.getHealth();
			m.name = "Silnymph Mother";
			
			m.redCost = 0;
			m.orangeCost = 0;
			m.yellowCost = 0;
			m.greenCost = 4;
			m.blueCost = 0;
			m.purpleCost = 0;
			
			return m;
		}

		@Override
		public int getId() {
			
			return 4003;
		}

		@Override
		public String getName() {
			
			return "Silnymph Mother";
		}
	}
	
	public Deck getPlantDeck(Player owner){
		Deck d = new Deck();
		
		for(int i = 0; i<29; i++){
			d.cards.add(getMinion(4001, owner));
		}
		
		d.commander = (Commander) getMinion(4003, owner);
		
		return d;
	}

}
