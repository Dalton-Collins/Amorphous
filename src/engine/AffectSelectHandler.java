package engine;

//this is used when an affect that requires target selection is activated
//it addes the destroy minion affect to the stack and starts processing
public class AffectSelectHandler{
	
	GameState gs;
	
	public AffectSelectHandler(GameState gss){
		gs = gss;
	}
	
	public void handle(GameCommand gc, ServerThread st) {
		
		if(st.id == gs.serverThread1.id){//if player 1 sent this command
			Event e = new Event("resumeProcessing");
			int field = 0;
			if(gc.displayMinion1.fieldLocation == 1){
				field = 1;
			}
			Minion target = gs.players.get(field).minions.get(gc.displayMinion1.cardPosition);
			e.m = target;
			gs.affectStack.afterSelectionAffect.setTarget(target);
			System.out.println("target is: " + target.name);
			gs.selectingAffectTarget = false;
			gs.affectStack.handleEvent(e);
		}else if(st.id == gs.serverThread2.id){//if player 2
			Event e = new Event("resumeProcessing");
			int field = 1;
			if(gc.displayMinion1.fieldLocation == 1){
				field = 0;
			}
			Minion target = gs.players.get(field).minions.get(gc.displayMinion1.cardPosition);
			e.m = target;
			gs.affectStack.afterSelectionAffect.setTarget(target);
			System.out.println("target is: " + target.name);
			gs.selectingAffectTarget = false;
			gs.affectStack.handleEvent(e);
		}
	}
	
}
