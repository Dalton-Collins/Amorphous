package engine;

//used to check what a minions attack is
//and change a minions attack
public interface AttackManipulator {
	
	public int getAttack();
	public int getAttack(Minion m);//this function requires a minion target for the attack
	public int getAttack(Player P);//this function requires a player target for the attack
	public void changeAttack(Integer change);//a positive int to increase attack, negative to decrease
	public void setAttack(Integer change);//set attack to this value
	
}
