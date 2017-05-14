package ozlympicGames;
import java.sql.*;

public class DatabaseController 
{
	public DatabaseController()
	{
		OpenDatabaseConnection();
	}
	
	void OpenDatabaseConnection()
	{
	   Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	}
}
