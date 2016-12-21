package affects;

import engine.Affect;
import engine.Minion;

public class DestroyMinionAffect implements Affect{
	
	Minion target = null;
	Minion owner;
	
	public DestroyMinionAffect(Minion ownerr){
		owner = ownerr;
	}
	@Override
	public void applyAffect() {
		assert(target != null);
		target.destroy(owner);
	}

}
