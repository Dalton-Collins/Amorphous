package engine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class CardViewHandler implements EventHandler<MouseEvent>{

	@Override
	public void handle(MouseEvent event) {
		if(GameState.getGameState().fxd.displayingDetailedCard){
			//remove the detailed card display
			GameState.getGameState().fxd.removeDetailedCard();
		}else{
			//display the detailed card
			CardButton cb = (CardButton)event.getSource();
			GameState.getGameState().fxd.displayDetailedCard(cb.minion);
		}
	}

}
