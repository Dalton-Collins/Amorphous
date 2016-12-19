package engine;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

 
public class fxDisplay extends Application {
	
	GameState gs;
	MinionToButton mtb;
	Stage primaryStage;
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStagee) {
    	primaryStage = primaryStagee;
    	primaryStage.setTitle("Amorphous");
    	//layouts
        StackPane titleLayout = new StackPane();
        
        StackPane boardLayout = new StackPane();
        
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        
        //scenes
        Scene titleScreen = new Scene (titleLayout, 1000, 800);
        Scene boardScene = new Scene (gridpane, 1000, 800);
        
        //buttons
        Button strtbtn = new Button();
        strtbtn.setText("Start Game");
        strtbtn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
            	gs = new GameState();
            	mtb = new MinionToButton();
            	primaryStage.setScene(boardScene);
            	updateDisplay();
            }
        });
        
        //layout buttons
        titleLayout.getChildren().add(strtbtn);
        
        primaryStage.setScene(titleScreen);
        primaryStage.show();
        
    }
    
    public void updateDisplay(){
    	GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);
    	Scene boardScene = new Scene(gridpane, 1000, 800);
    	
    	//update hands
    	for(Minion m : gs.players.get(0).hand.cards){
    		Button card = mtb.convert(m);
    		GridPane.setHalignment(card, HPos.CENTER);
    		gridpane.add(card, 0, 15);
    	}
    	//update field
    	
    	primaryStage.setScene(boardScene);
    }
}
