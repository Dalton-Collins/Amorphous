package engine;

public class Commander extends Minion{

	GameState gs;
	String manaPattern; // example of a pattern string "RRB", for Red Red Blue mana
	Boolean inCommandZone = true;
	
	public Commander(GameState gss, Player ownerr, Long uniqueid) {
		super(gss, ownerr, uniqueid);
		gs = gss;
	}
	
	@Override
	public void summon(){
		assert(inCommandZone);
		inCommandZone = false;
		owner.minions.add(this);
		summoningSickness = true;
		payMana();
		
		if(effect != null){
			gs.activeEffects.addEffect(effect);
			//System.out.println("added minion :" + name + "'s effect");
		}
		
		//System.out.println("Minion " + id + " was summoned");
		gs.updateDisplays();
		
		//create and send out summon event
		Event e = new Event("summon");
		e.m = this;
		gs.affectStack.handleEvent(e);
	}
	
	@Override
	public boolean canSummon(){
		boolean enough = false;
		if(        owner.redMana >= redCost       && owner.orangeMana >= orangeCost
				&& owner.yellowMana >= yellowCost && owner.greenMana >= greenCost
				&& owner.blueMana >= blueCost     && owner.purpleMana >= purpleCost){
			enough = true;
		}
		return (enough && (owner.totalMinions < 8) && inCommandZone);
	}
	
	@Override
	public void destroy(Minion destroyer){
		owner.minions.remove(this);
		inCommandZone = true;
		gs.activeEffects.removeEffect(effect);
		//System.out.println("Minion " + id + " was destroyed");
		
		//create destroyed event
		Event e = new Event("commanderDestroyed");
		e.m = this;//this was destroyed destroyer/m2
		e.m2 = destroyer;
		gs.affectStack.handleEvent(e);
	}

}
