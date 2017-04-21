package triggers;

import engine.Event;
import engine.Minion;

//when the owner declares an attack
public class PassiveTrigger implements Trigger{
	
	@Override
	public boolean isTriggered(Event e, Minion owner) {
		
		return false;
	}

	@Override
	public String getDescription() {
		
		return "Passive Effect:";
	}

}
