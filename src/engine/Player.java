package engine;

public class Player {
	int id;
	Hand hand;
	Deck deck;
	int totalMinions;
	int mana;
	int maxMana = 100;
	int life = 50;
	public Player(Deck deckk, int idd){
		hand = new Hand();
		mana = 100;
		deck = deckk;
		id = idd;
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
