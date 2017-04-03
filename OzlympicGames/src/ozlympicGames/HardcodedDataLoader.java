package ozlympicGames;

import java.util.ArrayList;

import games.*;
import persons.*;
import persons.Athlete.RepresentingState;

public class HardcodedDataLoader implements DataLoaderInterface {

	// Returns a hardcoded list of athletes of all types, and officials.
	// All names randomly generated.
	@Override
	public ArrayList<Person> LoadPersonList() {
		ArrayList<Person> personList = new ArrayList<Person>();

		// Add four officials
		personList.add(new Official("official1", "Samuel Darcie", 23));
		personList.add(new Official("official2", "Marquis Ollie", 45));
		personList.add(new Official("official3", "Janyce Elvie", 51));
		personList.add(new Official("official4", "Larhonda Cyril", 42));

		// Add eight cyclists
		personList.add(new Cyclist("cyclist1", "Lannie Janie", 18, RepresentingState.VIC));
		personList.add(new Cyclist("cyclist2", "Ervin Lynn", 22, RepresentingState.VIC));
		personList.add(new Cyclist("cyclist3", "Lorenzo Nubia", 23, RepresentingState.VIC));
		personList.add(new Cyclist("cyclist4", "Temple Dannielle", 19, RepresentingState.VIC));
		personList.add(new Cyclist("cyclist5", "Yvonne Micaela", 31, RepresentingState.VIC));
		personList.add(new Cyclist("cyclist6", "Kasey Britni", 26, RepresentingState.VIC));
		personList.add(new Cyclist("cyclist7", "Brandie Otilia", 24, RepresentingState.VIC));
		personList.add(new Cyclist("cyclist8", "Fawn Charisse", 32, RepresentingState.VIC));

		// Add eight swimmers
		personList.add(new Swimmer("swimmer1", "Doretta Estela", 17, RepresentingState.VIC));
		personList.add(new Swimmer("swimmer2", "Clark Charlott", 19, RepresentingState.VIC));
		personList.add(new Swimmer("swimmer3", "Adolfo Raymundo", 21, RepresentingState.VIC));
		personList.add(new Swimmer("swimmer4", "Hugo Timmy", 22, RepresentingState.VIC));
		personList.add(new Swimmer("swimmer5", "Alda Lavonda", 25, RepresentingState.VIC));
		personList.add(new Swimmer("swimmer6", "Marie Casie", 30, RepresentingState.VIC));
		personList.add(new Swimmer("swimmer7", "Hyo Landon", 29, RepresentingState.VIC));
		personList.add(new Swimmer("swimmer8", "Mui Ardath", 27, RepresentingState.VIC));

		// Add eight sprinters
		personList.add(new Sprinter("sprinter1", "Samatha Earle", 19, RepresentingState.VIC));
		personList.add(new Sprinter("sprinter2", "Dalila Armanda", 20, RepresentingState.VIC));
		personList.add(new Sprinter("sprinter3", "Rusty Alesha", 31, RepresentingState.VIC));
		personList.add(new Sprinter("sprinter4", "Piper Pauline", 18, RepresentingState.VIC));
		personList.add(new Sprinter("sprinter5", "Brandee Katrina", 25, RepresentingState.VIC));
		personList.add(new Sprinter("sprinter6", "Logan Cecilia", 33, RepresentingState.VIC));
		personList.add(new Sprinter("sprinter7", "Shu Rubie", 18, RepresentingState.VIC));
		personList.add(new Sprinter("sprinter8", "Theodore Nicole", 22, RepresentingState.VIC));

		// Add four super athletes (can compete in all events)
		personList.add(new SuperAthlete("superAthlete1", "Jorge Titus", 23, RepresentingState.VIC));
		personList.add(new SuperAthlete("superAthlete2", "Verlene Providencia", 31, RepresentingState.VIC));
		personList.add(new SuperAthlete("superAthlete3", "Nery Jon", 25, RepresentingState.VIC));
		personList.add(new SuperAthlete("superAthlete4", "Jeanine Wilfredo", 32, RepresentingState.VIC));

		return personList;
	}

	// Returns a hardcoded list of games.
	@Override
	public ArrayList<Game> LoadGameList() {
		ArrayList<Game> gameList = new ArrayList<Game>();
		gameList.add(new Swimming("GameID_Swimming"));
		gameList.add(new Cycling("GameID_Cycling"));
		gameList.add(new Running("GameID_Running"));
		return gameList;
	}
}
