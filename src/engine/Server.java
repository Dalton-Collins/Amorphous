package engine;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
	
	public static final int PORT = 9082;
	public static boolean acceptConnections;
	
	Integer threadId;
	Integer gameId;
	
	Database database;
	Connection c;
	
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
		
		database = new Database();
		c = database.getConnection();
		database.createAccountsTable(c);
		//database.InsertAccount(c, "0000000001", "a", "a", "1", "100000", "0");
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
			//Does the server never close threads?
		}
		serverSocket.close();
	}
	
	static Server getServer(){ //Is this ever used? It seems unnecessary.
		return self;
	}
	
	GameState makeNewGame(ServerThread st){
		GameState gs = new GameState(st, this);
		st.gs = gs;
		games.put(gameId, gs);
		
		GameIdentifier gi = new GameIdentifier(gameId, "game " + gameId);
		gs.gameIdentifier = gi;
		joinableGames.add(gi);
		
		System.out.println("Made new game: "+gameId);
		gameId+=1;
		return gs;
	}
	
	void connectToGame(Integer id, ServerThread st){
		GameState gs = games.get(id);
		gs.initGameState(st);
		st.gs = gs;
		joinableGames.remove(gs.gameIdentifier);
	}
	
	void closeGame(Integer id){
		games.remove(id);
	}
	
	void sendGamesList(ServerThread st){
		safeWriteObject(st,joinableGames);
		System.out.println("sent games list with: " + joinableGames.size() + " size");
	}
	
	void concede(ServerThread st, GameState gs){
		if(st == gs.serverThread1){
			gs.winner = 1;
		}else{
			gs.winner = 2;
		}
		gs.updateDisplays();
		games.remove(gs);
	}
	
	void tryLogin(GameCommand gc, ServerThread st){
		//try to log the player in here
		ArrayList<Object> results = database.selectByAttribute(c, "Accounts", "ACCOUNTNAME", gc.s1, "PASSWORD");
		String password = null;
		if(results.size() > 0){
			password = (String) results.get(0);
		}else{
			System.out.println("account not found");
			safeWriteObject(st,"loginFailed");
			return; //Exits function if the user is not found in the database
		}
		if(password.equals(gc.s2)){
			st.accountName = gc.s1;
			st.loggedIn = true;
			System.out.println("login successful");
			safeWriteObject(st,"loginSuccessful");
		}else{
			System.out.println("incorrect Password");
			safeWriteObject(st,"loginFailed");
		}
	}
	void tryRegister(GameCommand gc, ServerThread st){ ///WIP!!!
		//Attempt to register a new account
		String username=gc.s1;
		String password=gc.s2;
		System.out.println("Starting register: "+username);
		ArrayList<Object> results = database.selectByAttribute(c, "Accounts", "ACCOUNTNAME", username, "PASSWORD");
		if(results.size() > 0){
			System.out.println("User already exists!");
			safeWriteObject(st,"registerFailedUserAlreadyExists");
			return; //Exit function if register failed
		}else{
			ArrayList<Object> res = database.selectByAttribute(c, "Accounts", "ID", "0000000000", "PASSWORD");
			String id="";
			for(int i=0;res.size()>0;i++){
				id=String.format("%010d", i);
				res = database.selectByAttribute(c, "Accounts", "ID", id, "PASSWORD");
				//System.out.println(res.size()+":"+id);
			}
			System.out.println("Username available: \""+username+"\" with ID: "+id);
			database.InsertAccount(c, id, username, password, "1", "100000", "0");
			safeWriteObject(st,"registerSuccessful");
			st.accountName=username;
			st.loggedIn=true;
			safeWriteObject(st,"loginSuccessful");
		}
	}
	void safeWriteObject(ServerThread st, Object o){
		try {
			st.oos.writeObject(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
