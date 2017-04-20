package engine;

public class ZeroAttackManipulator implements AttackManipulator{

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

}
