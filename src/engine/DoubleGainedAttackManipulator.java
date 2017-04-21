package engine;

public class DoubleGainedAttackManipulator implements AttackManipulator{
	
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

}
