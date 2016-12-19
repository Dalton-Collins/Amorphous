package engine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
public class SummonHandler implements EventHandler<ActionEvent>{
	
	GameState gs;
	
	public SummonHandler(GameState gss){
		gs =gss;
	}
	
	@Override
	public void handle(ActionEvent event) {
		SummonEvent se = (SummonEvent)event;
		if(se.toSummon.canSummon()){
			se.toSummon.summon();
		}
	}

}
