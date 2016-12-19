package engine;
import javafx.scene.control.Button;

public class CardButton extends Button{
	Minion minion;
	public CardButton(String text, Minion mn){
		super(text);
		minion = mn;
	}
}
