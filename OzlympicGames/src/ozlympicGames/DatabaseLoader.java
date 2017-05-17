package ozlympicGames;
import java.lang.reflect.Constructor;
import java.sql.*;
import java.util.ArrayList;

import games.Game;
import games.GameCollection;
import persons.*;
import persons.Athlete.RepresentingState;

public class DatabaseLoader implements DataLoaderInterface
{
	PersonCollection persons;
	GameCollection games;

	Connection dbConnection = null;
	Statement dbStatement = null;
	ResultSet dbResultSet = null;
	
	public DatabaseLoader()
	{

		try {
			OpenDatabaseConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		persons = new PersonCollection(this);
		games = new GameCollection(this);

	}
	
	static boolean TestDBConnectionIsValid()
	{
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	void OpenDatabaseConnection() throws SQLException
	{
		dbConnection = DriverManager.getConnection("jdbc:sqlite:ozlymics.db");
		
		System.out.println("Opened database successfully");
	}

	@Override
	public ArrayList<Person> loadPersonList() {
		
		ArrayList<Person> personList = new ArrayList<Person>();
		
		try {
			
			//Query the database's Athletes table to get all athletes.
			dbStatement = dbConnection.createStatement();

			dbResultSet = dbStatement.executeQuery( "SELECT * FROM Athletes;" );
		    
		    while ( dbResultSet.next() )
		    {
		    	String id = dbResultSet.getString("ID");
		    	String type = dbResultSet.getString("Type");
		    	String name = dbResultSet.getString("Name");
		    	int age = dbResultSet.getInt("Age");
		    	String state = dbResultSet.getString("State");
		    	
		    	
		    	Class<?> classType;
		    	Constructor<?> constructorParameters;
		    	Object object = null;
		    	Athlete athleteEntry = null;
		    	
		    	
				try {
					
					classType = Class.forName("persons." + type);

					constructorParameters = classType.getConstructor(String.class, String.class, int.class, RepresentingState.class);
			    	object = constructorParameters.newInstance(id, name, age, RepresentingState.valueOf(state));
			    	
			    	athleteEntry = (Athlete)object;
		    	
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				personList.add(athleteEntry);
		    }
		    
			dbResultSet = dbStatement.executeQuery( "SELECT * FROM Officials;" );
		    
		    while ( dbResultSet.next() )
		    {
		    	String id = dbResultSet.getString("ID");
		    	String name = dbResultSet.getString("Name");
		    	int age = dbResultSet.getInt("Age");
		    	
		    	Class<?> classType;
		    	Constructor<?> constructorParameters;
		    	Object object = null;
		    	Official officialEntry = null;
		    	
		    	
				try {
					
					classType = Class.forName("persons.Official");

					constructorParameters = classType.getConstructor(String.class, String.class, int.class);
			    	object = constructorParameters.newInstance(id, name, age);
			    	
			    	officialEntry = (Official)object;
		    	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				personList.add(officialEntry);
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return personList;
	}

	@Override
	public ArrayList<Game> loadGameList() {
		
		ArrayList<Game> gameList = new ArrayList<Game>();
		
		try {
			
			dbResultSet = dbStatement.executeQuery( "SELECT * FROM Games;" );
		    
		    while ( dbResultSet.next() )
		    {
		    	String id = dbResultSet.getString("GameID");
		    	String type = dbResultSet.getString("GameType");
		    	
		    	Class<?> classType;
		    	Constructor<?> constructorParameters;
		    	Object object = null;
		    	Game gameEntry = null;
		    	
				try {
					
					classType = Class.forName("games." + type);
	
					constructorParameters = classType.getConstructor(String.class);
			    	object = constructorParameters.newInstance(id);
			    	
			    	gameEntry = (Game)object;
		    	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				gameList.add(gameEntry);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return gameList;
	}

	@Override
	public String loadGameResults() {
		String allGameResults = "";

		try {

			dbResultSet = dbStatement.executeQuery("SELECT * FROM GameResults;");
			
		    while ( dbResultSet.next() )
		    {
		    	String gameID = dbResultSet.getString("GameID");
		    	String officialID = dbResultSet.getString("OfficialID");
		    	String date = dbResultSet.getString("Date");
		    	
		    	allGameResults += "Game ID: " + gameID + " Official ID: " + officialID + " Game Date: " + date + "\n";
		    	
		    	Statement gameStatement = dbConnection.createStatement();
		    	ResultSet dbGameResultSet = gameStatement.executeQuery("SELECT * FROM AthleteScores WHERE AthleteScores.GameID = '" + gameID + "';");
			    while ( dbGameResultSet.next() )
			    {
			    	String athleteID = dbGameResultSet.getString("AthleteID");
			    	String time = dbGameResultSet.getString("Time");
			    	int score = dbGameResultSet.getInt("Score");
			    	
			    	allGameResults += "AthleteID: " + athleteID + " Time: " + time + " Score: " + score;
			    }
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allGameResults;
	}

	@Override
	public String loadAthleteResults() {
		String athleteResults = "";

		try {

			dbResultSet = dbStatement.executeQuery("SELECT * FROM AthleteResults;");

			while (dbResultSet.next()) {
				
		    	String athleteID = dbResultSet.getString("AthleteID");
		    	String score = dbResultSet.getString("Score");
				
				athleteResults += "AthleteID: " + athleteID + "\tAthlete Name: " + persons.getPersonByID(athleteID).getName() + "\tScore:" + score + "\n";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return athleteResults;
	}

	@Override
	public boolean setAthleteResults(ArrayList<Athlete> athletes) {
		
		//Remove the old results first
		try {
			dbStatement.execute("DELETE FROM AthleteResults;");
			
			 String sql = "INSERT INTO AthleteResults (AthleteID, Score) VALUES";
			
			for(Athlete athlete : athletes)
			{
			   
				sql += "('" + athlete.getUniqueID() + "', " + athlete.getCurrentPointCount() + " ),"; 
			    	    
	    	   
			}
			
			//Pop off the last comma
			sql = sql.substring(0, sql.length()-1);
			
			sql += ";";
			
			 dbStatement.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;	//Operation failed
		}
		
		return true;	//Operation succeeded
	}

	@Override
	public boolean addGameResult(Game game) {
		
		
		
		String sql = "INSERT INTO GameResults (GameID, OfficialID, Date) VALUES ('" + game.getUniqueGameID() + 
																		"', '" + game.getReferee().getUniqueID() + 
																		"', '" + game.getGameDate() +"');";
		
		 try {
			dbStatement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		 sql = "INSERT INTO AthleteScores (GameID, AthleteID, Time, Score) VALUES ";
		 
			
		for(Athlete athlete : game.getCompetitors())
		{
		
			
			sql += "('" + game.getUniqueGameID() + "', '" + 
							athlete.getUniqueID() + "', '" +
							athlete.getLastTimeRecorded() +", " +
							athlete.getCurrentPointCount() + "),";

		    
		}
		
		//Pop off the last comma
		sql = sql.substring(0, sql.length()-1);
		
		sql += ";";
		
		return true;
	}

	@Override
	public PersonCollection getPersonCollection() {
		return persons;
	}

	@Override
	public GameCollection getGameCollection() {
		return games;
	}
}
