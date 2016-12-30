package engine;

public class EndTurnHandler{
	
	GameState gs;
	
	public EndTurnHandler(GameState gss){
		gs = gss;
	}
	
	public void handle(GameCommand gc, ServerThread st) {
		if(gs.turnPlayerId == st.id){//this makes sure the player trying to end the turn
									// is the actual turnplayer
			gs.nextTurn();
			gs.selectingAffectTarget = false;
			gs.affectStack.processing = false;
			gs.affectStack.pauseProcessing = false;
			gs.affectStack.afterSelectionAffect = null;
			
			gs.updateDisplays();
		}
	}

}
