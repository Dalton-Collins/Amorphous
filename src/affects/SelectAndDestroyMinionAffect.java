package affects;

import engine.Affect;
import engine.GameState;
import engine.Minion;

public class SelectAndDestroyMinionAffect implements Affect{
	
	Minion owner;
	public SelectAndDestroyMinionAffect(Minion ownerr){
		owner = ownerr;
	}
	@Override
	public void applyAffect() {
		GameState.getGameState().affectStack.pauseProcessing = true;
		
		System.out.println("select and destroy here");
		GameState.getGameState().affectStack.afterSelectionAffect = new DestroyMinionAffect(owner);
		GameState.getGameState().fxd.affectSelection(this);
	}

}
