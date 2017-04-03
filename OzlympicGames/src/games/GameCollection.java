package games;

import java.util.ArrayList;

import ozlympicGames.DataLoaderInterface;

public class GameCollection {
	
	private ArrayList<Game> gameList;
	
	public GameCollection(DataLoaderInterface dataLoader){
		setGameList(dataLoader.LoadGameList());
	}

	public ArrayList<Game> getGameList() {
		return gameList;
	}

	private void setGameList(ArrayList<Game> gameList) {
		this.gameList = gameList;
	}

}
