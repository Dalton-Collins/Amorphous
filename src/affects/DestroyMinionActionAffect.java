package affects;

import engine.Minion;

public class DestroyMinionActionAffect implements Affect{
	
	Minion target;
	Minion destroyer;
	
	public DestroyMinionActionAffect(Minion targ, Minion destroyr){
		target = targ;
		destroyer = destroyr;
	}
	@Override
	public void applyAffect() {
		target.destroy(destroyer);
		
	}

	@Override
	public String getDescription() {
		
		return "should not be directly used";
	}

}
