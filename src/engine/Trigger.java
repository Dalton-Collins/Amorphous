package engine;
//this is used to determine if the AFFECT will be activated or not
public interface Trigger {
	
	public boolean isTriggered(Event e, Minion owner);
}
