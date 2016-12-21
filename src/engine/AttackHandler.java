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
		//if a minion to attack has been selected, this allows you to choose the target
		if(fxd.selectingAttackTarget){
			CardButton sourceButton = (CardButton)event.getSource();
			Minion attackTarget = sourceButton.minion;
			if(fxd.attackingMinion.canAttack(attackTarget)){
				fxd.attackingMinion.attack(attackTarget);
				System.out.println("attacked");
			}
			//whether the attack was successful or not, reset the attacking minion
			//then update display
			fxd.selectingAttackTarget = false;
			fxd.attackingMinion = null;
			fxd.updateDisplay();
			
		//no minion has been selected to attack with, so this click selects the minion
		} else{
			CardButton sourceButton = (CardButton)event.getSource();
			Minion toAttackWith = sourceButton.minion;
			if(toAttackWith.owner != GameState.getGameState().turnPlayer){
				return;
			}
			fxd.selectingAttackTarget = true;
			fxd.attackingMinion = toAttackWith;
			System.out.println("selected");
		}
		
		
		fxd.updateDisplay();
	}
}
