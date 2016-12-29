package engine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AffectSelectHandler implements EventHandler<ActionEvent>{
	
	GameState gs;
	
	public AffectSelectHandler(GameState gss){
		gs = gss;
	}
	
	public void handle(ActionEvent event) {

		Event e = new Event("resumeProcessing");
		e.m = target.minion;
		gs.affectStack.afterSelectionAffect.setTarget(target.minion);
		System.out.println("target is: " + target.minion.name);
		gs.selectingAffectTarget = false;
		gs.affectStack.handleEvent(e);
	}
	
}
