
import java.sql.*;

public class Database {

		private static User mainUser;
	
		
	
	   public static void createDB(Controler c) {
		   
		   
		   mainUser = c.mainUser;
		   String DB_URL = "jdbc:sqlite:Clavardage - "+ mainUser.getPseudo()+".db";

	   Connection conn = null;
	   Statement stmt = null;
	   try{

	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL);

	      //STEP 4: Execute a query
	      System.out.println("Creating database...");
	      stmt = conn.createStatement();
	      
	      


	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}//end createDB

	   public static void write(Message m, boolean send)
	   {
		   Connection conn = null;
		   Statement stmt = null;
		   try{

			   String DB_URL = "jdbc:sqlite:Clavardage - "+ mainUser.getPseudo()+".db";
			   String tableName;

		      //STEP 3: Open a connection
		      conn = DriverManager.getConnection(DB_URL);

		      //STEP 4: Execute a query
		      stmt = conn.createStatement();
		      
		      //Cree la table correspondant au pseudo
		      if(send)   // MainUser est la source
		    	tableName =  m.getUserDest().getPseudo();
		      else // MainUser recoit le message
			    tableName =  m.getUserSrc().getPseudo();		      
		    	  
		      String sql = "CREATE TABLE IF NOT EXISTS "+ tableName +"(PSEUDO VARCHAR(20),MESSAGE VARCHAR(150))";
		      stmt.executeUpdate(sql);
		      
		      
		      sql = "INSERT INTO " + tableName + " VALUES ('" + m.getUserSrc() + "' , '" + m.getPDU() + "')"; 
		      stmt.executeUpdate(sql);
		      
		      
		      
		   		}
		   catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){
			      }// nothing we can do
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
	   
	   }  
	   
	   
}

