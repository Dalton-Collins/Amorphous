package triggers;

import engine.Event;
import engine.Minion;
import engine.Trigger;

public class OnSummonTrigger implements Trigger{

	@Override
	public boolean isTriggered(Event e, Minion owner) {
		return (e.m == owner && e.eventType == "summon");
	}
	
}
