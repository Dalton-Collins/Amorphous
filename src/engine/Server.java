package engine;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
	
	public static final int PORT = 9082;
	public static boolean acceptConnections;
	
	Integer threadId;
	Integer gameId;
	
	HashMap<Integer, ServerThread> threads;
	HashMap<Integer, Socket> outputSockets;
	HashMap<Integer, GameState> games;
	
	ArrayList<GameIdentifier> joinableGames;
	
	private static Server self = new Server();
	
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		acceptConnections = true;
		self.runServer();
	}
	
	public void runServer() throws IOException, ClassNotFoundException{
		
		threads = new HashMap<Integer, ServerThread>();
		outputSockets = new HashMap<Integer, Socket>();
		games = new HashMap<Integer, GameState>();
		joinableGames = new ArrayList<GameIdentifier>();
		
		threadId = 0;
		gameId = 0;
		
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server running...");
		
		//this creates new threads for each client to use
		while(acceptConnections){
			Socket inputSocket = serverSocket.accept();
			ServerThread serverThread = new ServerThread(inputSocket, this);
			Socket outputSocket = serverSocket.accept();
			outputSockets.put(threadId, outputSocket);
			serverThread.oos = new ObjectOutputStream(outputSocket.getOutputStream());
			
			serverThread.id = threadId;
			threads.put(threadId, serverThread);
			serverThread.start();
			System.out.println("Accepted new client# " + threadId);
			threadId+=1;
		}
		serverSocket.close();
	}
	
	static Server getServer(){
		return self;
	}
	
	GameState makeNewGame(ServerThread st){
		GameState gs = new GameState(st, this);
		st.gs = gs;
		gs.id = gameId;
		games.put(gameId, gs);
		
		GameIdentifier gi = new GameIdentifier(gameId, "game " + gameId);
		joinableGames.add(gi);
		
		gameId+=1;
		return gs;
	}
	
	void connectToGame(Integer id, ServerThread st){
		GameState gs = games.get(id);
		gs.initGameState(st);
		st.gs = gs;
	}
	
	void closeGame(Integer id){
		games.remove(id);
	}
	
	void sendGamesList(ServerThread st){
		try {
			st.oos.writeObject(joinableGames);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
