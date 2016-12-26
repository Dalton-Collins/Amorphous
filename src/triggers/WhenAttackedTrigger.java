package triggers;

import engine.Event;
import engine.Minion;

public class WhenAttackedTrigger implements Trigger{

	@Override
	public boolean isTriggered(Event e, Minion owner) {
		
		return (e.eventType == "declaredAttack" && e.m2 == owner 
				&& owner.effect.activationsThisTurn < owner.effect.maxActivationsPerTurn);
	}

	@Override
	public String getDescription() {
		
		return "When a minion declares an attack on this minion:";
	}

}
