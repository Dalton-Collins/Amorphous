package engine;

import java.util.ArrayList;

import ClientSide.CardViewHandler;
import ClientSide.fxDisplay;

//This class contains all the information relevant to the current game
//uses the singleton pattern to allow global access to the single instance of this class
public class GameState {
	
	Integer id;
	
	public ArrayList<Player> players;
	ServerThread p1;
	ServerThread p2;
	
	public static int maxMinions = 10;//10 minions per player possible
	Player turnPlayer;//whos turn is it to play
	
	public ActiveEffects activeEffects;
	public AffectStack affectStack;
	public MinionFactory minionFactory;
	public MinionGenerator minionGenerator;
	
	boolean selectingAttackTarget = false;
	boolean selectingAffectTarget = false;
	
	Minion attackingMinion;
	
	public fxDisplay fxd;
	
	SummonHandler summonHandler;
	AttackHandler attackHandler;
	EndTurnHandler endTurnHandler;
	AffectSelectHandler affectSelectHandler;
	DirectAttackHandler directAttackHandler;
	CardViewHandler cardViewHandler;
	
	public void initGameState(fxDisplay fxdd){
		
		//Set Handlers
    	
    	summonHandler = new SummonHandler(this);
    	attackHandler = new AttackHandler(this);
    	endTurnHandler = new EndTurnHandler(this);
    	affectSelectHandler = new AffectSelectHandler(this);
    	directAttackHandler = new DirectAttackHandler(this);
    	
		//initialize game
		fxd = fxdd;
		minionGenerator = new MinionGenerator(this);
		players = new ArrayList<Player>();
		players.add(new Player(this, 0));
		players.add(new Player(this, 1));
		players.get(0).deck = minionGenerator.makeRandomDeck(players.get(0));
		players.get(1).deck = minionGenerator.makeRandomDeck(players.get(1));
		
		activeEffects = new ActiveEffects();
		affectStack = new AffectStack(this);
		
		players.get(0).draw(4);
		players.get(1).draw(4);
		
		turnPlayer = players.get(0);
	}
	
	public void nextTurn(){
		int turn = turnPlayer.id;
		turn = (turn+1)%players.size();
		turnPlayer = players.get(turn);
		
		//reset attacks this turn to 0
		for(Minion m : turnPlayer.minions){
			m.removeSummoningSickness();//change this
			m.resetTurnAttacks();
		}
		//reset effect activations this turn to 0;
		for(Effect e : activeEffects.activeEffects){
			e.activationsThisTurn = 0;
		}
		
		System.out.println("Its now player " + turn + "'s turn");
		turnPlayer.maxMana+=10;
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
