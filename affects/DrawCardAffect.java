package affects;

import engine.Minion;

public class DrawCardAffect implements Affect{
	Minion owner;
	
	public DrawCardAffect(Minion ownerr){
		owner = ownerr;
	}
	@Override
	public void applyAffect(){
		owner.effect.activationsThisTurn+=1;
		owner.owner.draw(1);
	}

	@Override
	public String getDescription() {
		
		return "Draw a card";
	}

}
