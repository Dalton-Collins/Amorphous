package engine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AttackHandler implements EventHandler<ActionEvent>{
	
	fxDisplay fxd;
	
	public AttackHandler(fxDisplay fxdd){
		fxd = fxdd;
	}
	
	@Override
	public void handle(ActionEvent event) {
		CardButton sourceButton = (CardButton)event.getSource();
		Minion toAttackWith = sourceButton.minion;
		if(toAttackWith.canAttack(target)){
			toAttackWith.attack(target);;
		}
		
		fxd.updateDisplay();
	}
}
