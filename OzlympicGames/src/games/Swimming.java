package games;

import persons.*;

public class Swimming extends Game {

	public Swimming(String uniqueGameID) {
		super(uniqueGameID);
	}

	@Override
	public void runGame() {
		// Before we run the game defined in the base class "game", we tell the
		// super athletes what they're competing as.
		for (Athlete competitor : getCompetitors()) {
			if (competitor.getClass().equals(SuperAthlete.class)) {
				((SuperAthlete) competitor).setCompetingAs(Swimmer.class);
			}
		}

		super.runGame();
	}

	@Override
	public String getGameName() {
		return "Swimming";
	}

	@Override
	public Class<?> getAltheteClassTypeForGame() {
		return Swimmer.class;
	}
}
