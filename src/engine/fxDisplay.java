package engine;

import affects.Affect;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

 
public class fxDisplay extends Application {
	
	Stage primaryStage;
	StackPane mainStack;
	
	BoardLayoutMaker boardLayoutMaker;
	MinionToButton minionToButton;
	
	SummonHandler summonHandler;
	AttackHandler attackHandler;
	EndTurnHandler endTurnHandler;
	AffectSelectHandler affectSelectHandler;
	DirectAttackHandler directAttackHandler;
	CardViewHandler cardViewHandler;
	
	//display states
	boolean selectingAttackTarget = false;
	boolean selectingAffectTarget = false;
	boolean displayingDetailedCard = false;
	
	Text detailedCard;
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
    	affectSelectHandler = new AffectSelectHandler(this);
    	directAttackHandler = new DirectAttackHandler(this);
    	cardViewHandler = new CardViewHandler();
    	
    	//initialize
    	GameState.getGameState().initGameState(this);
    	
    	minionToButton = new MinionToButton(this);
    	
    	primaryStage = primaryStagee;
    	primaryStage.setTitle("Amorphous");
    	
    	boardLayoutMaker = new BoardLayoutMaker();
    	
    	
    	//layouts
        StackPane boardLayout = boardLayoutMaker.getLayout();
        
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
    	StackPane boardStack = boardLayoutMaker.getLayout();
    	mainStack = boardStack;
    	BorderPane boardLayout = (BorderPane) boardStack.getChildren().get(0);
    	Scene boardScene = new Scene(boardStack, 1200, 1000);
    	
    	//buttons 
    	setEndTurnButton(boardLayout);
        
        //labels
        //update mana/life
        setManaLife(boardLayout);
        
    	//update hands
        setHandCards(boardLayout);
    	
