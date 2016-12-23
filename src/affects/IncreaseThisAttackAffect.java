package affects;

import engine.Minion;

public class IncreaseThisAttackAffect implements Affect{
	
	Minion owner;
	int atkIncrease;
	public IncreaseThisAttackAffect(Minion ownerr, int incatk){
		owner = ownerr;
		atkIncrease = incatk;
	}
	@Override
	public void applyAffect() {
		owner.atk+=atkIncrease;
	}
	@Override
	public String getDescription() {
		
		return "Increase this minions Atk by " + atkIncrease;
	}

}
