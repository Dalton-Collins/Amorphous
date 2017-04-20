package engine;

//used to check what a minions health is
//and change a minions health
public interface HealthManipulator {
	
	public int getHealth();
	public void changeHealth(Integer change);//a positive int to increase attack, negative to decrease
	public void setHealth(Integer change);//set attack to this value
	
}