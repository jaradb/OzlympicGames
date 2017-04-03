package persons;

import java.util.ArrayList;

import ozlympicGames.DataLoaderInterface;

public class PersonCollection {

	private ArrayList<Person> personList;

	public PersonCollection(DataLoaderInterface dataLoader) {
		personList = dataLoader.LoadPersonList();
	}

	public ArrayList<Person> GetPersonsByType(Class<?> classType) {
		ArrayList<Person> subList = new ArrayList<Person>();

		for (Person person : personList) {
			if (person.getClass().equals(classType)) {
				subList.add(person);
			}
		}

		return subList;
	}

	public Person GetPersonByID(String uniqueID) {
		for (Person person : personList) {
			if (person.getUniqueID().equals(uniqueID))
				return person;
		}

		// Couldn't find anybody by that ID.
		return null;
	}

}
