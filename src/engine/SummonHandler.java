package engine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
public class SummonHandler implements EventHandler<ActionEvent>{
	
	fxDisplay fxd;
	
	public SummonHandler(fxDisplay fxdd){
		fxd = fxdd;
	}
	
	@Override
	public void handle(ActionEvent event) {
		CardButton sourceButton = (CardButton)event.getSource();
		Minion toSummon = sourceButton.minion;
		if(toSummon.canSummon()){
			toSummon.summon();
		}
		
		fxd.updateDisplay();
	}

}
