package engine;

public class AttackManipulatorNoPlayerDamage implements AttackManipulator{
	
	public AttackManipulatorNoPlayerDamage(Minion mm){
		m = mm;
	}
	
	Minion m;
	
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
		
		return 0;
	}

	@Override
	public void changeAttack(Integer change) {
		
		atk = atk + change;
	}

	@Override
	public void setAttack(Integer change) {
		
		atk = change;
	}
	
	@Override
	public String getDescription(){
		return "";
	}

}