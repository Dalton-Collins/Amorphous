package engine;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

//This class contains all the information relevant to the current game
//uses the singleton pattern to allow global access to the single instance of this class
public class GameState {
	
	Integer id;
	
	public ArrayList<Player> players;
	ServerThread st1;
	ServerThread st2;
	
	public static int maxMinions = 10;//10 minions per player possible
	Player turnPlayer;//whos turn is it to play
	
	public ActiveEffects activeEffects;
	public AffectStack affectStack;
	public MinionFactory minionFactory;
	public MinionGenerator minionGenerator;
	
	boolean selectingAttackTarget = false;
	boolean selectingAffectTarget = false;
	
	SummonHandler summonHandler;
	AttackHandler attackHandler;
	EndTurnHandler endTurnHandler;
	AffectSelectHandler affectSelectHandler;
	DirectAttackHandler directAttackHandler;
	
	public GameState(ServerThread st){
		st1 = st;
	}
	
	public void initGameState(ServerThread s2){
		
		st2 = s2;
		
		//Set Handlers
    	
    	summonHandler = new SummonHandler(this);
    	attackHandler = new AttackHandler(this);
    	endTurnHandler = new EndTurnHandler(this);
    	affectSelectHandler = new AffectSelectHandler(this);
    	directAttackHandler = new DirectAttackHandler(this);
    	
		//initialize game
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
		
		updateDisplays();
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
		
		updateDisplays();
	}
	
	public Player getEnemy(Player p){
		if(players.get(0) == p){
			return players.get(1);
		}else{
			return players.get(0);
		}
	}
	
	public void updateDisplays(){
		//update for player 1
		DisplayGameState dgs1 = getUpdatedDisplayGameState(players.get(0), players.get(1));
		
		ObjectOutputStream oos;
		try {
			System.out.println("writing displaygamestate to client 1");
			oos = new ObjectOutputStream(st1.socket.getOutputStream());
			oos.writeObject(dgs1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//update for player 2
		
		DisplayGameState dgs2 = getUpdatedDisplayGameState(players.get(1), players.get(0));
		
		ObjectOutputStream oos2;
		try {
			System.out.println("writing displaygamestate to client 2");
			oos2 = new ObjectOutputStream(st2.socket.getOutputStream());
			oos2.writeObject(dgs2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	DisplayGameState getUpdatedDisplayGameState(Player p1, Player p2){
		DisplayGameState dgs = new DisplayGameState();
		//update hand
		for(Minion m : p1.hand.cards){
			DisplayMinion dm = new DisplayMinion(m);
			dgs.handMinions.add(dm);
		}
		//update friendly minions
		for(Minion m : p1.minions){
			DisplayMinion dm = new DisplayMinion(m);
			dgs.friendlyFieldMinions.add(dm);
		}
		//update enemy minions
		for(Minion m : p2.minions){
			DisplayMinion dm = new DisplayMinion(m);
			dgs.enemyFieldMinions.add(dm);
		}
		//update other stuff
		dgs.enemyHandSize = p2.hand.cards.size();
		dgs.selectingAttackTarget = selectingAttackTarget;
		dgs.selectingAffectTarget = selectingAffectTarget;
		
		dgs.mana = p1.mana;
		dgs.maxMana = p1.maxMana;
		dgs.life = p1.life;
		
		dgs.enemyMana = p2.mana;
		dgs.enemyMaxMana = p2.maxMana;
		dgs.enemyLife = p2.life;
		
		return dgs;
	}
}
