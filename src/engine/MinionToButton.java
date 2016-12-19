package engine;
import javafx.scene.control.Button;

public class MinionToButton {
	public Button convert(Minion m){
		
		String cardText = "";
		cardText = cardText + m.name + "\n \n \n \n " + 
		"ATK " + m.atk + "   HP " + m.health;
		Button b = new Button(cardText);
		b.setStyle("-fx-font: 20 arial; -fx-base: #2211ee;");
		return b;
	}
}
