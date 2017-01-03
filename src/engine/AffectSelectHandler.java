package engine;

//this is used when an affect that requires target selection is activated
//it addes the destroy minion affect to the stack and starts processing
public class AffectSelectHandler{
	
	GameState gs;
	
	public AffectSelectHandler(GameState gss){
		gs = gss;
	}
	
	public void handle(GameCommand gc, ServerThread st) {
		
		Minion target = null;
		for(Minion m : gs.players.get(0).minions){
			if(m.uniqueId.equals(gc.displayMinion1.uniqueId)){
				target = m;
			}
		}
		for(Minion m : gs.players.get(1).minions){
			if(m.uniqueId.equals(gc.displayMinion1.uniqueId)){
				target = m;
			}
		}
		assert(target != null);
		if(st.id == gs.serverThread1.id){//if player 1 sent this command
			Event e = new Event("resumeProcessing");
			e.m = target;
			gs.affectStack.afterSelectionAffect.setTarget(target);
			System.out.println("target is: " + target.name);
			gs.selectingAffectTarget = false;
			System.out.println("affect select handler completed here");
			gs.affectStack.handleEvent(e);
			
		}else if(st.id == gs.serverThread2.id){//if player 2
			Event e = new Event("resumeProcessing");
			e.m = target;
			gs.affectStack.afterSelectionAffect.setTarget(target);
			System.out.println("target is: " + target.name);
			gs.selectingAffectTarget = false;
			System.out.println("affect select handler completed here");
			gs.affectStack.handleEvent(e);
		}
	}
	
}
