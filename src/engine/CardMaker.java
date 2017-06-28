package engine;

public interface CardMaker {
	
	Minion makeCard(Player owner);
	
	int getId();
	
	String getName();
	
}
