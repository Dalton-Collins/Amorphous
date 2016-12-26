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
		GameState.getGameState().affectStack.pauseProcessing = true;
		
		System.out.println("select and destroy here");
		GameState.getGameState().affectStack.afterSelectionAffect = new DestroyMinionAffect(owner);
		GameState.getGameState().fxd.affectSelection(this);
	}
	@Override
	public String getDescription() {
		
		return "You can select a minion to destroy";
	}

}
