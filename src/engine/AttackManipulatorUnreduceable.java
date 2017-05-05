package engine;

public class AttackManipulatorUnreduceable implements AttackManipulator{
	
	int atk;

	@Override
	public int getAttack() {
		
		return atk;
	}

	@Override//used when this attack is specifically targeting a minion
	public int getAttack(Minion m) {
		
		return atk;
	}

	@Override//used when this attack is specifically targeting a player
	public int getAttack(Player P) {
		
		return atk;
	}

	@Override
	public void changeAttack(Integer change) {
		if(change <1){
			return;
		}else{
			atk+=change;
		}
		
	}

	@Override
	public void setAttack(Integer change) {
		if(change < atk){
			return;
		}else{
			atk = change;
		}
		
	}
	
	@Override
	public String getDescription(){
		return "This minions attack cannot be reduced";
	}

}
