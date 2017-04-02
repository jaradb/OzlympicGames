package ozlympicGames;

import java.util.ArrayList;

import games.*;
import persons.*;

public class Driver {
	
	private GameCollection games;
	private PersonCollection people;
	private HardcodedDataLoader loader;

	public Driver()
	{
		loader = new HardcodedDataLoader();
		
		InitialiseData();
	}
	
	public void InitialiseData()
	{
		games = new GameCollection(loader);
		people = new PersonCollection(loader);
	}
	
	public void DisplayMenu()
	{
		System.out.println("This is the menu");
	}
}
