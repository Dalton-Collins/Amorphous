package engine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EndTurnHandler implements EventHandler<ActionEvent>{
	
	GameState gs;
	
	public EndTurnHandler(GameState gss){
		gs = gss;
	}
	@Override
	public void handle(ActionEvent event) {
		gs.nextTurn();
		gs.selectingAffectTarget = false;
		gs.affectStack.processing = false;
		gs.affectStack.pauseProcessing = false;
		gs.affectStack.afterSelectionAffect = null;
		fxd.updateDisplay();
	}

}
