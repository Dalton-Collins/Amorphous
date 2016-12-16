package engine;

public class Player {
	int id;
	Hand hand;
	Deck deck;
	public Player(Deck deckk){
		hand = new Hand();
		deck = deckk;
	}
}
