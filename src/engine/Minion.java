package engine;

import java.io.IOException;

import affects.DestroyMinionActionAffect;

//this class handles minions stats and interactions
public class Minion {
	public int id;
	public Long uniqueId;
	public String name;
	public int redCost;
	public int orangeCost;
	public int yellowCost;
	public int greenCost;
	public int blueCost;
	public int purpleCost;
	public int greyCost;
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
	
	GameState gs;
	
	public Minion(GameState gss, Player ownerr){
		gs = gss;
		owner  = ownerr;
	}
	//summons this minion to the field
	public void summon(){
		owner.hand.cards.remove(this);
		owner.minions.add(this);
		summoningSickness = true;
		payMana();
		
		if(effect != null){
			gs.activeEffects.addEffect(effect);
			System.out.println("added minion :" + name + "'s effect");
		}
		
		System.out.println("Minion " + id + " was summoned");
		gs.updateDisplays();
		
		//create and send out summon event
		Event e = new Event("summon");
		e.m = this;
		gs.affectStack.handleEvent(e);
	}
	//checks if the minion can be summoned
	//maybe have different summoning conditions in the future
	public boolean canSummon(){
		boolean enough = false;
		if(        owner.redMana >= redCost       && owner.orangeMana >= orangeCost
				&& owner.yellowMana >= yellowCost && owner.greenMana >= greenCost
				&& owner.blueMana >= blueCost     && owner.purpleMana >= purpleCost){
			enough = true;
		}
		return (enough && owner.totalMinions < 8);
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
		gs.affectStack.handleEvent(e);
		
		attacksThisTurn+=1;
		target.damageMinion(atk, this);
		damageMinion(target.atk, target);
		gs.affectStack.processStack();
		System.out.println("Minion " + id + " attacked minion " + target.id);
	}
	
	public void attack(Player target){
		Event e = new Event("declaredAttack");
		e.m = this;
		e.p2 = target;
		gs.affectStack.handleEvent(e);
		
		attacksThisTurn+=1;
		target.damagePlayer(atk, this);
		System.out.println("Minion " + id + " attacked player " + target.id);
	}
	
	public void destroy(Minion destroyer){
		owner.minions.remove(this);
		gs.activeEffects.removeEffect(effect);
		System.out.println("Minion " + id + " was destroyed");
		
		//create destroyed event
		Event e = new Event("minionDestroyed");
		e.m = this;//this was destroyed destroyer/m2
		e.m2 = destroyer;
		gs.affectStack.handleEvent(e);
	}
	
	public void removeSummoningSickness(){
		summoningSickness = false;
	}
	public void resetTurnAttacks(){
		attacksThisTurn = 0;
	}
	
	public void damageMinion(int damage, Minion damager){
		if(damage == 0){
			return;
		}
		if(damage < 0){
			healMinion(damage*-1, damager);
			return;
		}
		health-=damage;
		Event e = new Event("tookDamage");
		e.m = this;
		e.m2 = damager;
		e.amount = damage;
		gs.affectStack.handleEvent(e);
		
		if(health <1){
			DestroyMinionActionAffect dmaa = new DestroyMinionActionAffect(this, damager);
			gs.affectStack.addAction(dmaa);
		}
	}
	
	public void healMinion(int healing, Minion healer){
		health+=healing;
		if(health > maxHealth){
			health = maxHealth;
		}
		
		Event e = new Event("wasHealed");
		e.m = this;
		e.m2 = healer;
		e.amount = healing;
		gs.affectStack.handleEvent(e);
	}
	
	public void payMana(){
		owner.redMana -= redCost;
		owner.orangeMana -= orangeCost;
		owner.yellowMana -= yellowCost;
		owner.greenMana -= greenCost;
		owner.blueMana -= blueCost;
		owner.purpleMana -= purpleCost;
	}
	
	public void setCosts(int red, int orange, int yellow, int green, int blue, int purple){
		redCost = red;
		orangeCost = orange;
		yellowCost = yellow;
		greenCost = green;
		blueCost = blue;
		purpleCost = purple;
		
	}
}
