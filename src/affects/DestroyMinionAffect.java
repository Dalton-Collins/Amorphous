package affects;

import engine.Affect;
import engine.Minion;

public class DestroyMinionAffect implements Affect{
	
	Minion target = null;
	Minion destroyer = null;
	@Override
	public void applyAffect() {
		assert(target != null);
		assert(destroyer != null);
		target.destroy(destroyer);
	}

}
