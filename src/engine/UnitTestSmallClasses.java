package engine;

import org.junit.Test;

public class UnitTestSmallClasses {

	@Test
	public void test() {
		Server server = new Server();
		ServerThread serverThread = null;
		GameState gs = new GameState(serverThread, server);
		
		AffectStack as = new AffectStack(gs);
		Event e = new Event("noMatch");
		as.handleEvent(e);
		assert(!as.pauseProcessing && ! as.processing);
		assert(as.affectsToProcess.isEmpty());
	}

}
