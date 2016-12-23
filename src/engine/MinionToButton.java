package engine;
import javafx.scene.control.Button;

public class MinionToButton {
	
	fxDisplay fxd;
	
	public MinionToButton(fxDisplay fxdd){
		fxd = fxdd;
	}
	
	//this button is in the hand and can summon cards
	public Button convertForHand(Minion m){
		
		String cardText = "";
		cardText = cardText + m.name + " Cost " + m.cost + "\n \n \n " + 
		"ATK " + m.atk + "   HP " + m.health;
		CardButton cb = new CardButton(cardText);
		cb.minion = m;
		if(m.owner.id == 0){
			cb.setStyle("-fx-font: 20 arial; -fx-base: #2211ee;");
		}else{
			cb.setStyle("-fx-font: 20 arial; -fx-base: #ee1122;");
		}
		cb.setOnAction(fxd.summonHandler);
		cb.setOnMouseEntered(fxd.cardViewHandler);
		cb.setOnMouseExited(fxd.cardViewHandler);
		return cb;
	}
	
	//this button is on the field and can attack
	public Button convertForField(Minion m){
		
		String cardText = "";
		cardText = cardText + m.name + " Cost " + m.cost + "\n \n \n " + 
		"ATK " + m.atk + "   HP " + m.health;
		CardButton cb = new CardButton(cardText);
		cb.minion = m;
		if(m.owner.id == 0){
			cb.setStyle("-fx-font: 20 arial; -fx-base: #2211ee;");
		}else{
			cb.setStyle("-fx-font: 20 arial; -fx-base: #ee1122;");
		}
		
		cb.setOnAction(fxd.attackHandler);
		cb.setOnMouseEntered(fxd.cardViewHandler);
		cb.setOnMouseExited(fxd.cardViewHandler);
		return cb;
	}
	
	public Button convertForEffectSelection(Minion m){
		
		String cardText = "";
		cardText = cardText + m.name + " Cost " + m.cost + "\n \n \n " + 
		"ATK " + m.atk + "   HP " + m.health;
		CardButton cb = new CardButton(cardText);
		cb.minion = m;
		if(m.owner.id == 0){
			cb.setStyle("-fx-font: 20 arial; -fx-base: #e4fc6c;");
		}else{
			cb.setStyle("-fx-font: 20 arial; -fx-base: #e4fc6c;");
		}
		
		cb.setOnAction(fxd.affectSelectHandler);
		cb.setOnMouseEntered(fxd.cardViewHandler);
		cb.setOnMouseExited(fxd.cardViewHandler);
		return cb;
	}
	
	public Button convertForInaction(Minion m){
		String cardText = "";
		cardText = cardText + m.name + " Cost " + m.cost + "\n \n \n " + 
		"ATK " + m.atk + "   HP " + m.health;
		CardButton cb = new CardButton(cardText);
		cb.minion = m;
		cb.setStyle("-fx-font: 20 arial; -fx-base: #777b82;");
		return cb;
	}
}
