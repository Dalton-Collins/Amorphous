package affects;

import engine.Affect;
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

}
