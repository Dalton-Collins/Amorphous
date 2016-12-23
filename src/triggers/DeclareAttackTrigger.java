package triggers;

import engine.Event;
import engine.Minion;
import engine.Trigger;

//when the owner declares an attack
public class DeclareAttackTrigger implements Trigger{
	
	@Override
	public boolean isTriggered(Event e, Minion owner) {
		
		return (e.eventType == "declaredAttack" && e.m == owner);
	}

}
