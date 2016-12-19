package engine;
import javafx.event.ActionEvent;

public class SummonEvent extends ActionEvent{

	private static final long serialVersionUID = 1L;
	
	Minion toSummon;
	String type;
	
	public SummonEvent(Minion m){
		type = "Summon";
		toSummon = m;
	}

}
