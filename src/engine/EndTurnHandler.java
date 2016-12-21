package engine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EndTurnHandler implements EventHandler<ActionEvent>{
	
	fxDisplay fxd;
	public EndTurnHandler(fxDisplay fxdd){
		fxd = fxdd;
	}
	@Override
	public void handle(ActionEvent event) {
		GameState.getGameState().nextTurn();
		fxd.updateDisplay();
	}

}
