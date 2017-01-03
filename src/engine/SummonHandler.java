package engine;

public class SummonHandler{
	
	GameState gs;
	
	public SummonHandler(GameState gss){
		gs =gss;
	}
	
	public void handle(GameCommand gc, ServerThread st) {
		Minion toSummon = null;
		for(Minion m : gs.players.get(0).hand.cards){
			if(m.uniqueId.equals(gc.displayMinion1.uniqueId)){
				toSummon = m;
			}
		}
		for(Minion m : gs.players.get(1).hand.cards){
			if(m.uniqueId.equals(gc.displayMinion1.uniqueId)){
				toSummon = m;
			}
		}
		assert(toSummon != null);
		if(st.id == gs.serverThread1.id){
			if(toSummon.canSummon() && toSummon.owner == gs.turnPlayer){
				toSummon.summon();
			}
		}else if(st.id == gs.serverThread2.id){
			if(toSummon.canSummon() && toSummon.owner == gs.turnPlayer){
				toSummon.summon();
			}
		}
		if(!gs.selectingAffectTarget){
			gs.updateDisplays();
		}
	}

}
