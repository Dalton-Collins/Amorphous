package engine;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

 
public class fxDisplay extends Application {
	
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
    	GameState.getGameState().initGameState();
    	
    	minionToButton = new MinionToButton(this);
    	
    	primaryStage = primaryStagee;
    	primaryStage.setTitle("Amorphous");
    	
    	boardLayoutMaker = new BoardLayoutMaker();
    	
    	
    	//layouts
        BorderPane boardLayout = boardLayoutMaker.getLayout();
        
        StackPane titleLayout = new StackPane();
        //scenes
        Scene titleScreen = new Scene (titleLayout, 1200, 1000);
        Scene boardScene = new Scene (boardLayout, 1200, 1000);
        
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
    	//create new scene
    	BorderPane boardLayout = boardLayoutMaker.getLayout();
    	Scene boardScene = new Scene(boardLayout, 1200, 1000);
    	
    	//buttons 
    	Button endTurn = new Button();
        endTurn.setText("End Turn");
        endTurn.setOnAction(endTurnHandler);
        ((VBox)boardLayout.getRight()).getChildren().add(endTurn);
        
        //labels
        //update mana/life
        Label p2Mana = new Label();
        p2Mana.setText("Mana: " + GameState.getGameState().players.get(1).mana);
        p2Mana.setFont(new Font("Arial", 30));
        p2Mana.setTextFill(Color.web("#ff38c3"));
        ((VBox)boardLayout.getLeft()).getChildren().add(p2Mana);
        
        Label p2Life = new Label();
        p2Life.setText("Life: " + GameState.getGameState().players.get(1).life);
        p2Life.setFont(new Font("Arial", 30));
        p2Life.setTextFill(Color.web("#ff5e5e"));
        ((VBox)boardLayout.getLeft()).getChildren().add(p2Life);
        
        Label p1Life = new Label();
        p1Life.setText("Life: " + GameState.getGameState().players.get(0).life);
        p1Life.setFont(new Font("Arial", 30));
        p1Life.setTextFill(Color.web("#3891ff"));
        ((VBox)boardLayout.getLeft()).getChildren().add(p1Life);
        
        Label p1Mana = new Label();
        p1Mana.setText("Mana: " + GameState.getGameState().players.get(0).mana);
        p1Mana.setFont(new Font("Arial", 30));
        p1Mana.setTextFill(Color.web("#38d0ff"));
        ((VBox)boardLayout.getLeft()).getChildren().add(p1Mana);
        
    	//update hands
    	for(Minion m : GameState.getGameState().players.get(0).hand.cards){
    		Button card = minionToButton.convertForHand(m);
    		HBox bottomHBox = (HBox) boardLayout.getBottom();
    		bottomHBox.getChildren().add(card);
    	}
    	
    	for(Minion m : GameState.getGameState().players.get(1).hand.cards){
    		Button card = minionToButton.convertForHand(m);
    		HBox topHBox = (HBox) boardLayout.getTop();
    		topHBox.getChildren().add(card);
    		
    	}
    	//update field
    	for(Minion m: GameState.getGameState().players.get(0).minions){
    		Button card = minionToButton.convertForField(m);
    		GridPane gridPane = (GridPane) boardLayout.getCenter();
    		HBox bottomFieldHBox = (HBox) gridPane.getChildren().get(0);
    		bottomFieldHBox.getChildren().add(card);	
    		
    	}
    	
    	for(Minion m: GameState.getGameState().players.get(1).minions){
    		Button card = minionToButton.convertForField(m);
    		GridPane gridPane = (GridPane) boardLayout.getCenter();
    		HBox topFieldHBox = (HBox) gridPane.getChildren().get(1);
    		topFieldHBox.getChildren().add(card);
    		
    	}
    	primaryStage.setScene(boardScene);
    }
}
