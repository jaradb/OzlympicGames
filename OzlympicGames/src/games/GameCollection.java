package games;

import java.util.ArrayList;

public class GameCollection {
	
	private ArrayList<Game> gameList;
	
	public GameCollection()
	{
		gameList = new ArrayList<Game>();
		PopulateGames();
	}

	//Create the games. This could be replaced with a data driven solution, but as per
	//the specifications, this implementation is a hard coded data collection.
	public void PopulateGames()
	{
		gameList.add(new Cycling());
		gameList.add(new Swimming());
		gameList.add(new Running());
	}
}
