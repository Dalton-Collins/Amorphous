package engine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SummonHandler{
	
	GameState gs;
	
	public SummonHandler(GameState gss){
		gs =gss;
	}
	
	@Override
	public void handle(GameCommand gc) {
		CardButton sourceButton = (CardButton)event.getSource();
		Minion toSummon = sourceButton.minion;
		if(toSummon.canSummon() && toSummon.owner == gs.turnPlayer){
			toSummon.summon();
		}
		if(!gs.selectingAffectTarget){
			gs.updateDisplays();
		}
	}

}
