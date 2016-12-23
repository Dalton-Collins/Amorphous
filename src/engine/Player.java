package engine;

import java.util.ArrayList;

public class Player {
	public int id;
	public Hand hand;
	public Deck deck;
	public ArrayList<Minion> minions;
	public int totalMinions;
	public int mana; //can spend mana to add max mana
	public int maxMana = 20;
	public int life = 300;
	public Player(int idd){
		hand = new Hand();
		mana = 20;
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
		
		Event e = new Event("playerDrewCard");
		e.p = this;
		e.amount = numberOfCards;
		GameState.getGameState().affectStack.handleEvent(e);
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
		GameState.getGameState().affectStack.handleEvent(e);
	}
}
