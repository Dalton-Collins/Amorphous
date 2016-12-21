package engine;

import java.util.ArrayList;
import java.util.LinkedList;

//this class holds a list of affects that are to be applied
//in first in first out order, this requires a list because
//multiple affects can be triggered at once
public class AffectStack {
	
	LinkedList<Affect> affectsToProcess;
	
	public AffectStack(){
		affectsToProcess = new LinkedList<Affect>();
	}
	
	public void handleEvent(Event event){
		for(Effect effect : GameState.getGameState().activeEffects.activeEffects){
			if(effect.trigger.isTriggered(event, effect.owner)){
				affectsToProcess.add(effect.affect);
			}
		}
		
		while(!affectsToProcess.isEmpty()){
			//process the first affect
			Affect a = affectsToProcess.poll();
			a.applyAffect();
		}
	}
	
}
