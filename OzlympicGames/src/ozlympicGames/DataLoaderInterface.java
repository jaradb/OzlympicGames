package ozlympicGames;

import java.util.ArrayList;

import games.Game;
import persons.Person;

public interface DataLoaderInterface {
	
	public ArrayList<Person> LoadPersonList();
	public ArrayList<Game> LoadGameList();
	
}
