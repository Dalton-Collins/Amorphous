package engine;

import java.util.ArrayList;
import java.util.LinkedList;

//this class holds a list of affects that are to be applied
//in first in first out order, this requires a list because
//multiple affects can be triggered at once
public class AffectStack {
	
	LinkedList<Affect> affectsToProcess;
	boolean processing;
	public boolean pauseProcessing;
	public AfterSelectionAffect afterSelectionAffect;
	
	public AffectStack(){
		affectsToProcess = new LinkedList<Affect>();
		processing = false;
		pauseProcessing = false;
	}
	
	public void handleEvent(Event event){
		if(event.eventType == "resumeProcessing"){
			pauseProcessing = false;
			processing = false;
			assert(afterSelectionAffect != null);
			affectsToProcess.add(0, afterSelectionAffect);
		} else{
			for(Effect effect : GameState.getGameState().activeEffects.activeEffects){
				if(effect.trigger.isTriggered(event, effect.owner)){
					affectsToProcess.add(effect.affect);
					System.out.println("added affect triggered by: " + event.eventType);
				}
			}
		}
		if(!processing){
			while(!affectsToProcess.isEmpty() && !pauseProcessing){
				processing = true;
				//process the first affect
				Affect a = affectsToProcess.poll();
				System.out.println("applying effect");
				a.applyAffect();
			}
		}
		if(affectsToProcess.isEmpty()){
			processing = false;
		}
	}
	
}
