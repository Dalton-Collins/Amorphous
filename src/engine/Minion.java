package engine;

//this class handles minions stats and interactions
public class Minion {
	int id;
	String name;
	int cost;
	int atk;
	int health;
	String type;// curent types: humanoid beast machine
	public Player owner;
	Effect effect;
	int attacksThisTurn = 0;
	int maxAttacks = 1;
	
	boolean summoningSickness;
	
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
			System.out.println("added minion :" + name + "'s effect");
		}
		
		System.out.println("Minion " + id + " was summoned");
		GameState.getGameState().fxd.updateDisplay();
		
		//create and send out summon event
		Event e = new Event("summon");
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
		Event e = new Event("declaredAttack");
		e.m = this;
		e.m2 = target;
		GameState.getGameState().affectStack.handleEvent(e);
		
		attacksThisTurn+=1;
		target.health = target.health - atk;
		health = health- target.atk;
		System.out.println("Minion " + id + " attacked minion " + target.id);
		if(target.health < 1){
			target.destroy(this);
		}
		if(health < 1){
			destroy(target);
		}
	}
	
	public void destroy(Minion destroyer){
		owner.minions.remove(this);
		System.out.println("Minion " + id + " was destroyed");
		
		//create destroyed event
		Event e = new Event("minionDestroyed");
		e.m = this;//this was destroyed destroyer/m2
		e.m2 = destroyer;
		GameState.getGameState().affectStack.handleEvent(e);
	}
	
	public void removeSummoningSickness(){
		summoningSickness = false;
	}
	public void resetTurnAttacks(){
		attacksThisTurn = 0;
	}
}
