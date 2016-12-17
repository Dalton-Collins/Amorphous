package engine;

import java.util.ArrayList;

//holds an arraylist of cards for this player
public class Hand {
	int playerId;
	ArrayList<Minion> cards;
	
	public Hand(){
		cards = new ArrayList<Minion>();
	}
}
