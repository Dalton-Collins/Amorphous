package affects;

import engine.GameState;
import engine.Minion;
import engine.Player;

public class RemoveAllAlliesSummoningSickness implements Affect{
	
	GameState gs;
	int damage;
	Minion owner;
	
	public RemoveAllAlliesSummoningSickness(GameState gss, Minion ownerr){
		gs = gss;
		owner = ownerr;
	}
	public void applyAffect() {
		owner.effect.activationsThisTurn+=1;
		for(Minion m : owner.owner.minions){
			m.removeSummoningSickness();
		}
		
	}
	@Override
	public String getDescription() {
		
		return "Remove Summoning Sickness from all of your active minions";
	}

}
