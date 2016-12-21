package engine;

public class Event {
	int playerId;//who triggered the event or who's minion
	Minion m;//minion that triggered event(such as summoned or activated effect)
	Minion m2;//a minion that was affected by the event(such as got destroyed)
	int mana;
	String eventType; //description of what happened
	/* types include
	summon
	attack
	destroyed(this minion destroyed another)
	wasDestroyed
	spentMana
	
	*/
}
