package engine;

import java.util.ArrayList;

//This class contains all the information relevant to the current game
public class GameState {
	ArrayList<Minion> minions;
	ArrayList<Player> players;
	public static int maxMinions = 10;//10 minions per player possible
	
	public GameState(){
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		for(int i = 0; i < 30; i++){
			deck1.cards.add(new TestMinion());
			deck2.cards.add(new TestMinion());
		}
		players.add(new Player(deck1, 1));
		players.add(new Player(deck2, 2));
	}
}
