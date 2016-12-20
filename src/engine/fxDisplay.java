package engine;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

 
public class fxDisplay extends Application {
	
	GameState gs;
	MinionToButton minionToButton;
	Stage primaryStage;
	SummonHandler summonHandler;
	AttackHandler attackHandler;
	EndTurnHandler endTurnHandler;
	BoardLayoutMaker boardLayoutMaker;
	
	//display states
	boolean selectingAttackTarget = false;
	Minion attackingMinion;
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStagee) {
    	
    	//Set Handlers
    	
    	summonHandler = new SummonHandler(this);
    	attackHandler = new AttackHandler(this);
    	endTurnHandler = new EndTurnHandler(this);
    	
    	//initialize
    	gs = new GameState();
    	minionToButton = new MinionToButton(this);
    	
    	primaryStage = primaryStagee;
    	primaryStage.setTitle("Amorphous");
    	
    	boardLayoutMaker = new BoardLayoutMaker();
    	
    	
    	//layouts
        BorderPane boardLayout = boardLayoutMaker.getLayout();
        
        StackPane titleLayout = new StackPane();
        //scenes
        Scene titleScreen = new Scene (titleLayout, 1000, 800);
        Scene boardScene = new Scene (boardLayout, 1000, 800);
        
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
    	
    	BorderPane boardLayout = boardLayoutMaker.getLayout();
    	Scene boardScene = new Scene(boardLayout, 1000, 800);
    	
    	Button endTurn = new Button();
        endTurn.setText("End Turn");
        endTurn.setOnAction(endTurnHandler);
        ((VBox)boardLayout.getRight()).getChildren().add(endTurn);
    	//update hands
    	for(Minion m : gs.players.get(0).hand.cards){
    		Button card = minionToButton.convertForHand(m);
    		HBox bottomHBox = (HBox) boardLayout.getBottom();
    		bottomHBox.getChildren().add(card);
    	}
    	
    	for(Minion m : gs.players.get(1).hand.cards){
    		Button card = minionToButton.convertForHand(m);
    		HBox topHBox = (HBox) boardLayout.getTop();
    		topHBox.getChildren().add(card);
    		
    	}
    	//update field
    	for(Minion m: gs.players.get(0).minions){
    		Button card = minionToButton.convertForField(m);
    		GridPane gridPane = (GridPane) boardLayout.getCenter();
    		HBox bottomFieldHBox = (HBox) gridPane.getChildren().get(0);
    		bottomFieldHBox.getChildren().add(card);
    		
    	}
    	
    	for(Minion m: gs.players.get(1).minions){
    		Button card = minionToButton.convertForField(m);
    		GridPane gridPane = (GridPane) boardLayout.getCenter();
    		HBox topFieldHBox = (HBox) gridPane.getChildren().get(1);
    		topFieldHBox.getChildren().add(card);
    		
    	}
    	
    	primaryStage.setScene(boardScene);
    }
}
