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
		
		GameState.getGameState().affectStack.afterSelectionAffect = new DestroyMinionAffect();
		GameState.getGameState().fxd.affectSelection(this);
	}

}
