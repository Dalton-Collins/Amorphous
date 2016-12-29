package affects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

import engine.DisplayGameState;
import engine.ServerThread;

public class ServerOutputThread extends Thread{
	
	ServerThread serverThread;
	Socket socket;
	
	ServerOutputThread(Socket sockett, ServerThread svrthrd) throws IOException{
		serverThread = svrthrd;
		socket = sockett;
	}
	
	@SuppressWarnings("static-access")
	public void run(DisplayGameState dgs){
		
		while(true){
			try {
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(dgs);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}