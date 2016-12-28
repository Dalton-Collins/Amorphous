package affects;

import engine.GameState;
import engine.Minion;

public class SelectAndDestroyMinionAffect implements Affect{
	
	GameState gs;
	Minion owner;
	public SelectAndDestroyMinionAffect(GameState gss, Minion ownerr){
		gs = gss;
		owner = ownerr;
	}
	@Override
	public void applyAffect() {
		owner.effect.activationsThisTurn+=1;
		gs.affectStack.pauseProcessing = true;
		
		System.out.println("select and destroy called here");
		gs.affectStack.afterSelectionAffect = new DestroyMinionAffect(gs, owner);
		gs.fxd.affectSelection(this);
	}
	@Override
	public String getDescription() {
		
		return "You can select a minion to destroy";
	}

}
