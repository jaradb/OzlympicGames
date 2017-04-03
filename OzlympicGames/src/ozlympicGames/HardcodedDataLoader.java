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
		
		personList.add(new Official("official1", "Bapis", 15));
	
		personList.add(new SuperAthlete("yes1", "bepis1", 42, RepresentingState.VIC));
		personList.add(new SuperAthlete("yes2", "bepis2", 11, RepresentingState.ACT));
		personList.add(new SuperAthlete("yes3", "bepis3", 22, RepresentingState.WA));
		personList.add(new SuperAthlete("yes4", "bepis4", 72, RepresentingState.QLD));

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
