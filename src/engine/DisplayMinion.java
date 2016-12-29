package engine;

import java.io.Serializable;

public class DisplayMinion implements Serializable{

	private static final long serialVersionUID = -740483854398002466L;
	
	public String name;
	public int cost;
	public int atk;
	public int baseAtk;
	public int health;
	public int maxHealth;
	public String type;// curent types: humanoid beast machine
	public int owner;//0 for player 1, 1 for player 2
	public String affectText;
	public String triggerText;
	public int attacksThisTurn = 0;
	public int maxAttacks = 1;
	boolean summoningSickness;
	
	public DisplayMinion(Minion m){
		name = m.name;
		cost = m.cost;
		atk = m.atk;
		baseAtk = m.baseAtk;
		health = m.health;
		maxHealth = m.maxHealth;
		type = m.type;
		owner = m.owner.id;
		affectText = m.effect.affect.getDescription();
		triggerText = m.effect.trigger.getDescription();
		attacksThisTurn = m.attacksThisTurn;
		maxAttacks = m.maxAttacks;
		summoningSickness = m.summoningSickness;
	}
}
