package triggers;

import engine.Event;
import engine.Minion;

//triggers when the minion owning this trigger is destroyed
public class WhenDestroyedTrigger implements Trigger{

	@Override
	public boolean isTriggered(Event e, Minion owner) {

		return (e.eventType == "minionDestroyed" && e.m == owner);
	}

	@Override
	public String getDescription() {
		
		return "When this minion is destroyed:";
	}
	
}
