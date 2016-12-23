package engine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class CardViewHandler implements EventHandler<MouseEvent>{

	@Override
	public void handle(MouseEvent arg0) {
		// TODO Auto-generated method stub
		GameState.getGameState().fxd.displayDetailedCard(boardStack, m);
	}

}
