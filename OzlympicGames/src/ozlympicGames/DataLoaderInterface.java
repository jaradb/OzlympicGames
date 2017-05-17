package ozlympicGames;

import java.util.ArrayList;

import games.Game;
import games.GameCollection;
import persons.Athlete;
import persons.Person;
import persons.PersonCollection;

//A basic interface that determines the minimum functionality required to load data.
//Example: A file reading implementation would have to return these two methods.
//See HardcodedDataLoader for an implementation example
public interface DataLoaderInterface {

	public ArrayList<Person> loadPersonList();

	public ArrayList<Game> loadGameList();
	
	public String loadGameResults();
	
	public String loadAthleteResults();
	
	//Returns true if IO succeeded
	public boolean setAthleteResults(ArrayList<Athlete> athletes);
	
	//Returns true if IO succeeded
	public boolean addGameResult(Game game);
	
	public PersonCollection getPersonCollection();
	public GameCollection getGameCollection();
	
	

}
