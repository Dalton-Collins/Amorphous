package engine;

public interface AfterSelectionAffect extends Affect{
	Minion getTarget();
	void setTarget(Minion target);
}
