package engine;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import affects.SelectAndDestroyMinionAffect;

//This class contains all the information relevant to the current game
//uses the singleton pattern to allow global access to the single instance of this class
public class GameState {
	
	Integer id;
	Server server;
	
	public ArrayList<Player> players;
	ServerThread serverThread1;
	ServerThread serverThread2;
	
	Socket outputSocket1;
	Socket outputSocket2;
	public ObjectOutputStream oos1;
	public ObjectOutputStream oos2;
	
	public static int maxMinions = 10;//10 minions per player possible
	Player turnPlayer;//whos turn is it to play
	int turnPlayerId;//equal to the thread id of the current turn player
	
	public ActiveEffects activeEffects;
	public AffectStack affectStack;
	public MinionFactory minionFactory;
	public MinionGenerator minionGenerator;
	
	boolean selectingAffectTarget = false;
	
	SummonHandler summonHandler;
	AttackHandler attackHandler;
	EndTurnHandler endTurnHandler;
	AffectSelectHandler affectSelectHandler;
	DirectAttackHandler directAttackHandler;
	
	public GameState(ServerThread st, Server serverr){
		server = serverr;
		serverThread1 = st;
		outputSocket1 = server.outputSockets.get(st.id);
	}
	
	public void initGameState(ServerThread s2){
		
		serverThread2 = s2;
		outputSocket2 = server.outputSockets.get(s2.id);
		try {
			oos1 = new ObjectOutputStream(outputSocket1.getOutputStream());
			oos2 = new ObjectOutputStream(outputSocket2.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
		turnPlayerId = serverThread1.id;
		
		updateDisplays();
	}
	
	public void nextTurn(){
		int turn = turnPlayer.id;
		turn = (turn+1)%players.size();
		turnPlayer = players.get(turn);
		
		if(turnPlayerId == serverThread1.id){
			turnPlayerId = serverThread2.id;
		}else{
			turnPlayerId = serverThread1.id;
		}
		
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
		if(selectingAffectTarget){
			return;
		}
		//update for player 1
		DisplayGameState dgs1 = getUpdatedDisplayGameState(players.get(0), players.get(1));
		
		try {
			System.out.println("writing displaygamestate to client 1");
			oos1.writeObject(dgs1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//update for player 2
		
		DisplayGameState dgs2 = getUpdatedDisplayGameState(players.get(1), players.get(0));
		
		try {
			System.out.println("writing displaygamestate to client 2");
			oos2.writeObject(dgs2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateDisplays(SelectAndDestroyMinionAffect sadma){
		if(sadma.owner.owner == players.get(0)){//if the affect activating belongs to player 1
			DisplayGameState dgs = getUpdatedDisplayGameState(players.get(0), players.get(1));
			dgs.selectingAffectTarget = true;
			try {
				oos1.writeObject(dgs);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			DisplayGameState dgs = getUpdatedDisplayGameState(players.get(1), players.get(0));
			dgs.selectingAffectTarget = true;
			try {
				oos2.writeObject(dgs);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public DisplayGameState getUpdatedDisplayGameState(Player p1, Player p2){
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
		dgs.selectingAffectTarget = selectingAffectTarget;
		
		dgs.mana = p1.mana;
		dgs.maxMana = p1.maxMana;
		dgs.life = p1.life;
		
		dgs.enemyMana = p2.mana;
		dgs.enemyMaxMana = p2.maxMana;
		dgs.enemyLife = p2.life;
		
		if(turnPlayer == p1){
			dgs.yourTurn = true;
		}else{
			dgs.yourTurn = false;
		}
		
		return dgs;
	}
}
