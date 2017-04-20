package affects;

import engine.Minion;

public class DamageMinionActionAffect implements Affect{
	
	Minion target;
	Minion damager;
	int damage;
	
	public DamageMinionActionAffect(Minion targ, Minion damagerr, int damagee){
		target = targ;
		damager = damagerr;
		damage = damagee;
	}
	@Override
	public void applyAffect() {
		target.damageMinion(damage, damager);
		
	}

	@Override
	public String getDescription() {
		
		return "should not be directly used";
	}

}