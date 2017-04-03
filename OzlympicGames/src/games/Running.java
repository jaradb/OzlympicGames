package games;

import persons.*;

public class Running extends Game {

	@Override
	public void runGame() {
		for(Athlete competitor : getCompetitors())
		{
			if(competitor.getClass().equals(SuperAthlete.class))
			{
				((SuperAthlete)competitor).setCompetingAs(Sprinter.class);
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
