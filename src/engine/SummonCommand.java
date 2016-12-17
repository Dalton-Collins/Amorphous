package engine;

public class SummonCommand extends Command{
	
	Minion toSummon;
	
	public SummonCommand(Minion m){
		type = "Summon";
		toSummon = m;
	}
}
