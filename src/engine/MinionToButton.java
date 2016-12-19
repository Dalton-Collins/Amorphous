package engine;
import javafx.scene.control.Button;

public class MinionToButton {
	
	fxDisplay fxd;
	
	public MinionToButton(fxDisplay fxdd){
		fxd = fxdd;
	}
	public Button convertForHand(Minion m){
		
		String cardText = "";
		cardText = cardText + m.name + m.id + "\n \n \n " + 
		"ATK " + m.atk + "   HP " + m.health;
		CardButton cb = new CardButton(cardText, m);
		cb.setStyle("-fx-font: 20 arial; -fx-base: #2211ee;");
		cb.setOnAction(fxd.summonHandler);
		return cb;
	}
	
public Button convertForField(Minion m){
		
		String cardText = "";
		cardText = cardText + m.name + m.id + "\n \n \n " + 
		"ATK " + m.atk + "   HP " + m.health;
		CardButton cb = new CardButton(cardText, m);
		cb.setStyle("-fx-font: 20 arial; -fx-base: #2211ee;");
		cb.setOnAction(fxd.summonHandler);
		return cb;
	}
}
