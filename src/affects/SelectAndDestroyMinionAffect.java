package affects;

import engine.GameState;
import engine.Minion;

public class SelectAndDestroyMinionAffect implements Affect{
	//this affect is part of a minions effect
	//pauses the stack and sends the client a message asking for input
	
	GameState gs;
	public Minion owner;
	public SelectAndDestroyMinionAffect(GameState gss, Minion ownerr){
		gs = gss;
		owner = ownerr;
	}
	@Override
	public void applyAffect() {
		//owner.effect.activationsThisTurn+=1;
		gs.affectStack.pauseProcessing = true;
		gs.selectingAffectTarget = true;
		
		System.out.println("select and destroy called here");
		gs.affectStack.afterSelectionAffect = new DestroyMinionAffect(gs, owner);
		gs.selectUpdateDisplays(this);
	}
	@Override
	public String getDescription() {
		
		return "You can select a minion to destroy";
	}

}
