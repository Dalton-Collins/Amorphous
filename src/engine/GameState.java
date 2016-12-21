package engine;

import java.util.ArrayList;

//This class contains all the information relevant to the current game
public class GameState {
	ArrayList<Player> players;
	public static int maxMinions = 10;//10 minions per player possible
	Player turnPlayer;//whos turn is it to play
	
	
	public GameState(){
		//initialize game
		players = new ArrayList<Player>();
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		players.add(new Player(deck1, 0));
		players.add(new Player(deck2, 1));
		
		for(int i = 1; i < 31; i++){
			deck1.cards.add(new TestMinion(players.get(0), this, (i*2) - 1));
			deck2.cards.add(new TestMinion(players.get(1), this, i*2));
		}
		
		players.get(0).draw(4);
		players.get(1).draw(4);
		
		turnPlayer = players.get(0);
	}
	
	public void nextTurn(){
		int turn = turnPlayer.id;
		turn = (turn+1)%players.size();
		turnPlayer = players.get(turn);
		for(Minion m : turnPlayer.minions){
			m.removeSummoningSickness();//change this
			m.resetTurnAttacks();
		}
		System.out.println("Its now player " + turn + "'s turn");
		turnPlayer.mana = turnPlayer.maxMana;
		turnPlayer.draw(1);
	}
}
