package engine;

import java.util.ArrayList;

//this class holds a list of effects that are currently triggerable
public class ActiveEffects {
	
	ArrayList<Effect> activeEffects;
	
	public void addEffect(Effect e){
		activeEffects.add(e);
	}
	public void removeEffect(Effect e){
		activeEffects.remove(e);
	}
	public ActiveEffects(){
		activeEffects = new ArrayList<Effect>();
	}
	
}
