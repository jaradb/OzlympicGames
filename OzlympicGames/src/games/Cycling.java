package games;

import persons.*;

public class Cycling extends Game {

	@Override
	public void runGame() {
		for(Athlete competitor : getCompetitors())
		{
			if(competitor.getClass().equals(SuperAthlete.class))
			{
				((SuperAthlete)competitor).setCompetingAs(Cyclist.class);
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
