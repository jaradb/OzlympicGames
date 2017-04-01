package ozlympicGames;

import games.GameCollection;
import persons.PersonCollection;

public class Driver {
	
	private GameCollection games;
	private PersonCollection people;

	public Driver()
	{
		InitialiseData();
	}
	
	public void InitialiseData()
	{
		games = new GameCollection();
		people = new PersonCollection();
	}
	
	public void DisplayMenu()
	{
		System.out.println("This is the menu");
	}
}
