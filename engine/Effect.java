package engine;

import triggers.Trigger;
import affects.Affect;

//minions can have effects which have trigger conditions and affects
//that change the game
public class Effect {
	int effectId;
	Minion owner;
	Trigger trigger;//this is the object we ask to check if the affect will be activated
	Affect affect;//this will change the game when activated
	public int maxActivationsPerTurn = 1;
	public int activationsThisTurn = 0;
	
	public Effect(Minion ownerr, Trigger trig, Affect aff, int actsperturn){
		owner = ownerr;
		trigger = trig;
		affect = aff;
		maxActivationsPerTurn = actsperturn;
		assert(owner != null);
		assert(trigger != null);
		assert(affect != null);
	}
}
