package ozlympicGames;

import java.util.ArrayList;

import games.Game;
import persons.Person;

//A basic interface that determines the minimum functionality required to load data.
//Example: A file reading implementation would have to return these two methods.
//See HardcodedDataLoader for an implementation example
public interface DataLoaderInterface {

	public ArrayList<Person> LoadPersonList();

	public ArrayList<Game> LoadGameList();

}
