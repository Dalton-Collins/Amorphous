package affects;

import engine.AfterSelectionAffect;
import engine.GameState;
import engine.Minion;

public class DestroyMinionAffect implements AfterSelectionAffect{
	
	GameState gs;
	Minion target = null;
	Minion owner;
	
	public DestroyMinionAffect(GameState gss, Minion ownerr){
		gs = gss;
		owner = ownerr;
	}
	@Override
	public void applyAffect() {
		owner.effect.activationsThisTurn+=1;
		assert(target != null);
		target.destroy(owner);
		//set gamestate to the proper state
		gs.fxd.updateDisplay();
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
