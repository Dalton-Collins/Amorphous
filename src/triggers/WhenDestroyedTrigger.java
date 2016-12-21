package triggers;

import engine.Event;
import engine.Minion;
import engine.Trigger;

//triggers when the minion owning this trigger is destroyed
public class WhenDestroyedTrigger implements Trigger{

	@Override
	public boolean isTriggered(Event e, Minion owner) {

		return (e.eventType == "minionDestroyed" && e.m == owner);
	}
	
}
