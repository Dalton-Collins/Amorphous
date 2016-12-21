package affects;

import engine.Affect;
import engine.Minion;

public class DestroySelectedMinionAffect implements Affect{
	
	Minion owner;
	public DestroySelectedMinionAffect(Minion ownerr){
		owner = ownerr;
	}
	@Override
	public void applyAffect() {
		
		//get minion slection from display
		//destroy minion
	}

}
