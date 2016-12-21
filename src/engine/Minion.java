package engine;

public class Minion {
	int id;
	String name;
	int cost;
	int atk;
	int health;
	String type;
	public Player owner;
	Effect effect;
	
	boolean summoningSickness;
	int attacksThisTurn;
	int maxAttacks;
	
	public Minion(Player ownerr){
		owner  = ownerr;
	}
	//summons this minion to the field
	public void summon(){
		owner.hand.cards.remove(this);
		owner.minions.add(this);
		summoningSickness = true;
		owner.mana = owner.mana - cost;
		
		if(effect != null){
			GameState.getGameState().activeEffects.addEffect(effect);
		}
		
		System.out.println("Minion " + id + " was summoned");
		
		//create and send out event
		Event e = new Event();
		e.eventType = "summon";
		e.m = this;
		GameState.getGameState().affectStack.handleEvent(e);
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
		if(target.health < 1){
			target.destroy();
		}
		if(health < 1){
			destroy();
		}
	}
	
	public void destroy(){
		owner.minions.remove(this);
		System.out.println("Minion " + id + " was destroyed");
	}
	
	public void removeSummoningSickness(){
		summoningSickness = false;
	}
	public void resetTurnAttacks(){
		attacksThisTurn = 0;
	}
}
