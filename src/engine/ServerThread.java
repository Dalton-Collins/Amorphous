package engine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerThread extends Thread{
	
	Server server;
	Socket socket;
	GameState gs;
	int id;
	
	ServerThread(Socket sockett, Server serverr){
		socket = sockett;
		server = serverr;
	}
	
	public void run(){
		try{
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			GameCommand gc;
			
			while((gc = (GameCommand)ois.readObject()) != null){
				System.out.println("recieved this command: " + gc.commandType);
				if(gc.commandType.equals("update")){
					System.out.println("server updating clients");
					gs.updateDisplays();
				}else if(gc.commandType.equals("makeGame")){
					System.out.println("making new game");
					gs = server.makeNewGame(this);
				}else if(gc.commandType.equals("joinGame")){
					System.out.println("connecting player to game");
					server.connectToGame(gc.n, this);
				}else if(gc.commandType.equals("summon")){
					gs.summonHandler.handle(gc, this);
				}else if(gc.commandType.equals("endTurn")){
					gs.endTurnHandler.handle(gc, this);
				}else if(gc.commandType.equals("attack")){
					gs.attackHandler.handle(gc, this);
				}
			}
			socket.close();
		} catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
	}
}

