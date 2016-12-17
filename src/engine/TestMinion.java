package engine;

public class TestMinion extends Minion{
	public TestMinion(Player ownerr, GameState gs, int idd){
		super(gs);
		id = idd;
		cost = 30;
		health = 3;
		atk = 2;
		type = "Humanoid";
		attacksThisTurn = 0;
		maxAttacks = 1;
		owner = ownerr;
		name = "TestMinion";
	}
	
}
