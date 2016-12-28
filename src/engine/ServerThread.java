package engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread{
	Socket socket;
	TestGame tg;
	int id;
	
	ServerThread(Socket sockett){
		socket = sockett;
	}
	
	public void run(){
		try{
			String message = null;
			InputStreamReader isr = new InputStreamReader(socket.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			
			while((message = br.readLine()) != null){
				System.out.println("recieved this message: " + message);
				if(message.contains("game")){
					tg = TestServer.getTestServer().makeTestGame(this);
					tg.start(this);
				}else if(message.contains("join")){
					tg = TestServer.getTestServer().games.get(0);
					tg.connect(this);
					
				}else if(message.contains("mess")){
					tg.change(id);
				}
			}
			socket.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}

