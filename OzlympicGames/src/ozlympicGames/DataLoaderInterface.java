package ozlympicGames;

import java.util.ArrayList;

import games.Game;
import games.GameCollection;
import persons.Athlete;
import persons.Person;
import persons.PersonCollection;

//An interface that determines the minimum functionality required to load data.
//See DatabaseLoader for an implementation example
public interface DataLoaderInterface {

	public ArrayList<Person> loadPersonList();

	public ArrayList<Game> loadGameList();

	public String loadGameResults();

	public String loadAthleteResults();

	// Returns true if IO succeeded
	public boolean setAthleteResults(ArrayList<Athlete> athletes);

	// Returns true if IO succeeded
	public boolean addGameResult(Game game);

	public PersonCollection getPersonCollection();

	public GameCollection getGameCollection();

}
