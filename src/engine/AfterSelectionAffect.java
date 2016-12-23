package engine;

import affects.Affect;

public interface AfterSelectionAffect extends Affect{
	Minion getTarget();
	void setTarget(Minion target);
}
