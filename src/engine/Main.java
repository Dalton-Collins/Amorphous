package engine;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	public static void main(String args[]){
		launch(args);
		/*
		while(true){
			if(GS.turnPlayer.canDraw(1)){
				GS.turnPlayer.draw(1);
			}
			ArrayList<Command> moves;
			moves = fm.getMoves(GS, GS.turnPlayer);
			while(!moves.isEmpty()){
				System.out.println("Choose a move from " + moves.size());
				int i = reader.nextInt();
				if(i == -1){
					break;
				}
				GS.doCommand(moves.get(i));
				moves = fm.getMoves(GS, GS.turnPlayer);
			}
			
			GS.nextTurn();
		}
		*/
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		fxDisplay fxd = new fxDisplay();
		fxd.start(primaryStage);
	}
}
