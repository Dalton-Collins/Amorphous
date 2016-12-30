package affects;

import java.io.IOException;

import engine.DisplayGameState;
import engine.GameCommand;
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
		if(owner.owner == gs.players.get(0)){//if the affect activating belongs to player 1
			DisplayGameState dgs = gs.getUpdatedDisplayGameState(gs.players.get(0), gs.players.get(1));
			dgs.selectingAffectTarget = true;
			try {
				gs.oos1.writeObject(dgs);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			DisplayGameState dgs = gs.getUpdatedDisplayGameState(gs.players.get(1), gs.players.get(0));
			dgs.selectingAffectTarget = true;
			try {
				gs.oos2.writeObject(dgs);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public String getDescription() {
		
		return "You can select a minion to destroy";
	}

}
