package engine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AffectSelectHandler implements EventHandler<ActionEvent>{
	
	fxDisplay fxd;
	public AffectSelectHandler(fxDisplay fxdd){
		fxd = fxdd;
	}
	
	@Override
	public void handle(ActionEvent event) {

		Event e = new Event("resumeProcessing");
		CardButton target = (CardButton)event.getSource();
		e.m = target.minion;
		GameState.getGameState().affectStack.afterSelectionAffect.setTarget(target.minion);
		System.out.println("target is: " + target.minion.name);
		GameState.getGameState().selectingAffectTarget = false;
		GameState.getGameState().affectStack.handleEvent(e);
	}
	
}
