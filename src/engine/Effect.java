package engine;

//minions can have effects which have trigger conditions and affects
//that change the game
public class Effect {
	int effectId;
	Trigger trigger;//this is the object we ask to check if the affect will be activated
	Affect affect;//this will change the game when activated
	
	public Effect(Trigger trig, Affect aff){
		trigger = trig;
		affect = aff;
	}
}
