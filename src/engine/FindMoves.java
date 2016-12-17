package engine;

import java.util.ArrayList;

public class FindMoves {
	//returns a list of command objects that can be chosen from when making a move
	
	public ArrayList<Command> getMoves(GameState gs, Player p){
		ArrayList<Command> moves = new ArrayList<Command>();
		//check for possible attack options
		for(Minion m : gs.minions){
			for(Minion t : gs.minions){
				if(m.owner == p && m.canAttack(t)){
					Command move = new AttackCommand(m, t);
					moves.add(move);
				}
			}
		}
		//check for summon options
		for(Minion m : p.hand.cards){
			if(m.canSummon()){
				Command move = new SummonCommand(m);
				moves.add(move);
			}
		}
		return moves;
	}
}
