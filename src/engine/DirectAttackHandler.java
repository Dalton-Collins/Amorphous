package engine;

public class DirectAttackHandler{
	
	GameState gs;
	
	public DirectAttackHandler(GameState gss){
		gs = gss;
	}
	
	public void handle(GameCommand gc, ServerThread st) {
		
		if(st.id == gs.serverThread1.id){//if player 1 sent this command
			Minion attackingMinion = gs.players.get(0).minions.get(gc.displayMinion1.cardPosition);
			Player target = gs.players.get(1);
			if(attackingMinion.canAttack(target)){
				attackingMinion.attack(target);
				gs.updateDisplays();
			}
		}else if(st.id == gs.serverThread2.id){//if player 2
			Minion attackingMinion = gs.players.get(1).minions.get(gc.displayMinion1.cardPosition);
			Player target = gs.players.get(0);
			if(attackingMinion.canAttack(target)){
				attackingMinion.attack(target);
				gs.updateDisplays();
			}
		}
	}
}