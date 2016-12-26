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
		owner.effect.activationsThisTurn+=1;
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
	@Override
	public String getDescription() {
		
		return "Not to be directly used, use Select and Destroy instead";
	}

}
