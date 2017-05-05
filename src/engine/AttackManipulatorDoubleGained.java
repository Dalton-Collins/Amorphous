package engine;

public class AttackManipulatorDoubleGained implements AttackManipulator{
	
	int atk;

	@Override
	public int getAttack() {
		
		return atk;
	}

	@Override
	public int getAttack(Minion m) {
		
		return atk;
	}

	@Override
	public int getAttack(Player P) {
		
		return atk;
	}

	@Override
	public void changeAttack(Integer change) {
		atk = atk + change*2;
		
	}

	@Override
	public void setAttack(Integer change) {
		atk = change;
		
	}
	
	@Override
	public String getDescription(){
		return "Double any attack gains this minion receives";
	}

}
