package engine;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;

 
public class fxDisplay extends Application {
	
	GameState gs;
	MinionToButton minionToButton;
	Stage primaryStage;
	SummonHandler summonHandler;
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStagee) {
    	
    	//Set Handlers
    	
    	summonHandler = new SummonHandler(this);
    	
    	//initialize
    	gs = new GameState();
    	minionToButton = new MinionToButton(this);
    	
    	primaryStage = primaryStagee;
    	primaryStage.setTitle("Amorphous");
    	
    	//layouts
        StackPane titleLayout = new StackPane();
        
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
        gridpane.setVgap(9);
    	Scene boardScene = new Scene(gridpane, 1000, 800);
    	
    	//update hands
    	int i = 0;
    	for(Minion m : gs.players.get(0).hand.cards){
    		Button card = minionToButton.convert(m);
    		gridpane.add(card, i, 60);
    		
    		i++;
    	}
    	
    	int i2 = 0;
    	for(Minion m : gs.players.get(1).hand.cards){
    		Button card = minionToButton.convert(m);
    		gridpane.add(card, i2, 0);
    		
    		i2++;
    	}
    	//update field
    	int j = 0;
    	for(Minion m: gs.players.get(0).minions){
    		Button card = minionToButton.convert(m);
    		gridpane.add(card, j, 40);
    		
    		j++;
    	}
    	
    	int j2 = 0;
    	for(Minion m: gs.players.get(1).minions){
    		Button card = minionToButton.convert(m);
    		gridpane.add(card, j2, 20);
    		
    		j2++;
    	}
    	
    	primaryStage.setScene(boardScene);
    }
}
