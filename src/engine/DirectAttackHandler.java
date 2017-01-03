package engine;

public class DirectAttackHandler{
	
	GameState gs;
	
	public DirectAttackHandler(GameState gss){
		gs = gss;
	}
	
	public void handle(GameCommand gc, ServerThread st) {
		Minion attackingMinion = null;
		for(Minion m : gs.players.get(0).minions){
			if(m.uniqueId.equals(gc.displayMinion1.uniqueId)){
				attackingMinion = m;
			}
		}
		for(Minion m : gs.players.get(1).minions){
			if(m.uniqueId.equals(gc.displayMinion1.uniqueId)){
				attackingMinion = m;
			}
		}
		assert(attackingMinion != null);
		if(st.id == gs.serverThread1.id){//if player 1 sent this command
			Player target = gs.players.get(1);
			if(attackingMinion.canAttack(target)){
				attackingMinion.attack(target);
				gs.updateDisplays();
			}
		}else if(st.id == gs.serverThread2.id){//if player 2
			Player target = gs.players.get(0);
			if(attackingMinion.canAttack(target)){
				attackingMinion.attack(target);
				gs.updateDisplays();
			}
		}
	}
}