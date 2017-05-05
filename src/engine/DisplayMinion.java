package engine;

import java.io.Serializable;

public class DisplayMinion implements Serializable{

	private static final long serialVersionUID = -740483854398002466L;
	
	public Long uniqueId;
	public String name;
	public int redCost;
	public int orangeCost;
	public int yellowCost;
	public int greenCost;
	public int blueCost;
	public int purpleCost;
	public int atk;
	public int baseAtk;
	public int health;
	public int maxHealth;
	public String type;// curent types: humanoid beast machine
	public int owner;//0 for player 1, 1 for player 2
	public String affectText;
	public String triggerText;
	public String manipulatorText;
	public int attacksThisTurn = 0;
	public int maxAttacks = 1;
	boolean summoningSickness;
	boolean inCommandZone = false;
	
	public int fieldLocation;//0 for my field, 1 for enemys field
	
	public DisplayMinion(Minion m){
		
		uniqueId = m.uniqueId;
		name = m.name;
		redCost = m.redCost;
		orangeCost = m.orangeCost;
		yellowCost = m.yellowCost;
		greenCost = m.greenCost;
		blueCost = m.blueCost;
		purpleCost = m.purpleCost;
		atk = m.getAttack();
		health = m.getHealth();
		maxHealth = m.maxHealth;
		type = m.type;
		owner = m.owner.id;
		affectText = m.effect.affect.getDescription();
		triggerText = m.effect.trigger.getDescription();
		if(m.attackManipulatorStack.get(0).getDescription().equals("")){
			manipulatorText = m.healthManipulatorStack.get(0).getDescription();
		}else{
			manipulatorText = m.attackManipulatorStack.get(0).getDescription();
		}
		attacksThisTurn = m.attacksThisTurn;
		maxAttacks = m.maxAttacks;
		summoningSickness = m.summoningSickness;
	}
}
