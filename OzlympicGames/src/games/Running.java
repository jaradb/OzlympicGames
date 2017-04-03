package games;

import persons.*;

public class Running extends Game {

	public Running(String uniqueGameID) {
		super(uniqueGameID);
	}

	@Override
	public void runGame() {
		//Before we run the game defined in the base class "game", we tell the super
		//athletes what they're competing as.
		for (Athlete competitor : getCompetitors()) {
			if (competitor.getClass().equals(SuperAthlete.class)) {
				((SuperAthlete) competitor).setCompetingAs(Sprinter.class);
			}
		}
		super.runGame();
	}

	@Override
	public String getGameName() {
		return "Running";
	}

	@Override
	public Class<?> getAltheteClassTypeForGame() {
		return Sprinter.class;
	}
}
