package engine;

//this class handles minions stats and interactions
public class Minion {
	public int id;
	public String name;
	public int cost;
	public int atk;
	public int baseAtk;
	public int health;
	public int maxHealth;
	public String type;// curent types: humanoid beast machine
	public Player owner;
	public Effect effect;
	public int attacksThisTurn = 0;
	public int maxAttacks = 1;
	
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
	
	public boolean canAttack(Player target){
		if(atk < 1 || summoningSickness || (attacksThisTurn >= maxAttacks) || target == owner){
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
		target.damageMinion(atk, this);
		damageMinion(target.atk, target);
		System.out.println("Minion " + id + " attacked minion " + target.id);
	}
	
	public void attack(Player target){
		Event e = new Event("declaredAttack");
		e.m = this;
		e.p2 = target;
		GameState.getGameState().affectStack.handleEvent(e);
		
		attacksThisTurn+=1;
		target.damagePlayer(atk, this);
		System.out.println("Minion " + id + " attacked player " + target.id);
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
	
	void damageMinion(int damage, Minion damager){
		health-=damage;
		Event e = new Event("tookDamage");
		e.m = this;
		e.m2 = damager;
		e.amount = damage;
		GameState.getGameState().affectStack.handleEvent(e);
		
		if(health <1){
			destroy(damager);
		}
	}
	
	void healMinion(int healing, Minion healer){
		health+=healing;
		if(health > maxHealth){
			health = maxHealth;
		}
		
		Event e = new Event("wasHealed");
		e.m = this;
		e.m2 = healer;
		e.amount = healing;
		GameState.getGameState().affectStack.handleEvent(e);
	}
}
