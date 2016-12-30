package engine;

public class SummonHandler{
	
	GameState gs;
	
	public SummonHandler(GameState gss){
		gs =gss;
	}
	
	public void handle(GameCommand gc, ServerThread st) {
		if(st.id == gs.serverThread1.id){
			Minion toSummon = gs.players.get(0).hand.cards.get(gc.displayMinion1.cardPosition);
			if(toSummon.canSummon() && toSummon.owner == gs.turnPlayer){
				toSummon.summon();
			}
		}else if(st.id == gs.serverThread2.id){
			Minion toSummon = gs.players.get(1).hand.cards.get(gc.displayMinion1.cardPosition);
			if(toSummon.canSummon() && toSummon.owner == gs.turnPlayer){
				toSummon.summon();
			}
		}
		if(!gs.selectingAffectTarget){
			gs.updateDisplays();
		}
	}

}
