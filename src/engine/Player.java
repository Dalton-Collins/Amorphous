package engine;

import java.util.ArrayList;

public class Player {
	public int id;//0 or 1 for the players id
	public Hand hand;
	public Deck deck;
	public ArrayList<Minion> minions;
	public int totalMinions;
	public int redMana;
	public int orangeMana;
	public int yellowMana;
	public int greenMana;
	public int blueMana;
	public int purpleMana;
	public int maxRedMana = 1;
	public int maxOrangeMana = 1;
	public int maxYellowMana = 1;
	public int maxGreenMana = 1;
	public int maxBlueMana = 1;
	public int maxPurpleMana = 1;
	public int life = 30;
	
	GameState gs;
	
	public Player(GameState gss, int idd){
		gs = gss;
		hand = new Hand();
		id = idd;
		minions = new ArrayList<Minion>();
	}
	//draws cards from the deck to the hand
	public void draw(int numberOfCards){
		for(int i = 0; i < numberOfCards; i++){
			Minion c = deck.cards.get(0);
			hand.cards.add(c);
			deck.cards.remove(0);
		}
		
		Event e = new Event("playerDrewCards");
		e.p = this;
		e.amount = numberOfCards;
		gs.affectStack.handleEvent(e);
	}
	
	//checks if the given number of cards can be drawn
	public boolean canDraw(int numberOfCards){
		return deck.cards.size() >= numberOfCards;
	}
	
	public void damagePlayer(int damage, Minion damager){
		life-=damage;
		
		Event e = new Event("playerDamaged");
		e.m = damager;
		e.amount = damage;
		gs.affectStack.handleEvent(e);
		
		if(life <=0){
			gs.winner = gs.getOtherPlayer(this).id;
			gs.updateDisplays();
		}
	}
}
