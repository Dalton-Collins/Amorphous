package triggers;

import engine.Event;
import engine.Minion;
import engine.Trigger;

public class WhenAttackedTrigger implements Trigger{

	@Override
	public boolean isTriggered(Event e, Minion owner) {
		
		return (e.eventType == "declaredAttack" && e.m2 == owner);
	}

}
