package engine;

public class StandardHealthManipulator implements HealthManipulator{
	
	public StandardHealthManipulator(Minion mm){
		m = mm;
	}
	
	Minion m;
	
	int health;
	
	@Override
	public int getHealth() {
		
		return health;
	}

	@Override
	public void changeHealth(Integer change) {
		
		health = health + change;
	}

	@Override
	public void setHealth(Integer change) {
		
		health = change;
	}

}