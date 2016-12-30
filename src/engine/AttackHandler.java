package engine;

public class AttackHandler{
	
	GameState gs;
	
	public AttackHandler(GameState gss){
		gs = gss;
	}

	public void handle(GameCommand gc, ServerThread st) {
		
		if(st.id == gs.serverThread1.id){//if player 1 sent this command
			Minion attackingMinion = gs.players.get(0).minions.get(gc.displayMinion1.cardPosition);
			Minion target = gs.players.get(1).minions.get(gc.displayMinion2.cardPosition);
			if(attackingMinion.canAttack(target)){
				attackingMinion.attack(target);
				gs.updateDisplays();
			}
		}else if(st.id == gs.serverThread2.id){//if player 2
			Minion attackingMinion = gs.players.get(1).minions.get(gc.displayMinion1.cardPosition);
			Minion target = gs.players.get(0).minions.get(gc.displayMinion2.cardPosition);
			if(attackingMinion.canAttack(target)){
				attackingMinion.attack(target);
				gs.updateDisplays();
			}
		}
	}
}
