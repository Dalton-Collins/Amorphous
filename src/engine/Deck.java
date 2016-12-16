package engine;

import java.util.ArrayList;

public class Deck {
	ArrayList<Minion> cards;
	
	public void draw(Hand h){
		Minion card = cards.get(0);
		h.cards.add(card);
	}
}
