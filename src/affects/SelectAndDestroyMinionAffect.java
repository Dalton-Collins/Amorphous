package affects;

import engine.GameState;
import engine.Minion;

public class SelectAndDestroyMinionAffect implements Affect{
	
	Minion owner;
	public SelectAndDestroyMinionAffect(Minion ownerr){
		owner = ownerr;
	}
	@Override
	public void applyAffect() {
		owner.effect.activationsThisTurn+=1;
		GameState gs = GameState.getGameState();
		gs.affectStack.pauseProcessing = true;
		
		System.out.println("select and destroy called here");
		gs.affectStack.afterSelectionAffect = new DestroyMinionAffect(owner);
		gs.fxd.affectSelection(this);
	}
	@Override
	public String getDescription() {
		
		return "You can select a minion to destroy";
	}

}
