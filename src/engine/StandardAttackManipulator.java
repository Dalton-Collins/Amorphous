package engine;

public class StandardAttackManipulator implements AttackManipulator{
	
	public StandardAttackManipulator(Minion mm){
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
		
		return atk;
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
		return "This should not appear";
	}

}
