package engine;
//This is the super class for the large number of effects that will be created
public abstract class Effect {
	int effectId;
	int triggerType;
	Trigger trigger;//this is the object we ask to see if the affect will be activated
	Affect affect;//this will change the game when activated
}
