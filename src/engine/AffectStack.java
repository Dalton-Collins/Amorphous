package engine;

import java.util.ArrayList;

//this class holds a list of affects that are to be applied
//in first in first out order, this requires a list because
//multiple affects can be triggered at once
public class AffectStack {
	
	GameState gs;
	ArrayList<Affect> affectsToProcess;
	
	public AffectStack(GameState gss){
		gs = gss;
		affectsToProcess = new ArrayList<Affect>();
	}
	
	public void handleEvent(Event event){
		for(Effect effect : gs.activeEffects.activeEffects){
			if(effect.trigger.isTriggered(event, effect.owner)){
				
			}
		}
	}
	
}
