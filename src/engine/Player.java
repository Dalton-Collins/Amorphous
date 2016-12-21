package engine;

import java.util.ArrayList;

public class Player {
	int id;
	Hand hand;
	Deck deck;
	ArrayList<Minion> minions;
	int totalMinions;
	int mana; //can spend mana to add max mana
	int maxMana = 100;
	int life = 100;
	public Player(Deck deckk, int idd){
		hand = new Hand();
		mana = 100;
		deck = deckk;
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
	}
	
	//checks if the given number of cards can be drawn
	public boolean canDraw(int numberOfCards){
		return deck.cards.size() >= numberOfCards;
	}
}
