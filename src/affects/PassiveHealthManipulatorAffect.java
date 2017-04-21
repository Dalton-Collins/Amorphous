package affects;

import engine.HealthManipulator;
import engine.Minion;

public class PassiveHealthManipulatorAffect implements Affect{
	Minion owner;
	HealthManipulator hm;
	
	public PassiveHealthManipulatorAffect(Minion ownerr, HealthManipulator hmm){
		owner = ownerr;
		hm = hmm;
	}
	@Override
	public void applyAffect(){
		
	}

	@Override
	public String getDescription() {
		
		return hm.getDescription();
	}

}
