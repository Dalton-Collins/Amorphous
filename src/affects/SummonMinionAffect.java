package affects;

import engine.GameState;
import engine.Minion;

public class SummonMinionAffect implements Affect{
	
	GameState gs;
	int damage;
	Minion owner;
	
	String name;
	int minionId;
	
	public SummonMinionAffect(GameState gss, Minion ownerr, int minionIdd, String namee){
		gs = gss;
		owner = ownerr;
		minionId = minionIdd;
		name = namee;
		
	}
	public void applyAffect() {
		owner.effect.activationsThisTurn+=1;
		Minion m = gs.CDB.getMinion(minionId, owner.owner);
		m.forceSummon();
	}
	@Override
	public String getDescription() {
		
		return "Summon " + name;
	}

}
