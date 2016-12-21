package engine;

public class Event {
	public int playerId;//who triggered the event or who's minion
	public Minion m;//minion that triggered event(such as summoned or activated effect)
	public Minion m2;//a minion that was affected by the event(such as got destroyed)
	public int mana;
	public String eventType; //description of what happened
	/* types include
	summon
	attack
	destroyed(this minion destroyed another)
	wasDestroyed
	spentMana
	
	*/
}
