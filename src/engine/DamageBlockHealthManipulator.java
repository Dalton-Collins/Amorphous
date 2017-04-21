package engine;

public class DamageBlockHealthManipulator implements HealthManipulator{
	
	public DamageBlockHealthManipulator(int block){
		damageBlock = block;
	}
	int damageBlock;
	int health;
	
	@Override
	public int getHealth() {
		
		return health;
	}

	@Override
	public void changeHealth(Integer change) {
		if(change < 0){
			change+= damageBlock;
			if(change > 0){
				change = 0;
			}
		}
		health+= change;
	}

	@Override
	public void setHealth(Integer change) {
		
		health = change;
	}
	
	@Override
	public String getDescription(){
		return "this minion takes -" + damageBlock+" damage from hits";
	}

}
