package games;

import persons.*;

public class Cycling extends Game {

	public Cycling(String uniqueGameID) {
		super(uniqueGameID);
	}

	@Override
	public void runGame() {
		// Before we run the game defined in the base class "game", we tell the
		// super athletes what they're competing as.
		for (Athlete competitor : getCompetitors()) {
			if (competitor.getClass().equals(SuperAthlete.class)) {
				((SuperAthlete) competitor).setCompetingAs(Cyclist.class);
			}
		}
		super.runGame();
	}

	@Override
	public String getGameName() {
		return "Cycling";
	}

	@Override
	public Class<?> getAltheteClassTypeForGame() {
		return Cyclist.class;
	}

}
