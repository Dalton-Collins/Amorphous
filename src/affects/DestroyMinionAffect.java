package affects;

import engine.AfterSelectionAffect;
import engine.GameState;
import engine.Minion;

public class DestroyMinionAffect implements AfterSelectionAffect{
	
	Minion target = null;
	Minion owner;
	
	public DestroyMinionAffect(Minion ownerr){
		owner = ownerr;
	}
	@Override
	public void applyAffect() {
		System.out.println("destroy called here");
		assert(target != null);
		target.destroy(owner);
		//set gamestate to the proper state
		GameState.getGameState().fxd.updateDisplay();
	}
	@Override
	public Minion getTarget() {
		
		return target;
	}
	@Override
	public void setTarget(Minion targett) {
		target = targett;
	}

}
