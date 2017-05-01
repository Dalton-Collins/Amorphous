package affects;

import engine.AttackManipulator;
import engine.Minion;

public class PassiveAttackManipulatorAffect implements Affect{
	Minion owner;
	AttackManipulator am;
	
	public PassiveAttackManipulatorAffect(Minion ownerr, AttackManipulator amm){
		owner = ownerr;
		am = amm;
	}
	@Override
	public void applyAffect(){
		
	}

	@Override
	public String getDescription() {
		
		return am.getDescription();
	}

}
