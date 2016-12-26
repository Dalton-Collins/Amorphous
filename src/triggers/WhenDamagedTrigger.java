package triggers;

import engine.Event;
import engine.Minion;

public class WhenDamagedTrigger implements Trigger{

	@Override
	public boolean isTriggered(Event e, Minion owner) {
		
		return (e.eventType == "tookDamage" && e.m == owner 
				&& owner.effect.activationsThisTurn < owner.effect.maxActivationsPerTurn);
	}

	@Override
	public String getDescription() {
		
		return "When this minion is damaged:";
	}

}
