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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class BoardLayoutMaker {
	
	public BorderPane getLayout(){
		//layouts
        
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(5);
        
        HBox topFieldHBox = new HBox();
        topFieldHBox.setPadding(new Insets(15, 12, 15, 12));
        topFieldHBox.setSpacing(10);
        topFieldHBox.setStyle("-fx-background-color: #dbb1b4;");
        
        HBox bottomFieldHBox = new HBox();
        bottomFieldHBox.setPadding(new Insets(15, 12, 15, 12));
        bottomFieldHBox.setSpacing(10);
        bottomFieldHBox.setStyle("-fx-background-color: #9ec4ff;");
        
        HBox topHBox = new HBox();
        topHBox.setPadding(new Insets(15, 12, 15, 12));
        topHBox.setSpacing(10);
        topHBox.setStyle("-fx-background-color: #ed796a;");
        
        HBox bottomHBox = new HBox();
        bottomHBox.setPadding(new Insets(15, 12, 15, 12));
        bottomHBox.setSpacing(10);
        bottomHBox.setStyle("-fx-background-color: #5f8fe8;");
        
        VBox rightVBox = new VBox();
        rightVBox.setPadding(new Insets(15, 12, 15, 12));
        rightVBox.setSpacing(10);
        rightVBox.setStyle("-fx-background-color: #565656;");
        
        BorderPane border = new BorderPane();
        border.setTop(topHBox);
        border.setBottom(bottomHBox);
        border.setCenter(gridpane);
        border.setRight(rightVBox);
        
        gridpane.add(bottomFieldHBox, 0, 4);
        gridpane.add(topFieldHBox, 0, 0);
        return border;
	}
}
