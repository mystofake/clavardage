
import java.sql.*;

public class Database {

	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/mysql?serverTimezone=UTC";

	   //  Database credentials
	   static final String USER = "Clavardage";
	   static final String PASS = "guerrier";
	   
	   static User mainUser;
	   
	   
	   
	   public static void createDB(Controler c) {

	   Connection conn = null;
	   Statement stmt = null;
	   mainUser = c.mainUser;
	   try{
	      //STEP 2: Register JDBC driver
	      //Class.forName("sun.jdbc.odbcJdbcOdbcDriver");
		     
		  Class.forName("com.mysql.cj.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating database...");
	      stmt = conn.createStatement();
	      
	      String sql = "CREATE DATABASE IF NOT EXISTS CLAVARDAGE ";
	      stmt.executeUpdate(sql);
	      System.out.println("Database created successfully...");
	      
	      sql = "use CLAVARDAGE";
	      stmt.executeUpdate(sql);
	      
	      //Cree la table correspondant au pseudo
	      sql = "CREATE TABLE IF NOT EXISTS "+ c.mainUser.getPseudo()+"(PSEUDO VARCHAR(20),MESSAGE VARCHAR(150))";
	      stmt.executeUpdate(sql);

	      //sql = "INSERT INTO "+ user.getPseudo()+" VALUES ('EVAN','BONJOUR ! ')";
	      //stmt.executeUpdate(sql);

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
	   
	   public static void write(Message m)
	   {
		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver			     
			  Class.forName("com.mysql.cj.jdbc.Driver");

		      //STEP 3: Open a connection
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);

		      //STEP 4: Execute a query
		      stmt = conn.createStatement();
		      
		      String sql = "use CLAVARDAGE";
		      stmt.executeUpdate(sql);
		      
		      sql = "INSERT INTO " + mainUser.getPseudo() + " VALUES (" + m.getUserDest() + " , " + m.getPDU() + ")";
		      
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
