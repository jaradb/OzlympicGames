package persons;

import java.util.ArrayList;

import ozlympicGames.DataLoaderInterface;

public class PersonCollection {

	private ArrayList<Person> personList;

	public PersonCollection(DataLoaderInterface dataLoader) {
		personList = dataLoader.loadPersonList();
	}

	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> getPersonsByType(Class<?> classType) {
		
		ArrayList<T> subList = new ArrayList<T>();
	

		for (Person person : personList) {

			if(person.getClass().equals(classType))
			{
				subList.add((T)person);
			}
		}

		return subList;
	}

	public Person getPersonByID(String uniqueID) {
		for (Person person : personList) {
			if (person.getUniqueID().equals(uniqueID))
				return person;
		}

		// Couldn't find anybody by that ID.
		return null;
	}

}
