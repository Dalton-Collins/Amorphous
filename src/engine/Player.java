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
}
