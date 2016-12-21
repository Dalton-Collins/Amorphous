package engine;

import java.util.ArrayList;
import java.util.LinkedList;

//this class holds a list of affects that are to be applied
//in first in first out order, this requires a list because
//multiple affects can be triggered at once
public class AffectStack {
	
	LinkedList<Affect> affectsToProcess;
	boolean processing;
	
	public AffectStack(){
		affectsToProcess = new LinkedList<Affect>();
		processing = false;
	}
	
	public void handleEvent(Event event){
		for(Effect effect : GameState.getGameState().activeEffects.activeEffects){
			if(effect.trigger.isTriggered(event, effect.owner)){
				affectsToProcess.add(effect.affect);
			}
		}
		if(!processing){
			while(!affectsToProcess.isEmpty()){
				processing = true;
				//process the first affect
				Affect a = affectsToProcess.poll();
				a.applyAffect();
			}
		}
		if(affectsToProcess.isEmpty()){
			processing = false;
		}
	}
	
}
