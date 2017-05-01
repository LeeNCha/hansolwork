package webcrawl;

//STEP 1. Import required packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
//DB Connection
public class DB {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static String DB_URL = "jdbc:mysql://localhost:3306/";
	
	//  Database credentials
   static final String USER = "root";
   static final String PASS = "qwerty123";
	
	public Connection conn = null;
	public Statement sta =  null;
	
	public DB() {
		try {
			//STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);
			
			//STEP 3: Open a connection
		    System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
		    //STEP 4: Execute a query
		    System.out.println("Creating database...");
		    sta = conn.createStatement();
		    
		    String sql = "CREATE DATABASE IF NOT EXISTS LIBINFOSYS";		  
		    sta.executeUpdate(sql);
		    System.out.println("Database created successfully...");
		    
		    System.out.println("Connecting to a selected database...");
		    DB_URL = "jdbc:mysql://localhost:3306/LIBINFOSYS";
		    conn = DriverManager.getConnection(DB_URL, USER, PASS);
		    System.out.println("Connected database successfully...");
		    
		    System.out.println("Creating table in given database...");
		    sta = conn.createStatement();
		    
		    sql = "CREATE TABLE IF NOT EXISTS crawl_p"
		    		+ "(idcrawl_p INTEGER NOT NULL AUTO_INCREMENT,"
		    		+ "crawl_name VARCHAR(45) NOT NULL,"
		    		+ "crawl_days VARCHAR(45),"
		    		+ "crawl_author VARCHAR(45),"
		    		+ "PRIMARY KEY(idcrawl_p) )";
		   
		    sta.executeUpdate(sql);
		    System.out.println("Created table in given database...");
		    
		} catch (SQLException e) {
			//Handle errors for JDBC
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			//Handle errors for Class.forName
			e.printStackTrace();
		}
	}
	
 
	public ResultSet runSql(String sql) throws SQLException {
		sta = conn.createStatement();
		return sta.executeQuery(sql);
	}
 
	public boolean runSql2(String sql) throws SQLException {
		sta = conn.createStatement();
		return sta.execute(sql);
	}
 
	@Override
	protected void finalize() throws Throwable {
		if (conn != null || !conn.isClosed()) {
			conn.close();
		}
	}
}
