package ozlympicGames;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.StringTokenizer;

import games.Game;
import games.GameCollection;
import persons.Athlete;
import persons.Official;
import persons.Person;
import persons.PersonCollection;
import persons.Athlete.RepresentingState;

//The FileLoader implementation of DataLoaderInterface. 
//Loads data from plain text files by tokenizing each line of the file as a string
//to extract data, provided it is formatted correctly.

public class FileLoader implements DataLoaderInterface {

	static String participantsFilename = "participants.txt";
	static String gameResultsFilename = "gameResults.txt";

	static String officialsFilename = "officials.txt";
	static String athleteResultsFilename = "athleteResults.txt";
	static String gameTypesFilename = "gameTypes.txt";

	PersonCollection persons;
	GameCollection games;

	public FileLoader() {
		persons = new PersonCollection(this);
		games = new GameCollection(this);
	}

	@Override
	public ArrayList<Person> loadPersonList() {

		ArrayList<Person> personList = new ArrayList<Person>();

		File file = new File(participantsFilename);
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String fileLine;

		try {
			while ((fileLine = reader.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(fileLine, "\t");

				// If the line doesn't contain 5 attributes then it may be
				// corrupted, so skip it
				if (tokenizer.countTokens() != 5) {
					continue;
				}

				String id = tokenizer.nextToken();
				String type = tokenizer.nextToken();
				String name = tokenizer.nextToken();
				int age = Integer.parseInt(tokenizer.nextToken());
				String state = tokenizer.nextToken();

				Class<?> classType;
				Constructor<?> constructorParameters;
				Object object = null;
				Athlete athleteEntry = null;

				try {

					classType = Class.forName("persons." + type);
					constructorParameters = classType.getConstructor(String.class, String.class, int.class,
							RepresentingState.class);
					object = constructorParameters.newInstance(id, name, age, RepresentingState.valueOf(state));

					athleteEntry = (Athlete) object;

				} catch (Exception e) {
					e.printStackTrace();
				}

				personList.add(athleteEntry);

			}

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		file = new File(officialsFilename);

		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			while ((fileLine = reader.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(fileLine, "\t");

				// If the line doesn't contain 3 attributes then it may be
				// corrupted, so skip it
				if (tokenizer.countTokens() != 3) {
					continue;
				}

				String id = tokenizer.nextToken();
				String name = tokenizer.nextToken();
				int age = Integer.parseInt(tokenizer.nextToken());

				Class<?> classType;
				Constructor<?> constructorParameters;
				Object object = null;
				Official officialEntry = null;

				try {
					classType = Class.forName("persons.Official");
					constructorParameters = classType.getConstructor(String.class, String.class, int.class);
					object = constructorParameters.newInstance(id, name, age);
					officialEntry = (Official) object;

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				personList.add(officialEntry);
			}

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return personList;
	}

	@Override
	public ArrayList<Game> loadGameList() {
		ArrayList<Game> gameList = new ArrayList<Game>();

		File file = new File(gameTypesFilename);
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String fileLine;

		try {
			while ((fileLine = reader.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(fileLine, "\t");

				// If the line doesn't contain 2 attributes then it may be
				// corrupted, so skip it
				if (tokenizer.countTokens() != 2) {
					continue;
				}

				String id = tokenizer.nextToken();
				String type = tokenizer.nextToken();

				Class<?> classType;
				Constructor<?> constructorParameters;
				Object object = null;
				Game gameEntry = null;

				try {

					classType = Class.forName("games." + type);

					constructorParameters = classType.getConstructor(String.class);
					object = constructorParameters.newInstance(id);

					gameEntry = (Game) object;

				} catch (Exception e) {
					e.printStackTrace();
				}

				gameList.add(gameEntry);
			}

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return gameList;
	}

	@Override
	public String loadGameResults() {

		File file = new File(gameResultsFilename);
		BufferedReader reader = null;

		String gameResults = "";

		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String fileLine;

		try {
			while ((fileLine = reader.readLine()) != null) {
				gameResults += fileLine + "\n";
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return gameResults;
	}

	@Override
	public String loadAthleteResults() {
		File file = new File(athleteResultsFilename);
		BufferedReader reader = null;

		String athleteResults = "";

		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String fileLine;

		try {
			while ((fileLine = reader.readLine()) != null) {
				athleteResults += fileLine + "\n";
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return athleteResults;
	}

	@Override
	public boolean setAthleteResults(ArrayList<Athlete> athletes) {

		File file = new File(athleteResultsFilename);

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			for (int i = 0; i < athletes.size(); i++) {
				writer.write("AthleteID: " + athletes.get(i).getUniqueID() + "\tAthlete Name: "
						+ persons.getPersonByID(athletes.get(i).getUniqueID()).getName() + "\tScore:"
						+ athletes.get(i).getCurrentPointCount() + "\n");
			}

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean addGameResult(Game game) {
		File file = new File(gameResultsFilename);

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file, true));
			writer.write(game.getGameResult());

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
