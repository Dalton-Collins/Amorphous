package engine;

import java.util.ArrayList;

//This class contains all the information relevant to the current game
//uses the singleton pattern to allow global access to the single instance of this class
public class GameState {
	
	private static GameState self = new GameState();
	
	
	public ArrayList<Player> players;
	public static int maxMinions = 10;//10 minions per player possible
	Player turnPlayer;//whos turn is it to play
	public ActiveEffects activeEffects;
	
	public void initGameState(){
		//initialize game
		players = new ArrayList<Player>();
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		players.add(new Player(deck1, 0));
		players.add(new Player(deck2, 1));
		
		activeEffects = new ActiveEffects();
		
		for(int i = 1; i < 31; i++){
			deck1.cards.add(new TestMinion(players.get(0), this, (i*2) - 1));
			deck2.cards.add(new TestMinion(players.get(1), this, i*2));
		}
		
		players.get(0).draw(4);
		players.get(1).draw(4);
		
		turnPlayer = players.get(0);
	}
	
	public static GameState getGameState() { 
		return self; 
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
	
	public Player getEnemy(Player p){
		if(players.get(0) == p){
			return players.get(1);
		}else{
			return players.get(0);
		}
	}
}
