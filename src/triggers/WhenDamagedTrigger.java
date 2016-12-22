package triggers;

import engine.Event;
import engine.Minion;
import engine.Trigger;

public class WhenDamagedTrigger implements Trigger{

	@Override
	public boolean isTriggered(Event e, Minion owner) {
		
		return (e.eventType == "tookDamage" && e.m == owner);
	}

}
