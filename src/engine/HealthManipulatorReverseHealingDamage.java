package engine;

public class HealthManipulatorReverseHealingDamage implements HealthManipulator{
	
	public HealthManipulatorReverseHealingDamage(){
	}
	int health;
	
	@Override
	public int getHealth() {
		
		return health;
	}

	@Override
	public void changeHealth(Integer change) {
		health+= -change;
	}

	@Override
	public void setHealth(Integer change) {
		
		health = change;
	}
	
	@Override
	public String getDescription(){
		return "Healing and damage done to this minion is inverted";
	}

}
