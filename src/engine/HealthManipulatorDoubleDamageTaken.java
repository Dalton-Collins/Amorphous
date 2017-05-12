package engine;

public class HealthManipulatorDoubleDamageTaken implements HealthManipulator{
	
	public HealthManipulatorDoubleDamageTaken(Minion mm){
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
		
		if(change <1){
			health+= 2*change;
		}else{
			health +=change;
		}
	}

	@Override
	public void setHealth(Integer change) {
		
		health = change;
	}
	
	@Override
	public String getDescription(){
		return "";
	}

}