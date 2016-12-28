package engine;
import ClientSide.CardButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SummonHandler implements EventHandler<ActionEvent>{
	
	GameState gs;
	
	public SummonHandler(GameState gss){
		gs =gss;
	}
	
	@Override
	public void handle(ActionEvent event) {
		CardButton sourceButton = (CardButton)event.getSource();
		Minion toSummon = sourceButton.minion;
		if(toSummon.canSummon() && toSummon.owner == gs.turnPlayer){
			toSummon.summon();
		}
		if(!gs.selectingAffectTarget){
			fxd.updateDisplay();
		}
	}

}
