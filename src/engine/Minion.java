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
	public void summon(){
		owner.hand.cards.remove(this);
		owner.minions.add(this);
		summoningSickness = true;
		System.out.println("Minion " + id + " was summoned");
		//trigger summoned event
	}
	//checks if the minion can be summoned
	//maybe have different summoning conditions in the future
	public boolean canSummon(){
		return (owner.mana >= cost && owner.totalMinions < 10);
	}
	
	public boolean canAttack(Minion target){
		if(atk < 1 || summoningSickness || (attacksThisTurn >= maxAttacks) || target.owner == owner){
			return false;
		}
		return true;
	}
	
	public void attack(Minion target){
		attacksThisTurn+=1;
		target.health = target.health - atk;
		health = health- target.atk;
		System.out.println("Minion " + id + " attacked minion " + target.id);
	}
	
	public void destroy(){
		owner.minions.remove(this);
		System.out.println("Minion " + id + " was destroyed");
	}
	
	public void removeSummoningSickness(){
		summoningSickness = false;
	}
}
