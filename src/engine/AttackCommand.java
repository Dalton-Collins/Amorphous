package engine;

public class AttackCommand extends Command{
	
	Minion attacker;
	Minion target;
	
	public AttackCommand(Minion attackerr, Minion targett){
		type = "Attack";
		attacker = attackerr;
		target = targett;
	}
}
