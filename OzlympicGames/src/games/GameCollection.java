package games;

import java.util.ArrayList;

import ozlympicGames.DataLoaderInterface;

public class GameCollection {
	
	private ArrayList<Game> gameList;
	
	public GameCollection(DataLoaderInterface dataLoader){
		gameList = dataLoader.LoadGameList();
	}

}
