package engine;

public class Minion {
	int id;
	String name;
	int cost;
	int atk;
	int health;
	String type;
	Player owner;
	
	Affect effect;
	boolean summoningSickness;
	int attacksThisTurn;
	int maxAttacks;
	GameState gs;
	
	public Minion(GameState gss){
		gs = gss;
	}
	//summons a minion to the field
	public void summon(Player p){
		p.hand.cards.remove(this);
		gs.minions.add(this);
		//trigger summoned event
	}
	//checks if the minion can be summoned
	//maybe have different summoning conditions in the future
	public boolean canSummon(Player p){
		return (p.mana >= cost && p.totalMinions < 10);
	}
	
	public boolean canAttack(Minion target){
		if(atk < 1 || summoningSickness || (attacksThisTurn >= maxAttacks) || target.owner == owner){
			return false;
		}
		return true;
	}
	
	public void attack(Minion target){
		target.health = target.health - atk;
		health = health- target.atk;
		
	}
	
	public void destroy(){
		gs.minions.remove(this);
	}
}
