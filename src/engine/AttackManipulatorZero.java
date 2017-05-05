package engine;

public class AttackManipulatorZero implements AttackManipulator{
	
	//sets units attack to zero while this manipulator is active

	@Override
	public int getAttack() {
		
		return 0;
	}

	@Override
	public int getAttack(Minion m) {
		
		return 0;
	}

	@Override
	public int getAttack(Player P) {
		
		return 0;
	}

	@Override
	public void changeAttack(Integer change) {
		return;
		
	}

	@Override
	public void setAttack(Integer change) {
		return;
		
	}
	
	@Override
	public String getDescription(){
		return "This minions attack is always 0";
	}

}