    	//update field
    	setFieldCards(boardLayout);
    	primaryStage.setScene(boardScene);
    }
    
    public void affectSelection(Affect a){
    	System.out.println("enter affect selection");
    	selectingAffectTarget = true;
    	
    	StackPane boardStack = boardLayoutMaker.getLayout();
    	mainStack = boardStack;
    	BorderPane boardLayout = (BorderPane) boardStack.getChildren().get(0);
    	Scene boardScene = new Scene(boardStack, 1200, 1000);
    	
        setEndTurnButton(boardLayout);
        
        Button cancelEffectSelection = new Button();
        cancelEffectSelection.setText("Cancel Effect");
        cancelEffectSelection.setOnAction(new EventHandler<ActionEvent>(){
        	public void handle(ActionEvent event) {
        		GameState gs = GameState.getGameState();
        		selectingAffectTarget = false;
        		gs.affectStack.processing = false;
        		gs.affectStack.pauseProcessing = false;
        		gs.affectStack.afterSelectionAffect = null;
        		updateDisplay();
        	}
        });
        ((VBox)boardLayout.getLeft()).getChildren().add(cancelEffectSelection);
        
        //labels
        //update mana/life
        setManaLife(boardLayout);
        
    	//update hands
        setHandCardsInactive(boardLayout);
    	
    	//update field
    	setEffectSelectionCards(boardLayout);

    	primaryStage.setScene(boardScene);
    }
    
    void displayDetailedCard(Minion m){
    	Text card = new Text();
    	String cardText = "";
    	cardText+= m.name + "    " + m.cost + "\n\n"
    	+ m.effect.trigger.getDescription() + "\n"
    	+ m.effect.affect.getDescription() + "\n\nATK "
    	+ m.atk + "     HP " + m.health;
    	
    	card.setWrappingWidth(200);
    	card.setText(cardText);
    	card.setStyle("-fx-font: 25 arial;");
        
    	detailedCard = card;
    	displayingDetailedCard = true;
    	mainStack.getChildren().add(card);
    }
    
    void removeDetailedCard(){
    	mainStack.getChildren().remove(detailedCard);
    	detailedCard = null;
    	displayingDetailedCard = false;
    }
    
    void setEndTurnButton(BorderPane boardLayout){
    	Button endTurn = new Button();
        endTurn.setText("End Turn");
        endTurn.setFont(new Font("Arial", 18));
        endTurn.setOnAction(endTurnHandler);
        ((VBox)boardLayout.getRight()).getChildren().add(endTurn);
    }
    
    void setPlayerDamageButton(BorderPane boardLayout, Player p){
    	CardButton attackPlayer = new CardButton("      Direct Attack      ");
    	attackPlayer.p = p;
    	attackPlayer.setFont(new Font("Arial", 30));
    	attackPlayer.setOnAction(directAttackHandler);
    	GridPane grid = (GridPane)boardLayout.getCenter();
    	if(p.id == 0){
    		attackPlayer.setStyle("-fx-font: 20 arial; -fx-base: #2211ee;");
    		grid.add(attackPlayer, 0, 4);
    	} else{
    		attackPlayer.setStyle("-fx-font: 20 arial; -fx-base: #ee1122;");
    		grid.add(attackPlayer, 0, 0);
    	}
    }
    
    void setFieldCards(BorderPane boardLayout){
    	if(GameState.getGameState().players.get(0).minions.isEmpty()){
    		setPlayerDamageButton(boardLayout, GameState.getGameState().players.get(0));
    	}
    	if(GameState.getGameState().players.get(1).minions.isEmpty()){
    		setPlayerDamageButton(boardLayout, GameState.getGameState().players.get(1));
    	}
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
    }
    
    void setEffectSelectionCards(BorderPane boardLayout){
    	for(Minion m: GameState.getGameState().players.get(0).minions){
    		Button card = minionToButton.convertForEffectSelection(m);
    		GridPane gridPane = (GridPane) boardLayout.getCenter();
    		HBox bottomFieldHBox = (HBox) gridPane.getChildren().get(0);
    		bottomFieldHBox.getChildren().add(card);	
    		
    	}
    	
    	for(Minion m: GameState.getGameState().players.get(1).minions){
    		Button card = minionToButton.convertForEffectSelection(m);
    		GridPane gridPane = (GridPane) boardLayout.getCenter();
    		HBox topFieldHBox = (HBox) gridPane.getChildren().get(1);
    		topFieldHBox.getChildren().add(card);
    		
    	}
    }
    
    void setHandCards(BorderPane boardLayout){
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
    }
    
    void setHandCardsInactive(BorderPane boardLayout){
    	for(Minion m : GameState.getGameState().players.get(0).hand.cards){
    		Button card = minionToButton.convertForInaction(m);
    		HBox bottomHBox = (HBox) boardLayout.getBottom();
    		bottomHBox.getChildren().add(card);
    	}
    	
    	for(Minion m : GameState.getGameState().players.get(1).hand.cards){
    		Button card = minionToButton.convertForInaction(m);
    		HBox topHBox = (HBox) boardLayout.getTop();
    		topHBox.getChildren().add(card);
    		
    	}
    }
    
    void setManaLife(BorderPane boardLayout){
    	Label p2Mana = new Label();
        p2Mana.setText("Mana: " + GameState.getGameState().players.get(1).mana);
        p2Mana.setFont(new Font("Arial", 25));
        p2Mana.setTextFill(Color.web("#ff38c3"));
        ((VBox)boardLayout.getLeft()).getChildren().add(p2Mana);
        
        Label p2Life = new Label();
        p2Life.setText("Life: " + GameState.getGameState().players.get(1).life);
        p2Life.setFont(new Font("Arial", 25));
        p2Life.setTextFill(Color.web("#ff5e5e"));
        ((VBox)boardLayout.getLeft()).getChildren().add(p2Life);
        
        Label p1Life = new Label();
        p1Life.setText("Life: " + GameState.getGameState().players.get(0).life);
        p1Life.setFont(new Font("Arial", 25));
        p1Life.setTextFill(Color.web("#3891ff"));
        ((VBox)boardLayout.getLeft()).getChildren().add(p1Life);
        
        Label p1Mana = new Label();
        p1Mana.setText("Mana: " + GameState.getGameState().players.get(0).mana);
        p1Mana.setFont(new Font("Arial", 25));
        p1Mana.setTextFill(Color.web("#38d0ff"));
        ((VBox)boardLayout.getLeft()).getChildren().add(p1Mana);
    }
}
