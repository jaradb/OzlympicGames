package ozlympicGames;

import java.util.ArrayList;

import games.*;
import persons.*;
import persons.Athlete.RepresentingState;

public class HardcodedDataLoader implements DataLoaderInterface {

	//Returns a hardcoded list of athletes of all types, and officials.
	@Override
	public ArrayList<Person> LoadPersonList() {

		ArrayList<Person> personList = new ArrayList<Person>();
	
		personList.add(new SuperAthlete("yes", "bepis", 42, RepresentingState.VIC));

		return personList;
	}

	//Returns a hardcoded list of games.
	@Override
	public ArrayList<Game> LoadGameList() {
		
		ArrayList<Game> gameList = new ArrayList<Game>();
		
		gameList.add(new Swimming());
		
		return gameList;
	}

}
