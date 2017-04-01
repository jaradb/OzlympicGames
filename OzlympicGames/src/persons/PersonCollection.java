package persons;

import java.util.ArrayList;


public class PersonCollection {
	
	private ArrayList<Person> personList;
	
	public PersonCollection()
	{
		personList = new ArrayList<Person>();
		PopulatePeople();
	}

	public void PopulatePeople()
	{
		//Add some officials
		personList.add(new Official("official01", "Jon Arbuckle", 30));
		personList.add(new Official("official02", "Toby Fox", 25));
		
		//Add some cyclists
		personList.add(new Cyclist("cyclist01", "Bike McBikey", 32, Athlete.RepresentingState.NSW));
		personList.add(new Cyclist("cyclist02", "Cycle McCycle", 18, Athlete.RepresentingState.VIC));
		
		//etc
		
	}
}
