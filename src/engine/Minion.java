package engine;

public class Minion {
	int id;
	int cost;
	int atk;
	int health;
	String type;
	Player owner;
	
	Effect effect;
	
	//summons a minion to the field
	public void summon(Player p, GameState gs){
		p.hand.cards.remove(this);
		gs.minions.add(this);
		//trigger summoned event
	}
	//checks if the minion can be summoned
	//maybe have different summoning conditions in the future
	public boolean canSummon(Player p){
		return (p.mana >= cost && p.totalMinions < 10);
	}
}
