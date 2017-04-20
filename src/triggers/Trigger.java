package triggers;

import engine.Event;
import engine.Minion;

//this is used to determine if the AFFECT will be activated or not
public interface Trigger {
	
	public boolean isTriggered(Event e, Minion owner);
	public String getDescription();
}
