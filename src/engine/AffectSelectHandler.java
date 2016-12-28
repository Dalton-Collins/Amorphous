package engine;

public class AffectSelectHandler{
	
	GameState gs;
	
	public AffectSelectHandler(GameState gss){
		gs = gss;
	}
	
	@Override
	public void handle(ActionEvent event) {

		Event e = new Event("resumeProcessing");
		e.m = target.minion;
		gs.affectStack.afterSelectionAffect.setTarget(target.minion);
		System.out.println("target is: " + target.minion.name);
		gs.selectingAffectTarget = false;
		gs.affectStack.handleEvent(e);
	}
	
}
