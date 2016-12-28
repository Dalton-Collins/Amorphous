package engine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

//this is the object that will handle the game itself
//once both players connect the game can start
public class GameThread extends Thread{
	
	Socket p1Socket;
	Socket p2Socket;
	
	boolean p1Connected = false;
	boolean p2Connected = false;
	
	GameThread(Socket p1Sockett){
		p1Socket = p1Sockett;
		p1Connected = true;
	}
	
	public void start(){
		try{
			
			
			
			//wait for a connection from p2;
			while(!p1Connected || !p2Connected){
				//wait
			}
			//start game
			GameState gs = new GameState();
			
			ObjectInputStream p1InputStream = new ObjectInputStream(p1Socket.getInputStream());
			ObjectInputStream p2InputStream = new ObjectInputStream(p2Socket.getInputStream());
			
			p1Socket.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void connectPlayer(Socket p2Sockett){
		p2Socket = p2Sockett;
		p2Connected = true;
	}
}