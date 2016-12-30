package engine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

//this is used when an affect that requires target selection is activated
public class AffectSelectHandler{
	
	GameState gs;
	
	public AffectSelectHandler(GameState gss){
		gs = gss;
	}
	
	public void handle(GameCommand gc, ServerThread st) {
		
		if(st.id == gs.serverThread1.id){//if player 1 sent this command
			Event e = new Event("resumeProcessing");
			Minion target = gs.players.get(0).minions.get(gc.displayMinion1.cardPosition);
			e.m = target;
			gs.affectStack.afterSelectionAffect.setTarget(target);
			System.out.println("target is: " + target.name);
			gs.selectingAffectTarget = false;
			gs.affectStack.handleEvent(e);
		}else if(st.id == gs.serverThread2.id){//if player 2
			
		}
	}
	
}
