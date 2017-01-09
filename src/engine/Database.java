package engine;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database{
	public Connection getConnection(){
		
	    Connection c = null;
	    try{
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:Amorphous.db");
	    	System.out.println("Opened database successfully");
	    	return c;
	    }catch ( Exception e ){
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	    System.out.println("failed to open database");
	    return null;
	}
	
	public void createAccountsTable(Connection c){
	    Statement stmt = null;
	    try{
	    	Class.forName("org.sqlite.JDBC");
	    	System.out.println("Trying to make accounts table");
	    	DatabaseMetaData meta = c.getMetaData();
	    	ResultSet res = meta.getTables(null, null, "ACCOUNTS", null);
	    	if(res.next()){
	    		res.close();
	    		//Statement stmnt = c.createStatement();
	    		//String sqll ="DROP TABLE IF EXISTS ITEMS";
	    		//stmnt.executeUpdate(sqll);
	    		System.out.println("Accounts table aready exists");
	    		return;
	    	}
	    	stmt = c.createStatement();
	    	String sql = "CREATE TABLE ACCOUNTS " +
	    			"(ID    BLOB PRIMARY KEY NOT NULL," +
	    			" ACCOUNTNAME   BLOB     NOT NULL, " +
	    			" PASSWORD      BLOB     NOT NULL, " +
	    			" CARDBANK      BLOB     NOT NULL, " +
	    			" GOLD          BLOB     NOT NULL, " +
	    			" FRIENDSLISTID   BLOB) ";
	    	stmt.executeUpdate(sql);
	    	stmt.close();
	    	System.out.println("Table created successfully");
	    } catch ( Exception e ) {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	}
	
	public void InsertAccount(Connection c,  String ID, String ACCOUNTNAME, String PASSWORD, String CARDBANK,
			String GOLD, String FRIENDSLISTID){
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c.setAutoCommit(false);
	      System.out.println("Trying to Insert account");
	      
	      stmt = c.createStatement();
	      //create sql string and insert it
	      String sql = "INSERT INTO ACCOUNTS (ID, ACCOUNTNAME, PASSWORD, CARDBANK, GOLD, FRIENDSLISTID)"
                     + "VALUES ('" + ID + "', '" + ACCOUNTNAME + "', '" + PASSWORD  + "', '" + CARDBANK  + "', '" + GOLD
                     + "', '" + FRIENDSLISTID  + "');";
	      
	      stmt.executeUpdate(sql);

	      stmt.close();
	      c.commit();
	      System.out.println("Account added successfully");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	  }
	/*
	 * Cardbank notes, the card bank is a very long string of 1's and 0's
	 * each card has an internal cardbank id, which corresponds to its index
	 * on this long string, if the string has a 1 at that index, then the player
	 * owns that card.
	 */
	
	public ArrayList<Object> selectAttribute(Connection c, String table, String attr, String value, String getattr){
	    Statement stmt = null;
	    ArrayList<Object> results = new ArrayList<Object>();
	    try{
	    	Class.forName("org.sqlite.JDBC");
	    	System.out.println("Trying to select");
	    	
	    	stmt = c.createStatement();
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM " + table + " WHERE " + attr + "='" + value + "';" );
	        while ( rs.next() ) {
	           //System.out.println("got select results ");
	           Object ob = rs.getObject(getattr);
	           results.add(ob);
	        }
	        //System.out.println("Select Successful");
	        
	    	stmt.close();
	    	return results;
	    } catch ( Exception e ) {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	    System.out.println("Select unsuccessful");
	    return null;
	}
	
	public ArrayList<Object> getAllItems(){
	    Connection c = null;
	    Statement stmt = null;
	    ArrayList<Object> results = new ArrayList<Object>();
	    try{
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:POE.db");
	    	//System.out.println("Trying to select");
	    	
	    	stmt = c.createStatement();
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM ITEMS");
	        while(rs.next()){
	        	results.add(rs.getObject(1));
	        }
	    	stmt.close();
	    	c.close();
	    	System.out.println(rs);
	    	return results;
	    	
	    } catch ( Exception e ) {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	    //System.out.println("Select Successful");
	    return null;
	}

	public ArrayList<ArrayList<Object>> getItems(){
		Connection c = null;
	    Statement stmt = null;
	    try{
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:POE.db");
	    	//System.out.println("Trying to select");
	    	
	    	stmt = c.createStatement();
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM ITEMS");
	    	ArrayList<ArrayList<Object>> items = new ArrayList<ArrayList<Object>>();
	    	int itemCount = 0;
	    	while(rs.next()){
	    		ArrayList<Object> item = new ArrayList<Object>();
	    		
	    		for(int i = 7; i < 23; i ++){
	    			if(rs.getObject(i) != null){
	    				//System.out.println("adding items");
	    				item.add(rs.getObject(i));
	    				items.add(item);
	    				itemCount +=1;
	    			}
	    		}
	    	}
	    	System.out.println("Added " + itemCount + " items");
	    	System.out.println("getItems Successful");
	    	stmt.close();
	    	c.close();
	    	return items;
	    } catch ( Exception e ) {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	    return null;
	}
}