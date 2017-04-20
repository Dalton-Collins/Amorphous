package engine;

public class StandardAttackManipulator implements AttackManipulator{
	
	public StandardAttackManipulator(Minion mm){
		m = mm;
	}
	
	Minion m;
	
	@Override
	public int getAttack() {
		
		return m.atk;
	}

	@Override
	public int getAttack(Minion m) {
		
		return m.atk;
	}

	@Override
	public int getAttack(Player P) {
		
		return m.atk;
	}

	@Override
	public void changeAttack(Integer change) {
		
		m.atk = m.atk + change;
	}

	@Override
	public void setAttack(Integer change) {
		
		m.atk = change;
	}

}
