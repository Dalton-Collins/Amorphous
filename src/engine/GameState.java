package engine;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import affects.SelectAndDestroyMinionAffect;

//This class contains all the information relevant to the current game
//uses the singleton pattern to allow global access to the single instance of this class
public class GameState {
	
	GameIdentifier gameIdentifier;
	Server server;
	
	Long uniqueMinionId;
	
	public CardDatabase CDB;
	public ArrayList<Player> players;
	ServerThread serverThread1;
	ServerThread serverThread2;
	
	Socket outputSocket1;
	Socket outputSocket2;
	public ObjectOutputStream oos1;
	public ObjectOutputStream oos2;
	
	public int winner = 0;//1 for p1 wins, 2 for p2 wins
	public static int maxMinions = 10;//10 minions per player possible
	Player turnPlayer;//whos turn is it to play
	int turnPlayerId;//equal to the thread id of the current turn player
	
	public ActiveEffects activeEffects;
	public AffectStack affectStack;
	public MinionGenerator minionGenerator;
	
	public boolean selectingAffectTarget = false;
	
	SummonHandler summonHandler;
	AttackHandler attackHandler;
	EndTurnHandler endTurnHandler;
	AffectSelectHandler affectSelectHandler;
	DirectAttackHandler directAttackHandler;
	
	public GameState(ServerThread st, Server serverr){
		server = serverr;
		serverThread1 = st;
		outputSocket1 = server.outputSockets.get(st.id);
		
		uniqueMinionId = (long) 0;
	}
	
	public void initGameState(ServerThread s2){
		
		serverThread2 = s2;
		outputSocket2 = server.outputSockets.get(s2.id);
		oos1 = serverThread1.oos;
		oos2 = serverThread2.oos;
		
		CDB = new CardDatabase(this);
		
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
		
		players.get(0).deck = CDB.getPlantDeck(players.get(0));
		
		//players.get(0).deck = minionGenerator.makeRandomDeck(players.get(0));
		players.get(1).deck = minionGenerator.makeRandomDeck(players.get(1));
		setMinionUniqueIds(players.get(0).deck);
		setMinionUniqueIds(players.get(1).deck);
		
		activeEffects = new ActiveEffects();
		affectStack = new AffectStack(this);
		
		players.get(0).draw(4);
		players.get(1).draw(4);
		
		players.get(0).id = 0;
		players.get(1).id = 1;
		
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
		turnPlayer.maxRedMana+=1;
		turnPlayer.maxOrangeMana+=1;
		turnPlayer.maxYellowMana+=1;
		turnPlayer.maxGreenMana+=1;
		turnPlayer.maxBlueMana+=1;
		turnPlayer.maxPurpleMana+=1;
		
		turnPlayer.redMana = turnPlayer.maxRedMana;
		turnPlayer.orangeMana = turnPlayer.maxOrangeMana;
		turnPlayer.yellowMana = turnPlayer.maxYellowMana;
		turnPlayer.greenMana = turnPlayer.maxGreenMana;
		turnPlayer.blueMana = turnPlayer.maxBlueMana;
		turnPlayer.purpleMana = turnPlayer.maxPurpleMana;
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
	
	public void selectUpdateDisplays(SelectAndDestroyMinionAffect sadma){
		//sends a message to the client asking them to select a target
		if(sadma.owner.owner == players.get(0)){//if the affect activating belongs to player 1
			DisplayGameState dgs = getUpdatedDisplayGameState(players.get(0), players.get(1));
			dgs.selectingAffectTarget = true;
			try {
				oos1.writeObject(dgs);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{//or player 2
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
		//update commanders
		DisplayMinion cm = new DisplayMinion(p1.deck.commander);
		if(p1.deck.commander.inCommandZone){
			cm.inCommandZone = true;
		}
		dgs.commander = cm;
		
		DisplayMinion ecm = new DisplayMinion(p2.deck.commander);
		if(p2.deck.commander.inCommandZone){
			ecm.inCommandZone = true;
		}
		dgs.enemyCommander = ecm;
		
		//update other stuff
		if(winner == 1){
			if(p1 == players.get(0)){
				dgs.winner = 1;
			}else if(p1 == players.get(1)){
				dgs.winner = 2;
			}
		}else if(winner == 2){
			if(p1 == players.get(0)){
				dgs.winner = 2;
			}else if(p1 == players.get(1)){
				dgs.winner = 1;
			}
		}
		dgs.enemyHandSize = p2.hand.cards.size();
		dgs.selectingAffectTarget = selectingAffectTarget;
		
		dgs.redMana = p1.redMana;
		dgs.orangeMana = p1.orangeMana;
		dgs.yellowMana = p1.yellowMana;
		dgs.greenMana = p1.greenMana;
		dgs.blueMana = p1.blueMana;
		dgs.purpleMana = p1.purpleMana;
		
		dgs.maxRedMana = p1.maxRedMana;
		dgs.maxOrangeMana = p1.maxOrangeMana;
		dgs.maxYellowMana = p1.maxYellowMana;
		dgs.maxGreenMana = p1.maxGreenMana;
		dgs.maxBlueMana = p1.maxBlueMana;
		dgs.maxPurpleMana = p1.maxPurpleMana;
		
		dgs.life = p1.life;
		
		dgs.enemyRedMana = p2.redMana;
		dgs.enemyOrangeMana = p2.orangeMana;
		dgs.enemyYellowMana = p2.yellowMana;
		dgs.enemyGreenMana = p2.greenMana;
		dgs.enemyBlueMana = p2.blueMana;
		dgs.enemyPurpleMana = p2.purpleMana;
		
		dgs.enemyMaxRedMana = p2.maxRedMana;
		dgs.enemyMaxOrangeMana = p2.maxOrangeMana;
		dgs.enemyMaxYellowMana = p2.maxYellowMana;
		dgs.enemyMaxGreenMana = p2.maxGreenMana;
		dgs.enemyMaxBlueMana = p2.maxBlueMana;
		dgs.enemyMaxPurpleMana = p2.maxPurpleMana;
		
		dgs.enemyLife = p2.life;
		
		if(turnPlayer == p1){
			dgs.yourTurn = true;
		}else{
			dgs.yourTurn = false;
		}
		
		return dgs;
	}
	
	void setMinionUniqueIds(Deck deck){
		Random rand = new Random();
		for(Minion m : deck.cards){
			m.uniqueId = rand.nextLong();
		}
	}
	
	Player getOtherPlayer(Player p){
		if(players.get(0) == p){
			return players.get(1);
		}else{
			return players.get(0);
		}
	}
	
	Long getUniqueMinionId(){
		
		this.uniqueMinionId+=1;
		return this.uniqueMinionId;
	}
}
