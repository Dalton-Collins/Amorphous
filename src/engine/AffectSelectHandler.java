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
		//start the affect loop again and give it
		//the information on the target of the effect
		Event e = new Event("resumeProcessing");
		CardButton target = (CardButton)event.getSource();
		e.m = target.minion;
	}
	
}
