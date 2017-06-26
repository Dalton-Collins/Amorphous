package engine;

public class AttackHandler{
	
	GameState gs;
	
	public AttackHandler(GameState gss){
		gs = gss;
	}

	public void handle(GameCommand gc, ServerThread st) {
		
		System.out.println("engine attack handler called ");
		
		Minion attackingMinion = null;
		Minion target = null;
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
		for(Minion m : gs.players.get(0).minions){
			if(m.uniqueId.equals(gc.displayMinion2.uniqueId)){
				target = m;
			}
		}
		for(Minion m : gs.players.get(1).minions){
			if(m.uniqueId.equals(gc.displayMinion2.uniqueId)){
				target = m;
			}
		}
		assert(attackingMinion != null);
		assert(target != null);
		
		if(st.id == gs.serverThread1.id){//if player 1 sent this command
			//check if minion can attack, and that the player who sent the attack owns the minion
			if(attackingMinion.canAttack(target) && attackingMinion.owner.id == gs.players.get(0).id){
				attackingMinion.attack(target);
				gs.updateDisplays();
			}
		}else if(st.id == gs.serverThread2.id && attackingMinion.owner.id == gs.players.get(1).id){//if player 2
			if(attackingMinion.canAttack(target)){
				attackingMinion.attack(target);
				gs.updateDisplays();
			}
		}
		
		System.out.println("engine attack handler completed");
	}
}
