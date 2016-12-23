package engine;

//minions can have effects which have trigger conditions and affects
//that change the game
public class Effect {
	int effectId;
	Minion owner;
	Trigger trigger;//this is the object we ask to check if the affect will be activated
	Affect affect;//this will change the game when activated
	int activationsPerTurn;
	
	public Effect(Minion ownerr, Trigger trig, Affect aff, int actsperturn){
		owner = ownerr;
		trigger = trig;
		affect = aff;
		activationsPerTurn = actsperturn;
	}
}
