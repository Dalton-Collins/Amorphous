package engine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DirectAttackHandler implements EventHandler<ActionEvent>{
	
	fxDisplay fxd;
	
	public DirectAttackHandler(fxDisplay fxdd){
		fxd = fxdd;
	}
	
	@Override
	public void handle(ActionEvent event) {
		//if a minion to attack has been selected, this allows you to choose the target
		GameState gs = GameState.getGameState();
		if(gs.selectingAttackTarget){
			CardButton sourceButton = (CardButton)event.getSource();
			Player attackTarget = sourceButton.p;
			if(gs.attackingMinion.canAttack(attackTarget)){
				gs.attackingMinion.attack(attackTarget);
				System.out.println("attacked");
			}
			//whether the attack was successful or not, reset the attacking minion
			//then update display
			gs.selectingAttackTarget = false;
			gs.attackingMinion = null;
			fxd.updateDisplay();
			
		//no minion has been selected to attack with, so this click selects the minion
		} else{
			CardButton sourceButton = (CardButton)event.getSource();
			Minion toAttackWith = sourceButton.minion;
			if(toAttackWith.owner != GameState.getGameState().turnPlayer){
				return;
			}
			gs.selectingAttackTarget = true;
			gs.attackingMinion = toAttackWith;
			System.out.println("selected");
		}
		
		
		fxd.updateDisplay();
	}
}