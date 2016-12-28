package engine;

import java.util.ArrayList;

//this class holds all the information sent by the server
//that needs to be displayed to the client
public class DisplayGameState {
	
	ArrayList<DisplayMinion> handMinions;
	ArrayList<DisplayMinion> friendlyFieldMinions;
	ArrayList<DisplayMinion> enemyFieldMinions;
	
	int enemyHandSize;
	boolean selectingAttackTarget;
	boolean selectingAffectTarget;
	
	int mana;
	int maxMana;
	int life;
	
	int enemyMana;
	int enemyMaxMana;
	int enemyLife;
	
}
