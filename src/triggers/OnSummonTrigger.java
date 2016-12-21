package triggers;

import engine.Event;
import engine.Minion;
import engine.Trigger;

public class OnSummonTrigger implements Trigger{
	
	//effects need to get removed from the active effects
	//when they use an onsummon trigger
	//not yet implemented
	@Override
	public boolean isTriggered(Event e, Minion owner) {
		return (e.m == owner && e.eventType == "summon");
	}
	
}
