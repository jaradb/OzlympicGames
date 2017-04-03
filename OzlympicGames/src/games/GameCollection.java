package games;

import java.util.ArrayList;

import ozlympicGames.DataLoaderInterface;

//A simple collection class for all the available games at the Ozlympics.
public class GameCollection {

	private ArrayList<Game> gameList;

	public GameCollection(DataLoaderInterface dataLoader) {
		setGameList(dataLoader.loadGameList());
	}

	public ArrayList<Game> getGameList() {
		return gameList;
	}

	private void setGameList(ArrayList<Game> gameList) {
		this.gameList = gameList;
	}

}
