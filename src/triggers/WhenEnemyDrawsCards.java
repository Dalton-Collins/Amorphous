package triggers;

import engine.Event;
import engine.Minion;

public class WhenEnemyDrawsCards implements Trigger{
	
	int cardsRequired;//number of cards required to be drawn at once
	
	public WhenEnemyDrawsCards(int required){
		assert(required > 0);
		cardsRequired = required;
	}
	
	@Override
	public boolean isTriggered(Event e, Minion owner) {

		return (e.amount >= cardsRequired && e.p != owner.owner 
				&& owner.effect.activationsThisTurn < owner.effect.maxActivationsPerTurn);
	}

	@Override
	public String getDescription() {
		if(cardsRequired == 1){
			return "When an enemy draws " + cardsRequired + " card:";
		}
		return "When an enemy draws " + cardsRequired + " cards:";
	}

}
